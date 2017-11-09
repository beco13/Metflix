package bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.metflix.AdministradorEJB;
import com.metflix.Empleado;
import com.metflix.Genero;
import com.metflix.Pelicula;

@ManagedBean
public class PeliculaBean {

	@EJB
	private AdministradorEJB administradorEJB;
	private double calificacion;
	private String clasificacion;
	private String director;
	private Date fecha_estreno;
	private String idioma;
	private String pais;
	private String reparto;
	private String sinopsis;
	private String titulo;
	private int genero_id;
	
	public List<Genero> generos(){
		return administradorEJB.consultarGeneros();
	}
	
	public void registrarPelicula() {
		try{
			Pelicula pelicula = (Pelicula) administradorEJB
					.registrarPelicula(
							titulo,
							calificacion,
							clasificacion,
							director,
							fecha_estreno,
							genero_id,
							idioma,
							pais,
							reparto,
							sinopsis);
			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso" + pelicula.toString());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		
	}
}
