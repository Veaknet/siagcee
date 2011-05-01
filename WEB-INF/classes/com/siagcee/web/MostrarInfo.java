package com.siagcee.web;

import com.siagcee.logic.Administrador;
import com.siagcee.logic.Estudio;
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

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 16/12/2009
 * Hora: 10:46:31 PM
 */
public class MostrarInfo extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				String _accion = "";
				if(request.getParameter("accion") != null){
					_accion = (String)request.getParameter("accion");
					if(_accion.equals("mostrarinfodeobjeto")){
						if(request.getParameter("id") != null){
							int _id = Integer.parseInt((String)request.getParameter("id"));
							Objeto _miObj = Objeto.retornaObjeto(admin, micon, _id);
							request.setAttribute("accion", _accion);
							request.setAttribute("objeto", _miObj);
						}
					}else if(_accion.equals("mostrarinfodepregunta")){
						int _id = Integer.parseInt((String)request.getParameter("id"));
						Pregunta _miPreg = new Pregunta(admin, micon, _id);
						request.setAttribute("accion", _accion);
						request.setAttribute("pregunta", _miPreg);
					}else if(_accion.equals("mostrarinfodeestudio")){
						int _id = Integer.parseInt((String)request.getParameter("id"));
						Estudio _miEst = new Estudio(admin, micon, _id);
						request.setAttribute("accion", _accion);
						request.setAttribute("estudio", _miEst);
					}
				}
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/mostrarinfo.jsp");
				view.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "<span style='color:red'>Error procesando su solicitud</span>");
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/mostrarinfo.jsp");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
