/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author thiago
 */
@Entity
@Table (name = "entrada")
public class Entrada implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "DATE")
    private LocalDate data;
    
    private Integer quantidade;
    
    @ManyToOne
    @JoinColumn (name = "id_fornecedor")
    private FornecedorExterno fornecedorExterno;
    
    @ManyToOne
    @JoinColumn (name = "id_produto_almoxarifado")
    private ProdutoAlmoxarifado produtoAlmoxarifado;

    public Entrada() {
        this.data = LocalDate.now();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    
    public String getDataFormatada() {
        return this.data.toString();
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public FornecedorExterno getFornecedorExterno() {
        return fornecedorExterno;
    }

    public void setFornecedorExterno(FornecedorExterno fornecedorExterno) {
        this.fornecedorExterno = fornecedorExterno;
    }

    public ProdutoAlmoxarifado getProdutoAlmoxarifado() {
        return produtoAlmoxarifado;
    }

    public void setProdutoAlmoxarifado(ProdutoAlmoxarifado produtoAlmoxarifado) {
        this.produtoAlmoxarifado = produtoAlmoxarifado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", data=" + data + ", quantidade=" + quantidade + ", fornecedorExterno=" + fornecedorExterno + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Entrada other = (Entrada) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
}
