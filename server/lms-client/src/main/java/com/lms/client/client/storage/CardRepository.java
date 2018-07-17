package com.lms.client.client.storage;

import com.lms.client.client.storage.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
