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

Pregunta preguntaseleccionada = null;
if(request.getAttribute("preguntaseleccionada") != null){
	preguntaseleccionada = (Pregunta)request.getAttribute("preguntaseleccionada");
}

Vector _insObj  = new Vector();
if(request.getAttribute("objetosInstanciados") != null){
	_insObj = (Vector)request.getAttribute("objetosInstanciados");
}
Collections.sort(_insObj, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.OBJETO));

%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_preguntas").css("color","red");
	});
</script>

<table cellspacing="2" cellpadding="2" class='tablasecundaria'>
	<tr>
		<td valign="top" align="left" colspan="3">
			<h2>Selecci&oacute;n de instrumento finalizado para importaci&oacute;n de datos</h2>
			<h4><span id="subtitulo" name="subtitulo">
			<%
			if(preguntaseleccionada != null){
				int total_instancias = 0;
				out.print("Pregunta: "+preguntaseleccionada.getPregunta());
			%>
			</span></h4>
				<div id="formInstancias" name="formInstancias">
					<form action="adminrespuestas3.do" method="get" name="miforma" id="miforma">
						<input type="hidden" value="<% out.print(preguntaseleccionada.getId()); %>" name="preguntaseleccionada" id="preguntaseleccionada">
						<input type="hidden" value="verdad" name="filtrarinstrumento" id="filtrarinstrumento">
						<label>Ind&iacute;que el instrumento deseado:</label><br />
						<select name="instrumentoseleccionado" id="instrumentoseleccionado" onchange="document.miforma.submit()" multiple="multiple" size="<% out.print(_insObj.size() + 1); %>">
							<%
							try{
								Enumeration _enu = _insObj.elements();
								InstanciaObjeto _act = null;
								while(_enu.hasMoreElements()){
									_act = (InstanciaObjeto)_enu.nextElement();
									out.println("<option value='"+_act.getId()+"'>"+_act.getObjeto()+"</option>");
								}
							}catch(Exception e){e.printStackTrace();}
							%>
						</select>
					</form>
				</div>
				<%
			}else{
				out.print("No ha seleccionado ninguna pregunta. <p />");
			}
			%>
		</td>
	</tr>
</table>
<%@include file="adminfooter.jsp" %>
