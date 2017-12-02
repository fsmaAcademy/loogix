/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.FuncaoDAO;
import br.com.loogix.model.Funcao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
/**
 *
 * @author thiago
 */
@Named(value = "funcaoController")
@SessionScoped
public class FuncaoController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EJB
    private FuncaoDAO daoFuncao;

    private Funcao funcao;
    private List<Funcao> funcoes;
    private boolean alterando = false;

    
    public FuncaoController() {
    }

    public String listar() {
        this.funcoes = this.daoFuncao.getList();
        return "funcao?faces-redirect=true";
    }

    public String novo() {
        this.funcao = new Funcao();
        this.alterando = false;
        return "novo-funcao?faces-redirect=true";
    }

    public String iniciarAlterar(Funcao funcao) {
        this.funcao = funcao;
        this.alterando = true;
        return "novo-funcao?faces-redirect=true";
    }

    public String gravar() {

        if (this.alterando == false) {
            this.daoFuncao.add(this.funcao);
        } else {
            this.daoFuncao.update(this.funcao);
        }

        this.funcoes = this.daoFuncao.getList();

        return "funcao?faces-redirect=true";
    }

    public String excluir(Funcao funcao) {
        this.daoFuncao.delete(funcao);
        this.funcoes = this.daoFuncao.getList();
        return null;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public List<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
    
    

}
