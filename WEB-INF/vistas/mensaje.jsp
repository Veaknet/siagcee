<%@page session="true" %>
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

<script type="text/javascript" charset="UTF8">


</script>
<%@include file="adminheader.jsp" %>
<%

String _accion = "";
if(request.getAttribute("accion") != null){
	_accion = (String)request.getAttribute("accion");
}

String _mensaje = "";
if(request.getAttribute("mensaje") != null){
	_mensaje = (String)request.getAttribute("mensaje");
}

String _titulo = "";
if(request.getAttribute("titulo") != null){
	_titulo = (String)request.getAttribute("titulo");
}

%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#opciones_link").css("color","red");
	});
</script>

<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
			<h2>Enviar un mensaje por correo electr&oacute;nico</h2>
		</td>
	</tr>
	<tr>
		<td>
			<% if(_accion.equals("finalizado")){ %>
				Se ha enviado el mensaje a las personas indicadas por usted.<br /><strong>Mensaje enviado:</strong><br /><p />
				<% out.print(_mensaje); %>
			<% }else if(_accion.equals("seleccionar_correos")){ %>
			<form action="mensaje.do?accioninvitar=true" method="post" onsubmit="$('#id_encapsulador').show('slow');">
				<input type="hidden" value="enviar_correos" id="opcion" name="opcion">
				<input type="hidden" value="<% out.print(_titulo); %>" id="titulo" name="titulo">
				<input type="hidden" value="<% out.print(_mensaje); %>" id="mensaje" name="mensaje">
				<label>Correos electr&oacute;nicos a contactar (1 por l&iacute;nea):</label><br />
				<textarea rows="7" cols="50" id="correos" name="correos"></textarea><p />
				<input type ="submit" value="Enviar Mensaje">
			</form>
			<% }else{ %>
			<form action="mensaje.do?accioninvitar=true" method="post" onsubmit="var _temp = document.getElementById('titulo');if(_temp.value == ''){alert('Debe indicar un asunto');_temp.style.backgroundColor = 'yellow';_temp.focus();return false;}else{_temp.style.backgroundColor = '';return true;}">
				<input type="hidden" value="seleccionar_correos" id="opcion" name="opcion">
				<label>Asunto:</label><br />
				<input type="text" id="titulo" name="titulo" size="100"><p />
				<label>Mensaje a ser colocado en el correo a enviar:</label><br />
				<textarea rows="10" cols="120" id="mensaje" name="mensaje"></textarea>
				<script language="JavaScript" type="text/javascript">
					makeWhizzyWig("mensaje");
				</script><p />
				<label>Indicar correos electr&oacute;nicos:</label><br />
				<select id="opcion_correo" name="opcion_correo">
					<option value="instrumento">
						De participantes en instrumento
					</option>
					<option value="manual">
						Manualmente
					</option>
				</select><p />
				<input type ="submit" value="Enviar">
			</form>
			<% } %>
		</td>
	</tr>
</table>

<%@include file="adminfooter.jsp" %>