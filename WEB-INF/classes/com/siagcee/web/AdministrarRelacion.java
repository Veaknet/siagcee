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
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 17/12/2009
 * Hora: 11:04:03 AM
 */

public class AdministrarRelacion extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrelaciones.jsp");
		if(admin != null){
			try{
				int _relacionATrabajar = -1;
				if(request.getParameter("objetoseleccionado") != null){
					_relacionATrabajar = Integer.parseInt((String)request.getParameter("objetoseleccionado"));
				}

				int _instrumentoSeleccionado = -1;
				if(request.getParameter("instrumentoseleccionado") != null){
					_instrumentoSeleccionado = Integer.parseInt((String)request.getParameter("instrumentoseleccionado"));
				}

				//accion a ejecutar
				String _accion = "seleccionar";
				if(request.getParameter("accion") != null){
					_accion = (String)request.getParameter("accion");
				}

				//VENGO DE GENERADORSQL.... proceso y termino.
				if ((request.getAttribute("generadorSql_sqlFinal") != null) && (!((String)request.getAttribute("generadorSql_sqlFinal")).equals(""))){
					//proceso el sql
					InstanciaPregunta _preguntaJoinRelacion = (InstanciaPregunta)sesion.getAttribute("generadorSql_instanciaPreguntaJoinRelacion");
					InstanciaPregunta _preguntaJoinInstrumento = (InstanciaPregunta)sesion.getAttribute("generadorSql_instanciaPreguntaJoinInstrumento");
					String _sqlFinal = (String)request.getAttribute("generadorSql_sqlFinal");
					InstanciaObjeto _miRelacionPrincipal = (InstanciaObjeto)sesion.getAttribute("generadorSql_instanciaObjeto");
					InstanciaObjeto _miInstrumentoRelacionado = (InstanciaObjeto)sesion.getAttribute("generadorSql_instanciaObjetoAUX");
					Vector _pregsDestino = (Vector)sesion.getAttribute("generadorSql_pregDestino");
					Vector _pregsOrigen = (Vector)sesion.getAttribute("generadorSql_pregOrigen");

					_sqlFinal = "SELECT elaborado_por FROM respuestas WHERE "+_sqlFinal;

					DuplicadorRespuestas _miDp = new DuplicadorRespuestas(admin, micon);
					_miDp.setObjetoDestino(_miRelacionPrincipal);
					_miDp.setObjetoOrigen(_miInstrumentoRelacionado);
					_miDp.setPreguntasDestino(_pregsDestino);
					_miDp.setPreguntasOrigen(_pregsOrigen);
					_miDp.set_preguntaJoinInstrumento(_preguntaJoinInstrumento);
					_miDp.set_preguntaJoinRelacion(_preguntaJoinRelacion);

					if(_miDp.crearTempRespuestas(DuplicadorRespuestas.LIMPIAR_TEMPORALES_VIEJOS, _sqlFinal) != DuplicadorRespuestas.CORRECTO){
						_miDp.cierraTempRespuestas();
						//error
					}else{
						//correcto
						//Paso a respuestas y cierro
						if(_miDp.cambiarPreguntaID() == DuplicadorRespuestas.CORRECTO){
							//bien
							_miDp.deTempAProduccion();
						}else{
							//error
						}
						_miDp.cierraTempRespuestas();
					}

					//limpio
					sesion.setAttribute("generadorSql_instanciaPreguntaJoinRelacion", null);
					sesion.setAttribute("generadorSql_instanciaPreguntaJoinInstrumento", null);
					sesion.setAttribute("generadorSql_cancelarAndGoTo", "");
					sesion.setAttribute("generadorSql_finalizarAndGoTo", "");
					sesion.setAttribute("generadorSql_pregDestino", "");
					sesion.setAttribute("generadorSql_pregOrigen", "");
					sesion.setAttribute("generadorSql_instanciaObjeto", null);
					sesion.setAttribute("generadorSql_instanciaObjetoAUX", null);


					//al retornar de generarsql y luego de pasar la data lo que queda por hacer es volver a cargar la relacion por lo que:
					_relacionATrabajar = _miRelacionPrincipal.getId();
					_accion = "seleccionar";
					view = request.getRequestDispatcher("adminobjetos2.do?tipoinstrumento=relacion&opcionbase=modificar&objetoseleccionado="+_miRelacionPrincipal.getObjetoAsociado().getId());
				}

				if(_accion.equals("seleccionar")){
					//+++++++++++++++++++++++++++++++++++++++++
					//selecciono con cual instrumento se trabajara
					//+++++++++++++++++++++++++++++++++++++++++
					Objeto _miRelacion = Objeto.retornaObjeto(admin, micon, _relacionATrabajar);
					if(_relacionATrabajar == -1){
						request.setAttribute("objetoseleccionado", null);
					}else{
						request.setAttribute("objetoseleccionado", _miRelacion);
					}

					//id que quiero cargar para unir a la relacion
					InstanciaObjeto _miInstrumento = new InstanciaObjeto(admin, micon,  _instrumentoSeleccionado);
					if(_instrumentoSeleccionado == -1){
						request.setAttribute("instrumentoseleccionado", null);
					}else{
						request.setAttribute("instrumentoseleccionado", _miInstrumento);
					}
				}else if(_accion.equals("relacionarobjetos")){
					//+++++++++++++++++++++++++++++++++++++++++
					//Relaciono los objetos finalmente.
					//+++++++++++++++++++++++++++++++++++++++++
					//relacion a trabajar
					Objeto _miRelacion = Objeto.retornaObjeto(admin, micon, _relacionATrabajar);
					request.setAttribute("relacionATrabajar",_miRelacion);

					//necesito la instancia única de la relación
					Enumeration _enuthis = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0).elements();
					InstanciaObjeto _instanciaDeRelacion = null;
					while(_enuthis.hasMoreElements()){
						_instanciaDeRelacion = (InstanciaObjeto)_enuthis.nextElement();
						if(_instanciaDeRelacion.getObjetoAsociado().getId() == _miRelacion.getId()){
							break;
						}else{
							_instanciaDeRelacion = null;
						}
					}

					//como realizar el JOIN
					//por defecto 1 indica que se pasaran todos los datos, un simple UNION
					int _forma_de_relacion_de_datos = 1;
					if(request.getParameter("forma_de_relacion_de_datos") != null){
						_forma_de_relacion_de_datos = Integer.parseInt((String)request.getParameter("forma_de_relacion_de_datos"));
					}

					InstanciaObjeto _instanciaDeInstrumento = new InstanciaObjeto(admin, micon, _instrumentoSeleccionado);

					Vector _pregDeInstrumento = new Vector();
					Vector _pregDeRelacion = new Vector();
					if(_forma_de_relacion_de_datos != 6){
						//listado de preguntasseleccionadas
						String[] _listadoPreguntasDeseadas = request.getParameterValues("preguntasseleccionadas");
						if(_listadoPreguntasDeseadas != null){
							for(int p = 0; p < _listadoPreguntasDeseadas.length; ++p){
								String _miParam = _listadoPreguntasDeseadas[p];
								InstanciaPregunta _insPreg = new InstanciaPregunta(admin, micon, Integer.parseInt((String)_miParam));
								_pregDeInstrumento.add(_insPreg);
								//clono la instancia pregunta
								InstanciaPregunta _nuevaPreg = new InstanciaPregunta(_insPreg);
								_pregDeRelacion.add(_nuevaPreg);
								//establezco nuevo objeto asociado
								_nuevaPreg.setPadre(_miRelacion);
								//_nuevaPreg.setCampo_clave_unico(false);
								//_nuevaPreg.setCampo_comunicacion_email(false);
							}
						}
						Collections.sort(_pregDeInstrumento, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
						Collections.sort(_pregDeRelacion, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
					}

					InstanciaPregunta _preguntaJoinRelacion = null;
					InstanciaPregunta _preguntaJoinInstrumento = null;

					if(request.getParameter("pregunta_join_instrumento") != null){
						if(!(request.getParameter("pregunta_join_instrumento")).equals("-1")){
							_preguntaJoinInstrumento = new InstanciaPregunta(admin,micon, Integer.parseInt((request.getParameter("pregunta_join_instrumento"))));
							if((request.getParameter("pregunta_join_relacion") != null) && (_pregDeInstrumento != null)){
								if(!(request.getParameter("pregunta_join_relacion")).equals("-1")){
									_preguntaJoinRelacion = new InstanciaPregunta(admin,micon, Integer.parseInt((request.getParameter("pregunta_join_relacion"))));
								}else{
									_preguntaJoinInstrumento = null;
								}
							}else{
								_preguntaJoinInstrumento = null;
							}
						}
					}

					DuplicadorRespuestas _dP = new DuplicadorRespuestas(admin, micon);
					_dP.set_preguntaJoinInstrumento(_preguntaJoinInstrumento);
					_dP.set_preguntaJoinRelacion(_preguntaJoinRelacion);
					//realizo el paso de respuestas de un instrumento a la relacion
					if((_forma_de_relacion_de_datos == 1) || ((_preguntaJoinInstrumento == null || _preguntaJoinRelacion == null) && _forma_de_relacion_de_datos != 5 && _forma_de_relacion_de_datos != 6)){
						//UNION de todas las respuestas del instrumento y la relacion
						_dP.setObjetoDestino(_instanciaDeRelacion);
						_dP.setObjetoOrigen(_instanciaDeInstrumento);
						_dP.setPreguntasDestino(_pregDeRelacion);
						_dP.setPreguntasOrigen(_pregDeInstrumento);

						if(_dP.crearTempRespuestas(DuplicadorRespuestas.LIMPIAR_TEMPORALES_VIEJOS, "") != DuplicadorRespuestas.CORRECTO){
							_dP.cierraTempRespuestas();
							//error
						}else{
							//correcto
							//Paso a respuestas y cierro 
							if(_dP.cambiarPreguntaID() == DuplicadorRespuestas.CORRECTO){
								//bien
								_dP.deTempAProduccion();
							}else{
								//error
							}
							_dP.cierraTempRespuestas();
						}
						view = request.getRequestDispatcher("adminobjetos.do");
					}else if(_forma_de_relacion_de_datos == 2){
						//del instrumento segun los que estan en la relacion
						_dP.setObjetoDestino(_instanciaDeRelacion);
						_dP.setObjetoOrigen(_instanciaDeInstrumento);
						_dP.setPreguntasDestino(_pregDeRelacion);
						_dP.setPreguntasOrigen(_pregDeInstrumento);

						String _sqlFiltro = "";
						if(_preguntaJoinInstrumento.getTipoPregunta() < 30){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND id_respuestas_posibles IN (SELECT id_respuestas_posibles FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 33){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_date IN (SELECT respuesta_date FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 31){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_int IN (SELECT respuesta_int FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 32){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_float IN (SELECT respuesta_float FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else{
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_string IN (SELECT respuesta_string FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}

						if(_dP.crearTempRespuestas(DuplicadorRespuestas.LIMPIAR_TEMPORALES_VIEJOS, _sqlFiltro) != DuplicadorRespuestas.CORRECTO){
							_dP.cierraTempRespuestas();
							//error
						}else{
							//correcto
							//Paso a respuestas y cierro
							if(_dP.cambiarPreguntaID() == DuplicadorRespuestas.CORRECTO){
								//bien
								_dP.deTempAProduccion();
							}else{
								//error
							}
							_dP.cierraTempRespuestas();
						}
						view = request.getRequestDispatcher("adminobjetos.do");
					}else if(_forma_de_relacion_de_datos == 3){
						//de la relacion segun los que respondieron el instrumento indicado
						_dP.setObjetoDestino(_instanciaDeRelacion);
						_dP.setObjetoOrigen(_instanciaDeInstrumento);
						_dP.setPreguntasDestino(_pregDeRelacion);
						_dP.setPreguntasOrigen(_pregDeInstrumento);

						String _sqlFiltro = "";
						if(_preguntaJoinInstrumento.getTipoPregunta() < 30){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND id_respuestas_posibles IN (SELECT id_respuestas_posibles FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 33){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_date IN (SELECT respuesta_date FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 31){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_int IN (SELECT respuesta_int FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 32){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_float IN (SELECT respuesta_float FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else{
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_string IN (SELECT respuesta_string FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}

						_dP.deleteSegunSQL(_sqlFiltro);

						if(_dP.crearTempRespuestas(DuplicadorRespuestas.LIMPIAR_TEMPORALES_VIEJOS, "") != DuplicadorRespuestas.CORRECTO){
							_dP.cierraTempRespuestas();
							//error
						}else{
							//correcto
							//Paso a respuestas y cierro
							if(_dP.cambiarPreguntaID() == DuplicadorRespuestas.CORRECTO){
								//bien
								_dP.deTempAProduccion();
							}else{
								//error
							}
							_dP.cierraTempRespuestas();
						}
						view = request.getRequestDispatcher("adminobjetos.do");
					}else if(_forma_de_relacion_de_datos == 4){
						//solo datos que coinciden en relacion e instrumento
						_dP.setObjetoDestino(_instanciaDeRelacion);
						_dP.setObjetoOrigen(_instanciaDeInstrumento);
						_dP.setPreguntasDestino(_pregDeRelacion);
						_dP.setPreguntasOrigen(_pregDeInstrumento);

						String _sqlFiltro = "";
						if(_preguntaJoinInstrumento.getTipoPregunta() < 30){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND id_respuestas_posibles IN (SELECT id_respuestas_posibles FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 33){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_date IN (SELECT respuesta_date FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 31){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_int IN (SELECT respuesta_int FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 32){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_float IN (SELECT respuesta_float FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}else{
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"' AND respuesta_string IN (SELECT respuesta_string FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"')";
						}

						_dP.deleteSegunSQL(_sqlFiltro);


						if(_preguntaJoinInstrumento.getTipoPregunta() < 30){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND id_respuestas_posibles IN (SELECT id_respuestas_posibles FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 33){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_date IN (SELECT respuesta_date FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 31){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_int IN (SELECT respuesta_int FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else if(_preguntaJoinInstrumento.getTipoPregunta() == 32){
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_float IN (SELECT respuesta_float FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}else{
							_sqlFiltro = "SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeInstrumento.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinInstrumento.getId()+"' AND respuesta_string IN (SELECT respuesta_string FROM respuestas WHERE id_instancia_objetos = '"+_instanciaDeRelacion.getId()+"' AND id_instancia_preguntas = '"+_preguntaJoinRelacion.getId()+"')";
						}

						if(_dP.crearTempRespuestas(DuplicadorRespuestas.LIMPIAR_TEMPORALES_VIEJOS, _sqlFiltro) != DuplicadorRespuestas.CORRECTO){
							_dP.cierraTempRespuestas();
							//error
						}else{
							//correcto
							//Paso a respuestas y cierro
							if(_dP.cambiarPreguntaID() == DuplicadorRespuestas.CORRECTO){
								//bien
								_dP.deTempAProduccion();
							}else{
								//error
							}
							_dP.cierraTempRespuestas();
						}
						view = request.getRequestDispatcher("adminobjetos.do");
					}else if(_forma_de_relacion_de_datos == 5){
						//generadorsql
						//criterios personalizados de paso de data a la relacion
						//sesion.setAttribute("generadorSql_cancelarAndGoTo", "adminobjetos2.do?tipoinstrumento="+request.getParameter("tipoinstrumento")+"&opcionbase="+request.getParameter("opcionbase")+"&objetoseleccionado="+_instanciaDeRelacion.getObjetoAsociado().getId());
						//sesion.setAttribute("generadorSql_finalizarAndGoTo", "adminobjetos2.do?tipoinstrumento="+request.getParameter("tipoinstrumento")+"&opcionbase="+request.getParameter("opcionbase")+"&objetoseleccionado="+_instanciaDeRelacion.getObjetoAsociado().getId());
						sesion.setAttribute("generadorSql_cancelarAndGoTo", "adminrelaciones.do");
						sesion.setAttribute("generadorSql_finalizarAndGoTo", "adminrelaciones.do");

						sesion.setAttribute("generadorSql_instanciaPreguntaJoinRelacion", _preguntaJoinRelacion);
						sesion.setAttribute("generadorSql_instanciaPreguntaJoinInstrumento", _preguntaJoinInstrumento);
						sesion.setAttribute("generadorSql_instanciaObjeto", _instanciaDeRelacion);
						sesion.setAttribute("generadorSql_instanciaObjetoAUX", _instanciaDeInstrumento);
						sesion.setAttribute("generadorSql_pregDestino", _pregDeRelacion);
						sesion.setAttribute("generadorSql_pregOrigen", _pregDeInstrumento);
						view = request.getRequestDispatcher("generadorsql.do?soloeste=true&accioninvitar=true&accion=seleccionar&exclusivo=si&objetoatrabajar="+_instanciaDeInstrumento.getId());
					}else if(_forma_de_relacion_de_datos == 6){
						//solo data... no duplico instanciadepreguntas en la estructura.
						//debo pedir que asocie las preguntas de la relacion con alguna del instrumento.

						InstanciaObjeto _preguntasRelacion = _instanciaDeRelacion;
						InstanciaObjeto _preguntasInstrumento = _instanciaDeInstrumento;
						request.setAttribute("Relacion", _instanciaDeRelacion);
						request.setAttribute("Instrumento", _instanciaDeInstrumento);
						request.setAttribute("preguntaJoinRelacion", _preguntaJoinRelacion);
						request.setAttribute("preguntaJoinInstrumento", _preguntaJoinInstrumento);

						view = request.getRequestDispatcher("WEB-INF/vistas/adminrelaciones2.jsp");
					}
				}
				Vector _temp = InstanciaObjeto.todosObjetosInstanciados(admin, micon);
				request.setAttribute("instrumentos",_temp);

				view.forward(request, response);

			}catch(Exception e){
				//error voy a pantalla principal de objetos
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrelaciones.jsp");
		if(admin != null){
			try {
				int _id = -1;
				if(request.getParameter("objetoseleccionado") != null){
					_id = Integer.parseInt((String)request.getParameter("objetoseleccionado"));
				}

				Objeto _miRelacion = Objeto.retornaObjeto(admin, micon, _id);

				//cargo todos los objetos del sistema disponibles para mi
				Vector _temp = InstanciaObjeto.todosObjetosInstanciados(admin, micon);
				request.setAttribute("instrumentos",_temp);
				request.setAttribute("objetoseleccionado",((Relacion)_miRelacion));
				request.setAttribute("instrumentoseleccionado",null);
				view.forward(request, response);
				
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				e.printStackTrace();
				request.setAttribute("mensaje","<span style='color:red'>Error procesando la solicitud</span>");
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
