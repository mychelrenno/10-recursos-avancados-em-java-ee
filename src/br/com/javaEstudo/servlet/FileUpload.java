package br.com.javaEstudo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileUpload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//usar variavel fileUpload para salvar no banco de dados
			String fileUpload = request.getParameter("fileUpload");
			System.out.println(fileUpload);//file em base 64
			
			response.getWriter().write("Upload realizado com sucesso");
		} catch (Exception e) {
			response.getWriter().write("Erro fatal ao realizar upload");
		}
	}

}
