package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.metflix.AdministradorEJB;
import com.metflix.ClienteEJB;
import com.metflix.Empleado;
import com.metflix.Pelicula;
import com.metflix.Persona;
import com.metflix.Venta;

@ManagedBean
public class ClienteVentaListaBean {
	@EJB
	private ClienteEJB clienteEJB;
	
	/*
	@ManagedProperty(value="#{authBean.user.id}")
	private Integer userId;
	*/
	private List<Venta> compras;

	/**
	 * permite obtener todos los registros de peliculas
	 */
	public void listar(int userId) {
		compras = clienteEJB.consultarVentasByCliente(userId);
	}

	/*
		public int getUserId() {
			return userId;
		}
	
	
		public void setUserId(int userId) {
			this.userId = userId;
		}
	 */

	public List<Venta> getCompras() {
		return compras;
	}

	public void setCompras(List<Venta> compras) {
		this.compras = compras;
	}



}
