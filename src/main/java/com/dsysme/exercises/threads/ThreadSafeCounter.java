package com.dsysme.exercises.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter implements Runnable {

    private AtomicInteger atomicCounter = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 25000000; ++i) {
            atomicCounter.incrementAndGet();
        }
    }

    public int getCurrentCount() {
        return atomicCounter.intValue();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ThreadSafeCounter counter = new ThreadSafeCounter();

        for (int i = 0; i < 4; ++i) {
            executorService.submit(counter);
        }

        while(counter.getCurrentCount() < 25000000 * 4) {
            continue;
        }

        System.out.println("Current count:" + counter.getCurrentCount());
        executorService.shutdown();
    }

}
