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

public class Salir extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession sesion = request.getSession();
		Administrador admin = (Administrador)sesion.getAttribute("administrador");

		RequestDispatcher view;
		view = request.getRequestDispatcher("autenticar.do");
		if(admin != null){
			admin.invalidaUsuario();
			view = request.getRequestDispatcher("autenticar.do");
		}
		sesion.invalidate();
		view.forward(request, response);

	}
}