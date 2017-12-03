/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Saida;
import java.io.Serializable;
import java.time.LocalDate;
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
public class SaidaDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    private GenericDAO<Saida> dao;
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        this.dao = new GenericDAO<Saida>(this.em);
    }
    
    public void add(Saida saida) {
       this.dao.add(saida);
    }
    
    public void update(Saida saida) {
        this.dao.update(saida);
    }
    
    public void delete(Saida saida) {
        saida = this.em.merge(saida);
        this.dao.delete(saida);
    }
    
    public List<Saida> getList() {
        Query q = em.createQuery("select s from Saida s");
        return q.getResultList();
    }

    public Long getSize() {
        TypedQuery<Long> q = em.createQuery("select count(s.id) from Saida s", Long.class);
        return q.getSingleResult();
    }
    
    public List<Saida> buscaPorTempoDeterminado(LocalDate dataInicio, LocalDate dataFim) {
        
        TypedQuery tq = this.em.createQuery(
                "SELECT s from Saida s WHERE s.data = :dataInicio and s.data = :dataFim",
                Saida.class);
        
        tq.setParameter("dataInicio", dataInicio);
        tq.setParameter("dataFim", dataFim);
        
        try {
            return tq.getResultList();
        } catch (Exception e){
            return null;
        }
        
    }
    
}
