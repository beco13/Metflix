package bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.metflix.AdministradorEJB;
import com.metflix.Empleado;
import com.metflix.Genero;
import com.metflix.Pelicula;

@ManagedBean
public class AdministradorPeliculaFormularioBean {

	@EJB
	private AdministradorEJB administradorEJB;
	
	private List<Genero> generos;
	private String id;
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

	/**
	 * permite inicializar los datos necesarios para el funcionamiento del formulario
	 */
	@PostConstruct
	public void init() {
		cargarGeneros();
	}

	/**
	 * metodo que se ejecuta antes de cargar la vista y permite cargar los datos de una pelicula, 
	 * esto para el caso en que se este editando
	 * 
	 * @param event
	 */
	public void initView(ComponentSystemEvent event) {
		
		if(cargarIdParametro() != null) {
			if(id == null) {
				id = cargarIdParametro();
				cargarPelicula(id);
			}
		}
	}
	
	/**
	 * permite obtener el id recibido por parametro
	 * 
	 * @return
	 */
	public String cargarIdParametro() {
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String parameterOne = params.get("parameterOne");
		return params.get("id");
	}
	

	/**
	 * permite consultar la informacion de una pelicula para cargar los datos sobre el formulario
	 * 
	 * @param pelicula_id
	 */
	public void cargarPelicula(String pelicula_id) {
		Pelicula tmpPelicula = administradorEJB.buscarPeliculaPorId(Integer.parseInt(pelicula_id));

		if (tmpPelicula != null) {
			id = Integer.toString(tmpPelicula.getId());
			calificacion = tmpPelicula.getCalificacion();
			clasificacion = tmpPelicula.getClasificacion();
			director = tmpPelicula.getDirector();
			fecha_estreno = tmpPelicula.getFechaEstreno();
			idioma = tmpPelicula.getIdioma();
			pais = tmpPelicula.getPais();
			reparto = tmpPelicula.getReparto();
			sinopsis = tmpPelicula.getSinopsis();
			titulo = tmpPelicula.getTitulo();
			genero = Integer.toString(tmpPelicula.getGenero().getId());
		}
	}

	/**
	 * permite registrar o guardar los cambios de una pelicula
	 * @return
	 */
	public String guardar() {
		
		try {
			
			FacesMessage facesMsg;
			
			if(id == null || id.trim() == "") {
				Pelicula tmpPelicula = (Pelicula) administradorEJB.registrarPelicula(titulo, calificacion, clasificacion, director, fecha_estreno, Integer.parseInt(genero), idioma, pais, reparto, sinopsis);
				 facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Registro exitoso: " + tmpPelicula.toString());
				id = Integer.toString(tmpPelicula.getId());
			}else {
				administradorEJB.actualizarPelicula(Integer.parseInt(id), titulo, calificacion, clasificacion, director, fecha_estreno, Integer.parseInt(genero), idioma, pais, reparto, sinopsis);
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambios guardados", "Cambios guardados ");
			}
			
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return  "detalles?faces-redirect=true&id="+id;
			
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
	
		return null;
	}

	/**
	 * permite cargar los generos
	 */
	public void cargarGeneros() {
		generos = administradorEJB.consultarGeneros();
		System.out.println("generos: "+generos.toString());
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<Genero> getGeneros() {
		return generos;
	}
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
}
