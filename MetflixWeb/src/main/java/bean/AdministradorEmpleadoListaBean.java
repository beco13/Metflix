package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.metflix.AdministradorEJB;
import com.metflix.Empleado;
import com.metflix.Pelicula;

@ManagedBean
public class AdministradorEmpleadoListaBean {
	@EJB
	private AdministradorEJB administradorEJB;
	private List<Empleado> empleados;
	private String filtro;

	/**
	 * permite obtener todos los registros de peliculas
	 */
	@PostConstruct
	public void listar() {
		setEmpleados(administradorEJB.consultarEmpleados());
	}

	/**
	 * permite buscar peliculas por nombre o por calificación
	 */
	public void buscar() {
		empleados = administradorEJB.buscarEmpleadoPorCedula(filtro);
	}

	/**
	 * permite eliminar peliculas por el id de registro
	 * @param pelicula_id
	 */
	public void eliminar(int id) {
		administradorEJB.eliminarEmpleado(id);
		listar();
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

}
