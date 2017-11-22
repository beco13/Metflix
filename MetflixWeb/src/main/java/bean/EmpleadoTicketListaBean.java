package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.metflix.AdministradorEJB;
import com.metflix.ClienteEJB;
import com.metflix.Empleado;
import com.metflix.EmpleadoEJB;
import com.metflix.Pelicula;
import com.metflix.Persona;
import com.metflix.Ticket;
import com.metflix.Venta;

@ManagedBean
public class EmpleadoTicketListaBean {
	
	@EJB
	private EmpleadoEJB empleadoEJB;
	
	private List<Ticket> tickets;

	/**
	 * permite obtener todos los registros de peliculas
	 */
	@PostConstruct
	public void listar() {
		setTickets(empleadoEJB.consultarTickets());
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
