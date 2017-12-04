package br.com.loogix.model;

import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-03T22:29:15")
@StaticMetamodel(ProdutoAlmoxarifado.class)
public class ProdutoAlmoxarifado_ { 

    public static volatile SingularAttribute<ProdutoAlmoxarifado, Produto> produto;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Almoxarifado> almoxarifado;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Long> id;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Integer> quantidade;

}