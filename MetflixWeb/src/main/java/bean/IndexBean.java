package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Locale;


@ManagedBean
public class IndexBean {

	public void cambiarIdioma() {
		
		if(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage() == "es") {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
		}
		else{
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es"));
		}
		
	}
	
}
