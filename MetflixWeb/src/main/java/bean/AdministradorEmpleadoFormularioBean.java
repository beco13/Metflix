package bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.metflix.AdministradorEJB;
import com.metflix.Empleado;

@ManagedBean
public class AdministradorEmpleadoFormularioBean {

	@EJB
	private AdministradorEJB administradorEJB;
	
	@Size(min=0)
	private String id;
	private String nombre;
	private String apellido;
	private String identificacion;
	private String correo;
	private String contrasena;

	/**
	 * permite inicializar los datos necesarios para el funcionamiento del formulario
	 */
	@PostConstruct
	public void init() {
		
	}

	/**
	 * metodo que se ejecuta antes de cargar la vista y permite cargar los datos de una pelicula, 
	 * esto para el caso en que se este editando
	 * 
	 * @param event
	 */
	public void initView(ComponentSystemEvent event) {
		
		String tmpId = cargarIdParametro(); 
		
		if(tmpId != null) {
			if(id == null) {
				id = tmpId;
				System.out.println("CARGANDO EMPLEADO: "+id);
				cargarEmpleado(id);
			}
		}
	}
	
	/**
	 * permite obtener el id recibido por parametro
	 * 
	 * @return
	 */
	public String cargarIdParametro() {
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String parameterOne = params.get("parameterOne");
		return params.get("id");
	}
	

	/**
	 * permite consultar la informacion de una pelicula para cargar los datos sobre el formulario
	 * 
	 * @param pelicula_id
	 */
	public void cargarEmpleado(String id) {
		
		Empleado tmpEmpleado = administradorEJB.buscarEmpleadoPorId(Integer.parseInt(id));

		if (tmpEmpleado != null) {
			id = Integer.toString(tmpEmpleado.getId());
			nombre = tmpEmpleado.getNombre();
			apellido = tmpEmpleado.getApellido();
			identificacion = tmpEmpleado.getIdentificacion();
			correo =  tmpEmpleado.getCorreo();
			contrasena = tmpEmpleado.getContrasena();
		}
	}

	/**
	 * permite registrar o guardar los cambios de una pelicula
	 * @return
	 */
	public String guardar() {
		
		try {
			
			FacesMessage facesMsg;
			
			if(id == null || id.trim() == "") {
				Empleado tmpEmpleado = (Empleado) administradorEJB.registrarEmpleado(identificacion, nombre, apellido, correo, contrasena);
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Registro exitoso: " + tmpEmpleado.toString());
				id = Integer.toString(tmpEmpleado.getId());
			}else {
				administradorEJB.actualizarEmpleado(Integer.parseInt(id), identificacion, nombre, apellido, correo, contrasena);
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambios guardados", "Cambios guardados ");
			}
			
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return  "detalles?faces-redirect=true&id="+id;
			
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
	
		return null;
	}

	public AdministradorEJB getAdministradorEJB() {
		return administradorEJB;
	}

	public void setAdministradorEJB(AdministradorEJB administradorEJB) {
		this.administradorEJB = administradorEJB;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id.trim() != "") {
			this.id = id;
		}
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
