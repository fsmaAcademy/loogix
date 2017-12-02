package br.com.loogix.model;

import br.com.loogix.model.FornecedorExterno;
import br.com.loogix.model.ProdutoAlmoxarifado;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-01T22:39:02")
@StaticMetamodel(Entrada.class)
public class Entrada_ { 

    public static volatile SingularAttribute<Entrada, LocalDate> data;
    public static volatile SingularAttribute<Entrada, ProdutoAlmoxarifado> produtoAlmoxarifado;
    public static volatile SingularAttribute<Entrada, Long> id;
    public static volatile SingularAttribute<Entrada, FornecedorExterno> fornecedorExterno;
    public static volatile SingularAttribute<Entrada, Integer> quantidade;

}