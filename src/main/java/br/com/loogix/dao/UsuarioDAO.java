/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.dao;

import br.com.loogix.model.Usuario;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author thiago
 */
@Stateless
public class UsuarioDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public boolean existe(Usuario usuario) {

        TypedQuery<Usuario> query = em.createQuery(
                " select u from Usuario u "
                + " where u.login = :pLogin and u.senha = :pSenha", Usuario.class);

        query.setParameter("pLogin", usuario.getLogin());
        query.setParameter("pSenha", usuario.getSenha());
        try {
            @SuppressWarnings("unused")
            Usuario resultado = query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    public Usuario buscaUsuarioPelaAutenticacao(Usuario usuario) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select u from Usuario u ");
        jpql.append(" where ");
        jpql.append("       u.login = :pLogin ");
        jpql.append("   and u.senha = :pSenha ");

        TypedQuery<Usuario> query = em.createQuery(jpql.toString(), Usuario.class);

        query.setParameter("pLogin", usuario.getLogin());
        query.setParameter("pSenha", usuario.getSenha());
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public void adiciona(Usuario usuario) {
        this.em.persist(usuario);
    }

    public void atualiza(Usuario usuario) {
        this.em.merge(usuario);
    }

    public void remove(Usuario usuario) {
        this.em.merge(usuario);
        this.em.remove(usuario);
    }

    public Usuario buscaPorId(Long id) {
        return this.em.find(Usuario.class, id);
    }

    public Usuario buscaPorEmail(String email) {
        String jpql = " select u from Usuario u where u.email = :pEmail";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("pEmail", email.trim().toLowerCase());
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
