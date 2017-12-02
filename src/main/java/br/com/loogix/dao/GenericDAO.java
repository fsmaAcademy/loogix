/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author thiago
 * @param <T>
 */

public class GenericDAO<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private EntityManager em;

    public GenericDAO(EntityManager em) {
        this.em = em;
    }
    
    public void add(T t) {
        this.em.persist(t);
    }
    
    public void update(T t) {
        this.em.merge(t);
    }
    
    public void delete(T t) {
        this.em.remove(t);
    }
    
}
