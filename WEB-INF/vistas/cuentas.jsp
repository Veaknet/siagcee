<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Enumeration" %>
<%@include file="admininicio.jsp" %>

<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 14/12/2009
 * Hora: 09:52:10 AM
-->

<%@include file="adminheader.jsp" %>
<%

Vector _usuarios = new Vector();
if(request.getAttribute("usuarios") != null){
	_usuarios = (Vector)request.getAttribute("usuarios");
	Collections.sort(_usuarios, new OrdenadorUsuarios());
}

Administrador _usuario = null;
if(request.getAttribute("usuario") != null){
	_usuario = (Administrador)request.getAttribute("usuario");
}

String _accion = "";
if(request.getAttribute("accion") != null){
	_accion = (String)request.getAttribute("accion");
}

String _mensaje = "";
if(request.getAttribute("mensaje") != null){
	_mensaje = (String)request.getAttribute("mensaje");
}

%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#opciones_link").css("color","red");
	});
</script>
<table cellspacing="4" cellpadding="4" class="tablasecundaria">
	<tr>
		<td>
<%
	if(_accion.equals("listarTodos")){
		out.println("<h4>Cuentas de Administradores.</h4>");
		if(!_mensaje.equals("")){
			out.println("<div style='color:green'>"+_mensaje+"</div>");
		}
		Enumeration _enu = _usuarios.elements();
		Administrador _tmp = null;
		out.println("<a href='cuentas.do?idusuario=-1'><img src='comunes/imagenes/add-user.png' height='24' title='Crear administrador' alt='Crear administrador'>&nbsp;Nuevo Administrador.</a><p />");
		while(_enu.hasMoreElements()){
			_tmp = (Administrador)_enu.nextElement();
			out.println("<a href='cuentas.do?accion=delete&idusuario="+_tmp.getUsuarioId()+"' onclick='if(confirm(\"Desea eliminar esta cuenta de administrador?.Esta accion no tiene regreso.\")){return true;}else{return false;}'><img src='comunes/imagenes/delete-user.png' height='24' title='Eliminar administrador' alt='Eliminar administrador'></a>");
			out.println("<a href='cuentas.do?idusuario="+_tmp.getUsuarioId()+"'><img src='comunes/imagenes/modify.png' alt='Modificar Datos del Administrador' title='Modificar Datos del Administrador' height='24'></a>");
			out.println("<a href='cuentas.do?idusuario="+_tmp.getUsuarioId()+"'>"+_tmp.getUsername()+"</a><br />");
		}
	}else{
		out.println("<h4>Datos.</h4>");
		if(!_mensaje.equals("")){
			out.println("<div style='color:red'>"+_mensaje+"</div>");
		}
		out.println("<form action='cuentas.do' method='post' id='formulario' name='formulario'>");
		out.println("<input type='hidden' id='idusuario' name='idusuario' value='"+_usuario.getUsuarioId()+"'>");
		out.println("<table cellpadding='4' cellspacing='4' border='0'>");
		if(_usuario.getUsuarioId() == -1){
			out.println("<tr><td><label>Usuario:</label></td><td><input type='text' id='login' name='login'></td></tr>");
		}else{
			out.println("<tr><td><label>Usuario:</label></td><td>"+_usuario.getUsername()+"</td></tr>");
		}
		out.println("<tr><td><label>Nombre Completo:</label></td><td><input type='text' id='nombre' name='nombre' value='"+_usuario.getNombre()+"'></td></tr>");
		out.println("<tr><td><label>Email:</label></td><td><input type='text' id='email' name='email' value='"+_usuario.getEmail()+"'></td></tr>");
		if(admin.getTipoUsuario().equals("regular")){
			out.println("<tr><td></td><td><input type='hidden' id='tipousuario' name='tipousuario' value='"+_usuario.getTipoUsuario()+"'></td></tr>");
		}else{
			out.println("<tr><td><label>Tipo Administrador:</label></td><td><select id='tipousuario' name='tipousuario'>");
			out.println("<option value='superadministrador'");
			if(_usuario.getTipoUsuario().equals("superadministrador")){
				out.println(" selected='selected' ");
			}
			out.println(">Super Administrador</option>");
			out.println("<option value='regular'");
			if(_usuario.getTipoUsuario().equals("regular")){
				out.println(" selected='selected' ");
			}
			out.println(">Administrador Regular</option>");
			out.println("</select></td></tr>");
		}
		out.println("<tr><td><label>Nueva clave:</label></td><td><input type='password' id='clave' name='clave' value=''>&nbsp;*Solo si desea modificar la clave.</td></tr>");
		out.println("<tr><td><label>Reingrese la clave:</label></td><td><input type='password' id='clave2' name='clave2' value=''></td></tr>");
		out.println("<tr><td>&nbsp;</td><td><input type='submit' value='Enviar' onclick='if(document.formulario.clave.value == document.formulario.clave2.value){return true;}else{alert(\"Las claves no coinciden.\");return false;}'></td></tr>");
		out.println("</table>");
		out.println("</form>");
	}
%>
		</td>
	</tr>
</table>
<%@include file="adminfooter.jsp" %>