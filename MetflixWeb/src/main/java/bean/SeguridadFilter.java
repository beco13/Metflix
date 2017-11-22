package bean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/pages/auth/*")
public class SeguridadFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * metodo que permite hacer filtro a cada peticion que se hace del servidor, 
	 * con el objetivo de determinar si el usuario esta navegando a la sección correcta dentro del sistema
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);
		String loginURL = req.getContextPath() + "/pages/index.xhtml";

		// obtenemos la informacion de la sesion
		AuthBean authBean = (AuthBean) ((HttpServletRequest) req).getSession().getAttribute("authBean");
		
		// verificamos si esta logueado
		boolean autenticado = authBean != null && authBean.getEstado();
		
		boolean isUserValid = false;
		
		// verificamos si el usuario es cliente y esta dentro de cliente
		if(authBean.userEsCliente() && req.getRequestURI().contains("/pages/auth/cliente")) {
			isUserValid = true;
		}else if(authBean.userEsEmpleado() && req.getRequestURI().contains("/pages/auth/empleado")) {
			isUserValid = true; 
		} else if(authBean.userEsAdministrador() && req.getRequestURI().contains("/pages/auth/administrador")) {
			isUserValid = true;
		}
		
		// verificamos si el usuario esta en un lugar diferente y que tampoco esté dentro de carpetas que no le corresponden
		if(!isUserValid && !req.getRequestURI().contains("/pages/auth/")) {
			isUserValid = true; 
		}
		
		// validamos finalmente si esta en un lugar diferente
		if(!autenticado || !isUserValid) {
			res.sendRedirect(loginURL);
			return;
		}
		
		// dejamos que continue la peticion
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
