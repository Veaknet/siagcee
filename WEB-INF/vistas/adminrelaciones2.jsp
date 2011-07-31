<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Enumeration" %>
<%@include file="admininicio.jsp" %>

<%@include file="adminheader.jsp" %>

<%

InstanciaObjeto _instanciaObjeto = null;
if(request.getAttribute("Relacion") != null){
	_instanciaObjeto = (InstanciaObjeto)request.getAttribute("Relacion");
	Collections.sort(_instanciaObjeto.getObjetoAsociado().getPreguntas(), new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
}

InstanciaObjeto _relacionadaInstancia = null;
if(request.getAttribute("Instrumento") != null){
	_relacionadaInstancia = (InstanciaObjeto)request.getAttribute("Instrumento");
	Collections.sort(_relacionadaInstancia.getObjetoAsociado().getPreguntas(), new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.TIPO_PREGUNTA));
}
%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_colecciones_de_datos").css("color","red");
	});
</script>

<table cellpadding="4" cellspacing="4" class="tablasecundaria" >
	<tr>
		<td >
			<h2>Combinaci&oacute;n de instrumentos por equivalencia de datos</h2>
<%
if(request.getAttribute("mensaje") != null) {
	out.println((String)request.getAttribute("mensaje") + "<br />");
}

if(_instanciaObjeto != null && _relacionadaInstancia != null){
	if(!_instanciaObjeto.getObjetoAsociado().getPreguntas().isEmpty() && !_relacionadaInstancia.getObjetoAsociado().getPreguntas().isEmpty()){
		%>
		<label>Por favor indique la equivalencia de los datos seg&uacute;n considere:</label><p />
		<form action="adminrelaciones2.do" method="post">
			<input type="hidden" name="relacion" id="relacion" value="<% out.print(_instanciaObjeto.getId()); %>">
			<input type="hidden" name="instrumento" id="instrumento" value="<% out.print(_relacionadaInstancia.getId()); %>">
			<%
			if(request.getAttribute("preguntaJoinRelacion") != null){
				out.println("<input type='hidden' value='"+((InstanciaPregunta)request.getAttribute("preguntaJoinRelacion")).getId()+"' name='pregJoinRelacion' id='pregJoinRelacion'>");
			}
			if(request.getAttribute("preguntaJoinInstrumento") != null){
				out.println("<input type='hidden' value='"+((InstanciaPregunta)request.getAttribute("preguntaJoinInstrumento")).getId()+"' name='pregJoinInstrumento' id='pregJoinInstrumento'>");
			}
			%>
			<table cellpadding="4" cellspacing="4" style="max-width:95%;" class="reporte">
				<thead>
					<tr class="reporteTR">
						<th style="text-align:center;border: 1px solid #3333ff" >Datos del Instrumento 1:<br /><% out.print(_instanciaObjeto.getObjeto()); %></th>
						<th style="text-align:center;border: 1px solid #3333ff" >Datos del Instrumento 2:<br /><% out.print(_relacionadaInstancia.getObjeto()); %></th>
					</tr>
				</thead>
				<tbody>
					<%
					Enumeration _enu = _instanciaObjeto.getObjetoAsociado().getPreguntas().elements();
					InstanciaPregunta _miPreg = null;
					while(_enu.hasMoreElements()){
						_miPreg = (InstanciaPregunta)_enu.nextElement();
						out.println("<tr style='border: 1px groove #9999ff'>");
						out.println("<td style='text-align:right;'>");
						out.println(_miPreg.getAcronimo());
						out.println("</td>");
						out.println("<td style='text-align:left;'>");
						out.println("<select id='pregunta_"+_miPreg.getId()+"' name='pregunta_"+_miPreg.getId()+"'><option value='-1'>No asociar pregunta</option>");
						Enumeration _enuIn = _relacionadaInstancia.getObjetoAsociado().getPreguntas().elements();
						InstanciaPregunta _miPregIn = null;
						while(_enuIn.hasMoreElements()){
							_miPregIn = (InstanciaPregunta)_enuIn.nextElement();
							if((_miPreg.getTipoPregunta() < 30 && (_miPreg.getPreguntaAsociada().getId() != _miPregIn.getPreguntaAsociada().getId())) || (_miPreg.getTipoPregunta() >= 30 && (_miPreg.getTipoPregunta() != _miPregIn.getTipoPregunta()))){
								continue;
							}
							out.println("<option value='"+_miPregIn.getId()+"'>"+_miPregIn.getAcronimo()+"</option>");
						}
						out.println("</select>");
						out.println("</td>");
						out.println("</tr>");
					}
					%>
					<tr><td></td><td><input type="submit" value="Procesar" onclick="return confirm('Esta seguro que quiere procesar ya el formulario?. Esta accion no tiene regreso.');"></td></tr>
				</tbody>
			</table>
		</form>
		<%
	}else{
	  out.println("O el Instrumento o la Colecci&oacute;n no tienen datos asocidos.");
	}
}else{
	out.println("No se han definido ni el Instrumento ni la Colecci&oacute;n con la que se trabajar&aactue;.");
}
%>
</td></tr></table>
<%@include file="adminfooter.jsp" %>
