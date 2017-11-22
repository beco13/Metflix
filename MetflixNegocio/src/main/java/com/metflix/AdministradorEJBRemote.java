package com.metflix;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdministradorEJBRemote {
	
	String JNDI = "java:global/MetflixEAR/MetflixNegocio/AdministradorEJB!com.metflix.AdministradorEJBRemote";
	
	public Administrador login(String correo, String password);
	

	public Genero buscarGeneroPorId(Integer id);

	public Genero registrarGenero(String nuevoGenero);
	
	public Genero actualizarGenero(Integer id, String nuevoNombre);
	
	public void eliminarGenero(Integer id);
	
	public List<Genero> consultarGeneros();
	
	public Pelicula registrarPelicula(String titulo, Double calificacion, String clasificacion, 
			String director, Date fechaEstreno, int genero_id , String idioma, String pais, 
			String reparto, String sinopsis);
	
	public List<Pelicula> consultarPeliculas();
	
	public Pelicula buscarPeliculaPorId(int pelicula_id);
	
	public List<Pelicula> buscarPelicula(String filtro) ;
	
	public void actualizarPelicula(int pelicula_id, String titulo, Double calificacion,String clasificacion, 
			String director, Date fechaEstreno, int genero_id , String idioma, String pais, 
			String reparto, String sinopsis);
	
	public void eliminarPelicula(Integer id);
	
	public Empleado registrarEmpleado(String identificacion, String nombre, String apellido, String correo, String contrasena);
	
	public List<Empleado> consultarEmpleados();
	
	public Empleado buscarEmpleadoPorId(int emplado_id);
	
	public List<Empleado> buscarEmpleadoPorCedula(String cedula) ;
	
	public void actualizarEmpleado(int id, String identificacion, String nombre, String apellido, String correo, String contrasena);
	
	public void eliminarEmpleado(Integer id);
	
	public int findPeliculasVendidasIntervalo(int clienteId, Date fechaInicio, Date fechaFin );
}
