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

public class AdministrarRelacion2 extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/adminrelaciones.jsp");
		if(admin != null){
			try{
				InstanciaObjeto _relacion = null;
				InstanciaObjeto _instrumento = null;
				if(request.getParameter("relacion") != null){
					int _t = Integer.parseInt((String)request.getParameter("relacion"));
					_relacion = new InstanciaObjeto(admin, micon, _t);
					Collections.sort(_relacion.getObjetoAsociado().getPreguntas(), new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
				}
				if(request.getParameter("instrumento") != null){
					int _t = Integer.parseInt((String)request.getParameter("instrumento"));
					_instrumento = new InstanciaObjeto(admin, micon, _t);
					Collections.sort(_instrumento.getObjetoAsociado().getPreguntas(), new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.TIPO_PREGUNTA));
				}

				if(_relacion != null && _instrumento != null){
					//pasar datos
					Enumeration _enu = _relacion.getObjetoAsociado().getPreguntas().elements();
					InstanciaPregunta _miPreg = null;
					InstanciaPregunta _miPregIn = null;
					DuplicadorRespuestas _dP = new DuplicadorRespuestas(admin, micon);
					Vector _preOrigen = new Vector();
					Vector _preDestino = new Vector();
					while(_enu.hasMoreElements()){
						//pregunta de la relacion
						_miPreg = (InstanciaPregunta)_enu.nextElement();
						if(request.getParameter("pregunta_"+_miPreg.getId()) != null){
							//pregunta que quiero asociar a la de la relacion
							if(Integer.parseInt((String)request.getParameter("pregunta_"+_miPreg.getId())) != -1){
								_miPregIn = new InstanciaPregunta(admin, micon, Integer.parseInt((String)request.getParameter("pregunta_"+_miPreg.getId())));
								_preOrigen.add(_miPregIn);
								_preDestino.add(_miPreg);
							}
						}
					}

					InstanciaPregunta _preguntaJoinInstrumento = null;
					InstanciaPregunta _preguntaJoinRelacion = null;
					if(request.getParameter("pregJoinInstrumento") != null){
						if(!(request.getParameter("pregJoinInstrumento")).equals("-1")){
							_preguntaJoinInstrumento = new InstanciaPregunta(admin,micon, Integer.parseInt((request.getParameter("pregJoinInstrumento"))));
							if(request.getParameter("pregJoinRelacion") != null){
								if(!(request.getParameter("pregJoinRelacion")).equals("-1")){
									_preguntaJoinRelacion = new InstanciaPregunta(admin,micon, Integer.parseInt((request.getParameter("pregJoinRelacion"))));
								}else{
									_preguntaJoinInstrumento = null;
								}
							}else{
								_preguntaJoinInstrumento = null;
							}
						}
					}
					_dP.set_preguntaJoinInstrumento(_preguntaJoinInstrumento);
					_dP.set_preguntaJoinRelacion(_preguntaJoinRelacion);
					_dP.setObjetoDestino(_relacion);
					_dP.setObjetoOrigen(_instrumento);
					_dP.setPreguntasDestino(_preDestino);
					_dP.setPreguntasOrigen(_preOrigen);

					if(_dP.crearTempRespuestas(DuplicadorRespuestas.LIMPIAR_TEMPORALES_VIEJOS, "SELECT elaborado_por FROM respuestas WHERE elaborado_por NOT IN(SELECT elaborado_por FROM respuestas WHERE id_instancia_objetos = "+_relacion.getId()+")") != DuplicadorRespuestas.CORRECTO){
						_dP.cierraTempRespuestas();
						//error
					}else{
						//correcto
						//Paso a respuestas y cierro
						if(_dP.cambiarPreguntaID() == DuplicadorRespuestas.CORRECTO){
							//bien
							_dP.deTempAProduccion();
							request.setAttribute("relacionATrabajar",_relacion);
							request.setAttribute("todasInstancias", InstanciaObjeto.todosObjetosInstanciados(admin, micon));
							request.setAttribute("todasEstructuras", Objeto.todosObjetos(admin, micon, 0, true, false));
						}else{
							//error
						}
						_dP.cierraTempRespuestas();
					}
					view = request.getRequestDispatcher("adminobjetos2.do?tipoinstrumento=relacion&opcionbase=modificar&objetoseleccionado="+_relacion.getObjetoAsociado().getId());
				}
				view.forward(request, response);
			}catch(Exception e){
				//error voy a pantalla principal
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
			try{

			}catch(Exception e){
				//error voy a pantalla principal
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

}