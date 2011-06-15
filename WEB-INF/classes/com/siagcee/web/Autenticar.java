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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class Autenticar extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		String user = request.getParameter("usuario");
		String pass = request.getParameter("clave");

		try {
			HttpSession sesion = request.getSession();
			sesion.setMaxInactiveInterval(999999);
			Connection micon = (Connection) getServletContext().getAttribute("conexion");
			Administrador admin = new Administrador(micon, user, pass);
			//credenciales correctas
			if(admin.getValidado()){
				sesion.setAttribute("administrador", admin);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
				view.forward(request, response);
			}else{
				sesion.invalidate();
				String mensaje = "<span style='color:red;'>Datos incorrectos. Por favor intente de nuevo</span>";
				request.setAttribute("accion", "mostrarForm");
				request.setAttribute("mensaje", mensaje);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminform.jsp");
				view.forward(request, response);
			}
		}catch (Exception e) {
			//e.printStackTrace();
			request.setAttribute("accion", "mostrarForm");
			request.setAttribute("mensaje", "");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminform.jsp");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		String accion = new String("mostrarForm");
		String mensaje = new String("");
		request.setAttribute("accion", accion);
		request.setAttribute("mensaje", mensaje);
		HttpSession sesion = request.getSession();
		Administrador admin = (Administrador)sesion.getAttribute("administrador");
		if(admin != null){
			if(admin.getValidado()){
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
				view.forward(request, response);
			}else{
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminform.jsp");
				view.forward(request, response);
			}
		}else{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminform.jsp");
			view.forward(request, response);
		}
	}
}