package bean;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import com.metflix.AdministradorEJB;
import com.metflix.Pelicula;

public class DetallesPeliculaBean 
{
	@EJB
	private AdministradorEJB administradorEJB;
	//private String id;
	private double calificacion;
	private String clasificacion;
	private String director;
	private Date fecha_estreno;
	private String idioma;
	private String pais;
	private String reparto;
	private String sinopsis;
	private String titulo;
	private String genero;
	
	@PostConstruct
	public void init()
	{
		Map<String, String> params =FacesContext.getCurrentInstance().
	            getExternalContext().getRequestParameterMap();
	//String parameterOne = params.get("parameterOne");
	System.out.println(" valores get:  "+params.toString());
	cargarPelicula(params.get("id"));
	}
	
	public void cargarPelicula(String pelicula_id)
	{
		Pelicula tmpPelicula=administradorEJB.buscarPeliculaPorId(Integer.parseInt( pelicula_id));
		
		if(tmpPelicula!=null)
		{
			calificacion=tmpPelicula.getCalificacion();
			clasificacion=tmpPelicula.getClasificacion();
			director=tmpPelicula.getDirector();
			fecha_estreno=tmpPelicula.getFechaEstreno();
			idioma=tmpPelicula.getIdioma();
			pais=tmpPelicula.getPais();
			reparto=tmpPelicula.getReparto();
			sinopsis=tmpPelicula.getSinopsis();
			titulo=tmpPelicula.getTitulo();
			genero=tmpPelicula.getGenero();
		}
		else {
			
		}
		
		
		
		
	}
	
	
	
	
}
