/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.AlmoxarifadoDAO;
import br.com.loogix.dao.EmpregadoDAO;
import br.com.loogix.dao.EstadosBrasileirosDAO;
import br.com.loogix.dao.ProdutoAlmoxarifadoDAO;
import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Empregado;
import br.com.loogix.model.EstadosBrasileiros;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author thiago
 */
@Named(value = "almoxarifadoController")
@SessionScoped
public class AlmoxarifadoController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EJB
    private AlmoxarifadoDAO daoAlmoxarifado;
    
    @EJB
    private ProdutoAlmoxarifadoDAO produtoAlmoxarifadoDAO;
    
    @EJB
    private EmpregadoDAO empregadoDAO;
    
    @EJB
    private EstadosBrasileirosDAO estadosBrasileirosDAO;
    
    private Almoxarifado almoxarifado;
    private List<Almoxarifado> almoxarifados;
    private boolean alterando = false;
    private Long idEmpregadoAlmoxarifado;
    
    
    public String detalhe(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
        return "almoxarifado-detalhe?faces-redirect=true";
    }
    
    public String listar() {
        this.almoxarifados = this.daoAlmoxarifado.getList();
        return "almoxarifado?faces-redirect=true";
    }
    
    public String novo() {
        this.almoxarifado = new Almoxarifado();
        this.alterando = false;
        return "novo-almoxarifado?faces-redirect=true";
    }
    
    public String iniciarAlterar(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
        this.alterando = true;
        return "novo-almoxarifado?faces-redirect=true";
    }
    
    
    public String gravar() {
        
        if (this.alterando == false)
            this.daoAlmoxarifado.add(this.almoxarifado);
        else
            this.daoAlmoxarifado.update(this.almoxarifado);
        
        this.almoxarifados = this.daoAlmoxarifado.getList();
        
        return "almoxarifado?faces-redirect=true"; 
    }
    
    public String excluir(Almoxarifado almoxarifado) {
        this.daoAlmoxarifado.delete(almoxarifado);
        this.almoxarifados = this.daoAlmoxarifado.getList();
        return null;
    }

    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }

    public void setAlmoxarifado(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
    }

    public List<Almoxarifado> getAlmoxarifados() {
        return this.daoAlmoxarifado.getList();
    }

    public Long getIdEmpregadoAlmoxarifado() {
        return idEmpregadoAlmoxarifado;
    }

    public void setIdEmpregadoAlmoxarifado(Long idEmpregadoAlmoxarifado) {
        this.idEmpregadoAlmoxarifado = idEmpregadoAlmoxarifado;
    }
    
    public List<Empregado> getResponsaveis() {
        return this.empregadoDAO.getResponsaveis();
    }
    
    public List<Empregado> getEmpregados() {
        return this.empregadoDAO.getEmpregadosDoAlmoxarifado(this.almoxarifado.getId());
    }
    
    public Long getSize() {
        return this.daoAlmoxarifado.getSize();
    }
    
    public List<EstadosBrasileiros> getEstadosBrasileiros() {
        return this.estadosBrasileirosDAO.getList();
    }
    
    
}
