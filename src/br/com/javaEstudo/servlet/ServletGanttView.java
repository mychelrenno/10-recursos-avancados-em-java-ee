package br.com.javaEstudo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.javaEstudo.dao.DAOGanttView;
import br.com.javaEstudo.models.Projeto;
import br.com.javaEstudo.models.Serie;

@WebServlet("/pages/ganttView")
public class ServletGanttView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DAOGanttView daoGanttView;
       
    public ServletGanttView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		daoGanttView = new DAOGanttView();
		List<Projeto> projetos = daoGanttView.getProjetos();
		
		if (!projetos.isEmpty()) {
			String grantJson = new Gson().toJson(projetos);
		
//		String json = "["+
//		           	"{"+
//		        		"\"id\": \"1\","+
//		        		"\"name\": \"Feature 1\","+
//		        		"\"series\": ["+
//		        			"{ name: \"Planned\", start: new Date(2010,00,01), end: new Date(2010,00,03) },"+
//		        			"{ name: \"Actual\", start: new Date(2010,00,02), end: new Date(2010,00,05), color: \"#f0f0f0\" }"+
//		        		"]"+
//		        	"}"+
//		        "];";
		
			response.setStatus(response.SC_OK);
			response.getWriter().write(grantJson);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
//			System.out.println(request.getParameter("id"));
//			System.out.println(request.getParameter("name"));
//			System.out.println(request.getParameter("start"));
//			System.out.println(request.getParameter("end"));
//			System.out.println(request.getParameter("idProjeto"));
//			System.out.println(request.getParameter("color"));

			Serie s = new Serie();
			s.setId(Long.valueOf(request.getParameter("id")));
			s.setName(request.getParameter("name"));
			s.setStart(request.getParameter("start"));
			s.setEnd(request.getParameter("end"));
			s.setIdProjeto(Long.valueOf(request.getParameter("idProjeto")));
			s.setColor(request.getParameter("color"));

			daoGanttView.atualizar(s);
		} catch (SQLException e) {
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(e.getMessage());
		}
	}

}
