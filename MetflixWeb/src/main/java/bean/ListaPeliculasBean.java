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
	List<Pelicula> peliculas;
	String filtro;
	
	
	public void listarPeliculas()
	{
		peliculas = administradorEJB.consultarPeliculas();
		System.out.println(peliculas.toString());
	}
	
	public void buscarPeliculas()
	{
		peliculas = administradorEJB.buscarPeliculaPorTitulo(filtro);
	}
	
	
}
