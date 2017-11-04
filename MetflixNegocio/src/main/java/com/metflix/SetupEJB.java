package com.metflix;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {
	
	@PersistenceContext
	private EntityManager entityManager;


    /**
     * Default constructor. 
     */
    public SetupEJB() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    @PostConstruct
    public void install() {
    	
    		long totalAdmins = entityManager.createNamedQuery(Administrador.COUNT_ADMIN, Long.class).getSingleResult();
    		
    		System.out.println("valor admins: "+totalAdmins);
    		
    		if(totalAdmins  == 0) {
    			Administrador tmpAdmin = new Administrador();
    			tmpAdmin.setNombre("Alejandro");
    			tmpAdmin.setApellido("Rodriguez");
    			tmpAdmin.setCorreo("rodriguezalejo7@gmail.com");
    			tmpAdmin.setIdentificacion("1094938");
    			tmpAdmin.setContrasena("1234qwer");
    			entityManager.persist(tmpAdmin);
    			
    			
    			Administrador adminLore = new Administrador();
    			adminLore.setNombre("Lorena");
    			adminLore.setApellido("Alvarez");
    			adminLore.setCorreo("clalvarezg@uqvirtual.edu.co");
    			adminLore.setIdentificacion("1094885");
    			adminLore.setContrasena("1234qwer");
    			entityManager.persist(adminLore);
    		}
    		
    }
    
}
