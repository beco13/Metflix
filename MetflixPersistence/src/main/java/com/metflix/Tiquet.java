package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tiquet
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Tiquet.GET_BY_CLIENTE, query = "select tiquet from Tiquet tiquet where tiquet.cliente = :cliente" ),
	@NamedQuery(name = Tiquet.GET_BY_ID, query = "select tiquet from Tiquet tiquet where tiquet.id = :id" ),
	@NamedQuery(name = Tiquet.GET_ALL, query = "select tq from Tiquet tq" )
})
public class Tiquet implements Serializable {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Column(length = 100)
	private String asunto;
	
	
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToOne(mappedBy="tiquet")
	private Respuesta respuesta;
	
	private static final long serialVersionUID = 1L;
	

	public static final String GET_BY_CLIENTE = "Tiquets_findByCliente";
	public static final String GET_BY_ID = "Tiquets_findById";
	public static final String GET_ALL = "Tiquet_getAll";

	public Tiquet() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return "Tiquet [id=" + id + ", asunto=" + asunto + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", cliente=" + cliente + ", respuesta=" + respuesta + "]";
	}
   
}
