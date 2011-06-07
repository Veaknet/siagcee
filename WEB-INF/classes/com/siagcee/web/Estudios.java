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
 * Fecha: 23/02/2010
 * Hora: 10:43:00 AM
 */
public class Estudios extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view;
		if(admin != null){
			request.setAttribute("objetoatrabajar", (String)request.getParameter("objetoatrabajar"));
			request.setAttribute("opcionprincipal", "estudios");
			request.setAttribute("opcionbase",request.getParameter("opcionbase"));
			request.setAttribute("listaTipoDeDatos", null);
			request.setAttribute("objetoatrabajar", null);
			request.setAttribute("listadoEstudios", null);
			request.setAttribute("_todas_preguntas_estaticas", Pregunta.todasPreguntas(admin, micon, 0, true, false));
			try{
				InstanciaObjeto _miObj = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
				//generadorestudios?accion=listarestudios&objetoatrabajar="+miObj.getObjetoAsociado().getId()+"
				Vector _listaTiposDeDatos = Pregunta.preguntasDeObjeto(admin, micon, _miObj.getObjetoAsociado());

				EstudioPerso.getInstance().setConexion(micon);
				EstudioPerso.getInstance().setAdmin(admin);
				request.setAttribute("listaTipoDeDatos", _listaTiposDeDatos);
				request.setAttribute("objetoatrabajar", _miObj);
				request.setAttribute("listadoEstudios", EstudioPerso.obtenerEstudiosDeEstructura(admin, micon, _miObj.getObjetoAsociado(), true));

				if(request.getParameter("opcionbase").contains("modificar")){
					view = request.getRequestDispatcher("WEB-INF/vistas/listarestudios.jsp");
				}else if(request.getParameter("opcionbase").contains("eliminar")){
					view = request.getRequestDispatcher("WEB-INF/vistas/listarestudios.jsp");
				}else{
					view = request.getRequestDispatcher("autenticar.do");
				}
			}catch(Exception e){
				//si algun error ocurrio ubicando las respuestas
				//entonces intento mostrar solo las preguntas.
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
			}
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
		}
		view.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/estudios.jsp");
		if(admin != null){
			EstudioPerso.getInstance().setConexion(micon);
			EstudioPerso.getInstance().setAdmin(admin);
			try {
				Vector _estructuras;
				if(admin.getTipoUsuario().equals("superadministrador")){
					_estructuras = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0, false);
				}else{
					_estructuras = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0, false);
				}
				request.setAttribute("estructuras",_estructuras);
				request.setAttribute("opcionbase",request.getParameter("opcionbase"));
			}catch(Exception e) {
				//si algun error ocurrio ubicando las respuestas
				//entonces intento mostrar solo las preguntas.
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
			}
			view.forward(request, response);
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
