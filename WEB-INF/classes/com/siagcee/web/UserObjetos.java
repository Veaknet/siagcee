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
import java.util.Enumeration;
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 24/11/2009
 * Hora: 01:21:48 PM
 */

public class UserObjetos extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Encuestado encuestado = (Encuestado)sesion.getAttribute("usuario");
		RequestDispatcher view = null;
		if(encuestado != null){
			try{
				view = request.getRequestDispatcher("WEB-INF/vistas/userobjetos.jsp");

				int _seleccionado = Integer.parseInt((String)request.getParameter("id_instrumento"));
				InstanciaObjeto _miIns = new InstanciaObjeto(encuestado, micon, _seleccionado, 1);

				String _accion = "seleccionar";
				if(request.getParameter("accionaejecutar") != null){
					_accion = (String)request.getParameter("accionaejecutar");
				}
				request.setAttribute("seleccionado", _miIns);

				Vector _respuestasEnBD = Respuesta.todasRespuestas(encuestado, micon, null, _miIns);
				request.setAttribute("respuestasDadas", _respuestasEnBD);

				if(_accion.equals("insertar")){
					view = request.getRequestDispatcher("WEB-INF/vistas/userobjetos.jsp");
					Vector _temporalPreguntas = _miIns.getObjetoAsociado().getPreguntas(true);
					Collections.sort(_temporalPreguntas, new OrdenadorInstanciaPreguntas());
					Enumeration _misPreg = _temporalPreguntas.elements();
					InstanciaPregunta miPreg;
					Respuesta miResp;
					Vector vectorResp = new Vector();
					while(_misPreg.hasMoreElements()){
						miPreg = (InstanciaPregunta)_misPreg.nextElement();
						//vectorResp = Respuesta.todasRespuestas(encuestado, micon, miPreg, _miIns);
						//miResp = (Respuesta)vectorResp.elementAt(0);

						miResp = new Respuesta(encuestado, micon);
						miResp.asociarInstanciaObjeto(_miIns);
						miResp.asociarInstanciaPregunta(miPreg);

						if(miPreg.getTipoPregunta() == 1){
						  //seleccion simple
							try{
								miResp.setRespuesta(new RespuestasPosibles(encuestado, micon, Integer.parseInt((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId())))));
							}catch(Exception e){e.printStackTrace();}
						}else if(miPreg.getTipoPregunta() == 2){
							//seleccion multiple
							String[] _listadoRespuestas = request.getParameterValues("pregunta_"+String.valueOf(miPreg.getId()));
							if(_listadoRespuestas != null){
								Respuesta.delRespuestasDePreguntaMultiple(encuestado, micon, miPreg, _miIns);
								for(int p = 0; p < _listadoRespuestas.length; ++p){
									miResp = new Respuesta(encuestado, micon);
									miResp.asociarInstanciaObjeto(_miIns);
									miResp.asociarInstanciaPregunta(miPreg);
									miResp.setRespuesta(new RespuestasPosibles(encuestado, micon, Integer.parseInt(_listadoRespuestas[p])));
								}
							}
						}else if(miPreg.getTipoPregunta() == 30){
							//abierta texto
							try{
                if(((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId()))).equals("")){continue;}
								miResp.setRespuesta((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId())));
							}catch(Exception e){e.printStackTrace();}
						}else if(miPreg.getTipoPregunta() == 31){
							//abierta int
							try{
                if(((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId()))).equals("")){continue;}
								miResp.setRespuesta(Long.parseLong((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId()))));
							}catch(Exception e){e.printStackTrace();}
						}else if(miPreg.getTipoPregunta() == 32){
							//abierta Double
							try{
                if(((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId()))).equals("")){continue;}
								miResp.setRespuesta(Double.parseDouble((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId()))));
							}catch(Exception e){e.printStackTrace();}
						}else if(miPreg.getTipoPregunta() == 33){
							//abierta date
							try{
								if(!(((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId()))).equals(""))){
									SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
									String[] _fechaFormateada = ((String)request.getParameter("pregunta_"+String.valueOf(miPreg.getId()))).split("-");
									miResp.setRespuesta(_temp.parse(_fechaFormateada[2]+"-"+_fechaFormateada[1]+"-"+_fechaFormateada[0]));
								}else{
                  continue;
                }
							}catch(Exception e){e.printStackTrace();}
						}
					}
					request.setAttribute("mensaje", "<span style='color:green'>Gracias por su participaci&oacute;n</span>");
					Vector _soloInsVector = InstanciaObjeto.todosObjetosInstanciados(null, micon, true, 1);
					request.setAttribute("insVector", _soloInsVector);
					request.setAttribute("InstanciaSeleccionadaPorId", null);
					view = request.getRequestDispatcher("WEB-INF/vistas/userform.jsp");
					view.forward(request, response);
					//doGet(request, response);
				}else{
					view.forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticarusuario.do");
				view.forward(request, response);
			}
		}else{
			view = request.getRequestDispatcher("autenticarusuario.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Encuestado encuestado = (Encuestado)sesion.getAttribute("usuario");
		RequestDispatcher view = null;
		if(encuestado != null){
			try{
				String _id_poblacion = "-1";
				if(request.getParameter("id_poblacion") != null){
					_id_poblacion = (String)request.getParameter("id_poblacion");
				}
				request.setAttribute("id_poblacion", _id_poblacion);

				String _campo_clave = "";
				if(request.getParameter("campo_clave") != null){
					_campo_clave = (String)request.getParameter("campo_clave");
				}
				request.setAttribute("campo_clave", _campo_clave);

				int id_poblacional = -1;
				try{
					id_poblacional = Integer.parseInt(_id_poblacion);
				}catch(Exception e){
					e.printStackTrace();
				}

				Vector _insObj = new Vector();
				InstanciaObjeto _tempObj = null;
				Enumeration _enu = InstanciaObjeto.todosObjetosInstanciados(encuestado, micon, true, 1).elements();
				while(_enu.hasMoreElements()){
					_tempObj = (InstanciaObjeto)_enu.nextElement();
					if(!_tempObj.getObjetoAsociado().getClass().toString().contains("Censo")){
						if(_tempObj.getPoblacion_asociada().getId() == id_poblacional){
							_insObj.add(_tempObj);
						}
					}else{
						if(_tempObj.getId() == id_poblacional){
							_insObj.add(_tempObj);
						}
					}
				}

				Vector _insObjFinal = new Vector();
				InstanciaObjeto _miObj;

				Enumeration _misObjetos = _insObj.elements();
				while(_misObjetos.hasMoreElements()){
					_miObj = (InstanciaObjeto)_misObjetos.nextElement();
					AccesosEncuestados _ae = new AccesosEncuestados(micon, encuestado, _miObj);

					//compruebo el acceso al instrumento para este usuario
					//si es publico ó es privado y tiene acceso
					if((_miObj.getAcceso() == 0) || ((_miObj.getAcceso() == 1) && (_ae.compruebaAcceso()))){
						_insObjFinal.addElement(_miObj);
					}
				}

				request.setAttribute("objetos", _insObjFinal);
				view = request.getRequestDispatcher("WEB-INF/vistas/userobjetos.jsp");
			}catch(Exception e){
				view = request.getRequestDispatcher("autenticarusuario.do");
			}
			view.forward(request, response);
		}else{
			view = request.getRequestDispatcher("autenticarusuario.do");
			view.forward(request, response);
		}
	}
}
