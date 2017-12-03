/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.FornecedorExterno;
import br.com.loogix.model.Produto;
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
public class FornecedorExternoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private GenericDAO<FornecedorExterno> dao;
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        this.dao = new GenericDAO<FornecedorExterno>(this.em);
    }
    
    public void add(FornecedorExterno fornecedorExterno) {
       this.em.persist(fornecedorExterno);
    }
    
    public void update(FornecedorExterno fornecedorExterno) {
        this.em.merge(fornecedorExterno);
    }
    
    public void delete(FornecedorExterno fornecedorExterno) {
        fornecedorExterno = this.em.merge(fornecedorExterno);
        this.em.remove(fornecedorExterno);
    }
    
    public List<FornecedorExterno> getList() {
        Query q = em.createQuery("select fe from FornecedorExterno fe");
        return q.getResultList();
    }

    public FornecedorExterno buscaPorId(Long id) {
        TypedQuery<FornecedorExterno> tq = this.em.createQuery(
                "select fe from FornecedorExterno fe where fe.id = :id",
                FornecedorExterno.class
        );
        tq.setParameter("id", id);

        try {
            return tq.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
