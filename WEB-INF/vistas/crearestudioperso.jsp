<%@page session="true" import="java.util.Enumeration" %>
<%@page import="java.util.Vector" %>
<%@ page import="java.util.HashMap" %>
<%@include file="admininicio.jsp" %>

<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 14/05/2011
-->

<%@include file="adminheader.jsp" %>

<script type="text/javascript">
var array_preguntas = new Array(); //contiene las preguntas disponibles


<%

Vector _datos = new Vector();
if(request.getAttribute("datos") != null){
	_datos = (Vector)request.getAttribute("datos");
	Enumeration _misPreguntas = _datos.elements();
	InstanciaPregunta preg;
	int i = 0, j = 0;
	while(_misPreguntas.hasMoreElements()){
		preg = (InstanciaPregunta)_misPreguntas.nextElement();
		out.println("array_preguntas["+i+"] = new Array();");
		out.println("array_preguntas["+i+"]['id'] = '"+preg.getId()+"';");
		out.println("array_preguntas["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(preg.getAcronimo(),"'","\\'").trim()+"';");
		out.println("array_preguntas["+i+"]['tipopregunta'] = '"+preg.getTipoPregunta()+"';");
		out.println("array_preguntas["+i+"]['ordenpregunta'] = '"+preg.getOrden()+"';");
		out.println("array_preguntas["+i+"]['tipodato'] = '"+preg.getPreguntaAsociada().getId()+"';");
		out.println("array_preguntas["+i+"]['respuestas'] = new Array();");

		Vector _resp = preg.respuestasPosibles();
		Enumeration _misRespuestas = _resp.elements();
		RespuestasPosibles resp;
		while(_misRespuestas.hasMoreElements()){
			resp = (RespuestasPosibles)_misRespuestas.nextElement();
			out.println("array_preguntas["+i+"]['respuestas']["+j+"] = new Array();");
			out.println("array_preguntas["+i+"]['respuestas']["+j+"]['id'] = '"+resp.getId()+"';");
			out.println("array_preguntas["+i+"]['respuestas']["+j+"]['respuesta'] = '"+UtilidadesVarias.reemplazarCaracteres(resp.getRespuesta(),"'","\\'").trim()+"';");
			j++;
		}
		i++;
		j = 0;
	}
}

InstanciaObjeto _objTrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	_objTrabajar = (InstanciaObjeto)request.getAttribute("objetoatrabajar");
}

String _codigo = "";
if(request.getAttribute("codigoestudio") != null){
	_codigo = (String)request.getAttribute("codigoestudio");
}

Boolean mostrarresultados = false;
if(request.getAttribute("mostrarresultados") != null){
	mostrarresultados = (Boolean)request.getAttribute("mostrarresultados");
}

Boolean mostrarguardar = true;
if(request.getAttribute("mostrarguardar") != null){
	mostrarguardar = (Boolean)request.getAttribute("mostrarguardar");
}

String errores ="";


%>

//crea un select con las preguntas disponibles asignando automanticamente su id, nombre
function creaSelectPreguntas(){
	var _select = document.getElementById("preguntas");
	var _temp;
	_select.options[0] = new Option("Seleccione...", "");
	for(var i = 0; i < array_preguntas.length ;i++){
		_temp = new Option(array_preguntas[i]["pregunta"], "["+array_preguntas[i]["pregunta"]+"]");
		_select.options[_select.options.length] = _temp;
	}
	if(_select.options.length > 0){
		_select.options[0].defaultSelected = true;
		_select.options[0].selected = true;
	}
}

function doGetCaretPosition (ctrl) {

	var CaretPos = 0;
	// IE Support
	if (document.selection) {

		ctrl.focus ();
		var Sel = document.selection.createRange ();
		Sel.moveStart ('character', -ctrl.value.length);
		CaretPos = Sel.text.length;
	}
	// Firefox support
	else if (ctrl.selectionStart || ctrl.selectionStart == '0')
		CaretPos = ctrl.selectionStart;

	return (CaretPos);
}


function setCaretPosition(ctrl, pos){
	if(ctrl.setSelectionRange){
		ctrl.focus();
		ctrl.setSelectionRange(pos,pos);
	}else if (ctrl.createTextRange) {
		var range = ctrl.createTextRange();
		range.collapse(true);
		range.moveEnd('character', pos);
		range.moveStart('character', pos);
		range.select();
	}
}

function insertAtCursor(myField, myValue){
	if(document.selection){
		//IE support
		var temp;
		myField.focus();
		var sel = document.selection.createRange();
		temp = sel.text.length;
		sel.text = myValue;
		if(myValue.length == 0){
			sel.moveStart('character', myValue.length);
			sel.moveEnd('character', myValue.length);
		}else{
			sel.moveStart('character', -myValue.length + temp);
		}
		//sel.select();
	}else if (myField.selectionStart || myField.selectionStart == '0'){
		//MOZILLA/NETSCAPE support
		var startPos = myField.selectionStart;
		var endPos = myField.selectionEnd;
		myField.value = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
		myField.selectionStart = startPos + myValue.length;
		myField.selectionEnd = startPos + myValue.length;
	}else{
		myField.value += myValue;
	}
}

function validarEntero(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
  var er = /^[-]*[0-9]+$/;
  if(mielem.value.match(er)){
	  return true;
  }else{
	  alert("Solo pueden ingresarse numeros enteros.");
	  mielem.value = "0";
	  mielem.focus();
	  return false;
  }
}

function validarDouble(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
  var er = /^[-+]?[0-9]+(\.[0-9]+)?$/;
  if(mielem.value.match(er)){
	  return true;
  }else{
	  alert("Solo pueden ingresarse numeros con o sin decimales.");
	  mielem.value = "0.0";
	  mielem.focus();
	  return false;
  }
}

function ltrim(str, chars) {
	chars = chars || "\\s";
	return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
}

function rtrim(str, chars) {
	chars = chars || "\\s";
	return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
}

//sin chars, se eliminan espacios en blanco...
function trim(str, chars) {
	return ltrim(rtrim(str, chars), chars);
}

//crea un select con las respuestas disponibles asignando automanticamente su id, nombre
//si _tipodedato es false busca la pregunta padre en el select de preguntas... caso contrario
//busca las respuestas asociadas del select de variables pedidas
function creaSelectRespuestas(_tipodedato){
	var _tipoPregunta = 30;
	var _valorpadre;
	var _span;
	var _select;
	var _boton = null;

	try{
		_span = document.getElementById("span_respuestas");
		if(_span == null){
			_span = document.createElement("span");
		}
	}catch(error){
		_span = document.createElement("span");
	}

	try{
		var _padre;
		if(_tipodedato){
			_padre = document.getElementById("valores_solicitados");
		}else{
			_padre = document.getElementById("preguntas");
		}
		if(_padre == null){
			_valorpadre = 0;
		}else{
			_valorpadre = _padre.value;
		}
	}catch(error){
		_valorpadre = 0;
	}

	for(var i = 0; i < array_preguntas.length ;i++){
		if(_tipodedato){
			if(trim(_valorpadre, " ") != trim(array_preguntas[i]["tipodato"], " ")){
				continue;
			}
		}else{
			if(trim(_valorpadre, " ") != '['+trim(array_preguntas[i]["pregunta"], " ")+']'){
				continue;
			}
		}
		_tipoPregunta = array_preguntas[i]["tipopregunta"];

		try{
			var _select2 = document.getElementById("respuestas");
			if(_select2 != null){
				//lo consigue debe entonces borrar lo que hay antes de agregar
				var _tempPadre = _select2.parentNode;
				_tempPadre.removeChild(_select2);
			}
		}catch(error){
		}

		try{
			var _boton2 = document.getElementById("botonAgregar");
			if(_boton2 != null){
				//lo consigue debe entonces borrar lo que hay antes de agregar
				var _tempPadre = _boton2.parentNode;
				_tempPadre.removeChild(_boton2);
			}
		}catch(error){
		}

		if(_tipoPregunta >= 30){
			_select = document.createElement("input");
			_select.type = "text";

			_boton = document.createElement("input");
			_boton.type = "button";
			_boton.value = "agregar";
			_boton.id = "botonAgregar";
			_boton.name = "botonAgregar";

			if(_tipoPregunta == 30){
				_boton.onclick = function(){
					var textArea = document.getElementById("codigoestudio");
					if(_select.value == ""){
						return false;
					}
					var _stringTemporal = '"'+_select.value+'"';
					insertAtCursor(textArea, _stringTemporal);
					textArea.focus();
				};
			}
			if(_tipoPregunta == 31){
				//solo entero
				_select.value = "0";
				_select.onblur = function(){
					validarEntero("respuestas");
				}
				_boton.onclick = function(){
					var textArea = document.getElementById("codigoestudio");
					if(_select.value == ""){
						return false;
					}
					var _stringTemporal = _select.value;
					insertAtCursor(textArea, _stringTemporal);
					textArea.focus();
				};
			}
			if(_tipoPregunta == 32){
				//solo Double
				_select.value = "0.0";
				_select.onblur = function(){
					validarDouble("respuestas");
				}
				_boton.onclick = function(){
					var textArea = document.getElementById("codigoestudio");
					if(_select.value == ""){
						return false;
					}
					var _stringTemporal = _select.value;
					insertAtCursor(textArea, _stringTemporal);
					textArea.focus();
				};
			}
			if(_tipoPregunta == 33){
				//solo fecha
				_select.readOnly = true;
				_select.onclick = function(){
 	          	if(self.gfPop)gfPop.fPopCalendar(document.getElementById("respuestas"));
					return false;
				}
				_boton.onclick = function(){
					var textArea = document.getElementById("codigoestudio");
					if(_select.value == ""){
						return false;
					}
					var _stringTemporal = '"'+_select.value+'"';
					insertAtCursor(textArea, _stringTemporal);
					textArea.focus();
				};
			}

		}else{
			_select = document.createElement("select");
			_select.multiple = false;
			var _temp = new Option("Seleccione...", "", true, true);
			_select.options[_select.options.length] = _temp;
			for(var j = 0; j < array_preguntas[i]["respuestas"].length ;j++){
				_temp = new Option(array_preguntas[i]["respuestas"][j]["respuesta"], array_preguntas[i]["respuestas"][j]["respuesta"]);
				_select.options[_select.options.length] = _temp;
			}
			_select.onchange = function() {
				var textArea = document.getElementById("codigoestudio");
				if(_select.value == ""){
					return false;
				}
				var _stringTemporal = '"'+_select.value+'"';
				insertAtCursor(textArea, _stringTemporal);
				textArea.focus();
			};
		}
		_select.name = "respuestas";
		_select.id = "respuestas";
		break;
	}

	_span.name = "span_respuestas";
	_span.id = "span_respuestas";
	if(_select != null){
		_span.appendChild(_select);
		if(_boton != null){
			_span.appendChild(_boton);
		}
	}
}

$(document).ready(function(){
	$("#link_estudios").css("color","red");
});


<% if((request.getParameter("opcionbase")!= null) && "eliminar".equals((String)request.getParameter("opcionbase"))){ %>

function eliminarEsto(){
	if(confirm('Realmente desea eliminar este estudio?')){
		window.location = "crearestudioperso?accion=delete&objetoatrabajar=<% out.print(_objTrabajar.getId());%>&idestudio=<% out.print(EstudioPerso.getInstance().get_id());%>";
	}
}

$(document).ready(function(){
	eliminarEsto();
});
<% } %>

</script>

<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
			<h2 class="hideprint">Creaci&oacute;n del estudio</h2>
			<%
			if(mostrarresultados){
				if(EstudioPerso.getInstance().hayErrores()){
					String _error = EstudioPerso.getInstance().firstError();
					while(!_error.equals("")){
						errores = errores+"Error: "+_error+"\n";
						_error = EstudioPerso.getInstance().nextError();
					}
				}
				if(EstudioPerso.getInstance().hayResultado() && !EstudioPerso.getInstance().hayErrores()){
					String _resul = "";
					if(EstudioPerso.getInstance().get_titulo() != null && !EstudioPerso.getInstance().get_titulo().equals("")){
						out.print("<h4>"+EstudioPerso.getInstance().get_titulo()+"</h4>");
					}
					out.println("<table id=\"_myTable\" class=\"tablesorter\"  style=\"min-width:100px;max-width:940px;width:100px\">");
					out.println("<tr><th>Resultado(s)</th></tr><tbody>");
					HashMap _resulMostrados = new HashMap();
					_resul = EstudioPerso.getInstance().firstResultado();
					if(_resul != null){
						_resulMostrados.put(_resul, true);
						out.print("<tr><td nowrap>"+_resul+"</td></tr>");
					}
					while(EstudioPerso.getInstance().hayMasResultados()){
						if((_resul = EstudioPerso.getInstance().nextResultado()) != null){
							if(_resulMostrados.get(_resul) != null){continue;}
							_resulMostrados.put(_resul, true);
							out.print("<tr><td nowrap>"+_resul+"</td></tr>");
						}
					}
					out.print("</tbody></table>");
					%>
					<form id="expor" method="post" action="#" target="_blank" class="hideprint">
					<input type="hidden" value="" id="exportar" name="exportar">
					<input type="hidden" value="<% out.print(_objTrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
					<input type="hidden" value="<% out.print(_codigo); %>" id="codigoestudio" name="codigoestudio">
					<a href="javascript:void(null);" onclick="$('#expor').attr('action', 'est_archivo.pdf');$('#exportar').val('exportapdf');$('#guardar').val('');$('#expor').submit();"><img height="24" src="comunes/imagenes/pdf.png" alt="Exportar estos datos a pdf" title="Exportar estos datos a pdf"/></a>
					&nbsp;&nbsp;<a href="javascript:void(null);" onclick="$('#expor').attr('action', 'est_archivo.docx');$('#exportar').val('exportaword');$('#guardar').val('');$('#expor').submit();"><img height="24" src="comunes/imagenes/word.png" alt="Exportar estos datos a word" title="Exportar estos datos a word"/></a>
					&nbsp;&nbsp;<a href="javascript:void(null);" onclick="$('#expor').attr('action', 'est_archivo.xls');$('#exportar').val('exportaexcel');$('#guardar').val('');$('#expor').submit();"><img height="24" src="comunes/imagenes/excel.png" alt="Exportar estos datos a excel" title="Exportar estos datos a excel"/></a>
					&nbsp;&nbsp;<a href="javascript:void(null);" onclick="$('.qtip').hide();self.print();"><img height="24" src="comunes/imagenes/print.png" alt="Imprimir" title="Imprimir"/></a>
					</form><p />
					<%
				}else if(EstudioPerso.getInstance().hayErrores()){
					%>
					<h4>Errores</h4>
					<%
					out.print(errores);
				}else{
					%>
					No se obtuvieron resultados.
					<%
				}
				if(!EstudioPerso.getInstance().hayErrores() && mostrarguardar){
					//desea guardarel estudio?
					%>
					<h4 class="hideprint">Desea guardar este estudio?</h4>
					<form id="formestudios" method="post" action="crearestudioperso" onsubmit="if(document.getElementById('titulo').value==''){alert('Debe indicar el titulo.');document.getElementById('titulo').focus();return false;}if(document.getElementById('acronimo').value==''){alert('Debe indicar el acronimo.');document.getElementById('acronimo').focus();return false;}" class="hideprint">
						<input type="hidden" value="true" id="guardar" name="guardar">
						<input type="hidden" value="<% out.print(_objTrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
						<input type="hidden" value="<% out.print(_codigo); %>" id="codigoestudio" name="codigoestudio">
						<table>
							<tr>
								<td>
									T&iacute;tulo:
								</td>
								<td>
									<input type="text" size="50" value="" id="titulo" name="titulo">
								</td>
							</tr>
							<tr>
								<td>
									Acr&oacute;nimo:
								</td>
								<td>
									<input type="text" size="30" value="" id="acronimo" name="acronimo">
								</td>
							</tr>
						</table>
						<input type="submit" value="Guardar" />
					</form><p />
					<%
				}
			}else{
				if(_datos.isEmpty()){
					out.println("No existen datos disponibles en la estructura para crear un estudio.");
				}else{
					%>
					<form id="formestudios" method="post" action="crearestudioperso" class="hideprint">
						<input type="hidden" value="<% out.print(_objTrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
						<table>
							<tr>
								<td width="130" style="max-width:150px;text-align:right;">
									<span id="titulo_preguntas"><label>Datos:</label></span>
								</td>
								<td width="35%" style="text-align:left;">
									<select id="preguntas" name="preguntas" onchange="creaSelectRespuestas(false);">
										<option value="" selected="selected">Seleccione...</option>
									</select>
									<script type="text/javascript" charset="UTF-8">
										creaSelectPreguntas();
									</script>
									<input type="button" value="Ingresar" onclick="if(document.getElementById('preguntas').value != ''){insertAtCursor(document.getElementById('codigoestudio'), document.getElementById('preguntas').value);}" />
								</td>
								<td width="150" style="max-width:150px;text-align:right;">
									<span id="titulo_respuestas"><label>Ingresar respuesta:</label></span>
								</td>
								<td width="35%" style="text-align:left;">
									<span id="span_respuestas">&nbsp;</span>
								</td>
							</tr>
							<tr>
								<td width="130" style="max-width:150px;text-align:right;">
									<span id="titulo_oepraciones"><label>Operaciones:</label></span>
								</td>
								<td width="35%" style="text-align:left;">
									<select id="operadores" name="operadores" onchange="insertAtCursor(document.getElementById('codigoestudio'), this.value);this.value='';">
										<option value="" selected="selected">Seleccione...</option>
										<optgroup label="Operadores aritm&eacute;ticos binarios">
											<option value="+">Suma: +</option>
											<option value="-">Resta: -</option>
											<option value="*">Multiplicaci&oacute;n: *</option>
											<option value="/">Divisi&oacute;n: /</option>
										</optgroup>
										<optgroup label="Operadores l&oacute;gicos">
											<option value="==">Es igual a</option>
											<option value="<>">Es distinto a</option>
											<option value="Y">Y l&oacute;gico</option>
											<option value="O">O l&oacute;gico</option>
											<option value="NO( )">Opuesto al valor dado, negaci&oacute;n</option>
											<option value=">=">Mayor o igual que ( >= )</option>
											<option value=">">Mayor estricto que ( > )</option>
											<option value="<=">Menor o igual que ( <= )</option>
											<option value="<">Menor estricto que ( < )</option>
										</optgroup>
										<optgroup label="Funciones">
											<option value="promedio( )">Promedio de un dato</option>
											<option value="sumatoria( )">Sumatoria de un dato</option>
											<option value="contar( )">Conteo de respuestas de un dato</option>
											<option value="max( )">M&aacute;ximo valor de un dato</option>
											<option value="min( )">M&iacute;nimo valor de un dato</option>
											<option value="redondea( )">Redondea un valor</option>
										<optgroup label="Funciones Utilitarias">
											<option value="diff_fechas( , )">Calcula diferencia entre fechas en a&ntilde;os</option>
											<option value="concatenar( , )">Concatena dos valores</option>
										</optgroup>
										</optgroup>
									</select>
								</td>
								<td width="150" style="max-width:150px;text-align:right;">
									<span id="titulo_acciones"><label>Acciones preestablecidas:</label></span>
								</td>
								<td width="35%" style="text-align:left;">
									<select id="acciones" name="acciones" onchange="insertAtCursor(document.getElementById('codigoestudio'), this.value);this.value='';">
										<option value="" selected="selected">Seleccione...</option>
										<option value="pre{ }" >Pre-acciones</option>
										<option value="post{ }" >Post-acciones</option>
										<option value="si( ; )" >Condicional</option>
										<option value="obtener " >Obtener valor</option>
									</select>
								</td>
							</tr>
						</table>
						<p />
						<center><textarea rows="25" cols="130" style="width:700px;height:200px" id="codigoestudio" name="codigoestudio"><% out.print(_codigo); %></textarea>
						<p /><input type="submit" value="Procesar" /></center>
					</form>
					<div id="errores" style="color:red">
						<%
						out.print(errores);
						%>
					</div>
					<%
				}
			}
			%>
		</td>
	</tr>
</table>

<iframe name="gToday:contrast:agenda.js" id="gToday:contrast:agenda.js" src="Contrast/ipopeng.htm" style="visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;" scrolling="no" frameborder="0" height="142" width="132">
</iframe>
<%@include file="adminfooter.jsp" %>
