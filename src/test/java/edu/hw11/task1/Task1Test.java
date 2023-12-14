package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {
    @Test
    public void assertThatDynamicClassToStringReturnedRightString()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try (DynamicType.Unloaded<Object> unloadedClass = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()) {

            Class<?> loadedClass = unloadedClass.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

            assertEquals("Hello, ByteBuddy!", loadedClass.getDeclaredConstructor().newInstance().toString());
        }
    }
}
