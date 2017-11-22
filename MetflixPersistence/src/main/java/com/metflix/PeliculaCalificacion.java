package com.metflix;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Clasificaciones_pelicula
 *
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = PeliculaCalificacion.GET_ALL, query = "select cp from PeliculaCalificacion cp"),
	@NamedQuery(name = PeliculaCalificacion.GET_BY_CLIENTE_PELICULA, query = "select cp from PeliculaCalificacion cp WHERE cp.pelicula = :pelicula and cp.cliente = :cliente  "),
	@NamedQuery(name = PeliculaCalificacion.GET_ALL_BY_PELICULA, query = "select cp from PeliculaCalificacion cp WHERE cp.pelicula = :pelicula") 
})
@Table(name = "PELICULA_CALIFICACION")
public class PeliculaCalificacion implements Serializable {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Pelicula pelicula;

	@Column(length = 5)
	private double calificacion;

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL = "Calificaciones_pelicula_getAll";
	public static final String GET_ALL_BY_PELICULA = "Calificaciones_pelicula_getAllByPelicula";
	public static final String GET_BY_CLIENTE_PELICULA = "Calificaciones_pelicula_getByClientePelicula";

	public PeliculaCalificacion() {
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

	public double getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(double calificacion) {
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
