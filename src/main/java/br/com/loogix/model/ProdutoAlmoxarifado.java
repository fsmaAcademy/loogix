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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author thiago
 */
@Entity
public class ProdutoAlmoxarifado implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    
    @ManyToOne
    @JoinColumn (name = "id_almoxarifado")
    private Almoxarifado almoxarifado;
    
    @OneToOne (mappedBy = "produtoAlmoxarifado", cascade = CascadeType.PERSIST)
    private Entrada entrada;
    
    @OneToOne (mappedBy = "produtoAlmoxarifado", cascade = CascadeType.PERSIST)
    private Saida saida;
    
    @ManyToOne
    @JoinColumn (name = "id_produto")
    private Produto produto;

   

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }

    public void setAlmoxarifado(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
    }

//    public List<Entrada> getEntradas() {
//        return Collections.unmodifiableList(entradas);
//    }
//    
//    public void addEntrada(Entrada entrada) {
//        this.entradas.add(entrada);
//        entrada.setProdutoAlmoxarifado(this);
//        this.quantidade += entrada.getQuantidade();
//    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }
    
    
    
//    public List<Saida> getSaidas() {
//        return Collections.unmodifiableList(saidas);
//    }
//
//    public void addSaida(Saida saida) {
//        this.saidas.add(saida);
//        saida.setProdutoAlmoxarifado(this);
//        this.quantidade -= saida.getQuantidade();
//    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Saida getSaida() {
        return saida;
    }

    public void setSaida(Saida saida) {
        this.saida = saida;
    }
    
    

    @Override
    public String toString() {
        return "ProdutoAlmoxarifado{" + "id=" + id + ", quantidade=" + quantidade + ", almoxarifado=" + almoxarifado + ", entrada=" + entrada + ", saida=" + saida + ", produto=" + produto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final ProdutoAlmoxarifado other = (ProdutoAlmoxarifado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
