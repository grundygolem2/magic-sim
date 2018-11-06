package com.github.grundygolem2.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.grundygolem2.javapojo.Card;
import com.github.grundygolem2.javapojo.CardCount;
import com.github.grundygolem2.javapojo.Deck;
import com.github.grundygolem2.javapojo.Hand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:configuration.properties")
public class AnalysisConfiguration {

    @Value("${deck.definition.root}")
    private String deckDefinitionRoot;

    @Bean
    String handJson() {
        InputStream stream = AnalysisConfiguration.class.getResourceAsStream(deckDefinitionRoot + "/hands.json");
        return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));
    }

    @Bean
    List<Hand> hands(String handJson) throws IOException {
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Hand.class);
        return new ObjectMapper().readValue(handJson, typeReference);
    }

    @Bean
    String cardsJson() {
        InputStream stream = AnalysisConfiguration.class.getResourceAsStream(deckDefinitionRoot + "/cards.json");
        return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));
    }

    @Bean
    List<Card> cards(String cardsJson) throws IOException {
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Card.class);
        return new ObjectMapper().readValue(cardsJson, typeReference);
    }

    @Bean
    String deckJson() {
        InputStream stream = AnalysisConfiguration.class.getResourceAsStream(deckDefinitionRoot + "/deck.json");
        return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));
    }

    @Bean
    Deck deck(String deckJson) throws IOException {
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Deck.class);
        return new ObjectMapper().readValue(deckJson, typeReference);
    }

    @Bean
    List<Card> deckList(Deck deck,List<Card> cards){
        List<Card> deckList = new ArrayList<>();
        for (CardCount cardCount : deck.getCardCounts()){
            for (Card card: cards){
                if (card.getName().equals(cardCount.getName())){
                    for (int i = 0; i< cardCount.getQuantity();i++){
                        deckList.add(card);
                    }
                }
            }
        }
        return deckList;
    }


}
