package com.siagcee.web;

import com.siagcee.logic.Administrador;
import com.siagcee.logic.InstanciaPregunta;
import com.siagcee.logic.Objeto;
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

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 29/11/2009
 * Hora: 11:59:45 AM
 */

public class AdministrarObjetos3 extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				if(request.getParameter("objetoseleccionado") != null){
					Objeto _objetoSeleccionado = null;
					_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionado")));

					if(request.getParameter("objetoseleccionadoexterno") != null){
						Objeto _objetoSeleccionadoExterno = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionadoexterno")));
						request.setAttribute("objetoexterno", _objetoSeleccionadoExterno);
						Vector _preguntasExternas = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _objetoSeleccionadoExterno, true);
						request.setAttribute("listadoPreguntasExternas", _preguntasExternas);
					}

					request.setAttribute("objetoatrabajar", _objetoSeleccionado);

					//muestro objetos disponibles para que seleccione sus preguntas
					Vector _objetosExternos = Objeto.todosObjetos(admin, micon, true, false);
					request.setAttribute("objetosexternos", _objetosExternos);

					RequestDispatcher view;

					if(request.getParameter("accion") != null){
						if(((String)request.getParameter("accion")).equals("agregarPreguntas")){
							//agrego las preguntas
							//listadoPreguntasDeseadas
							String[] _listadoPreguntasDeseadas = request.getParameterValues("listadoPreguntasDeseadas");
							if(_listadoPreguntasDeseadas != null){
								for(int p = 0; p < _listadoPreguntasDeseadas.length; ++p){
									String _miParam = _listadoPreguntasDeseadas[p];
									InstanciaPregunta _insPreg = new InstanciaPregunta(admin, micon, Integer.parseInt((String)_miParam));
									//clono la instancia pregunta
									InstanciaPregunta _nuevaPreg = new InstanciaPregunta(_insPreg);
									//establezco nuevo objeto asociado
									_nuevaPreg.setPadre(_objetoSeleccionado);
								}
							}
						}
						Vector _preguntasComunes = Pregunta.todasPreguntas(admin, micon, true, false);
						Vector _misPreguntas = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _objetoSeleccionado, true);
						request.setAttribute("listadoPreguntas", _misPreguntas);
						request.setAttribute("listadoPreguntasComunes", _preguntasComunes);

						view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos2.jsp");
					}else{
						view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos3.jsp");
					}
					view.forward(request, response);
				}else{
					Vector _objetos = Objeto.todosObjetos(admin, micon, 0, false, true);
					request.setAttribute("listadoObjetos", _objetos);
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos.jsp");
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
				if(request.getParameter("objetoseleccionado") != null){
					Objeto _objetoSeleccionado = null;
					_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionado")));

					if(request.getParameter("objetoseleccionadoexterno") != null){
						Objeto _objetoSeleccionadoExterno = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionadoexterno")));
						request.setAttribute("objetoexterno", _objetoSeleccionadoExterno);
						Vector _preguntasExternas = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _objetoSeleccionadoExterno, true);
						request.setAttribute("listadoPreguntasExternas", _preguntasExternas);
					}

					//muestro objetos disponibles para que seleccione sus preguntas
					Vector _objetosExternos = Objeto.todosObjetos(admin, micon, true, false);
					request.setAttribute("objetosexternos", _objetosExternos);

					request.setAttribute("objetoatrabajar", _objetoSeleccionado);
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos3.jsp");
					view.forward(request, response);
				}else{
					Vector _objetos = Objeto.todosObjetos(admin, micon, 1, false, true);
					request.setAttribute("listadoObjetos", _objetos);
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos.jsp");
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				//e.printStackTrace();
				RequestDispatcher view = request.getRequestDispatcher("adminobjetos.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
