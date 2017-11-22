
package com.metflix;


import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Administrador.COUNT_ADMIN, query = "SELECT COUNT(admin) FROM Administrador admin" ),
	@NamedQuery(name = Administrador.GET_BY_EMAIL, query = "SELECT admin FROM Administrador admin WHERE admin.correo = :correo" ),
	@NamedQuery(name = Administrador.LOGIN_ADMIN, query = "SELECT admin FROM Administrador admin WHERE admin.correo = :correo AND admin.contrasena = :contrasena" ),
})
public class Administrador extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String COUNT_ADMIN = "total_administradores";
	public static final String LOGIN_ADMIN = "login_admin";
	public static final String GET_BY_EMAIL = "get_by_email";

	public Administrador() {
		super();
	}   
   
}

