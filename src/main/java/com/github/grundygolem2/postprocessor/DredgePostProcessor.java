package com.github.grundygolem2.postprocessor;

import com.github.grundygolem2.javapojo.Card;

import java.util.List;

import org.apache.commons.math3.distribution.HypergeometricDistribution;

public class DredgePostProcessor implements PostProcessor<Card> {

    int deckSize;
    int populationSuccesses;

    public DredgePostProcessor(List<Card> decklist) {
        this.deckSize = decklist.size();
        this.populationSuccesses = (int) decklist.stream().filter(card -> card.getTags().contains("dredger")).count();
    }

    public boolean postProcess(List<Card> cards) {
        if (cards.stream().anyMatch(card -> card.getTags().contains("dredger"))) return true;

        //-1 here, and the ++ in the loop, account for draw steps between enablers
        int dredgeDepth = -1;
        //accounts for the scry rule on hands smaller than 7
//        if (cards.size() < 7) dredgeDepth++;
        for (Card card : cards) {
            if (null != card.getAdditionalProperties().get("dredgeDepth")) {
                dredgeDepth += (Integer) card.getAdditionalProperties().get("dredgeDepth");
                dredgeDepth++;
            }
        }

        HypergeometricDistribution distribution = new HypergeometricDistribution(deckSize - cards.size(), populationSuccesses, dredgeDepth);
        return distribution.upperCumulativeProbability(1) >= .75;

    }
}
