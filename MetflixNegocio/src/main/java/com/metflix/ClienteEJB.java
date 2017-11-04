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
     * permite registrar un cliente
     */
    public Cliente registrarCliente(String apellido, String contrasena, String correo, 
    									String identificacion, String nombre) {
    	
    	
	    	Cliente cliente = new Cliente();
	    	cliente.setApellido(apellido);
	    	cliente.setContrasena(contrasena);
	    	cliente.setCorreo(correo);
	    	cliente.setIdentificacion(identificacion);
	    	cliente.setNombre(nombre);
	    
	        		    	
	    	// guardamos el registro
	    	em.persist(cliente);
	    	em.flush();
	    	
	    	return   cliente;
    }

    
    /**
     * permite buscar un cliente 
     * 
     * @param id
     * @return
     */
    public Cliente buscarCliente(Integer id) {
		Query query = em.createNamedQuery(Cliente.FIND_BY_ID);
		query.setParameter("id", id);
		return (Cliente) query.getSingleResult();
    }
        
    
    /**
     * permite Eliminar un cliente
     * 
     * @param id integer
     */    
    
    public void eliminarCliente(Integer id) {
		
		Cliente tmpCliente = buscarCliente(id);
		
		em.getTransaction().begin();
		em.remove(tmpCliente);
		em.getTransaction().commit();
    }
    
    /**
     * permite actualizar un cliente
     * 
     * @param id int
     * 
     * @return
     */
    public Cliente actualizarCliente(Integer id, String apellido, String contrasena, 
    									String correo, String identificacion, String nombre) {

    		Cliente tmpCliente = buscarCliente(id);
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
    		return Collections.checkedList(query.getResultList(), Pelicula.class) ;
    }
    
    /**
     * permite buscar una pelicula 
     * 
     * @param id
     * @return
     */
    public Pelicula buscarPelicula(Integer id) {
		Query query = em.createNamedQuery(Pelicula.FIND_BY_ID);
		query.setParameter("id", id);
		return (Pelicula) query.getSingleResult();
    }
    
    
    /**
     * Permite registrar una compra con sus respectivas peliculas
     * @param fecha
     * @param cliente_id
     * @param peliculas_id
     * @return
     */
    public Venta registrarCompra(Date fecha, Integer cliente_id, ArrayList<Integer>peliculas_id )
    {
    	Cliente cliente = buscarCliente(cliente_id);
    
    	List<Pelicula>peliculas = new ArrayList<Pelicula>();
    	
    	for(int i=0; i< peliculas_id.size(); i++ )
    	{
    		Pelicula pelicula = buscarPelicula(peliculas_id.get(i));
    		peliculas.add(pelicula);
    	}
    	
    	  	
    	Venta venta = new Venta();
    
    	venta.setPeliculas(peliculas);    	
    	venta.setCliente(cliente);
    	venta.setFecha(fecha);
    	
    	em.persist(venta);
    	em.flush();
    	
    	return venta;
    }
    
    @SuppressWarnings("unchecked")
    public List<CalificacionesPelicula> consultarCalificacionesBYPelicula(Integer pelicula_id) {
    		Query query = em.createNamedQuery(CalificacionesPelicula.GET_ALL_BY_PELICULA);
    		query.setParameter("pelicula_id", pelicula_id);
    		return Collections.checkedList(query.getResultList(), CalificacionesPelicula.class) ;
    }
    
    public Double calificarPeliculas(Integer cliente_id, Integer pelicula_id, Integer calificacion)
    {
	    	Cliente cliente = buscarCliente(cliente_id);
	    	Pelicula pelicula = buscarPelicula(pelicula_id);
	    	
	    	CalificacionesPelicula calificacionPelicula = new CalificacionesPelicula();
	    	
	    	calificacionPelicula.setPelicula(pelicula);
	    	calificacionPelicula.setCliente(cliente);
	    	calificacionPelicula.setCalificacion(calificacion);
	    	    	
	    	em.persist(calificacionPelicula);
	    	
	    	Integer sumaCalificaciones=0;
	    	List<CalificacionesPelicula> calificaciones = consultarCalificacionesBYPelicula(pelicula_id);
	    	
	    	for (int i = 0; i < calificaciones.size(); i++) 
	    	{
	    		sumaCalificaciones = calificaciones.get(i).getCalificacion();
			}
	    	
	    	//pelicula.setCalificacion(Double.p sumaCalificaciones / calificaciones.size());
	    	em.flush();
	    	
	    	
	    	return 0.0;
    	
    }
    
    
    
    
    
    
    
    
}
