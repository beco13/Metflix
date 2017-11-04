package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Venta
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Venta.GET_ALL, query = "select vta from Venta vta" )
})

public class Venta implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA")
	private Date fecha;
		
	@ManyToOne
	private Cliente cliente;
		
	@ManyToMany(mappedBy="ventas")
	private List<Pelicula> peliculas;
		
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL = "Venta_getAll";
	
	
	public Venta() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente= cliente;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	

	@Override
	public String toString() {
		return "Venta [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + ", peliculas=" + peliculas + "]";
	}

}
