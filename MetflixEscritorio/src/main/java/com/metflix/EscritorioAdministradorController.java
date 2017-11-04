package com.metflix;

import java.io.IOException;
import java.net.URL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;

public class EscritorioAdministradorController implements Initializable{
	
		public static AdministradorDelegado delegadoEjb = AdministradorDelegado.getInstancia();
		
		private ObservableList<Empleado> empleadoData;
		
		private ObservableList<Pelicula> peliculaData;
		
		private ObservableList<Venta> ventaData;
		
		private ObservableList<Map> calificacionData;
		
		@FXML
	    private TableView<Pelicula> tbl_peliculas;

	    @FXML
	    private TableColumn<Pelicula, String> col_pelicula_id;

	    @FXML
	    private TableColumn<Pelicula, String> col_pelicula_titulo;

	    @FXML
	    private TableColumn<Pelicula, String> col_pelicula_idioma;

	    @FXML
	    private TableColumn<Pelicula, String> col_pelicula_clasificacion;

	    @FXML
	    private TableColumn<Pelicula, String> col_pelicula_director;

	    @FXML
	    private TableColumn<Pelicula, String> col_pelicula_fecha;
		
		
		
	    @FXML
	    private JFXButton btn_pelicula_buscar;
	    
	    @FXML
	    private JFXTextField txt_peluculas_buscar;

	    @FXML
	    private DatePicker dp_venta_inicio;

	    @FXML
	    private JFXButton btn_buscar_epleado;

	    @FXML
	    private JFXButton btn_peliculas_agregar;

	    @FXML
	    private JFXButton btn_empleados_agregar;

	   
	    
	    @FXML
	    private TableView<Empleado> tbl_empleados;
	    
	    @FXML
	    private TableColumn<Empleado, String> col_empleado_id;
	    
	    @FXML
	    private TableColumn<Empleado, String> col_empleado_identificacion;

	    @FXML
	    private TableColumn<Empleado, String> col_empleado_nombre;

	    @FXML
	    private TableColumn<Empleado, String> col_empleado_apellido;
	    
	    @FXML
	    private TableColumn<Empleado, String> col_empleado_correo;

	    @FXML
	    private JFXTextField txt_empleado_buscar;

	    @FXML
	    private JFXButton btn_ventas_buscar;

	    @FXML
	    private DatePicker dp_venta_fin;

	    @FXML
	    private TableView<Venta> tbl_ventas;

	    @FXML
	    private JFXTextField txt_buscar_calificacion;
	    
	    @FXML
	    private TableView<Pelicula> tbl_calificacion_pelicula;

	    @FXML
	    private JFXButton btn_buscar_calificacion_pelicula;
	    
	    
	    /**
	     * Obtiene todos los empleados de la base de datos y los lista en la tabla de empleados
	     * */
	    public void llenarTablaEmpleados() {
	    	empleadoData =  FXCollections.observableArrayList();
	    	List<Empleado> getEmpleados = delegadoEjb.consultarEmpleados();
	    	for (Empleado empleado : getEmpleados) {
	    		/*Map<String, String> dataRow = new HashMap<>();
	    		dataRow.put("ID", String.valueOf(empleado.getId()));
	    		dataRow.put("IDENTIFICACION", empleado.getIdentificacion());
	    		dataRow.put("NOMBRE", empleado.getNombre());
	    		dataRow.put("APELLIDO", empleado.getApellido());
	    		dataRow.put("CORREO", empleado.getCorreo());
	    		*/
	    		//ObservableList<Empleado> row = FXCollections.observableArrayList();
	    		//row.add(empleado);
	    		empleadoData.add(empleado);
			}
	    	tbl_empleados.setItems(empleadoData);
	    }
	    
	    /**
	     * Obtiene todas los peliculas de la base de datos y las lista en la tabla de peliculas
	     * */
	    public void llenarTablaPeliculas() {
	    	peliculaData =  FXCollections.observableArrayList();
	    	List<Pelicula> getPeliculas =  delegadoEjb.consultarPeliculas();
	    	for (Pelicula pelicula : getPeliculas) {
	    		/*Map<String, String> dataRow = new HashMap<>();
	    		dataRow.put("ID", String.valueOf(pelicula.getId()));
	    		dataRow.put("TITULO", pelicula.getTitulo());
	    		dataRow.put("IDIOMA", pelicula.getIdioma());
	    		dataRow.put("CLASIFICACION", pelicula.getClasificacion());
	    		dataRow.put("DIRECTOR", pelicula.getDirector());
	    		dataRow.put("FECHA ESTRENO", pelicula.getFechaEstreno().toString());
	    		
	    		//ObservableList<Empleado> row = FXCollections.observableArrayList();
	    		//row.add(empleado);
	    		peliculaData.add(dataRow);
	    		*/
	    		peliculaData.add(pelicula);
			}
	    	tbl_peliculas.setItems(peliculaData);
	    }
	    
	    @FXML
	    void agregarPelicula(ActionEvent event) throws IOException {
			//System.out.println(administrador.toString());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PeliculasFormulario.fxml")); 
	
			Parent root = (Parent) loader.load();
			Stage stage =  new Stage();
			
			//EscritorioAdministradorController escritorioAdminController = loader.getController();
			stage.setScene(new Scene(root));
			
			stage.show();
			Stage stage1 = (Stage) btn_ventas_buscar.getScene().getWindow();
			stage1.close();
			//escritorioAdminController.getData(data,username, password);
	    }	

	    @FXML
	    void buscarPelicula(ActionEvent event) {
	    	try{
	    		if(Utilidades
	    				.esNuloVacio(txt_peluculas_buscar.getText())) {
		    		JOptionPane.showMessageDialog(null, "Debe diligenciar todos los campos");
		    	}else {
		    		peliculaData = FXCollections.observableArrayList();
		    		Pelicula pelicula = delegadoEjb.buscarPeliculaPorId(Integer.valueOf(txt_peluculas_buscar.getText()));
		    		//Map<String, String> dataRow = new HashMap<>();
		    		//dataRow.put("ID", String.valueOf(pelicula.getId()));
		    		//dataRow.put("TITULO", pelicula.getTitulo());
		    		//dataRow.put("IDIOMA", pelicula.getIdioma());
		    		//dataRow.put("CLASIFICACION", pelicula.getClasificacion());
		    		//dataRow.put("DIRECTOR", pelicula.getDirector());
		    		//dataRow.put("FECHA ESTRENO", pelicula.getFechaEstreno().toString());
		    		peliculaData.add(pelicula);
		    	}
		    	//delegadoEjb.buscarEmpleadoPorCedula(txt_empleado_buscar.getText());
	    	} catch (NullPointerException e) {
				// TODO: handle exception
	    		JOptionPane.showMessageDialog(null, "Datos incorrectos");
	    		txt_peluculas_buscar.setText("");
	    		llenarTablaPeliculas();
			}
	    }

	    @FXML
	    void agregarEmpleados(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpleadosFromulario.fxml")); 
	    	
			Parent root = (Parent) loader.load();
			Stage stage =  new Stage();
			
			//EscritorioAdministradorController escritorioAdminController = loader.getController();
			stage.setScene(new Scene(root));
			
			stage.show();
			Stage stage1 = (Stage) btn_empleados_agregar.getScene().getWindow();
			stage1.close();
	    }

	    @FXML
	    void buscarEmpleado(ActionEvent event) {
	    	try{
	    		if(Utilidades
	    				.esNuloVacio(txt_empleado_buscar.getText())) {
		    		JOptionPane.showMessageDialog(null, "Debe diligenciar todos los campos");
		    	}else {
		    		empleadoData = FXCollections.observableArrayList();
		    		Empleado empleado = delegadoEjb.buscarEmpleadoPorCedula(txt_empleado_buscar.getText());
		    		/*Map<String, String> dataRow = new HashMap<>();
		    		dataRow.put("ID", String.valueOf(empleado.getId()));
		    		dataRow.put("IDENTIFICACION", empleado.getIdentificacion());
		    		dataRow.put("NOMBRE", empleado.getNombre());
		    		dataRow.put("APELLIDO", empleado.getApellido());
		    		dataRow.put("CORREO", empleado.getCorreo());
		    		*/
		    		empleadoData.add(empleado);
		    	}
	    	} catch (NullPointerException e) {
	    		JOptionPane.showMessageDialog(null, "Datos incorrectos");
	    		txt_empleado_buscar.setText("");
	    		llenarTablaEmpleados();
			}
	    }

	    @FXML
	    void buscarCalificacion(ActionEvent event) {
	    	try{
	    		if(Utilidades
	    				.esNuloVacio(txt_buscar_calificacion.getText())) {
		    		JOptionPane.showMessageDialog(null, "Debe diligenciar todos los campos");
		    	}else {
		    		calificacionData = FXCollections.observableArrayList();
		    		List<Pelicula> getPelicula = delegadoEjb.consultarPeliculasByCalificacion(Double.valueOf(txt_buscar_calificacion.getText()));
		    		for (Pelicula pelicula : getPelicula) {
			    		Map<String, String> dataRow = new HashMap<>();
			    		dataRow.put("ID", String.valueOf(pelicula.getId()));
			    		dataRow.put("TITULO", pelicula.getTitulo());
			    		dataRow.put("CALIFICACION", pelicula.getCalificacion().toString());
			    		dataRow.put("CLASIFICACION", pelicula.getClasificacion());
			    		calificacionData.add(dataRow);
		    		}
		    	}
	    	} catch (NullPointerException e) {
				// TODO: handle exception
	    		JOptionPane.showMessageDialog(null, "Datos incorrectos");
	    		txt_buscar_calificacion.setText("");
			}
	    }
	    
	    @FXML
	    void buscarVentas(ActionEvent event) {
	    	
	    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//llenarTablaEmpleados();
		llenarTablaPeliculas();
	}

}
