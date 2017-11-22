package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.metflix.AdministradorEJB;
import com.metflix.Cliente;
import com.metflix.Empleado;
import com.metflix.EmpleadoEJB;
import com.metflix.Pelicula;

@ManagedBean
public class EmpleadoClienteListaBean {
	@EJB
	private EmpleadoEJB empleadoEJB;
	private List<Cliente> clientes;
	private String filtro;

	/**
	 * permite obtener todos los registros de peliculas
	 */
	@PostConstruct
	public void listar() {
		clientes = empleadoEJB.consultarClientes();
	}

	/**
	 * permite buscar peliculas por nombre o por calificación
	 */
	public void buscar() {
		clientes = empleadoEJB.buscarClientePorCedula(filtro);
	}

	/**
	 * Permite eliminar un cliente
	 * 
	 * @param id
	 */
	public void eliminar(int id) {
		empleadoEJB.eliminarCliente(id);
		listar();
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
