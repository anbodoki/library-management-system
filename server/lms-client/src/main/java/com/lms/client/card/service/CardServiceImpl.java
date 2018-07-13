package com.lms.client.card.service;

import com.lms.client.card.storage.CardRepository;
import com.lms.common.dto.client.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository repository;

    @Autowired
    public CardServiceImpl(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    public CardDTO addCard(CardDTO card) {
        return null;
    }

    @Override
    public CardDTO updateCard(CardDTO card) {
        return null;
    }

    @Override
    public void deleteCard(Long cardId) {

    }

    @Override
    public List<CardDTO> getClientCards() {
        return null;
    }
}
