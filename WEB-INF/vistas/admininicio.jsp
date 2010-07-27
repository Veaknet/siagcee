<%@page import="java.util.Collections" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@page import="com.siagcee.logic.*" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
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
HttpSession sesion = request.getSession();
Administrador admin = (Administrador)sesion.getAttribute("administrador");
if(admin == null){
%>
	<jsp:forward page="autenticar.do"/>
<%
}
%>
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
		<script type="text/javascript" src="comunes/javascript/jquery.tablesorter.min.js"></script>
		<script type="text/javascript" src="comunes/javascript/espanol.js"></script>
		<script type="text/javascript" src="comunes/javascript/whizzywig61.js"></script>
	</head>
