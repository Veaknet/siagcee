<%@page import="com.siagcee.logic.Objeto" %>
<%@ page import="com.siagcee.logic.OrdenadorObjetos" %>
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
			<h2>Coleccionar Datos</h2>
<%
if(request.getAttribute("mensaje") != null) {
	out.println((String)request.getAttribute("mensaje") + "<br />");
}

if(_instanciaObjeto != null && _relacionadaInstancia != null){
	if(!_instanciaObjeto.getObjetoAsociado().getPreguntas().isEmpty() && !_relacionadaInstancia.getObjetoAsociado().getPreguntas().isEmpty()){
		%>
		<label>Por favor asocie las preguntas seg&uacute;n su consideraci&oacute;n, para luego pasar la data del Instrumento a la Colecci&oacute;n en la pregunta correspondiente:</label><p />
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
			<table cellpadding="4" cellspacing="4" border="0" width="95%">
				<tr>
					<th style="text-align:right;padding-right:5px" width="50%" nowrap>Preguntas de la Colecci&oacute;n de Datos</th>
					<th style="text-align:left;padding-left:5px" width="50%" nowrap>Preguntas del Instrumento</th>
				</tr>
					<%
					Enumeration _enu = _instanciaObjeto.getObjetoAsociado().getPreguntas().elements();
					InstanciaPregunta _miPreg = null;
					while(_enu.hasMoreElements()){
						_miPreg = (InstanciaPregunta)_enu.nextElement();
						out.println("<tr>");
						out.println("<td style='text-align:right'>");
						out.println(_miPreg.getTextoPregunta());
						out.println("</td>");
						out.println("<td style='text-align:left'>");
						out.println("<select id='pregunta_"+_miPreg.getId()+"' name='pregunta_"+_miPreg.getId()+"'><option value='-1'>No asociar pregunta</option>");
						Enumeration _enuIn = _relacionadaInstancia.getObjetoAsociado().getPreguntas().elements();
						InstanciaPregunta _miPregIn = null;
						while(_enuIn.hasMoreElements()){
							_miPregIn = (InstanciaPregunta)_enuIn.nextElement();
							if((_miPreg.getTipoPregunta() < 30 && (_miPreg.getPreguntaAsociada().getId() != _miPregIn.getPreguntaAsociada().getId())) || (_miPreg.getTipoPregunta() >= 30 && (_miPreg.getTipoPregunta() != _miPregIn.getTipoPregunta()))){
								continue;
							}
							out.println("<option value='"+_miPregIn.getId()+"'>"+_miPregIn.getTextoPregunta()+"</option>");
						}
						out.println("</select>");
						out.println("</td>");
						out.println("</tr>");
					}
					%>
				<tr><td></td><td><input type="submit" value="Agregar" onclick="return confirm('Esta seguro que quiere procesar ya el formulario?. Esta accion no tiene regreso.');"></td></tr>
			</table>
		</form>
		<%
	}else{
	  out.println("O el Instrumento o la Colecci&oacute;n no tienen preguntas asocidas para poder trabajar.");
	}
}else{
	out.println("No se han definido ni el Instrumento ni la Colecci&oacute;n con la que se trabajar&aactue;.");
}
%>
</td></tr></table>
<%@include file="adminfooter.jsp" %>
	