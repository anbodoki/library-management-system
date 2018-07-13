package com.lms.client.card.service;

import com.lms.common.dto.client.CardDTO;

import java.util.List;

public interface CardService {

    CardDTO addCard(CardDTO card);

    CardDTO updateCard(CardDTO card);

    void deleteCard(Long cardId);

    List<CardDTO> getClientCards();
}
