package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.metflix.AdministradorEJB;
import com.metflix.Pelicula;

@ManagedBean
public class AdministradorPeliculaListaBean {
	@EJB
	private AdministradorEJB administradorEJB;
	private List<Pelicula> peliculas;
	private String filtro;

	/**
	 * permite obtener todos los registros de peliculas
	 */
	@PostConstruct
	public void listarPeliculas() {
		peliculas = administradorEJB.consultarPeliculas();
	}

	/**
	 * permite buscar peliculas por nombre o por calificación
	 */
	public void buscarPeliculas() {
		peliculas = administradorEJB.buscarPelicula(filtro);
	}

	/**
	 * permite eliminar peliculas por el id de registro
	 * @param pelicula_id
	 */
	public void eliminarPelicula(int pelicula_id) {
		administradorEJB.eliminarPelicula(pelicula_id);
		listarPeliculas();
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
