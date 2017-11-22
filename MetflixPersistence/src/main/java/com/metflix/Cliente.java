package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Cliente.FIND_BY_ID, query = "select persona from Cliente persona where persona.id = :id" ),
	@NamedQuery(name = Cliente.FIND_BY_CEDULA, query = "select persona from Cliente persona where persona.identificacion LIKE :identificacion" ),
	@NamedQuery(name = Cliente.GET_ALL, query = "select persona from Cliente persona" ),
	@NamedQuery(name = Cliente.LOGIN, query = "select persona from Cliente persona where persona.correo = :correo and persona.contrasena = :contrasena" ),
	@NamedQuery(name = Cliente.GET_BY_EMAIL, query = "select persona from Cliente persona where persona.correo = :correo" ),
})
public class Cliente extends Persona implements Serializable {

	@OneToMany(mappedBy="cliente")
	private List<PeliculaCalificacion> calificaciones;
	
	@OneToMany(mappedBy="cliente")
	private List<Venta> ventas;
	
	@OneToMany(mappedBy="cliente")
	private List<Ticket> tiquets;
		
	private static final long serialVersionUID = 1L;
	

	public static final String FIND_BY_ID = "Cliente_findById";
	public static final String FIND_BY_CEDULA = "Cliente_findByIdentificacion";
	public static final String GET_ALL = "Cliente_getAll";
	public static final String LOGIN = "Cliente_getByLogin";
	public static final String GET_BY_EMAIL = "Cliente_getByEmail";

	
	

	public Cliente() {
		super();
	}

	public List<PeliculaCalificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<PeliculaCalificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<Ticket> getTiquets() {
		return tiquets;
	}

	public void setTiquets(List<Ticket> tiquets) {
		this.tiquets = tiquets;
	}

	@Override
	public String toString() {
		
		return super.toString() + " => Cliente [calificaciones=" + calificaciones + ", ventas=" + ventas + ", tiquets=" + tiquets + "]";
	}   
	
	
}
