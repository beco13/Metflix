package com.metflix;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EmpleadosFormularioController implements Initializable{

	public static AdministradorDelegado delegadoEjb = AdministradorDelegado.getInstancia(); 
	
	//Empleado obtenido
	private Empleado datoEmpleado = null;
	
	/**
	 * Estado de formulario al momento de guardar un empleado
	 * true = para guardar empleado nuevo
	 * false para editar empleado existente
	 * */
	private Boolean estado = true;
	
    @FXML
    private JFXTextField txt_identificacion_empleado;

    @FXML
    private JFXTextField txt_nombre_empleado;

    @FXML
    private JFXPasswordField  txt_contrasenare_empleado;

    @FXML
    private JFXTextField txt_apellido_empleado;

    @FXML
    private JFXTextField txt_correo_empleado;

    @FXML
    private JFXPasswordField  txt_contrasena_empleado;

    @FXML
    private JFXButton btn_guardar_empleado;

    @FXML
    private JFXButton btn_editar_empleado;

    @FXML
    private JFXButton btn_eliminar_empleado;
    
    /**
     * Guarda un empleado cuando todos los datos son correctos
     * @return null
     * */
    @FXML
    void guardarEmpleado(ActionEvent event) {
    	
    	if(txt_contrasena_empleado.getText().equals(txt_contrasenare_empleado.getText())) {
    		if(estado) {
    			Empleado empleado = delegadoEjb
            			.registrarEmpleado(
            					txt_identificacion_empleado.getText(),
            					txt_nombre_empleado.getText(), 
            					txt_apellido_empleado.getText(),
            					txt_correo_empleado.getText(),
            					txt_contrasena_empleado.getText());
    			System.out.println("Empleado Guardado: " + empleado.toString());
        	}else{
        		 delegadoEjb
        				.actualizarEmpleado(
        						datoEmpleado.getId(), 
		        				txt_identificacion_empleado.getText(),
		    					txt_nombre_empleado.getText(), 
		    					txt_apellido_empleado.getText(),
		    					txt_correo_empleado.getText(),
		    					txt_contrasena_empleado.getText());
        		 System.out.println("Empleado Acualizado: " + datoEmpleado.toString());
        	}
    		
    	}else if(Utilidades
    				.esNuloVacio(txt_identificacion_empleado.getText(),
    					txt_nombre_empleado.getText(), 
    					txt_apellido_empleado.getText(),
    					txt_correo_empleado.getText(),
    					txt_contrasena_empleado.getText())) {
    		
    		JOptionPane.showMessageDialog(null, "Debe diligenciar todos los campos");
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "La contraseña no coincide");
    	}
    }

    /**
     * Modifica un empleado existente
     * */
    @FXML
    void editarEmpleado(ActionEvent event) {
    	camposDisable(false);
    	estado  = false;
    }
    
    /**
     * Elimina a un empleado por el ID
     * @return null 
     * */
    @FXML
    void eliminarEmpleado(ActionEvent event) {
    	if(datoEmpleado != null) {
    		if (JOptionPane.showConfirmDialog(null,
    				"¿Seguro que desea eliminar a " + datoEmpleado.getNombre() + " " + datoEmpleado.getApellido() + "?",
    				"WARNING",
    		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    			delegadoEjb.eliminarEmpleado(datoEmpleado.getId());
    		}
    	}
    	
    }
	
    
    /**
     * Inicializa el estado de los campos dependiendo de si el empleado existe 
     * */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(datoEmpleado != null) {
			camposDisable(true);
		}else {
			btn_editar_empleado.setDisable(true);
			btn_eliminar_empleado.setDisable(true);
			camposDisable(false);	
		}
	}
	

    /**
     * LLenar el empleado en los campos disponibles
     * */
    public void llenarEmpleado(Empleado empleado) {
    	datoEmpleado = empleado;
    	txt_identificacion_empleado.setText(datoEmpleado.getIdentificacion());
    	txt_nombre_empleado.setText(datoEmpleado.getNombre());
    	txt_apellido_empleado.setText(datoEmpleado.getApellido());
    	txt_correo_empleado.setText(datoEmpleado.getCorreo());
    	txt_contrasena_empleado.setText(datoEmpleado.getContrasena());
    	txt_contrasenare_empleado.setText(datoEmpleado.getContrasena());
    }
	
	/**
	 * Inhabilita o habilita los campos de texto para editarlos
	 * @return null
	 * */
	public void camposDisable(boolean flag) {
		txt_identificacion_empleado.setDisable(flag);
		txt_nombre_empleado.setDisable(flag);
		txt_apellido_empleado.setDisable(flag);
		txt_correo_empleado.setDisable(flag);
		txt_contrasena_empleado.setDisable(flag);
		txt_contrasenare_empleado.setDisable(flag);
	}

}
