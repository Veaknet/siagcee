package com.siagcee.web;
/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 24/11/2009
 * Hora: 01:21:48 PM
 */

import com.siagcee.logic.Administrador;
import com.siagcee.logic.Pregunta;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

public class AdministrarRespuestas extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				//cargo todas las preguntas editables
				Vector _preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
				request.setAttribute("preguntas", _preguntas);

				if (request.getParameter("preguntaseleccionada") != null) {
					//se ubica la pregunta seleccionada en el formulario.
					int idPreguntaTrabajar = Integer.parseInt((String) request.getParameter("preguntaseleccionada"));
					Pregunta preguntaATrabajar = new Pregunta(admin, micon, idPreguntaTrabajar);

					//obtenemos las respuestas asociadas a la pregunta seleccionada
					Vector _respuestas = preguntaATrabajar.retornaRespuestasPosibles();
					request.setAttribute("preguntaatrabajar", preguntaATrabajar);
					request.setAttribute("respuestas", _respuestas);
				}
				//enviamos a la vista de edicion de respuestas
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas2.jsp");
				view.forward(request, response);
			}catch(Exception e) {
				//si algun error ocurrio ubicando las respuestas
				//entonces intento mostrar solo las preguntas.
				try{
					if(admin != null){
						//cargo todas las preguntas editables
						Vector _preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
						request.setAttribute("preguntas", _preguntas);
						RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas.jsp");
						view.forward(request, response);
					}else{
						//sesion no iniciada
						RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
						view.forward(request, response);
					}
				}catch(Exception e2){
					//no se pudieron mostrar las preguntas editables. entonces no se muestra nada
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas.jsp");
					view.forward(request, response);
				}
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		try{
			Connection micon = (Connection) getServletContext().getAttribute("conexion");
			Administrador admin = (Administrador) sesion.getAttribute("administrador");
			if(admin != null){
				//cargo todas las preguntas editables
				Vector _preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
				request.setAttribute("preguntas", _preguntas);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas.jsp");
				view.forward(request, response);
			}else{
				//sesion no iniciada
				RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}catch (Exception e) {
			//no se pudieron mostrar las preguntas editables. entonces no se muestra nada
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas.jsp");
			view.forward(request, response);
		}
	}
}