/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.UsuarioDAO;
import br.com.loogix.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thiago
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String USUARIO_LOGADO = "usuarioLogado";
    
    private boolean alterando = false;


    private HttpSession session;
    @EJB
    private UsuarioDAO usuarioDao;
    private Usuario usuario = new Usuario();

    
    @PostConstruct
    public void init() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        session = (HttpSession) context.getExternalContext().getSession(true);
        
        usuario = (Usuario) context.getExternalContext().getSessionMap().get(USUARIO_LOGADO);
        
        if (usuario == null) {
          
            usuario = new Usuario();
           
        }
        
    }

    public String loga() {

        Usuario usuarioAutenticado = usuarioDao.buscaUsuarioPelaAutenticacao(this.usuario);

        if (usuarioAutenticado != null) {

            usuarioDao.atualiza(usuarioAutenticado);
            session.setAttribute(USUARIO_LOGADO, usuarioAutenticado);
            usuario = usuarioAutenticado;
            return "index?faces-redirect=true";
        }
//		String mensagem = "Usuário e/ou senha não inválido!";
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", mensagem));

        return null;
    }

    public String desloga() {
        session.removeAttribute(USUARIO_LOGADO);
        session.invalidate();
        usuario = new Usuario();
        return "login?faces-redirect=true";
    }
    
    public String novoUsuario() {
        this.usuario = new Usuario();
        this.alterando = false;
        return "novo-usuario";
    }
    
    public String iniciarAlterar() {
        this.alterando = true;
        return "novo-usuario?faces-redirect=true";
    }
    
    public String gravaUsuario() {
        if (this.alterando == false) {
            this.usuarioDao.adiciona(this.usuario);
        } else {
            this.usuarioDao.atualiza(this.usuario);
        }
        return "login?faces-redirect=true";
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

}
