package br.com.loogix.model;

import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Entrada;
import br.com.loogix.model.Produto;
import br.com.loogix.model.Saida;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-03T03:07:52")
@StaticMetamodel(ProdutoAlmoxarifado.class)
public class ProdutoAlmoxarifado_ { 

    public static volatile SingularAttribute<ProdutoAlmoxarifado, Produto> produto;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Entrada> entrada;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Almoxarifado> almoxarifado;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Saida> saida;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Long> id;
    public static volatile SingularAttribute<ProdutoAlmoxarifado, Integer> quantidade;

}