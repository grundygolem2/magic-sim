package com.github.grundygolem2;

import com.github.grundygolem2.configuration.AnalysisConfiguration;
import com.github.grundygolem2.handcomparitor.HandTester;
import com.github.grundygolem2.javapojo.Card;
import com.github.grundygolem2.montecarlo.MonteCarloExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AnalysisConfiguration.class);
        ctx.refresh();
        MonteCarloExecutor<Card> mce = ctx.getBean(MonteCarloExecutor.class);
        HandTester tester = ctx.getBean(HandTester.class);
        for (int i = 0; i < 20; i++) {
            List<Card> cards = mce.getSample(7);
            System.out.println(cards.stream().map(card -> card.getName()).collect(Collectors.toList()) + "->" + tester.test(cards));
        }

        System.out.println("Keepable 7: " + mce.runTest(7, 100000));
        System.out.println("Keepable 6: " + mce.runTest(6, 100000));
        System.out.println("Keepable 5: " + mce.runTest(5, 100000));

    }
}
