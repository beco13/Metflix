package com.metflix;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Clasificaciones_pelicula
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = CalificacionesPelicula.GET_ALL, query = "select cp from CalificacionesPelicula cp"),
		@NamedQuery(name = CalificacionesPelicula.GET_ALL_BY_PELICULA, query = "select cp from CalificacionesPelicula cp WHERE cp.pelicula = :pelicula_id") })
@Table(name = "CALIFICACIONES_PELICULA")
public class CalificacionesPelicula implements Serializable {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Pelicula pelicula;

	@Column(unique = true, length = 20)
	private Integer calificacion;

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL = "Calificaciones_pelicula_getAll";
	public static final String GET_ALL_BY_PELICULA = "Calificaciones_pelicula_getAllByPelicula";

	public CalificacionesPelicula() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pelicula getPelicula_id() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula_id) {
		this.pelicula = pelicula_id;
	}

	public Integer getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Calificaciones_pelicula [id=" + id + ", cliente=" + cliente + ", pelicula=" + pelicula
				+ ", calificacion=" + calificacion + "]";
	}

}
