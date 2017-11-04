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
public class Cliente extends Persona implements Serializable {

	@OneToMany(mappedBy="cliente")
	private List<CalificacionesPelicula> calificaciones;
	
	@OneToMany(mappedBy="cliente")
	private List<Venta> ventas;
	
	@OneToMany(mappedBy="cliente")
	private List<Tiquet> tiquets;
		
	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
	}

	public List<CalificacionesPelicula> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<CalificacionesPelicula> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<Tiquet> getTiquets() {
		return tiquets;
	}

	public void setTiquets(List<Tiquet> tiquets) {
		this.tiquets = tiquets;
	}

	@Override
	public String toString() {
		
		return super.toString() + " => Cliente [calificaciones=" + calificaciones + ", ventas=" + ventas + ", tiquets=" + tiquets + "]";
	}   
	
	
}
