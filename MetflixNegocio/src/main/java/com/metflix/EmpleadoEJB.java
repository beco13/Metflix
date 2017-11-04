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
     * Permite buscar un empleado por la cedula
     *
     * @param cedula
     * @return
     */
    public Cliente buscarClientePorCedula(String cedula) {
		Query query = em.createNamedQuery(Cliente.FIND_BY_CEDULA);
		query.setParameter("identificacion", cedula);
		return (Cliente) query.getSingleResult();
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
     * permite consultar los tiquets que hay registradas por cliente
     */
    @SuppressWarnings("unchecked")
    public List<Tiquet> consultarTicketsFromCliente(int cliente_id) {
    		Query query = em.createNamedQuery(Tiquet.GET_BY_CLIENTE);
    		query.setParameter("cliente", cliente_id);
    		return Collections.checkedList(query.getResultList(), Tiquet.class) ;
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
    public Tiquet buscarTicketPorId(int ticket_id) {
		Query query = em.createNamedQuery(Tiquet.GET_BY_ID);
		query.setParameter("id", ticket_id);
		return (Tiquet) query.getSingleResult();
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
    public Respuesta registrarRespuesta (int empleado_id, int ticket_id, Date fecha, String descripcion) {
    		
    		Empleado empleado = buscarEmpleadoPorId(empleado_id);
    		Tiquet ticket = buscarTicketPorId(ticket_id);
    		
    		Respuesta respuesta = new Respuesta();
    		respuesta.setFecha(fecha);
    		respuesta.setDescripcion(descripcion);
    		respuesta.setEmpleado(empleado);
    		respuesta.setTiquet(ticket);
    		
    		em.persist(respuesta);
    		em.flush();
    		
    		return respuesta;
    }
    

}
