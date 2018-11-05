package com.github.grundygolem2.montecarlo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class MonteCarloExecutor<T> {
    private static final Logger logger = LoggerFactory.getLogger(MonteCarloExecutor.class);
    private List<T> source;
    private Predicate<List<T>> tester;

    public MonteCarloExecutor(List<T> source, Predicate<List<T>> tester) {
        this.source = source;
        this.tester = tester;
    }

    public List<T> getSample(int sampleSize) {
        List<T> copy = new ArrayList<>(source);
        Collections.shuffle(copy);
        return copy.subList(0, sampleSize);
    }

    public double runTest(int sampleSize, int sampleCount) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Result result = new Result(sampleCount);
        for (int i = 0; i < sampleCount; i++){
            executor.submit(new TestRunner(sampleSize,result));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(sampleCount * 10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            logger.error("Interrupted while running MonteCarlo, result may be invalid");
        }
        return result.getResult();
    }

    private class TestRunner implements Runnable {
        private int sampleSize;
        private Result result;

        public TestRunner(int sampleSize, Result result) {
            this.sampleSize = sampleSize;
            this.result = result;
        }

        @Override
        public void run() {
            if (tester.test(getSample(sampleSize))) {
                result.success();
            }
        }
    }

    private class Result {
        private final int size;
        private int successes = 0;

        public Result(int size){
            this.size = size;
        }

        public void success(){
            successes++;
        }

        public double getResult(){
            return ((double)successes)/((double)size);
        }
    }
}
