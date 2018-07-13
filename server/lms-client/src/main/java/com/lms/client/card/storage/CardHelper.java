package com.lms.client.card.storage;

import com.lms.client.card.storage.model.Card;
import com.lms.common.dto.client.CardDTO;

import java.util.ArrayList;
import java.util.List;

public class CardHelper {

    public static Card toEntity(CardDTO card) {
        if (card == null) {
            return null;
        }
        Card result = new Card();
        result.setId(card.getId());
        result.setIdentifier(card.getIdentifier());
        result.setCreationDate(card.getCreationDate());
        return result;
    }

    public static CardDTO fromEntity(Card card) {
        if (card == null) {
            return null;
        }
        CardDTO result = new CardDTO();
        result.setId(card.getId());
        result.setIdentifier(card.getIdentifier());
        result.setCreationDate(card.getCreationDate());
        return result;
    }

    public static List<Card> toEntities(List<CardDTO> cards) {
        if (cards == null) {
            return null;
        }
        List<Card> result = new ArrayList<>();
        for (CardDTO card : cards) {
            result.add(toEntity(card));
        }
        return result;
    }

    public static List<CardDTO> fromEntities(List<Card> cards) {
        if (cards == null) {
            return null;
        }
        List<CardDTO> result = new ArrayList<>();
        for (Card card : cards) {
            result.add(fromEntity(card));
        }
        return result;
    }
}
