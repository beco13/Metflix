package com.metflix;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;



import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;


@RunWith(Arquillian.class)
public class AdministradorEJBTest {

	
	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(EJB.class)
						.addPackage(Administrador.class.getPackage())
						.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	@Test
	public void registrarPeliculaTest() {
		
	}
}
