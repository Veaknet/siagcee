<%@include file="admininicio.jsp" %>

<head>
<script type="text/javascript" charset="UTF-8">
var contador_preguntas = 0;
var confirmacion_aceptada = false;
function revisarEstructura(){
  var _select = document.getElementById("tipopregunta");
	window.open("mostrarinfo.do?accion=mostrarinfodepregunta&id="+_select.value , "mostrarInfo" , "width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");
}

function revisarEstudio(){
  var _select = document.getElementById("tipoestudio");
	window.open("mostrarinfo.do?accion=mostrarinfodeestudio&id="+_select.value , "mostrarInfo" , "width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");
}

function compruebaSeleccionadoEstudio(_estudio){
	if(_estudio){
		if((_estudio.parentNode.style.display == 'none') || ((_estudio.parentNode.style.display != 'none') && (_estudio.value != '-1'))){
			return true;
		}
		alert('Debe indicar el estudio');
		_estudio.focus();
		return false;
	}
	return true;
}

function cerrarDiv(){
	var formularioPregunta = document.getElementById("formularioPregunta");
	if(formularioPregunta != null){
		formularioPregunta.style.display = "none";
	}
}

function compruebaVacioSelect(elem){
	var plantillaPregunta = document.getElementById("linkPlantillaPregunta");
	if(elem){
		if(elem.value != -1){
			plantillaPregunta.style.display = 'block';
		}else{
			plantillaPregunta.style.display = 'none';
		}
	}else{
		plantillaPregunta.style.display = 'none';
	}
}

function compruebaNoVacio(elem){
	if(elem){
		if(elem.value != ""){
			return true;
		}
		alert('Debe indicar un texto para la pregunta.');
		elem.focus();
		elem.style.backgroundColor = 'yellow';
		elem.onkeyup = function(){
			elem.style.backgroundColor = '';
		};
		return false;
	}
	return false;
}

function compruebaSeleccionado(elem){
	if(elem){
		if(elem.value != -1){
			return true;
		}
		alert('Debe seleccionar una pregunta.');
		elem.focus();
		return false;
	}
	elem.focus();
	return false;
}

function habilitarInsertDiv(){
	var formulario = document.getElementById("formularioPregunta");
	var plantillaPregunta = document.getElementById("linkPlantillaPregunta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var boton2 = document.getElementById("boton2");
	var valor = document.getElementById("valor");
	var acronimo = document.getElementById("acronimo");
	var orden = document.getElementById("orden");
	var tipopregunta = document.getElementById("tipopregunta");
	var subtitulo2 = document.getElementById("subtitulo2");
	subtitulo2.innerHTML = "Incorporar pregunta";
	accion.value = "insertar";
	boton.value = "Agregar Solo Esta Pregunta";
	boton.style.display = 'none';
	boton2.style.display = 'inline';
	valor.value = "";
	acronimo.value = "";
	plantillaPregunta.style.display = 'none';
	orden.value = contador_preguntas + 1;
	tipopregunta.value = 1;
	$("#formularioPregunta").modal();
	//formulario.style.display = "block";
	valor.focus();

}

function habilitarInsertDivParaRelacion(){
	var formulario = document.getElementById("formularioPregunta");
	var plantillaPregunta = document.getElementById("linkPlantillaPregunta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var valor = document.getElementById("valor");
	var acronimo = document.getElementById("acronimo");
	var orden = document.getElementById("orden");
	var tipopregunta = document.getElementById("tipopregunta");
	var subtitulo2 = document.getElementById("subtitulo2");
	subtitulo2.innerHTML = "Incorporar pregunta";
	accion.value = "insertar";
	boton.value = "Agregar Pregunta";
	boton.style.display = 'inline';
	valor.value = "";
	acronimo.value = "";
	plantillaPregunta.style.display = 'none';
	orden.value = contador_preguntas + 1;
	tipopregunta.value = 1;
	tipopregunta.disabled = false;
	$("#formularioPregunta").modal();
//	formulario.style.display = "block";
	valor.focus();

}

function habilitarUpdateDiv(id, acronimo, texto, _orden, _preguntaasociada, _estudioasociado, isCampo_clave_unico, isCampo_identificador, isCampo_comunicacion_email, isCampo_comunicacion_telefono, isCampo_comunicacion_telefono2){
	var formulario = document.getElementById("formularioPregunta");
	var plantillaPregunta = document.getElementById("linkPlantillaPregunta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var boton2 = document.getElementById("boton2");
	var _acronimo = document.getElementById("acronimo");
	var valor = document.getElementById("valor");
	var orden = document.getElementById("orden");
	var preguntaseleccionada = document.getElementById("preguntaseleccionada");
	var tipoestudio = document.getElementById("tipoestudio");
	var tipopregunta = document.getElementById("tipopregunta");
	var subtitulo2 = document.getElementById("subtitulo2");
	subtitulo2.innerHTML = "Modificar pregunta";
	preguntaseleccionada.value = id;
	accion.value = "actualizar";
	plantillaPregunta.style.display = 'block';
	boton.value = "Actualizar";
	boton.style.display = 'inline';
	boton2.style.display = 'none';
	orden.value = _orden;
	_acronimo.value = acronimo;
	valor.value = texto;
	tipopregunta.value = _preguntaasociada;
	tipoestudio.value = _estudioasociado;
	$("#formularioPregunta").modal();
//	formulario.style.display = "block";
	if(isCampo_clave_unico){
		$("#campo_clave").attr("checked", "checked");
	}else{
		$("#campo_clave").removeAttr("checked");
	}
	if(isCampo_identificador){
		$("#campo_identificador").attr("checked", "checked");
	}else{
		$("#campo_identificador").removeAttr("checked");
	}
	if(isCampo_comunicacion_email){
		$("#campo_email").attr("checked", "checked");
	}else{
		$("#campo_email").removeAttr("checked");
	}
	if(isCampo_comunicacion_telefono){
		$("#campo_telefono").attr("checked", "checked");
	}else{
		$("#campo_telefono").removeAttr("checked");
	}
	if(isCampo_comunicacion_telefono2){
		$("#campo_telefono2").attr("checked", "checked");
	}else{
		$("#campo_telefono2").removeAttr("checked");
	}
	valor.focus();
}

function habilitarUpdateDivParaRelacion(id, acronimo, texto, _orden, _preguntaasociada, _estudioasociado, isCampo_clave_unico, isCampo_identificador, isCampo_comunicacion_email, isCampo_comunicacion_telefono, isCampo_comunicacion_telefono2){
	var formulario = document.getElementById("formularioPregunta");
	var plantillaPregunta = document.getElementById("linkPlantillaPregunta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton");
	var _acronimo = document.getElementById("acronimo");
	var valor = document.getElementById("valor");
	var orden = document.getElementById("orden");
	var preguntaseleccionada = document.getElementById("preguntaseleccionada");
	var tipoestudio = document.getElementById("tipoestudio");
	var tipopregunta = document.getElementById("tipopregunta");
	var subtitulo2 = document.getElementById("subtitulo2");
	subtitulo2.innerHTML = "Modificar pregunta";
	preguntaseleccionada.value = id;
	accion.value = "actualizar";
	boton.value = "Actualizar";
	boton.style.display = 'inline';
	plantillaPregunta.style.display = 'block';
	orden.value = _orden;
	_acronimo.value = acronimo;
	valor.value = texto;
	tipopregunta.value = _preguntaasociada;
	tipoestudio.value = _estudioasociado;
	tipopregunta.disabled = true;
	$("#formularioPregunta").modal();
//	formulario.style.display = "block";
	if(isCampo_clave_unico){
		$("#campo_clave").attr("checked", "checked");
	}else{
		$("#campo_clave").removeAttr("checked");
	}
	if(isCampo_identificador){
		$("#campo_identificador").attr("checked", "checked");
	}else{
		$("#campo_identificador").removeAttr("checked");
	}
	if(isCampo_comunicacion_email){
		$("#campo_email").attr("checked", "checked");
	}else{
		$("#campo_email").removeAttr("checked");
	}
	if(isCampo_comunicacion_telefono){
		$("#campo_telefono").attr("checked", "checked");
	}else{
		$("#campo_telefono").removeAttr("checked");
	}
	if(isCampo_comunicacion_telefono2){
		$("#campo_telefono2").attr("checked", "checked");
	}else{
		$("#campo_telefono2").removeAttr("checked");
	}
	valor.focus();
}

function habilitadeshabilitaestudios(){
	var midiv = document.getElementById("selectestudio");
	var miselect = document.getElementById("tipoestudio");
	var mipregunta = document.getElementById("tipopregunta");
	midiv.style.display = 'none';
//	miselect.value = '-1';
	for(var i=0;i<array_preguntas_estudios.length;i++){
		if(array_preguntas_estudios[i] == mipregunta.value){
			midiv.style.display = 'block';
			break;
		}
	}

}

var array_preguntas_estudios = new Array();
	
</script>
</head>

<%@include file="adminheader.jsp" %>

<%

boolean showForm = false;

String _opcionBase = "armar";
if(request.getParameter("opcionbase")!=null){
	_opcionBase = request.getParameter("opcionbase");
}

String _tipoinstrumento = "";
if(request.getParameter("tipoinstrumento")!=null){
	_tipoinstrumento = request.getParameter("tipoinstrumento");
}
	
String _mensaje = "";
if (request.getAttribute("mensaje") != null) {
	_mensaje = (String)request.getAttribute("mensaje");
}

if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}

Objeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (Objeto)request.getAttribute("objetoatrabajar");
}

Vector _listadoPreguntas = new Vector();
if(request.getAttribute("listadoPreguntas") != null){
	_listadoPreguntas = (Vector)request.getAttribute("listadoPreguntas");
	Collections.sort(_listadoPreguntas, new OrdenadorInstanciaPreguntas());
	out.println("<script type='text/javascript'>contador_preguntas = "+_listadoPreguntas.size()+";</script>");
}

Vector _listadoEstudios = new Vector();
if(request.getAttribute("listadoEstudios") != null){
	_listadoEstudios = (Vector)request.getAttribute("listadoEstudios");
	Collections.sort(_listadoEstudios, new OrdenadorEstudios(OrdenadorEstudios.TITULO));
}

Vector _listadoPreguntasComunes = new Vector();
if(request.getAttribute("listadoPreguntasComunes") != null){
	_listadoPreguntasComunes = (Vector)request.getAttribute("listadoPreguntasComunes");
	Collections.sort(_listadoPreguntasComunes, new OrdenadorPreguntas());
}

String _titulo = "";
if(_opcionBase.equals("armar")){
	_titulo = "Crear ";
}else if(_opcionBase.equals("modificar")){
	_titulo = "Modificar ";
}else if(_opcionBase.equals("eliminar")){
	_titulo = "Eliminar ";
}else if(_opcionBase.equals("revisar")){
	_titulo = "Revisar ";
}else if(_opcionBase.equals("crear")){
	_titulo = "Generar ";
}

String _elemento = "";
String _prefix = "";
if(_tipoinstrumento.equals("estructura")){
	_titulo = _titulo+"Estructura Base";
    _prefix = "la";
	_elemento = "estructura";
}else	if(_tipoinstrumento.equals("relacion")){
	_titulo = _titulo+"Colecci&oacute;n de Datos";
    _prefix = "la";
	_elemento = "colecci&oacute;n de datos";
}else{
	_titulo = _titulo+"Instrumento";
    _prefix = "el";
	_elemento = "instrumento";
}

try{
	Enumeration preguntasComunes = _listadoPreguntasComunes.elements();
	Pregunta preguntaComun;
	%>
  <script type="text/javascript" charset="utf-8">
	<%
	while(preguntasComunes.hasMoreElements()){
		preguntaComun = (Pregunta)preguntasComunes.nextElement();
		if(preguntaComun.getTipoPregunta() != 100){
			continue;	
		}
		%>
			array_preguntas_estudios[array_preguntas_estudios.length] = '<% out.print(preguntaComun.getId()); %>';
		<%
	}
	%>
	</script>
	<%
}catch(Exception e){}

sesion.setAttribute("retornoDireccion", "adminobjetos2.do?accion=seleccionar&objetoseleccionado="+objetoatrabajar.getId());

%>
<script type="text/javascript">
	$(document).ready(function(){
		<% if(_tipoinstrumento.equals("instrumento")){ %>
		$("#link_instrumentos").css("color","red");
		<% } %>
		<% if(_tipoinstrumento.equals("relacion")){ %>
		$("#link_colecciones_de_datos").css("color","red");
		<% } %>
		<% if(_tipoinstrumento.equals("estructura")){ %>
		$("#link_estructuras").css("color","red");
		<% } %>
	});
</script>

<%
if(!_mensaje.equals("")){
	out.println(_mensaje);
}
%>
<table cellspacing="2" cellpadding="2" class="tablasecundaria">
	<tr>
		<td style="max-width:35%;min-width:35%" valign="top" align="left">
		<h2><% out.print(_titulo);%></h2>
			<h3><span id="subtitulo" name="subtitulo">
			<%
			if(objetoatrabajar != null){
				out.print(objetoatrabajar.getObjeto());
				%>
				<!--// <a href="generadorestudios?accion=listarestudios&objetoatrabajar=<% /* out.print(objetoatrabajar.getId()); */%>"><img src="comunes/imagenes/barchart.png" alt="Administrar Estudios de <% /* out.print(_elemento); */ %>" title="Administrar Estudios de <% /* out.print(_elemento); */ %>" height="24"></a>//--></span></h3>
				<%
				try{
					if(_opcionBase.equals("revisar")){
						if(objetoatrabajar.getClass().toString().contains("Censo")){
							out.println("Instrumento para censos.<p />");
						}
						if(objetoatrabajar.getClass().toString().contains("Encuesta")){
							out.println("Instrumento para encuestas.<p />");
						}
						if(objetoatrabajar.getClass().toString().contains("Relacion")){
							out.println("Colecciones de datos.<p />");
						}
						if(objetoatrabajar.getClass().toString().contains("EstructuraBase")){
							out.println("Estructura base.<p />");
						}
						//muestro toda la info publica del objeto
						Vector _misPregs = objetoatrabajar.getPreguntas();
						Collections.sort(_misPregs, new OrdenadorInstanciaPreguntas());
						Enumeration _misPregsEnu = _misPregs.elements();
						while(_misPregsEnu.hasMoreElements()){
							InstanciaPregunta _temp = (InstanciaPregunta)_misPregsEnu.nextElement();
							out.println("<h4>"+String.valueOf(_temp.getOrden())+". "+_temp.getTextoPregunta());
							if(_temp.isCampo_clave_unico()){
								out.println("<img src='comunes/imagenes/primary_key_icon.jpg' height='24' alt='Pregunta clave' title='Pregunta Clave'>");
							}
							if(_temp.isCampo_comunicacion_email()){
								out.println("<img src='comunes/imagenes/email.png' height='24' alt='Elemento de comunicaci&oacute;n por email' title='Elemento de comunicaci&oacute;n por email'>");
							}
							out.println("<br />");
							out.println("(Tipo de Dato: "+_temp.getPreguntaAsociada().getPregunta()+")</h4>");
							if(_temp.getTipoPregunta() == 1){
								out.print("Cerrado de selecci&oacute;n simple, con los siguientes valores:<p />");
							}
							if(_temp.getTipoPregunta() == 2){
								out.print("Cerrado de selecci&oacute;n m&uacute;ltiple, con los siguientes valores:<p />");
							}
							if(_temp.getTipoPregunta() == 30){
								out.print("Abierto que acepta cualquier texto sin validaciones adicionales.<p />");
							}
							if(_temp.getTipoPregunta() == 31){
								out.print("Abierto que acepta &uacute;nicamente n&uacute;meros sin decimales.<p />");
							}
							if(_temp.getTipoPregunta() == 32){
								out.print("Abierto que acepta &uacute;nicamente n&uacute;meros con o sin decimales.<p />");
							}
							if(_temp.getTipoPregunta() == 33){
								out.print("Abierto que acepta &uacute;nicamente fechas.<p />");
							}
							if(_temp.getTipoPregunta() == 100){
								out.print("Abierto que no ser&aacute; mostrado a los encuestados y su utilidad es proveer un campo en la estructura para guardar valores de un estudio en particular.<p />");
							}
							if(_temp.getTipoPregunta() < 30){
								Vector _misResp = _temp.respuestasPosibles();
								Enumeration _misRespEnu = _misResp.elements();
								while(_misRespEnu.hasMoreElements()){
									RespuestasPosibles _tempResp = (RespuestasPosibles)_misRespEnu.nextElement();
									out.println(" - "+_tempResp.getRespuesta()+"<br />");
								}
							}
						}
					}else{
						%>
						<div id="formularioObjeto" name="formularioObjeto" style="display:block">
							<form action="adminobjetos2.do" method="post">
								<input type="hidden" name="tipoinstrumento" value="<% out.print(_tipoinstrumento);%>">
								<input type="hidden" name="opcionbase" value="<% out.print(_opcionBase);%>">
								<input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" name="objetoseleccionado" id="objetoseleccionado">
								<input type="hidden" value="actualizarobjeto" name="accionobjeto" id="accionobjeto">
								<label>Nombre de <% if(_elemento.equals("instrumento")){%> el <%}else{%> la <%} out.print(_elemento);%>:</label><br /><input type="text" value="<% out.print(objetoatrabajar.getObjeto()); %>" name="valorobjeto" id="valorobjeto" size="40" onkeyup="$('#miboton').removeAttr('disabled');"><p />
								<% if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
									<label style='display:none'>Tipo de Instrumento:</label>
									<select style='display:none' name="tipoobjeto" id="tipoobjeto" onchange="$('#miboton').removeAttr('disabled');">
										<option value="1" <% if(objetoatrabajar.getClass().equals(Censo.class)){out.print("selected='selected'");} %> >Censo</option>
										<option value="2" <% if(objetoatrabajar.getClass().equals(Encuesta.class)){out.print("selected='selected'");} %> >Encuesta</option>
										<option value="3" <% if(objetoatrabajar.getClass().equals(Relacion.class)){out.print("selected='selected'");} %> >Colecci&oacute;n de Datos</option>
									</select>
								<% }else if(objetoatrabajar.getClass().toString().contains("EstructuraBase")){ %>
									<input type="hidden" value="0" name="tipoobjeto" id="tipoobjeto">
								<% }else{ %>
									<label>Tipo de Instrumento:</label><br />
									<select name="tipoobjeto" id="tipoobjeto" onchange="$('#miboton').removeAttr('disabled');">
										<option value="1" <% if(objetoatrabajar.getClass().equals(Censo.class)){out.print("selected='selected'");} %> >Censo</option>
										<option value="2" <% if(objetoatrabajar.getClass().equals(Encuesta.class)){out.print("selected='selected'");} %> >Encuesta</option>
									</select><p />
								<% } %>
								<% if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
									<input type="hidden" value="1" name="visible" id="visible">
								<% }else{ %>
									<input type="hidden" value="0" name="visible" id="visible">
								<% } %>
								<input type="submit" value="Guardar Cambios" name="miboton" id="miboton" disabled="disabled" onclick="return compruebaNoVacio(getElementById('valorobjeto'))">
							</form>
							<% if(!objetoatrabajar.getClass().toString().contains("Relacion") && !_opcionBase.equals("eliminar")){ %>
								<br /><p />
								<% if(!_listadoPreguntas.isEmpty()){ %>
									<% if((!_elemento.equals("estructura")) && (objetoatrabajar.retornaPreguntaClave() == null) && (objetoatrabajar.retornaPreguntaComunicacionEmail() == null)){ %>
                                        <input type="button" value="Generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> " id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" onclick="confirmacion_aceptada = confirm('Desea generar un instrumento sin pregunta clave y son elemento de comunicacion por email.\n\n- Cuando no se define una pregunta clave se limitan las opciones en las colecciones de datos, ya que los individuos no pueden identificarse.\n- Cuando no se define este elemento se limita la posibilidad de contactar por email a los encuestados.\n\nDesea continuar sin definir la pregunta clave ni el elemento de comunicacion por email?\n\nRealmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');if(confirmacion_aceptada){window.location='adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=crear&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>';return true;}return confirmacion_aceptada; " />
                                    	<!-- Para generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> haga clic <a id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" href="adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=crear&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>" onclick="confirmacion_aceptada = confirm('Desea generar un instrumento sin pregunta clave y son elemento de comunicacion por email.\n\n- Cuando no se define una pregunta clave se limitan las opciones en las colecciones de datos, ya que los individuos no pueden identificarse.\n- Cuando no se define este elemento se limita la posibilidad de contactar por email a los encuestados.\n\nDesea continuar sin definir la pregunta clave ni el elemento de comunicacion por email?\n\nRealmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');return confirmacion_aceptada; ">aqu&iacute;</a> //-->
									<% }else if((!_elemento.equals("estructura")) && (objetoatrabajar.retornaPreguntaClave() == null)){ %>
                                        <input type="button" value="Generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> " id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" onclick="confirmacion_aceptada = confirm('Desea generar un instrumento sin pregunta clave, lo que significa que no se identificaran a los individuos de la poblacion.\n\n- Cuando no se define una pregunta clave se limitan las opciones en las colecciones de datos, ya que los individuos no pueden identificarse.\n\nDesea continuar sin definir la pregunta clave?\n\nRealmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');if(confirmacion_aceptada){window.location='adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=crear&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>';return true;}return confirmacion_aceptada; " />			
                                        <!-- Para generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> haga clic <a id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" href="adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=crear&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>" onclick="confirmacion_aceptada = confirm('Desea generar un instrumento sin pregunta clave, lo que significa que no se identificaran a los individuos de la poblacion.\n\n- Cuando no se define una pregunta clave se limitan las opciones en las colecciones de datos, ya que los individuos no pueden identificarse.\n\nDesea continuar sin definir la pregunta clave?\n\nRealmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');return confirmacion_aceptada; ">aqu&iacute;</a> //-->
									<% }else if((!_elemento.equals("estructura")) && (objetoatrabajar.retornaPreguntaComunicacionEmail() == null)){ %>
                                        <input type="button" value="Generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> " id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" onclick="confirmacion_aceptada = confirm('Desea generar un instrumento sin elemento de comunicacion por email.\n\n -Cuando no se define este elemento se limita la posibilidad de contactar por email a los encuestados.\n\nDesea continuar sin definir el elemento de comunicacion por email?\n\nRealmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');if(confirmacion_aceptada){window.location='adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=crear&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>';return true;}return confirmacion_aceptada; " />
									    <!-- Para generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> haga clic <a id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" href="" onclick="confirmacion_aceptada = confirm('Desea generar un instrumento sin elemento de comunicacion por email.\n\n -Cuando no se define este elemento se limita la posibilidad de contactar por email a los encuestados.\n\nDesea continuar sin definir el elemento de comunicacion por email?\n\nRealmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');return confirmacion_aceptada; ">aqu&iacute;</a> //-->
									<% }else{ %>
									    <input type="button" value="Generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> " id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" onclick="confirmacion_aceptada = confirm('Realmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');if(confirmacion_aceptada){window.location='adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=crear&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>';return true;}return confirmacion_aceptada; " />
                                        <!-- Para generar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %> haga clic <a id="link_crear" title="Es necesario generar, una vez haya finalizado de armar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else{out.print("este instrumento");} %>" href="adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=crear&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>" onclick="confirmacion_aceptada = confirm('Realmente desea generar <% out.print(objetoatrabajar.getObjeto()); %>?\nuna vez que sea generado no podra modificarse y estara disponible para su uso');return confirmacion_aceptada; ">aqu&iacute;</a> //-->
									<% } %>
								<% } %>
							<% } %>
							<br /><p />
							<%
							try{
								if(_opcionBase.equals("eliminar")){
								%>									
									Para eliminar <% if(_elemento.equals("estructura")){out.print("esta estructura");}else if(_elemento.equals("colecci&oacute;n de datos")){out.print("esta colecci&oacute;n de datos");}else{out.print("este instrumento");} %> haga clic <a alt="Eliminar <% out.print(_elemento);%>" href="adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&accionobjeto=eliminar&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>" onclick="return confirm('Realmente desea eliminar <% out.print(objetoatrabajar.getObjeto()); %> y las preguntas asociadas?');">aqu&iacute;</a>
								<% } %>
							<% }catch (Exception ee){} %>
						</div>
						<%
						if(showForm){
						%>
						<div style='display:block;' name="formularioPregunta" id="formularioPregunta" class="simplemodal-container">
						<%
						}else{
						%>
						<div style='display:none;' name="formularioPregunta" id="formularioPregunta" class="simplemodal-container">
						<%
						}
						%>
						<img src="comunes/imagenes/x.png" class="modalCloseImg" id="linkClose" name="linkClose" onclick="$.modal.close();" />
							<h4><span id="subtitulo2" name="subtitulo2"></span></h4>
							<form action="adminobjetos2.do" method="post">
								<input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" name="objetoseleccionado" id="objetoseleccionado">
								<input type="hidden" value="-1" name="preguntaseleccionada" id="preguntaseleccionada">
								<input type="hidden" value="insertar" name="accion" id="accion">
								<input type="hidden" name="tipoinstrumento" value="<% out.print(_tipoinstrumento);%>">
								<input type="hidden" name="opcionbase" value="<% out.print(_opcionBase);%>">
								<label>Nombre que tendr&aacute; la pregunta en <% out.print(_prefix+"&nbsp;"+_tipoinstrumento);%>:</label><br /><input type="text" value="" name="valor" id="valor" size="50"><p />
								<label>Acr&oacute;nimo: (Si es omitido se tomar&aacute; el nombre indicado en campo anterior)</label><br /><input type="text" value="" name="acronimo" id="acronimo" size="50"><p />
								<label>Orden de la pregunta:</label><br /><input type="text" value="0" name="orden" id="orden" size="3"><p />
								<input type="checkbox" id="campo_clave" name="campo_clave"><label>Establecer como pregunta clave en <% out.print(_prefix+"&nbsp;"+_tipoinstrumento);%></label><br />
								<!--// <input type="checkbox" id="campo_identificador" name="campo_identificador"><label>Establecer como elemento de identificaci&oacute;n</label><br /> //-->
								<input type="checkbox" id="campo_email" name="campo_email" onclick="if(this.checked){$('#campo_telefono').removeAttr('checked');$('#campo_telefono2').removeAttr('checked');}"><label>Establecer como elemento de comunicaci&oacute;n por email en <% out.print(_prefix+"&nbsp;"+_tipoinstrumento);%></label><br />
								<!--// <input type="checkbox" id="campo_telefono" name="campo_telefono" onclick="if(this.checked){$('#campo_email').removeAttr('checked');}"><label>Establecer como elemento de comunicaci&oacute;n por tel&eacute;fono</label><br /> //-->
								<!--// <input type="checkbox" id="campo_telefono2" name="campo_telefono2" onclick="if(this.checked){$('#campo_email').removeAttr('checked');}"><label>Establecer como elemento de comunicaci&oacute;n por tel&eacute;fono 2</label><p /> //-->
								<p />
								<% if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
									<label>pregunta creada a utilizar:</label><br />
									<select name="tipopregunta" id="tipopregunta" disabled="1" onchange="habilitadeshabilitaestudios();">
										<option value="-1" selected="selected">Seleccione...</option>
										<%
										try{
											Enumeration preguntasComunes = _listadoPreguntasComunes.elements();
											Pregunta preguntaComun;
											while(preguntasComunes.hasMoreElements()){
												preguntaComun = (Pregunta)preguntasComunes.nextElement();
												if(preguntaComun.getTipoPregunta() == 100){continue;}
												out.println("<option value='"+preguntaComun.getId()+"'>"+preguntaComun.getPregunta()+"</option>");
											}
										}catch(Exception e){}
										%>
									</select>
								<% }else{ %>
									<label>pregunta creada a utilizar:</label><br />
									<select name="tipopregunta" id="tipopregunta" onchange="compruebaVacioSelect(this);habilitadeshabilitaestudios();">
										<option value="-1" selected="selected">Seleccione...</option>
										<%
										try{
											Enumeration preguntasComunes = _listadoPreguntasComunes.elements();
											Pregunta preguntaComun;
											while(preguntasComunes.hasMoreElements()){
												preguntaComun = (Pregunta)preguntasComunes.nextElement();
												if(preguntaComun.getTipoPregunta() == 100){continue;}
												out.println("<option value='"+preguntaComun.getId()+"'>"+preguntaComun.getPregunta()+"</option>");
											}
										}catch(Exception e){}
										%>
									</select>
								<% } %>
								<br /><a id="linkPlantillaPregunta" href="#" onclick="$('#id_encapsulador').show('slow');revisarEstructura();" style="display:none;">Revisar informaci&oacute;n sobre esta pregunta</a>
								<% sesion.setAttribute("retornodedato", "adminobjetos2.do?&accion=seleccionar&objetoseleccionado="+objetoatrabajar.getId());%>
								<!--//<a href="adminpreguntas.do?showform=si">Crear nuevo dato.</a> -->
								<p />
								<div id="selectestudio" name="selectestudio" style="display:none;">
									<label>Indique el estudio:</label><br />
									<select name="tipoestudio" id="tipoestudio">
										<option value="-1" selected="selected">Seleccione...</option>
										<%
										try{
											Enumeration _estudios = _listadoEstudios.elements();
											Estudio _esEstudio;
											while(_estudios.hasMoreElements()){
												_esEstudio = (Estudio)_estudios.nextElement();
												out.println("<option value='"+_esEstudio.getId()+"'>"+_esEstudio.getTitulo()+"</option>");
											}
										}catch(Exception e){e.printStackTrace();}
										%>
									</select>
									<br /><a href="#" onclick="revisarEstudio();">Revisar Estudio</a><p />
								</div>
								<input type="hidden" id="ContinuarPreguntando" name="ContinuarPreguntando" value="no">
								<% if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
									<input type="submit" value="Agregar Pregunta" name="boton" id="boton" onclick="document.getElementById('ContinuarPreguntando').value = 'no';return (compruebaNoVacio(getElementById('valor')) && compruebaSeleccionado(getElementById('tipopregunta')) && compruebaSeleccionadoEstudio(getElementById('tipoestudio')))">
								<% }else{ %>
									<input type="submit" value="Ingresar" name="boton2" id="boton2" onclick="document.getElementById('ContinuarPreguntando').value = 'si';return (compruebaNoVacio(getElementById('valor')) && compruebaSeleccionado(getElementById('tipopregunta')) && compruebaSeleccionadoEstudio(getElementById('tipoestudio')))">
									<input type="submit" style='display:none' value="Actualizar" name="boton" id="boton" onclick="document.getElementById('ContinuarPreguntando').value = 'no';return (compruebaNoVacio(getElementById('valor')) && compruebaSeleccionado(getElementById('tipopregunta')) && compruebaSeleccionadoEstudio(getElementById('tipoestudio')))">
								<% } %>
								<input type="button" value="Finalizar" onclick="	$.modal.close();">
							</form>
						</div>
				</td>
				<td valign="top" align="left" style="max-width:65%;min-width:65%;padding-left:50px">
					<table cellpadding="4" cellspacing="4" border="0" width="100%">
						<tr>
							<td valign="top" align="left" nowrap>
								<% if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
									<!--// <a href="#" title="Agregar una Nueva Pregunta" alt="Agregar una Nueva Pregunta" onclick="habilitarInsertDivParaRelacion();"><label>Agregar una Nueva Pregunta.</label></a> //-->
								<% }else{ %>
									<a href="#" title="Agregar una Nueva Pregunta" alt="Agregar una Nueva Pregunta" onclick="habilitarInsertDiv();"><label>Agregar una Nueva Pregunta.</label></a>
								<% } %>
								<% if(!objetoatrabajar.getClass().toString().contains("Relacion")){ %>
									<br /><a href="adminobjetos3.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&objetoseleccionado=<% out.print(objetoatrabajar.getId()); %>" title="Importar Preguntas desde Otro <% out.print(_elemento);%>" ><label>Importar Preguntas desde <% if(_elemento.equals("estructura")){out.print("Otra Estructura");}else{out.print("Otro Instrumento o Estructura Base");} %>.</label></a>
								<% }else{ %>
									<a href="adminrelaciones.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&objetoseleccionado=<% out.print(objetoatrabajar.getId()); %>" title="Coleccionar Preguntas de Instrumentos"><label>Coleccione Datos de Instrumentos.</label></a><br />
								<%
									Enumeration _enuthis = InstanciaObjeto.todosObjetosInstanciados(objetoatrabajar.getUsuario(), objetoatrabajar.getConexion(), false, 0).elements();
									InstanciaObjeto _instanciaDeRelacion = null;
									while(_enuthis.hasMoreElements()){
										_instanciaDeRelacion = (InstanciaObjeto)_enuthis.nextElement();
										if(_instanciaDeRelacion.getObjetoAsociado().getId() == objetoatrabajar.getId()){
											break;
										}
									}
								%>
									<a href='admininsobj.do?objetoatrabajar=<% out.print(_instanciaDeRelacion.getId()); %>&accion=seleccionar&soloeste=true&accioninvitar=false&invitar=<% out.print(_instanciaDeRelacion.getId()); %>'>Revisar Todos Los Datos Coleccionados</a>
								<% } %>
							</td>
						</tr>
						<tr>
							<td valign="top" align="left">
								<% if(!_listadoPreguntas.isEmpty()){ %>
									<% if(!objetoatrabajar.getClass().toString().contains("Relacion")){ %>
										<strong><label>Listado de Preguntas Agregadas.</label></strong><p />
									<% }else{ %>
										<strong><label>Listado de Preguntas Coleccionadas.</label></strong><p />
									<% } %>
								<% } %>
								<table cellpadding="2" cellspacing="2" style="border-collapse:collapse" width="100%" border="0">
									<%
									try{
										Enumeration preguntasDisponibles = _listadoPreguntas.elements();
										InstanciaPregunta miPregunta;
										while(preguntasDisponibles.hasMoreElements()){
											miPregunta = (InstanciaPregunta)preguntasDisponibles.nextElement();
											%>
											<tr>
												<% if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
													<td align="left" valign="top"><a href="adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase); %>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accion=eliminar&preguntaseleccionada=<% out.print(miPregunta.getId()); %>&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>" onclick="return confirm('Realmente desea eliminar esta pregunta y las respuestas asociadas?');"><img src="comunes/imagenes/delete.png" alt="Eliminar Pregunta" title="Eliminar Pregunta" height="24" /></a></td>
													<td align="left" valign="top"><a href="#" onclick="habilitarUpdateDivParaRelacion(<% out.print(miPregunta.getId()); %>,'<% out.print(miPregunta.getAcronimo()); %>','<% out.print(UtilidadesVarias.reemplazarCaracteres(UtilidadesVarias.reemplazarCaracteres(miPregunta.getTextoPregunta(), "'", "\\'"), "\"", "\\'")); %>',<% out.print(miPregunta.getOrden()); %>, <% out.print(miPregunta.getPreguntaAsociada().getId()); %>, <% if(miPregunta.getEstudioAsociado() == null){out.print(-1);}else{out.print(miPregunta.getEstudioAsociado().getId());} %>, <% if(miPregunta.isCampo_clave_unico()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_identificador()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_email()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono2()){out.print("true");}else{out.print("false");}%>);habilitadeshabilitaestudios();"><img src="comunes/imagenes/modify.png" alt="Modificar Pregunta" title="Modificar Pregunta" height="24" /></a></td>
												<% }else{ %>
													<td align="left" valign="top"><a href="adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase); %>&accion=eliminar&preguntaseleccionada=<% out.print(miPregunta.getId()); %>&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>" onclick="return confirm('Realmente desea eliminar esta pregunta?');"><img src="comunes/imagenes/delete.png" alt="Eliminar Pregunta" title="Eliminar Pregunta" height="24" /></a></td>
													<td align="left" valign="top"><a href="#" onclick="habilitarUpdateDiv(<% out.print(miPregunta.getId()); %>,'<% out.print(miPregunta.getAcronimo()); %>','<% out.print(UtilidadesVarias.reemplazarCaracteres(UtilidadesVarias.reemplazarCaracteres(miPregunta.getTextoPregunta(), "'", "\\'"), "\"", "\\'")); %>',<% out.print(miPregunta.getOrden()); %>, <% out.print(miPregunta.getPreguntaAsociada().getId()); %>, <% if(miPregunta.getEstudioAsociado() == null){out.print(-1);}else{out.print(miPregunta.getEstudioAsociado().getId());} %>, <% if(miPregunta.isCampo_clave_unico()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_identificador()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_email()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono2()){out.print("true");}else{out.print("false");}%>);habilitadeshabilitaestudios();"><img src="comunes/imagenes/modify.png" alt="Modificar Pregunta" title="Modificar Pregunta" height="24" /></a></td>
												<% } %>
												<td align="left" valign="top" nowrap>
													<% if(miPregunta.isCampo_clave_unico()){%><img src="comunes/imagenes/primary_key_icon.jpg" height="24" alt="Pregunta clave" title="Pregunta clave"><%} %>
													<% /* if(miPregunta.isCampo_identificador()){ */ %><!--//<img src="comunes/imagenes/profile.png" height="24" alt="Elemento identificador" title="Elemento identificador">//--><% /* } */ %>
													<% if(miPregunta.isCampo_comunicacion_email()){%><img src="comunes/imagenes/email.png" height="24" alt="Elemento de comunicaci&oacute;n por email" title="Elemento de comunicaci&oacute;n por email"><%} %>
													<% /* if(miPregunta.isCampo_comunicacion_telefono()){ */ %><!--//<img src="comunes/imagenes/phone.png" height="24" alt="Elemento de comunicaci&oacute;n por tel&eacute;fono" title="Elemento de comunicaci&oacute;n por tel&eacute;fono">//--><% /* } */ %>
													<% /* if(miPregunta.isCampo_comunicacion_telefono2()){ */ %><!--//<img src="comunes/imagenes/phone.png" height="24" alt="Elemento de comunicaci&oacute;n por tel&eacute;fono" title="Elemento de comunicaci&oacute;n por tel&eacute;fono">//--><% /* } */ %>
												</td>
												<td align="left" valign="top"><% out.print(miPregunta.getOrden()); %></td>
												<% if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
													<td align="left" valign="top" width="100%"><a href="#" title="Haga clic aqu&iacute; para modificar esta pregunta" onclick="habilitarUpdateDivParaRelacion(<% out.print(miPregunta.getId()); %>,'<% out.print(miPregunta.getAcronimo()); %>','<% out.print(UtilidadesVarias.reemplazarCaracteres(UtilidadesVarias.reemplazarCaracteres(miPregunta.getTextoPregunta(), "'", "\\'"), "\"", "\\'")); %>',<% out.print(miPregunta.getOrden()); %>, <% out.print(miPregunta.getPreguntaAsociada().getId()); %>, <% if(miPregunta.getEstudioAsociado() == null){out.print(-1);}else{out.print(miPregunta.getEstudioAsociado().getId());} %>, <% if(miPregunta.isCampo_clave_unico()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_identificador()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_email()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono2()){out.print("true");}else{out.print("false");}%>);habilitadeshabilitaestudios();"><% out.print(miPregunta.getTextoPregunta()); %></a></td>
												<% }else{ %>
													<td align="left" valign="top" width="100%"><a href="#" title="Haga clic aqu&iacute; para modificar esta pregunta" onclick="habilitarUpdateDiv(<% out.print(miPregunta.getId()); %>,'<% out.print(miPregunta.getAcronimo()); %>','<% out.print(UtilidadesVarias.reemplazarCaracteres(UtilidadesVarias.reemplazarCaracteres(miPregunta.getTextoPregunta(), "'", "\\'"), "\"", "\\'")); %>',<% out.print(miPregunta.getOrden()); %>, <% out.print(miPregunta.getPreguntaAsociada().getId()); %>, <% if(miPregunta.getEstudioAsociado() == null){out.print(-1);}else{out.print(miPregunta.getEstudioAsociado().getId());} %>, <% if(miPregunta.isCampo_clave_unico()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_identificador()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_email()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono()){out.print("true");}else{out.print("false");}%>, <% if(miPregunta.isCampo_comunicacion_telefono2()){out.print("true");}else{out.print("false");}%>);habilitadeshabilitaestudios();"><% out.print(miPregunta.getTextoPregunta()); %></a></td>
												<% } %>
											</tr>
											<%
										}
									}catch(Exception e){}
									%>
								</table>
							</td>
						</tr>
						<%
					}
				}catch (Exception ee){}
			}else{
				out.print("No ha seleccionado "+_elemento+".<p />");
			}
			%>

			</table>
		</td>
	</tr>
</table>
<%
	try{
	if(request.getParameter("ContinuarPreguntando")!=null && request.getParameter("ContinuarPreguntando").equals("si")){
		%>  
		<script type="text/javascript" >
			$(document).ready(function(){
				habilitarInsertDiv();
			});
		</script>
		<%
	}
	if(_opcionBase.equals("eliminar")){
		if(objetoatrabajar.getClass().toString().contains("Relacion")){ %>
			<script type="text/javascript" >
				$(document).ready(function(){
					if(confirm('Realmente desea eliminar <% out.print(objetoatrabajar.getObjeto()); %> y las preguntas asociadas?')){
						window.location = "adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&<% if(request.getParameter("relacionid") != null){out.print("relacionid="+(String)request.getParameter("relacionid")+"&");} %>accionobjeto=eliminar&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>";
					}
				});
			</script>
		<% }else{ %>
			<script type="text/javascript" >
				$(document).ready(function(){
					if(confirm('Realmente desea eliminar <% out.print(objetoatrabajar.getObjeto()); %> y las preguntas asociadas?')){
						window.location = "adminobjetos2.do?tipoinstrumento=<% out.print(_tipoinstrumento);%>&opcionbase=<% out.print(_opcionBase);%>&accionobjeto=eliminar&objetoseleccionado=<% out.print(objetoatrabajar.getId());%>";
					}
				});
			</script>
		<% }
	}else if(_opcionBase.equals("crear")){
		%>
		<script type="text/javascript" >
			$(document).ready(function(){
				if($("#link_crear").click()){
					if(confirmacion_aceptada){
						window.location = document.getElementById("link_crear").href;
					}
				}
			});
		</script>
		<%
	}
}catch (Exception e){e.printStackTrace();}
%>
<%@include file="adminfooter.jsp" %>
