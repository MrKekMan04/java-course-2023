package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
@SuppressWarnings("UncommentedMain")
public class ReflectionBenchmark {
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaMetaFactory;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.minutes(1))
            .measurementIterations(1)
            .measurementTime(TimeValue.minutes(2))
            .resultFormat(ResultFormatType.CSV)
            .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("John", "Watson");

        method = Student.class.getDeclaredMethod("name");

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.unreflect(method);

        lambdaMetaFactory = (Function<Student, String>) LambdaMetafactory.metafactory(
                lookup,
                "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                methodHandle.type()
            )
            .getTarget()
            .invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        blackhole.consume(student.name());
    }

    @Benchmark
    public void invokeReflection(Blackhole blackhole) throws InvocationTargetException, IllegalAccessException {
        blackhole.consume(method.invoke(student));
    }

    @Benchmark
    public void invokeMethodHandle(Blackhole blackhole) throws Throwable {
        blackhole.consume(methodHandle.invoke(student));
    }

    @Benchmark
    public void invokeLambdaMetaFactory(Blackhole blackhole) {
        blackhole.consume(lambdaMetaFactory.apply(student));
    }
}
