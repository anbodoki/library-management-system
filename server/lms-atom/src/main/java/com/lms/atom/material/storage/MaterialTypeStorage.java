package com.lms.atom.material.storage;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MaterialTypeStorage {

    @PersistenceContext
    private EntityManager em;
}
