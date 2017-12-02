/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Produto;
import br.com.loogix.model.ProdutoAlmoxarifado;
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
public class ProdutoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private GenericDAO<Produto> dao;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        this.dao = new GenericDAO<Produto>(this.em);
    }

    public void add(Produto produto) {
        this.dao.add(produto);
    }

    public void update(Produto produto) {
        this.dao.update(produto);
    }

    public void delete(Produto produto) {
        produto = this.em.merge(produto);
        this.dao.delete(produto);
    }

    public List<Produto> getList() {
        Query q = em.createQuery("select p from Produto p");
        return q.getResultList();
    }

    public Long getSize() {
        TypedQuery<Long> q = em.createQuery("select count(p.id) from Produto p", Long.class);
        return q.getSingleResult();
    }

    public Produto buscaPorId(Long id) {

        TypedQuery<Produto> tq = this.em.createQuery(
                "selec p from Produto p where p.id = :id",
                Produto.class
        );
        tq.setParameter("id", id);

        try {
            return tq.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public Produto buscaPorCodigoOuNome(String nomeBusca) {
        
        TypedQuery<Produto> tq = this.em.createQuery(
                "select p from Produto p where p.codigo = :codigo or p.nome = :nome",
                Produto.class
        );
        
        tq.setParameter("nome", nomeBusca);
        tq.setParameter("codigo", nomeBusca);
        
        try {
            return tq.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
