package com.metflix;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class TiquetTest {

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
	 * */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,
		"test.war").addPackage(Tiquet.class.getPackage())
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
	@UsingDataSet({"tiquet.json"})
	public void findTest(){
	Tiquet tiquet = entityManager.find(Tiquet.class,1);
	//Assert.assertEquals(1,tiquet.getCliente_id());
	}
	
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tiquet.json"})
	public void persistTest(){
		Tiquet tiquet = new Tiquet();
		Cliente cliente= entityManager.find(Cliente.class, 1);
		tiquet.setAsunto("reprehenderit in");
		tiquet.setCliente(cliente);
		tiquet.setDescripcion("Amet occaecat amet nostrud ipsum aute enim non duis Lorem eiusmod voluptate voluptate tempor ad.");
	}
	
}
