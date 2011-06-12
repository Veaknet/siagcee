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

public class SetEditables extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
		if(admin != null){
			try{
				InstanciaObjeto _obj = null;
				if(request.getParameter("objetoatrabajar") != null){
					_obj = new InstanciaObjeto(admin, micon, Integer.parseInt(request.getParameter("objetoatrabajar")));
					request.setAttribute("highlight", _obj.getId());

					PreguntaEditable.delPreguntasEditables(admin, micon, _obj);
					PreguntaEditable _prgEdit = null;
					String[] _listaPreguntas = request.getParameterValues("preguntaseditables");
					if(_listaPreguntas != null){
						for(int p = 0; p < _listaPreguntas.length; ++p){
							int _idP = Integer.parseInt(_listaPreguntas[p]);
							_prgEdit = new PreguntaEditable(admin, micon);
							_prgEdit.set_InsObjeto(_obj);
							_prgEdit.set_InsPregunta(new InstanciaPregunta(admin, micon, _idP));
						}
					}

					Vector _objetos = Objeto.todosObjetos(admin, micon, 0, true, false);
					Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0);

					request.setAttribute("objetosDisponibles", _objetos);
					request.setAttribute("objetosInstanciados", _instanciados);
					view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
					view.forward(request, response);
				}else{
					view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
					view.forward(request, response);
				}
			}catch (Exception e){
				view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
				view.forward(request, response);
			}
		}else{
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
		if(admin != null){
			try{
				InstanciaObjeto _obj = null;
				if(request.getParameter("objetoatrabajar") != null){
					_obj = new InstanciaObjeto(admin, micon, Integer.parseInt(request.getParameter("objetoatrabajar")));
					request.setAttribute("objetoatrabajar", _obj);
					request.setAttribute("preguntasTotales", InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _obj.getObjetoAsociado(), true));
					request.setAttribute("preguntasEditables",PreguntaEditable.retornaTodasEditables(admin, micon, _obj));
					view = request.getRequestDispatcher("WEB-INF/vistas/seteditables.jsp");
					view.forward(request, response);
				}else{
					view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
					view.forward(request, response);
				}
			}catch (Exception e){
				view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
				view.forward(request, response);
			}
		}else{
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}