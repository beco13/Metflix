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
	
}
