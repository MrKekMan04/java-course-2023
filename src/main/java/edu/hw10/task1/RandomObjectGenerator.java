package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.security.SecureRandom;
import java.util.Arrays;

public class RandomObjectGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();

    public <T> T nextObject(Class<T> objectClass)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return nextObject(objectClass, null);
    }

    public <T> T nextObject(Class<T> objectClass, String fabricMethodName)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T instance;

        if (objectClass.isRecord()) {
            Class<?>[] fieldTypes =
                Arrays.stream(objectClass.getRecordComponents())
                    .map(RecordComponent::getType)
                    .toArray(Class<?>[]::new);

            Object[] parameters = Arrays.stream(objectClass.getDeclaredFields())
                .map(RandomObjectGenerator::getValueForField)
                .toArray();

            instance = objectClass.getDeclaredConstructor(fieldTypes).newInstance(parameters);
        } else {
            Constructor<T> constructor = objectClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            if (fabricMethodName != null && !fabricMethodName.isEmpty()) {
                instance = (T) objectClass.getDeclaredMethod(fabricMethodName).invoke(null);
            } else {
                instance = constructor.newInstance();
            }

            for (Field field : objectClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Min.class) || field.isAnnotationPresent(Max.class)
                    || field.isAnnotationPresent(NotNull.class)) {
                    field.setAccessible(true);

                    field.set(instance, getValueForField(field));
                }
            }
        }

        return instance;
    }

    private static Object getValueForField(Field field) {
        Class<?> type = field.getType();

        Object result = null;

        if (type.equals(int.class) || type.equals(Integer.class)) {
            result = RANDOM.nextInt(
                field.isAnnotationPresent(Min.class) ? field.getAnnotation(Min.class).value() : Integer.MIN_VALUE,
                field.isAnnotationPresent(Max.class) ? field.getAnnotation(Max.class).value() : Integer.MAX_VALUE
            );
        } else if (type.equals(String.class) && field.isAnnotationPresent(NotNull.class)) {
            result = "value";
        }

        return result;
    }
}
