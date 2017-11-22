package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ticket
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Ticket.GET_BY_CLIENTE, query = "select tiquet from Ticket tiquet where tiquet.cliente = :cliente" ),
	@NamedQuery(name = Ticket.GET_BY_ID, query = "select tiquet from Ticket tiquet where tiquet.id = :id" ),
	@NamedQuery(name = Ticket.GET_ALL, query = "select tq from Ticket tq" )
})
public class Ticket implements Serializable {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Column(length = 100)
	private String asunto;
	
	@Column(length = 500)
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	private Boolean estado = false;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToOne(mappedBy="ticket")
	private TicketRespuesta respuesta;
	
	private static final long serialVersionUID = 1L;
	

	public static final String GET_BY_CLIENTE = "Tiquets_findByCliente";
	public static final String GET_BY_ID = "Tiquets_findById";
	public static final String GET_ALL = "Tiquet_getAll";

	public Ticket() {
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
	
	public TicketRespuesta getRespuesta() {
		return respuesta;
	}
	
	public void setRespuesta(TicketRespuesta respuesta) {
		this.respuesta = respuesta;
	}
	

	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Tiquet [id=" + id + ", asunto=" + asunto + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", cliente=" + cliente + ", respuesta=" + respuesta + "]";
	}
   
}
