package com.metflix;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PeliculaTest {

	/**
	 * Se encarga de manejar las transacciones con las entidades del proyectoPerisistencia
	 **/	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Genera el archivo ShirnkWrap con base a la cual se realizan las priebas con Arquillian
	 * @return archivo ShirnkWrap
	 * */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,
		"test.war").addPackage(Persona.class.getPackage())
		.addClasses(
				CalificacionesPelicula.class,
				Genero.class,
				Pelicula.class,
				Venta.class)
		.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}	
	
	public void generacionTest() {
		
	} 
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","pelicula.json", "genero.json","CalificacionesPelicula.json","venta.json"})
	public void findTest() {
		Pelicula pelicula = entityManager.find(Pelicula.class,1);
		Assert.assertEquals("dolor",pelicula.getDirector());
	}
	

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"pelicula.json","CalificacionesPelicula.json"})
	public void persistTest(){
		Pelicula pelicula = new Pelicula();
		pelicula.setClasificacion("Clasificacion");
		pelicula.setDirector("Director");
		pelicula.setFechaEstreno(new Date());
		
		Genero genero = entityManager.find(Genero.class, 1);
		List<Genero> generos =  new  ArrayList<Genero>();
		generos.add(genero);
		
		pelicula.setGenero(genero);
		
		CalificacionesPelicula calificacion = entityManager.find(CalificacionesPelicula.class, 1);		
		List<CalificacionesPelicula> calificaciones = new ArrayList<CalificacionesPelicula>();
		calificaciones.add(calificacion);
		
		pelicula.setCalificaciones(calificaciones);
		
		pelicula.setIdioma("Idioma");
		pelicula.setPais("Pais");
		pelicula.setReparto("Reparto");
		pelicula.setSinopsis("sinopsis");
		pelicula.setTitulo("Titulo");
		
		Venta venta = entityManager.find(Venta.class, 1);
		List<Venta> ventas =  new ArrayList<Venta>(); 
		ventas.add(venta);
		
		pelicula.setVentas(ventas);
		
		entityManager.persist(pelicula);
	}
	
}
