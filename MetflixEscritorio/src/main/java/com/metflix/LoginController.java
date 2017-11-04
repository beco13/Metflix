package com.metflix;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	public static AdministradorDelegado delegadoEjb = AdministradorDelegado.getInstancia(); 
	
	@FXML
    private JFXTextField txt_contrasena;

    @FXML
    private JFXTextField txt_usuario;

    @FXML
    private JFXButton btn_login;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
    	
    		Administrador administrador = delegadoEjb.login(txt_usuario.getText(), txt_contrasena.getText());
    		
    		if(administrador == null) {
    			JOptionPane.showMessageDialog(null,"Datos incorrectos");
    		}else {
    			//System.out.println(administrador.toString());
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("EscritorioAdministrador.fxml")); 
    	
    			Parent root = (Parent) loader.load();
    			Stage stage =  new Stage();
    			
    			//EscritorioAdministradorController escritorioAdminController = loader.getController();
    			stage.setScene(new Scene(root));
    			
    			stage.show();
    			Stage stage1 = (Stage) btn_login.getScene().getWindow();
    			stage1.close();
    			//escritorioAdminController.getData(data,username, password);
    			
    		}	
    }
	
    @FXML
    void olvidoContrasena(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RestablecerContrasena.fxml")); 
    	
		Parent root = (Parent) loader.load();
		Stage stage =  new Stage();
		
		stage.setScene(new Scene(root));
		
		stage.show();
		Stage stage1 = (Stage) btn_login.getScene().getWindow();
		stage1.close();
    }


}
