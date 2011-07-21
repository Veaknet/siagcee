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
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
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

public class AdministrarRespuestas4 extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
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
					request.setAttribute("preguntaseleccionada", _preguntaSeleccionada);

					//cargo los instrumentos finalizados disponibles
					SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //formatear la fecha para comprobacion interna de la pagina
					SimpleDateFormat _dateFormatVisible = new SimpleDateFormat("dd/MM/yyyy");  //formatear la fecha para la visualizacion del usuario
					Date _hoy = new Date();
					Vector _insObj = null;
					Vector _insObjFinal = new Vector();
					if(admin.getTipoUsuario().equals("superadministrador")){
						_insObj = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0);
					}else{
						_insObj = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0);
					}
					Enumeration _enu = _insObj.elements();
					while(_enu.hasMoreElements()){
						InstanciaObjeto _insObjAct = (InstanciaObjeto)_enu.nextElement();
						if(!_insObjAct.isEliminado() &&
							   !(_dateFormat.format(_hoy).compareTo(_dateFormat.format(_insObjAct.getFechaInicio())) < 0) &&
							   !(_dateFormat.format(_hoy).compareTo(_dateFormat.format(_insObjAct.getFechaCierre())) < 0)){
							_insObjFinal.add(_insObjAct);
						}
					}
					request.setAttribute("objetosInstanciados", _insObjFinal);

					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas4.jsp");
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				//e.printStackTrace();
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
