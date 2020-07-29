package br.com.javaEstudo.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.javaEstudo.dao.DAOImage;
import br.com.javaEstudo.models.Image;

@WebServlet("/pages/fileUpload")
public class ServletFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletFileUpload() {
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
			
			//base64 para byte[]
			String extensao = fileUpload.split(",")[0].split(";")[0].split("/")[1];
			String base64string = fileUpload.split(",")[1];
			byte[] imagem = Base64.getDecoder().decode(base64string);
			//byte[] para base64
			String byteToBase64 = Base64.getEncoder().encodeToString(imagem);
			Image i = new Image();
			i.setImagem(imagem);
			i.setExtensao(extensao);
			//persiste imagem no banco de dados
			DAOImage imagemDAO = new DAOImage();
			imagemDAO.addImagem(i);
			
			
			
			System.out.println( System.getProperty("user.dir") );
			System.out.println( new File(".").getAbsolutePath() );
			System.out.println( getClass().getResource("") );
			System.out.println( getClass().getResourceAsStream("") );
			System.out.println( Paths.get("").toAbsolutePath().getParent() );
			System.out.println( super.getServletContext().getRealPath("imagens") );
			System.out.println( request.getSession().getServletContext().getRealPath("imagens") );
			
			
			
//			File file = new File("C:\\projetos\\10-recursos-avancados-em-java-ee\\10-recursos-avancados-em-java-ee\\WebContent\\imagens\\meuarquivo2." + extensao); //Criamos um nome para o arquivo
//			Path path = file.toPath();
//			if (file.exists()) {
//				file.delete();
//			}
//			file.createNewFile();
			
//			OutputStream output = new FileOutputStream(file);
//			output.write(imagem);
//			output.close();
			
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file)); //Criamos o arquivo
//			bos.write(imagem); //Gravamos os bytes lá
//			bos.close(); //Fechamos o stream.
			
//			OutputStream os = response.getOutputStream();
//			Files.copy(path, os);
			
			response.getWriter().write("Upload realizado com sucesso");
		} catch (Exception e) {
			response.getWriter().write("Erro fatal ao realizar upload");
		}
	}

}
