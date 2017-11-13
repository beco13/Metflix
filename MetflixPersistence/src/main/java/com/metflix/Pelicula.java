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
	@NamedQuery(name = Pelicula.FIND_BY_TITULO, query = "select pelicula from Pelicula pelicula where pelicula.titulo like '%:titulo%'" ),
	@NamedQuery(name = Pelicula.GET_ALL, query = "select pelicula from Pelicula pelicula" ),
	@NamedQuery(name = Pelicula.GET_BY_CALIFICACION, query = "select pelicula from Pelicula pelicula where pelicula.calificacion = :calificacion" )
})
public class Pelicula implements Serializable {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Column(length = 45)
	private String titulo;
	
	private String sinopsis;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_estreno")
	private Date fechaEstreno;
	
	@Column(length = 100)
	private String pais;
	
	private String reparto;
	
	@Column(length = 100)
	private String director;
	
	@Column(length = 45)
	private String idioma;
	
	//@ManyToOne
	//private Genero genero;
	
	@Column(length = 100)
	private String genero;


	@Column(length = 56)
	private String clasificacion;
	
	@Column(length = 3)
	private Double calificacion;
	
	@ManyToMany
	private List<Venta> ventas;
	
	@OneToMany(mappedBy="pelicula")
	private List<CalificacionesPelicula> calificaciones;
	
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_ID = "Pelicula_findById";
	public static final String GET_ALL = "Pelicula_getAll";
	public static final String FIND_BY_TITULO = "Pelicula_findByTitulo";
	public static final String GET_BY_CALIFICACION = "Pelicula_findByCalificacion";

	public Pelicula() {
		super();
	}
	
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
/*
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	*/

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

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<CalificacionesPelicula> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<CalificacionesPelicula> calificaciones) {
		this.calificaciones = calificaciones;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", sinopsis=" + sinopsis + ", fechaEstreno="
				+ fechaEstreno + ", pais=" + pais + ", reparto=" + reparto + ", director=" + director + ", idioma="
				+ idioma + ", genero=" + genero + ", clasificacion=" + clasificacion + ", ventas=" + ventas + ", calificaciones="
				+ calificaciones + "]";
	}
	
	
	
}
