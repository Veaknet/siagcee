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
 * Fecha: 29/11/2009
 * Hora: 11:59:45 AM
 */

public class AdministrarObjetos extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos2.jsp");
		if(admin != null){
			try {
				if(request.getParameter("objetoseleccionado") != null){
					Objeto _objetoSeleccionado = null;
					if(request.getParameter("accion") != null){
						if(((String)request.getParameter("accion")).equals("insertar")){
							//Creo el Objeto
							if(Integer.parseInt((String)request.getParameter("tipoobjeto")) == 1){ //Censo
								_objetoSeleccionado = new Censo(admin, micon, (String)request.getParameter("valor"));
							}else if(Integer.parseInt((String)request.getParameter("tipoobjeto")) == 2){
								//Encuesta
								_objetoSeleccionado = new Encuesta(admin, micon, (String)request.getParameter("valor"));
							}else if(Integer.parseInt((String)request.getParameter("tipoobjeto")) == 3){
								//Relacion
								_objetoSeleccionado = new Relacion(admin, micon, (String)request.getParameter("valor"));
								_objetoSeleccionado.setPublico(true);
								InstanciaObjeto _miRelacion = new InstanciaObjeto(admin, micon, (String)request.getParameter("valor"));
								_miRelacion.asociaObjeto(_objetoSeleccionado);
								//las fechas no importan se coloca la de hoy.
								//aunque no importen deben indicarse
								//TODO
								//ingresar un dia previo al actual...
								_miRelacion.setFechaCierre(new java.util.Date());
								_miRelacion.setFechaInicio(new java.util.Date());
								//el tipo de acceso es restringido, sin invitar a ningun usuario.
								_miRelacion.setAcceso(0);
							}else if(Integer.parseInt((String)request.getParameter("tipoobjeto")) == 0){ //estructura base
								_objetoSeleccionado = new EstructuraBase(admin, micon, (String)request.getParameter("valor"));
							}
							String _visible = (String) request.getParameter("visible");
							boolean _miVisible = false;
							if(_visible.equals("1")){
								_miVisible = true;
							}
							_objetoSeleccionado.setPublico(_miVisible);
							if(!_objetoSeleccionado.getCargadaDeBD()){
								//error en su creacion... probablemente el nombre indicado no esta disponible
								request.setAttribute("mensaje", "<span style='color:red'>El nombre indicado no est&aacute; diponible.</span>");
								view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos.jsp");
								doGet(request, response);
							}
						}else{
							//accion no establecida. Se muestra objeto seleccionado
							_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionado")));
						}
					}else{
						//no hay accion solo se muestra el objetoSeleccionado
						_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionado")));
					}

					Vector _preguntasComunes = Pregunta.todasPreguntas(admin, micon, true, false);
					Vector _misPreguntas = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _objetoSeleccionado);

					//solo me interesa el objeto a trabajar y sus preguntas asociadas
					//el restante de objetos disponibles no serán de importancia para adminobjetos2
					request.setAttribute("listadoPreguntasComunes", _preguntasComunes);
					request.setAttribute("listadoPreguntas", _misPreguntas);
					request.setAttribute("objetoatrabajar", _objetoSeleccionado);
					request.setAttribute("listadoEstudios", Estudio.obtenerEstudiosDeEstructura(admin, micon, _objetoSeleccionado, false));
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				e.printStackTrace();
				view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos.jsp");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				int noinstanciadas = 1;
				boolean _visible = false;
				try{
					if((request.getParameter("opcionbase") != null) && ((String)request.getParameter("opcionbase")).equals("revisar")){
						noinstanciadas = 0;
					}
					if(((String)request.getParameter("tipoinstrumento")).equals("relacion")){
						noinstanciadas = 0;
						_visible = true;
					}
				}catch (Exception ee){ee.printStackTrace();}
				Vector _objetos = Objeto.todosObjetos(admin, micon, noinstanciadas, _visible, true);
				request.setAttribute("listadoObjetos", _objetos);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos.jsp");
				view.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
