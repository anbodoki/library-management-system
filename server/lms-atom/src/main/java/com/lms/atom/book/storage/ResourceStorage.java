package com.lms.atom.book.storage;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ResourceStorage {

    @PersistenceContext
    private EntityManager em;
}
