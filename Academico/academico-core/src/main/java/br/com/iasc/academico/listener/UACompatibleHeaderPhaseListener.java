package br.com.iasc.academico.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author tiago.menezes
 *
 */
public class UACompatibleHeaderPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2786759869607308908L;

	public void afterPhase(PhaseEvent arg0) {
	}

	public void beforePhase(PhaseEvent event) {
		final FacesContext facesContext = event.getFacesContext();
		final HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addHeader("X-UA-Compatible", "IE=EmulateIE9");
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}