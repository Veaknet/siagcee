<%@page session="true" import="com.siagcee.logic.Objeto" %>
<%@page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
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
	Collections.sort(_estructuras, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.CLASS));
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
			<h2>Instrumentos publicados factibles para elaborar estudio</h2>
		<%
			if(_estructuras.isEmpty()){
				out.println("No existen estructuras disponibles para crear un estudio.");
			}else{
				boolean soloInsConEst = false;
				int newSize = 0;
				if(request.getParameter("opcionbase") != null && request.getParameter("opcionbase").equals("crear")){
					%>
					<form action="crearestudioperso" method="get">
					<%
				}else{
					soloInsConEst = true;
					Enumeration _misObjetos = _estructuras.elements();
					InstanciaObjeto miObj;
					while(_misObjetos.hasMoreElements()){
						miObj = (InstanciaObjeto)_misObjetos.nextElement();
						if(miObj.getObjetoAsociado().getClass().toString().contains("EstructuraBase")){continue;}
						if(soloInsConEst && miObj.getObjetoAsociado().estudiosAsociados(true).isEmpty()){continue;}
						newSize++;
					}
					%>
					<form action="estudios.do" method="post">
					<%
				}
				%>
					<label>Indique el instrumento con el que trabajar&aacute;:</label><br />
					<input type="hidden" value="<% out.print(request.getAttribute("opcionbase")); %>" id="opcionbase" name="opcionbase">
					<select id="objetoatrabajar" name="objetoatrabajar" multiple="multiple" size="<% if(soloInsConEst){out.print(newSize + 3); }else{ out.print(_estructuras.size() + 3);} %>" onchange="if(this.value != -1){this.parentNode.submit();return true;}else{alert('Debe seleccionar un instrumento.');return false;}">
						<%
							try{
								String miClase = "";
								String miClasePrint = "";
								Enumeration _misObjetos = _estructuras.elements();
								InstanciaObjeto miObj;
								while(_misObjetos.hasMoreElements()){
									miObj = (InstanciaObjeto)_misObjetos.nextElement();
									if(miObj.getObjetoAsociado().getClass().toString().contains("EstructuraBase")){continue;}
									if(soloInsConEst && miObj.getObjetoAsociado().estudiosAsociados(true).isEmpty()){continue;}
									//solo se hace lo siguiente para mostrarlos por grupos: censos, encuestas y relaciones
									if(!miClase.equals(miObj.getObjetoAsociado().getClass().toString())){
										if(!miClase.equals("")){
											out.print("</optgroup>");
										}

										miClase = miObj.getObjetoAsociado().getClass().toString();

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