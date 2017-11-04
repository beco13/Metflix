package com.metflix;

import static org.junit.Assert.fail;

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
public class CalificacionesTest {

	/**
	 * Se encarga de manejar las transacciones con las entidades del proyectoPerisistencia
	 **/	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void test() {
		
	}
	
	/**
	 * Genera el archivo ShirnkWrap con base a la cual se realizan las priebas con Arquillian
	 * @return archivo ShirnkWrap
	 *
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,
		"test.war").addPackage(CalificacionesPelicula.class.getPackage())
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
	@UsingDataSet({"CalificacionesPelicula.json"})
	public void findTest(){
	CalificacionesPelicula CalificacionesPelicula = entityManager.find(CalificacionesPelicula.class,1);
	System.out.println(CalificacionesPelicula.toString());
	//Assert.assertEquals(1,CalificacionesPelicula.getPelicula_id());
	}
	
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"CalificacionesPelicula.json"})
	public void persistTest(){
		Pelicula pelicula= entityManager.find(Pelicula.class, 1);
		CalificacionesPelicula CalificacionesPelicula = new CalificacionesPelicula();
		CalificacionesPelicula.setId(1);
		CalificacionesPelicula.setCalificacion(1);
		CalificacionesPelicula.setPelicula(pelicula);		
	}
		
}
