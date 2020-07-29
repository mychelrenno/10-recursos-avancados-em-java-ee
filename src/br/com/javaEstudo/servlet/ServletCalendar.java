package br.com.javaEstudo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.javaEstudo.dao.DAOEvent;
import br.com.javaEstudo.models.Event;

@WebServlet("/pages/calendar")
public class ServletCalendar extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DAOEvent daoEvent = new DAOEvent();
       
    public ServletCalendar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String data = "[{ \"title\": \"all day event\", \"start\": \"2020-07-29\"},"
//						+"{\"title\": \"long event\", \"start\": \"2020-07-01\", \"end\": \"2020-07-10\"},"
//						+"{\"title\": \"go to google\", \"url\": \"https://google.com.br\", \"start\": \"2020-07-15\" }]";
		
		List<Event> events = daoEvent.getEvents();
		String data = "[";
		int index = 1;
		for (Event event : events) {
			data += "{ \"title\": \""+ event.getDescription() +"\", \"start\": \""+ event.getDate() +"\"}";
			if (index < events.size()) {
				data += ",";
			}
			index++;
		}
		data += "]";
		
		response.setStatus(response.SC_OK);
		response.getWriter().write(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
