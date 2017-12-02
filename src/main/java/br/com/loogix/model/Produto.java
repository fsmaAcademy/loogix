/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author thiago
 */
@Entity
public class Produto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private String descricao;
    
    @OneToMany(mappedBy = "produto")
    private List<ProdutoAlmoxarifado> produtoAlmoxarifados;

    public Produto() {
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ProdutoAlmoxarifado> getProdutos() {
        return Collections.unmodifiableList(produtoAlmoxarifados);
    }

    public void addProdutoAlmoxarifado(ProdutoAlmoxarifado produtoAlmoxarifado) {
        this.produtoAlmoxarifados.add(produtoAlmoxarifado);
        produtoAlmoxarifado.setProduto(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
