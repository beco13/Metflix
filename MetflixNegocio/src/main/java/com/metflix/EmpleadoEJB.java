package com.metflix;

import java.util.Collections;
import java.util.List;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class EmpleadoEJB
 */
@Stateless
@LocalBean
public class EmpleadoEJB implements EmpleadoEJBRemote {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public EmpleadoEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Permite consultar las credenciales de acceso al sistema
	 *
	 * @param correo
	 * @param password
	 * @return
	 * 
	 */
	public Empleado login(String correo, String password) {
		try {
			Query query = em.createNamedQuery(Empleado.LOGIN);
			query.setParameter("correo", correo);
			query.setParameter("contrasena", password);
			return (Empleado) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception

			// System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * permite consultar un administrador por el correo
	 * 
	 * @param correo
	 * @return
	 */
	public Empleado remember(String correo) {
		try {
			Query query = em.createNamedQuery(Empleado.GET_BY_EMAIL);
			query.setParameter("correo", correo);
			return (Empleado) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * permite obtener todos los registos de Clientes
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> consultarClientes() {
		Query query = em.createNamedQuery(Cliente.GET_ALL);
		return Collections.checkedList(query.getResultList(), Ticket.class);
	}

	/**
	 * Permite buscar un empleado por la cedula
	 *
	 * @param cedula
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> buscarClientePorCedula(String cedula) {
		Query query = em.createNamedQuery(Cliente.FIND_BY_CEDULA);
		query.setParameter("identificacion", "%" + cedula + "%");
		return Collections.checkedList(query.getResultList(), Ticket.class);
	}

	/**
	 * Permite buscar un empleado por el id de registro
	 *
	 * @param cedula
	 * @return
	 */
	public Cliente buscarClientePorId(int id) {
		Query query = em.createNamedQuery(Cliente.FIND_BY_ID);
		query.setParameter("id", id);
		return (Cliente) query.getSingleResult();
	}

	/**
	 * Permite eliminar el registro de un cliente
	 * 
	 * @param id
	 */
	public void eliminarCliente(Integer id) {
		Cliente cliente = buscarClientePorId(id);
		em.remove(cliente);
	}

	/**
	 * permite consultar los tiquets
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> consultarTickets() {
		Query query = em.createNamedQuery(Ticket.GET_ALL);
		return Collections.checkedList(query.getResultList(), Ticket.class);
	}

	/**
	 * Permite buscar un empleado por le id de registro
	 *
	 * @param emplado_id
	 * @return
	 */
	public Empleado buscarEmpleadoPorId(int emplado_id) {
		Query query = em.createNamedQuery(Empleado.FIND_BY_ID);
		query.setParameter("id", emplado_id);
		return (Empleado) query.getSingleResult();
	}

	/**
	 * Permite buscar un ticket por el id de registro
	 *
	 * @param ticket_id
	 * @return
	 */
	public Ticket buscarTicketPorId(int ticket_id) {
		try {
			Query query = em.createNamedQuery(Ticket.GET_BY_ID);
			query.setParameter("id", ticket_id);
			return (Ticket) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * Permite cargar la respuesta de un ticket
	 * 
	 * @param ticket_id
	 * @return
	 */
	public TicketRespuesta buscarRespuestaFromTicket(int ticket_id) {

		try {
			Ticket tmpTicket = buscarTicketPorId(ticket_id);
			Query query = em.createNamedQuery(TicketRespuesta.GET_BY_TICKET);
			query.setParameter("ticket", tmpTicket);
			return (TicketRespuesta) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * permite registrar una respuesta a un ticket generado por un cliente
	 * 
	 * @param empleado_id
	 * @param ticket_id
	 * @param fecha
	 * @param descripcion
	 * @return
	 */
	public TicketRespuesta registrarRespuesta(int empleado_id, int ticket_id, String respuestaTexto) {

		Empleado empleado = buscarEmpleadoPorId(empleado_id);
		Ticket ticket = buscarTicketPorId(ticket_id);

		TicketRespuesta respuesta = new TicketRespuesta();
		respuesta.setFecha(new Date());
		respuesta.setDescripcion(respuestaTexto);
		respuesta.setEmpleado(empleado);
		respuesta.setTicket(ticket);
		em.persist(respuesta);

		ticket.setEstado(true);
		em.persist(ticket);

		em.flush();

		return respuesta;
	}

}
