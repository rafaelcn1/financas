package br.com.financas.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.financas.bean.LoginBean;

public class LoginFilter implements Filter {

	@Inject
	private LoginBean loginBean;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String url = request.getRequestURL().toString();
		
		if (url.contains("/pages") && loginBean.getUsuario() == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/login.xhtml");
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		
		
	}

}
