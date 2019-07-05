package com.github.grundygolem2.handcomparitor;

import com.github.grundygolem2.javapojo.Card;
import com.github.grundygolem2.javapojo.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class HandTester /*implements Predicate<List<Card>> todo revert*/ {

    private List<Hand> hands;

    public HandTester(List<Hand> hands) {
        this.hands = hands;
    }

//    @Override todo revert
    public boolean test(List<Card> cards, Integer mullNumber) {
        return hands.stream().map((hand) -> (matches(hand, cards, mullNumber))).reduce(Boolean.FALSE, Boolean::logicalOr);
    }

    public static boolean matches(Hand hand, List<Card> cards, Integer mullNumber) {
        if (hand.getMaxKeepSize() < (7-mullNumber)) return false; //todo revert
        for (String cardName : hand.getExcludesCards()) {
            for (Card card : cards) {
                if (card.getName().equals(cardName)) return false;
            }
        }

        for (String tagValue : hand.getExcludesTags()) {
            for (Card card : cards) {
                if (card.getTags().contains(tagValue)) return false;
            }
        }


        List<Card> cardsCopy = new ArrayList<>(cards);

        for (String cardName : hand.getContainsCards()) {
            boolean cardFound = false;
            for (Card card : cardsCopy) {
                if (card.getName().equals(cardName)) {
                    cardsCopy.remove(card);
                    cardFound = true;
                    break;
                }
            }
            if (!cardFound) return false;
        }


        for (String tagValue : hand.getContainsTags()) {
            boolean tagFound = false;
            for (Card card : cardsCopy) {
                if (card.getTags().contains(tagValue)) {
                    cardsCopy.remove(card);
                    tagFound = true;
                    break;
                }
            }
            if (!tagFound) return false;
        }

        return true;
    }
}
