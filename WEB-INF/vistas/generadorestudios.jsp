<%@ page import="it.sauronsoftware.base64.Base64" %>
<%@include file="admininicio.jsp" %>

<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 12/01/2010
 * Hora: 06:14:48 PM
-->

<script type="text/javascript" charset="UTF-8">
/**
*
*  Base64 encode / decode
*  http://www.webtoolkit.info/
*
**/

var Base64 = {

	// private property
	_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

	// public method for encoding
	encode : function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;

		input = Base64._utf8_encode(input);

		while (i < input.length) {

			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);

			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;

			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}

			output = output +
			this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
			this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

		}

		return output;
	},

	// public method for decoding
	decode : function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;

		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

		while (i < input.length) {

			enc1 = this._keyStr.indexOf(input.charAt(i++));
			enc2 = this._keyStr.indexOf(input.charAt(i++));
			enc3 = this._keyStr.indexOf(input.charAt(i++));
			enc4 = this._keyStr.indexOf(input.charAt(i++));

			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;

			output = output + String.fromCharCode(chr1);

			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}

		}

		output = Base64._utf8_decode(output);

		return output;

	},

	// private method for UTF-8 encoding
	_utf8_encode : function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";

		for (var n = 0; n < string.length; n++) {

			var c = string.charCodeAt(n);

			if (c < 128) {
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}

		return utftext;
	},

	// private method for UTF-8 decoding
	_utf8_decode : function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;

		while ( i < utftext.length ) {

			c = utftext.charCodeAt(i);

			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			}
			else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			}
			else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}

		}

		return string;
	}

}
var array_preguntas = new Array(); //contiene las preguntas disponibles
var array_preguntas_estaticas = new Array(); //contiene las preguntas disponibles
var valido = false;
var error = "";
var _declarando = true;
var _lineaactual = 0;  //contador de lineas para validar codigo
var array_historial = new Array(); //para undo, redo
var historia_actual = 0;
array_historial[0] = new Array(); //empieza vacio si es modificacion luego se actualiza
array_historial[0]['posicion'] = 0;
array_historial[0]['codigo'] = "";

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

function setVisualTitulo(_mititulo, _color){
	var _titulo = document.getElementById(_mititulo);
	_titulo.style.textDecoration = "underline";
	_titulo.style.fontWeight  = "bold";
	_titulo.style.color = _color;
}

function resetVisualTitulos(){
	var _titulo = document.getElementById("titulo_respuestas");
	_titulo.style.textDecoration = "none";
	_titulo.style.fontWeight  = "normal";
	_titulo.style.color = "black";

	_titulo = document.getElementById("titulo_preguntas");
	_titulo.style.textDecoration = "none";
	_titulo.style.fontWeight  = "normal";
	_titulo.style.color = "black";

	_titulo = document.getElementById("titulo_palabras_reservadas");
	_titulo.style.textDecoration = "none";
	_titulo.style.fontWeight  = "normal";
	_titulo.style.color = "black";

	_titulo = document.getElementById("titulo_operadores");
	_titulo.style.textDecoration = "none";
	_titulo.style.fontWeight  = "normal";
	_titulo.style.color = "black";

	_titulo = document.getElementById("titulo_tipos_de_datos");
	_titulo.style.textDecoration = "none";
	_titulo.style.fontWeight  = "normal";
	_titulo.style.color = "black";
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

<%

if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}

boolean compilado = false;
boolean validaCompilacion = false;
Vector misErrores = new Vector();
if (request.getAttribute("compilado") != null) {
	compilado = (Boolean)request.getAttribute("compilado");
	validaCompilacion = (Boolean)request.getAttribute("ValidadaCompilacion");
	misErrores = (Vector)request.getAttribute("listadoErrores");
}

Vector _listaTipoDatos = new Vector();
if(request.getAttribute("listaTipoDeDatos") != null){
  _listaTipoDatos = (Vector)request.getAttribute("listaTipoDeDatos");
	Collections.sort(_listaTipoDatos, new OrdenadorPreguntas(OrdenadorPreguntas.PREGUNTA));
}

Vector _todas_preguntas_estaticas = new Vector();
if(request.getAttribute("_todas_preguntas_estaticas") != null){
  _todas_preguntas_estaticas = (Vector)request.getAttribute("_todas_preguntas_estaticas");
}

Estudio estudioACargar = null;
if(request.getAttribute("estudioACargar") != null){
	estudioACargar = (Estudio)request.getAttribute("estudioACargar");
}

Vector _todasPreguntas = new Vector();
Objeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (Objeto)request.getAttribute("objetoatrabajar");
	_todasPreguntas = objetoatrabajar.getPreguntas();
	Collections.sort(_todasPreguntas, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
}


if(objetoatrabajar != null){
	Enumeration _misPreguntas = _todasPreguntas.elements();
	InstanciaPregunta preg;
	int i = 0, j = 0;
	while(_misPreguntas.hasMoreElements()){
		preg = (InstanciaPregunta)_misPreguntas.nextElement();
		if(preg.getTipoPregunta() == 100){continue;}
		out.println("array_preguntas["+i+"] = new Array();");
		out.println("array_preguntas["+i+"]['id'] = '"+preg.getId()+"';");
		out.println("array_preguntas["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(preg.getAcronimo(),"'","\\'")+"';");
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
			out.println("array_preguntas["+i+"]['respuestas']["+j+"]['respuesta'] = '"+UtilidadesVarias.reemplazarCaracteres(resp.getRespuesta(),"'","\\'")+"';");
			j++;
		}
		i++;
		j = 0;
	}

	_misPreguntas = _todas_preguntas_estaticas.elements();
	Pregunta _preg;
	i = 0;
	j = 0;
	while(_misPreguntas.hasMoreElements()){
		_preg = (Pregunta)_misPreguntas.nextElement();
		if(_preg.getTipoPregunta() == 100){continue;}
		out.println("array_preguntas_estaticas["+i+"] = new Array();");
		out.println("array_preguntas_estaticas["+i+"]['id'] = '"+_preg.getId()+"';");
		out.println("array_preguntas_estaticas["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_preg.getPregunta(),"'","\\'")+"';");
		out.println("array_preguntas_estaticas["+i+"]['tipopregunta'] = '"+_preg.getTipoPregunta()+"';");
		out.println("array_preguntas_estaticas["+i+"]['tipodato'] = '"+_preg.getId()+"';");
		out.println("array_preguntas_estaticas["+i+"]['respuestas'] = new Array();");

		Vector _resp = _preg.retornaRespuestasPosibles();
		Enumeration _misRespuestas = _resp.elements();
		RespuestasPosibles resp;
		while(_misRespuestas.hasMoreElements()){
			resp = (RespuestasPosibles)_misRespuestas.nextElement();
			out.println("array_preguntas_estaticas["+i+"]['respuestas']["+j+"] = new Array();");
			out.println("array_preguntas_estaticas["+i+"]['respuestas']["+j+"]['id'] = '"+resp.getId()+"';");
			out.println("array_preguntas_estaticas["+i+"]['respuestas']["+j+"]['respuesta'] = '"+UtilidadesVarias.reemplazarCaracteres(resp.getRespuesta(),"'","\\'")+"';");
			j++;
		}
		i++;
		j = 0;
	}
}
%>
<%
try{
	if(request.getParameter("codigo")!=null){
		out.print("array_historial[0] = new Array();");
		out.print("array_historial[0]['codigo'] = '"+Base64.encode((String)request.getParameter("codigo"), "UTF-8")+"';");
		%>
			$(document).ready(function(){
				array_historial[0]['posicion'] = doGetCaretPosition(document.getElementById("codigo"));
			});
		<%
	}else{
		if(estudioACargar != null){
			out.print("array_historial[0] = new Array();");
			out.print("array_historial[0]['codigo'] = '"+Base64.encode(estudioACargar.getCodigo(), "UTF-8")+"';");
			%>
				$(document).ready(function(){
					array_historial[0]['posicion'] = doGetCaretPosition(document.getElementById("codigo"));
				});
			<%
		}
	}
}catch(Exception ee){ee.printStackTrace();} 

%>
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
	//almaceno historial
	if(historia_actual < 30){
		historia_actual++;
		array_historial[historia_actual] = new Array();
		array_historial[historia_actual]['codigo'] = Base64.encode($("#codigo").val());
		array_historial[historia_actual]['posicion'] = doGetCaretPosition(document.getElementById("codigo"));
	}else{
		array_historial[30]['codigo'] = Base64.encode($("#codigo").val());
		array_historial[30]['posicion'] = doGetCaretPosition(document.getElementById("codigo"));
	}
}

//crea un select con las preguntas disponibles asignando automanticamente su id, nombre
function creaSelectPreguntas(){
	var _select = document.getElementById("preguntas");
	var _temp;
	_select.options[0] = new Option("Seleccione...", "");
	for(var i = 0; i < array_preguntas.length ;i++){
		_temp = new Option(array_preguntas[i]["pregunta"], array_preguntas[i]["id"]+"-"+array_preguntas[i]["pregunta"]);
		_select.options[_select.options.length] = _temp;
	}
	if(_select.options.length > 0){
		_select.options[0].defaultSelected = true;
		_select.options[0].selected = true;
	}
}

function ingresar_tipo_de_dato(_select){
	var textArea = document.getElementById("codigo");

	resetVisualTitulos();
	insertAtCursor(textArea, " "+trim(_select.value, "")+" ");
	setVisualTitulo("titulo_preguntas", "green");
	setVisualTitulo("titulo_palabras_reservadas","green")
	_select.value = "";
	textArea.focus();
}

function ingresar_palabra_clave(_select){
	var textArea = document.getElementById("codigo");
	var _stringTemporal = "";

	var _posicionAc = doGetCaretPosition(textArea);
	
	resetVisualTitulos();
	if(_select.value == 'IF'){
		_stringTemporal = "CUANDO SE CUMPLA CON:\n\nREALIZAR:\n\nFIN..\n";
		setVisualTitulo("titulo_preguntas", "green");
		setVisualTitulo("titulo_operadores", "green");
		insertAtCursor(textArea, _stringTemporal);
	}else if(_select.value == 'COMENTARIO'){
		//_stringTemporal = "!#----/ comentario";
		$("#formularioComentario").modal();
		$("#inputComentario").focus();
		setVisualTitulo("titulo_preguntas", "green");
		setVisualTitulo("titulo_operadores", "green");
	}else if(_select.value == 'CONSTANTE'){
		//_stringTemporal = " ^valor^ ";
		$("#formularioConstante").modal();
		$("#inputConstante").focus();
		setVisualTitulo("titulo_preguntas", "green");
		setVisualTitulo("titulo_operadores", "green");
	}else if(_select.value == 'PEDIR'){
		//_stringTemporal = "PEDIR EN EJECUCION:\nDE TIPO DE DATO:\n\n USAR ACRONIMO:\n\n MOSTRAR TITULO:\n\nFIN.-\n";
		$("#formularioPedirValor").modal();
		setVisualTitulo("titulo_tipos_de_datos", "red");
		$("#tituloPV").focus();
	}else if(_select.value == 'OPERAR'){
		_stringTemporal = "REALIZAR CALCULO:\n\nFIN DEL CALCULO.-\n";
		setVisualTitulo("titulo_preguntas", "green");
		setVisualTitulo("titulo_operadores", "green");
		insertAtCursor(textArea, _stringTemporal);
	}
	if(_select.value == 'IF'){
		setCaretPosition(textArea, _posicionAc+22);
	}else if(_select.value == 'OPERAR'){
		setCaretPosition(textArea, _posicionAc+18);
	}
	if(_select.value == 'COMENTARIO'){
		$("#inputComentario").focus();
	}else if(_select.value == 'CONSTANTE'){
		$("#inputConstante").focus();
	}else if(_select.value == 'PEDIR'){
		$("#tituloPV").focus();
	}else{
		textArea.focus();
	}
	_select.value = "";
}

function ingresar_operador(_select){
	var textArea = document.getElementById("codigo");
	var _stringTemporal = "";

	resetVisualTitulos();
	if(_select.value == '+'){
		_stringTemporal = " + ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '-'){
		_stringTemporal = " - ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == 'x'){
		_stringTemporal = " * ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '/'){
		_stringTemporal = " / ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '~'){
		_stringTemporal = " PARECIDO A: ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '='){
		_stringTemporal = " ASIGNAR: ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '=='){
		_stringTemporal = " ES IGUAL A: ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '<>'){
		_stringTemporal = " ES DISTINTO A: ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == 'Y'){
		_stringTemporal = " Y ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '<='){
		_stringTemporal = " <= ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '<'){
		_stringTemporal = " < ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '>'){
		_stringTemporal = " > ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == '>='){
		_stringTemporal = " >= ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == 'O'){
		_stringTemporal = " O ";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == 'NO'){
		_stringTemporal = " NEGACION DE:( )";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "green");
	}else if(_select.value == 'TOTAL'){
		_stringTemporal = "CONTAR TOTALES DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'COUNT'){
		_stringTemporal = "CONTAR COINCIDENCIAS DE:(pregunta)";
		setVisualTitulo("titulo_respuestas", "green");
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'SUM'){
		_stringTemporal = "SUMAR VALORES DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'PROM'){
		_stringTemporal = "PROMEDIAR VALORES DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'SIN'){
		_stringTemporal = "SENO DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'COS'){
		_stringTemporal = "COSENO DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'TAN'){
		_stringTemporal = "TANGENTE DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'LOG'){
		_stringTemporal = "LOGARITMO BASE(base) DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'EXP'){
		_stringTemporal = "EXPONENCIAL BASE(base) DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}else if(_select.value == 'SQR'){
		_stringTemporal = "RAIZ BASE(base) DE:(pregunta)";
		setVisualTitulo("titulo_preguntas", "red");
	}
	insertAtCursor(textArea, _stringTemporal);
	_select.value = "";

	textArea.focus();
}


function creaSelectRespuestasEstaticas(){
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
		_padre = document.getElementById("tipo_de_dato");
		if(_padre == null){
			_valorpadre = 0;
		}else{
			_valorpadre = _padre.value;
		}
	}catch(error){
		_valorpadre = 0;
	}

	var separadosPadre;
	separadosPadre = _valorpadre.split("-");
	_valorpadre = separadosPadre[0];

	for(var i = 0; i < array_preguntas_estaticas.length ;i++){
		if(_valorpadre != array_preguntas_estaticas[i]["tipodato"]){
			continue;
		}
		_tipoPregunta = array_preguntas_estaticas[i]["tipopregunta"];
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

			if(_tipoPregunta == 31){
				//solo entero
				_select.value = "0";
				_select.onblur = function(){
						validarEntero("respuestas");
					}
			}
			if(_tipoPregunta == 32){
				//solo Double
				_select.value = "0.0";
				_select.onblur = function(){
						validarDouble("respuestas");
					}
			}
			if(_tipoPregunta == 33){
				//solo fecha
				_select.readOnly = true;
				_select.onclick = function(){
 	          if(self.gfPop)gfPop.fPopCalendar(document.getElementById("respuestas"));
						return false;
					}
			}
			_boton = document.createElement("input");
			_boton.type = "button";
			_boton.value = "agregar";
			_boton.id = "botonAgregar";
			_boton.name = "botonAgregar";
			_boton.onclick = function(){
				var textArea = document.getElementById("codigo");
				if(_select.value == ""){
					return false;
				}
				var _stringTemporal = " ^"+_select.value+"^ ";
				insertAtCursor(textArea, _stringTemporal);

				resetVisualTitulos();
				setVisualTitulo("titulo_operadores", "green");
				setVisualTitulo("titulo_preguntas", "green");
				setVisualTitulo("titulo_palabras_reservadas", "green");
				textArea.focus();
			};


		}else{
			_select = document.createElement("select");
			_select.multiple = false;
			var _temp = new Option("Seleccione...", "", true, true);
			_select.options[_select.options.length] = _temp;
			_temp = new Option("0-No sabe / No responde", "0-No sabe / No responde");
			_select.options[_select.options.length] = _temp;
			for(var j = 0; j < array_preguntas_estaticas[i]["respuestas"].length ;j++){
				_temp = new Option(array_preguntas_estaticas[i]["respuestas"][j]["id"]+"-"+array_preguntas_estaticas[i]["respuestas"][j]["respuesta"], array_preguntas_estaticas[i]["respuestas"][j]["id"]+"-"+array_preguntas_estaticas[i]["respuestas"][j]["respuesta"]);
				_select.options[_select.options.length] = _temp;
			}
			_select.onchange = function() {
				var textArea = document.getElementById("codigo");
				if(_select.value == ""){
					return false;
				}
				var _stringTemporal = " RESPUESTA CERRADA: ^"+_select.value+"^ ";
				insertAtCursor(textArea, _stringTemporal);

				resetVisualTitulos();
				setVisualTitulo("titulo_operadores", "green");
				setVisualTitulo("titulo_preguntas", "green");
				setVisualTitulo("titulo_palabras_reservadas", "green");
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

//crea un select con las respuestas disponibles asignando automanticamente su id, nombre
//si _tipodedato es false busca la pregunta padre en el select de preguntas... caso contrario
//busca las respuestas asociadas del select de variables pedidas
function creaSelectRespuestas(_tipodedato){
	if(_tipodedato){return creaSelectRespuestasEstaticas();}
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

	var separadosPadre;
	separadosPadre = _valorpadre.split("-");
	_valorpadre = separadosPadre[0];

	for(var i = 0; i < array_preguntas.length ;i++){
		if(_tipodedato){
			if(_valorpadre != array_preguntas[i]["tipodato"]){
				continue;
			}
		}else{
			if(_valorpadre != array_preguntas[i]["id"]){
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

			if(_tipoPregunta == 31){
				//solo entero
				_select.value = "0";
				_select.onblur = function(){
						validarEntero("respuestas");
					}
			}
			if(_tipoPregunta == 32){
				//solo Double
				_select.value = "0.0";
				_select.onblur = function(){
						validarDouble("respuestas");
					}
			}
			if(_tipoPregunta == 33){
				//solo fecha
				_select.readOnly = true;
				_select.onclick = function(){
 	          if(self.gfPop)gfPop.fPopCalendar(document.getElementById("respuestas"));
						return false;
					}
			}
			_boton = document.createElement("input");
			_boton.type = "button";
			_boton.value = "agregar";
			_boton.id = "botonAgregar";
			_boton.name = "botonAgregar";
			_boton.onclick = function(){
				var textArea = document.getElementById("codigo");
				if(_select.value == ""){
					return false;
				}
				var _stringTemporal = " ^"+_select.value+"^ ";
				insertAtCursor(textArea, _stringTemporal);

				resetVisualTitulos();
				setVisualTitulo("titulo_operadores", "green");
				setVisualTitulo("titulo_preguntas", "green");
				setVisualTitulo("titulo_palabras_reservadas", "green");
				textArea.focus();
			};
			

		}else{
			_select = document.createElement("select");
			_select.multiple = false;
			var _temp = new Option("Seleccione...", "", true, true);
			_select.options[_select.options.length] = _temp;
			_temp = new Option("No sabe / No responde", "0-No sabe / No responde");
			_select.options[_select.options.length] = _temp;
			for(var j = 0; j < array_preguntas[i]["respuestas"].length ;j++){
				_temp = new Option(array_preguntas[i]["respuestas"][j]["respuesta"], array_preguntas[i]["respuestas"][j]["id"]+"-"+array_preguntas[i]["respuestas"][j]["respuesta"]);
				_select.options[_select.options.length] = _temp;
			}
			_select.onchange = function() {
				var textArea = document.getElementById("codigo");
				if(_select.value == ""){
					return false;
				}
				var _stringTemporal = " RESPUESTA CERRADA: ^"+_select.value+"^ ";
				insertAtCursor(textArea, _stringTemporal);

				resetVisualTitulos();
				setVisualTitulo("titulo_operadores", "green");
				setVisualTitulo("titulo_preguntas", "green");
				setVisualTitulo("titulo_palabras_reservadas", "green");
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

function ingresar_pregunta(_select){
	var textArea = document.getElementById("codigo");

	if(_select.value == ""){
		return false;
	}
	var _stringTemporal = " RESPUESTA DADA A PREGUNTA: ^"+_select.value+"^ ";

	insertAtCursor(textArea, _stringTemporal);
	creaSelectRespuestas(false);

	resetVisualTitulos();
	setVisualTitulo("titulo_operadores", "red");
	textArea.focus();
}

function esTipoDeDato(_miTipo){
	var _select = document.getElementById("tipo_de_dato");
	for(var ii=0;ii<_select.options.length;ii++){
		if(_select.options[ii].value == _miTipo){
			return true;
		}
	}
	return false;
}

function saltarLineaNula(_lineas){
	while(trim(_lineas[_lineaactual],"") == ""){
		_lineaactual++;
	}
}

function ingresar_valor_solicitado(_select){
	var textArea = document.getElementById("codigo");

	if(_select.value == ""){
		return false;
	}
	var _stringTemporal = " VALOR: ^"+_select.options[_select.selectedIndex].text+"^ ";

	insertAtCursor(textArea, _stringTemporal);
	creaSelectRespuestas(true);
	resetVisualTitulos();
	setVisualTitulo("titulo_operadores", "green");
	setVisualTitulo("titulo_tipos_de_datos", "green");
	textArea.focus();
	_select.value = "";
}

function crearSelectValoresSolicitados(){
	var miselect = document.getElementById("valores_solicitados");

	for (var iiii = miselect.length - 1; iiii>=0; iiii--) {
		miselect.remove(iiii);
	}

	var _temp = new Option("Seleccione...", "", true, true);
	miselect.options[0] = _temp;
	miselect.onchange = function(){
		ingresar_valor_solicitado(miselect);
	};

	var textArea = document.getElementById("codigo");
	_lineaactual = 0;
	var _codigo = document.getElementById("codigo").value;
	//divido en lineas
	var _lineas = _codigo.split("\n");
	var mitipodedato;
	while(_lineaactual < _lineas.length){
		if(trim(_lineas[_lineaactual],"") == "PEDIR EN EJECUCION:"){
			_lineaactual++;
			if(trim(_lineas[_lineaactual],"") == "DE TIPO DE DATO:"){
				_lineaactual++;
				mitipodedato = trim(_lineas[_lineaactual],"");
				_lineaactual++;
				if(trim(_lineas[_lineaactual],"") == "USAR ACRONIMO:"){
					_lineaactual++;
					var miTemp = trim(_lineas[_lineaactual],"").split(" ");
					if((trim(_lineas[_lineaactual],"") != "") && (miTemp.length == 1)){
						miselect.options[miselect.options.length] = new Option(trim(_lineas[_lineaactual],""), mitipodedato, false, false);
					}
					_lineaactual++;
				}else{
					_lineaactual++;
				}
			}else{
				_lineaactual++;
			}
		}else{
			_lineaactual++;
		}
	}
	if(miselect.options.length == 1){
		miselect.remove(0);
		miselect.options[miselect.options.length] = new Option("No ha solicitado valores", "", false, false);
		miselect.style.display = 'none';
	}else{
		miselect.style.display = 'inline';
	}
}

//valida linea por línea recursivamente.
//en _linea[0] tendremos la siguiente línea a revisar
function validarSiguiente(_lineas){
	var _aunValido = true;
	if(_lineas.length <= _lineaactual){
		//no hay mas lineas
		return true;
	}
	if(trim(_lineas[_lineaactual],"") == ""){
		_lineaactual++;
		error = "";
		return validarSiguiente(_lineas);
	}else	if(trim(_lineas[_lineaactual],"") == "PEDIR EN EJECUCION:"){
		_lineaactual++;
		error = "";
		if(_declarando){
			if(trim(_lineas[_lineaactual],"") == "DE TIPO DE DATO:"){
				_lineaactual++;
				error = "";
				if((esTipoDeDato(trim(_lineas[_lineaactual],""))) && (trim(_lineas[_lineaactual],"") != "")){
					_lineaactual++;
					error = "";
					if(trim(_lineas[_lineaactual],"") == "USAR ACRONIMO:"){
						_lineaactual++;
						error = "";
						var miTemp = trim(_lineas[_lineaactual],"").split(" ");
						if((trim(_lineas[_lineaactual],"") != "") && (miTemp.length == 1)){
							_lineaactual++;
							error = "";
							if(trim(_lineas[_lineaactual],"") == "MOSTRAR TITULO:"){
								_lineaactual++;
								error = "";
								if((trim(_lineas[_lineaactual],"") != "") && (miTemp.length == 1)){
									_lineaactual++;
									error = "";
									if(trim(_lineas[_lineaactual],"") == "FIN.-"){
										_lineaactual++;
										error = "";
										return validarSiguiente(_lineas);
									}else{
										error = "Sintaxis incorrecta, se esperaba 'FIN.-' en linea"+(_lineaactual + 1);
										return false;
									}
								}else{
									error = "Debe indicar el titulo en linea: "+(_lineaactual + 1);
									return false;
								}
							}else{
								error = "Sintaxis incorrecta, se esperaba 'MOSTRAR TITULO:' en linea"+(_lineaactual + 1);
								return false;
							}
						}else{
							error = "Debe indicar un acronimo sin espacios en blanco en linea: "+(_lineaactual + 1);
							return false;
						}
					}else{
						error = "Sintaxis incorrecta, se esperaba 'USAR ACRONIMO:' en linea: "+(_lineaactual + 1);
						return false;
					}
				}else{
					error = "No se indico correctamente el tipo de dato a usar en linea"+(_lineaactual + 1);
					return false;
				}
			}else{
				error = "Sintaxis incorrecta, se esperaba 'DE TIPO DE DATO:' en linea"+(_lineaactual + 1);
				return false;
			}
		}else{
			error = "No puede pedir datos una vez comenzada la ejecucion del estudio.\nError en linea: "+_lineaactual;
			return false;
		}
	}else	if(trim(_lineas[_lineaactual],"") == "CUANDO SE CUMPLA CON:"){
		_lineaactual++;
		error = "";
		_declarando = false;
		_aunValido = validarSiguiente(_lineas);
		while(_lineaactual < _lineas.length){
			if(trim(_lineas[_lineaactual],"") == "REALIZAR:"){
				break;
			}
			_lineaactual++;
		}
		if(trim(_lineas[_lineaactual],"") == "REALIZAR:"){
			_lineaactual++;
			error = "";
			_aunValido = validarSiguiente(_lineas);
			if(trim(_lineas[_lineaactual],"") == "FIN.."){
				_lineaactual++;
				error = "";
				return validarSiguiente(_lineas);
			}else{
				error = "Sintaxis incorrecta, se esperaba 'FIN..' en linea: "+(_lineaactual + 1);
				return _aunValido;
			}
		}else{
			error = "Sintaxis incorrecta, se esperaba 'REALIZAR:' en linea: "+(_lineaactual + 1);
			return _aunValido;
		}
	}else	if(trim(_lineas[_lineaactual],"") == "REALIZAR CALCULO:"){
		_declarando = false;
		while(_lineaactual < _lineas.length){
			if(trim(_lineas[_lineaactual],"") == "FIN DEL CALCULO.-"){
				break;
			}
			_lineaactual++;
		}
		if(_lineaactual >= _lineas.length){
			error = "Sintaxis incorrecta, se esperaba: 'FIN DEL CALCULO.-'";
			return false;
		}
		if(trim(_lineas[_lineaactual],"") == "FIN DEL CALCULO.-"){
			return true;
		}
		error = "Sintaxis incorrecta, se esperaba: 'FIN DEL CALCULO.-'";
		return false;
	}else	if((trim(_lineas[_lineaactual],"").substr(0, 7)) == "!#----/"){
		_lineaactual++;
		error = "";
		return validarSiguiente(_lineas);
	}else{
		//no entró en ningún caso, error
		error = "Sintaxis incorrecta, se encontro frase no reconocible:\n"+trim(_lineas[_lineaactual],"")+"\nError encontrado en linea: "+(_lineaactual + 1);
		return true;
	}
}

//en cualquiera de los casos validar retorna verdad o falso si se completó o no con éxito.
function validar(){
	valido = true;
	_lineaactual = 0;
	_declarando = true;
	var _codigo = document.getElementById("codigo").value;
	//divido en lineas
	var _lineas = _codigo.split("\n");

	//evaluo
	valido = validarSiguiente(_lineas);
	if(!valido){
		alert(error);
	}
	return valido;
}

function revisarEstructura2(_valor){
	window.open("mostrarinfo.do?accion=mostrarinfodeobjeto&id="+_valor , "mostrarInfo" , "width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");
}

function nuevoNombre(_nombre){
	<% if(estudioACargar != null && estudioACargar.getCargadaDeBD()){ %>
		if(_nombre.value == '<% out.print(estudioACargar.getTitulo()); %>'){
			alert('Debe indicar otro nombre para este estudio');
			_nombre.focus();
			return false;
		}else{
			return true;
		}
	<% }else{ %>
		return true;
	<% } %>
}

function eliminarEsto(){
	<% if(estudioACargar != null && estudioACargar.getCargadaDeBD()){ %>
		if(confirm('Realmente desea eliminar este estudio?')){
			window.location = "generadorestudios?opcionbase=<% out.print(request.getParameter("opcionbase")); %>&accion=delete&objetoatrabajar=<% out.print(objetoatrabajar.getId());%>&idestudio=<% out.print(estudioACargar.getId());%>";
		}
	<% } %>
}

<% if((request.getParameter("opcionbase")!= null) && "eliminar".equals((String)request.getParameter("opcionbase"))){ %>
$(document).ready(function(){
	eliminarEsto();
});
<% } %>

</script>
<script type="text/javascript" charset="utf-8">
	function clear_form_elements(ele) {
		$(ele).find(':input').each(function() {
				switch(this.type) {
						case 'password':
						case 'select-multiple':
						case 'select-one':
						case 'text':
						case 'textarea':
								$(this).val('');
								break;
						case 'checkbox':
						case 'radio':
								this.checked = false;
				}
		});
	}
	$(document).ready(function(){
		$('#cssdropdown li.headlink').hover(
			function() { $('ul', this).css('display', 'block'); },
			function() { $('ul', this).css('display', 'none'); });
			$("#link_estudios").css("color","red");
	});
	function procesarConstante(){
		if($("#inputConstante").val() == ''){
			alert('Debe indicar el valor.');
			return false;
		}else{
			var textArea = document.getElementById("codigo");
			var _constante = $("#inputConstante").val();
			var _stringTemporal = " ^"+_constante+"^ ";
			insertAtCursor(textArea, _stringTemporal);
			textArea.focus();
			return true;
		}
	}
	function procesarComentario(){
		var textArea = document.getElementById("codigo");
		var _comentario = $("#inputComentario").val();
		var _lineas = _comentario.split("\n");
		var _stringTemporal = "";
		for(var i=0;i<_lineas.length;i++){
			_stringTemporal = _stringTemporal+"!#----/ "+_lineas[i]+"\n";
		}
		insertAtCursor(textArea, _stringTemporal);
		textArea.focus();
		return true;
	}
	function revisarEstructura(){
	  var _select = document.getElementById("tipo_de_dato");
		var _valor = _select.value.split("-");
		window.open("mostrarinfo.do?accion=mostrarinfodepregunta&id="+_valor[0] , "mostrarInfo" , "width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");
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
	function procesarPedirValor(){
		if(($("#acronimoPV").val() == '') || ($("#tituloPV").val() == '') || ($("#tipo_de_dato").attr("value") == '')){
			alert('Debe rellenar todos los campos.');
			return false;
		}else{
			var textArea = document.getElementById("codigo");
			var _acronimo = $("#acronimoPV").val();
			var _titulo = $("#tituloPV").val();
			var _dato = $("#tipo_de_dato").attr("value");
			var _stringTemporal = "PEDIR EN EJECUCION:\nDE TIPO DE DATO:\n"+_dato+"\n USAR ACRONIMO:\n"+_acronimo+"\n MOSTRAR TITULO:\n"+_titulo+"\nFIN.-\n\n";
			setCaretPosition(textArea, 0);
			insertAtCursor(textArea, _stringTemporal);
			textArea.focus();
			return true;
		}
	}

$(document).ready(function(){
	$("#codigo").keypress(function (e){
	  if( e.which!=8 && e.which!=0){
	    return false;
	  }
		//almaceno historial
		if(historia_actual < 30){
			historia_actual++;
			array_historial[historia_actual] = new Array();
			array_historial[historia_actual]['codigo'] = Base64.encode($("#codigo").val());
			array_historial[historia_actual]['posicion'] = doGetCaretPosition(document.getElementById("codigo"));
		}else{
			array_historial[30]['codigo'] = Base64.encode($("#codigo").val());
			array_historial[30]['posicion'] = doGetCaretPosition(document.getElementById("codigo"));
		}
	})
});
function undo(){
	if(historia_actual > 0){
		--historia_actual;
		$("#codigo").val(Base64.decode(array_historial[historia_actual]['codigo']));
		setCaretPosition(document.getElementById("codigo"), array_historial[historia_actual]['posicion']);
		crearSelectValoresSolicitados();
	}
}
function redo(){
	if(array_historial.length > (historia_actual + 1)){
		++historia_actual;
		$("#codigo").val(Base64.decode(array_historial[historia_actual]['codigo']));
		setCaretPosition(document.getElementById("codigo"), array_historial[historia_actual]['posicion']);
		crearSelectValoresSolicitados();
	}
}
</script>

<center>
<table cellpadding="0" cellspacing="0" class="tablaprincipal" style="max-width:1150px;min-width:1150px;width:1150px">
	<tr>
		<td valign="middle" align="left" style="border-top: burlywood ridge thick;border-left: burlywood ridge thick;border-width:3px;max-width:320px;width:320px">
			<a href="autenticar.do"><img src="comunes/imagenes/siagcee_logo.png" alt=""></a>
		</td>
	</tr>
	<tr>
		<td valign="top" align="left">
				<table cellpadding="0" cellspacing="0" class="tablasecundariatitulo" style="max-width:1150px;min-width:1150px;width:1150px">
					<tr>
						<td style="text-align: left;">
							<div style="position:absolute; z-index:1000;overflow:hidden;text-align:center;">
								<ul id="cssdropdown" style="padding-left:2px">
									<li class="headlink">
										<a href="#" id='link_preguntas' title="Cree, modifique o elimine preguntas que ser&aacute;n usadas en las estructuras base, censos o encuestas" onmouseover="this.focus();">Preguntas</a>
										<ul style="display: none;">
											<li class="childlink"><a href="adminpreguntas.do?opcionbase=crear"><img src="comunes/imagenes/file-add.png" height="18" title="Crear pregunta">&nbsp;Crear</a></li>
											<li class="childlink"><a href="adminpreguntas.do?opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar pregunta">&nbsp;Modificar</a></li>
											<li class="childlink"><a href="adminpreguntas.do?opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar pregunta">&nbsp;Eliminar</a></li>
											<li class="childlink"><a href="adminpreguntas.do?opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar preguntas existentes que no se pueden modificar">&nbsp;Revisar</a></li>
										</ul>
									</li>
									<li class="headlink">
										<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
											<a href="#" id='link_estructuras' title="Cree, modifique, publique o elimine estructuras que sirven de base para la creaci&oacute;n de censos o encuestas" onmouseover="this.focus();">Estructuras Base</a>
										<% }	%>
										<% if(admin.getTipoUsuario().equals("regular")){ %>
											<a href="#" id='link_estructuras' title="Revise estructuras que sirven de base para la creaci&oacute;n de censos o encuestas" onmouseover="this.focus();">Revisar Estructuras Base</a>
										<% }	%>
										<ul style="display: none;">
											<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="18" title="Armar estructura base">&nbsp;Crear</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar estructura base">&nbsp;Modificar</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=crear"><img src="comunes/imagenes/file-check.png" height="18" title="Generar estructura base previamente armada">&nbsp;Generar</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar estructura base">&nbsp;Eliminar</a></li>
											<% } %>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar estructuras base generadas que no ya no pueden modificarse">&nbsp;Revisar</a></li>
										</ul>
									</li>
									<li class="headlink">
										<a href="#" id='link_instrumentos' title="Cree, modifique, publique o elimine censos o encuestas" onmouseover="this.focus();">Instrumentos Base</a>
										<ul style="display: none;">
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="18" title="Crear censo o encuesta">&nbsp;Crear</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar censo o encuesta">&nbsp;Modificar</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=crear"><img src="comunes/imagenes/file-check.png" height="18" title="Generar censo o encuesta previamente armado">&nbsp;Generar</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar censo o encuesta">&nbsp;Eliminar</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar censo o encuesta generadas que no ya no pueden modificarse">&nbsp;Revisar</a></li>
										</ul>
									</li>
									<li class="headlink">
										<a id='link_generar_instrumentos' href="implementobjeto.do" title="Publique censos o encuestas previamente generados" onmouseover="this.focus();">Publicar Instrumento</a>
									</li>
									<li class="headlink">
										<a id='link_instrumentos_generados' href="#" title="Revise censos o encuestas ya publicados" onmouseover="this.focus();">Instrumentos Publicados</a>
										<ul style="display: none;">
											<li class="childlink"><a href="admininsobj.do?opcionbase=pendientes"><img src="comunes/imagenes/file-pendiente.png" height="18" title="Mostrar s&oacute;lo censos o encuestas pendientes">&nbsp;Pendientes</a></li>
											<li class="childlink"><a href="admininsobj.do?opcionbase=enejecucion"><img src="comunes/imagenes/file-ejecucion.png" height="18" title="Mostrar s&oacute;lo censos o encuestas actualmente en ejecuci&oacute;n">&nbsp;En Ejecuci&oacute;n</a></li>
											<li class="childlink"><a href="admininsobj.do?opcionbase=finalizados"><img src="comunes/imagenes/file-finalizado.png" height="18" title="Mostrar s&oacute;lo censos o encuestas finalizados">&nbsp;Finalizados</a></li>
											<li class="childlink"><a href="admininsobj.do?opcionbase=todos"><img src="comunes/imagenes/file-todos.png" height="18" title="Mostrar censos o encuestas pendientes, actualmente en ejecuci&oacute;n y finalizados">&nbsp;Todos</a></li>
											<li class="childlink"><a href="admininsobj.do?opcionbase=eliminados"><img src="comunes/imagenes/delete.png" height="18" title="Mostrar s&oacute;lo censos o encuestas eliminados">&nbsp;Eliminados</a></li>
										</ul>
									</li>
									<li class="headlink">
										<a id='link_estudios' href="#" title="Cree, modifique y elimine estudios asociados a un censos, encuestas o colecci&oacute;nes de datos" onmouseover="this.focus();">Estudios</a>
										<ul style="display: none;">
											<li class="childlink"><a href="estudios.do?opcionbase=crear"><img src="comunes/imagenes/file-add.png" height="18" title="Crear estudio">&nbsp;Crear</a></li>
											<li class="childlink"><a href="estudios.do?opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar estudio">&nbsp;Modificar</a></li>
											<li class="childlink"><a href="estudios.do?opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar estudio">&nbsp;Eliminar</a></li>
											<li class="childlink"><a href="admininsobj.do?opcionbase=todos&soloaplicar=true"><img src="comunes/imagenes/file-check.png" height="18" title="Aplicar estudio">&nbsp;Aplicar</a></li>
										</ul>
									</li>
									<li class="headlink">
										<a id='link_colecciones_de_datos' href="#" title="Arme, modifique y elimine agrupaci&oacute;n de datos entre censos y/o encuestas" onmouseover="this.focus();">Colecciones De Datos</a>
										<ul style="display: none;">
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="18" title="Crear colecci&oacute;n de datos">&nbsp;Crear</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar colecci&oacute;n de datos">&nbsp;Modificar</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar colecci&oacute;n de datos">&nbsp;Eliminar</a></li>
											<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar colecci&oacute;n de datos">&nbsp;Revisar</a></li>
										</ul>
									</li>
									<li class="headlink">
										<a id='opciones_link' href="#" title="Otras opciones" onmouseover="this.focus();">Opciones</a>
										<ul style="display: none;">
											<li class="childlink">
												<a id='link_msg' href="mensaje.do"><img src="comunes/imagenes/email.png" height="18" title="Env&iacute;e un correo electr&oacute;nico a un conjunto de personas">&nbsp;Mensaje</a>
											</li>
											<li class="childlink">
												<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
													<a id='link_administrar_cuentas' href="cuentas.do"><img src="comunes/imagenes/profile.png" height="18" title="Maneje las cuentas de administradores del sistema">&nbsp;Cuentas</a>
												<% } 	%>
												<% if(admin.getTipoUsuario().equals("regular")){ %>
													<a id='link_administrar_cuentas' href="cuentas.do"><img src="comunes/imagenes/profile.png" height="18" title="Administre sus datos personales">&nbsp;Mi Cuenta</a>
												<% } 	%>
											</li>
											<li class="childlink">
												<a id='link_salir' href="salir.do" onclick="return confirm('Desea realmente abandonar la aplicacion?');"><img src="comunes/imagenes/exit.png" height="18" title="Cierre su sesi&oacute;n y vuelva a la p&aacute;gina inicial">&nbsp;Salir</a>
											</li>
										</ul>
									</li>
								</ul>
							</div>
							<br /><br />
						</td>
					</tr>
				</table>
<table cellpadding="4" cellspacing="4" style="max-width:1150px;min-width:1150px;width:1150px">
	<tr>
		<td style="text-align:left" valign="top" colspan="4">
			<h2><% out.print(request.getParameter("opcionbase")); %> Estudio</h2>
		</td>
	</tr>
	<tr>
		<td width="150" style="max-width:150px;text-align:right;">
			<span id="titulo_palabras_reservadas"><label>Acciones Preestablecidas:</label></span>
		</td>
		<td width="35%" style="text-align:left;">
			<select id="palabras_claves" name="palabras_claves" onchange="ingresar_palabra_clave(this);crearSelectValoresSolicitados();">
				<option value="" selected="selected">Seleccione...</option>
				<option value="PEDIR">Solicitar valor antes de ejecutar</option>
				<option value="COMENTARIO">Ingresar un comentario sobre el codigo resultante</option>
				<option value="CONSTANTE">Indique valor constante a incorporar al estudio</option>
				<option value="IF">Cuando se cumpla condicion - ejecutar - fin</option>
				<option value="OPERAR">Introducir calculo</option>
			</select>
		</td>
		<td width="150" style="max-width:150px;text-align:right;">
			<span id="titulo_variables_solicitadas"><label>Valores Solicitados:</label></span>
		</td>
		<td width="35%" style="text-align:left;">
			<span id="span_valores_solicitados">
				<select id="valores_solicitados" name="valores_solicitados" onchange="ingresar_valor_solicitado(this);">
					<option value="" selected="selected">No ha solicitado valores</option>
				</select>
			</span>
			<img src="comunes/imagenes/refresh.png" alt="Actualizar" title="Actualizar" onclick="crearSelectValoresSolicitados();" height="22"/>
		</td>
	</tr>
	<tr>
		<td width="150" style="max-width:150px;text-align:right;">
			<span id="titulo_operadores"><label>Operadores l&oacute;gicos/Aritm&eacute;ticos:</label></span>
		</td>
		<td width="35%" style="text-align:left;">
			<select id="operadores" name="operadores" onchange="ingresar_operador(this);crearSelectValoresSolicitados();">
				<option value="" selected="selected">Seleccione...</option>
				<optgroup label="Operadores aritm&eacute;ticos binarios">
					<option value="+">Suma: +</option>
					<option value="-">Resta: -</option>
					<option value="x">Multiplicaci&oacute;n: x</option>
					<option value="/">Divisi&oacute;n: /</option>
				</optgroup>
				<optgroup label="Operadores aritm&eacute;ticos unarios">
					<option value="SIN">Seno</option>
					<option value="COS">Coseno</option>
					<option value="TAN">Tangente</option>
<!--					<option value="LOG">Logaritmo base X</option>
					<option value="EXP">Exponencial base X</option>
					<option value="SQR">Raiz X</option>
					<option value="SUM">Sumar valores de una pregunta</option>
					<option value="COUNT">Contar las coincidencias de cada respuesta</option>
					<option value="TOTAL">Contar totales pregunta</option>
					<option value="PROM">Promediar valores de una pregunta</option>  //-->
				</optgroup>
				<optgroup label="Operadores l&oacute;gicos">
					<option value="==">Es igual a</option>
					<option value="<>">Es distinto a</option>
					<option value="Y">Y l&oacute;gico</option>
					<option value="O">O l&oacute;gico</option>
					<option value="NO">Opuesto al valor dado, negaci&oacute;n</option>
					<option value=">=">Mayor o igual que ( >= )</option>
					<option value=">">Mayor estricto que ( > )</option>
					<option value="<=">Menor o igual que ( <= )</option>
					<option value="<">Menor estricto que ( < )</option>
				</optgroup>
			</select>
		</td>
		<td width="150" style="max-width:150px;text-align:right;">
			<span id="titulo_preguntas"><label>Preguntas:</label></span>
		</td>
		<td width="35%" style="text-align:left;">
			<select id="preguntas" name="preguntas" onchange="ingresar_pregunta(this);crearSelectValoresSolicitados();">
				<option value="" selected="selected">Seleccione...</option>
			</select>
			<script type="text/javascript" charset="UTF-8">
				creaSelectPreguntas();
			</script>
		</td>
	</tr>
	<tr>
		<td width="150" style="max-width:150px;text-align:left;" colspan="2">
			<span id="titulo_tipos_de_datos"></span>
			<%
			  if("eliminar".equals((String)request.getParameter("opcionbase"))){
				  %>
						<span id="elimficha"><a style="color:red" href="#" onclick="eliminarEsto();">Eliminar estudio</a></span><p />
					<%
			  }
			%>
			<span id="ficha"><a href="#" onclick="$('#id_encapsulador').show('slow');revisarEstructura2(<% out.print(objetoatrabajar.getId());%>)"><label>Revisar Estructura de: <% out.print(objetoatrabajar.getObjeto());%></label></a></span>
			<%
				if(compilado){
					if(validaCompilacion){
						out.println("<hr /><p /><span style='color:green'>Compilaci&oacute;n realizada con &eacute;xito.</span>");
					}else{
						out.println("<hr /><p /><span style='color:red'>Errores en compilaci&oacute;n:</span>");
						Enumeration _enuErr = misErrores.elements();
						String _errores;
						while(_enuErr.hasMoreElements()){
							_errores = (String)_enuErr.nextElement();
							out.println(_errores);
						}
					}
				}
			%>
		</td>
		<td width="150" style="max-width:150px;text-align:right;">
			<span id="titulo_respuestas"><label>Respuestas:</label></span>
		</td>
		<td width="35%" style="text-align:left;">
			<span id="span_respuestas">&nbsp;</span>
		</td>
	</tr>
</table>
<br />
<form action="generadorestudios" method="post">
	<table cellpadding="4" cellspacing="4" style='max-width:1150px;min-width:1150px;width:1150px'>
		<tr>
			<td>
				<img src="comunes/imagenes/undoicon.png" title="Deshacer" onclick="undo();" alt="Deshacer" height="24"/>&nbsp;&nbsp;<img src="comunes/imagenes/redoicon.png" alt="Rehacer" height="24" title="Rehacer" onclick="redo();"/><br />
				<textarea rows="25" cols="130" id="codigo" style="width:1100px;height:400px" name="codigo"><% try{ if(request.getParameter("codigo")!=null){out.print((String)request.getParameter("codigo"));}else{if(estudioACargar != null){out.print(estudioACargar.getCodigo());}}}catch(Exception ee){ee.printStackTrace();} %></textarea>
			</td>
		</tr>
	</table>
	<input type="hidden" value="<% if(request.getParameter("objetoatrabajar")!=null){out.print((String)request.getParameter("objetoatrabajar"));}else{out.print(objetoatrabajar.getId());}%>" id="objetoatrabajar" name="objetoatrabajar"><br />
	<input type="hidden" value="compilar" id="accion" name="accion">
	<input type="hidden" value="" id="idcodigo" name="idcodigo">
	<table cellpadding="4" cellspacing="4" class='tablageneradorestudio2' style='max-width:1150px;min-width:1150px;width:1150px'>
		<tr>
			<td style="text-align:right">
				<label>T&iacute;tulo del estudio:</label>
			</td>
			<td style="text-align:left">
				<input size="50" type="text" value="<% if(request.getParameter("titulo_estudio")!=null){out.print((String)request.getParameter("titulo_estudio"));}else{if(estudioACargar != null){out.print(estudioACargar.getTitulo());}} %>" id="titulo_estudio" name="titulo_estudio">
			</td>
		</tr>
		<tr>
			<td style="text-align:right">
				<label>Descripci&oacute;n del estudio:</label>
			</td>
			<td style="text-align:left">
				<textarea rows="5" cols="80" id="descripcion_estudio" name="descripcion_estudio"><% if(request.getParameter("descripcion_estudio")!=null){out.print((String)request.getParameter("descripcion_estudio"));}else{if(estudioACargar != null){out.print(estudioACargar.getDescripcion());}} %></textarea>
			</td>
		</tr>
		<tr>
			<td style="text-align:right">
				<input type="hidden" value="<% out.print(request.getParameter("opcionbase")); %>" id="opcionbase" name="opcionbase">
				<input type="button" value="Validar" onclick="if(validar()){alert('En una primera etapa de validacion sintactica el codigo se muestra correcto.\nValidaciones semanticas posteriores se realizan una vez analizado el codigo definitivo.')};">
			</td>
			<td style="text-align:left">
				<input type="submit" value="Procesar" onclick="var _esVacio = document.getElementById('titulo_estudio');if(_esVacio.value != ''){return nuevoNombre(document.getElementById('titulo_estudio'))&&validar();}else{alert('Debe indicar un titulo para el estudio.');return false;}">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
		resetVisualTitulos();
		setVisualTitulo("titulo_preguntas", "green");
		setVisualTitulo("titulo_operadores", "green");
		setVisualTitulo("titulo_palabras_reservadas", "green");
		crearSelectValoresSolicitados();
	});
</script>

<div style='display:none;' name="formularioPedirValor" id="formularioPedirValor" class="simplemodal-container">
	<img src="comunes/imagenes/x.png" class="modalCloseImg" id="linkClose" name="linkClose" onclick="$.modal.close();" />
	<h3>Rellene los campos que se muestran:</h3>
	<form action="#" method="GET" onsubmit="return false;">
		<label>Texto que contendr&aacute; la pregunta a efectuar:</label><br />
		<input type="text" id="tituloPV" name="tituloPV" size="50"><p />
		<label>Acr&oacute;nimo:</label><br />
		<input type="text" id="acronimoPV" name="acronimoPV" size="15"><p />
		<label>Elemento validaci&oacute;n/Control a utilizar en la pregunta:</label><br />
		<select id="tipo_de_dato" name="tipo_de_dato" onchange="compruebaVacioSelect(this);">
			<option value="" selected="selected">Seleccione...</option>
			<%
				//Enumeration _enulista = _listaTipoDatos.elements();
				Enumeration _enulista = _todas_preguntas_estaticas.elements();
				Pregunta _miPregTemp = null;
				while(_enulista.hasMoreElements()){
					_miPregTemp = (Pregunta)_enulista.nextElement();
					if(_miPregTemp.getTipoPregunta() == 100){continue;}
					out.print("<option value='"+_miPregTemp.getId()+"-"+_miPregTemp.getPregunta()+"'>"+_miPregTemp.getPregunta()+"</option>");
				}
			%>
		</select><a id="linkPlantillaPregunta" href="#" onclick="revisarEstructura();" style="display:none;">Informaci&oacute;n sobre esta pregunta</a><p />
	</form>
	<input type="button" value="Cancelar" onclick="clear_form_elements($('#formularioPedirValor'));$.modal.close();"><input type="button" value="Agregar" onclick="if(procesarPedirValor()){crearSelectValoresSolicitados();creaSelectRespuestas(true);$.modal.close();clear_form_elements($('#formularioPedirValor'));}">
</div>

<div style='display:none;' name="formularioComentario" id="formularioComentario" class="simplemodal-container">
	<img src="comunes/imagenes/x.png" class="modalCloseImg" id="linkClose1" name="linkClose1" onclick="$.modal.close();" />
	<h3>Indique el comentario que desea agregar:</h3>
	<form action="#" method="GET" onsubmit="return false;">
	<label>Comentario:</label><br />
	<textarea id="inputComentario" name="inputComentario" cols="40" rows="10"></textarea><p />
	</form>
	<input type="button" value="Cancelar" onclick="clear_form_elements($('#formularioComentario'));$.modal.close();"><input type="button" value="Agregar" onclick="if(procesarComentario()){$.modal.close();clear_form_elements($('#formularioComentario'));}">
</div>

<div style='display:none;' name="formularioConstante" id="formularioConstante" class="simplemodal-container">
	<img src="comunes/imagenes/x.png" class="modalCloseImg" id="linkClose2" name="linkClose2" onclick="$.modal.close();" />
	<p />
	<h3>Indique el valor que desea agregar:</h3>
	<form action="#" method="GET" onsubmit="return false;">
	<label>Constante:</label><br />
	<input type="text" id="inputConstante" name="inputConstante" size="20"><p />
	</form>
	<input type="button" value="Cancelar" onclick="clear_form_elements($('#formularioConstante'));$.modal.close();"><input type="button" value="Agregar" onclick="if(procesarConstante()){$.modal.close();clear_form_elements($('#formularioConstante'));}">
</div>
				
<div class="encapsulador" id="id_encapsulador" onclick="$('#id_encapsulador').hide('slow')">
	<div class="fondo">
		<div class="mensaje_alerta">
			<p><img src="comunes/imagenes/loading2.gif" title="Cargando... por favor, espere un momento.">&nbsp;Cargando... por favor, espere un momento.</p>
		</div>
	</div>
</div>

<iframe name="gToday:contrast:agenda.js" id="gToday:contrast:agenda.js" src="Contrast/ipopeng.htm" style="visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;" scrolling="no" frameborder="0" height="142" width="132">
</iframe>
<%@include file="adminfooter.jsp" %>