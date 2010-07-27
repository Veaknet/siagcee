<%@include file="admininicio.jsp" %>

<head>
<script type="text/javascript">
</script>
</head>

<%@include file="adminheader.jsp" %>

<%
String _opcionprincipal = "";
if (request.getAttribute("opcionprincipal") != null) {
	_opcionprincipal = (String)request.getAttribute("opcionprincipal");
}

String _titulo = "";	
if(_opcionprincipal.equals("plantillapreguntas")){
	_titulo = "Preguntas";
}else if(_opcionprincipal.equals("estructurasbases")){
	_titulo = "Estructuras Bases";
}else if(_opcionprincipal.equals("instrumentos")){
	_titulo = "Instrumentos";
}else if(_opcionprincipal.equals("estudios")){
	_titulo = "Estudios";
}else if(_opcionprincipal.equals("generados")){
	_titulo = "Instrumentos Generados";
}else if(_opcionprincipal.equals("relaciones")){
	_titulo = "Colecciones de Datos";
}
%>

<table cellpadding="4" cellspacing="4" class="tablasecundariatitulo">
	<tr>
		<td style="text-align:left" valign="top">
			<h2><% out.print(_titulo); %></h2>
		</td>
		<td style="text-align:right" valign="bottom" width="36px">
			<% if(_opcionprincipal.equals("estudios")){ %>
				<% if((sesion.getAttribute("retornoDireccion")!=null) && ((String)sesion.getAttribute("retornoDireccion")).equals("admininsobj.do")){ %>
					<a href="admininsobj.do"><img src="comunes/imagenes/back.png" title="Regresar a la Secci&oacute;n Anterior" alt="Regresar a la Secci&oacute;n Anterior" ></a>
				<% }else{ %>
					<a href="estudios.do" ><img src="comunes/imagenes/back.png" title="Regresar a la Secci&oacute;n Anterior" alt="Regresar a la Secci&oacute;n Anterior" ></a>
				<% } %>
			<% }else{ %>
				<a href="autenticar.do" ><img src="comunes/imagenes/back.png" title="Regresar a la Secci&oacute;n Anterior" alt="Regresar a la Secci&oacute;n Anterior" ></a>
			<% } %>
		</td>
	</tr>
</table>

<%
String _mensaje = "";
if (request.getAttribute("mensaje") != null) {
	_mensaje = (String)request.getAttribute("mensaje");
}

if(!_mensaje.equals("")){
	out.println(_mensaje);
}
%>
<table cellspacing="2" cellpadding="2" class="tablasecundaria">
	<tr>
		<td style="text-align:right" valign="top">
			<img height="24" src="comunes/imagenes/info-about.png" title="Mostrar ayuda sobre este t&oacute;pico" alt="Mostrar ayuda sobre este t&oacute;pico" onclick='window.open("ayuda.do?tema=tipodedatos","ayuda","width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");'>
		</td>
	</tr>
	<tr>
		<td valign="top" align="center" style="text-align:center;">
		<center>
			<table cellpadding="4" cellspacing="4" border="0" width="500" style="text-align:center;padding-bottom:30px">
				<tr>
					<% if(_opcionprincipal.equals("plantillapreguntas")){ %>
					<td>
						<a href="adminpreguntas.do?opcionbase=crear"><img src="comunes/imagenes/file-add.png" height="38" title="Crear Pregunta"><br />Crear</a>
					</td>
					<td>
						<a href="adminpreguntas.do?opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="38" title="Modificar Pregunta"><br />Modificar</a>
					</td>
					<td>
						<a href="adminpreguntas.do?opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="38" title="Eliminar Pregunta"><br />Eliminar</a>
					</td>
					<td>
						<a href="adminpreguntas.do?opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="38" title="Revisar Preguntas Existentes Que no se Pueden Modificar"><br />Revisar</a>
					</td>
					<% }else if(_opcionprincipal.equals("estructurasbases")){ %>
						<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
							<td>
								<a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="38" title="Armar Estructura Base"><br />Crear</a>
							</td>
							<td>
								<a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="38" title="Modificar Estructura Base"><br />Modificar</a>
							</td>
							<td>
								<a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=crear"><img src="comunes/imagenes/file-check.png" height="38" title="Publicar Estructura Base Previamente Armada"><br />Publicar</a>
							</td>
							<td>
								<a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="38" title="Eliminar Estructura Base"><br />Eliminar</a>
							</td>
						<% } %>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="38" title="Revisar Estructuras Bases Que no se Pueden Modificar"><br />Revisar</a>
						</td>
					<% }else if(_opcionprincipal.equals("instrumentos")){ %>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="38" title="Crear Instrumento"><br />Crear</a>
						</td>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="38" title="Modificar Instrumento"><br />Modificar</a>
						</td>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=crear"><img src="comunes/imagenes/file-check.png" height="38" title="Publicar Instrumento Previamente Armado"><br />Publicar</a>
						</td>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="38" title="Eliminar Instrumento"><br />Eliminar</a>
						</td>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="38" title="Revisar Instrumentos Que no se Pueden Modificar"><br />Revisar</a>
						</td>
					<% }else if(_opcionprincipal.equals("estudios")){ %>
						<td>
							<a href="generadorestudios?desdeindex=true&opcionbase=crear&objetoatrabajar=<% out.print(request.getParameter("estructura")); %>"><img src="comunes/imagenes/file-add.png" height="38" title="Crear Estudio"><br />Crear</a>
						</td>
						<td>
							<a href="estudios.do?desdeindex=true&opcionbase=modificar&estructura=<% out.print(request.getParameter("estructura")); %>"><img src="comunes/imagenes/file-edit.png" height="38" title="Modificar Estudio"><br />Modificar</a>
						</td>
						<td>
							<a href="estudios.do?desdeindex=true&opcionbase=eliminar&estructura=<% out.print(request.getParameter("estructura")); %>"><img src="comunes/imagenes/file-delete.png" height="38" title="Eliminar Estudio"><br />Eliminar</a>
						</td>
					<% }else if(_opcionprincipal.equals("generados")){ %>
						<td>
							<a href="admininsobj.do?opcionbase=pendientes"><img src="comunes/imagenes/file-pendiente.png" height="38" title="Mostrar S&oacute;lo los Instrumentos Pendientes"><br />Instrumentos Pendientes</a>
						</td>
						<td>
							<a href="admininsobj.do?opcionbase=enejecucion"><img src="comunes/imagenes/file-ejecucion.png" height="38" title="Mostrar S&oacute;lo los Instrumentos Actualmente en Ejecuci&oacute;n"><br />Instrumentos En Ejecuci&oacute;n</a>
						</td>
						<td>
							<a href="admininsobj.do?opcionbase=finalizados"><img src="comunes/imagenes/file-finalizado.png" height="38" title="Mostrar S&oacute;lo los Instrumentos Finalizados"><br />Instrumentos Finalizados</a>
						</td>
						<!--// <td>
							<a href="admininsobj.do?opcionbase=relaciones"><img src="comunes/imagenes/file-colecciones.png" height="38" title="Mostrar S&oacute;lo las Colecciones de Datos"><br />Colecciones de Datos</a>
						</td>  //-->
						<td>
							<a href="admininsobj.do?opcionbase=todos"><img src="comunes/imagenes/file-todos.png" height="38" title="Mostrar Instrumentos Pendientes, Actualmente en Ejecuci&oacute;n y Finalizados"><br />Mostrar Todos</a>
						</td>
					<% }else if(_opcionprincipal.equals("relaciones")){ %>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="38" title="Crear Coleccion de Datos"><br />Crear</a>
						</td>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="38" title="Modificar Coleccion de Datos"><br />Modificar</a>
						</td>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="38" title="Eliminar Coleccion de Datos"><br />Eliminar</a>
						</td>
						<td>
							<a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=revisar"><img src="comunes/imagenes/file-edit.png" height="38" title="Revisar Coleccion de Datos"><br />Revisar</a>
						</td>
						<!--// <td>
							<a href="admininsobj.do?opcionbase=relaciones&retornasoloa=relaciones"><img src="comunes/imagenes/file-review.png" height="38" title="Mostrar Todas las Colecciones de Datos"><br />Revisar Todas</a>
						</td> //-->
					<% } %>
				</tr>
			</table>
			</center>
		</td>
	</tr>
</table>

<%@include file="adminfooter.jsp" %>

