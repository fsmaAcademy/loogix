/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.EstadosBrasileiros;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author thiago
 */
@Stateless
public class EstadosBrasileirosDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    EntityManager em;

    public List<EstadosBrasileiros> getList() {
        TypedQuery<EstadosBrasileiros> q = this.em.createQuery("select eb from EstadosBrasileiros eb", EstadosBrasileiros.class);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
