package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.metflix.AdministradorEJB;
import com.metflix.Pelicula;

@ManagedBean
public class ListaPeliculasBean 
{
	@EJB
	private AdministradorEJB administradorEJB;
	private List<Pelicula> peliculas;
	private String filtro;
	
	@PostConstruct
	public void listarPeliculas()
	{
		peliculas = administradorEJB.consultarPeliculas();
	}
	
	public void buscarPeliculas()
	{
		peliculas = administradorEJB.buscarPeliculaPorTitulo(filtro);
	}
	
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
