package br.com.loogix.model;

import br.com.loogix.model.Empregado;
import br.com.loogix.model.ProdutoAlmoxarifado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-03T22:29:15")
@StaticMetamodel(Almoxarifado.class)
public class Almoxarifado_ { 

    public static volatile SingularAttribute<Almoxarifado, String> cidade;
    public static volatile SingularAttribute<Almoxarifado, String> estado;
    public static volatile SingularAttribute<Almoxarifado, Boolean> ativo;
    public static volatile SingularAttribute<Almoxarifado, String> numero;
    public static volatile SingularAttribute<Almoxarifado, String> logradouro;
    public static volatile SingularAttribute<Almoxarifado, String> bairro;
    public static volatile ListAttribute<Almoxarifado, Empregado> empregados;
    public static volatile SingularAttribute<Almoxarifado, Long> id;
    public static volatile ListAttribute<Almoxarifado, ProdutoAlmoxarifado> produtoAlmoxarifados;

}