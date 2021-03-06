/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loogix.controller;

import br.com.loogix.dao.AlmoxarifadoDAO;
import br.com.loogix.dao.EmpregadoDAO;
import br.com.loogix.dao.ProdutoAlmoxarifadoDAO;
import br.com.loogix.dao.ProdutoDAO;
import br.com.loogix.dao.SaidaDAO;
import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Empregado;
import br.com.loogix.model.Produto;
import br.com.loogix.model.ProdutoAlmoxarifado;
import br.com.loogix.model.Saida;
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
@Named(value = "saidaController")
@SessionScoped
public class SaidaController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private SaidaDAO saidaDAO;

    @EJB
    private AlmoxarifadoDAO almoxarifadoDAO;
    private Almoxarifado almoxarifado;

    @EJB
    private ProdutoAlmoxarifadoDAO produtoAlmoxarifadoDAO;
    ProdutoAlmoxarifado produtoAlmoxarifado;

    @EJB
    private ProdutoDAO produtoDAO;
    private Produto produto;

    @EJB
    private EmpregadoDAO empregadoDAO;
    private Empregado empregado;

    private Saida saida;
    private List<Saida> saidas;
    private List<Saida> saidasFiltradas;

    private Long idEmpregadoSaida;
    private Long idProduto;
    private Long idAlmoxarifado;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String mensagem;

    public String listar() {
        this.saidas = this.saidaDAO.getList();
        return "saida?faces-redirect=true";
    }

    public String novo() {
        this.saida = new Saida();
        this.produtoAlmoxarifado = new ProdutoAlmoxarifado();
        this.saida.setData(LocalDate.now());
        return "novo-saida?faces-redirect=true";
    }

    public String gravar() {

        this.mensagem = null;
        this.almoxarifado = this.almoxarifadoDAO.buscaPorId(idAlmoxarifado);
        this.empregado = this.empregadoDAO.buscaPorId(idEmpregadoSaida);
        this.produto = this.produtoDAO.buscaPorId(idProduto);
        
        if (saida.getQuantidade() <= 0) {
            this.mensagem = "A quantidade deve ser maior que zero";
            return null;
        }
        
        if (almoxarifado == null) {
            this.mensagem = "O almoxarifado deve ser informado";
            return null;
        }

        if (produto == null) {
            this.mensagem = "O produto deve ser informado";
            return null;
        }

        ProdutoAlmoxarifado pa = this.produtoAlmoxarifadoDAO.busca(
                idAlmoxarifado,
                idProduto
        );
        if (pa == null) {
            this.mensagem = "Não temos esse produto neste almoxarifado";
            return null;
        }

        if (empregado == null) {
            this.mensagem = "O responsável deve ser informado";
            return null;
        }

        if (pa.getQuantidade() < saida.getQuantidade()) {
            this.mensagem = "Quantidade informada não tem em nosso estoque";
            return null;
        }

        pa.setQuantidade(pa.getQuantidade() - saida.getQuantidade());

        this.saida.setProdutoAlmoxarifado(pa);
        this.saida.setEmpregado(empregado);

        this.saidaDAO.update(this.saida);

        this.saidas = this.saidaDAO.getList();

        return "saida?faces-redirect=true";

    }

    public String gerarRelatorioDetalhe() {
        
        if (this.dataInicio == null) {
            this.mensagem = "Data de inicio não foi encontrada";
            return null;
        }
        
        if (this.dataInicio == null) {
            this.mensagem = "Data de fim não foi encontrada";
            return null;
        }
        
        this.saidasFiltradas = this.saidaDAO.buscaPorTempoDeterminado(
                this.dataInicio,
                this.dataFim
        );
        
        if(this.saidasFiltradas == null) {
            this.mensagem = "Não há registros entre essas datas";
            return null;
        }
        
        return "relatorio-saida-detalhe?faces-redirect=true";
    }

    public String relatorio() {
        this.dataInicio = null;
        this.dataFim = null;
        return "relatorio-saida?faces-redirect=true";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdAlmoxarifado() {
        return idAlmoxarifado;
    }

    public void setIdAlmoxarifado(Long idAlmoxarifado) {
        this.idAlmoxarifado = idAlmoxarifado;
    }

    public Long getEmpregadoSaida() {
        return idEmpregadoSaida;
    }

    public void setEmpregadoSaida(Long id) {
        this.idEmpregadoSaida = id;
    }

    public Saida getSaida() {
        return this.saida;
    }

    public void setSaida(Saida saida) {
        this.saida = saida;
    }

    public List<Saida> getSaidas() {
        return this.saidaDAO.getList();
    }

    public List<Empregado> getEmpregados() {
        return this.empregadoDAO.getList();
    }

    public List<Empregado> getResponsaveis() {
        return this.empregadoDAO.getResponsaveis();
    }

    public List<Produto> getProdutos() {
        return this.produtoDAO.getList();
    }

    public List<Almoxarifado> getAlmoxarifados() {
        return this.almoxarifadoDAO.getList();
    }

    public Long getSize() {
        return this.saidaDAO.getSize();
    }

    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }

    public void setAlmoxarifado(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
    }

    public ProdutoAlmoxarifado getProdutoAlmoxarifado() {
        return produtoAlmoxarifado;
    }

    public void setProdutoAlmoxarifado(ProdutoAlmoxarifado produtoAlmoxarifado) {
        this.produtoAlmoxarifado = produtoAlmoxarifado;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
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

    public Long getIdEmpregadoSaida() {
        return idEmpregadoSaida;
    }

    public void setIdEmpregadoSaida(Long idEmpregadoSaida) {
        this.idEmpregadoSaida = idEmpregadoSaida;
    }

    public List<Saida> getSaidasFiltradas() {
        return saidasFiltradas;
    }

    public void setSaidasFiltradas(List<Saida> saidasFiltradas) {
        this.saidasFiltradas = this.saidaDAO.buscaPorTempoDeterminado(dataInicio, dataFim);
    }

    public String getMensagem() {
        return this.mensagem;
    }

}
