/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.AlmoxarifadoDAO;
import br.com.loogix.dao.EmpregadoDAO;
import br.com.loogix.dao.FuncaoDAO;
import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Empregado;
import br.com.loogix.model.Funcao;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author thiago
 */
@Named(value = "empregadoController")
@SessionScoped
public class EmpregadoController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private EmpregadoDAO daoEmpregado;
    
    @EJB
    private FuncaoDAO daoFuncao;
    
    @EJB
    private AlmoxarifadoDAO almoxarifadoDAO;
    
    private Empregado empregado;
    private List<Empregado> empregados;
    private boolean alterando = false;
    private Long idFuncaoEmpregado;
    private Long idAlmoxarifadoEmpregado;

    public String listar() {
        this.empregados = this.daoEmpregado.getList();
        return "empregado?faces-redirect=true";
    }
    
    public String novo() {
        this.idFuncaoEmpregado = null;
        this.empregado = new Empregado();
        this.alterando = false;
        return "novo-empregado?faces-redirect=true";
    }
    
    public String iniciarAlterar(Empregado empregado) {
        this.empregado = empregado;
        this.alterando = true;
        return "novo-empregado?faces-redirect=true";
    }
    
    
    public String gravar() {
        
        Funcao funcao = this.daoFuncao.buscaPorId(this.idFuncaoEmpregado);
        this.empregado.setFuncao(funcao);
        
        Almoxarifado almoxarifado = this.almoxarifadoDAO.buscaPorId(this.idAlmoxarifadoEmpregado);
        this.empregado.setAlmoxarifado(almoxarifado);
        
        if (this.alterando == false)
            this.daoEmpregado.add(this.empregado);
        else
            this.daoEmpregado.update(this.empregado);
        
        this.empregados = this.daoEmpregado.getList();
        
        return "empregado?faces-redirect=true"; 
    }
    
    public String excluir(Empregado empregado) {
        this.daoEmpregado.delete(empregado);
        this.empregados = this.daoEmpregado.getList();
        return null;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public List<Empregado> getEmpregados() {
        return this.daoEmpregado.getList();
    }
    
    public List<Funcao> getFuncoes() {
        return this.daoFuncao.getList();
    }
    
    public Long getFuncaoEmpregado() {
        return idFuncaoEmpregado;
    }
    
    public void setFuncaoEmpregado(Long id) {
        idFuncaoEmpregado = id;
    }

    public Long getAlmoxarifadoEmpregado() {
        return idAlmoxarifadoEmpregado;
    }

    public void setAlmoxarifadoEmpregado(Long idAlmoxarifadoEmpregado) {
        this.idAlmoxarifadoEmpregado = idAlmoxarifadoEmpregado;
    }
    
    public List<Almoxarifado> getAlmoxarifados() {
        return this.almoxarifadoDAO.getList();
    }
    
}
