/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Funcao;
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
public class FuncaoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private GenericDAO<Funcao> dao;
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        this.dao = new GenericDAO<Funcao>(this.em);
    }
    
    public void add(Funcao funcao) {
       this.dao.add(funcao);
    }
    
    public void update(Funcao funcao) {
        this.dao.update(funcao);
    }
    
    public void delete(Funcao funcao) {
        funcao = this.em.merge(funcao);
        this.dao.delete(funcao);
    }
    
    public List<Funcao> getList() {
        Query q = em.createQuery("select f from Funcao f");
        return q.getResultList();
    }

    public Funcao buscaPorId(Long idFuncaoEmpregado) {
        TypedQuery<Funcao> q = em.createQuery("select f from Funcao f where f.id = :id", Funcao.class);
        q.setParameter("id", idFuncaoEmpregado);
        try {
            return q.getSingleResult();        
        } catch (Exception e) {
            return null;
        }
    }
}
