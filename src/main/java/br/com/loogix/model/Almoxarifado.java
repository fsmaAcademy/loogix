/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.model;

import java.util.Objects;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thiago
 */
@Entity
@Table(name = "almoxarifado")
public class Almoxarifado implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean ativo;
    
    @OneToMany(mappedBy = "almoxarifado")
    private List<Empregado> empregados;
    
    @OneToMany(mappedBy = "almoxarifado")
    private List<ProdutoAlmoxarifado> produtoAlmoxarifados;

    
    public Almoxarifado() {
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Empregado> getEmpregados() {
        return Collections.unmodifiableList(empregados);
    }
    
    public void addEmpregado(Empregado empregado) {
        this.empregados.add(empregado);
        empregado.setAlmoxarifado(this);
    }

    public List<ProdutoAlmoxarifado> getProdutoAlmoxarifados() {
        return Collections.unmodifiableList(produtoAlmoxarifados);
    }
    
    public void addProdutoAlmoxarifado(ProdutoAlmoxarifado produtoAlmoxarifado) {
        this.produtoAlmoxarifados.add(produtoAlmoxarifado);
        produtoAlmoxarifado.setAlmoxarifado(this);
    }

    public String getNumero() {
        return numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Almoxarifado{" + "id=" + id + ", numero=" + numero + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", ativo=" + ativo + ", empregados=" + empregados + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Almoxarifado other = (Almoxarifado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregados.add(empregado);
    }
    
}
