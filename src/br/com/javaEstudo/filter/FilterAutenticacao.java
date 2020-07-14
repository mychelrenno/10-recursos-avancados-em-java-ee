package br.com.javaEstudo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.javaEstudo.user.UserLogado;

//@WebFilter(urlPatterns= {"/pages/acessoAoSistema.jsp"})
@WebFilter(urlPatterns= {"/pages/*"})
public class FilterAutenticacao implements Filter {

	//faz alguma coisa quando a aplicação é derrubada
	@Override
	public void destroy() {
		
	}

	//intercepta todas as requisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		//retorna null quando não esteja logado
		UserLogado userLogado = (UserLogado) session.getAttribute("usuario");
		
		System.out.println(req.getServletPath());
		String urlParaAutenticar = req.getServletPath();
		
		if (userLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp?url=" + urlParaAutenticar);
			dispatcher.forward(request, response);
			return;//para o processo redirecionar
		}
		
		//responsavel por fazer a execução do request e response
		chain.doFilter(request, response);
		
		System.out.println("Interceptando");
	}

	//executa alguma coisa quando a aplicação é iniciada
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}