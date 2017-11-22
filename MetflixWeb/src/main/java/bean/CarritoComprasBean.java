package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.metflix.Cliente;
import com.metflix.ClienteEJB;
import com.metflix.Correo;
import com.metflix.Pelicula;
import com.metflix.Persona;
import com.metflix.Venta;
import com.metflix.VentaItem;

@ManagedBean
@SessionScoped
public class CarritoComprasBean implements Serializable {

	@EJB
	ClienteEJB clienteEJB;
	
	private List<VentaItem> items = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * permite registrar la compra de todo lo que haya en el carrito de compras
	 */
	public String registrarCompra(int cliente_id) {
		try {
			// registramos la venta
			Venta tmpVenta = clienteEJB.registrarCompra(new Date(), cliente_id, items);
			
			// enviamos el correo de notificacion de la compra al cliente
			enviarCorreoConfirmacion(tmpVenta.getCliente());
			
			// reseteamos el carrito de compras
			items = new ArrayList<>();
			
			return "lista?faces-redirect=true";
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	/**
	 * metodo que permite enviar a un usuario un correo con la notificaicno de la compra
	 * 
	 * @param boolean
	 * @return
	 */
	public boolean enviarCorreoConfirmacion(Persona user) {
		
		String tmpPeliculas = "";
		
		for(VentaItem item : items) {
			tmpPeliculas += "pelicula: "+item.getPelicula().getTitulo()+" cantidad: "+item.getCantidad();
			tmpPeliculas += "\r\n";
		}
		
		Correo tmpCorreo = new Correo();
		tmpCorreo.setEmailDestinatario(user.getCorreo());
		tmpCorreo.setAsunto("Confirmacion de compra");
		tmpCorreo.setMensaje("Este correo se le ha enviado por que hemos recibido una compra de ud con los siguientes items: "+ tmpPeliculas);
		return tmpCorreo.enviar();
	}
	
	
	
	/**
	 * permite elimiar una pelicula del carrito de compras
	 * 
	 * @param pelicula_id
	 */
	public void eliminarItem(int pelicula_id) {
		
		for(int i = 0; i < items.size() ; i++) {
			
			// verificamos si es el mismo
			if(items.get(i).getPelicula().getId() == pelicula_id) {
				
				// eliminamos
				items.remove(i);
			}
		}
	}
	
	
	/**
	 * Permite agregar una pelicula al carrito de compras
	 * 
	 * @param pelicula_id
	 */
	public void agregarItem (int pelicula_id) {
		
		boolean alreadyExists = false;
		
		for(int i = 0; i < items.size() ; i++) {
		
			// verificamos si es el mismo
			if(items.get(i).getPelicula().getId() == pelicula_id) {
				// indicamos que ya existe
				alreadyExists = true;
				
				// obtenemos la cantidad que ya hay
				int tmpCantidad = items.get(i).getCantidad();
				
				// aumentamos la cantidad en 1
				items.get(i).setCantidad(tmpCantidad + 1);
			}
		}
		
		if(!alreadyExists) {
			Pelicula tmpPelicula = null;
			
			try {
				tmpPelicula = clienteEJB.buscarPeliculaPorId(pelicula_id);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Pelicula no encontrada: "+pelicula_id);
			}
			
			if(tmpPelicula != null) {
				VentaItem tmpItem = new VentaItem();
				tmpItem.setPelicula(tmpPelicula);
				tmpItem.setCantidad(1);
				items.add(tmpItem);
			}
		}
		
		
	}
	
	/**
	 * permite obtener la cantidad de items en el carrito de compras
	 * 
	 * @return
	 */
	public int totalItems() {
		int cantidadTotal = 0;
		for(VentaItem item : items) {
			cantidadTotal += item.getCantidad();
		}
		return cantidadTotal;
	}
	
	/**
	 * permite ajustar la cantidad de un item agregado al carrito de compras
	 * 
	 * @param pelicula_id
	 * @param operacion
	 */
	public void ajustarCantidadItem(int pelicula_id, String operacion) {
		
		for(int i = 0; i < items.size() ; i++) {
			
			// verificamos si es el mismo
			if(items.get(i).getPelicula().getId() == pelicula_id) {
				
				int tmpCantidad = items.get(i).getCantidad();
				
				if(operacion.equals("+")) {
					items.get(i).setCantidad(tmpCantidad + 1); 
				}else {
					if(tmpCantidad > 0) {
						items.get(i).setCantidad(tmpCantidad - 1);
					}
				}
				
				break;
			}
		}
		
	}
	
	
	public List<VentaItem> getItems() {
		return items;
	}
	
	public void setItems(List<VentaItem> items) {
		this.items = items;
	}
	
	
}
