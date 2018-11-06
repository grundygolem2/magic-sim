package com.github.grundygolem2.handcomparitor;

import com.github.grundygolem2.javapojo.Card;
import com.github.grundygolem2.javapojo.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class HandTester implements Predicate<List<Card>> {

    private List<Hand> hands;

    @Override
    public boolean test(List<Card> cards) {
        return hands.stream().map((hand) -> (matches(hand,cards))).reduce(Boolean.FALSE,Boolean::logicalOr);
    }

    public static boolean matches(Hand hand, List<Card> cards) {
        if (hand.getExcludesCards().size() > 0){
            for (String cardName: hand.getExcludesCards()){
                for (Card card : cards){
                    if (card.getName().equals(cardName)) return false;
                }
            }
        }
        if (hand.getExcludesTags().size() > 0){
            for (String tagValue: hand.getExcludesTags()){
                for (Card card : cards){
                    if (card.getTags().contains(tagValue)) return false;
                }
            }
        }
        if (hand.getMaxKeepSize() < cards.size()) return false;

        List<Card> cardsCopy = new ArrayList<>(cards);

        if (hand.getContainsCards().size() > 0) {
            for (String cardName: hand.getContainsCards()){
                boolean cardFound = false;
                for (Card card : cardsCopy){
                    if (card.getName().equals(cardName)){
                        cardsCopy.remove(card);
                        cardFound = true;
                        break;
                    }
                }
                if (!cardFound) return false;
            }
        }

        if (hand.getContainsTags().size() > 0) {
            for (String tagValue: hand.getContainsCards()){
                boolean tagFound = false;
                for (Card card : cardsCopy){
                    if (card.getTags().contains(tagValue)){
                        cardsCopy.remove(card);
                        tagFound = true;
                        break;
                    }
                }
                if (!tagFound) return false;
            }
        }
        return true;
    }
}
