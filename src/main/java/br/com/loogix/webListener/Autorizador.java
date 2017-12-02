package br.com.loogix.webListener;

import br.com.loogix.model.Usuario;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class Autorizador implements PhaseListener {

    private static final long serialVersionUID = 1L;
    
    @Override
    public void afterPhase(PhaseEvent evento) {
        System.out.println("afterPhase(PhaseEvent evento)");
        FacesContext context = evento.getFacesContext();
        String nomePagina = context.getViewRoot().getViewId();
        System.out.println("Nome da página: " + nomePagina);

        if (nomePagina.endsWith("login.xhtml")) {
            return;
        }
        Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
        if (usuarioLogado != null) {
            System.out.println("Usuário está logado");
            return;
        }

        // Redirecionamento para login.xhtml
        NavigationHandler handler = context.getApplication().getNavigationHandler();
        handler.handleNavigation(context, null, "login?faces-redirect=true");
        context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent evento) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
