package bean;

import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import com.metflix.AdministradorEJB;
import com.metflix.Cliente;
import com.metflix.Empleado;
import com.metflix.EmpleadoEJB;
import com.metflix.Pelicula;
import com.metflix.Ticket;
import com.metflix.TicketRespuesta;

@ManagedBean
public class EmpleadoTicketDetallesBean {

	@EJB
	private EmpleadoEJB empleadoEJB;

	@ManagedProperty(value = "#{id}")
	private String id;

	private Date fecha;
	private String asunto;
	private String descripcion;
	private Cliente cliente;
	private boolean estado = false;
	
	private String nuevaRespuesta;
	private TicketRespuesta respuesta;
	
	 

	public EmpleadoTicketDetallesBean() {

	}

	public void init(ComponentSystemEvent event) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String parameterOne = params.get("parameterOne");
		
		if (params.get("id") != null) {
			id = params.get("id");
			cargarTicket(Integer.parseInt(id));
		}else {
			if(id != null) {
				cargarTicket(Integer.parseInt(id));
			}
		}
	}

	/**
	 * permite consultar la information de una pelicula para cargar los datos sobre
	 * el formulario
	 * 
	 * @param id
	 */
	public void cargarTicket(int id) {
		Ticket tmpTicket = empleadoEJB.buscarTicketPorId(id);
		if (tmpTicket != null) {
			fecha = tmpTicket.getFecha();
			asunto = tmpTicket.getAsunto();
			descripcion = tmpTicket.getDescripcion();
			cliente = tmpTicket.getCliente();
			estado = tmpTicket.getEstado();
			
			
			if(estado) {
				cargarTicketRespuesta(id);
			}
		}
	}
	
	/**
	 * Permite cargar la respuesta del ticket
	 * 
	 * @param id
	 */
	public void cargarTicketRespuesta(int id) {
		respuesta = empleadoEJB.buscarRespuestaFromTicket(id);
	}
	

	/**
	 * Permite registrar una respuesta
	 * 
	 * @param empleado_id
	 * @return
	 */
	public String registrarRespuesta(int empleado_id) {

		try {

			FacesMessage facesMsg;

			respuesta = empleadoEJB.registrarRespuesta(empleado_id, Integer.parseInt(id), nuevaRespuesta);
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso: " + respuesta.toString());
			
			// actualizamos el estado para que ya no se pueda registrar mas respuestas
			estado = true;

			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "lista?faces-redirect=true";

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERORR GUARDADNDO RESPUESTA: "+e.getMessage());
		}
		

		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getNuevaRespuesta() {
		return nuevaRespuesta;
	}

	public void setNuevaRespuesta(String nuevaRespuesta) {
		this.nuevaRespuesta = nuevaRespuesta;
	}

	public TicketRespuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(TicketRespuesta respuesta) {
		this.respuesta = respuesta;
	}
	
	

}
