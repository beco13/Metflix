package bean;

import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.metflix.Cliente;
import com.metflix.ClienteEJB;
import com.metflix.Pelicula;
import com.metflix.Venta;
import com.metflix.VentaItem;

@ManagedBean
@RequestScoped
public class ClienteVentaDetallesBean {
	
	@EJB
	private ClienteEJB clienteEJB;
	
	@ManagedProperty(value = "#{id}")
	private String id;
	private Date fecha;
	private Cliente cliente;
	private List<VentaItem> items;
	
	
	public ClienteVentaDetallesBean() {
		
	}
	
	public void init(ComponentSystemEvent event) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String parameterOne = params.get("parameterOne");
		id = params.get("id");
		
		if(id != null) {
			cargarVenta(id);
			cargarItemsVenta(id);
		}
		
	}


	/**
	 * permite cargar la informacion de una venta
	 * @param venta_id
	 */
	public void cargarVenta(String venta_id) {
		Venta tmpVenta = clienteEJB.buscarVentaPorId(Integer.parseInt(venta_id));
		System.out.println("INFO VENTA: "+tmpVenta.toString());
		if (tmpVenta != null) {
			fecha = tmpVenta.getFecha();
			cliente = tmpVenta.getCliente();
		}
	}
	
	/**
	 * permite cargar los elementos de una venta
	 * @param venta_id
	 */
	public void cargarItemsVenta(String venta_id) {
		System.out.println("informacion de venta id: "+venta_id);
		items = clienteEJB.consultarVentasItemsByVenta(Integer.parseInt(venta_id));
		System.out.println("ITEMS VENTA: "+items.toString());
	}
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<VentaItem> getItems() {
		return items;
	}

	public void setItems(List<VentaItem> items) {
		this.items = items;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

}
