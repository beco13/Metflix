package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Respuesta
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = TicketRespuesta.GET_ALL, query = "select resp from TicketRespuesta resp" ),
	@NamedQuery(name = TicketRespuesta.GET_BY_TICKET, query = "select resp from TicketRespuesta resp WHERE resp.ticket = :ticket" )
})
@Table(name = "TICKET_RESPUESTA")
public class TicketRespuesta implements Serializable {
	   
	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne
	private Empleado empleado;
	
	@OneToOne
	private Ticket ticket;
	
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL = "Respuesta_getAll";
	public static final String GET_BY_TICKET = "Respuesta_getFromTicket";

	public TicketRespuesta() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}   
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", descripcion=" + descripcion + ", fecha=" + fecha + ", empleado=" + empleado
				+ ", tiquet=" + ticket + "]";
	}
	
}
