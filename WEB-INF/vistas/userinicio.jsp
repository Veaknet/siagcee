<%@page import="com.siagcee.logic.Encuestado" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%
HttpSession sesion = request.getSession();
Encuestado encuestado = (Encuestado)sesion.getAttribute("usuario");
if(encuestado == null){
%>
	<jsp:forward page="autenticarusuario.do"/>
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
		<link rel="stylesheet" type="text/css" href="comunes/css/micss_print.css" media="print">
		<!-- IE 6 "fixes" -->
		<!--[if lt IE 7]>
		<link type='text/css' href='comunes/css/basic_ie.css' rel='stylesheet' media='screen' />
		<![endif]-->
		<script type="text/javascript" src="comunes/javascript/jquery.js"></script>
		<script type="text/javascript" src="comunes/javascript/jquery.simplemodal.js"></script>
	</head>
