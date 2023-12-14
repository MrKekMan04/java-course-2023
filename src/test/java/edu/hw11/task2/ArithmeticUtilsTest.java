package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticUtilsTest {
    @Test
    public void assertThatSumInterceptWithMultiply() {
        ByteBuddyAgent.install();

        try (DynamicType.Unloaded<ArithmeticUtils> unloadedClass = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(ClassDelegate.class))
            .make()) {

            unloadedClass.load(getClass().getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

            assertEquals(1, ArithmeticUtils.sum(1, 1));
            assertEquals(6, ArithmeticUtils.sum(2, 3));
            assertEquals(9, ArithmeticUtils.sum(3, 3));
        }
    }

    static final class ClassDelegate {
        public static int mul(int a, int b) {
            return a * b;
        }
    }
}
