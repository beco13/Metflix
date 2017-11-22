package bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.metflix.AdministradorEJB;
import com.metflix.ClienteEJB;
import com.metflix.Empleado;
import com.metflix.Genero;
import com.metflix.Pelicula;
import com.metflix.Ticket;

@ManagedBean
public class ClienteTicketFormularioBean {

	@EJB
	private ClienteEJB clienteEJB;
	
	
	private String asunto;
	private String descripcion;
	
	/**
	 * permite inicializar los datos necesarios para el funcionamiento del formulario
	 */
	@PostConstruct
	public void init() {
		
	}
	
	/**
	 * permite obtener el id recibido por parametro
	 * 
	 * @return
	 */
	public String cargarIdParametro() {
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String parameterOne = params.get("parameterOne");
		return params.get("id");
	}
	

	/**
	 * permite registrar o guardar los cambios de una pelicula
	 * @return
	 */
	public String guardar(int cliente_id) {
		try {
			
			FacesMessage facesMsg;
			Ticket tmpTicket = clienteEJB.registrarTicket(cliente_id, asunto, descripcion);
			
			if(tmpTicket != null) {
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambios guardados", "Cambios guardados ");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				return  "lista?faces-redirect=true";
			}
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return null;
	}

	
	
	
	

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
