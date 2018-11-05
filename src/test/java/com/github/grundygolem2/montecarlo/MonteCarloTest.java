package com.github.grundygolem2.montecarlo;


import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static java.lang.Math.abs;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MonteCarloTest {

    @Test
    public void testMonteCarloExecutorWithAllOfList() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4);
        Predicate<List<Integer>> pred = (l -> l.containsAll(list));
        MonteCarloExecutor<Integer> mce = new MonteCarloExecutor<>(list, pred);
        assertTrue(pred.test(mce.getSample(4)));
    }

    @Test
    public void testMonteCarloExecutorWithPartialList() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4);
        Predicate<List<Integer>> pred = (l -> l.containsAll(list));
        MonteCarloExecutor<Integer> mce = new MonteCarloExecutor<>(list, pred);
        assertFalse(pred.test(mce.getSample(3)));
    }

    @Test
    public void testMonteCarloExecutorWithAnyOfSize1() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4);
        Predicate<List<Integer>> pred = (l -> l.contains(1));
        MonteCarloExecutor<Integer> mce = new MonteCarloExecutor<>(list, pred);
        assertTrue(abs(.25 - mce.runTest(1, 100000)) < .01);
    }

    @Test
    public void testMonteCarloExecutorWithAnyOfSize2() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4);
        Predicate<List<Integer>> pred = (l -> l.contains(1));
        MonteCarloExecutor<Integer> mce = new MonteCarloExecutor<>(list, pred);
        assertTrue(abs(.5 - mce.runTest(2, 100000)) < .01);
    }
}
