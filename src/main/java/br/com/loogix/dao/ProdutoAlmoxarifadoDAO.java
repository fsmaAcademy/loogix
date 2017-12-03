/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.ProdutoAlmoxarifado;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author thiago
 */
@Stateless
public class ProdutoAlmoxarifadoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    EntityManager em;
    
    public void add(ProdutoAlmoxarifado produtoAlmoxarifado) {
       this.em.persist(produtoAlmoxarifado);
    }
    
    public void update(ProdutoAlmoxarifado produtoAlmoxarifado) {
        this.em.merge(produtoAlmoxarifado);
    }
    
    public void delete(ProdutoAlmoxarifado produtoAlmoxarifado) {
        produtoAlmoxarifado = this.em.merge(produtoAlmoxarifado);
        this.em.remove(produtoAlmoxarifado);
    }
    
    public ProdutoAlmoxarifado busca(Long idAlmoxarifado, Long idProduto) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("select pa from ProdutoAlmoxarifado pa ");
        sb.append("where pa.almoxarifado = :pAlmoxarifado ");
        sb.append("and pa.produto = :pProduto");
        
        TypedQuery<ProdutoAlmoxarifado> query = em.createQuery(
                "select pa from ProdutoAlmoxarifado pa where pa.almoxarifado.id = :idAlmoxarifado and pa.produto.id = :idProduto",
                ProdutoAlmoxarifado.class
        );
        query.setParameter("idAlmoxarifado", idAlmoxarifado);
        query.setParameter("idProduto", idProduto);
        
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }

    public ProdutoAlmoxarifado buscaPorId(Long idAlmoxarifadoProdutoAlmoxarifado) {
        TypedQuery<ProdutoAlmoxarifado> tq = this.em.createQuery(
                "select a from Almoxarifado a where a.id = :id",
                ProdutoAlmoxarifado.class
        );
        tq.setParameter("id", idAlmoxarifadoProdutoAlmoxarifado);
        try {
            return tq.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    
}
