package com.dsysme.exercises.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter implements Runnable {

    public static final int MAX_PER_COUNTER = 25000000;
    public static final int N_THREADS = 4;
    private AtomicInteger atomicCounter = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < MAX_PER_COUNTER; ++i) {
            atomicCounter.incrementAndGet();
        }
    }

    public int getCurrentCount() {
        return atomicCounter.intValue();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        ThreadSafeCounter counter = new ThreadSafeCounter();

        for (int i = 0; i < N_THREADS; ++i) {
            executorService.submit(counter);
        }

        // https://stackoverflow.com/questions/1250643/how-to-wait-for-all-threads-to-finish-using-executorservice
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // What should I do hear?
        }

        System.out.println("Current count:" + counter.getCurrentCount());
        executorService.shutdown();
    }

}
