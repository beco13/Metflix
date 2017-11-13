package bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.metflix.AdministradorEJB;
import com.metflix.Cliente;
import com.metflix.ClienteEJB;
import com.metflix.Pelicula;


@ManagedBean
public class ClienteBean 
{
	@EJB
	private ClienteEJB clienteEJB;
	
	private String identificacion;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasena;

	public void registrarCliente() {
		try {
			 Cliente cliente = (Cliente) clienteEJB.registrarCliente(apellido, contrasena, correo, 
						identificacion, nombre );
			

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso" + cliente.toString());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}

	}

	public ClienteEJB getClienteEJB() {
		return clienteEJB;
	}

	public void setClienteEJB(ClienteEJB clienteEJB) {
		this.clienteEJB = clienteEJB;
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
	
	
	
}
