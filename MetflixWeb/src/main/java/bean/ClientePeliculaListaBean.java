package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.metflix.AdministradorEJB;
import com.metflix.ClienteEJB;
import com.metflix.Empleado;
import com.metflix.Pelicula;

@ManagedBean
public class ClientePeliculaListaBean {
	@EJB
	private ClienteEJB clienteEJB;
	private List<Pelicula> peliculas;
	private String filtro;

	/**
	 * permite obtener todos los registros de peliculas
	 */
	@PostConstruct
	public void listar() {
		setPeliculas(clienteEJB.consultarPeliculas());
	}

	/**
	 * permite buscar peliculas por nombre o por calificación
	 */
	public void buscar() {
		//empleados = administradorEJB.buscarEmpleadoPorCedula(filtro);
	}

	/**
	 * permite eliminar peliculas por el id de registro
	 * @param pelicula_id
	 */
	public void eliminar(int id) {
		//administradorEJB.eliminarEmpleado(id);
		listar();
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}


}
