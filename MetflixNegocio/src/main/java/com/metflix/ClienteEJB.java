package com.metflix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ClienteEJB
 */
@Stateless
@LocalBean
public class ClienteEJB implements ClienteEJBRemote {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ClienteEJB() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * permite autenticar un usuario cliente en el sistema
	 * 
	 * @param correo
	 * @param pass
	 * @return
	 */
	public Cliente login(String correo, String pass) {
		try {
			Query query = em.createNamedQuery(Cliente.LOGIN);
			query.setParameter("correo", correo);
			query.setParameter("contrasena", pass);
			return (Cliente) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * Permite consultar un cliente por el correo
	 * @param correo
	 * @return
	 */
	public Cliente remember(String correo) {
		try {
			Query query = em.createNamedQuery(Cliente.GET_BY_EMAIL);
			query.setParameter("correo", correo);
			return (Cliente) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	

	/**
	 * permite registrar un cliente
	 */
	public Cliente registrarCliente(String apellido, String contrasena, String correo, String identificacion,
			String nombre) {

		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setIdentificacion(identificacion);
		cliente.setCorreo(correo);
		cliente.setContrasena(contrasena);

		// guardamos el registro
		em.persist(cliente);
		em.flush();

		return cliente;
	}

	/**
	 * permite buscar un cliente
	 * 
	 * @param id
	 * @return
	 */
	public Cliente buscarClientePorId(Integer id) {
		try {
			Query query = em.createNamedQuery(Cliente.FIND_BY_ID);
			query.setParameter("id", id);
			return (Cliente) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * permite Eliminar un cliente
	 *
	 * @param id
	 */
	public void eliminarCliente(Integer id) {
		Cliente tmpCliente = buscarClientePorId(id);
		em.remove(tmpCliente);
	}

	/**
	 * permite actualizar un cliente
	 *  
	 * @param id
	 * @param apellido
	 * @param contrasena
	 * @param correo
	 * @param identificacion
	 * @param nombre
	 * @return
	 */
	public Cliente actualizarCliente(Integer id, String apellido, String contrasena, String correo,
			String identificacion, String nombre) {

		Cliente tmpCliente = buscarClientePorId(id);
		tmpCliente.setApellido(apellido);
		tmpCliente.setContrasena(contrasena);
		tmpCliente.setCorreo(correo);
		tmpCliente.setIdentificacion(identificacion);
		tmpCliente.setNombre(nombre);

		em.persist(tmpCliente);
		em.flush();

		return tmpCliente;
	}

	/**
	 * permite consultar las peliculas que hay registradas
	 */
	@SuppressWarnings("unchecked")
	public List<Pelicula> consultarPeliculas() {
		Query query = em.createNamedQuery(Pelicula.GET_ALL);
		return Collections.checkedList(query.getResultList(), Pelicula.class);
	}

	/**
	 * permite buscar una pelicula
	 * 
	 * @param id
	 * @return
	 */
	public Pelicula buscarPeliculaPorId(Integer id) {
		Query query = em.createNamedQuery(Pelicula.FIND_BY_ID);
		query.setParameter("id", id);
		return (Pelicula) query.getSingleResult();
	}

	/**
	 * Permite registrar una compra con sus respectivas peliculas
	 * 
	 * @param fecha
	 * @param cliente_id
	 * @param peliculas_id
	 * @return
	 */
	public Venta registrarCompra(Date fecha, Integer cliente_id, List<VentaItem> items) {
		
		Cliente cliente = buscarClientePorId(cliente_id);

		Venta venta = new Venta();
		venta.setCliente(cliente);
		venta.setFecha(fecha);
		em.persist(venta);
		
		for(VentaItem item : items) {
			item.setVenta(venta);
			em.persist(item);
		}
		
		em.flush();
		return venta;
	}

	/**
	 * permite obtener todas las calificaciones de una pelicula
	 * 
	 * @param pelicula_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PeliculaCalificacion> consultarCalificacionesBYPelicula(Integer pelicula_id) {
		
		Pelicula tmpPelicula = buscarPeliculaPorId(pelicula_id);
		
		if(tmpPelicula == null) {
			return new ArrayList<>();
		}
		
		Query query = em.createNamedQuery(PeliculaCalificacion.GET_ALL_BY_PELICULA);
		query.setParameter("pelicula", tmpPelicula);
		return Collections.checkedList(query.getResultList(), PeliculaCalificacion.class);
	}
	
	/**
	 * permite buscar una calificacion por pelicula y cliente,
	 * util para saber si un cliente ya califico una pelicula
	 * 
	 * @param pelicula_id
	 * @param cliente_id
	 * @return
	 */
	public PeliculaCalificacion buscarCalificacion(int pelicula_id, int cliente_id) {
		
		Pelicula tmpPelicula = buscarPeliculaPorId(pelicula_id);
		Cliente tmpCliente = buscarClientePorId(cliente_id);
		
		try {
			Query query = em.createNamedQuery(PeliculaCalificacion.GET_BY_CLIENTE_PELICULA);
			query.setParameter("pelicula", tmpPelicula);
			query.setParameter("cliente", tmpCliente);
			return (PeliculaCalificacion) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	

	/**
	 * permite registrar una calificación de una pelicula
	 * 
	 * @param cliente_id
	 * @param pelicula_id
	 * @param calificacion
	 * @return
	 */
	public void calificarPelicula(Integer cliente_id, Integer pelicula_id, double calificacion) {
		
		Pelicula pelicula = buscarPeliculaPorId(pelicula_id);
		
		// verificamos si existe un registro de calificacion 
		PeliculaCalificacion calificacionPelicula = buscarCalificacion(pelicula_id, cliente_id);
		
		// verificamos si el cliente ya habia calificado la pelicula
		if(calificacionPelicula == null) {

			Cliente cliente = buscarClientePorId(cliente_id);
			
			calificacionPelicula = new PeliculaCalificacion();
			calificacionPelicula.setPelicula(pelicula);
			calificacionPelicula.setCliente(cliente);
		}
		
		// asignamos la calificacion
		calificacionPelicula.setCalificacion(calificacion);
		em.persist(calificacionPelicula);

		double sumaCalificaciones = 0;
		List<PeliculaCalificacion> calificaciones = consultarCalificacionesBYPelicula(pelicula_id);
		for (int i = 0; i < calificaciones.size(); i++) {
			sumaCalificaciones += calificaciones.get(i).getCalificacion();
		}

		pelicula.setCalificacion( ((double)  sumaCalificaciones) / calificaciones.size());
		em.persist(pelicula);
		em.flush();
	}
	
	/**
	 * Permite consultar las compras que ha hecho un cliente
	 * 
	 * @param cliente_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Venta> consultarVentasByCliente(int cliente_id) {
		Cliente tmpCliente = buscarClientePorId(cliente_id);
		Query query = em.createNamedQuery(Venta.GET_ALl_BY_CLIENT);
		query.setParameter("cliente", tmpCliente);
		return Collections.checkedList(query.getResultList(), Venta.class);
	}
	
	
	/**
	 * Permite obtener una venta por el id
	 *  
	 * @param id
	 * @return
	 */
	public Venta buscarVentaPorId(int id) {
		try {
			Query query = em.createNamedQuery(Venta.FIND_BY_ID);
			query.setParameter("id", id);
			return (Venta) query.getSingleResult();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * Permite Obtener los items de una venta
	 * 
	 * @param venta_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VentaItem> consultarVentasItemsByVenta(int venta_id) {
		Venta tmpVenta = buscarVentaPorId(venta_id);
		if(tmpVenta != null) {
			Query query = em.createNamedQuery(VentaItem.FIND_BY_VENTA);
			query.setParameter("venta", tmpVenta);
			return Collections.checkedList(query.getResultList(), VentaItem.class);
		}
		return null;
	}
	
	/**
	 * Permite obtener los tickets que ha hecho un cliente
	 * 
	 * @param cliente_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> consultarTicketsByCliente(int cliente_id) {
		Cliente tmpCliente = buscarClientePorId(cliente_id);
		Query query = em.createNamedQuery(Ticket.GET_BY_CLIENTE);
		query.setParameter("cliente", tmpCliente);
		return Collections.checkedList(query.getResultList(), Ticket.class);
	}
	
	/**
	 * Permite registrar un ticket por parte de un cliente 
	 * 
	 * @param cliente_id
	 * @param asunto
	 * @param descripcion
	 * @param fecha
	 * @return
	 */
	public Ticket registrarTicket(int cliente_id, String asunto, String descripcion) {
		
		Cliente tmpCliente = buscarClientePorId(cliente_id);
		
		if(tmpCliente == null) {
			return null;
		}
		
		Ticket ticket = new Ticket();
		ticket.setFecha(new Date());
		ticket.setAsunto(asunto);
		ticket.setDescripcion(descripcion);
		ticket.setEstado(false);
		ticket.setCliente(tmpCliente);
		
		em.persist(ticket);
		em.flush();
		
		
		return ticket;
	}

}
