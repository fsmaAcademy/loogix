/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author thiago
 */
@Named(value = "templateController")
@SessionScoped
public class TemplateController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public TemplateController() {
    }
    
    public String login() {
        return "login?faces-redirect=true";
    }
    
    public String home() {
        return "index?faces-redirect=true";
    }
    
    public String almoxarifado() {
        return "almoxarifado?faces-redirect=true";
    }
    
    public String fornecedor() {
        return "fornecedor?faces-redirect=true";
    }
    
    public String funcao() {
        return "funcao?faces-redirect=true";
    }
    
    public String empregado() {
        return "empregado?faces-redirect=true";
    }
    
    public String produto() {
        return "produto?faces-redirect=true";
    }

    public String entrada() {
        return "entrada?faces-redirect=true";
    }
    
    public String saida() {
        return "saida?faces-redirect=true";
    }
    
    
    
}
