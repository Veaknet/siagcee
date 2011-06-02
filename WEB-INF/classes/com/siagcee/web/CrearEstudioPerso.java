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
		RequestDispatcher view = request.getRequestDispatcher("adminobjetos.do");
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
					EstudioPerso.getInstance().setUsuario(admin);
					EstudioPerso.getInstance().set_obj(_objObjeto);
					EstudioPerso.getInstance().ejecutaEstudio(_cod);

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

				view = request.getRequestDispatcher("WEB-INF/vistas/crearestudioperso.jsp");
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
				int idObj = -1;
				if(request.getParameter("objetoatrabajar") != null){
					InstanciaObjeto _objObjeto = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					Vector _listaTiposDeDatos = _objObjeto.getObjetoAsociado().getPreguntas(true);
					request.setAttribute("datos", _listaTiposDeDatos);
					request.setAttribute("objetoatrabajar", _objObjeto);
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
