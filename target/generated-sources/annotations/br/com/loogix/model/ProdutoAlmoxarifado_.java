package br.com.loogix.model;

import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Entrada;
import br.com.loogix.model.Produto;
import br.com.loogix.model.Saida;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-01T22:39:02")
@StaticMetamodel(ProdutoAlmoxarifado.class)
public class ProdutoAlmoxarifado_ { 

    public static volatile SingularAttribute<ProdutoAlmoxarifado, Produto> produto;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Almoxarifado> almoxarifado;
    public static volatile ListAttribute<ProdutoAlmoxarifado, Entrada> entradas;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Long> id;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Integer> quantidade;
    public static volatile ListAttribute<ProdutoAlmoxarifado, Saida> saidas;

}