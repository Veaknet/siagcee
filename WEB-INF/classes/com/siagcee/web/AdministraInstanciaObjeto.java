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
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 14/12/2009
 * Hora: 09:12:59 AM
 */

public class AdministraInstanciaObjeto extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		if(admin != null){
			try {
				String _sqlFinal = "";
				if ((request.getAttribute("generadorSql_sqlFinal") != null) && (!((String)request.getAttribute("generadorSql_sqlFinal")).equals(""))) {
					_sqlFinal = (String)request.getAttribute("generadorSql_sqlFinal");
					//proceso el Sql para invitar a las personas indicadas
					//solo lo procesa el Get, si por casualidad entra por post se reenvia
					this.doGet(request, response);
					//despues de procesar limpio la sesion
					sesion.setAttribute("generadorSql_cancelarAndGoTo", "");
					sesion.setAttribute("generadorSql_finalizarAndGoTo", "");
				}else{

				}

			}catch(Exception e){
				e.printStackTrace();
				//error voy a pantalla principal
				RequestDispatcher view = request.getRequestDispatcher("admininsobj.do");
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
		RequestDispatcher view = null;
		if(admin != null){
			try {
				String _sqlFinal = "";
				if ((request.getAttribute("generadorSql_sqlFinal") != null) && (!((String)request.getAttribute("generadorSql_sqlFinal")).equals(""))){
					//proceso el sql
					_sqlFinal = (String)request.getAttribute("generadorSql_sqlFinal");

					//cargo el objeto que se creó para poder trabajar con su info
					InstanciaObjeto _miIns = (InstanciaObjeto) sesion.getAttribute("generadorSql_instanciaObjeto");

					//no es muy elegante pero se invitan a las personas directamente aqui
					String sqlFinal = "SELECT respuestas.*, respuestas.id_instancia_objetos as id_objeto_fuente FROM respuestas WHERE "+_sqlFinal;
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

					Vector _insObj = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0);
					request.setAttribute("objetosInstanciados", _insObj);
					view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");

				}else{
					if(request.getParameter("invitar") != null){
						int _idIns = Integer.parseInt((String)request.getParameter("invitar"));
						//datos necesarios para que generadorSQL pueda regresar la data al servlet indicado
						sesion.setAttribute("generadorSql_cancelarAndGoTo", "admininsobj.do");
						sesion.setAttribute("generadorSql_finalizarAndGoTo", "admininsobj.do");

						InstanciaObjeto _miIns = new InstanciaObjeto(admin, micon, _idIns);
						sesion.setAttribute("generadorSql_instanciaObjeto", _miIns);
						
						if(_miIns.getObjetoAsociado().getClass().toString().contains("Censo")){
							sesion.setAttribute("generadorSql_poblacionAsociada", null);
						}else{
							sesion.setAttribute("generadorSql_poblacionAsociada", _miIns.getPoblacion_asociada());
						}
						view = request.getRequestDispatcher("generadorsql.do");

					}else{
						int idToElim = -1;
						if(request.getParameter("eliminar") != null){
							idToElim = Integer.parseInt((String)request.getParameter("eliminar"));
							InstanciaObjeto _t = new InstanciaObjeto(admin, micon, idToElim);
							_t.setEliminado(true);
							//si es una Relacion entonces eliminar su estructura si es editable
							/*  NO LO PERMITIRÉ POR AHORA
							Objeto _oT = null;
							if(_t.getObjetoAsociado().getClass().toString().equals(Relacion.class.toString())){

								_oT = _t.getObjetoAsociado();
							}
							*/
							//tambien me eliminara las respuestas
							//_t.delInstanciaObjeto(true);
							//si es una Relacion entonces eliminar su estructura si es editable
							/*  NO LO PERMITIRÉ POR AHORA
							if(_oT != null){
								_oT.delObjeto();
							}
							*/
						}else if(request.getParameter("recuperar") != null){
							idToElim = Integer.parseInt((String)request.getParameter("recuperar"));
							InstanciaObjeto _t = new InstanciaObjeto(admin, micon, idToElim);
							_t.setEliminado(false);
						}

						String eliminados = "ninguno";
						try{
							eliminados = (String)request.getParameter("opcionbase");
						}catch(Exception ee){
							ee.printStackTrace();
							eliminados = "ninguno";
						}
						
						Vector _insObj = new Vector();
						if(eliminados.contains("eliminados")){
							_insObj = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0, true);
						}else{
							_insObj = InstanciaObjeto.todosObjetosInstanciados(admin, micon, false, 0, false);
						}
						request.setAttribute("objetosInstanciados", _insObj);
						view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
					}
				}

				view.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				//error voy a pantalla principal
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
