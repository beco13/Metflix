package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy=InheritanceType.JOINED)

@MappedSuperclass
@NamedQueries({
	@NamedQuery(name = Persona.FIND_BY_ID, query = "select persona from Persona persona where persona.id = :id" ),
	@NamedQuery(name = Persona.FIND_BY_CEDULA, query = "select persona from Persona persona where persona.identificacion = :identificacion" ),
	@NamedQuery(name = Persona.GET_ALL, query = "select persona from Persona persona" ),
	@NamedQuery(name = Persona.LOGIN, query = "select persona from Persona persona where persona.correo = :correo and persona.contrasena = :contrasena" ),
})
public class Persona implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(unique = true, nullable=false)
	private Integer id;
	
	@Column(unique = true, length = 14, nullable=false)
	private String identificacion;
	
	@Column(length = 45)
	private String nombre;
	
	@Column(length = 45)
	private String apellido;
	
	@Column(length = 100, unique = true)
	private String correo;
	
	@Column(length = 255)
	private String contrasena;
	
	private static final long serialVersionUID = 1L;

	public Persona() {
		super();
	}
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public static final String FIND_BY_ID = "Persona_findById";
	public static final String FIND_BY_CEDULA = "Persona_findByIdentificacion";
	public static final String GET_ALL = "Persona_getAll";
	public static final String LOGIN = "Persona_getByLogin";

	@Override
	public String toString() {
		return "Persona [id=" + id + ", identificacion=" + identificacion + ", nombre=" + nombre + ", apellido="
				+ apellido + ", correo=" + correo + ", contrasena=" + contrasena + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identificacion == null) ? 0 : identificacion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (contrasena == null) {
			if (other.contrasena != null)
				return false;
		} else if (!contrasena.equals(other.contrasena))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificacion == null) {
			if (other.identificacion != null)
				return false;
		} else if (!identificacion.equals(other.identificacion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}   
	
	
}
