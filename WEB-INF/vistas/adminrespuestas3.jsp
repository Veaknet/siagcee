<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Enumeration" %>
<%@include file="admininicio.jsp" %>

<script type="text/javascript" charset="UTF8">
function revisarEstructura(_select){
	window.open("mostrarinfo.do?accion=mostrarinfodepregunta&id="+_select, "mostrarInfo" , "width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");
}

function seleccionaTodo(){
	var _deseadas = document.getElementById("listadoRespuestasDeseadas");
	for(var i=_deseadas.length-1; i>=0; i--){
		_deseadas.options[i].selected = true;
	}
}

function agregarRespuesta(){
	var _externas = document.getElementById("listadoRespuestasExternas");
	var _deseadas = document.getElementById("listadoRespuestasDeseadas");

  var selIndex = _externas.selectedIndex;
  if (selIndex != -1){
    for(var i=_externas.length-1; i>=0; i--){
      if(_externas.options[i].selected){
	      var newOpt = new Option(_externas.options[i].text, _externas.options[i].value);
        _deseadas.options[_deseadas.length] = newOpt;
        _externas.options[i] = null;
      }
    }
  }
}

function eliminarRespuesta(){
	var _externas = document.getElementById("listadoRespuestasExternas");
	var _deseadas = document.getElementById("listadoRespuestasDeseadas");

  var selIndex = _deseadas.selectedIndex;
  if (selIndex != -1){
    for(var i=_deseadas.length-1; i>=0; i--){
      if(_deseadas.options[i].selected){
	      var newOpt = new Option(_deseadas.options[i].text, _deseadas.options[i].value);
        _externas.options[_externas.length] = newOpt;
        _deseadas.options[i] = null;
      }
    }
  }
}

</script>

<%@include file="adminheader.jsp" %>

<%
if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}
Pregunta preguntaatrabajar = null;
if(request.getAttribute("preguntaseleccionada") != null){
	preguntaatrabajar = (Pregunta)request.getAttribute("preguntaseleccionada");
}

Vector _preguntasExternas = new Vector();
if(request.getAttribute("preguntasexternas") != null){
	_preguntasExternas = (Vector)request.getAttribute("preguntasexternas");
}
Collections.sort(_preguntasExternas, new OrdenadorPreguntas(OrdenadorPreguntas.ID_PREGUNTA));

Pregunta preguntaExterna = null;
if(request.getAttribute("preguntaExterna") != null){
	preguntaExterna = (Pregunta)request.getAttribute("preguntaExterna");
}

Vector _listadoRespuestasExternas = new Vector();
if(request.getAttribute("listadoRespuestasExternas") != null){
	_listadoRespuestasExternas = (Vector)request.getAttribute("listadoRespuestasExternas");
}

%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_preguntas").css("color","red");
	});
</script>

<table cellspacing="2" cellpadding="2" class='tablasecundaria'>
	<tr>
		<td valign="top" align="left" colspan="3">
			<h2>Cargar Posibles Respuestas</h2>
			<h4><span id="subtitulo" name="subtitulo">
			<%
			if(preguntaatrabajar!= null){
				int total_preugntas = 0;
				out.print("pregunta: "+preguntaatrabajar.getPregunta());
				Enumeration preguntasExternas = _preguntasExternas.elements();
				Pregunta pre;
				try{
					while(preguntasExternas.hasMoreElements()){
						pre = (Pregunta)preguntasExternas.nextElement();
						if(pre.getId() == preguntaatrabajar.getId()){
							continue;
						}
						if(pre.getTipoPregunta() > 2 || pre.getTipoPregunta() < 1){
							continue;
						}
						total_preugntas++;
					}
				}catch(Exception e){e.printStackTrace();}
			%>
			</span></h4>
			<div id="formularioPregunta" name="formularioPregunta">
				<form action="adminrespuestas3.do" method="post" name="miforma" id="miforma">
					<input type="hidden" value="<% out.print(preguntaatrabajar.getId()); %>" name="preguntaseleccionada" id="preguntaseleccionada">
					<input type="hidden" value="cambiarpreguntaexterna" name="accionpregunta" id="accionpregunta">
					<label>Ind&iacute;que de qu&eacute; pregunta desea importar las respuestas:</label><br />
					<% if(preguntaExterna == null){ %>
						<select name="preguntaseleccionadaexterna" id="preguntaseleccionadaexterna" onchange="document.miforma.submit()" multiple="multiple" size="<% out.print(total_preugntas+1); %>">
					<% }else{ %>
						<select name="preguntaseleccionadaexterna" id="preguntaseleccionadaexterna" onchange="document.miforma.submit()">
							<option value="-1">Seleccione...</option>
					<% } %>
						<%
						try{
							preguntasExternas = _preguntasExternas.elements();
							while(preguntasExternas.hasMoreElements()){
								pre = (Pregunta)preguntasExternas.nextElement();
								if(pre.getId() == preguntaatrabajar.getId()){
									continue;
								}
								if(pre.getTipoPregunta() > 2 || pre.getTipoPregunta() < 1){
									continue;
								}
								out.println("<option value='"+pre.getId()+"'>"+pre.getPregunta()+"</option>");
							}
						}catch(Exception e){e.printStackTrace();}
						%>
					</select>
				</form>
			</div>

			<%
			}else{
				out.print("No ha seleccionado ninguna pregunta: <p />");
			}
			if(preguntaExterna != null){ %>
				<a href="#" onclick='revisarEstructura(<% out.print(preguntaExterna.getId()); %>);'>Revisar Informaci&oacute;n sobre la pregunta <% out.print(preguntaExterna.getPregunta()); %></a><br />
			<% } %>			
		</td>
	</tr>
	<tr>
		<td width="45%" style="max-width:45%" valign="top" align="left">
		  <% if(preguntaExterna != null){ %>
			<h4>Valores Disponibles de <% out.print(preguntaExterna.getPregunta()); %>:</h4>
			<select name="listadoRespuestasExternas" id="listadoRespuestasExternas" size="8" style="max-width:400px;width:400px;" multiple>
				<%
				try{
					Enumeration respuestasExternas = _listadoRespuestasExternas.elements();
					RespuestasPosibles resp;
					while(respuestasExternas.hasMoreElements()){
						resp = (RespuestasPosibles)respuestasExternas.nextElement();
						out.println("<option value='"+resp.getId()+"'>"+resp.getRespuesta()+"</option>");
					}
				}catch(Exception e){e.printStackTrace();}
				%>
			</select>
			<% } %>
		</td>
		<td width="10%" style="max-width:10%" valign="middle" align="center">
			<% if(preguntaExterna != null){ %>
			<img src="comunes/imagenes/next.png" alt="agregar" title="agregar" onclick="agregarRespuesta()" width="24"> <p /><br />
			<img src="comunes/imagenes/back.png" alt="eliminar" title="eliminar" onclick="eliminarRespuesta()" width="24"> <p />
			<% } %>
		</td>
		<td width="45%" style="max-width:45%" valign="top" align="left">
			<% if(preguntaExterna != null){ %>
			<h4>Valores que se agregar&aacute;n a <% out.print(preguntaatrabajar.getPregunta()); %>:</h4>
			<form action="adminrespuestas3.do" method="post" name="formListadoRespuestasDeseadas" id="formListadoRespuestasDeseadas">
				<input type="hidden" value="<% out.print(preguntaatrabajar.getId()); %>" name="preguntaseleccionada" id="preguntaseleccionada">
				<input type="hidden" value="agregarRespuestas" name="accion" id="accion">
				<select name="listadoRespuestasDeseadas" id="listadoRespuestasDeseadas" size="8" style="max-width:400px;width:400px;" multiple>
				</select><p />
				<input type="submit" value="Procesar" name="miboton2" id="miboton2" onclick="seleccionaTodo();">
			</form>
			<% } %>
		</td>
	</tr>
</table>
<%@include file="adminfooter.jsp" %>
