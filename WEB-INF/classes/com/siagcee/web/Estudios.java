package com.siagcee.web;

import com.siagcee.logic.Administrador;
import com.siagcee.logic.Pregunta;
import com.siagcee.logic.Objeto;
import com.siagcee.logic.Estudio;

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
			request.setAttribute("estructura", (String)request.getParameter("estructura"));
			request.setAttribute("opcionprincipal", "estudios");
			request.setAttribute("opcionbase",request.getParameter("opcionbase"));
			request.setAttribute("listaTipoDeDatos", null);
			request.setAttribute("objetoatrabajar", null);
			request.setAttribute("listadoEstudios", null);
			request.setAttribute("_todas_preguntas_estaticas", Pregunta.todasPreguntas(admin, micon, 0, true, false));
			try{
				Objeto _miObj = null;
				_miObj = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("estructura")));
				//generadorestudios?accion=listarestudios&objetoatrabajar="+miObj.getObjetoAsociado().getId()+"
				Vector _listaTiposDeDatos = Pregunta.preguntasDeObjeto(admin, micon, _miObj);

				request.setAttribute("listaTipoDeDatos", _listaTiposDeDatos);
				request.setAttribute("objetoatrabajar", _miObj);
				request.setAttribute("listadoEstudios", Estudio.obtenerEstudiosDeEstructura(admin, micon, _miObj, true));
				sesion.setAttribute("retornoDireccion", "estudios.do");
				if(request.getParameter("opcionbase").contains("crear")){
					view = request.getRequestDispatcher("WEB-INF/vistas/generadorestudios.jsp");
				}else if(request.getParameter("opcionbase").contains("modificar")){
					view = request.getRequestDispatcher("WEB-INF/vistas/listarestudios.jsp");
				}else if(request.getParameter("opcionbase").contains("eliminar")){
					view = request.getRequestDispatcher("WEB-INF/vistas/listarestudios.jsp");
				}else if(request.getParameter("opcionbase").contains("aplicar")){
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
			try {
				Vector _estructuras = Objeto.todosObjetos(admin, micon, 0, true, false);
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
