package com.metflix;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class PeliculasFormularioController implements Initializable{
	
	@FXML
    private DatePicker dp_estreno_pelicula;

    @FXML
    private JFXTextField txt_clasificacion_pelicula;

    @FXML
    private JFXButton btn_eliminar_pelicula;

    @FXML
    private JFXButton btn_editar_pelicula;

    @FXML
    private JFXTextField txt_titulo_pelicula;

    @FXML
    private JFXTextArea txa_reparto_pelicula;

    @FXML
    private JFXTextField txt_idioma_pelicula;

    @FXML
    private JFXTextField txt_pais_pelicula;

    @FXML
    private JFXButton btn_guardar_pelicula;

    @FXML
    private JFXTextArea txa_sinopsis_pelicula;

    @FXML
    private JFXTextField txt_director_director;
	
    @FXML
    void guardarPelicula(ActionEvent event) {

    }

    @FXML
    void editarPelucula(ActionEvent event) {

    }

    @FXML
    void eliminarPelicula(ActionEvent event) {

    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @param state
	 * Estado de el formulario
	 *  
	 * 
	 * */
	public void getData(int state) {
		
	}

}
