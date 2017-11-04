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
public class Empleado extends Persona implements Serializable {

	@OneToMany(mappedBy="empleado")
	private List<Respuesta> respuestas;   
	
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Override
	public String toString() {
		return "Empleado [respuestas=" + respuestas + "]";
	}   
	
	
	
}
