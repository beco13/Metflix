package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.metflix.Administrador;
import com.metflix.AdministradorEJB;
import com.metflix.Cliente;
import com.metflix.ClienteEJB;
import com.metflix.Correo;
import com.metflix.Empleado;
import com.metflix.EmpleadoEJB;
import com.metflix.Persona;

@ManagedBean
@SessionScoped
public class AuthBean implements Serializable{

	@EJB
	ClienteEJB clienteEJB;
	
	@EJB
	EmpleadoEJB empleadoEJB;
	
	@EJB
	AdministradorEJB administradorEJB;
	
	private Persona user ;
	private boolean estado = false;
	

	private static final long serialVersionUID = 1L;
	
	

	@PostConstruct
	public void init() {
		if(user == null) {
			user = new Persona();
		}
		
	}
	
	/**
	 * Permite cerrar Sesion
	 * 
	 * @return
	 */
	public String logout() {
		
		user = new Persona();
		estado = false;
		
		return "/pages/index?faces-redirect=true";
	}
	
	/**
	 * permite verificar las credenciales de acceso de un usuario al sistema
	 * 
	 * @return
	 */
	public String login() {

		Persona tmpUser;

		// mandamos los datos a loguear al area de clientes
		tmpUser = clienteEJB.login(user.getCorreo(), user.getContrasena());
		
		if (tmpUser == null) {
			tmpUser = empleadoEJB.login(user.getCorreo(), user.getContrasena());
		}
		
		if (tmpUser == null) {
			tmpUser = administradorEJB.login(user.getCorreo(), user.getContrasena());
		}

		if (tmpUser != null) {
			estado = true;
			user = tmpUser;
			return "index?faces-redirect=true";
		}

		return null;
	}

	/**
	 * permite hacer el registro de un nuevo cliente
	 * 
	 * @return
	 */
	public String registrarse() {

		try {

			Cliente tmpCliente = clienteEJB.registrarCliente(user.getApellido(), user.getContrasena(), user.getCorreo(),
					user.getIdentificacion(), user.getNombre());

			FacesMessage facesMsg;
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso: " + tmpCliente.toString());
			
			// establecemos el usuario logueado
			user = tmpCliente;
			
			// establecemos el nuevo estado de la sesión
			estado = true;
			
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "index?faces-redirect=true";

		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		
		return null;
	}

	/**
	 * permite buscar un usuario por el correo y enviar un correo con la contraseña
	 */
	public void recordarPassword() {
		
		Persona tmpUser;
		
		tmpUser = clienteEJB.remember(user.getCorreo());
		
		if(tmpUser == null) {
			tmpUser =  empleadoEJB.remember(user.getCorreo());
		}
		
		if(tmpUser == null) {
			tmpUser =  administradorEJB.remember(user.getCorreo());
		}
		
		FacesMessage facesMsg;
		
		// validamos si encontro algún usuario
		if(tmpUser == null) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario no encontrado", "No encontramos el usuario");
		}else {
			if(enviarCorreoRemember(tmpUser)) {
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo enviado", "Hemos envaido un coreo con los datos para recuperar tu cuenta");
			}else {
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo no se envió", "No fue posible enviar el correo, intentalo mas tarde");
			}
			user = new Persona();
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}
	
	/**
	 * metodo que permite enviar a un usuario un correo con la contraseña
	 * 
	 * @param user
	 * @return
	 */
	public boolean enviarCorreoRemember(Persona user) {
		Correo tmpCorreo = new Correo();
		tmpCorreo.setEmailDestinatario(user.getCorreo());
		tmpCorreo.setAsunto("Recordacion de Contraseña");
		tmpCorreo.setMensaje("Has solicitado recuperar la contraseña para lo cual esta es tu contraseña: "+ user.getContrasena());
		return tmpCorreo.enviar();
	}

	/**
	 * Permite identificar el tipo de usuario (administrador, empleado o cliente)
	 * 
	 * @return
	 */
	public String getTipoUser() {

		if (user instanceof Cliente) {
			return "cliente";
		}

		if (user instanceof Empleado) {
			return "empleado";
		}

		if (user instanceof Administrador) {
			return "administrador";
		}

		return null;
	}

	/**
	 * permite verificar si el usuario logueado es cliente
	 * 
	 * @return
	 */
	public Boolean userEsCliente() {
		if(estado == true && getTipoUser() == "cliente") {
			return true;
		}
		return false;
	}
	
	/**
	 * permite verificar si el usuario logueado es empleado
	 * 
	 * @return
	 */
	public Boolean userEsEmpleado() {
		if(estado == true && getTipoUser() == "empleado") {
			return true;
		}
		return false;
	}
	
	/**
	 * permite verificar si el usuario logueado es administrador
	 * 
	 * @return
	 */
	public Boolean userEsAdministrador() {
		if(estado == true && getTipoUser() == "administrador") {
			return true;
		}
		return false;
	}
	
	
	public Persona getUser() {
		return user;
	}
	
	public void setUser(Persona user) {
		this.user = user;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setestado(boolean estado) {
		this.estado = estado;
	}

}
