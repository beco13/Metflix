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
public class GeneroTest {

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
		.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}	
	
	public void generacionTest() {
		
	} 
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json"})
	public void findTest() {
		Genero genero = entityManager.find(Genero.class,1);
		//Assert.assertEquals("dolor",pelicula.getDirector());
	}
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"genero.json","pelicula.json"})
	public void persistTest(){
		Genero genero = new Genero();
		genero.setNombre("nombre");
		
		Pelicula pelicula = entityManager.find(Pelicula.class, 1);
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		peliculas.add(pelicula);
		
		genero.setPeliculas(peliculas);
		
		entityManager.persist(genero);
	}
}
