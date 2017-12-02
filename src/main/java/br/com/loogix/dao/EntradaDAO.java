/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Entrada;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author thiago
 */
@Stateless
public class EntradaDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private GenericDAO<Entrada> dao;
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        this.dao = new GenericDAO<Entrada>(this.em);
    }
    
    public void add(Entrada entrada) {
       this.dao.add(entrada);
    }
    
    public void update(Entrada entrada) {
        this.dao.update(entrada);
    }
    
    public void delete(Entrada entrada) {
        entrada = this.em.merge(entrada);
        this.dao.delete(entrada);
    }
    
    public List<Entrada> getList() {
        Query q = em.createQuery("select e from Entrada e");
        return q.getResultList();
    }

    public Long getSize() {
        TypedQuery<Long> q = em.createQuery("select count(e.id) from Entrada e", Long.class);
        return q.getSingleResult();
    }
    
}
