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
import com.siagcee.logic.Pregunta;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

public class AdministrarPreguntas extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminpreguntas.jsp");

		if(admin != null){
			try {
				//cargo todas las preguntas editables
				Vector _preguntas = new Vector();
				if((request.getParameter("opcionbase")!=null) && (((String)request.getParameter("opcionbase")).equals("revisar"))){
					_preguntas = Pregunta.todasPreguntas(admin, micon, 0, false, false);
				}else{
					//cargo todas las preguntas editables
					_preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
				}
				request.setAttribute("preguntas", _preguntas);

				int idPreguntaTrabajar = -1;
				Pregunta _preguntaATrabajar = null;

				//verifico si la ingreso o actualizo... eliminar viene por method get
				if (request.getParameter("accion") != null){
					String _accion = (String) request.getParameter("accion");

					if(_accion.equals("insertar")){
						int _tipoPregunta = Integer.parseInt((String) request.getParameter("tipopregunta"));
						String _visible = (String) request.getParameter("visible");
						boolean _miVisible = false;
						if(_visible.equals("1")){
							_miVisible = true;
						}
						String _valor = (String) request.getParameter("valor");

						_preguntaATrabajar = new Pregunta(admin, micon, _valor);
						_preguntaATrabajar.setTipoPregunta(_tipoPregunta);
						_preguntaATrabajar.setPublico(_miVisible);

						request.setAttribute("preguntaatrabajar", _preguntaATrabajar);

						_preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
						request.setAttribute("preguntas", _preguntas);
						if(!_preguntaATrabajar.getCargadaDeBD()){
							//error creando pregunta... posiblemente el nombre no esta diponible
							request.setAttribute("mensaje","<span style='color:red'>Una pregunta con este nombre ya existe en el sistema.</span>");
							request.setAttribute("preguntaatrabajar", null);
							doGet(request, response);
							return;
						}else{
							request.setAttribute("mensaje","<span style='color:green'>Pregunta creada con &eacute;xito.</span>");
						}
					}else if(_accion.equals("actualizar")){
						idPreguntaTrabajar = Integer.parseInt((String) request.getParameter("preguntaseleccionada"));
						_preguntaATrabajar = new Pregunta(admin, micon, idPreguntaTrabajar);

						int _tipoPregunta = Integer.parseInt((String) request.getParameter("tipopregunta"));
						String _visible = (String) request.getParameter("visible");
						boolean _miVisible = false;
						if(_visible.equals("1")){
							_miVisible = true;
						}
						String _valor = (String) request.getParameter("valor");

						_preguntaATrabajar = new Pregunta(admin, micon, idPreguntaTrabajar);
						_preguntaATrabajar.setPregunta(_valor);
						_preguntaATrabajar.setTipoPregunta(_tipoPregunta);
						_preguntaATrabajar.setPublico(_miVisible);

						request.setAttribute("preguntaatrabajar", _preguntaATrabajar);

						_preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
						request.setAttribute("preguntas", _preguntas);
						if(!_preguntaATrabajar.getCargadaDeBD()){
							//error creando pregunta... posiblemente el nombre no esta diponible
							request.setAttribute("mensaje","<span style='color:red'>Error creando la pregunta. Es probable que el nombre indicado ya exista en el sistema.</span>");
							request.setAttribute("preguntaatrabajar", null);
							doGet(request, response);
							return;
						}else{
							request.setAttribute("mensaje","<span style='color:green'>Pregunta actualizada con &eacute;xito.</span>");
						}
					}else{
						//no hay accion solo se ha seleccionado una pregunta para luego ejecutar la accion
						idPreguntaTrabajar = Integer.parseInt((String) request.getParameter("preguntaseleccionada"));
						_preguntaATrabajar = new Pregunta(admin, micon, idPreguntaTrabajar);
						request.setAttribute("preguntaatrabajar", _preguntaATrabajar);
					}
				}else{
					//no hay accion solo se ha seleccionado una pregunta para luego ejecutar la accion
					idPreguntaTrabajar = Integer.parseInt((String) request.getParameter("preguntaseleccionada"));
					_preguntaATrabajar = new Pregunta(admin, micon, idPreguntaTrabajar);
					request.setAttribute("preguntaatrabajar", _preguntaATrabajar);
				}
				//enviamos a la vista de edicion de respuestas
				view.forward(request, response);
			}catch(Exception e){
				//si algun error ocurrio ubicando las respuestas
				//entonces intento mostrar solo las preguntas.
				try{
					if(admin != null){
						//cargo todas las preguntas editables
						Vector _preguntas = new Vector();
						if((request.getParameter("opcionbase")!=null) && (((String)request.getParameter("opcionbase")).equals("revisar"))){
							_preguntas = Pregunta.todasPreguntas(admin, micon, 0, false, false);
						}else{
							//cargo todas las preguntas editables
							_preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
						}
						request.setAttribute("preguntas", _preguntas);
						view = request.getRequestDispatcher("WEB-INF/vistas/adminpreguntas.jsp");
						view.forward(request, response);
					}else{
						//sesion no iniciada
						view = request.getRequestDispatcher("autenticar.do");
						view.forward(request, response);
					}
				}catch(Exception e2){
					//no se pudieron mostrar las preguntas editables. entonces no se muestra nada
					view = request.getRequestDispatcher("WEB-INF/vistas/adminpreguntas.jsp");
					view.forward(request, response);
				}
			}
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		try{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminpreguntas.jsp");
			Connection micon = (Connection) getServletContext().getAttribute("conexion");
			Administrador admin = (Administrador) sesion.getAttribute("administrador");
			if(admin != null){
				if (request.getParameter("preguntaseleccionada") != null){
					//se ubica la pregunta seleccionada en el formulario.
					int idPreguntaTrabajar = Integer.parseInt((String) request.getParameter("preguntaseleccionada"));
					Pregunta preguntaATrabajar = new Pregunta(admin, micon, idPreguntaTrabajar);

					//verifico si elimino... ingresar y actualizar por method post
					if (request.getParameter("accion") != null) {
						String _accion = (String) request.getParameter("accion");

						if(_accion.equals("eliminar")){
							//tambien se eliminaran las respuestas asociadas.
							request.setAttribute("mensaje", "<span style='color:green'>"+preguntaATrabajar.getPregunta()+" se ha eliminado exitosamente</span>");
							preguntaATrabajar.delPregunta();
							request.setAttribute("opcionprincipal","plantillapreguntas");
							view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
						}
					}
				}
				Vector _preguntas = new Vector();
				if((request.getParameter("opcionbase")!=null) && (((String)request.getParameter("opcionbase")).equals("revisar"))){
					_preguntas = Pregunta.todasPreguntas(admin, micon, 0, false, false);
				}else{
					//cargo todas las preguntas editables
					_preguntas = Pregunta.todasPreguntas(admin, micon, 1, false, true);
				}
				request.setAttribute("preguntas", _preguntas);
				view.forward(request, response);
			}else{
				//sesion no iniciada
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}catch (Exception e){
			//e.printStackTrace();
			//no se pudieron mostrar las preguntas editables. entonces no se muestra nada
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminpreguntas.jsp");
			view.forward(request, response);
		}
	}
}
