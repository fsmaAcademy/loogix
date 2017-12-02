/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.UsuarioDAO;
import br.com.loogix.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author thiago
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
