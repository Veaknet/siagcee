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
 * Fecha: 12/04/2010
 * Hora: 06:24:46 PM
 */
public class SqlPreview extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/sqlpreview.jsp");
		if(admin != null){
			try{
				String _strListadoPreguntasDeseadas  = "";
				String _sqlFinal = "";
				if(request.getParameter("generadorSql_sqlFinal") != null){
					_sqlFinal = request.getParameter("generadorSql_sqlFinal");
				}
				request.setAttribute("generadorSql_sqlFinal", _sqlFinal);

				InstanciaObjeto _objetoatrabajar = null;
				if(request.getParameter("objetoatrabajar") != null){
					_objetoatrabajar = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
				}
				request.setAttribute("objetoatrabajar", _objetoatrabajar);

				String[] _listadoPreguntasDeseadas = request.getParameterValues("preguntasdeseadas");
				if(_listadoPreguntasDeseadas != null){
					for(int p = 0; p < _listadoPreguntasDeseadas.length; ++p){
						String _miParam = _listadoPreguntasDeseadas[p];
						if(p == 0){
							_strListadoPreguntasDeseadas = _miParam;
						}else{
							_strListadoPreguntasDeseadas = _strListadoPreguntasDeseadas+", "+ _miParam;
						}
					}
				}
				PreparedStatement pstmt;
				if(_listadoPreguntasDeseadas.length == 0){
					pstmt = micon.prepareStatement("SELECT * FROM respuestas WHERE elaborado_por IN (SELECT elaborado_por FROM respuestas WHERE "+_sqlFinal+") ORDER BY elaborado_por");
				}else{
					pstmt = micon.prepareStatement("SELECT * FROM respuestas WHERE id_instancia_preguntas IN ("+_strListadoPreguntasDeseadas+") AND elaborado_por IN (SELECT elaborado_por FROM respuestas WHERE "+_sqlFinal+") ORDER BY elaborado_por");
				}
				ResultSet _lasResp = pstmt.executeQuery();
				Vector _tempVec = new Vector();
				while(_lasResp.next()){
					_tempVec.add(new Respuesta(admin, micon, _lasResp.getInt("id_respuestas")));
				}
				request.setAttribute("respuestas", _tempVec);

				PreparedStatement pstmt2 = micon.prepareStatement("SELECT * FROM instancia_preguntas WHERE id_instancia_preguntas IN ("+_strListadoPreguntasDeseadas+") ORDER BY orden_pregunta");
				ResultSet _lasPreg = pstmt2.executeQuery();
				Vector _tempVec2 = new Vector();
				while(_lasPreg.next()){
					_tempVec2.add(new InstanciaPregunta(admin, micon, _lasPreg.getInt("id_instancia_preguntas")));
				}
				request.setAttribute("preguntas", _tempVec2);

                //es para exportar a Excel
				if(request.getParameter("accionextra") != null && request.getParameter("accionextra").equals("exportaexcel")){

                    String _nombreArchivo = getServletContext().getAttribute("javax.servlet.context.tempdir")+"/"+_objetoatrabajar.getId()+".xls";

                    UtilidadesVarias.generaExcel(_nombreArchivo, _tempVec2, _tempVec, _objetoatrabajar);

                    view = request.getRequestDispatcher("WEB-INF/tmp/"+_objetoatrabajar.getId()+".xls");
				}

				view.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/sqlpreview.jsp");
		if(admin != null){
			try{
				view.forward(request, response);
			}catch(Exception e){
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
