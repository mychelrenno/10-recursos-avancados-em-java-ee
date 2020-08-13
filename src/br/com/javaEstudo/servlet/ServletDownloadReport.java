package br.com.javaEstudo.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.javaEstudo.dao.DAOUser;
import br.com.javaEstudo.models.User;
import br.com.javaEstudo.service.ReportService;

@WebServlet("/pages/ServletDownloadReport")
public class ServletDownloadReport extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ReportService relatorioService = new ReportService();
	private DAOUser daoUser = new DAOUser();
       
    public ServletDownloadReport() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoExportar = request.getParameter("tipoExportar");
		List<User> users = daoUser.getUsers(null);
		ServletContext servletContext = request.getServletContext();
		
		String fileURL = relatorioService.gerarRelatorio(
				users,
				new HashMap(),
				"rel_usuario",
				"rel_usuario",
				servletContext,
				tipoExportar);
		
		//Constroi o caminho completo e absoluto do arquivo
		File downloadFile = new File(fileURL);
		FileInputStream fileInputStream = new FileInputStream(downloadFile);
		
		//obter o tipo mine do arquivo
		String mimeType = servletContext.getMimeType(fileURL);
		if (mimeType == null) {
			//define como tipo binário se o mapeamento mime não for encontrado
			mimeType = "aplication/octet-stream";
		}
		
		//define os atributos para resposta
		response.setContentType(mimeType);
		response.setContentLength( (int) downloadFile.length() );
		
		//define o cabeçalho para a resposta
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		
		response.setHeader(headerKey, headerValue);
		
		//obter fluxo de saída da resposta
		OutputStream outputStream = response.getOutputStream();
		
		byte[] bytes = new byte[4096];
		int bytesReader = -1;
		
		//escrever bytes lidos a partir do fluxo de entrada para o fluxo de saída
		while ( (bytesReader = fileInputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, bytesReader);
		}
		
		fileInputStream.close();
		outputStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
