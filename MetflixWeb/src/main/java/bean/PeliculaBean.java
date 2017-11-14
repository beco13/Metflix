package bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.metflix.AdministradorEJB;
import com.metflix.Empleado;
import com.metflix.Genero;
import com.metflix.Pelicula;

@ManagedBean
public class PeliculaBean {

	@EJB
	private AdministradorEJB administradorEJB;
	@DecimalMax("5.0")
	@DecimalMin("0.0")
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
/*
	public List<Genero> generos() {
		return administradorEJB.consultarGeneros();
	}*/

	public String registrarPelicula() {
		try {
			Pelicula pelicula = (Pelicula) administradorEJB.registrarPelicula(titulo, calificacion, clasificacion,
					director, fecha_estreno, genero, idioma, pais, reparto, sinopsis);
			

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso" + pelicula.toString());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "detallesPelicula";
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return null;
	}

	public AdministradorEJB getAdministradorEJB() {
		return administradorEJB;
	}

	public void setAdministradorEJB(AdministradorEJB administradorEJB) {
		this.administradorEJB = administradorEJB;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getFecha_estreno() {
		return fecha_estreno;
	}

	public void setFecha_estreno(Date fecha_estreno) {
		this.fecha_estreno = fecha_estreno;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


}
