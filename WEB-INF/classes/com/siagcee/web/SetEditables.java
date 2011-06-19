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
import java.util.Enumeration;
import java.util.HashMap;
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

					String _accion = request.getParameter("accion");
					if(_accion.equals("setopcionales")){
						HashMap _opcional = new HashMap();
						Enumeration _enu = PreguntaEditable.retornaTodasEditables(admin, micon, _obj).elements();
						PreguntaEditable _prEd;
						while(_enu.hasMoreElements()){
							_prEd = (PreguntaEditable)_enu.nextElement();
							_prEd.setRequerida(true);
							_opcional.put(_prEd.get_InsPregunta().getId(), _prEd);
						}

						String[] _listaPreguntas = request.getParameterValues("preguntaseditables");
						if(_listaPreguntas != null){
							for(int p = 0; p < _listaPreguntas.length; ++p){
								int _idP = Integer.parseInt(_listaPreguntas[p]);
								_prEd = (PreguntaEditable)_opcional.get(_idP);
								_prEd.setRequerida(false);
							}
						}

						Vector _objetos = Objeto.todosObjetos(admin, micon, 0, true, false);
						Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0);

						request.setAttribute("objetosDisponibles", _objetos);
						request.setAttribute("objetosInstanciados", _instanciados);
						view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
					}else{
						String[] _listaPreguntas = request.getParameterValues("preguntaseditables");
						if(_listaPreguntas != null){
							for(int p = 0; p < _listaPreguntas.length; ++p){
								int _idP = Integer.parseInt(_listaPreguntas[p]);
								new PreguntaEditable(admin, micon, _obj, new InstanciaPregunta(admin, micon, _idP));
							}
						}

						request.setAttribute("objetoatrabajar", _obj);
						request.setAttribute("accion", "setopcionales");
						Vector _pregTotal = new Vector();
						Enumeration _enu = PreguntaEditable.retornaTodasEditables(admin, micon, _obj).elements();
						while(_enu.hasMoreElements()){
							PreguntaEditable _pregEdit = (PreguntaEditable)_enu.nextElement();
							if(_pregEdit.get_InsPregunta().isCampo_clave_unico()){
								continue;
							}
							_pregTotal.add(_pregEdit.get_InsPregunta());
						}
						request.setAttribute("preguntasTotales", _pregTotal);
						request.setAttribute("preguntasEditables",PreguntaEditable.retornaTodasOpcionales(admin, micon, _obj));
						view = request.getRequestDispatcher("WEB-INF/vistas/seteditables.jsp");
					}
					view.forward(request, response);
				}else{
					view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
					view.forward(request, response);
				}
			}catch (Exception e){
				e.printStackTrace();
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
					request.setAttribute("accion", "seteditables");
					Vector _pregTotal = new Vector();
					Enumeration _enu = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _obj.getObjetoAsociado(), true).elements();
					while(_enu.hasMoreElements()){
						InstanciaPregunta _pregEdit = (InstanciaPregunta)_enu.nextElement();
						if(_pregEdit.getTipoPregunta() == 100){
							continue;
						}
						_pregTotal.add(_pregEdit);
					}
					request.setAttribute("preguntasTotales", _pregTotal);
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