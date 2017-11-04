package com.metflix;

import static org.junit.Assert.fail;

import java.util.ArrayList;
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
public class VentaTest {

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
		"test.war").addPackage(Venta.class.getPackage())
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
	@UsingDataSet({"venta.json"})
	public void findTest(){
	Venta venta = entityManager.find(Venta.class,1);
	//Assert.assertEquals(1,venta.getCliente_id());
	}
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"venta.json"})
	public void persistTest(){
		Venta venta = new Venta();
		Cliente cliente= entityManager.find(Cliente.class, 1);
		venta.setCliente(cliente);
		
		Pelicula pelicula = entityManager.find(Pelicula.class,1);
		List<Pelicula> peliculas = new ArrayList<Pelicula> ();
		peliculas.add(pelicula);
		venta.setPeliculas(peliculas);
		
		entityManager.persist(venta);
		
		/*Venta registrado = entityManager.find
				(Venta.class,venta.getCliente_id());
				Assert.assertEquals(venta , registrado );*/
	}
	
}
