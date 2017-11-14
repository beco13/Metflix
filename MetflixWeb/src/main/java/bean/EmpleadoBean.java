package bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.metflix.AdministradorEJB;
import com.metflix.Empleado;

@ManagedBean
public class EmpleadoBean 
{
	@EJB
	private AdministradorEJB administradorEJB;
	private Empleado empleado;
	
	private String identificacion;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasena;
	
	

	public void registrarEmpleado() {

		try{
			Empleado empleado = (Empleado) administradorEJB.registrarEmpleado(identificacion, nombre, apellido,  correo, contrasena);
			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso" + empleado.toString());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
			
	}
	

	public String getNombre() {
		return nombre;
	}
	
	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
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
	
	/*
	public void registrarEmpleado()
	{
		empleado = administradorEJB.registrarEmpleado(identificacion, nombre, apellido, correo, contrasena);
	}
	*/
	
}