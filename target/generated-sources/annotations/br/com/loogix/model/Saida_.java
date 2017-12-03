package br.com.loogix.model;

import br.com.loogix.model.Empregado;
import br.com.loogix.model.ProdutoAlmoxarifado;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-03T19:56:37")
@StaticMetamodel(Saida.class)
public class Saida_ { 

    public static volatile SingularAttribute<Saida, Empregado> empregado;
    public static volatile SingularAttribute<Saida, LocalDate> data;
    public static volatile SingularAttribute<Saida, ProdutoAlmoxarifado> produtoAlmoxarifado;
    public static volatile SingularAttribute<Saida, Long> id;
    public static volatile SingularAttribute<Saida, Integer> quantidade;

}