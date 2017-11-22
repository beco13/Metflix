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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.metflix.AdministradorEJB;
import com.metflix.ClienteEJB;
import com.metflix.Pelicula;

@ManagedBean
public class ClientePeliculaCalificarBean {
	
	@EJB
	private ClienteEJB clienteEJB;
	
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
	
	@DecimalMax("5.0")
	@DecimalMin("0.0")
	private double nuevaCalificacion = 0;

	
	public ClientePeliculaCalificarBean() {
		
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
		Pelicula tmpPelicula = clienteEJB.buscarPeliculaPorId(Integer.parseInt(pelicula_id));

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
			genero = tmpPelicula.getGenero().getNombre();
		}
	}
	
	/**
	 * permite calificar una pelicula a un cliente
	 * 
	 * @param cliente_id
	 */
	public String registrarCalificacion(int cliente_id) {
		//System.out.println("Cliente: "+cliente_id+" pelicula: "+id+" calificación: "+nuevaCalificacion);
		clienteEJB.calificarPelicula(cliente_id, Integer.parseInt(id), nuevaCalificacion);
		return  "detalles?faces-redirect=true&id="+id;
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

	public String getClasificacion() {
		return clasificacion;
	}

	public String getDirector() {
		return director;
	}

	public Date getFecha_estreno() {
		return fecha_estreno;
	}

	public String getIdioma() {
		return idioma;
	}

	public String getPais() {
		return pais;
	}

	public String getReparto() {
		return reparto;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getGenero() {
		return genero;
	}

	

	public double getNuevaCalificacion() {
		return nuevaCalificacion;
	}

	public void setNuevaCalificacion(double nuevaCalificacion) {
		this.nuevaCalificacion = nuevaCalificacion;
	}


}
