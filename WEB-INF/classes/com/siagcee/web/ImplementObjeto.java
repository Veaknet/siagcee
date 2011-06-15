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

public class ImplementObjeto extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/implementobjeto.jsp");

				String _sqlFinal = "";
				if ((request.getAttribute("generadorSql_sqlFinal") != null) && (!((String)request.getAttribute("generadorSql_sqlFinal")).equals(""))) {
					_sqlFinal = (String)request.getAttribute("generadorSql_sqlFinal");
					//proceso el Sql para invitar a las personas indicadas
					//solo lo procesa el Get, si por casualidad entra por post se reenvia
					this.doGet(request, response);
					//despues de procesar limpio la sesion
					sesion.setAttribute("generadorSql_cancelarAndGoTo", "");
					sesion.setAttribute("generadorSql_finalizarAndGoTo", "");
					InstanciaObjeto _miIns_1 = (InstanciaObjeto) sesion.getAttribute("generadorSql_instanciaObjeto");
					request.setAttribute("highlight", _miIns_1.getId());
					view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
				}else{
					if (request.getParameter("accion") != null){
						String _miObjeto = "";
						if (request.getParameter("objeto") != null) {
							_miObjeto = (String) request.getParameter("objeto");
						}

						Objeto _miObjetoAsociado = null;
						if (request.getParameter("objeto_asociado") != null){
							int _id = Integer.parseInt((String) request.getParameter("objeto_asociado"));
							_miObjetoAsociado = Objeto.retornaObjeto(admin, micon, _id);
						}

						int _tipoAcceso = 0;
						if (request.getParameter("tipo_acceso") != null) {
							_tipoAcceso = Integer.parseInt((String) request.getParameter("tipo_acceso"));
						}

						int _tipoInvitacion = 0;
						if (request.getParameter("tipo_invitacion") != null){
							_tipoInvitacion = Integer.parseInt((String) request.getParameter("tipo_invitacion"));
						}

						String _enlace = "";
						if (request.getParameter("enlace") != null){
							_enlace = (String)request.getParameter("enlace");
						}

						String _accion = (String)request.getParameter("accion");
						InstanciaObjeto _miIns = null;
						if (_accion.equals("insertar")){
							String[] _resltados;
							_miIns = new InstanciaObjeto(admin, micon, _miObjeto);
							_miIns.asociaObjeto(_miObjetoAsociado);

							if((_miIns.getObjetoAsociado().retornaPreguntaClave(true) != null) && (_miIns.getObjetoAsociado().retornaPreguntaClave(true).getCargadaDeBD())){
								//existe pregunta clave...
								_miIns.setAcceso(_tipoAcceso);
							}else{
								//no existe pregunta clave... la unica opcion de acceso es publica
								_miIns.setAcceso(0);
							}
							String _mifecha = (String)request.getParameter("cierre");
							if(_mifecha == null || _mifecha.equals("")){
								_miIns.setFechaCierre("");
							}else{
								_resltados = _mifecha.split("-");
								_miIns.setFechaCierre(_resltados[2]+"-"+_resltados[1]+"-"+_resltados[0]);
							}
							_mifecha = (String)request.getParameter("inicio");
							if(_mifecha == null || _mifecha.equals("")){
								_miIns.setFechaInicio("");
							}else{
								_resltados = _mifecha.split("-");
								_miIns.setFechaInicio(_resltados[2]+"-"+_resltados[1]+"-"+_resltados[0]);
							}

							if(_miIns.getCargadaDeBD()){
								if(_tipoInvitacion == 1){
									//datos necesarios para que generadorSQL pueda regresar la data al servlet indicado
									sesion.setAttribute("generadorSql_cancelarAndGoTo", "implementobjeto.do");
									sesion.setAttribute("generadorSql_finalizarAndGoTo", "implementobjeto.do");

									sesion.setAttribute("generadorSql_instanciaObjeto", _miIns);
									view = request.getRequestDispatcher("generadorsql.do");
								}else if(_tipoInvitacion == 2){
									//correo cargados manualmente
									request.setAttribute("highlight", _miIns.getId());
									request.setAttribute("objeto", _miIns);
									view = request.getRequestDispatcher("WEB-INF/vistas/correos_a_invitar.jsp");
                                }else if(_tipoInvitacion == 4){
                                    //carga desde excel
									request.setAttribute("idobjetodestino", String.valueOf(_miIns.getId()));
									view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");
								}else{
                                    //no invitar
									request.setAttribute("highlight", _miIns.getId());
									view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
                                }
							}
							_miIns.setIdPublico(_enlace);

							//agrego todas las preguntas a editables
							PreguntaEditable.delPreguntasEditables(admin, micon, _miIns);
							PreguntaEditable _pregEdit = null;
							Vector _todasMisPreguntas = InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _miIns.getObjetoAsociado(), true);
							Enumeration _ueP = _todasMisPreguntas.elements();
							InstanciaPregunta _instPregn = null;
							while(_ueP.hasMoreElements()){
								try{
									_instPregn = (InstanciaPregunta)_ueP.nextElement();
									if(_instPregn.getTipoPregunta() == 100){
										continue;
									}
									_pregEdit = new PreguntaEditable(admin, micon);
									_pregEdit.set_InsObjeto(_miIns);
									_pregEdit.set_InsPregunta(_instPregn);
								}catch(Exception ee3){}
							}
						}else if (_accion.equals("actualizar")){
							int _id = Integer.parseInt((String) request.getParameter("id"));
							_miIns = new InstanciaObjeto(admin, micon, _id);
							String[] _resltados;
							String _mifecha = (String)request.getParameter("cierre");
							if(_mifecha.equals("")){
								_miIns.setFechaCierre("");
							}else{
								_resltados = _mifecha.split("-");
								_miIns.setFechaCierre(_resltados[2]+"-"+_resltados[1]+"-"+_resltados[0]);
							}
							_mifecha = (String)request.getParameter("inicio");
							if(_mifecha.equals("")){
								_miIns.setFechaInicio("");
							}else{
								_resltados = _mifecha.split("-");
								_miIns.setFechaInicio(_resltados[2]+"-"+_resltados[1]+"-"+_resltados[0]);
							}
							_miIns.setIdPublico(_enlace);

							//_miIns.asociaObjeto(_miObjetoAsociado);
							request.setAttribute("highlight", _miIns.getId());

							//agrego todas las preguntas a editables
							InstanciaObjeto _obj = _miIns;
							request.setAttribute("objetoatrabajar", _obj);
							request.setAttribute("preguntasTotales", InstanciaPregunta.todasPreguntasInstanciadas(admin, micon, _obj.getObjetoAsociado(), true));
							request.setAttribute("preguntasEditables",PreguntaEditable.retornaTodasEditables(admin, micon, _obj));
							view = request.getRequestDispatcher("WEB-INF/vistas/seteditables.jsp");
						}
						if(request.getParameter("poblacion") != null){
							InstanciaObjeto _poblacionAsociada = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("poblacion")));
							if(_poblacionAsociada.getCargadaDeBD()){
								_miIns.setPoblacion_asociada(_poblacionAsociada);
							}else{
								_miIns.setPoblacion_asociada(null);
							}
						}else{
							_miIns.setPoblacion_asociada(null);
						}
					}
				}

				Vector _objetos = Objeto.todosObjetos(admin, micon, 0, true, false);
				Vector _instanciados;
				if(admin.getTipoUsuario().equals("superadministrador")){
					_instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0);
				}else{
					_instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0);
				}

				request.setAttribute("objetosDisponibles", _objetos);
				request.setAttribute("objetosInstanciados", _instanciados);

				view.forward(request, response);
			}
			catch (Exception e) {
				//e.printStackTrace();
				Vector _objetos = Objeto.todosObjetos(admin, micon, 0, true, false);
				Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon);

				request.setAttribute("objetosDisponibles", _objetos);
				request.setAttribute("objetosInstanciados", _instanciados);

				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/implementobjeto.jsp");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				String _sqlFinal = "";
				if ((request.getAttribute("generadorSql_sqlFinal") != null) && (!((String)request.getAttribute("generadorSql_sqlFinal")).equals(""))){
					//proceso el sql
					_sqlFinal = (String)request.getAttribute("generadorSql_sqlFinal");

					//cargo el objeto que se creó para poder trabajar con su info
					InstanciaObjeto _miIns = (InstanciaObjeto) sesion.getAttribute("generadorSql_instanciaObjeto");

					//no es muy elegante pero se invitan a las personas directamente aqui
					String sqlFinal = "SELECT DISTINCT ON(respuestas.elaborado_por) respuestas.elaborado_por as elaborado_por, id_instancia_objetos as id_objeto_fuente FROM respuestas WHERE "+_sqlFinal;
					int _id_pregunta_email = -1;
					if(request.getAttribute("generadorSql_campoEmail") != null){
						_id_pregunta_email = (Integer)request.getAttribute("generadorSql_campoEmail");
					}
					String _text_pregunta_email = "";
					if(request.getAttribute("generadorSql_textoParaEmail") != null){
						_text_pregunta_email = (String)request.getAttribute("generadorSql_textoParaEmail");
					}
					AccesosEncuestados.IngresarEncuestadosEnNuevoObjeto(micon, sqlFinal, _miIns, _id_pregunta_email, _text_pregunta_email);

					//despues de procesar limpio la sesion
					sesion.setAttribute("generadorSql_cancelarAndGoTo", "");
					sesion.setAttribute("generadorSql_finalizarAndGoTo", "");
				}else{
					if (request.getParameter("accion") != null){
						String _accion = (String) request.getParameter("accion");
						if (_accion.equals("eliminar")) {
							int _id = Integer.parseInt((String) request.getParameter("objetoseleccionado"));
							InstanciaObjeto _miIns = new InstanciaObjeto(admin, micon, _id);
							_miIns.delInstanciaObjeto(true);
						}
					}

					Vector _objetos = Objeto.todosObjetos(admin, micon, 0, true, false);
					Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0);

					request.setAttribute("objetosDisponibles", _objetos);
					request.setAttribute("objetosInstanciados", _instanciados);

					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/implementobjeto.jsp");
					view.forward(request, response);
				}
			}
			catch (Exception e) {
				//e.printStackTrace();
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

}