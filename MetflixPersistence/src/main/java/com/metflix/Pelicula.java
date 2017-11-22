package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pelicula
 * @param <Venta>
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Pelicula.FIND_BY_ID, query = "select pelicula from Pelicula pelicula where pelicula.id = :id" ),
	@NamedQuery(name = Pelicula.GET_BY_SEARCH, query = "select pelicula from Pelicula pelicula where pelicula.titulo like :buscador OR CAST(pelicula.calificacion as CHAR(10)) like :buscador" ),
	@NamedQuery(name = Pelicula.GET_ALL, query = "select pelicula from Pelicula pelicula" ),
})
public class Pelicula implements Serializable {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Column(length = 100)
	private String titulo;
	
	@Column(length = 700)
	private String sinopsis;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ESTRENO")
	private Date fechaEstreno;
	
	@Column(length = 100)
	private String pais;
	
	@Column(length = 255)
	private String reparto;
	
	@Column(length = 100)
	private String director;
	
	@Column(length = 45)
	private String idioma;
	
	@ManyToOne
	private Genero genero;

	@Column(length = 255)
	private String clasificacion;
	
	@Column(length = 3)
	private Double calificacion;
	
	@OneToMany(mappedBy="pelicula")
	private List<PeliculaCalificacion> calificaciones;
	
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_ID = "Pelicula_findById";
	public static final String GET_ALL = "Pelicula_getAll";
	public static final String GET_BY_SEARCH = "Pelicula_findBySearch";
	

	public Pelicula() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}


	public List<PeliculaCalificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<PeliculaCalificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	
	
}
