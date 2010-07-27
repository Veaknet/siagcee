<%@page session="true" import="com.siagcee.logic.*" %>
<%@page import="java.util.*" %>
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

<%@include file="adminheader.jsp" %>
<%

Vector _estructuras = new Vector();
if(request.getAttribute("estructuras") != null){
	_estructuras = (Vector)request.getAttribute("estructuras");
}
%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_estudios").css("color","red");
	});
</script>

<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
			<h2>Selecci&oacute;n de Instrumento para Estudios</h2>
		<%
			if(_estructuras.isEmpty()){
				out.println("No existen estructuras disponibles para crear un estudio.");
			}else{
				%>
			  <form action="estudios.do" method="post">
				  <label>Indique el instrumento con el que trabajar&aacute;:</label><br />
				  <input type="hidden" value="<% out.print(request.getAttribute("opcionbase")); %>" id="opcionbase" name="opcionbase">
				  <select id="estructura" name="estructura" multiple="multiple" size="<% out.print(_estructuras.size() + 3); %>" onchange="if(this.value != -1){this.parentNode.submit();return true;}else{alert('Debe seleccionar un instrumento.');return false;}">
						<%
							try{
								String miClase = "";
								String miClasePrint = "";
								Enumeration _misObjetos = _estructuras.elements();
								Objeto miObj;
								while(_misObjetos.hasMoreElements()){
									miObj = (Objeto)_misObjetos.nextElement();
									if(miObj.getClass().toString().contains("EstructuraBase")){continue;}
									//solo se hace lo siguiente para mostrarlos por grupos: censos, encuestas y relaciones
									if(!miClase.equals(miObj.getClass().toString())){
										if(!miClase.equals("")){
											out.print("</optgroup>");
										}

										miClase = miObj.getClass().toString();

										if(miClase.contains("Censo")){
											miClasePrint = "Censos";
										}else if(miClase.contains("Encuesta")){
											miClasePrint = "Encuestas";
										}else if(miClase.contains("Relacion")){
											miClasePrint = "Colecciones de Datos";
										}else{continue;}
										%>
										<optgroup label="<% out.print(miClasePrint); %>">
										<%
									}
									out.println("<option value='"+miObj.getId()+"'>"+miObj.getObjeto()+"&nbsp;&nbsp;</option>");
								}
							}catch(Exception e){e.printStackTrace();}
							out.print("</optgroup>");
						%>
				  </select>
				</form>
				<%
			}
		%>
		</td>
	</tr>
</table>
<%@include file="adminfooter.jsp" %>