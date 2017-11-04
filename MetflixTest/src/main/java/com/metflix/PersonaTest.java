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
public class PersonaTest {
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
	
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void findTest() {
		Persona persona = entityManager.find(Persona.class,1);
		//Assert.assertEquals("1austinfisher@flotonic.com",persona.getCorreo());
	}

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void persistTest(){
		Persona persona = new Persona();
		persona.setIdentificacion("423456789");
		persona.setNombre("Carlos");
		persona.setApellido("Mendez");
		persona.setCorreo("cmendez@mail.com");
		persona.setContrasena("12345");
	}

	
}
