package com.siagcee.web;

import com.siagcee.logic.Administrador;
import com.siagcee.logic.InstanciaObjeto;
import com.siagcee.logic.Objeto;
import com.siagcee.logic.UtilidadesVarias;

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
 * Fecha: 16/04/2010
 * Hora: 10:16:32 AM
 */
public class CorreosAInvitar extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");

		String dominioweb = UtilidadesVarias.dominioWeb;
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
		if(admin != null){
			try {
				InstanciaObjeto _miIns = null;
				if(request.getParameter("_hightlight") != null){
					_miIns = new InstanciaObjeto(admin, micon, Integer.parseInt(request.getParameter("_hightlight")));

					String _mensaje = "";
					if(request.getParameter("mensaje") != null){
						_mensaje = request.getParameter("mensaje");
					}
					String _listadoCorreos = "";
					String[] _listadoCorreos2;
					if(request.getParameter("correos") != null){
						_listadoCorreos = request.getParameter("correos");
						_listadoCorreos2 = _listadoCorreos.split("\n");
						_mensaje = _mensaje+"\n<br /><p />\n"+"<a target='_blank' href=\""+UtilidadesVarias.dominioWeb+"autenticarusuario.do?identificador_publico="+_miIns.getIdPublico()+"\">"+_miIns.getObjeto()+"</a>";
						UtilidadesVarias.enviarMailSinAutenticacion(_listadoCorreos2, "Usted esta cordialmente invitado a participar.", _mensaje, "SIGECENE <sigecene@sigecene.com>", "", "mail.cantv.net");

/*							for(int p = 0; p < _listadoCorreos2.length; ++p){
								String _miParam = _listadoCorreos2[p];
								try{
									UtilidadesVarias.enviarMailSinAutenticacion(_miParam, "Usted esta cordialmente invitado a participar.", _mensaje, "SIGECENE <sigecene@sigecene.com>", "", "mail.cantv.net");
								}catch (Exception ee){ee.printStackTrace();}
							}*/
						//invito a cada correo... y finalizo
					}
					Vector _objetos = Objeto.todosObjetos(admin, micon, 0, true, false);
					Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon);

					request.setAttribute("objetosDisponibles", _objetos);
					request.setAttribute("objetosInstanciados", _instanciados);
					
					request.setAttribute("hightlight", _miIns.getId());
					request.setAttribute("_hightlight", _miIns.getId());
					view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
					view.forward(request, response);
				}else{
					view = request.getRequestDispatcher("autenticar.do");
					view.forward(request, response);
				}

			}catch (Exception e){
				//sesion no iniciada
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else {
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
		if(admin != null){
			try {
				InstanciaObjeto _miIns = null;
				if(request.getParameter("_hightlight") != null){
					_miIns = new InstanciaObjeto(admin, micon, Integer.parseInt(request.getParameter("_hightlight")));

					request.setAttribute("highlight", _miIns.getId());
					request.setAttribute("objeto", _miIns);

					view = request.getRequestDispatcher("WEB-INF/vistas/correos_a_invitar.jsp");
					view.forward(request, response);
				}
			}catch (Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}