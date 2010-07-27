<%@include file="admininicio.jsp" %>
<%@include file="adminheader.jsp" %>

<%
if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}
Vector _preguntas = (Vector)request.getAttribute("preguntas");
Collections.sort(_preguntas, new OrdenadorPreguntas());
	
%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_preguntas").css("color","red");
	});
</script>

<table cellspacing="2" cellpadding="2" class="tablasecundaria">
	<tr>
		<td valign="top">
			<h2>Preguntas</h2>
			Puede crear una nueva pregunta haciendo clic <a href="adminpreguntas.do">aqu&iacute;</a>
			<p/>
			<%
			if((_preguntas != null) && (!_preguntas.isEmpty())){
				Enumeration preguntasDisponibles = _preguntas.elements();
			%>
				<span class="indicativo">Para modificar una pregunta existente ind&iacute;quela acontinuaci&oacute;n:<p /></span>
				<form name="miformulario" action="adminpreguntas.do" method="post">
					<input type="hidden" name="accion" value="seleccionar">
					<select name="preguntaseleccionada" onchange="document.miformulario.submit()">
						<option value="">Seleccione...</option>
						<%
						Pregunta miPregunta = null;
						while(preguntasDisponibles.hasMoreElements()){
							miPregunta	= (Pregunta)preguntasDisponibles.nextElement();
							if(miPregunta.getTipoPregunta() >= 30){
								continue;
							}
						%>
						<option value="<% out.print(miPregunta.getId()); %>"><% out.print(miPregunta.getPregunta()); %></option>
						<%
						}
						%>
					</select>
					<p/>
				</form>
				<p/>

			<%
			}else { %>
				<span class="indicativo">Si desea crear una nueva pregunta haga clic <a href="adminpreguntas.do">aqu&iacute;</a>.</span>
			<%
			}
			%>
		</td>
	</tr>
</table>

<%@include file="adminfooter.jsp" %>
