package br.com.loogix.model;

import br.com.loogix.model.Almoxarifado;
import br.com.loogix.model.Funcao;
import br.com.loogix.model.Saida;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-01T22:39:02")
@StaticMetamodel(Empregado.class)
public class Empregado_ { 

    public static volatile SingularAttribute<Empregado, Funcao> funcao;
    public static volatile SingularAttribute<Empregado, Almoxarifado> almoxarifado;
    public static volatile SingularAttribute<Empregado, String> matricula;
    public static volatile SingularAttribute<Empregado, String> nome;
    public static volatile SingularAttribute<Empregado, Long> id;
    public static volatile ListAttribute<Empregado, Saida> saidas;

}