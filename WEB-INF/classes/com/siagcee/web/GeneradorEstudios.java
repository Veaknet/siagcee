package com.siagcee.web;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 12/01/2010
 * Hora: 06:26:13 PM
 */

import com.siagcee.logic.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import java.util.Enumeration;


public class GeneradorEstudios extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/generadorestudios.jsp");
		if(admin != null){
			String accion = "";
			request.setAttribute("_todas_preguntas_estaticas", Pregunta.todasPreguntas(admin, micon, 0, true, false));
			try{
				Objeto _objetoSeleccionado = null;
				if(request.getParameter("objetoatrabajar") != null){
					_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					Vector _listaTiposDeDatos = Pregunta.preguntasDeObjeto(admin, micon, _objetoSeleccionado);

					request.setAttribute("listaTipoDeDatos", _listaTiposDeDatos);
					request.setAttribute("objetoatrabajar", _objetoSeleccionado);
				}else{
					request.setAttribute("listaTipoDeDatos", null);
					request.setAttribute("objetoatrabajar", null);
				}
				
				if(request.getParameter("accion") != null){
					accion = (String)request.getParameter("accion");
				}

				if(accion.equals("compilar")){
					int idcodigo = -1;
					if(request.getParameter("idcodigo") != null){
						try{
							idcodigo = Integer.parseInt((String)request.getParameter("idcodigo"));
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					Estudio _miEstudio = null;
					if(idcodigo != -1){
						//cargo el viejo para reemplazarlo
						//para este momento nunca cargo uno viejo para reemplazarlo...
						_miEstudio = new Estudio(admin, micon, idcodigo);
					}else{
						_miEstudio = new Estudio(admin, micon);						
						_miEstudio.setObjeto(_objetoSeleccionado);
					}
					//compilo
					_miEstudio.setTitulo((String)request.getParameter("titulo_estudio"));
					//valido que el nombre este disponible
					if(_miEstudio.tituloDisponible()){
						_miEstudio.setDescripcion((String)request.getParameter("descripcion_estudio"));
						_miEstudio.setCodigo((String)request.getParameter("codigo"));
						//valido
						if(_miEstudio.isCodigoValidado()){
							//Válido
							request.setAttribute("compilado",true);
							request.setAttribute("ValidadaCompilacion",true);
						}else{
							//Error
							request.setAttribute("compilado",true);
							request.setAttribute("ValidadaCompilacion",false);
							request.setAttribute("listadoErrores",_miEstudio.getError());
						}
					}else{
						request.setAttribute("compilado",true);
						request.setAttribute("ValidadaCompilacion",false);
						Vector _ttt = new Vector();
						_ttt.add("T&iacute;tulo indicado para el estudio no esta disponible...");
						request.setAttribute("listadoErrores",_ttt);
					}
					
					Enumeration _enuPreg = _objetoSeleccionado.getPreguntas().elements();
					boolean encontrada = false;
					while(_enuPreg.hasMoreElements()){
						InstanciaPregunta _insPregTemp = (InstanciaPregunta)_enuPreg.nextElement();
						if((_insPregTemp.getEstudioAsociado() != null) && (_insPregTemp.getTipoPregunta() == 100) && (_miEstudio.getId() == _insPregTemp.getEstudioAsociado().getId())){
							//encontrada pregunta para estudio... no hago nada
							encontrada = true;
							break;
						}
					}
					if(!encontrada){
						//por defecto se asume que el id de pool_preguntas para tipo estudio es 1
						Pregunta _poolPregunta = new Pregunta(admin, micon, 1);
						InstanciaPregunta _nuevaInstancia = new InstanciaPregunta(admin, micon, "Estudio: "+_miEstudio.getTitulo(), _poolPregunta, null);
						_nuevaInstancia.setOrden(9000);
						_nuevaInstancia.setEstudioAsociado(_miEstudio);
						_nuevaInstancia.setPadre(_objetoSeleccionado);
					}
					//termino
					view = request.getRequestDispatcher("WEB-INF/vistas/generadorestudios.jsp");
				}else if(accion.equals("seleccionarestudio")){
					Estudio _miEstudio = null;
					if(request.getParameter("idestudio") != null){
						_miEstudio = new Estudio(admin, micon, Integer.valueOf((String)request.getParameter("idestudio")));
					}
					request.setAttribute("estudioACargar", _miEstudio);
					view = request.getRequestDispatcher("WEB-INF/vistas/generadorestudios.jsp");
				}
				if((request.getParameter("opcionbase") != null) && request.getParameter("opcionbase").equals("aplicar")){
					view = request.getRequestDispatcher("aplicadorestudios?objetoatrabajar="+_objetoSeleccionado.getId());
				}
				view.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
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
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/generadorestudios.jsp");
		String _miaccion = "";
		Objeto _objetoSeleccionado = null;
		if(admin != null){
			request.setAttribute("_todas_preguntas_estaticas", Pregunta.todasPreguntas(admin, micon, 0, true, false));
			try{
				if(request.getParameter("accion") != null){
					_miaccion = (String)request.getParameter("accion");
				}
				if(_miaccion.equals("delete")){
					if(request.getParameter("idestudio") != null){
						Integer _idestudio = Integer.parseInt((String)request.getParameter("idestudio"));
						Estudio _estudio = new Estudio(admin, micon, _idestudio);
						_estudio.delEstudio();
					}
					//_miaccion = "listarestudios";
					request.getRequestDispatcher("WEB-INF/vistas/indexadminmenu.jsp");
				}
				request.setAttribute("listaTipoDeDatos", null);
				request.setAttribute("objetoatrabajar", null);
				try{
					_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					Vector _listaTiposDeDatos = Pregunta.preguntasDeObjeto(admin, micon, _objetoSeleccionado);
					request.setAttribute("listaTipoDeDatos", _listaTiposDeDatos);
					request.setAttribute("objetoatrabajar", _objetoSeleccionado);
				}catch (Exception ee){ee.printStackTrace();}
				if(_miaccion.equals("listarestudios")){
					request.setAttribute("listadoEstudios", Estudio.obtenerEstudiosDeEstructura(admin, micon, _objetoSeleccionado, true));
					view = request.getRequestDispatcher("WEB-INF/vistas/listarestudios.jsp");
				}
			}catch(Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
			}finally {
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
	
}

