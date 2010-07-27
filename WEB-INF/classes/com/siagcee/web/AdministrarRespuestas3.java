package com.siagcee.web;

import com.siagcee.logic.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 29/11/2009
 * Hora: 11:59:45 AM
 */

public class AdministrarRespuestas3 extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				if(request.getParameter("preguntaseleccionada") != null){
					Pregunta _preguntaSeleccionada = null;
					_preguntaSeleccionada = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionada")));

					if(request.getParameter("preguntaseleccionadaexterna") != null){
						Pregunta _preguntaSeleccionadaExterna = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionadaexterna")));
						request.setAttribute("preguntaExterna", _preguntaSeleccionadaExterna);
						Vector _respuestasExternas = _preguntaSeleccionadaExterna.retornaRespuestasPosibles(true);
						request.setAttribute("listadoRespuestasExternas", _respuestasExternas);
					}

					request.setAttribute("preguntaseleccionada", _preguntaSeleccionada);

					//muestro preguntas disponibles para que seleccione sus respuestas posibles
					Vector _preguntasExternas = Pregunta.todasPreguntas(admin, micon, 0, true, false);
					request.setAttribute("preguntasexternas", _preguntasExternas);

					RequestDispatcher view;

					if(request.getParameter("accion") != null){
						if(((String)request.getParameter("accion")).equals("agregarRespuestas")){
							//agrego las respuestas
							//listadoRespuestasDeseadas
							String[] _listadoRespuestasDeseadas = request.getParameterValues("listadoRespuestasDeseadas");
							if(_listadoRespuestasDeseadas != null){
								for(int p = 0; p < _listadoRespuestasDeseadas.length; ++p){
									String _miParam = _listadoRespuestasDeseadas[p];
									RespuestasPosibles _miResp = new RespuestasPosibles(admin, micon, Integer.parseInt((String)_miParam));
									//clono la respuesta
									RespuestasPosibles _nuevaResp = new RespuestasPosibles(admin, micon, _miResp.getRespuesta());
									//asocio a la pregunta
									_preguntaSeleccionada.agregarRespuesta(_nuevaResp);
								}
							}
						}

						Vector _misRespuestas = RespuestasPosibles.obtenerRespuestas(admin, micon, _preguntaSeleccionada);
						request.setAttribute("respuestas", _misRespuestas);
						request.setAttribute("preguntaatrabajar", _preguntaSeleccionada);

						view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas2.jsp");
					}else{
						view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas3.jsp");
					}
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				RequestDispatcher view = request.getRequestDispatcher("adminobjetos.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				if(request.getParameter("preguntaseleccionada") != null){
					Pregunta _preguntaSeleccionada = null;
					_preguntaSeleccionada = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionada")));

					if(request.getParameter("preguntaseleccionadaexterna") != null){
						Pregunta _preguntaSeleccionadaExterna = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionadaexterna")));
						request.setAttribute("preguntaExterna", _preguntaSeleccionadaExterna);
						Vector _respuestasExternas = _preguntaSeleccionadaExterna.retornaRespuestasPosibles(true);
						request.setAttribute("listadoRespuestasExternas", _respuestasExternas);
					}

					request.setAttribute("preguntaseleccionada", _preguntaSeleccionada);

					//muestro preguntas disponibles para que seleccione sus respuestas posibles
					Vector _preguntasExternas = Pregunta.todasPreguntas(admin, micon, 0, true, false);
					request.setAttribute("preguntasexternas", _preguntasExternas);

					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas3.jsp");
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				e.printStackTrace();
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
