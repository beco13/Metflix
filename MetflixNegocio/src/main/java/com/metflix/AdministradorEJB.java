package com.metflix;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AdministradorEJB
 */
@Stateless
@LocalBean
public class AdministradorEJB implements AdministradorEJBRemote {

	@PersistenceContext
	private EntityManager em;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdministradorEJB() {
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
	public Administrador login(String correo, String password) {

		try {
			Query query = em.createNamedQuery(Administrador.LOGIN_ADMIN);
			query.setParameter("correo", correo);
			query.setParameter("contrasena", password);
			return (Administrador) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception

			// System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * permite buscar un registro de Genero por el id
	 * 
	 * @param id
	 * @return
	 */
	public Genero buscarGeneroPorId(Integer id) {
		Query query = em.createNamedQuery(Genero.FIND_BY_ID);
		query.setParameter("id", id);
		return (Genero) query.getSingleResult();
	}

	/**
	 * permite registrar un genero
	 * 
	 * @param nuevoGenero
	 *            string
	 * @return
	 */
	public Genero registrarGenero(String nuevoGenero) {

		Genero genero = new Genero();
		genero.setNombre(nuevoGenero);

		// guardamos el registro
		em.persist(genero);
		em.flush();

		return genero;
	}

	/**
	 * permite actualizar el nombre de un genero
	 * 
	 * @param id
	 *            int
	 * @param nuevoNombre
	 *            string
	 * @return
	 */
	public Genero actualizarGenero(Integer id, String nuevoNombre) {

		Genero tmpGenero = buscarGeneroPorId(id);
		tmpGenero.setNombre(nuevoNombre);

		em.persist(tmpGenero);
		em.flush();

		return tmpGenero;
	}

	/**
	 * permite Eliminar un registro de genero
	 * 
	 * @param id
	 *            integer
	 */
	public void eliminarGenero(Integer id) {

		Genero tmpGenero = buscarGeneroPorId(id);

		em.getTransaction().begin();
		em.remove(tmpGenero);
		em.getTransaction().commit();
	}

	/**
	 * permite consultar todas los generos registrados
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Genero> consultarGeneros() {
		Query query = em.createNamedQuery(Genero.GET_ALL);
		return Collections.checkedList(query.getResultList(), Genero.class);
	}

	/**
	 * permite hacer el registro de una Pelicula
	 * 
	 * @param titulo
	 * @param calificacion
	 * @param clasificacion
	 * @param director
	 * @param fechaEstreno
	 * @param genero_id
	 * @param idioma
	 * @param pais
	 * @param reparto
	 * @param sinopsis
	 */
	public Pelicula registrarPelicula(String titulo, Double calificacion, String clasificacion, String director,
			Date fechaEstreno, int genero_id, String idioma, String pais, String reparto, String sinopsis) {

		Genero tmpGenero = buscarGeneroPorId(genero_id);

		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		pelicula.setCalificacion(calificacion);
		pelicula.setClasificacion(clasificacion);
		pelicula.setDirector(director);
		pelicula.setFechaEstreno(new Date());
		pelicula.setGenero(tmpGenero);
		pelicula.setIdioma(idioma);
		pelicula.setPais(pais);
		pelicula.setReparto(reparto);
		pelicula.setSinopsis(sinopsis);

		// guardamos el registro
		em.persist(pelicula);
		em.flush();

		return pelicula;
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
	 * permite consultar las peliculas que hay registradas
	 */
	@SuppressWarnings("unchecked")
	public List<Pelicula> consultarPeliculasByCalificacion(Double calificacion) {
		Query query = em.createNamedQuery(Pelicula.GET_BY_CALIFICACION);
		query.setParameter("calificacion", calificacion);
		return Collections.checkedList(query.getResultList(), Pelicula.class);
	}

	/**
	 * metodo que permite buscar una pelicula por el identificador de registro
	 * 
	 * @param pelicula_id
	 * @return
	 */
	public Pelicula buscarPeliculaPorId(int pelicula_id) {
		Query query = em.createNamedQuery(Pelicula.FIND_BY_ID);
		query.setParameter("id", pelicula_id);
		return (Pelicula) query.getSingleResult();
	}

	/**
	 * permite actualizar la informacion de una pelicula
	 * 
	 * @param pelicula_id
	 * @param titulo
	 * @param calificacion
	 * @param clasificacion
	 * @param director
	 * @param fechaEstreno
	 * @param genero_id
	 * @param idioma
	 * @param pais
	 * @param reparto
	 * @param sinopsis
	 */
	public void actualizarPelicula(int pelicula_id, String titulo, Double calificacion, String clasificacion,
			String director, Date fechaEstreno, int genero_id, String idioma, String pais, String reparto,
			String sinopsis) {

		Genero tmpGenero = buscarGeneroPorId(genero_id);

		Pelicula pelicula = buscarPeliculaPorId(pelicula_id);
		pelicula.setTitulo(titulo);
		pelicula.setCalificacion(calificacion);
		pelicula.setClasificacion(clasificacion);
		pelicula.setDirector(director);
		pelicula.setFechaEstreno(new Date());
		pelicula.setGenero(tmpGenero);
		pelicula.setIdioma(idioma);
		pelicula.setPais(pais);
		pelicula.setReparto(reparto);
		pelicula.setSinopsis(sinopsis);

		// guardamos el registro
		em.persist(pelicula);
		em.flush();
	}

	/**
	 * permite Eliminar un registro de pelicula
	 * 
	 * @param id
	 *            integer
	 */
	public void eliminarPelicula(Integer id) {

		Pelicula tmpPelicula = buscarPeliculaPorId(id);

		em.getTransaction().begin();
		em.remove(tmpPelicula);
		em.getTransaction().commit();
	}

	/**
	 * permite registrar un empleado
	 * 
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @param contrasena
	 */
	public Empleado registrarEmpleado(String identificacion, String nombre, String apellido, String correo,
			String contrasena) {

		Empleado empleado = new Empleado();
		empleado.setIdentificacion(identificacion);
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setCorreo(correo);
		empleado.setContrasena(contrasena);

		// guardamos el registro
		em.persist(empleado);
		em.flush();

		return empleado;
	}

	public boolean registrarEmpleado2(String identificacion, String nombre, String apellido, String email,
			String contrasena) {

		if (entityManager.find(Empleado.class, identificacion) != null) {

			System.out.println("El usuario ya est� registrado");

		} else {
			Empleado empleado = new Empleado();
			empleado.setIdentificacion(identificacion);
			empleado.setNombre(nombre);
			empleado.setApellido(apellido);
			empleado.setContrasena(contrasena);
			empleado.setCorreo(email);

			entityManager.persist(empleado);
			return true;
		}
		return false;

	}

	/**
	 * permite consultar las peliculas que hay registradas
	 */
	@SuppressWarnings("unchecked")
	public List<Empleado> consultarEmpleados() {
		Query query = em.createNamedQuery(Empleado.GET_ALL);
		return Collections.checkedList(query.getResultList(), Empleado.class);
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
	 * Permite buscar un empleado por le id de registro
	 *
	 * @param cedula
	 * @return
	 */
	public Empleado buscarEmpleadoPorCedula(String cedula) {
		Query query = em.createNamedQuery(Empleado.FIND_BY_CEDULA);
		query.setParameter("identificacion", cedula);
		return (Empleado) query.getSingleResult();
	}

	/**
	 * permite actualizar la informacion de un empleado
	 * 
	 * @param id
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @param contrasena
	 */
	public void actualizarEmpleado(int id, String identificacion, String nombre, String apellido, String correo,
			String contrasena) {

		Empleado empleado = buscarEmpleadoPorId(id);
		empleado.setIdentificacion(identificacion);
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setCorreo(correo);
		empleado.setContrasena(contrasena);

		// guardamos el registro
		em.persist(empleado);
		em.flush();
	}

	/**
	 * permite eliminar el registro de un empleado
	 * 
	 * @param id
	 */
	public void eliminarEmpleado(Integer id) {

		Empleado empleado = buscarEmpleadoPorId(id);

		em.getTransaction().begin();
		em.remove(empleado);
		em.getTransaction().commit();
	}

	/**
	 * Permite consultar el número de peliculas compradas por un cliente en un
	 * intervalo de tiempo dado
	 * 
	 * @param clienteId
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public int findPeliculasVendidasIntervalo(int clienteId, Date fechaInicio, Date fechaFin) {
		Query query = em.createQuery(
				"select count(*) as total_peliculas_compradas from pelicula_venta PV left join venta V on PV.ventas_ID = v.ID where V.CLIENTE_ID = :cliente_id and V.fecha between :fechaInicio AND :fechaFin ");
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("cliente_id", clienteId);
		return (Integer) query.getSingleResult();
	}

	/**
	 * 
	 * Permite buscar un administrador por el correo y luego le envia la contraseña
	 * al email que el administrador tenga registrado
	 * 
	 * 
	 * @param correo
	 * @return
	 */
	public int recuperarPassword(String correo) {

		Administrador administrador = null;

		try {
			Query query = em.createNamedQuery(Administrador.REQUEST_PASS);
			query.setParameter("correo", correo);
			administrador = (Administrador) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}

		Correo tmpCorreo = new Correo();
		tmpCorreo.setEmailDestinatario(correo);
		tmpCorreo.setMensaje("Has solicitado recuperar la contraseña para lo cual esta es tu contraseña: "
				+ administrador.getContrasena());

		return tmpCorreo.enviar() ? 1 : 0;
	}

}
