package bean;

import java.util.List;

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
	
	
	public void listarPeliculas()
	{
		peliculas = administradorEJB.consultarPeliculas();
		System.out.println("Mostrando peliculas: "+peliculas.toString());
	}
	
	public void buscarPeliculas()
	{
		peliculas = administradorEJB.buscarPeliculaPorTitulo(filtro);
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
