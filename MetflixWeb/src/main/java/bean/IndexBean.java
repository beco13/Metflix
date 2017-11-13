package bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import java.util.Locale;
import java.util.Properties;

@ManagedBean
public class IndexBean {

	public void cambiarIdioma() {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
	}
	
	public String getMensaje()
	{
		String mensaje = "no cargado--";
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/main/resources/resources/mensajes.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			mensaje = prop.getProperty("saludo");
			
			System.out.println("mensaje; "+mensaje);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return mensaje;
	}
}
