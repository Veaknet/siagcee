package com.siagcee.web;

import com.siagcee.logic.Administrador;
import com.siagcee.logic.Encuestado;
import com.siagcee.logic.Respuesta;
import com.siagcee.logic.UtilidadesVarias;

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

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 21/05/2010
 * Hora: 08:27:25 AM
 */
public class Mensaje extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");

		String dominioweb = UtilidadesVarias.dominioWeb;
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
		if(admin != null){
			try {
				String _sqlFinal = "";
				if ((request.getAttribute("generadorSql_sqlFinal") != null) && (!((String)request.getAttribute("generadorSql_sqlFinal")).equals(""))){
					//proceso el sql
					_sqlFinal = (String)request.getAttribute("generadorSql_sqlFinal");

					String _mensaje = "";
					if(sesion.getAttribute("mensaje") != null){
						_mensaje = (String)sesion.getAttribute("mensaje");
					}
					String _titulo = "";
					if(sesion.getAttribute("titulo") != null){
						_titulo = (String)sesion.getAttribute("titulo");
					}
					
					int _id_pregunta_email = -1;
					if(request.getAttribute("generadorSql_campoEmail") != null){
						_id_pregunta_email = (Integer)request.getAttribute("generadorSql_campoEmail");
					}
					String _text_pregunta_email = "";
					if(request.getAttribute("generadorSql_textoParaEmail") != null){
						_text_pregunta_email = (String)request.getAttribute("generadorSql_textoParaEmail");
					}

					//no es muy elegante pero se invitan a las personas directamente aqui
					String sqlFinal = "SELECT respuestas.*, respuestas.id_instancia_objetos as id_objeto_fuente FROM respuestas WHERE (id_instancia_preguntas = "+_id_pregunta_email+") AND "+_sqlFinal;
					try{
						PreparedStatement pstmt = micon.prepareStatement(sqlFinal);
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()){
							Respuesta _re = new Respuesta(new Encuestado(micon, rs.getInt("elaborado_por")), micon, rs.getInt("id_respuestas"));
							if(_re.getInstanciaPregunta().getTipoPregunta() == 30 || _re.getInstanciaPregunta().getTipoPregunta() == 100){
								UtilidadesVarias.enviarMailSinAutenticacion(_re.getRespuestaAbiertaTexto(), _titulo, _mensaje, "SIGECENE <sigecene@sigecene.com>", "", "mail.cantv.net");
							}else{
								break;
							}
						}
					}catch (Exception eee){eee.printStackTrace();}

					//despues de procesar limpio la sesion
					sesion.setAttribute("generadorSql_cancelarAndGoTo", "");
					sesion.setAttribute("generadorSql_finalizarAndGoTo", "");

					view = request.getRequestDispatcher("WEB-INF/vistas/mensaje.jsp");
					request.setAttribute("accion", "finalizado");
					request.setAttribute("mensaje", _mensaje);
					request.setAttribute("titulo", _titulo);
					view.forward(request, response);
					return;
				}

				String _mensaje = "";
				if(request.getParameter("mensaje") != null){
					_mensaje = request.getParameter("mensaje");
				}
				String _titulo = "";
				if(request.getParameter("titulo") != null){
					_titulo = request.getParameter("titulo");
				}
				String _opcion = "";
				if(request.getParameter("opcion") != null){
					_opcion = request.getParameter("opcion");
				}
				if(!_opcion.equals("")){
					if(_opcion.equals("seleccionar_correos")){
						String _opcion_correo = "manual";
						if(request.getParameter("opcion_correo") != null){
							_opcion_correo = request.getParameter("opcion_correo");
						}
						if(_opcion_correo.equals("instrumento")){

							//datos necesarios para que generadorSQL pueda regresar la data al servlet indicado
							sesion.setAttribute("generadorSql_cancelarAndGoTo", "mensaje.do");
							sesion.setAttribute("generadorSql_finalizarAndGoTo", "mensaje.do");
							sesion.setAttribute("generadorSql_instanciaObjeto", null);
							sesion.setAttribute("generadorSql_poblacionAsociada", null);
							sesion.setAttribute("mensaje", _mensaje);
							sesion.setAttribute("titulo", _titulo);
							view = request.getRequestDispatcher("generadorsql.do");

						}else{
							request.setAttribute("accion", "seleccionar_correos");
							request.setAttribute("mensaje", _mensaje);
							request.setAttribute("titulo", _titulo);

							view = request.getRequestDispatcher("WEB-INF/vistas/mensaje.jsp");
						}
					}else if(_opcion.equals("enviar_correos")){
						request.setAttribute("accion", "finalizado");
						request.setAttribute("mensaje", _mensaje);
						request.setAttribute("titulo", _titulo);
						String _listadoCorreos = "";
						String[] _listadoCorreos2;
						if(request.getParameter("correos") != null){
							_listadoCorreos = request.getParameter("correos");
							_listadoCorreos2 = _listadoCorreos.split("\n");
							UtilidadesVarias.enviarMailSinAutenticacion(_listadoCorreos2, _titulo, _mensaje, "SIGECENE <sigecene@sigecene.com>", "", "mail.cantv.net");
/*							for(int p = 0; p < _listadoCorreos2.length; ++p){
								String _miParam = _listadoCorreos2[p];
								try{
									UtilidadesVarias.enviarMailSinAutenticacion(_miParam, _titulo, _mensaje, "SIGECENE <sigecene@sigecene.com>", "", "mail.cantv.net");
								}catch (Exception ee){ee.printStackTrace();}
							}*/
						}
						view = request.getRequestDispatcher("WEB-INF/vistas/mensaje.jsp");
					}else{

						view = request.getRequestDispatcher("WEB-INF/vistas/mensaje.jsp");
					}
				}else{
					doGet(request, response);
					return;
				}
				view.forward(request, response);
			}catch (Exception e){
				//sesion no iniciada
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else {
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/mensaje.jsp");
		if(admin != null){
			try {
				view.forward(request, response);
			}catch (Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}
}
