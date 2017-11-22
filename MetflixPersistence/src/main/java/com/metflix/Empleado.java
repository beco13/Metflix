package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Empleado.FIND_BY_ID, query = "select persona from Empleado persona where persona.id = :id" ),
	@NamedQuery(name = Empleado.FIND_BY_CEDULA, query = "select persona from Empleado persona where persona.identificacion LIKE :identificacion" ),
	@NamedQuery(name = Empleado.GET_ALL, query = "select persona from Empleado persona" ),
	@NamedQuery(name = Empleado.LOGIN, query = "select persona from Empleado persona where persona.correo = :correo and persona.contrasena = :contrasena" ),
	@NamedQuery(name = Empleado.GET_BY_EMAIL, query = "SELECT admin FROM Administrador admin WHERE admin.correo = :correo" ),
})
public class Empleado extends Persona implements Serializable {

	@OneToMany(mappedBy="empleado")
	private List<TicketRespuesta> respuestas;   
	
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Empleado_findById";
	public static final String FIND_BY_CEDULA = "Empleado_findByIdentificacion";
	public static final String GET_ALL = "Empleado_getAll";
	public static final String LOGIN = "Empleado_getByLogin";
	public static final String GET_BY_EMAIL = "Empleado_getByEmail";
	
	
	public Empleado() {
		super();
	}

	public List<TicketRespuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<TicketRespuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Override
	public String toString() {
		return "Empleado [respuestas=" + respuestas + "]";
	}   
	
	
	
}
