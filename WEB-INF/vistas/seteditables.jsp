<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Vector" %>
<%@include file="admininicio.jsp" %>

<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 21/01/2010
 * Hora: 11:42:59 PM
-->

<%@include file="adminheader.jsp" %>

<%
InstanciaObjeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (InstanciaObjeto)request.getAttribute("objetoatrabajar");
}

Vector preguntasTotales = new Vector();
if(request.getAttribute("preguntasTotales") != null){
	preguntasTotales = (Vector)request.getAttribute("preguntasTotales");
}

Vector preguntasEditables = new Vector();
if(request.getAttribute("preguntasEditables") != null){
	preguntasEditables = (Vector)request.getAttribute("preguntasEditables");
}

String accion = "seteditables";
if(request.getAttribute("accion") != null){
	accion = (String)request.getAttribute("accion");
}

HashMap _editable = new HashMap();
Enumeration _enu = preguntasEditables.elements();
PreguntaEditable _prEd;
while(_enu.hasMoreElements()){
	_prEd = (PreguntaEditable)_enu.nextElement();
	_editable.put(_prEd.get_InsPregunta().getId(), true);
}

if(objetoatrabajar != null){
	if(!preguntasTotales.isEmpty()){
		_enu = preguntasTotales.elements();
		%>
		<table cellspacing="4" cellpadding="4" class='tablasecundaria'>
			<tr>
				<td valign="top">
					<h2>Selecci&oacute;n de preguntas objeto de actualizaci&oacute;n</h2>
					<h4>Instrumento sobre el que se trabaja:<br /><% out.print(objetoatrabajar.getObjeto());%></h4>
					<% if(accion.equals("setopcionales")){ %>
						<label>Indique, si lo desea, que datos se indicar&aacute;n como opcionales:</label>
					<% }else{ %>
						<label>Seleccione los datos que se permitir&aacute;n modificar:</label>
					<% } %>
					<form action="seteditables" method="post">
						<input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
						<input type="hidden" value="<% out.print(accion); %>" id="accion" name="accion">
						<select id="preguntaseditables" name="preguntaseditables" multiple="multiple" size="<% out.print(preguntasTotales.size() + 1); %>">
							<%
							InstanciaPregunta _pre = null;
							while(_enu.hasMoreElements()){
								_pre = (InstanciaPregunta)_enu.nextElement();
								if(_pre.getTipoPregunta() == 100){
									continue;
								}
								String _selected = "";
								if(_editable.get(_pre.getId()) != null){
									_selected = " selected='selected' ";
								}
								out.println("<option value='"+_pre.getId()+"' "+_selected+">"+_pre.getAcronimo()+"</option>");
							}
							%>
						</select><p />
						<input type="submit" value="Procesar" id="enviar" name="enviar">
					</form>
				</td>
		</tr>
		</table>
		<%
	}else{
		%>
		<table cellspacing="4" cellpadding="4" class='tablasecundaria'>
			<tr>
				<td valign="top">
					<% out.println("No existen preguntas a seleccionar."); %>
				</td>
			</tr>
		</table>
	<%
	}
}else{
	%>
	<table cellspacing="4" cellpadding="4" class='tablasecundaria'>
		<tr>
			<td valign="top">
				<% out.println("No se indic&oacute; un instrumento con el que se pueda trabajar."); %>
			</td>
		</tr>
	</table>
<%
	}
%>

<%@include file="adminfooter.jsp" %>
