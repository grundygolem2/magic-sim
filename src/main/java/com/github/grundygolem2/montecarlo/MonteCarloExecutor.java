package com.github.grundygolem2.montecarlo;

import com.github.grundygolem2.handcomparitor.HandTester;
import com.github.grundygolem2.javapojo.Card;
import com.github.grundygolem2.postprocessor.PostProcessor;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class MonteCarloExecutor {
    private static final Logger logger = LoggerFactory.getLogger(MonteCarloExecutor.class);
    private List<Card> source;
    private HandTester tester;
    private Optional<PostProcessor<Card>> postProcessor;

    public MonteCarloExecutor(List<Card> source, HandTester tester, PostProcessor<Card> postProcessor) {
        this.source = source;
        this.tester = tester;

        this.postProcessor = Optional.of(postProcessor);
    }

    public MonteCarloExecutor(List<Card> source, HandTester tester) {
        this.source = source;
        this.tester = tester;
        this.postProcessor = Optional.empty();
    }

    public List<Card> getSample(int sampleSize) {
        List<Card> copy = new ArrayList<>(source);
        Collections.shuffle(copy);
        return copy.subList(0, sampleSize);
    }

    public double runTest(int sampleSize, int sampleCount, int mullNumber) {
        ExecutorService executor = Executors.newFixedThreadPool(25);
        Result result = new Result(sampleCount);
        for (int i = 0; i < sampleCount; i++) {
            executor.submit(new TestRunner(sampleSize, result, mullNumber));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(sampleCount * 10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error("Interrupted while running MonteCarlo, result may be invalid");
        }
        return result.getResult();
    }

    private class TestRunner implements Runnable {
        private int sampleSize;
        private Result result;
        private int mullNumber;

        public TestRunner(int sampleSize, Result result, int mullNumber) {
            this.sampleSize = sampleSize;
            this.result = result;
            this.mullNumber = mullNumber;
        }

        @Override
        public void run() {
            List<Card> sample = getSample(sampleSize);
            boolean testResult = tester.test(sample, mullNumber);
            boolean postProcessResult = postProcessor.map(processor -> processor.postProcess(sample)).orElse(true);
            if (testResult && postProcessResult) {
                result.success();
            }
        }
    }

    private class Result {
        private final int size;
        private int successes = 0;

        public Result(int size) {
            this.size = size;
        }

        public void success() {
            successes++;
        }

        public double getResult() {
            return ((double) successes) / ((double) size);
        }
    }
}
