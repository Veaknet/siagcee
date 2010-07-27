<%@include file="admininicio.jsp" %>

<head>
<script type="text/javascript">
function cerrarDiv(){
	var formularioRespuesta = document.getElementById("formularioRespuesta");
	if(formularioRespuesta != null){
		formularioRespuesta.style.display = "none";
	}
	formularioRespuesta = document.getElementById("subtitulo");
	if(formularioRespuesta != null){
		formularioRespuesta.innerHTML = "";
	}
}

function compruebaNoVacio(elem){
	if(elem){
		if(elem.value != ""){
			return true;
		}
		alert('Debe indicar el valor.');
		elem.focus();
		elem.style.backgroundColor = 'yellow';
		return false;
	}
	return false;
}

function habilitarInsertDiv(){
	var formulario = document.getElementById("formularioRespuesta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var respuestaid = document.getElementById("respuestaid");
	var valor = document.getElementById("valor");
	formulario.style.display = "block";
	accion.value = "insertar";
	respuestaid.value = "";
	boton.value = "Agregar";
	valor.value = "";
	valor.focus();

}
function habilitarUpdateDiv(id, texto){
	var formulario = document.getElementById("formularioRespuesta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var respuestaid = document.getElementById("respuestaid");
	var valor = document.getElementById("valor");
	formulario.style.display = "block";
	accion.value = "actualizar";
	respuestaid.value = id;
	boton.value = "Guardar Cambios";
	valor.value = texto;
	valor.focus();

}
</script>
</head>

<%@include file="adminheader.jsp" %>

<%
if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}
Vector _respuestas = (Vector)request.getAttribute("respuestas");
Pregunta preguntaatrabajar = (Pregunta)request.getAttribute("preguntaatrabajar");

String _opcionBase = "crear";
if(request.getParameter("opcionbase")!=null){
	_opcionBase = request.getParameter("opcionbase");
}
%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_preguntas").css("color","red");
	});
</script>

<table cellspacing="2" cellpadding="2" class="tablasecundaria">
	<tr>
		<td  valign="top" colspan="4">
			<h2>Definir Posibles Respuestas</h2>
		</td>
	</tr>
	<tr>
		<td valign="top" style="width:25px"><img src="comunes/imagenes/add.png" height="24" alt="Ingresar Nueva Respuesta Posible" title="Ingresar Nueva Respuesta Posible" onclick="habilitarInsertDiv()"/></td>
		<td valign="top" style="width:25px"><a href="adminrespuestas3.do?preguntaseleccionada=<% out.print(preguntaatrabajar.getId());%>"><img src="comunes/imagenes/load.png" height="24" alt="Importar Respuestas Posibles Desde Otra Pregunta" title="Importar Respuestas Posibles Desde Otra Pregunta" /></a></td>
		<td valign="top" style="min-width:50%; width:50%; max-width:50%" align="left">
			<%
			if(preguntaatrabajar != null){
			%>
				<strong>Pregunta: <% out.print(preguntaatrabajar.getPregunta());%></strong><p />
			<%
			boolean showForm = false;
				if((_respuestas == null) || (_respuestas.isEmpty())){
					showForm = true;
					%>
					No exiten respuestas para esta pregunta.<br />Puede crearlas en el siguiente formulario:<p />
					<script type="text/javascript"></script>
					<%
				}else{
					Enumeration respuestasDisponibles = _respuestas.elements();
					RespuestasPosibles miRespuesta = null;
					%>
					<h4>Respuestas Incorporadas.</h4>
					<table cellpadding="3" cellspacing="3" border="0" style="max-width:500px;">
						<%
						while(respuestasDisponibles.hasMoreElements()){
							miRespuesta	= (RespuestasPosibles)respuestasDisponibles.nextElement();
						%>
						<tr>
							<td valign="top" align="left"><a href="adminrespuestas2.do?opcionprincipal=plantillapreguntas&accion=eliminar&preguntaseleccionada=<% out.print(preguntaatrabajar.getId());%>&respuestaid=<%	out.print(miRespuesta.getId()); %>" onclick="return confirm('Realmente desea eliminar este valor?');"><img src="comunes/imagenes/delete.png" title="Eliminar Posible Respuesta" alt="Eliminar Posible Respuesta" height="24"></a></td>
							<td valign="top" align="left"><a href="#" onclick="habilitarUpdateDiv(<%	out.print(miRespuesta.getId()); %>, '<%	out.print(miRespuesta.getRespuesta()); %>')"><img src="comunes/imagenes/modify.png" title="Modificar Posible Respuesta" alt="Modificar Posible Respuesta" height="24"></a></td>
							<td valign="top" align="left" width="100%"><a href="#" title="Haga clic aqu&iacute; para modificar esta respuesta" onclick="habilitarUpdateDiv(<% out.print(miRespuesta.getId()); %>, '<%	out.print(miRespuesta.getRespuesta()); %>')">
								<%	out.print(miRespuesta.getRespuesta()); %>
								</a>
							</td>
						</tr>
						<%
						}
						%>
					</table><p />
					<%
				}
			%>
			</td>
			<td style='max-width:50%;min-width:50%;width:50%;' valign="top" align="left">
			<div style='display:block;' name="formularioRespuesta" id="formularioRespuesta">
				<h4><span id="subtitulo" name="subtitulo"></span></h4>
					<form action="adminrespuestas2.do" method="post">
						<input type="hidden" value="<% out.print(preguntaatrabajar.getId());%>" name="preguntaseleccionada">
						<input type="hidden" value="insertar" name="accion" id="accion">
						<input type="hidden" value="plantillapreguntas" name="opcionprincipal" id="opcionprincipal">
						<input type="hidden" value="" name="respuestaid" id="respuestaid">
						<label>Posible Respuesta:</label><br /><input type="text" value="" name="valor" id="valor" size="30" onkeyup="this.style.backgroundColor = '';"><p />
						<input type="submit" value="Agregar Respuesta" name="boton" id="boton" onclick="return compruebaNoVacio(getElementById('valor'))">
						<input type="button" value="Cancelar" onclick="cerrarDiv()">
					</form>
				</div>
			<%
			}else { %>
				<span class="indicativo">No se ha indicado la pregunta. Haga clic <a href="adminpreguntas.do">aqu&iacute;</a> para regresar.</span>
			<%
			}
			%>&nbsp;
		</td>
	</tr>
</table>
<script type="text/javascript" >$("#valor").focus();</script>
<%@include file="adminfooter.jsp" %>
