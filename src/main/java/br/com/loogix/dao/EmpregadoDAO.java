/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Empregado;
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
public class EmpregadoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private GenericDAO<Empregado> dao;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        this.dao = new GenericDAO<Empregado>(this.em);
    }

    public void add(Empregado empregado) {
        this.dao.add(empregado);
    }

    public void update(Empregado empregado) {
        this.dao.update(empregado);
    }

    public void delete(Empregado empregado) {
        empregado = this.em.merge(empregado);
        this.dao.delete(empregado);
    }

    public List<Empregado> getList() {
        Query q = em.createQuery("select e from Empregado e");
        return q.getResultList();
    }

    public List<Empregado> getResponsaveis() {
        
        TypedQuery q = em.createQuery("Select e from Empregado e where e.funcao.nome = :supervisor or e.funcao.nome = :gerente", Empregado.class);
        q.setParameter("supervisor", "Supervisor");
        q.setParameter("gerente", "Gerente");
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Empregado buscaPorId(Long idEmpregadoAlmoxarifado) {
        TypedQuery<Empregado> tq = this.em.createQuery(
                "select e from Empregado e where e.id = :id",
                Empregado.class);
        tq.setParameter("id", idEmpregadoAlmoxarifado);
        try {
            return tq.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Empregado buscaPorIdAndFuncao(Long idEmpregadoAlmoxarifado) {
        TypedQuery<Empregado> tq = this.em.createQuery(
                "select e from Empregado e where e.id = :id and e.funcao.nome = :supervisor",
                Empregado.class);
        tq.setParameter("id", idEmpregadoAlmoxarifado);
        tq.setParameter("supervisor", "Supervisor");
        try {
            return tq.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Empregado> getEmpregadosDoAlmoxarifado(Long idAlmoxarifado) {
        TypedQuery<Empregado> tq = this.em.createQuery(
                "SELECT e FROM Empregado e WHERE e.almoxarifado.id = :id",
                Empregado.class);
        tq.setParameter("id", idAlmoxarifado);
        return tq.getResultList();
    }

}
