package bean;


import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import com.metflix.AdministradorEJB;
import com.metflix.Cliente;
import com.metflix.Empleado;
import com.metflix.EmpleadoEJB;

@ManagedBean
@RequestScoped
public class EmpleadoClienteDetallesBean {

	@EJB
	private EmpleadoEJB empleadoEJB;

	@ManagedProperty(value = "#{id}")
	private String id;

	private String nombre;
	private String apellido;
	private String identificacion;
	private String correo;
	private String contrasena;

	public EmpleadoClienteDetallesBean() {

	}

	public void init(ComponentSystemEvent event) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String parameterOne = params.get("parameterOne");
		id = params.get("id");

		if (id != null) {
			cargarEmpleado(id);
		}

	}

	/**
	 * permite consultar la informacion de una pelicula para cargar los datos sobre
	 * el formulario
	 * 
	 * @param pelicula_id
	 */
	public void cargarEmpleado(String id) {

		Cliente tmpCliente = empleadoEJB.buscarClientePorId(Integer.parseInt(id));

		if (tmpCliente != null) {
			id = Integer.toString(tmpCliente.getId());
			nombre = tmpCliente.getNombre();
			apellido = tmpCliente.getApellido();
			identificacion = tmpCliente.getIdentificacion();
			correo = tmpCliente.getCorreo();
			contrasena = tmpCliente.getContrasena();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
