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
	@NamedQuery(name = Respuesta.GET_ALL, query = "select resp from Respuesta resp" )
})

public class Respuesta implements Serializable {

	   
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
	private Tiquet tiquet;
	
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL = "Respuesta_getAll";

	public Respuesta() {
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
	
	public Tiquet getTiquet() {
		return this.tiquet;
	}

	public void setTiquet(Tiquet tiquet) {
		this.tiquet = tiquet;
	}
	
	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", descripcion=" + descripcion + ", fecha=" + fecha + ", empleado=" + empleado
				+ ", tiquet=" + tiquet + "]";
	}
	
}
