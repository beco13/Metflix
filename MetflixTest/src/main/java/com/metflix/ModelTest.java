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
public class ModelTest {

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
				Persona.class,
				Administrador.class,
				CalificacionesPelicula.class,
				Cliente.class,
				Empleado.class,
				Genero.class,
				Pelicula.class,
				Respuesta.class,
				Tiquet.class,
				Venta.class)
		.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}	
	
	public void generacionTest() {
		
	} 
	
	/**
	 * 
	 * */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","pelicula.json", "genero.json","CalificacionesPelicula.json","venta.json"})
	public void findTest() {
		Pelicula pelicula = entityManager.find(Pelicula.class,1);
		Genero genero = entityManager.find(Genero.class, 1);
		CalificacionesPelicula calificaciones = entityManager.find(CalificacionesPelicula.class, 1);
		Venta venta = entityManager.find(Venta.class, 1);
		Persona persona = entityManager.find(Persona.class, 1);
		Administrador administrador = entityManager.find(Administrador.class, 1);
		//Assert.assertEquals("dolor",pelicula.getDirector());
	}
	
	
	
	/*
	 * Genero
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void findTest() {
		Genero persona = entityManager.find(Genero.class,1);
		Assert.assertEquals("deserunst",persona.getNombre());
	}
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void persistTest(){
		Genero persona = new Genero();
		persona.setNombre("Carlos");
	}*/
/*	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"administrador.json"})
	public void persistTest(){
		Administrador persona = new Administrador();
		persona.setIdentificacion("423456789");
		persona.setNombre("Carlos");
		persona.setApellido("Mendez");
		persona.setCorreo("cmendez1@mail.com");
		persona.setContrasena("12345");
	}
	*/
}
