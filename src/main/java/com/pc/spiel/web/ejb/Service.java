package com.pc.spiel.web.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Service {

    @PersistenceContext
    private EntityManager entityManager;


}
