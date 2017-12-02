/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.FornecedorExternoDAO;
import br.com.loogix.model.FornecedorExterno;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author thiago
 */
@Named(value = "fornecedorController")
@SessionScoped
public class FornecedorExternoController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EJB
    private FornecedorExternoDAO daoFornecedorExterno;
    
    private FornecedorExterno fornecedorExterno;
    private List<FornecedorExterno> fornecedoresExternos;
    private boolean alterando = false;

    public FornecedorExternoController() {
    }
    
    public String listar() {
        this.fornecedoresExternos = this.daoFornecedorExterno.getList();
        return "fornecedor?faces-redirect=true";
    }
    
    public String novo() {
        this.fornecedorExterno = new FornecedorExterno();
        this.alterando = false;
        return "novo-fornecedor-externo?faces-redirect=true";
    }
    
    public String iniciarAlterar(FornecedorExterno fornecedorExterno) {
        this.fornecedorExterno = fornecedorExterno;
        this.alterando = true;
        return "novo-fornecedor-externo?faces-redirect=true";
    }
    
    
    public String gravar() {
        
        if (this.alterando == false)
            this.daoFornecedorExterno.add(this.fornecedorExterno);
        else
            this.daoFornecedorExterno.update(this.fornecedorExterno);
        
        this.fornecedoresExternos = this.daoFornecedorExterno.getList();
        
        return "fornecedor?faces-redirect=true"; 
    }
    
    public String excluir(FornecedorExterno fornecedorExterno) {
        this.daoFornecedorExterno.delete(fornecedorExterno);
        this.fornecedoresExternos = this.daoFornecedorExterno.getList();
        return null;
    }

    public FornecedorExterno getFornecedorExterno() {
        return fornecedorExterno;
    }

    public void setFornecedorExterno(FornecedorExterno fornecedorExterno) {
        this.fornecedorExterno = fornecedorExterno;
    }

    public List<FornecedorExterno> getFornecedoresExternos() {
        return this.daoFornecedorExterno.getList();
    }
    
}
