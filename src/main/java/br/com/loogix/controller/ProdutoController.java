/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.ProdutoDAO;
import br.com.loogix.model.Produto;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author thiago
 */
@Named(value = "produtoController")
@SessionScoped
public class ProdutoController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private ProdutoDAO daoProduto;
    
    private Produto produto;
    private List<Produto> produtos;
    private boolean alterando = false;
    private String nomeBusca = null;
    /**
     * Creates a new instance of ProdutoController
     */
    public ProdutoController() {
    }
    
    public String listar() {
        this.produtos = this.daoProduto.getList();
        return "produto?faces-redirect=true";
    }
    
    public String novo() {
        this.produto = new Produto();
        this.alterando = false;
        return "novo-produto?faces-redirect=true";
    }
    
    public String iniciarAlterar(Produto produto) {
        this.produto = produto;
        this.alterando = true;
        return "novo-produto?faces-redirect=true";
    }
    
    
    public String gravar() {
        
        if (this.alterando == false)
            this.daoProduto.add(this.produto);
        else
            this.daoProduto.update(this.produto);
        
        this.produtos = this.daoProduto.getList();
        
        return "produto?faces-redirect=true"; 
    }
    
    public String excluir(Produto produto) {
        this.daoProduto.delete(produto);
        this.produtos = this.daoProduto.getList();
        return null;
    }
    
    
     public String produtosPorCodigoOuNome() {
        
        Produto produto = this.daoProduto.buscaPorCodigoOuNome(this.nomeBusca);
        
        if (produto == null) {
            return "busca-produto-error?faces-redirect=true";
        }
        
        this.produto = produto;
        return "busca-produto?faces-redirect=true";
    }

    public Long getSize() {
        return this.daoProduto.getSize();
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

   

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }
    
    
    
}
