/**
 * 
 */
package br.com.iasc.seguranca.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author tiago.menezes
 * 
 */
public class CharsetFilter implements Filter {

	private String encoding;

	/* 
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */

	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(this.encoding);
		}

		//		response.setContentType("text/html; charset=ISO-8859-1");
		//		response.setCharacterEncoding("ISO-8859-1");

		response.setContentType("text/html; charset=UTF-8");
		//response.setCharacterEncoding("UTF-8");

		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */

	public void init(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("requestEncoding");

		if (this.encoding == null) {
			this.encoding = "UTF-8";
			//			this.encoding = "ISO-8859-1";
		}
	}

}
