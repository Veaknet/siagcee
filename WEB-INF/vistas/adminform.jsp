<%@page session="true" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<html>
	<head>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>SIGECENE - Sistema Para La Gestión De Censos, Encuestas y Estudios</title>
		<link type="image/x-icon" href="comunes/imagenes/favicon.ico" rel="icon" />
		<link type="image/x-icon" href="comunes/imagenes/favicon.ico" rel="shortcut icon" />
		<link rel="stylesheet" type="text/css" href="comunes/css/micss.css">
		<!-- IE 6 "fixes" -->
		<!--[if lt IE 7]>
		<link type='text/css' href='comunes/css/basic_ie.css' rel='stylesheet' media='screen' />
		<![endif]-->
		<script type="text/javascript" src="comunes/javascript/jquery.js"></script>
		<script type="text/javascript" src="comunes/javascript/jquery.simplemodal.js"></script>
	</head>
	<body>
		<center>
		<table cellpadding="0" cellspacing="0" class="tablaprincipal">
			<tr>
				<td valign="middle" align="left" style="border-top: burlywood ridge thick;border-left: burlywood ridge thick;border-width:3px;max-width:320px;width:320px">
					<a href="autenticar.do"><img src="comunes/imagenes/siagcee_logo.png" alt=""></a>
				</td>
			</tr>
			<tr>
				<td valign="top" align="center">
					<center>
<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 10/12/2009
 * Hora: 09:07:17 AM
-->
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
%>
	<div style="text-align:center;">
		<form method="post" action="autenticar.do">
			<table cellpadding="1" cellspacing="4" style="max-width:700px;width:950px" class="tablasecundariatitulo">
				<tr>
					<td>
						&nbsp;<p /><br />
					</td>
				</tr>
			</table>
			<table cellpadding="1" cellspacing="4" style="max-width:700px;width:950px" class="tablasecundaria">
				<tr>
					<td colspan="3">
						<h3>Bienvenido a SIGECENE</h3>
						<%
						if (!mensaje.equals("")) {
							out.println(mensaje + "<p />");
						}
						if(accion.equals("mostrarForm")){
							%>
							Indique sus credenciales en el siguiente formulario:<p />
					</td>
				</tr>
				<tr>
					<td rowspan="3" valign="top" style="max-width:75px"><img src="comunes/imagenes/user-id.png" alt=""></td>
					<td style="text-align:right">Usuario:</td><td style="text-align:left"><input type="text" name="usuario" value="administrador"></td>
				</tr>
				<tr>
					<td style="text-align:right">Clave:</td><td style="text-align:left"><input type="password" name="clave" value="clave"></td>
				</tr>
				<tr>
					<td>&nbsp;</td><td><input type="submit" value="Acceder"></td>
				</tr>
			</table>
		</form>
	</div>
	<%
}
%>
<%@include file="adminfooter.jsp" %>
