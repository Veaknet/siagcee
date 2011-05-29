package com.siagcee.web;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 03/12/2009
 * Hora: 02:06:58 PM
 */

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
import java.util.Vector;

public class AutenticarUsuario extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		String mensaje = "";
		RequestDispatcher view;
		view = request.getRequestDispatcher("WEB-INF/vistas/userform.jsp");
		request.setAttribute("poblacion", null);
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		try {
			if(request.getParameter("id_instrumento") != null){

				request.setAttribute("id_instrumento", (String)request.getParameter("id_instrumento"));

				Encuestado _yo = null;
				if(request.getParameter("campo_clave") == null){
					HttpSession sesion = request.getSession();
					sesion.setMaxInactiveInterval(999999);
					_yo = new Encuestado(micon, (String)request.getParameter("id_instrumento"), "");
					sesion.setAttribute("usuario", _yo);
				}else{
					HttpSession sesion = request.getSession();
					sesion.setMaxInactiveInterval(999999);
					_yo = new Encuestado(micon, (String)request.getParameter("id_instrumento"), (String)request.getParameter("campo_clave"));
					sesion.setAttribute("usuario", _yo);
				}

				InstanciaObjeto _miObjeto = new InstanciaObjeto(_yo, micon, Integer.parseInt((String)request.getParameter("id_instrumento")));

				request.setAttribute("objetoatrabajar", _miObjeto);
				request.setAttribute("preguntasTotales", InstanciaPregunta.todasPreguntasInstanciadas(new Administrador(), micon, _miObjeto.getObjetoAsociado()));
				request.setAttribute("preguntasEditables",PreguntaEditable.retornaTodasEditables(new Administrador(), micon, _miObjeto));

				if((_miObjeto.getObjetoAsociado().retornaPreguntaClave(true) != null) && (request.getParameter("campo_clave") == null)){
					request.setAttribute("InstanciaSeleccionadaPorId", _miObjeto);
					request.setAttribute("mensaje", "<span style='color:red'>No pudo ser autenticado para acceder a este instrumento.</span>");
					view = request.getRequestDispatcher("WEB-INF/vistas/userform.jsp");
				}else{
					//valido que este autorizado para entrar
					AccesosEncuestados _ae = new AccesosEncuestados(micon, _yo, _miObjeto);
					if(_miObjeto.getAcceso() == 1 && !_ae.compruebaAcceso()){
						//no tiene acceso a este instrumento
						request.setAttribute("InstanciaSeleccionadaPorId", _miObjeto);
						_yo.delUsuario();
						request.setAttribute("mensaje", "<span style='color:red'>No tiene permisos para acceder a este instrumento.</span>");
						view = request.getRequestDispatcher("WEB-INF/vistas/userform.jsp");
					}else{
						if(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true) != null){
							//guardo respuesta
							Respuesta miResp = new Respuesta(_yo, micon);
							miResp.asociarInstanciaObjeto(_miObjeto);
							miResp.asociarInstanciaPregunta(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true));
							if(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true).getTipoPregunta() == 1){
								//seleccion simple
								try{
									miResp.setRespuesta(new RespuestasPosibles(_yo, micon, Integer.parseInt((String)request.getParameter("campo_clave"))));
								}catch(Exception e){e.printStackTrace();}
							}else if(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true).getTipoPregunta() == 2){
								//seleccion multiple
								String[] _listadoRespuestas = request.getParameterValues(request.getParameter("campo_clave"));
								if(_listadoRespuestas != null){
									Respuesta.delRespuestasDePreguntaMultiple(_yo, micon, _miObjeto.getObjetoAsociado().retornaPreguntaClave(true), _miObjeto);
									for(int p = 0; p < _listadoRespuestas.length; ++p){
										miResp = new Respuesta(_yo, micon);
										miResp.asociarInstanciaObjeto(_miObjeto);
										miResp.asociarInstanciaPregunta(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true));
										miResp.setRespuesta(new RespuestasPosibles(_yo, micon, Integer.parseInt(_listadoRespuestas[p])));
									}
								}
							}else if(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true).getTipoPregunta() == 30){
								//abierta texto
								try{
									miResp.setRespuesta((String)request.getParameter(request.getParameter("campo_clave")));
								}catch(Exception e){e.printStackTrace();}
							}else if(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true).getTipoPregunta() == 31){
								//abierta int
								try{
									miResp.setRespuesta(Long.parseLong(request.getParameter("campo_clave")));
								}catch(Exception e){e.printStackTrace();}
							}else if(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true).getTipoPregunta() == 32){
								//abierta Double
								try{
									miResp.setRespuesta(Double.parseDouble((String)request.getParameter(request.getParameter("campo_clave"))));
								}catch(Exception e){e.printStackTrace();}
							}else if(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true).getTipoPregunta() == 33){
								//abierta date
								try{
									if(!(((String)request.getParameter("pregunta_"+String.valueOf(_miObjeto.getObjetoAsociado().retornaPreguntaClave(true).getId()))).equals(""))){
										SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
										miResp.setRespuesta(_temp.parse(request.getParameter("campo_clave")));
									}
								}catch(Exception e){e.printStackTrace();}
							}
							view = request.getRequestDispatcher("WEB-INF/vistas/userobjetos.jsp");

							int _seleccionado = Integer.parseInt((String)request.getParameter("id_instrumento"));
							InstanciaObjeto _miIns = new InstanciaObjeto(_yo, micon, _seleccionado, 1);

							request.setAttribute("seleccionado", _miIns);

							Vector _respuestasEnBD = Respuesta.todasRespuestas(_yo, micon, null, _miIns);
							request.setAttribute("respuestasDadas", _respuestasEnBD);
						}else{
							view = request.getRequestDispatcher("WEB-INF/vistas/userobjetos.jsp");

							int _seleccionado = Integer.parseInt((String)request.getParameter("id_instrumento"));
							InstanciaObjeto _miIns = new InstanciaObjeto(_yo, micon, _seleccionado, 1);

							request.setAttribute("seleccionado", _miIns);

							Vector _respuestasEnBD = Respuesta.todasRespuestas(_yo, micon, null, _miIns);
							request.setAttribute("respuestasDadas", _respuestasEnBD);
						}
					}
				}
				view.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			Vector _soloInsVector = InstanciaObjeto.todosObjetosInstanciados(null, micon, true, 1);
			request.setAttribute("insVector", _soloInsVector);
			request.setAttribute("InstanciaSeleccionadaPorId", null);
			request.setAttribute("mensaje", "<span style='color:red'>Error no reconocible</span>");
			view = request.getRequestDispatcher("WEB-INF/vistas/userform.jsp");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		RequestDispatcher view;
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		request.setAttribute("insVector", null);
		InstanciaObjeto _miObjeto = null;

		if(request.getParameter("identificador_publico") != null){
			int _id = InstanciaObjeto.cargaInsObj(request.getParameter("identificador_publico"), micon);
			if(_id > -1){
				if(request.getParameter("campo_clave") == null){
					_miObjeto = new InstanciaObjeto(null, micon, _id);
				}else{
					_miObjeto = null;
				}
			}else{
				_miObjeto = null;
			}
		}else{
			_miObjeto = null;
			Vector _soloInsVector = InstanciaObjeto.todosObjetosInstanciados(null, micon, true, 1);
			request.setAttribute("insVector", _soloInsVector);
		}
		request.setAttribute("InstanciaSeleccionadaPorId", _miObjeto);
		view = request.getRequestDispatcher("WEB-INF/vistas/userform.jsp");
		view.forward(request, response);
	}
}