package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AuthBean {

	private int id;
	private String nombre;
	private String apellido;
	private String identificacion;
	private String correo;
	private String contrasenia;
	
	/**
	 * permite verificar las credenciales de acceso de un usuario al sistema 
	 * @return
	 */
	public String authenticate() {
		
		return null;
	}
	
	/**
	 * permite buscar un usuario por el correo y enviar un correo con la contraseña
	 */
	public void recordarPassword() {
		
	}
	
	/**
	 * permite hacer el registro de un nuevo cliente
	 * @return
	 */
	public String registrarse() {
		
		return null;
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

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}
