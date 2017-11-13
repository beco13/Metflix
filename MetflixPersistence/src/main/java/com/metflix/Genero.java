package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Genero
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Genero.FIND_BY_ID, query = "select gen from Genero gen where gen.id = :id" ),
	@NamedQuery(name = Genero.DELETE_BY_ID, query = "delete from Genero gen where gen.id = :id" ),
	@NamedQuery(name = Genero.GET_ALL, query = "select gen from Genero gen" )
})

public class Genero implements Serializable {

	//@OneToMany(mappedBy="genero")
	private List<Pelicula> peliculas;   
	
	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Column(length = 100)
	private String nombre;
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL = "Genero_getAll";
	public static final String FIND_BY_ID = "Genero_getById";
	public static final String DELETE_BY_ID = "Genero_deleteById";
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public String toString() {
		return "Genero [peliculas=" + peliculas + ", id=" + id + ", nombre=" + nombre + "]";
	}

	
	
}
