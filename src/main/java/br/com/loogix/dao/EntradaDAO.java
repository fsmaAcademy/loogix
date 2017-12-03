/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Entrada;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void persistirEmRelatorioCompleto() throws FileNotFoundException {
        
        String dado = "";
        
        for (Entrada entrada : this.getList()) {
            dado += entrada.getId().toString() + ";";
            dado += entrada.getProdutoAlmoxarifado().getProduto() + ";";
            dado += entrada.getQuantidade().toString() + ";";
            dado += entrada.getFornecedorExterno().getNome() + "\n";
        }
        
        try (Formatter f = new Formatter("entrada.csv")) {
            f.format(dado);
        }
        
    }

    public List<Entrada> buscaPorTempoDeterminado(LocalDate dataInicio, LocalDate dataFim) {
        
        TypedQuery tq = this.em.createQuery(
                "SELECT e from Entrada e WHERE e.data = :dataInicio and e.data = :dataFim",
                Entrada.class);
        
        tq.setParameter("dataInicio", dataInicio);
        tq.setParameter("dataFim", dataFim);
        
        try {
            return tq.getResultList();
        } catch (Exception e){
            return null;
        }
        
    }
    
}
