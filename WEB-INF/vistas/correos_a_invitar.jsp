<%@page session="true" import="com.siagcee.logic.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
InstanciaObjeto _miObj = null;
if(request.getAttribute("objeto") != null){
	_miObj = (InstanciaObjeto)request.getAttribute("objeto");
}

int _hightlight = -1;
if(request.getAttribute("highlight") != null){
	_hightlight = (Integer)request.getAttribute("highlight");
}

SimpleDateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
%>
<script type="text/javascript">
	$(document).ready(function(){
	<% if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){%>
		$("#link_instrumentos_generados").css("color","red");
	<% }else{ %>
		$("#link_estudios").css("color","red");
	<% } %>
	});
</script>

<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
			<h2>Invitar a participantes mediante correo electr&oacute;nico</h2>
		</td>
	</tr>
	<tr>
		<td>
			<form action="correos_a_invitar.do" method="post" onsubmit="$('#id_encapsulador').show('slow');">
				<input type="hidden" value="<% out.print(_hightlight);%>" id="_hightlight" name="_hightlight">
				<label>Mensaje a ser colocado en el correo a enviar:</label><br />
				<textarea rows="10" cols="120" id="mensaje" name="mensaje">
					Se le invita cordialmente a participar en <% if(_miObj.getObjetoAsociado().getClass().toString().contains("Censo")){out.print("el censo: ");}else{out.print("la encuesta: ");} out.print(_miObj.getObjeto());%>.
					<br />Iniciando el: <% out.print(_dateFormat.format(_miObj.getFechaInicio()));%> y finaliza el: <% out.print(_dateFormat.format(_miObj.getFechaCierre()));%>
					<br />
				</textarea><script language="JavaScript" type="text/javascript">
	        makeWhizzyWig("mensaje");
				</script><p />
				<label>Correos electr&oacute;nicos a contactar (1 por l&iacute;nea):</label><br />
				<textarea rows="7" cols="50" id="correos" name="correos"></textarea><p />
				<input type ="submit" value="Enviar">
				<p />Las invitaciones indicar&aacute;n el link: <a target='_blank' href="autenticarusuario.do?identificador_publico=<% out.print(_miObj.getIdPublico()); %>"><% out.print(_miObj.getObjeto());%></a> para poder ingresar.
			</form>
		</td>
	</tr>
</table>

<%@include file="adminfooter.jsp" %>