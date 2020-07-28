package br.com.javaEstudo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.javaEstudo.dao.UsuarioDAO;
import br.com.javaEstudo.dao.UsuarioDAOMySQL;
import br.com.javaEstudo.models.Usuario;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL();
       
    public CarregarDadosDataTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doGet sendo chamado pelo dataTable");
		try {
			String search = request.getParameter("search[value]");
			List<Usuario> usuarios = usuarioDAO.getUsuarios(search);
			String json = "";
			
			handleRequest(request, response);
			String draw = request.getParameter("draw");
			
			String data = "";
			if (!usuarios.isEmpty()) {
				int index = 1;
				for (Usuario usuario: usuarios) {
					data += "[\""+ usuario.getId() +"\", \""+ usuario.getLogin() +"\"]";
					if (index < usuarios.size()) {
						data += ", ";
					}
					index++;
				}
			}
			
			json = "{"+
					"\"draw\": "+ draw +","+
					"\"recordsTotal\": "+ usuarios.size() +","+
					"\"recordsFiltered\": "+ usuarios.size() +","+
					"\"processing\": false,"+
					"\"data\": ["+ data +"]"+ //fechamento do data
					"}"; //fechamento do jason
		
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		 
//        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");
 
        Enumeration<String> parameterNames = req.getParameterNames();
 
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
//            out.write(paramName);
//            out.write("n");
            System.out.print(paramName);
 
            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
//                out.write("t" + paramValue);
//                out.write("n");
                System.out.println("="+ paramValue);
            }
 
        }
 
//        out.close();
 
    }

}
