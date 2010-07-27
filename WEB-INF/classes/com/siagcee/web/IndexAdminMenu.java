package com.siagcee.web;

import com.siagcee.logic.Administrador;
import com.siagcee.logic.Pregunta;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 25/02/2010
 * Hora: 09:46:12 AM
 */
public class IndexAdminMenu extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		try{
			Connection micon = (Connection) getServletContext().getAttribute("conexion");
			Administrador admin = (Administrador) sesion.getAttribute("administrador");
			if(admin != null){
				RequestDispatcher view = null;
				String opcionPrincipal = "";
				/*
				if(request.getParameter("opcionprincipal") != null || request.getAttribute("opcionprincipal") != null){
					if(request.getParameter("opcionprincipal") != null){
						opcionPrincipal = (String)request.getParameter("opcionprincipal");
					}
					if(request.getAttribute("opcionprincipal") != null){
						opcionPrincipal = (String)request.getAttribute("opcionprincipal");
					}
					request.setAttribute("mensaje", null);
					if(opcionPrincipal.equals("plantillapreguntas")){
						request.setAttribute("admin", admin);
						request.setAttribute("opcionprincipal", "plantillapreguntas");
						view = request.getRequestDispatcher("WEB-INF/vistas/indexadminmenu.jsp");
					}else if(opcionPrincipal.equals("estructurasbases")){
						if(!admin.getTipoUsuario().equals("superadministrador")){
							request.setAttribute("mensaje", "<span style='color:red'>Error procesando la solicitud. No posee los privilegios necesarios para esta opci&oacute;n</span>");
							view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
						}else{
							view = request.getRequestDispatcher("WEB-INF/vistas/indexadminmenu.jsp");
							request.setAttribute("admin", admin);
							request.setAttribute("opcionprincipal", "estructurasbases");
						}
					}else if(opcionPrincipal.equals("instrumentos")){
						request.setAttribute("admin", admin);
						request.setAttribute("opcionprincipal", "instrumentos");
						view = request.getRequestDispatcher("WEB-INF/vistas/indexadminmenu.jsp");
					}else if(opcionPrincipal.equals("estudios")){
						request.setAttribute("admin", admin);
						request.setAttribute("opcionprincipal", "estudios");
						view = request.getRequestDispatcher("WEB-INF/vistas/indexadminmenu.jsp");
					}else if(opcionPrincipal.equals("generados")){
						request.setAttribute("admin", admin);
						request.setAttribute("opcionprincipal", "generados");
						view = request.getRequestDispatcher("WEB-INF/vistas/indexadminmenu.jsp");
					}else if(opcionPrincipal.equals("relaciones")){
						request.setAttribute("admin", admin);
						request.setAttribute("opcionprincipal", "relaciones");
						view = request.getRequestDispatcher("WEB-INF/vistas/indexadminmenu.jsp");
					}else{
						request.setAttribute("mensaje", "<span style='color:red'>Error procesando la solicitud. Opci&oacute;n no v&aacute;lida: "+opcionPrincipal+"</span>");
						view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
					}
				}else{
					request.setAttribute("mensaje", "<span style='color:red'>Error procesando la solicitud. Opci&oacute;n no reconocida.</span>");
					view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
				}*/
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}else{
				//sesion no iniciada
				RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}catch (Exception e){
			e.printStackTrace();
			//no se pudieron mostrar las preguntas editables. entonces no se muestra nada
			request.setAttribute("mensaje", "Error procesando la solicitud.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
			view.forward(request, response);
		}
	}
	
}
