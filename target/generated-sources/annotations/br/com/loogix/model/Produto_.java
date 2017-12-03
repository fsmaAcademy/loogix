package br.com.loogix.model;

import br.com.loogix.model.ProdutoAlmoxarifado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-03T19:56:37")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile SingularAttribute<Produto, String> codigo;
    public static volatile SingularAttribute<Produto, String> nome;
    public static volatile SingularAttribute<Produto, Long> id;
    public static volatile ListAttribute<Produto, ProdutoAlmoxarifado> produtoAlmoxarifados;
    public static volatile SingularAttribute<Produto, String> descricao;

}