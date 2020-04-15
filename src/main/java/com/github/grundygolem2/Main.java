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
        MonteCarloExecutor mce = ctx.getBean(MonteCarloExecutor.class);
        HandTester tester = ctx.getBean(HandTester.class);
        for (int i = 0; i < 35; i++) {
            List<Card> cards = mce.getSample(5);
            System.out.println(cards.stream().map(card -> card.getName()).collect(Collectors.toList()) + "->" + tester.test(cards,0));
        }

        double seven = mce.runTest(7, 100000,0);
        System.out.println("Keepable 7: " + seven);
        double six = mce.runTest(7, 100000,1);
        System.out.println("Keepable 6: " + six);
        double five = mce.runTest(7, 100000,2);
        System.out.println("Keepable 5: " + five);

        double sevenOrSix = seven + (1-seven)*(six);
        System.out.println("Keepable 7 or 6 : " + sevenOrSix);
        double sevenSixFive = sevenOrSix + (1-sevenOrSix)*(five);
        System.out.println("Keepable 7,6,5 : " + sevenSixFive);
    }
}
