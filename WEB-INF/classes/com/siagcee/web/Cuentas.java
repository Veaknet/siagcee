package com.siagcee.web;

import com.siagcee.logic.Administrador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 02/02/2010
 * Hora: 09:59:31 PM
 */
public class Cuentas extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/cuentas.jsp");
		String mensaje = "Datos Actualizados";
		if(admin != null){
			String accion = "";
			try{
				int idUsuario = -1;
				if(request.getParameter("idusuario") != null){
					idUsuario = Integer.parseInt((String)request.getParameter("idusuario"));
					accion = "listarUno";
				}
				Administrador _temp = null;
				if(idUsuario == -1){
					_temp = new Administrador(micon, ((String)request.getParameter("login")), ((String)request.getParameter("clave")), false);
					_temp = new Administrador(micon, _temp.getUsuarioId());
					if(_temp.getUsuarioId() == -1){
						//usuario ya existía
						mensaje = "Nombre de usuario no disponible.";
						request.setAttribute("mensaje", mensaje);
					}
				}else{
					_temp = new Administrador(micon, idUsuario);
					if(!request.getParameter("clave").equals("")){
						_temp.setClave((String)request.getParameter("clave"));
					}
				}
				_temp.setEmail((String)request.getParameter("email"));
				_temp.setNombre((String)request.getParameter("nombre"));
				_temp.setTipoUsuario((String)request.getParameter("tipousuario"));
				request.setAttribute("mensaje", mensaje);

				request.setAttribute("usuario", _temp);
				
				view.forward(request, response);

			}catch(Exception e){
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
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/cuentas.jsp");
		if(admin != null){
			String accion = "listarTodos";
			try{
				int idUsuario = -1;
				if(request.getParameter("idusuario") != null){
					idUsuario = Integer.parseInt((String)request.getParameter("idusuario"));
					accion = "listarUno";
				}
				if(admin.getTipoUsuario().equals("regular")){
					idUsuario = admin.getUsuarioId();
					accion = "listarUno";
				}
				if(request.getParameter("accion") != null){
					accion = (String)request.getParameter("accion");
				}

				String mensaje = "";
				if(accion.equals("listarUno")){
					Administrador _usuarioAdministrado = new Administrador(micon, idUsuario);
					request.setAttribute("usuario", _usuarioAdministrado);
				}else if(accion.equals("delete")){
					Administrador _usuarioAdministrado = new Administrador(micon, idUsuario);
					_usuarioAdministrado.delUsuario();
					_usuarioAdministrado = new Administrador(micon, idUsuario);
					if(_usuarioAdministrado.getUsuarioId() != -1){
						mensaje = "No fu&eacute; posible eliminar usuario.<br />Es posible que existan objetos asociados a este administrador.";
					}
					request.setAttribute("usuarios", Administrador.dameTodos(micon));
					accion = "listarTodos";
				}else{
					request.setAttribute("usuarios", Administrador.dameTodos(micon));
				}
				request.setAttribute("mensaje", mensaje);
				request.setAttribute("accion", accion);
				view.forward(request, response);

			}catch(Exception e){
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
