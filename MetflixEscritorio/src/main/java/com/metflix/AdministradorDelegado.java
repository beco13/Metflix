package com.metflix;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.metflix.AdministradorEJBRemote;

public class AdministradorDelegado{
	
	private static final AdministradorDelegado instancia = new AdministradorDelegado(); 
	
	private AdministradorEJBRemote administradorEJB;
	
	
	private AdministradorDelegado() {
		try {
			administradorEJB = (AdministradorEJBRemote) new InitialContext().lookup( AdministradorEJBRemote.JNDI);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static AdministradorDelegado getInstancia() {
		return instancia;
	}

	
	public Administrador login(String correo, String password) {
		// TODO Auto-generated method stub
		return administradorEJB.login(correo, password);
	}

	
	public Genero buscarGeneroPorId(Integer id) {
		// TODO Auto-generated method stub
		return administradorEJB.buscarGeneroPorId(id);
	}

	public Genero registrarGenero(String nuevoGenero) {
		// TODO Auto-generated method stub
		return administradorEJB.registrarGenero(nuevoGenero);
	}

	
	public Genero actualizarGenero(Integer id, String nuevoNombre) {
		// TODO Auto-generated method stub
		return administradorEJB.actualizarGenero(id, nuevoNombre);
	}

	
	public void eliminarGenero(Integer id) {
		// TODO Auto-generated method stub
		administradorEJB.eliminarGenero(id);
	}

	
	public List<Genero> consultarGeneros() {
		// TODO Auto-generated method stub
		return administradorEJB.consultarGeneros();
	}

	
	public Pelicula registrarPelicula(String titulo, Double calificacion, String clasificacion, String director,
			Date fechaEstreno, int genero_id, String idioma, String pais, String reparto, String sinopsis) {
		// TODO Auto-generated method stub
		return administradorEJB.registrarPelicula(titulo, calificacion, clasificacion, director, fechaEstreno, genero_id, idioma, pais, reparto, sinopsis);
	}

	
	public List<Pelicula> consultarPeliculas() {
		// TODO Auto-generated method stub
		
		return administradorEJB.consultarPeliculas();
	}

	
	public List<Pelicula> consultarPeliculasByCalificacion(Double calificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Pelicula buscarPeliculaPorId(int pelicula_id) {
		// TODO Auto-generated method stub
		
		return administradorEJB.buscarPeliculaPorId(pelicula_id);
	}

	
	public void actualizarPelicula(int pelicula_id, String titulo, Double calificacion, String clasificacion,
			String director, Date fechaEstreno, int genero_id, String idioma, String pais, String reparto,
			String sinopsis) {
		// TODO Auto-generated method stub
		administradorEJB.actualizarPelicula(pelicula_id, titulo, calificacion, clasificacion, director, fechaEstreno, genero_id, idioma, pais, reparto, sinopsis);
	}

	
	public void eliminarPelicula(Integer id) {
		// TODO Auto-generated method stub
		administradorEJB.eliminarPelicula(id);
	}

	
	public Empleado registrarEmpleado(String identificacion, String nombre, String apellido, String correo,
			String contrasena) {
		// TODO Auto-generated method stub
		return administradorEJB.registrarEmpleado(identificacion, nombre, apellido, correo, contrasena);
	}

	
	public List<Empleado> consultarEmpleados() {
		// TODO Auto-generated method stub
		return administradorEJB.consultarEmpleados();
	}

	
	public Empleado buscarEmpleadoPorId(int emplado_id) {
		// TODO Auto-generated method stub
		return administradorEJB.buscarEmpleadoPorId(emplado_id);
	}

	
	public Empleado buscarEmpleadoPorCedula(String cedula) {
		// TODO Auto-generated method stub
		return administradorEJB.buscarEmpleadoPorCedula(cedula);
	}

	
	public void actualizarEmpleado(int id, String identificacion, String nombre, String apellido, String correo,
			String contrasena) {
		// TODO Auto-generated method stub
		administradorEJB.actualizarEmpleado(id, identificacion, nombre, apellido, correo, contrasena);
	}

	
	public void eliminarEmpleado(Integer id) {
		// TODO Auto-generated method stub
		administradorEJB.eliminarEmpleado(id);
	}

	
	public int findPeliculasVendidasIntervalo(int clienteId, Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub
		return administradorEJB.findPeliculasVendidasIntervalo(clienteId, fechaInicio, fechaFin);
	}


}
