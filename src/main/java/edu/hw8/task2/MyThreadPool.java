package edu.hw8.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class MyThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Worker[] workers;
    private final Semaphore semaphore;
    private final ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<>();

    private MyThreadPool(int threadsCount) {
        if (threadsCount <= 0) {
            throw new IllegalArgumentException("Threads count must be more then 0");
        }

        workers = new Worker[threadsCount];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker();
        }

        semaphore = new Semaphore(threadsCount);
    }

    public static MyThreadPool create(int threadsCount) {
        return new MyThreadPool(threadsCount);
    }

    @Override
    public void close() {
        Arrays.stream(workers).forEach(Thread::interrupt);
    }

    @Override
    public void start() {
        Arrays.stream(workers).forEach(Thread::start);

        for (Worker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                try {
                    semaphore.acquire();

                    synchronized (tasks) {
                        if (tasks.isEmpty()) {
                            break;
                        }

                        task = tasks.poll();
                    }

                    task.run();
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                } finally {
                    semaphore.release();
                }
            }
        }
    }
}
