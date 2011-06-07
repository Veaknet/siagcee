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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class CrearEstudioPerso extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/crearestudioperso.jsp");
		if(admin != null){
			try{
				if(request.getParameter("objetoatrabajar") != null){

					InstanciaObjeto _objObjeto  = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					Vector _listaTiposDeDatos = _objObjeto.getObjetoAsociado().getPreguntas(true);

					request.setAttribute("datos", _listaTiposDeDatos);
					request.setAttribute("objetoatrabajar", _objObjeto);

					request.setAttribute("mostrarresultados", true);

					String _cod = "";
					if(request.getParameter("codigoestudio") != null){
						_cod = (String)request.getParameter("codigoestudio");
					}
					_cod = _cod+"\n";

					request.setAttribute("codigoestudio", _cod);

					EstudioPerso.resetInstance();
					EstudioPerso.getInstance().setConexion(micon);
					EstudioPerso.getInstance().setAdmin(admin);
					EstudioPerso.getInstance().set_obj(_objObjeto);
					EstudioPerso.getInstance().ejecutaEstudio(_cod);

					//exportar resultados
					if(request.getParameter("exportar") != null && !(request.getParameter("exportar")).equals("")){
						String _titSt = "Resultados Estudio";
						if(EstudioPerso.getInstance().getCargadaDeBD()){
							_titSt = _titSt+": "+EstudioPerso.getInstance().get_titulo();
						}
						//ins Preg
						InstanciaPregunta _insPre;
						Pregunta _pre = null;
						Vector _tempVec2 = new Vector();
						if(EstudioPerso.getInstance().getCargadaDeBD()){
							_insPre = EstudioPerso.getInstance().retornaPreguntaAsociada();
						}else{
							_pre = new Pregunta(admin, micon);
							_pre.setTipoPregunta(100);
							_insPre = new InstanciaPregunta(admin, micon);
							_insPre.setAcronimo("estudio");
							_insPre.setTextoPregunta("estudio");
							_insPre.asociarPregunta(_pre);
						}
						_tempVec2.add(_insPre);

						//resp
						HashMap _resulMostrados = new HashMap();
						Vector _tempVec = new Vector();
						HashMap _respuestas = EstudioPerso.getInstance().getHashMapResultados();
						Iterator _ite = _respuestas.entrySet().iterator();
						while(_ite.hasNext()){
							Map.Entry _temp = (Map.Entry)_ite.next();
							int encuestado = (Integer)(_temp.getKey());
							if(_temp.getValue() == null){continue;}
							String resultad = (String)(_temp.getValue());

							if(_resulMostrados.get(resultad) != null){
								continue;
							}
							_resulMostrados.put(resultad, true);
							Respuesta _respNueva = new Respuesta(new Encuestado(micon, encuestado),  micon);
							_respNueva.setElaborado_por(encuestado);
							_respNueva.asociarInstanciaPregunta(_insPre);
							_insPre.setEstudioAsociado(EstudioPerso.getInstance());
							_respNueva.asociarInstanciaObjeto(_objObjeto);
							_respNueva.setRespuesta(resultad);
							_tempVec.add(_respNueva);
						}

						if((request.getParameter("exportar")).equals("exportaexcel")){
							String _nombreArchivo = getServletContext().getRealPath("/")+"comunes/documentos/res_est"+_objObjeto.getId()+".xls";

							UtilidadesVarias.generaExcel(_nombreArchivo, _tempVec2, _tempVec, _titSt);

							view = request.getRequestDispatcher("comunes/documentos/res_est"+_objObjeto.getId()+".xls");
						}

						//es para exportar a PDF
						if((request.getParameter("exportar")).equals("exportapdf")){

							String _nombreArchivo = getServletContext().getRealPath("/")+"comunes/documentos/res_est"+_objObjeto.getId()+".pdf";

							UtilidadesVarias.generaPDF(_nombreArchivo, _tempVec2, _tempVec, _titSt);

							view = request.getRequestDispatcher("comunes/documentos/res_est"+_objObjeto.getId()+".pdf");
						}

						//es para exportar a word
						if((request.getParameter("exportar")).equals("exportaword")){

							String _nombreArchivo = getServletContext().getRealPath("/")+"comunes/documentos/res_est"+_objObjeto.getId();

							UtilidadesVarias.generaWord(_nombreArchivo, _tempVec2, _tempVec, _titSt);

							view = request.getRequestDispatcher("comunes/documentos/res_est"+_objObjeto.getId()+".docx");
						}

						//por salud, borro pregunta temporal
						if(!EstudioPerso.getInstance().getCargadaDeBD()){
							_insPre.delPregunta();
							if(_pre != null){
								_pre.delPregunta();
							}
						}
					}

					//guardar estudio
					if(request.getParameter("guardar") != null && (request.getParameter("guardar")).equals("true")){
						String _titulo = (String)request.getParameter("titulo");
						String _acronimo = (String)request.getParameter("acronimo");

						EstudioPerso.getInstance().set_acronimo(_acronimo);
						EstudioPerso.getInstance().set_titulo(_titulo);
						EstudioPerso.getInstance().guardar();

						Pregunta _pre = new Pregunta(admin, micon, _titulo, 100);

						InstanciaPregunta _insPreg = new InstanciaPregunta(admin, micon, _titulo, _pre, _objObjeto.getObjetoAsociado());
						_insPreg.setAcronimo(_acronimo);
						_insPreg.setOrden(1000);
						_insPreg.setEstudioAsociado(EstudioPerso.getInstance());

						_objObjeto.getObjetoAsociado().agregarInstanciaPregunta(_insPreg);

						//almaceno las respuestas
						if(_insPreg.getId() != -1 && _insPreg.getCargadaDeBD()){
							HashMap _respuestas = EstudioPerso.getInstance().getHashMapResultados();
							Iterator _ite = _respuestas.entrySet().iterator();
							while(_ite.hasNext()){
								Map.Entry _temp = (Map.Entry)_ite.next();
								int encuestado = (Integer)(_temp.getKey());
								if(_temp.getValue() == null){continue;}
								String resultad = (String)(_temp.getValue());

								Respuesta _respNueva = new Respuesta(new Encuestado(micon, encuestado),  micon);
								_respNueva.setElaborado_por(encuestado);
								_respNueva.asociarInstanciaPregunta(_insPreg);
								_insPreg.setEstudioAsociado(EstudioPerso.getInstance());
								_respNueva.asociarInstanciaObjeto(_objObjeto);
								_respNueva.setRespuesta(resultad);

								request.setAttribute("guardado", true);
							}
						}else{
							EstudioPerso.getInstance().delEstudio();
							_pre.delPregunta();
							_insPreg.delPregunta();
						}
					}
				}
				view.forward(request, response);
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				e.printStackTrace();
				view = request.getRequestDispatcher("adminobjetos.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
		if(admin != null){
			try{
				if(request.getParameter("objetoatrabajar") != null){
					InstanciaObjeto _objObjeto = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					Vector _listaTiposDeDatos = _objObjeto.getObjetoAsociado().getPreguntas(true);
					request.setAttribute("datos", _listaTiposDeDatos);
					request.setAttribute("objetoatrabajar", _objObjeto);
					if(request.getParameter("idestudio") != null){
						try{
							EstudioPerso.resetInstance();
							EstudioPerso.getInstance().setAdmin(admin);
							EstudioPerso.getInstance().setConexion(micon);
							EstudioPerso.getInstance().cargar(Integer.parseInt(request.getParameter("idestudio")));
							if(request.getParameter("accion") != null && request.getParameter("accion").equals("delete")){
								EstudioPerso.getInstance().delEstudio();
								view = request.getRequestDispatcher("estudios.do?opcionbase=eliminar");
								view.forward(request, response);
							}else{
								request.setAttribute("codigoestudio", EstudioPerso.getInstance().get_cod());
							}
						}catch (Exception eeee){
							request.setAttribute("codigoestudio", "");
						}
					}
				}
				view = request.getRequestDispatcher("WEB-INF/vistas/crearestudioperso.jsp");
				view.forward(request, response);
			}catch(Exception e){
				//error voy a pantalla principal de objetos
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			view.forward(request, response);
		}
	}
}
