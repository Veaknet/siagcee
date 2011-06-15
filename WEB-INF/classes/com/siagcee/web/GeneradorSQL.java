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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 07/12/2009
 * Hora: 02:21:25 PM
 */

//TODO
//filtrar por poblacion

public class GeneradorSQL extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		String _sqlFinal = "";
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/generadorsql.jsp");
		if(admin != null){
			try{
				sesion.setAttribute("generadorSql_cancelarAndGoTo", (String)sesion.getAttribute("generadorSql_cancelarAndGoTo"));
				sesion.setAttribute("generadorSql_finalizarAndGoTo", (String)sesion.getAttribute("generadorSql_finalizarAndGoTo"));
			}catch(Exception e){
				//e.printStackTrace();
			}

			try{

				if(request.getParameter("accion") != null){
					String _miaccion = (String)request.getParameter("accion");

					InstanciaObjeto _objetoseleccionado = null;
					if(_miaccion.equals("seleccionar")){
						try{
							_objetoseleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
							request.setAttribute("objetoatrabajar", _objetoseleccionado);
						}catch (Exception ee){
							request.setAttribute("accion","");
							view = request.getRequestDispatcher("WEB-INF/vistas/generadorsql.jsp");
						}
					}
				}
				//proceso el gran formulario para formar el Query.
				if(request.getParameter("miaccion") != null){
					String _miaccion = (String)request.getParameter("miaccion");
					if(_miaccion.equals("procesar") && ((String)request.getParameter("hacerpreview")).equals("verdad")){
						InstanciaObjeto _objetoseleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
						request.setAttribute("objetoatrabajar", _objetoseleccionado);
						_sqlFinal = this.generaSql(admin, micon, _objetoseleccionado, request, response);
						request.setAttribute("generadorSql_sqlFinal", _sqlFinal);
						PreparedStatement pstmt = micon.prepareStatement("SELECT * FROM respuestas WHERE elaborado_por IN (SELECT elaborado_por FROM respuestas WHERE "+_sqlFinal+") ORDER BY elaborado_por");
						ResultSet _lasResp = pstmt.executeQuery();
						Vector _tempVec = new Vector();
						while(_lasResp.next()){
							_tempVec.add(new Respuesta(admin, micon, _lasResp.getInt("id_respuestas")));
						}
						request.setAttribute("respuestas", _tempVec);
						view = request.getRequestDispatcher("WEB-INF/vistas/sqlpreview.jsp");

					}else if(_miaccion.equals("procesar") && ((String)request.getParameter("hacerpreview")).equals("falso")){
						InstanciaObjeto _objetoseleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
						request.setAttribute("objetoatrabajar", _objetoseleccionado);
						_sqlFinal = this.generaSql(admin, micon, _objetoseleccionado, request, response);
						request.setAttribute("generadorSql_sqlFinal", _sqlFinal);
						request.setAttribute("generadorSql_campoEmail", Integer.parseInt((String)request.getParameter("email_id_invite")));
						request.setAttribute("generadorSql_textoParaEmail", request.getParameter("email_id_invite_text"));

						//reenvio a la direccion deseada por parametro, pasando en "generadorSql_sqlFinal" las condiciones (WHERE) de la consulta sobre tabla resultados
						view = request.getRequestDispatcher((String)sesion.getAttribute("generadorSql_finalizarAndGoTo"));
					}
				}

				Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0);
				request.setAttribute("objetosInstanciados", _instanciados);
				view.forward(request, response);

			}catch(Exception e){
				//e.printStackTrace();
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
		if(admin != null){
			try{
				sesion.setAttribute("generadorSql_cancelarAndGoTo", (String)sesion.getAttribute("generadorSql_cancelarAndGoTo"));
				sesion.setAttribute("generadorSql_finalizarAndGoTo", (String)sesion.getAttribute("generadorSql_finalizarAndGoTo"));
			}catch(Exception e){
				//e.printStackTrace();
			}

			try{
				if(request.getParameter("accion") != null){
					String _miaccion = (String)request.getParameter("accion");

					if(_miaccion.equals("seleccionar")){
						InstanciaObjeto _objetoseleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
						request.setAttribute("objetoatrabajar", _objetoseleccionado);
					}
				}

				Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0);

				request.setAttribute("objetosInstanciados", _instanciados);

				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/generadorsql.jsp");
				view.forward(request, response);
			}catch(Exception e){
				//e.printStackTrace();
				Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon, true, 0);
				request.setAttribute("objetosInstanciados", _instanciados);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/generadorsql.jsp");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}


	//funcion que dado los parametros del formulario atado a este servlet devuelve el codigo SQL deseado
	private String generaSql(Usuario admin, Connection micon, InstanciaObjeto _padre,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		StringBuffer _sqlFinal = new StringBuffer("(");
		StringBuffer _sqltmp = new StringBuffer();
		Integer _totalLineas = Integer.parseInt((String)request.getParameter("total_lineas"));
		InstanciaObjeto _objetoseleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
		InstanciaPregunta _insPregunta;
		String _temporal = "";
		String _operador = "";
		if(_totalLineas == 0){
			//no hay condiciones, termino
			return " id_instancia_objetos = "+_objetoseleccionado.getId();
		}
		for(int i=1; i<=_totalLineas ;i++){
			_sqltmp = new StringBuffer();
			//recorro cada linea para formar el SQL
			try{
				//conector
				if(i > 1){
					_temporal = (String)request.getParameter("conector_"+i);
					_sqltmp.append(" " + _temporal+ " ");
				}

				//parentesis abierto?
				_temporal = (String)request.getParameter("parentesisabiertoinput_"+i);
				_sqltmp.append(" " + _temporal+ " ");

				//operadores
				_temporal = (String)request.getParameter("operador_"+i);
				if(_temporal.equals("equal")){
					_operador = " = ";
				}else if(_temporal.equals("notequal")){
					_operador = " <> ";
				}else if(_temporal.equals("parecido")){
					_operador = " LIKE ";
				}else if(_temporal.equals("lt")){
					_operador = " < ";
				}else if(_temporal.equals("let")){
					_operador = " <= ";
				}else if(_temporal.equals("gt")){
					_operador = " > ";
				}else if(_temporal.equals("get")){
					_operador = " >= ";
				}

				//selecciono la pregunta
				//dependiendo del tipo de pregunta se escoge un camino. si es seleccion multiple debe asociarse inmediatamente con las respuestas
				_insPregunta = new InstanciaPregunta(admin, micon, Integer.parseInt((String)request.getParameter("pregunta_"+i)));

				//para agrupar InstanciaPregunta con respuestaPosibles
				//las condiciones siempre van en pares
				_sqltmp.append(" elaborado_por IN (SELECT elaborado_por FROM respuestas WHERE ");
				_sqltmp.append(" id_instancia_preguntas = ");
				_sqltmp.append(_insPregunta.getId());
				_sqltmp.append(" AND ");

				if((_insPregunta.getTipoPregunta() == 1) || (_insPregunta.getTipoPregunta() == 2)){
					_sqltmp.append(" (respuestas.id_respuestas_posibles ");
					_sqltmp.append(_operador);
					_sqltmp.append((String)request.getParameter("respuesta_"+i));

				}else{
					//es abierta, depende del tipo de dato esperado
					if(_insPregunta.getTipoPregunta() == 30){
						//abierta con texto
						_sqltmp.append(" respuestas.respuesta_string ");
						_sqltmp.append(_operador);
						if(_operador.equals(" LIKE ")){
							_sqltmp.append("'%");
							_sqltmp.append((String)request.getParameter("respuesta_"+i));
							_sqltmp.append("%'");
						}else{
							_sqltmp.append("'");
							_sqltmp.append((String)request.getParameter("respuesta_"+i));
							_sqltmp.append("'");
						}
					}else if(_insPregunta.getTipoPregunta() == 31){
						//abierta numerica sin decimales
						_sqltmp.append(" respuestas.respuesta_int ");
						_sqltmp.append(_operador);
						_sqltmp.append((String)request.getParameter("respuesta_"+i));
					}else if(_insPregunta.getTipoPregunta() == 32){
						//abierta numerica con decimales
						_sqltmp.append(" respuestas.respuesta_float ");
						_sqltmp.append(_operador);
						_sqltmp.append((String)request.getParameter("respuesta_"+i));
					}else if(_insPregunta.getTipoPregunta() == 33){
						//abierta para fechas
						_sqltmp.append(" respuestas.respuesta_date ");
						_sqltmp.append(_operador);
						_sqltmp.append("'");
						_sqltmp.append((String)request.getParameter("respuesta_"+i));
						_sqltmp.append("'");
					}
				}

				//para agrupar InstanciaPregunta con respuestaPosibles
				//las condiciones siempre van en pares
				_sqltmp.append(")");

				//parentesis cerrado?
				_temporal = (String)request.getParameter("parentesiscerradoinput_"+i);
				_sqltmp.append(" " + _temporal+ " ");

			}catch(Exception e){
				//e.printStackTrace();
			}
			_sqlFinal.append(_sqltmp);


		}
		_sqlFinal.append(" ) AND id_instancia_objetos = "+_objetoseleccionado.getId());

        return new String(_sqlFinal);
	}

}
