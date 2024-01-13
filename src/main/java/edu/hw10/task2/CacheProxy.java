package edu.hw10.task2;

import edu.hw10.task2.annotation.Cache;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheProxy implements InvocationHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Object object;
    private final Path persistDirectory;
    private final Map<Method, Map<List<Object>, Object>> cache;

    public CacheProxy(Object object, Path persistDirectory) {
        this.object = object;
        this.persistDirectory = persistDirectory;
        cache = new HashMap<>();
    }

    public static <T> T create(T obj, Class<?> objectClass, Path persistDirectory) {
        return (T) Proxy.newProxyInstance(
            objectClass.getClassLoader(),
            objectClass.getInterfaces(),
            new CacheProxy(obj, persistDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (!cache.containsKey(method)) {
                cache.put(method, new HashMap<>());
            }

            List<Object> argsList = Arrays.stream(args).toList();

            if (cache.get(method).containsKey(argsList)) {
                return cache.get(method).get(argsList);
            } else {
                Object result = method.invoke(object, args);

                cache.get(method).put(argsList, result);

                if (method.getAnnotation(Cache.class).persist()) {
                    try {
                        persistData(method, argsList, result);
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                }

                return result;
            }
        }

        return method.invoke(object, args);
    }

    private void persistData(Method method, List<Object> argsList, Object result) throws IOException {
        final String fileName =
            (object != null ? object.toString() : "null") + "_" + method.getName() + "_" + argsList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("+"));

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(
            persistDirectory.toString(),
            fileName + ".cache"
        )))) {
            out.writeObject(result);
        }
    }
}
