package bean;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import com.metflix.AdministradorEJB;
import com.metflix.Pelicula;

@ManagedBean
@RequestScoped
public class DetallesPeliculaBean {
	
	@EJB
	private AdministradorEJB administradorEJB;
	
	@ManagedProperty(value = "#{id}")
	private String id;
	
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

	
	public DetallesPeliculaBean() {
		
	}
	
	public void init(ComponentSystemEvent event) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String parameterOne = params.get("parameterOne");
		id = params.get("id");
		
		if(id != null) {
			cargarPelicula(id);
		}
		
	}

	public void cargarPelicula(String pelicula_id) {
		Pelicula tmpPelicula = administradorEJB.buscarPeliculaPorId(Integer.parseInt(pelicula_id));

		if (tmpPelicula != null) {
			calificacion = tmpPelicula.getCalificacion();
			clasificacion = tmpPelicula.getClasificacion();
			director = tmpPelicula.getDirector();
			fecha_estreno = tmpPelicula.getFechaEstreno();
			idioma = tmpPelicula.getIdioma();
			pais = tmpPelicula.getPais();
			reparto = tmpPelicula.getReparto();
			sinopsis = tmpPelicula.getSinopsis();
			titulo = tmpPelicula.getTitulo();
			genero = tmpPelicula.getGenero();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
