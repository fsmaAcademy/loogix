/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.AlmoxarifadoDAO;
import br.com.loogix.dao.EntradaDAO;
import br.com.loogix.dao.FornecedorExternoDAO;
import br.com.loogix.dao.ProdutoAlmoxarifadoDAO;
import br.com.loogix.dao.ProdutoDAO;
import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Entrada;
import br.com.loogix.model.FornecedorExterno;
import br.com.loogix.model.Produto;
import br.com.loogix.model.ProdutoAlmoxarifado;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author thiago
 */
@Named(value = "entradaController")
@SessionScoped
public class EntradaController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EntradaDAO entradaDAO;

    @EJB
    private AlmoxarifadoDAO almoxarifadoDAO;
    private Almoxarifado almoxarifado;

    @EJB
    private ProdutoAlmoxarifadoDAO produtoAlmoxarifadoDAO;
    private ProdutoAlmoxarifado produtoAlmoxarifado;

    @EJB
    private ProdutoDAO produtoDAO;
    private Produto produto;

    @EJB
    private FornecedorExternoDAO fornecedorExternoDAO;
    private FornecedorExterno fornecedorExterno;

    private Entrada entrada;
    private List<Entrada> entradas;

    private Long idProdutoEntrada;
    private Long idFornecedorEntrada;
    private Long idAlmoxarifadoEntrada;
    private Long idProdutoAlmoxarifadoEntrada;
    
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public String detalhe(Entrada entrada) {
        this.entrada = entrada;
        return "entrada-detalhe?faces-redirect=true";
    }

    public String listar() {
        this.entradas = this.entradaDAO.getList();
        return "entrada?faces-redirect=true";
    }

    public String novo() {
        this.entrada = new Entrada();
        this.produtoAlmoxarifado = new ProdutoAlmoxarifado();
        this.entrada.setData(LocalDate.now());
        return "novo-entrada?faces-redirect=true";
    }
    
    public String gravarNovaEntrada() {
        System.out.println("Iniciar método gravar nova entrada");
        return "entrada?faces-redirect=true";
    }

    public String gravar() {

        this.fornecedorExterno = this.fornecedorExternoDAO.buscaPorId(idFornecedorEntrada);
        this.entrada.setFornecedorExterno(fornecedorExterno);
        
        this.almoxarifado = this.almoxarifadoDAO.buscaPorId(idAlmoxarifadoEntrada);       
        this.produto = this.produtoDAO.buscaPorId(idProdutoEntrada);
        
        this.produtoAlmoxarifado.setAlmoxarifado(this.almoxarifado);
        this.produtoAlmoxarifado.setProduto(this.produto);
        this.produtoAlmoxarifado.setQuantidade(0);
        System.out.println(this.produtoAlmoxarifado);
//        produtoAlmoxarifadoDAO.update(produtoAlmoxarifado);
        this.entrada.setProdutoAlmoxarifado(this.produtoAlmoxarifado);
        System.out.println(this.entrada);
        produtoAlmoxarifado.setEntrada(this.entrada);
        
        this.produtoAlmoxarifadoDAO.add(this.produtoAlmoxarifado);
        this.entradaDAO.add(this.entrada);

        this.entradas = this.entradaDAO.getList();
        return "entrada?faces-redirect=true";
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public FornecedorExterno getFornecedorExterno() {
        return fornecedorExterno;
    }

    public void setFornecedorExterno(FornecedorExterno fornecedorExterno) {
        this.fornecedorExterno = fornecedorExterno;
    }

    public Long getIdProdutoEntrada() {
        return idProdutoEntrada;
    }

    public void setIdProdutoEntrada(Long idProdutoEntrada) {
        this.idProdutoEntrada = idProdutoEntrada;
    }

    public Long getIdProdutoAlmoxarifadoEntrada() {
        return idProdutoAlmoxarifadoEntrada;
    }

    public void setIdProdutoAlmoxarifadoEntrada(Long idProdutoAlmoxarifadoEntrada) {
        this.idProdutoAlmoxarifadoEntrada = idProdutoAlmoxarifadoEntrada;
    }

    public Long getIdFornecedorEntrada() {
        return idFornecedorEntrada;
    }

    public void setIdFornecedorEntrada(Long idFornecedorEntrada) {
        this.idFornecedorEntrada = idFornecedorEntrada;
    }

    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }

    public void setAlmoxarifado(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
    }

    public Long getIdAlmoxarifadoEntrada() {
        return idAlmoxarifadoEntrada;
    }

    public void setIdAlmoxarifadoEntrada(Long idAlmoxarifadoEntrada) {
        this.idAlmoxarifadoEntrada = idAlmoxarifadoEntrada;
    }

    public List<FornecedorExterno> getFornecedores() {
        return this.fornecedorExternoDAO.getList();
    }

    public List<Produto> getProdutos() {
        return this.produtoDAO.getList();
    }

    public List<Almoxarifado> getAlmoxarifados() {
        return this.almoxarifadoDAO.getList();
    }

    public Long getSize() {
        return this.entradaDAO.getSize();
    }
    
    public String relatorio() {
        return "relatorio-entrada";
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    
    

}
