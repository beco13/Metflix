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
public class RespuestaTest {

	/**
	 * Se encarga de manejar las transacciones con las entidades del proyectoPerisistencia
	 **/	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/**
	 * Genera el archivo ShirnkWrap con base a la cual se realizan las priebas con Arquillian
	 * @return archivo ShirnkWrap
	 * */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,
		"test.war").addPackage(Respuesta.class.getPackage())
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
	@UsingDataSet({"respuesta.json"})
	public void findTest(){
	Respuesta respuesta = entityManager.find(Respuesta.class,1);
	//Assert.assertEquals(1,respuesta.getTiquet_id());
	}
	
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"respuesta.json"})
	public void persistTest(){
		Respuesta respuesta = new Respuesta();
		respuesta.setDescripcion("DEscripcion");
		Tiquet tiquet = entityManager.find(Tiquet.class, 1);
		respuesta.setTiquet(tiquet);
		Empleado empleado= entityManager.find(Empleado.class, 1);
		respuesta.setEmpleado(empleado);
		respuesta.setDescripcion("Consequat ipsum labore laboris mollit id Lorem adipisicing consectetur tempor ullamco aliqua.");
		
		try {
			SimpleDateFormat formato_fecha= new SimpleDateFormat ("yyyy-M-dd");
			String dateInString="2014-01-04";
			Date fecha;
			fecha = formato_fecha.parse(dateInString);
			respuesta.setFecha(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
