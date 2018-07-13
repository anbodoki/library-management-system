package com.lms.client.card.storage;

import com.lms.client.card.storage.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByIdentifier(String identifier);

}
