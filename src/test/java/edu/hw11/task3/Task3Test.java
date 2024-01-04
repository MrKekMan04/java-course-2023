package edu.hw11.task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    public void assertThatFibonacciCalculatesRight()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try (DynamicType.Unloaded<Object> unloadedClass = new ByteBuddy()
            .subclass(Object.class)
            .name("FibCalculator")
            .defineMethod("fib", long.class, Modifier.PUBLIC + Modifier.STATIC)
            .withParameter(int.class, "n")
            .intercept(new FibonacciImpl())
            .make()) {

            Class<?> loadedClass = unloadedClass.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

            Method method = loadedClass.getDeclaredMethod("fib", int.class);

            assertEquals(1L, method.invoke(null, 0));
            assertEquals(1L, method.invoke(null, 1));
            assertEquals(2L, method.invoke(null, 2));
            assertEquals(3L, method.invoke(null, 3));
            assertEquals(5L, method.invoke(null, 4));
        }
    }
}
