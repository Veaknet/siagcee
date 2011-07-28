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
import java.util.Enumeration;
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

public class AdministrarObjetos2 extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				if(request.getParameter("objetoseleccionado") != null){
					Objeto _objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionado")));

					if(request.getParameter("accionobjeto") != null){
						if(((String)request.getParameter("accionobjeto")).equals("actualizarobjeto")){

							//solo modifico nombre cuando es distinto al actual.
							if(!_objetoSeleccionado.getObjeto().equals((String)request.getParameter("valorobjeto"))){
								//cambio nombre
								_objetoSeleccionado.setObjeto((String)request.getParameter("valorobjeto"));
								//recargo para comprobar si se guardo en BD
								_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, _objetoSeleccionado.getId());
								//siguen siendo distintos entonces ese nombre no esta disponible
								if(!_objetoSeleccionado.getObjeto().equals((String)request.getParameter("valorobjeto"))){
									request.setAttribute("mensaje","<span style='color:red'>Nombre indicado ya existe. No ha podido realizarse el cambio.</span>");
								}
							}

							String _visible = (String) request.getParameter("visible");
							boolean _miVisible = false;
							if(_visible.equals("1")){
								_miVisible = true;
							}
							_objetoSeleccionado.setPublico(_miVisible);
							Objeto _miTemp = _objetoSeleccionado.setTipo(Integer.parseInt((String)request.getParameter("tipoobjeto")));
							if(_miTemp != null){
								_objetoSeleccionado = _miTemp;
								if((_objetoSeleccionado.getClass().toString().contains("Censo")) && (_objetoSeleccionado.retornaPreguntaClave(true) == null)){
									Vector _preg = _objetoSeleccionado.getPreguntas();
									if(!_preg.isEmpty()){
										((InstanciaPregunta)_preg.firstElement()).setCampo_clave_unico(true);
									}
								}
							}
						}
					}
					if(request.getParameter("accion") != null){
						InstanciaPregunta _nuevaInstancia = null;
						if(((String)request.getParameter("accion")).equals("actualizar")){
							String _txtPregunta = (String)request.getParameter("valor");
							String _acronimo = "";
							if(request.getParameter("acronimo") != null && !((String)request.getParameter("acronimo")).equals("")){
								_acronimo = (String)request.getParameter("acronimo");
							}else{
								_acronimo = _txtPregunta;
							}
							int _preguntaSeleccionada = Integer.parseInt((String)request.getParameter("preguntaseleccionada"));
							int _asociaPregunta;
							try{
								_asociaPregunta = Integer.parseInt((String)request.getParameter("tipopregunta"));
							}catch(Exception e){_asociaPregunta = -1;}
							int _orden;
							try{
								_orden = Integer.parseInt((String)request.getParameter("orden"));
							}catch(Exception e){_orden = 0;}

							Pregunta _preguntaAAsociar = new Pregunta(admin, micon, _asociaPregunta);

							_nuevaInstancia = new InstanciaPregunta(admin, micon, _preguntaSeleccionada);

							EstudioPerso estudioAAsociar = null;
							try{
								//estudioAAsociar = new EstudioPerso(admin, micon, Integer.parseInt((String)request.getParameter("tipoestudio")));
								estudioAAsociar = null;
							}catch(Exception ee){
								//ee.printStackTrace();
								estudioAAsociar = null;
							}
							_nuevaInstancia.setPadre(_objetoSeleccionado);
							if(_preguntaAAsociar != null && _preguntaAAsociar.getId() != -1){
								_nuevaInstancia.asociarPregunta(_preguntaAAsociar);
							}
							_nuevaInstancia.setAcronimo(_acronimo);
							_nuevaInstancia.setTextoPregunta(_txtPregunta);
							_nuevaInstancia.setOrden(_orden);
							_nuevaInstancia.setEstudioAsociado(estudioAAsociar);
							_objetoSeleccionado.ajustaNumeracion();
						}
						if(((String)request.getParameter("accion")).equals("insertar")){
							String _txtPregunta = (String)request.getParameter("valor");
							String _acronimo = "";
							if(request.getParameter("acronimo") != null && !((String)request.getParameter("acronimo")).equals("")){
								_acronimo = (String)request.getParameter("acronimo");
							}else{
								_acronimo = _txtPregunta;
							}
							int _asociaPregunta;
							try{
								_asociaPregunta = Integer.parseInt((String)request.getParameter("tipopregunta"));
							}catch(Exception e){_asociaPregunta = -1;}
							int _orden;
							try{
								_orden = Integer.parseInt((String)request.getParameter("orden"));
							}catch(Exception e){_orden = 0;}

							EstudioPerso estudioAAsociar = null;

							Pregunta _preguntaAAsociar = new Pregunta(admin, micon, _asociaPregunta);
							_nuevaInstancia = new InstanciaPregunta(admin, micon, _txtPregunta, _preguntaAAsociar, _objetoSeleccionado);
							_nuevaInstancia.setAcronimo(_acronimo);
							_nuevaInstancia.setOrden(_orden);
							_nuevaInstancia.asociarPregunta(_preguntaAAsociar);
							_nuevaInstancia.setEstudioAsociado(estudioAAsociar);
							_objetoSeleccionado.ajustaNumeracion();
						}
						//ahora indico si la pregunta es de tipo clave, o demás
						InstanciaPregunta _temporalPreg = null;
						if(request.getParameter("campo_clave") != null){
							_temporalPreg = _nuevaInstancia.getPadre().retornaPreguntaClave(true);
							if(_temporalPreg != null){
								_temporalPreg.setCampo_clave_unico(false);
							}
							_nuevaInstancia.setCampo_clave_unico(true);
						}else{
							_nuevaInstancia.setCampo_clave_unico(false);
						}
						if(request.getParameter("campo_email") != null){
							_temporalPreg = _nuevaInstancia.getPadre().retornaPreguntaComunicacionEmail(true);
							if(_temporalPreg != null){
								_temporalPreg.setCampo_comunicacion_email(false);
							}
							_nuevaInstancia.setCampo_comunicacion_email(true);
						}else{
							_nuevaInstancia.setCampo_comunicacion_email(false);
						}
						if(request.getParameter("campo_identificador") != null){
							_nuevaInstancia.setCampo_identificador(true);
							_temporalPreg = _nuevaInstancia.getPadre().retornaPreguntaIdentificador(true);
							_temporalPreg.setCampo_identificador(false);
						}else{
							_nuevaInstancia.setCampo_identificador(false);
						}
						if(request.getParameter("campo_telefono") != null){
							_nuevaInstancia.setCampo_comunicacion_telefono(true);
							_temporalPreg = _nuevaInstancia.getPadre().retornaPreguntaComunicacionTelefono(true);
							_temporalPreg.setCampo_comunicacion_telefono(false);
						}else{
							_nuevaInstancia.setCampo_comunicacion_telefono(false);
						}
						if(request.getParameter("campo_telefono2") != null){
							_nuevaInstancia.setCampo_comunicacion_telefono2(true);
							_temporalPreg = _nuevaInstancia.getPadre().retornaPreguntaComunicacionTelefono2(true);
							_temporalPreg.setCampo_comunicacion_telefono2(false);
						}else{
							_nuevaInstancia.setCampo_comunicacion_telefono2(false);
						}
					}


					Vector _preguntasComunes = Pregunta.todasPreguntas(admin, micon, true, false);
					Vector _misPreguntas = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _objetoSeleccionado, true);

					request.setAttribute("listadoPreguntas", _misPreguntas);
					request.setAttribute("listadoPreguntasComunes", _preguntasComunes);
					request.setAttribute("objetoatrabajar", _objetoSeleccionado);
					Vector _objetos = Objeto.todosObjetos(admin, micon, 1, false, true);
					request.setAttribute("listadoObjetos", _objetos);
					request.setAttribute("listadoEstudios", EstudioPerso.obtenerEstudiosDeEstructura(admin, micon, _objetoSeleccionado, false));
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos2.jsp");
					view.forward(request, response);
				}else{
					Vector _objetos = Objeto.todosObjetos(admin, micon, 1, false, true);
					request.setAttribute("listadoObjetos", _objetos);
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos.jsp");
					view.forward(request, response);
				}
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				RequestDispatcher view = request.getRequestDispatcher("adminobjetos.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
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
				try{
					if((request.getParameter("opcionbase") != null) && ((String)request.getParameter("opcionbase")).equals("revisar")){
						noinstanciadas = 0;
					}
				}catch (Exception ee){
					//ee.printStackTrace();
				}
				if(request.getParameter("objetoseleccionado") != null){
					Objeto _objetoSeleccionado = null;
					try{
						_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionado")));
					}catch (Exception ee){
						//ee.printStackTrace();
					}
					if(request.getParameter("accionobjeto") != null){
						if(((String)request.getParameter("accionobjeto")).equals("eliminar")){
							if((request.getParameter("tipoinstrumento") != null) && request.getParameter("tipoinstrumento").equals("relacion")){
								//necesito la instancia única de la relación
								Enumeration _enuthis = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0).elements();
								InstanciaObjeto _instanciaDeRelacion = null;
								while(_enuthis.hasMoreElements()){
									_instanciaDeRelacion = (InstanciaObjeto)_enuthis.nextElement();
									if(_instanciaDeRelacion.getObjetoAsociado().getId() == _objetoSeleccionado.getId()){
										_instanciaDeRelacion.getObjetoAsociado().delObjeto(true);
										_instanciaDeRelacion.delInstanciaObjeto(true);
										break;
									}
								}
							}
							request.setAttribute("mensaje", "<span style='color:green'>"+_objetoSeleccionado.getObjeto()+" se ha eliminado exitosamente</span>");
							_objetoSeleccionado.delObjeto(true);
						}
						if(((String)request.getParameter("accionobjeto")).equals("crear")){
							if(request.getParameter("relacionid") != null){
								InstanciaObjeto _miIns = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("relacionid")));
								_miIns.delInstanciaObjeto(true);
							}
							_objetoSeleccionado.setPublico(true);
							request.setAttribute("mensaje", "<span style='color:green'>"+_objetoSeleccionado.getObjeto()+" se ha generado exitosamente</span>");
						}
						Vector _objetos = Objeto.todosObjetos(admin, micon, noinstanciadas, false, true);
						request.setAttribute("listadoObjetos", _objetos);
						RequestDispatcher view = null;
						view = request.getRequestDispatcher("WEB-INF/vistas/adminindex.jsp");
						view.forward(request, response);
					}else{
						if(request.getParameter("accion") != null){
							if(((String)request.getParameter("accion")).equals("eliminar")){
								int _preguntaSeleccionada = Integer.parseInt((String)request.getParameter("preguntaseleccionada"));
								InstanciaPregunta _nuevaInstancia = new InstanciaPregunta(admin, micon, _preguntaSeleccionada);
								if(request.getParameter("relacionid") != null){
									InstanciaObjeto _miIns = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("relacionid")));
									Respuesta.delRespuesta(micon, _miIns, _nuevaInstancia);
								}
								if((request.getParameter("tipoinstrumento") != null) && request.getParameter("tipoinstrumento").equals("relacion")){
									//necesito la instancia única de la relación
									Enumeration _enuthis = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0).elements();
									InstanciaObjeto _instanciaDeRelacion = null;
									while(_enuthis.hasMoreElements()){
										_instanciaDeRelacion = (InstanciaObjeto)_enuthis.nextElement();
										if(_instanciaDeRelacion.getObjetoAsociado().getId() == _objetoSeleccionado.getId()){
											Respuesta.delRespuesta(micon, _instanciaDeRelacion, _nuevaInstancia);
											break;
										}
									}
								}
								_nuevaInstancia.delPregunta();
								_objetoSeleccionado.ajustaNumeracion();
							}
						}

						//no hay accion solo se muestra el objetoSeleccionado
						_objetoSeleccionado = Objeto.retornaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoseleccionado")));
						Vector _preguntasComunes = Pregunta.todasPreguntas(admin, micon, true, false);
						Vector _misPreguntas = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _objetoSeleccionado, true);

						request.setAttribute("listadoPreguntas", _misPreguntas);
						request.setAttribute("listadoPreguntasComunes", _preguntasComunes);
						request.setAttribute("objetoatrabajar", _objetoSeleccionado);
						request.setAttribute("listadoEstudios", EstudioPerso.obtenerEstudiosDeEstructura(admin, micon, _objetoSeleccionado, false));
						RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos2.jsp");
						view.forward(request, response);
					}
				}else{
					Vector _objetos = Objeto.todosObjetos(admin, micon, noinstanciadas, false, true);
					request.setAttribute("listadoObjetos", _objetos);
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminobjetos.jsp");
					view.forward(request, response);
				}
			}catch(Exception e){
				//e.printStackTrace();
				//error voy a pantalla principal de objetos
				RequestDispatcher view = request.getRequestDispatcher("adminobjetos.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
