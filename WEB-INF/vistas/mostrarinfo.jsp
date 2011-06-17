<%@page import="com.siagcee.logic.Objeto" %>
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
 * Fecha: 16/12/2009
 * Hora: 10:56:47 PM
-->

<body>
	<table cellpadding="0" cellspacing="0" class="tablaprincipal">
		<tr>
			<td valign="middle" align="left" style="border-top: burlywood ridge thick;border-left: burlywood ridge thick;border-width:3px;max-width:320px;width:320px">
				<img src="comunes/imagenes/siagcee_logo.png" alt="">
			</td>
		</tr>
		<tr>
			<td valign="top" align="left">
				<table cellpadding="4" cellspacing="4" class="tablasecundariatitulo" style="width:600px;min-width:600px;max-width:600px;">
					<tr>
						<td style="text-align:left" valign="top" >
							<h2>Informaci&oacute;n</h2>
						</td>
					</tr>
				</table>
				<table cellspacing="4" cellpadding="4" class="tablasecundaria" style="width:600px;min-width:600px;max-width:600px;">
					<tr>
						<td style="text-align:left" valign="top">

<%

String accion = "";
if(request.getAttribute("accion") != null){
	accion = (String)request.getAttribute("accion");
}

String mensaje = "";
if(request.getAttribute("mensaje") != null){
	mensaje = (String)request.getAttribute("mensaje");
	request.setAttribute("mensaje", "");
}

Objeto _miObj = null;
Pregunta _miPreg = null;
EstudioPerso _miEst = null;
if(accion.equals("mostrarinfodepregunta")){
	_miPreg = (Pregunta)request.getAttribute("pregunta");
	out.println("<h3>"+_miPreg.getPregunta()+"</h3>");
}else if(accion.equals("mostrarinfodeobjeto")){
	_miObj = (Objeto)request.getAttribute("objeto");
	out.println("<h3>"+_miObj.getObjeto()+"</h3>");
	if(_miObj.getClass().toString().contains("Censo")){
		out.println("Instrumento para Censos.<p />");
	}
	if(_miObj.getClass().toString().contains("Encuesta")){
		out.println("Instrumento para Encuestas.<p />");
	}
	if(_miObj.getClass().toString().contains("Relacion")){
		out.println("Colecci&oacute;n de Datos.<p />");
	}
	if(_miObj.getClass().toString().contains("EstructuraBase")){
		out.println("Estructura Base.<p />");
	}
}else if(accion.equals("mostrarinfodeestudio")){
	_miEst = (EstudioPerso)request.getAttribute("estudio");
	out.println("<h3>"+_miEst.get_titulo()+"</h3>");
}

if (!mensaje.equals("")) {
	out.println(mensaje + "<p />");
}

if(_miObj != null){
	if(_miObj.getCargadaDeBD()){
		//muestro toda la info publica del objeto
		Vector _misPregs = _miObj.getPreguntas(true);
		Collections.sort(_misPregs, new OrdenadorInstanciaPreguntas());
		Enumeration _misPregsEnu = _misPregs.elements();
		while(_misPregsEnu.hasMoreElements()){
			InstanciaPregunta _temp = (InstanciaPregunta)_misPregsEnu.nextElement();
			out.println("<h4>"+String.valueOf(_temp.getOrden())+". "+_temp.getTextoPregunta());
			if(_temp.isRequerida()){
				out.println("<img src='comunes/imagenes/promotion.png' height='24' alt='Pregunta obligatoria' title='Pregunta obligatoria'>");
			}
			if(_temp.isCampo_clave_unico()){
				out.println("<img src='comunes/imagenes/primary_key_icon.jpg' height='24' alt='Pregunta clave' title='Pregunta clave'>");
			}
			if(_temp.isCampo_comunicacion_email()){
				out.println("<img src='comunes/imagenes/email.png' height='24' alt='Elemento de comunicaci&oacute;n por email' title='Elemento de comunicaci&oacute;n por email'>");
			}
			out.println("<br />");
			out.println("(Acr&oacute;nimo: "+_temp.getAcronimo()+")<br />");
			out.println("(Pregunta: "+_temp.getPreguntaAsociada().getPregunta()+")</h4>");
			if(_temp.getTipoPregunta() == 1){
				out.print("Cerrada de selecci&oacute;n simple, con los siguientes valores:<p />");
			}
			if(_temp.getTipoPregunta() == 2){
				out.print("Cerrada de selecci&oacute;n m&uacute;ltiple, con los siguientes valores:<p />");
			}
			if(_temp.getTipoPregunta() == 30){
				out.print("Abierta que acepta cualquier texto sin validaciones adicionales.<p />");
			}
			if(_temp.getTipoPregunta() == 31){
				out.print("Abierta que acepta &uacute;nicamente n&uacute;meros sin decimales.<p />");
			}
			if(_temp.getTipoPregunta() == 32){
				out.print("Abierta que acepta &uacute;nicamente n&uacute;meros con o sin decimales.<p />");
			}
			if(_temp.getTipoPregunta() == 33){
				out.print("Abierta que acepta &uacute;nicamente fechas.<p />");
			}
			if(_temp.getTipoPregunta() == 100){
				out.print("Abierta que no ser&aacute; mostrado a los encuestados y su utilidad es proveer un campo en la estructura para guardar valores de un estudio en particular.<p />");
			}
			if(_temp.getTipoPregunta() < 30){
				Vector _misResp = _temp.respuestasPosibles();
				Enumeration _misRespEnu = _misResp.elements();
				while(_misRespEnu.hasMoreElements()){
					RespuestasPosibles _tempResp = (RespuestasPosibles)_misRespEnu.nextElement();
					out.println(" - "+_tempResp.getRespuesta()+"<br />");
				}
			}
		}
	}else{
	  out.println("No se ha encontrado la estructura deseada.");
	}
}
if(_miPreg != null){
	if(_miPreg.getCargadaDeBD()){
		out.println("Pregunta ");
		if(_miPreg.getTipoPregunta() == 1){
			out.print("Cerrada de selecci&oacute;n simple, con los siguientes valores:<p />");
		}
		if(_miPreg.getTipoPregunta() == 2){
			out.print("Cerrada de selecci&oacute;n m&uacute;ltiple, con los siguientes valores:<p />");
		}
		if(_miPreg.getTipoPregunta() == 30){
			out.print("Abierta que acepta cualquier texto sin validaciones adicionales.<p />");
		}
		if(_miPreg.getTipoPregunta() == 31){
			out.print("Abierta que acepta &uacute;nicamente n&uacute;meros sin decimales.<p />");
		}
		if(_miPreg.getTipoPregunta() == 32){
			out.print("Abierta que acepta &uacute;nicamente n&uacute;meros con o sin decimales.<p />");
		}
		if(_miPreg.getTipoPregunta() == 33){
			out.print("Abierta que acepta &uacute;nicamente fechas.<p />");
		}
		if(_miPreg.getTipoPregunta() == 100){
			out.print("Abierta que no ser&aacute; mostrado a los encuestados y su utilidad es proveer un campo en la estructura para guardar valores de un estudio en particular.<p />");
		}
		if(_miPreg.getTipoPregunta() < 30){
			Vector _misResp = _miPreg.retornaRespuestasPosibles();
			Enumeration _misRespEnu = _misResp.elements();
			while(_misRespEnu.hasMoreElements()){
				RespuestasPosibles _tempResp = (RespuestasPosibles)_misRespEnu.nextElement();
				out.println(" - "+_tempResp.getRespuesta()+"<br />");
			}
		}
	}else{
		out.println("No se ha encontrado la pregunta deseada.");
	}
}
if(_miEst != null){
	if(_miEst.getCargadaDeBD()){
		out.println(_miEst.get_titulo()+"<p />");
	}else{
		out.println("No se ha encontrado el estudio deseado.");
	}
}
	if(accion.equals("mostrarinfodeobjeto")){
		if((request.getParameter("perohaz") != null) && (request.getParameter("perohaz").equals("eliminar"))){
%>
							<div style='text-align:right'><input type="button" value="Eliminar" onclick='if(confirm("Realmente proceder con la eliminaci&oacute;n?")){window.opener.location = "adminobjetos2.do?tipoinstrumento=<% out.print(request.getParameter("tipoinstrumento"));%>&opcionbase=<% out.print(request.getParameter("opcionbase"));%>&accionobjeto=eliminar&objetoseleccionado=<% out.print(_miObj.getId());%>";window.close();}' title="Eliminar" alt="Eliminar" /></div>
<%
		}
	}else{
		if((request.getParameter("perohaz") != null) && (request.getParameter("perohaz").equals("eliminar"))){
%>
							<div style='text-align:right'><input type="button" value="Eliminar esta pregunta" onclick='if(confirm("Realmente desea eliminar esta pregunta?")){window.opener.location = "adminpreguntas.do?accion=eliminar&preguntaseleccionada=<% out.print(_miPreg.getId());%>";window.close();}' title="Eliminar" alt="Eliminar" /></div>
<%
		}
	}
%>
							<div style='text-align:right'><input type="button" value="Cerrar esta ventana" onclick="window.close();" title="Cerrar esta ventana" alt="Cerrar esta ventana" /></div>
						</td>
					</tr>
				</table>
				<script type="text/javascript" >
					function limpiarMsj(){
						window.opener.document.getElementById('id_encapsulador').style.display = 'none';
					}
					$(document).ready(function(){
						limpiarMsj();
						setTimeout(limpiarMsj,1000);
					});
				</script>
<%
if(accion.equals("mostrarinfodeobjeto")){
 	if((request.getParameter("perohaz") != null) && (request.getParameter("perohaz").equals("eliminar"))){
	_miObj = (Objeto)request.getAttribute("objeto");
%>
	<script type="text/javascript" >
		$(document).ready(function(){
			if(confirm('Realmente desea eliminar <% out.print(_miObj.getObjeto()); %> y las preguntas asociadas?')){
				window.opener.location = "adminobjetos2.do?tipoinstrumento=<% out.print(request.getParameter("tipoinstrumento"));%>&opcionbase=<% out.print(request.getParameter("opcionbase"));%>&accionobjeto=eliminar&objetoseleccionado=<% out.print(_miObj.getId());%>";
				window.close();
			}
		});
	</script>
<% }
}else{
 	if((request.getParameter("perohaz") != null) && (request.getParameter("perohaz").equals("eliminar"))){
	_miPreg = (Pregunta)request.getAttribute("pregunta");
%>
	<script type="text/javascript" >
		$(document).ready(function(){
			if(confirm('Realmente desea eliminar esta pregunta?')){
				window.opener.location = "adminpreguntas.do?accion=eliminar&preguntaseleccionada=<% out.print(_miPreg.getId());%>";
				window.close();
			}
		});
	</script>
<%	}
}
 %>
<%@include file="adminfooter.jsp" %>