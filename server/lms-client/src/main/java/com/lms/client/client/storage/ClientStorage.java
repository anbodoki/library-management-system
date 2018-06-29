package com.lms.client.client.storage;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ClientStorage {

    @PersistenceContext
    private EntityManager em;
}
