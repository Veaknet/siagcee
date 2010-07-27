<%@include file="admininicio.jsp" %>

<head>
<script type="text/javascript">
function cerrarDiv(){
	var formularioPregunta = document.getElementById("formularioPregunta");
	if(formularioPregunta != null){
		var accion = document.getElementById("accion");
		if(accion.value == "insertar"){
			formularioPregunta.style.display = "none";
			var subtitulo = document.getElementById("subtitulo");
			if(subtitulo){
				subtitulo.innerHTML = "";
			}
		}else{
			formularioPregunta.style.display = "none";
		}
	}
}

function compruebaNoVacio(elem){
	if(elem){
		if(elem.value != ""){
			return true;
		}
		alert('Debe indicar un texto para la pregunta.');
		elem.focus();
		elem.style.backgroundColor = "yellow";
		return false;
	}
	return false;
}

function habilitarInsertDiv(){
	var formulario = document.getElementById("formularioPregunta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var valor = document.getElementById("valor");
	var tipopregunta = document.getElementById("tipopregunta");
	var divpre = document.getElementById("divpre");
	var visible = document.getElementById("visible");
	if(divpre != null){
		divpre.style.display = "none";
	}
	accion.value = "insertar";
	boton.value = "Crear Pregunta";
	valor.value = "";
	visible.value = "1";
	tipopregunta.value = 1;
	formulario.style.display = "block";
	$('#boton').attr('disabled','disabled');
	valor.focus();
}
function habilitarUpdateDiv(id, texto, _tipopreg, _visible){
	var formulario = document.getElementById("formularioPregunta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var valor = document.getElementById("valor");
	var preguntaseleccionada = document.getElementById("preguntaseleccionada");
	var tipopregunta = document.getElementById("tipopregunta");
	var visible = document.getElementById("visible");
	preguntaseleccionada.value = id;
	accion.value = "actualizar";
	boton.value = "Modificar Pregunta";
	valor.value = texto;
	tipopregunta.value = _tipopreg;
	if(_visible){
		visible.value = "1";
	}else{
		visible.value = "0";
	}
	formulario.style.display = "block";
	$('#boton').attr('disabled','disabled');
	valor.focus();
}
</script>
</head>

<%@include file="adminheader.jsp" %>

<%

boolean showForm = false;
if (request.getParameter("showform") != null) {
	if(((String)request.getParameter("showform")).equals("si")){
		showForm = true;
	}
}

String _opcionBase = "crear";
if(request.getParameter("opcionbase")!=null){
	_opcionBase = request.getParameter("opcionbase");
}

if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}

String _mensaje = "";
if (request.getAttribute("mensaje") != null) {
	_mensaje = (String)request.getAttribute("mensaje");
}

Vector _preguntas = new Vector();
if(request.getAttribute("preguntas") != null){
	_preguntas = (Vector)request.getAttribute("preguntas");
	Collections.sort(_preguntas, new OrdenadorPreguntas());
}

Pregunta preguntaatrabajar = (Pregunta)request.getAttribute("preguntaatrabajar");

String _titulo = "";	
if(_opcionBase.equals("crear")){
	_titulo = "Crear Pregunta";
}else if(_opcionBase.equals("modificar")){
	_titulo = "Modificar Pregunta";
}else if(_opcionBase.equals("eliminar")){
	_titulo = "Eliminar Pregunta";
}else if(_opcionBase.equals("revisar")){
	_titulo = "Revisar Pregunta";
}

try{
	if((request.getParameter("accion")).equals("eliminar")){
		_titulo = "Eliminar Pregunta";
	}else if((request.getParameter("accion")).equals("seleccionar")){
		_titulo = "Modificar Pregunta";
	}else if((request.getParameter("accion")).equals("revisar")){
		_titulo = "Revisar Pregunta";
	}else if((request.getParameter("accion")).equals("actualizar")){
		_titulo = "Modificar Pregunta";
	}else if((request.getParameter("accion")).equals("insertar")){
		_titulo = "Modificar Pregunta";
	}
}catch (Exception e){

}

%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_preguntas").css("color","red");
	});
	function abrirInfo(){
		var _id = $("#preguntaseleccionada").attr("value");
		if(_id != ''){
			$('#id_encapsulador').show('slow');			
			var ventana = window.open('mostrarinfo.do?opcionbase=<% out.print(_opcionBase); %>&accion=mostrarinfodepregunta&perohaz=eliminar&id='+_id,'mostrarInfo','width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO');
			ventana.focus();
		}
		$("#preguntaseleccionada").attr("value","");
	}
</script>

<table cellspacing="2" cellpadding="2" class="tablasecundaria">
	<tr>
		<td valign="top" align="left">
			<h2><% out.print(_titulo);%></h2>
			<p/>
			<%
			if(_opcionBase.equals("modificar") || _opcionBase.equals("eliminar") || _opcionBase.equals("revisar")){
				Enumeration preguntasDisponibles = _preguntas.elements();
				if(_preguntas.isEmpty()){
					out.print("No existen preguntas creadas");
				}else{
				%>
					<label>Seleccione una pregunta:</label><br />
					<form name="miformulario" action="adminpreguntas.do" method="post">
						<input type="hidden" name="accion" value="<% if(_opcionBase.equals("modificar")){ out.print("seleccionar");}else if(_opcionBase.equals("eliminar")){ out.print("eliminar");}else if(_opcionBase.equals("revisar")){ out.print("revisar");}%>">
						<input type="hidden" name="opcionbase" value="<% if(_opcionBase.equals("revisar")){ out.print("revisar");}%>">
						<% if(_opcionBase.equals("eliminar")){ %>
							<select id="preguntaseleccionada" name="preguntaseleccionada" onchange="abrirInfo();return true;" multiple="multiple" size="<% out.print(_preguntas.size()); %>">
						<% }else{ %>
							<select name="preguntaseleccionada" id="preguntaseleccionada" onchange="document.miformulario.submit()" multiple="multiple" size="<% out.print(_preguntas.size()); %>">
						<% } %>
							<%
							Pregunta miPregunta = null;
							while(preguntasDisponibles.hasMoreElements()){
								miPregunta	= (Pregunta)preguntasDisponibles.nextElement();
								if(miPregunta.getTipoPregunta() == 100){continue;}
							%>
							<option value="<% out.print(miPregunta.getId()); %>"><% out.print(miPregunta.getPregunta()); %>&nbsp;&nbsp;</option>
							<%
							}
							%>
						</select>
					</form>
				<%
				}
			}
			if(_opcionBase.equals("crear")){
				showForm = true;
			}
			if(preguntaatrabajar != null){
				showForm = true;
			}
			try{
				if((request.getParameter("accion")).equals("revisar")){
					showForm = false;
				}
			}catch (Exception e){}				
			if(showForm){
			%>
			<div style='display:block;text-align:left' name="formularioPregunta" id="formularioPregunta">
			<%
			}else{
			%>
			<div style='display:none;text-align:left' name="formularioPregunta" id="formularioPregunta">
			<%
			}

			%>
				<form action="adminpreguntas.do" method="post">
					<input type="hidden" value="<% if(preguntaatrabajar != null){out.print(preguntaatrabajar.getId());}else{%>-1<%} %>" name="preguntaseleccionada" id="preguntaseleccionada">
					<input type="hidden" value="<% if(preguntaatrabajar != null){%>actualizar<%}else{%>insertar<%} %>" name="accion" id="accion">
					<label>Nombre de la pregunta:</label><br /><input type="text" value="<% if(preguntaatrabajar != null){out.print(preguntaatrabajar.getPregunta());} %>" name="valor" id="valor" size="50" onkeyup="this.style.backgroundColor = '';$('#boton').removeAttr('disabled');"><p />
					<label>Tipo de pregunta:</label><br />
					<select name="tipopregunta" id="tipopregunta" onchange="$('#boton').removeAttr('disabled');">
						<option value="1" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getTipoPregunta() == 1){%>selected="selected"<%}}else{%>selected="selected"<%} %>>Selecci&oacute;n simple</option>
						<option value="2" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getTipoPregunta() == 2){%>selected="selected"<%}}%>>Selecci&oacute;n m&uacute;ltiple</option>
						<option value="30" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getTipoPregunta() == 30){%>selected="selected"<%}}%> >Tipo abierto para texto</option>
						<option value="31" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getTipoPregunta() == 31){%>selected="selected"<%}}%>>Tipo abierto para n&uacute;meros sin decimales</option>
						<option value="32" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getTipoPregunta() == 32){%>selected="selected"<%}}%>>Tipo abierto para n&uacute;meros con decimales</option>
						<option value="33" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getTipoPregunta() == 33){%>selected="selected"<%}}%>>Tipo abierto para fecha</option>
						<option value="100" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getTipoPregunta() == 100){%>selected="selected"<%}}%>>De uso exclusivo para estudios</option>
					</select><p />
					<label style="display:none;">Trabajar como borrador?</label>
					<select style="display:none;" id="visible" name="visible" onchange="$('#boton').removeAttr('disabled');">
						<option value="1" <% if(preguntaatrabajar != null){if(preguntaatrabajar.getPublico()){%>selected="selected"<%}}%>>No</option>
						<option value="0" <% if(preguntaatrabajar != null){if(!preguntaatrabajar.getPublico()){%>selected="selected"<%}}%>>Si</option>
					</select>
					<input type="submit" disabled="disabled" value="<% if(preguntaatrabajar == null){%>Crear Pregunta<%}else{%>Guardar Cambios<%}%>" name="boton" id="boton" onclick="return compruebaNoVacio(getElementById('valor'))">
				</form>
			<%
				if(preguntaatrabajar != null){
					showForm = true;
					%>
					<div id="divpre" name="divpre">
						<%
							try{
								if(!(request.getParameter("accion")).equals("eliminar")){
									%>
									<% if(preguntaatrabajar.getTipoPregunta() < 30){ %>
									<a href="adminrespuestas2.do?preguntaseleccionada=<% out.print(preguntaatrabajar.getId()); %>" title="Para agregar,modificar y/o eliminar respuestas asociadas a esta pregunta"><img src="comunes/imagenes/next.png" height="20" title="Para agregar,modificar y/o eliminar respuestas asociadas a esta pregunta">&nbsp;Posibles respuestas de esta pregunta</a>
									<% } %>
									<p />
									<%
								}
							}catch (Exception eee){}
						%>
						<%
							try{
								if((request.getParameter("accion")).equals("eliminar")){
									%>
										Para eliminar esta pregunta haga clic <a id="linkelim" name="linkelim" href="adminpreguntas.do?accion=eliminar&preguntaseleccionada=<% out.print(preguntaatrabajar.getId());%>" onclick="return confirm('Realmente desea eliminar esta pregunta?');" title="Elimine esta pregunta y las respuestas asociadas">aqu&iacute;.</a><p />
									<%
								}
							}catch (Exception eee){}
						%>
					</div>
				<%
				}
			%>
				</div>
			    <p /><br />
					<%
					if(!_mensaje.equals("")){
						out.println(_mensaje);
					}
					%>
			</td>
		<td valign="top" width="50%">
<%
			try{
				if((request.getParameter("accion")).equals("revisar") || (request.getParameter("accion")).equals("eliminar")){
					%>
					<div style="text-align:left;">
						<h3><% out.print(preguntaatrabajar.getPregunta());%></h3>
						<%
						if(preguntaatrabajar.getTipoPregunta() == 1){
						%>
							Pregunta cerrada de selecci&oacute;n simple.<p />
						<%
						}else if(preguntaatrabajar.getTipoPregunta() == 2){
						%>
							Pregunta cerrada de selecci&oacute;n m&uacute;ltiple.<p />
						<%
						}else if(preguntaatrabajar.getTipoPregunta() == 30){
						%>
							Pregunta abierta que acepta cualquier texto.<p />
						<%
						}else if(preguntaatrabajar.getTipoPregunta() == 31){
						%>
							Pregunta abierta que acepta s&oacute;lo n&uacute;meros sin decimales.<p />
						<%
						}else if(preguntaatrabajar.getTipoPregunta() == 32){
						%>
							Pregunta abierta que acepta s&oacute;lo n&uacute;meros con decimales.<p />
						<%
						}else if(preguntaatrabajar.getTipoPregunta() == 33){
						%>
							Pregunta abierta que acepta s&oacute;lo fechas.<p />
						<%
						}else if(preguntaatrabajar.getTipoPregunta() == 100){
						%>
							Pregunta de uso exclusivo para resultados de estudios.<p />
						<%
						}
						if(preguntaatrabajar.getTipoPregunta() == 1 || preguntaatrabajar.getTipoPregunta() == 2){
							Vector _respu = preguntaatrabajar.retornaRespuestasPosibles();
							if(!_respu.isEmpty()){
								out.print("<h5>Respuestas posibles:</h5>");
								out.print("<ul>");
								Enumeration _enuResp = _respu.elements();
								RespuestasPosibles _resPos = null;
								while(_enuResp.hasMoreElements()){
									_resPos = (RespuestasPosibles)_enuResp.nextElement();
									out.print("<li>"+_resPos.getRespuesta()+"</li>");
								}
								out.print("</ul>");
							}
						}
					%>
					</div>
					<%
				}
			}catch(Exception e){}
%>			
		</td>
		</tr>
	</table>
<%
try{
	if((request.getParameter("accion")).equals("eliminar")){
	%>
	<script type="text/javascript" >
		$(document).ready(function(){
			if(confirm('Realmente desea eliminar esta pregunta?')){
				window.location = "adminpreguntas.do?accion=eliminar&preguntaseleccionada=<% out.print(preguntaatrabajar.getId());%>";
			}
		});
	</script>
	<%
	}
}catch (Exception e){}
try{
	if(_opcionBase.equals("crear")){
	%>
	<script type="text/javascript" >
		$(document).ready(function(){
			$("#valor").focus();
		});
	</script>
	<%
	}
}catch (Exception e){}
%>
<%@include file="adminfooter.jsp" %>
