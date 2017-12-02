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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thiago
 */
@Entity
@Table (name = "empregado")
public class Empregado implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "matricula", nullable = false)
    private String matricula;
    
    private String nome;
    
    @ManyToOne
    @JoinColumn (name = "id_funcao")
    private Funcao funcao;
    
    @ManyToOne
    @JoinColumn(name = "id_almoxarifado")
    private Almoxarifado almoxarifado;
    
    @OneToMany(mappedBy = "empregado")
    private List<Saida> saidas;

    public Empregado() {
    }

    public Empregado(Long id, String matricula, String nome, Funcao funcao, Almoxarifado almoxarifado, List<Saida> saidas) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.funcao = funcao;
        this.almoxarifado = almoxarifado;
        this.saidas = saidas;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }
    
    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }
    
    public void setAlmoxarifado(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
    }

    public List<Saida> getSaidas() {
        return Collections.unmodifiableList(saidas);
    }

    public void addSaida(Saida saida) {
        this.saidas.add(saida);
        saida.setEmpregado(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Empregado other = (Empregado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
