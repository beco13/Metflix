package com.metflix;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: VentaItem
 *
 */
@NamedQueries({
	@NamedQuery(name = VentaItem.FIND_BY_VENTA, query = "select item from VentaItem item where item.venta = :venta" )
})
@Entity
@Table(name = "VENTA_ITEM")
public class VentaItem implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Column(nullable=false)
	private int cantidad = 0;
	
	@ManyToOne
	private Venta venta;
	
	@ManyToOne
	private Pelicula pelicula;

	
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_VENTA = "VentaItem_getByVenta";
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	
	
	
}
