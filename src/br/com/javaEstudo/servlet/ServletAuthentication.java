package br.com.javaEstudo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.javaEstudo.user.UserLogged;

@WebServlet("/pages/ServletAuthentication")
public class ServletAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAuthentication() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		//neste momento pode ser feito uma validação no banco de dados
		if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {
			//se o login foi bem sucedido
			
			UserLogged userLogado = new UserLogged();
			userLogado.setLogin(login);
			userLogado.setSenha(senha);
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.setAttribute("usuario", userLogado);
			
			//redireciona para o sistema e autoriza
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else {//se o login falhou
			//redireciona para a tela de autenticação/login novamente
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
			dispatcher.forward(request, response);
		}
	}

}
