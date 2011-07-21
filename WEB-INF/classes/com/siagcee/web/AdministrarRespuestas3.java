package com.siagcee.web;

import com.siagcee.logic.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 29/11/2009
 * Hora: 11:59:45 AM
 */

public class AdministrarRespuestas3 extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view;
		if(admin != null){
			try {
				if(request.getParameter("preguntaseleccionada") != null){
					boolean filtrarinstrumento = false;
					InstanciaObjeto _insAct = null;
					Pregunta _preguntaSeleccionada = null;
					_preguntaSeleccionada = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionada")));
					request.setAttribute("preguntaseleccionada", _preguntaSeleccionada);

					Vector _preguntasExternas = new Vector();

					//muestro preguntas disponibles para que seleccione sus respuestas posibles
					if(request.getParameter("filtrarinstrumento") != null && request.getParameter("filtrarinstrumento").equals("verdad")){
						request.setAttribute("filtrarinstrumento", new Boolean(true));
						filtrarinstrumento = true;
						//filtramos las preguntas solo para este instrumento
						if(request.getParameter("instrumentoseleccionado") != null){
							try{
								_insAct = new InstanciaObjeto(admin, micon, Integer.parseInt(request.getParameter("instrumentoseleccionado")));
								if(_insAct.getCargadaDeBD()){
									request.setAttribute("instrumento", _insAct);
									_preguntasExternas = _insAct.getObjetoAsociado().getPreguntas(true);
								}else{
									_preguntasExternas = new Vector();
								}
							}catch (Exception eee){
								_preguntasExternas = new Vector();
							}
						}
					}else{
						_preguntasExternas = Pregunta.todasPreguntas(admin, micon, 0, true, false);
					}
					request.setAttribute("preguntasexternas", _preguntasExternas);

					if(request.getParameter("preguntaseleccionadaexterna") != null){
						if(filtrarinstrumento){
							InstanciaPregunta _preguntaSeleccionadaExterna = new InstanciaPregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionadaexterna")));
							request.setAttribute("preguntaExterna", _preguntaSeleccionadaExterna);

							PreparedStatement pstmt = micon.prepareStatement("SELECT * FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ?");
							pstmt.setInt(1, _insAct.getId());
							pstmt.setInt(2, _preguntaSeleccionadaExterna.getId());
							ResultSet rs = pstmt.executeQuery();
							HashMap _hm = new HashMap();
							while(rs.next()){
								if(_preguntaSeleccionadaExterna.getTipoPregunta() == 30){
									_hm.put(rs.getString("respuesta_string"), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 31){
									NumberFormat format = new DecimalFormat("####.####");
									_hm.put(String.valueOf(format.format(rs.getDouble("respuesta_int"))), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 32){
									NumberFormat format = new DecimalFormat("####.####");
									_hm.put(String.valueOf(format.format(rs.getFloat("respuesta_float"))), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 33){
									SimpleDateFormat _dateFormatVisible = new SimpleDateFormat("dd/MM/yyyy");
									_hm.put(_dateFormatVisible.format(rs.getDate("respuesta_date")), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 100){
									_hm.put(rs.getString("respuesta_string"), true);
								}
							}

							Vector _respuestasExternas = new Vector();
							Iterator _ite = _hm.entrySet().iterator();
							while(_ite.hasNext()){
								Map.Entry _temp = (Map.Entry)_ite.next();
								if(_temp.getKey().equals("")){
									continue;
								}
								_respuestasExternas.add(_temp.getKey());
							}
							request.setAttribute("listadoRespuestasExternas", _respuestasExternas);
						}else{
							Pregunta _preguntaSeleccionadaExterna = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionadaexterna")));
							request.setAttribute("preguntaExterna", _preguntaSeleccionadaExterna);
							Vector _respuestasExternas = _preguntaSeleccionadaExterna.retornaRespuestasPosibles(true);
							request.setAttribute("listadoRespuestasExternas", _respuestasExternas);
						}
					}

					if(request.getParameter("accion") != null){
						if(((String)request.getParameter("accion")).equals("agregarRespuestas")){
							//agrego las respuestas
							//listadoRespuestasDeseadas
							String[] _listadoRespuestasDeseadas = request.getParameterValues("listadoRespuestasDeseadas");
							if(_listadoRespuestasDeseadas != null){
								for(int p = 0; p < _listadoRespuestasDeseadas.length; ++p){
									String _miParam = _listadoRespuestasDeseadas[p];
									RespuestasPosibles _nuevaResp;
									if(filtrarinstrumento){
										//clono la respuesta
										_nuevaResp = new RespuestasPosibles(admin, micon, _miParam);
									}else{
										RespuestasPosibles _miResp = new RespuestasPosibles(admin, micon, Integer.parseInt(_miParam));
										//clono la respuesta
										_nuevaResp = new RespuestasPosibles(admin, micon, _miResp.getRespuesta());
										//asocio a la pregunta
									}
									_preguntaSeleccionada.agregarRespuesta(_nuevaResp);
								}
							}
						}

						Vector _misRespuestas = RespuestasPosibles.obtenerRespuestas(admin, micon, _preguntaSeleccionada);
						request.setAttribute("respuestas", _misRespuestas);
						request.setAttribute("preguntaatrabajar", _preguntaSeleccionada);

						view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas2.jsp");
					}else{
						view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas3.jsp");
					}
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				e.printStackTrace();
				view = request.getRequestDispatcher("adminobjetos.do");
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
				if(request.getParameter("preguntaseleccionada") != null){
					boolean filtrarinstrumento = false;
					InstanciaObjeto _insAct = null;
					Pregunta _preguntaSeleccionada = null;
					_preguntaSeleccionada = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionada")));

					request.setAttribute("preguntaseleccionada", _preguntaSeleccionada);
					Vector _preguntasExternas = new Vector();

					//muestro preguntas disponibles para que seleccione sus respuestas posibles
					if(request.getParameter("filtrarinstrumento") != null && request.getParameter("filtrarinstrumento").equals("verdad")){
						request.setAttribute("filtrarinstrumento", new Boolean(true));
						filtrarinstrumento = true;
						//filtramos las preguntas solo para este instrumento
						if(request.getParameter("instrumentoseleccionado") != null){
							try{
								_insAct = new InstanciaObjeto(admin, micon, Integer.parseInt(request.getParameter("instrumentoseleccionado")));
								if(_insAct.getCargadaDeBD()){
									request.setAttribute("instrumento", _insAct);
									_preguntasExternas = _insAct.getObjetoAsociado().getPreguntas(true);
								}else{
									_preguntasExternas = new Vector();
								}
							}catch (Exception eee){
								_preguntasExternas = new Vector();
							}
						}
					}else{
						_preguntasExternas = Pregunta.todasPreguntas(admin, micon, 0, true, false);
					}
					request.setAttribute("preguntasexternas", _preguntasExternas);

					if(request.getParameter("preguntaseleccionadaexterna") != null){
						if(filtrarinstrumento){
							InstanciaPregunta _preguntaSeleccionadaExterna = new InstanciaPregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionadaexterna")));
							request.setAttribute("preguntaExterna", _preguntaSeleccionadaExterna);

							PreparedStatement pstmt = micon.prepareStatement("SELECT * FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ?");
							pstmt.setInt(1, _insAct.getId());
							pstmt.setInt(2, _preguntaSeleccionadaExterna.getId());
							ResultSet rs = pstmt.executeQuery();
							HashMap _hm = new HashMap();
							while(rs.next()){
								if(_preguntaSeleccionadaExterna.getTipoPregunta() == 30){
									_hm.put(rs.getString("respuesta_string"), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 31){
									NumberFormat format = new DecimalFormat("####.####");
									_hm.put(String.valueOf(format.format(rs.getDouble("respuesta_int"))), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 32){
									_hm.put(String.valueOf(rs.getFloat("respuesta_float")), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 33){
									SimpleDateFormat _dateFormatVisible = new SimpleDateFormat("dd/MM/yyyy");  //formatear la fecha para la visualizacion del usuario
									_hm.put(_dateFormatVisible.format(rs.getDate("respuesta_date")), true);
								}else if(_preguntaSeleccionadaExterna.getTipoPregunta() == 100){
									_hm.put(rs.getString("respuesta_string"), true);
								}
							}

							Vector _respuestasExternas = new Vector();
							Iterator _ite = _hm.entrySet().iterator();
							while(_ite.hasNext()){
								Map.Entry _temp = (Map.Entry)_ite.next();
								if(_temp.getKey().equals("")){
									continue;
								}
								_respuestasExternas.add(_temp.getKey());
							}
							request.setAttribute("listadoRespuestasExternas", _respuestasExternas);
						}else{
							Pregunta _preguntaSeleccionadaExterna = new Pregunta(admin, micon, Integer.parseInt((String)request.getParameter("preguntaseleccionadaexterna")));
							request.setAttribute("preguntaExterna", _preguntaSeleccionadaExterna);
							Vector _respuestasExternas = _preguntaSeleccionadaExterna.retornaRespuestasPosibles(true);
							request.setAttribute("listadoRespuestasExternas", _respuestasExternas);
						}
					}

					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas3.jsp");
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				e.printStackTrace();
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrespuestas.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
