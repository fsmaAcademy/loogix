/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Almoxarifado;
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
public class AlmoxarifadoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private GenericDAO<Almoxarifado> dao;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        this.dao = new GenericDAO<Almoxarifado>(this.em);
    }

    public void add(Almoxarifado almoxarifado) {
        this.dao.add(almoxarifado);
    }

    public void update(Almoxarifado almoxarifado) {
        this.dao.update(almoxarifado);
    }

    public void delete(Almoxarifado almoxarifado) {
        almoxarifado = this.em.merge(almoxarifado);
        this.dao.delete(almoxarifado);
    }

    public List<Almoxarifado> getList() {
        Query q = em.createQuery("select a from Almoxarifado a");
        return q.getResultList();
    }

    public Almoxarifado buscaPorId(Long id) {
        TypedQuery<Almoxarifado> tq = this.em.createQuery("select a from Almoxarifado a where a.id = :id", Almoxarifado.class);
        tq.setParameter("id", id);
        try {
            return tq.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public Long getSize() {
        TypedQuery<Long> q = em.createQuery("select count(a.id) from Almoxarifado a", Long.class);
        return q.getSingleResult();
    }

}
