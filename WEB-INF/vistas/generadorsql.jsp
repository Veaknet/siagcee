<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Collections" %>
<%@include file="admininicio.jsp" %>

<%

boolean showForm = false;

if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}

Vector _todasPreguntas = null;
InstanciaObjeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (InstanciaObjeto)request.getAttribute("objetoatrabajar");
	_todasPreguntas = objetoatrabajar.getObjetoAsociado().getPreguntas();
	Collections.sort(_todasPreguntas, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
}

Vector _objetos = new Vector();
if(request.getAttribute("objetosInstanciados") != null){
	_objetos = (Vector)request.getAttribute("objetosInstanciados");
	Collections.sort(_objetos, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.CLASS));
}

%>
<script type="text/javascript" charset="UTF-8">
function validarEntero(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
  var er = /^[-]*[0-9]+$/;
  if(mielem.value.match(er)){
	  return true;
  }else{
	  alert("Solo pueden ingresarse numeros enteros.");
	  mielem.value = "0";
	  mielem.focus();
		hacerPreview();
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
		hacerPreview();
	  return false;
  }
}

// variables
var conteo_parentesis = 0; // para controlar cuantos parentesis se han incorporado.
var linea_actual = 1; //nos ubica en la linea donde se estan haciendo las operaciones.
var array_preguntas = new Array(); //contiene las preguntas disponibles ordenadas por ordenPregunta
var array_operadores = new Array(); //todos los operadores disponibles para el usuario.
var array_conectores = new Array(); //todos los conectores disponibles para el usuario.
var _noextendido = 1; //indica que los operadores no extendidos (para preguntas tipo 1 o 2) llega hasta el indice indicado

//indicamos todos los operadores.
array_operadores[0] = new Array();       //el operador en 0 siempre será default selected
array_operadores[0]["id"] = "equal";
array_operadores[0]["operador"] = " = ";
array_operadores[1] = new Array();
array_operadores[1]["id"] = "notequal";
array_operadores[1]["operador"] = " <> ";
array_operadores[2] = new Array();
array_operadores[2]["id"] = "parecido";
array_operadores[2]["operador"] = " ~ ";
array_operadores[3] = new Array();
array_operadores[3]["id"] = "lt";
array_operadores[3]["operador"] = " < ";
array_operadores[4] = new Array();
array_operadores[4]["id"] = "gt";
array_operadores[4]["operador"] = " > ";
array_operadores[5] = new Array();
array_operadores[5]["id"] = "let";
array_operadores[5]["operador"] = " <= ";
array_operadores[6] = new Array();
array_operadores[6]["id"] = "get";
array_operadores[6]["operador"] = " >= ";

//indicamos todos los conectores.
array_conectores[0] = new Array();       //el conector en 0 siempre será default selected
array_conectores[0]["id"] = "and";
array_conectores[0]["conector"] = " Y ";
array_conectores[1] = new Array();
array_conectores[1]["id"] = "or";
array_conectores[1]["conector"] = " O ";

<%

if(objetoatrabajar != null){
	Enumeration _misPreguntas = _todasPreguntas.elements();
	InstanciaPregunta preg;
	int i = 0, j = 0;
	while(_misPreguntas.hasMoreElements()){
	  preg = (InstanciaPregunta)_misPreguntas.nextElement();
	  out.println("array_preguntas["+i+"] = new Array();");
    out.println("array_preguntas["+i+"]['id'] = '"+preg.getId()+"';");
    out.println("array_preguntas["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(preg.getAcronimo(), "'", "\"")+"';");
    out.println("array_preguntas["+i+"]['tipopregunta'] = '"+preg.getTipoPregunta()+"';");
    out.println("array_preguntas["+i+"]['ordenpregunta'] = '"+preg.getOrden()+"';");
    out.println("array_preguntas["+i+"]['respuestas'] = new Array();");

    Vector _resp = preg.respuestasPosibles();
		Enumeration _misRespuestas = _resp.elements();
		RespuestasPosibles resp;
		while(_misRespuestas.hasMoreElements()){
			resp = (RespuestasPosibles)_misRespuestas.nextElement();
	    out.println("array_preguntas["+i+"]['respuestas']["+j+"] = new Array();");
	    out.println("array_preguntas["+i+"]['respuestas']["+j+"]['id'] = '"+resp.getId()+"';");
	    out.println("array_preguntas["+i+"]['respuestas']["+j+"]['respuesta'] = '"+UtilidadesVarias.reemplazarCaracteres(resp.getRespuesta(), "'", "\"")+"';");
			j++;
		}
		i++;
	  j = 0;
	}
}
%>

//crea un select con los operadores disponibles asignando automanticamente su id, nombre
//si _extendido muestra todos los operadores, caso contrario solo hasta donde indique __noextendido
function creaSelectOperadores(_id, _nombre, _preguntaPadreId){
	var _tipoPregunta = 30;
	var _valorpadre;
	var _span;
	var _retornar = true;
	var _select;

	try{
		_span = document.getElementById("span_operador_"+_id);
		_retornar = false;
		if(_span == null){
			_span = document.createElement("span");
			_retornar = true;
		}
	}catch(error){
		_span = document.createElement("span");
		_retornar = true;
	}

	try{
		var _padre = document.getElementById(_preguntaPadreId);
		if(_padre == null){
			_valorpadre = 0;
		}else{
			_valorpadre = _padre.value;
		}
	}catch(error){
		_valorpadre = 0;
	}

	for(var i = 0; i < array_preguntas.length ;i++){
		if(_valorpadre != array_preguntas[i]["id"]){
			continue;
		}
		_tipoPregunta = array_preguntas[i]["tipopregunta"];

		try{
			var _select2 = document.getElementById("operador_"+_id);
			if(_select2 != null){
				//lo consigue debe entonces borrar lo que hay antes de agregar
				var _tempPadre = _select2.parentNode;
				_tempPadre.removeChild(_select2);
			}
		}catch(error){
		}

		var _extendido = true;
		_select = document.createElement("select");
		_extendido = (_tipoPregunta >= 30);
		var _temp;
		for(var j = 0; j < array_operadores.length ;j++){
			if(!_extendido){
				if(j > _noextendido){
					break;
				}
			}
			if(j == 2 && (_tipoPregunta == 31 || _tipoPregunta == 32 || _tipoPregunta == 33)){
				continue;
			}
			_temp = new Option(array_operadores[j]["operador"], array_operadores[j]["id"]);
			_select.options[_select.options.length] = _temp;
		}
		_select.name = "operador_"+_nombre;
		_select.id = "operador_"+_id;
		_select.onchange = function(){
				hacerPreview();
			};
		break;
	}

	_span.name = "span_operador_"+_nombre;
	_span.id = "span_operador_"+_id;
	if(_select != null){
		_span.appendChild(_select);
	}
	if(_retornar){
		return _span;
	}else{
		return null;
	}
}

//crea un select con los conectores disponibles asignando automanticamente su id, nombre
function creaSelectConectores(_id, _nombre){
	var _span = document.createElement("span");

	var _select = document.createElement("select");
	var _temp;
	for(var i = 0; i < array_conectores.length ;i++){
		_temp = new Option(array_conectores[i]["conector"], array_conectores[i]["id"]);
		_select.options[_select.options.length] = _temp;
	}

	_select.options[0].defaultSelected = true;
	_select.options[0].selected = true;
	_select.name = "conector_"+_nombre;
	_select.id = "conector_"+_id;
	_span.name = "span_conector_"+_nombre;
	_span.id = "span_conector_"+_id;

	_span.appendChild(_select);
	_select.onchange = function(){
			hacerPreview();
		};
	return _span;
}

//crea un select con las preguntas disponibles asignando automanticamente su id, nombre
function creaSelectPreguntas(_id, _nombre){
	var _span = document.createElement("span");

	var _select = document.createElement("select");
	var _temp;
	_select.options[0] = new Option("Seleccione la pregunta", "-1");
	for(var i = 0; i < array_preguntas.length ;i++){
		_temp = new Option(array_preguntas[i]["pregunta"], array_preguntas[i]["id"]);
		_select.options[_select.options.length] = _temp;
	}
	if(_select.options.length > 0){
		_select.options[0].defaultSelected = true;
		_select.options[0].selected = true;
	}
	_select.name = "pregunta_"+_nombre;
	_select.id = "pregunta_"+_id;
	_span.name = "span_pregunta_"+_nombre;
	_span.id = "span_pregunta_"+_id;
	_select.onchange = function(){
			creaSelectRespuestas(_id, _nombre, "pregunta_"+_id);
			creaSelectOperadores(_id, _nombre, "pregunta_"+_id);
			var _span_ = document.getElementById("span_"+(_id));
			if(_id == linea_actual){
				_span_.style.display = "inline";
			}
			hacerPreview();
		};
	_span.appendChild(_select);
	return _span;
}

//crea un select con las respuestas disponibles asignando automanticamente su id, nombre
function creaSelectRespuestas(_id, _nombre, _preguntaPadreId){
	var _tipoPregunta = 30;
	var _valorpadre;
	var _span;
	var _retornar = true;
	var _select;

	try{
		_span = document.getElementById("span_respuesta_"+_id);
		_retornar = false;
		if(_span == null){
			_span = document.createElement("span");
			_retornar = true;
		}
	}catch(error){
		_span = document.createElement("span");
		_retornar = true;
	}

	try{
		var _padre = document.getElementById(_preguntaPadreId);
		if(_padre == null){
			_valorpadre = 0;
		}else{
			_valorpadre = _padre.value;
		}
	}catch(error){
		_valorpadre = 0;
	}

	for(var i = 0; i < array_preguntas.length ;i++){
		if(_valorpadre != array_preguntas[i]["id"]){
			continue;
		}
		_tipoPregunta = array_preguntas[i]["tipopregunta"];


		try{
			var _select2 = document.getElementById("respuesta_"+_id);
			if(_select2 != null){
				//lo consigue debe entonces borrar lo que hay antes de agregar
				var _tempPadre = _select2.parentNode;
				_tempPadre.removeChild(_select2);
			}
		}catch(error){
		}

		if(_tipoPregunta >= 30){
			_select = document.createElement("input");
			_select.type = "text";
			_select.onblur = function(){
				  hacerPreview();
				}
			_select.onmouseover = function(){
				  hacerPreview();
				}

			if(_tipoPregunta == 31){
				//solo entero
				_select.value = "0";
				_select.onblur = function(){
						validarEntero("respuesta_"+_id);
					}
			}
			if(_tipoPregunta == 32){
				//solo Double
				_select.value = "0.0";
				_select.onblur = function(){
						validarDouble("respuesta_"+_id);
					}
			}
			if(_tipoPregunta == 33){
				//solo fecha
				_select.readOnly = true;
				_select.onclick = function(){
 	          if(self.gfPop)gfPop.fPopCalendar(document.getElementById("respuesta_"+_id));
						return false;
					}
			}
		}else{
			_select = document.createElement("select");
			_select.multiple = false;
			//var _temp = new Option("No sabe / No responde", "-1", true, true);
			//_select.options[_select.options.length] = _temp;
			for(var j = 0; j < array_preguntas[i]["respuestas"].length ;j++){
				_temp = new Option(array_preguntas[i]["respuestas"][j]["respuesta"], array_preguntas[i]["respuestas"][j]["id"]);
				_select.options[_select.options.length] = _temp;
			}
		}
		_select.name = "respuesta_"+_nombre;
		_select.id = "respuesta_"+_id;
		_select.onchange = function(){
				hacerPreview();
			};
		break;
	}

	_span.name = "span_respuesta_"+_nombre;
	_span.id = "span_respuesta_"+_id;
	if(_select != null){
		_span.appendChild(_select);
	}
	if(_retornar){
		return _span;
	}else{
		return null;
	}
}

//crea un select con los parentesis asignando automanticamente su id, nombre
function creaSelectParentesisAbierto(_id, _nombre){
	var _span = document.createElement("span");
	var _select = document.createElement("select");
	var _input = document.createElement("input");
	_input.readOnly = true;

	var _temp;
	_temp = new Option("","");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+1 (","+1");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+2 ((","+2");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+3 (((","+3");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+5 (((((","+5");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("sin parentesis", "0");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-1", "-1");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-2","-2");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-3","-3");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-5","-5");
	_select.options[_select.options.length] = _temp;

	_select.options[5].defaultSelected = true;
	_select.options[5].selected = true;
	_select.name = "parentesisabierto_"+_nombre;
	_select.id = "parentesisabierto_"+_id;
	_input.name = "parentesisabiertoinput_"+_nombre;
	_input.id = "parentesisabiertoinput_"+_id;
	_input.value = "";
	_input.size = "4";
	_input.style.display = "none";

	_span.name = "span_parentesisabierto_"+_nombre;
	_span.id = "span_parentesisabierto_"+_id;
	_span.appendChild(_select);
	_span.appendChild(_input);
	_select.onchange = function(){
			var limit = 0;
			if(_select.value == "-1"){
			 limit = 1;
			}
			if(_select.value == "-2"){
				limit = 2;
			}
			if(_select.value == "-3"){
				limit = 3;
			}
			if(_select.value == "-5"){
				limit = 5;
			}
			if(limit > 0){
				var _total = _input.value.length - limit;
				_input.value = "";
				for(var i = 0;i<_total;i++){
					_input.value = _input.value+"(";
				}
			}
			if(_select.value == "0"){
				_input.value = "";
			}
			if(_select.value == "+1"){
				_input.value = _input.value+"(";
			}
			if(_select.value == "+2"){
				_input.value = _input.value+"((";
			}
			if(_select.value == "+3"){
				_input.value = _input.value+"(((";
			}
			if(_select.value == "+5"){
				_input.value = _input.value+"(((((";
			}
			if(_input.value.length > 0){
				_input.style.display = "inline";
				_select.options[0].defaultSelected = true;
				_select.options[0].selected = true;
			}else{
				_select.options[5].defaultSelected = true;
				_select.options[5].selected = true;
				_input.style.display = "none";
			}
			hacerPreview();
		};
	return _span;
}

//crea un select con los parentesis asignando automanticamente su id, nombre
function creaSelectParentesisCerrado(_id, _nombre){
	var _span = document.createElement("span");
	var _select = document.createElement("select");
	var _input = document.createElement("input");
	_input.readOnly = true;

	var _temp;
	_temp = new Option("","");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+1 )","+1");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+2 ))","+2");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+3 )))","+3");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("+5 )))))","+5");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("sin parentesis", "0");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-1", "-1");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-2","-2");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-3","-3");
	_select.options[_select.options.length] = _temp;
	_temp = new Option("-5","-5");
	_select.options[_select.options.length] = _temp;

	_select.options[5].defaultSelected = true;
	_select.options[5].selected = true;
	_select.name = "parentesiscerrado_"+_nombre;
	_select.id = "parentesiscerrado_"+_id;
	_input.name = "parentesiscerradoinput_"+_nombre;
	_input.id = "parentesiscerradoinput_"+_id;
	_input.value = "";
	_input.size = "4";
	_input.style.display = "none";

	_span.name = "span_parentesiscerrado_"+_nombre;
	_span.id = "span_parentesiscerrado_"+_id;
	_span.appendChild(_input);
	_span.appendChild(_select);
	_select.onchange = function(){
			var limit = 0;
			if(_select.value == "-1"){
			 limit = 1;
			}
			if(_select.value == "-2"){
				limit = 2;
			}
			if(_select.value == "-3"){
				limit = 3;
			}
			if(_select.value == "-5"){
				limit = 5;
			}
			if(limit > 0){
				var _total = _input.value.length - limit;
				_input.value = "";
				for(var i = 0;i<_total;i++){
					_input.value = _input.value+")";
				}
			}
			if(_select.value == "0"){
				_input.value = "";
			}
			if(_select.value == "+1"){
				_input.value = _input.value+")";
			}
			if(_select.value == "+2"){
				_input.value = _input.value+"))";
			}
			if(_select.value == "+3"){
				_input.value = _input.value+")))";
			}
			if(_select.value == "+5"){
				_input.value = _input.value+")))))";
			}
			if(_input.value.length > 0){
				_input.style.display = "inline";
				_select.options[0].defaultSelected = true;
				_select.options[0].selected = true;
			}else{
				_select.options[5].defaultSelected = true;
				_select.options[5].selected = true;
				_input.style.display = "none";
			}
			hacerPreview();
		};
	return _span;
}

//procedimientos
function validoConteoParentesis(){
	return (conteo_parentesis == 0);
}

function restaParentesis(){
	conteo_parentesis--;
}

function sumaParentesis(){
	conteo_parentesis++;
}

function restaLineaActual(){
	linea_actual--;
}

function sumaLineaActual(){
	linea_actual++;
}

function ruedaLineasArriba(linea){
	for(var i=linea;i<linea_actual;i++){
		$("#conector_"+i).val($("#conector_"+(i+1)).val());
		$("#conector_"+i).css("display",$("#conector_"+(i+1)).css("display"));

		$("#parentesisabierto_"+i).val($("#parentesisabierto_"+(i+1)).val());
		$("#parentesisabierto_"+i).css("display",$("#parentesisabierto_"+(i+1)).css("display"));
		
		$("#parentesisabiertoinput_"+i).val($("#parentesisabiertoinput_"+(i+1)).val());
		$("#parentesisabiertoinput_"+i).css("display", $("#parentesisabiertoinput_"+(i+1)).css("display"));

		$("#pregunta_"+i).val($("#pregunta_"+(i+1)).val());
		$("#pregunta_"+i).css("display", $("#pregunta_"+(i+1)).css("display"));
		
		creaSelectOperadores(i, i, "pregunta_"+i);
		creaSelectRespuestas(i, i, "pregunta_"+i);

		$("#operador_"+i).val($("#operador_"+(i+1)).val());
		$("#operador_"+i).css("display", $("#operador_"+(i+1)).css("display"));

		$("#respuesta_"+i).val($("#respuesta_"+(i+1)).val());
		$("#respuesta_"+i).css("display", $("#respuesta_"+(i+1)).css("display"));

		$("#parentesiscerradoinput_"+i).val($("#parentesiscerradoinput_"+(i+1)).val());
		$("#parentesiscerradoinput_"+i).css("display", $("#parentesiscerradoinput_"+(i+1)).css("display"));

		$("#parentesiscerrado_"+i).val($("#parentesiscerrado_"+(i+1)).val());
		$("#parentesiscerrado_"+i).css("display", $("#parentesiscerrado_"+(i+1)).css("display"));
	}
}

function ruedaLineasAbajo(linea){
	for(var i=(linea_actual-1);i>linea;i--){
		$("#parentesisabierto_"+(i+1)).val($("#parentesisabierto_"+i).val());
		$("#parentesisabierto_"+(i+1)).css("display", $("#parentesisabierto_"+i).css("display"));

		$("#parentesisabiertoinput_"+(i+1)).val($("#parentesisabiertoinput_"+i).val());
		$("#parentesisabiertoinput_"+(i+1)).css("display",$("#parentesisabiertoinput_"+i).css("display"));

		$("#pregunta_"+(i+1)).val($("#pregunta_"+i).val());
		$("#pregunta_"+(i+1)).css("display",$("#pregunta_"+i).css("display"));

		creaSelectOperadores((i+1), (i+1), "pregunta_"+(i+1));
		creaSelectRespuestas((i+1), (i+1), "pregunta_"+(i+1));

		$("#operador_"+(i+1)).val($("#operador_"+i).val());
		$("#operador_"+(i+1)).css("display", $("#operador_"+i).css("display"));

		$("#respuesta_"+(i+1)).val($("#respuesta_"+i).val());
		$("#respuesta_"+(i+1)).css("display",$("#respuesta_"+i).css("display"));

		$("#parentesiscerradoinput_"+(i+1)).val($("#parentesiscerradoinput_"+i).val());
		$("#parentesiscerradoinput_"+(i+1)).css("display",$("#parentesiscerradoinput_"+i).css("display"));

		$("#parentesiscerrado_"+(i+1)).val($("#parentesiscerrado_"+i).val());
		$("#parentesiscerrado_"+(i+1)).css("display",$("#parentesiscerrado_"+i).css("display"));
	}
	$("#parentesisabierto_"+(linea+1)).val("");
	$("#parentesisabiertoinput_"+(linea+1)).val("");
	$("#pregunta_"+(linea+1)).val("");
	creaSelectOperadores((linea+1), (linea+1), "pregunta_"+(linea+1));
	creaSelectRespuestas((linea+1), (linea+1), "pregunta_"+(linea+1));
	$("#operador_"+(linea+1)).val("");
	$("#respuesta_"+(linea+1)).val("");
	$("#parentesiscerradoinput_"+(linea+1)).val("");
	$("#parentesiscerrado_"+(linea+1)).val("");
}

function borraUltimaLinea(){
	//busco el div que corresponde
	var midiv = document.getElementById("div_"+linea_actual);
	var _tm = midiv.parentNode;
	_tm.removeChild(midiv);

	//creo el div eliminado, pero vacio
	midiv = document.createElement("div");
	midiv.name = "div_"+(linea_actual);
	midiv.id= "div_"+(linea_actual);
	midiv.style.display = "none";
	_tm.appendChild(midiv);

	var _span = document.getElementById("span_"+(linea_actual-1));
	_span.style.display = "inline";

	restaLineaActual();
	hacerPreview();
}

function creaNuevaLinea(){
	sumaLineaActual();
	//busco el div que corresponde para agregar todos los elementos necesarios
	var midiv = document.getElementById("div_"+linea_actual);

	midiv.appendChild(creaSelectConectores(linea_actual, linea_actual));
	midiv.appendChild(creaSelectParentesisAbierto(linea_actual, linea_actual));
	midiv.appendChild(creaSelectPreguntas(linea_actual, linea_actual));
	var _operador = creaSelectOperadores(linea_actual, linea_actual, "pregunta_"+linea_actual);
	if(_operador != null){
		//caso contrario == null => ya existia el span solo estoy actualizando el contenido
		midiv.appendChild(_operador);
	}
	var _respuesta = creaSelectRespuestas(linea_actual, linea_actual, "pregunta_"+linea_actual);
	if(_respuesta != null){
		//caso contrario == null => ya existia el span solo estoy actualizando el contenido
		midiv.appendChild(_respuesta);
	}
	midiv.appendChild(creaSelectParentesisCerrado(linea_actual, linea_actual));

	var _span = document.getElementById("span_"+(linea_actual-1));
	//dejo habilitado los img de add y delete
	//_span.style.display = "none";

	_span = document.createElement("span");
	_span.name = "span_"+linea_actual;
	_span.id = "span_"+linea_actual;

	var _mitemporal = document.createElement("span");
	_mitemporal.innerHTML = "&nbsp;&nbsp;"
	_span.appendChild(_mitemporal);

	var _imagenenlace = document.createElement("img");
	var _lAc = linea_actual;
	_imagenenlace.setAttribute("src","comunes/imagenes/add.png");
	_imagenenlace.setAttribute("alt","Agrega una nueva linea");
	_imagenenlace.setAttribute("title","Agrega una nueva linea");
	_imagenenlace.setAttribute("height","24");
	_imagenenlace.setAttribute("width","24");
	_imagenenlace.onclick = function(){
			creaNuevaLinea();
			ruedaLineasAbajo(_lAc);
		};
	_span.appendChild(_imagenenlace);

	_mitemporal = document.createElement("span");
	_mitemporal.innerHTML = "&nbsp;&nbsp;"
	_span.appendChild(_mitemporal);

	if(linea_actual > 1){
		_imagenenlace = document.createElement("img");
		_imagenenlace.setAttribute("src","comunes/imagenes/delete.png");
		_imagenenlace.setAttribute("alt","Eliminar Esta linea");
		_imagenenlace.setAttribute("title","Elimina Esta linea");
		_imagenenlace.setAttribute("height","24");
		_imagenenlace.setAttribute("width","24");
		_imagenenlace.onclick = function(){
				ruedaLineasArriba(_lAc);
		    borraUltimaLinea();
			};
		_span.appendChild(_imagenenlace);
	}
	_span.style.display = "inline";
	midiv.appendChild(_span);

	//creo el div siguiente que no contendrá nada pero estará listo para cuando se cree una nueva linea
	var _divt = document.createElement("div");
	_divt.name = "div_"+(linea_actual+1);
	_divt.id= "div_"+(linea_actual+1);
	_divt.style.display = "none";
	midiv.appendChild(_divt);

	//finalmente hago el div visible.
	midiv.style.display = "block";
	hacerPreview();
}

function hacerPreview(){
	conteo_parentesis = 0;
	var _output = document.getElementById("preview");
	var _elem;
	var _salida = "";
	var tipoPregunta = 30;
	var _temp;
	var _elempregunta;
	
	//vacio la pantalla.
	_output.innerHTML = "";

	for(var i = 1; i <= linea_actual ; i++){
		if(i > 1){
			_elem = document.getElementById("conector_"+i);
			_salida = _salida+"&nbsp;<span style='color:red;font-weight: bold;'>"+_elem.options[_elem.selectedIndex].text+"</span>"+"<br />";
		}

		_elem = document.getElementById("parentesisabiertoinput_"+i);
		_salida = _salida+"&nbsp;<span style='color:#336600'>"+_elem.value+"</span>";

		for(var mii = 0;mii <_elem.value.length; mii++){
			restaParentesis();
		}

		_elempregunta = document.getElementById("pregunta_"+i);
		for(var j=0;j < array_preguntas.length ; j++){
	 	  if(array_preguntas[j]["id"] == _elempregunta.value){
			   tipoPregunta = array_preguntas[j]["tipopregunta"];
			   break;
		   }
		}

		_salida = _salida+"&nbsp;<span style='color:blue;font-weight: normal;'>"+_elempregunta.options[_elempregunta.selectedIndex].text+"</span>";

		_elem = document.getElementById("operador_"+i);
		if(_elem != null){
			_salida = _salida+"&nbsp;<span style='color:red;font-weight: bold;'>"+_elem.options[_elem.selectedIndex].text+"</span>";
		}

		_elem = document.getElementById("respuesta_"+i);

		_temp = "";
		if(_elem != null){
			if((tipoPregunta == 1) || (tipoPregunta == 2)){
				_salida = _salida+"&nbsp;<span style='color:blue;font-weight: normal;'>"+_elem.options[_elem.selectedIndex].text+"</span>";

			}else{
				_salida = _salida+"&nbsp;<span style='color:blue;font-weight: normal;'>"+_elem.value+"</span>";
			}
		}

		_elem = document.getElementById("parentesiscerradoinput_"+i);
		_salida = _salida+"&nbsp;<span style='color:#336600'>"+_elem.value+"</span>";

		for(var mii = 0;mii <_elem.value.length; mii++){
			sumaParentesis();
		}

		_salida = _salida;
	}
	_output.innerHTML = _salida;
   var _miBotProce = document.getElementById("boton_procesar");
    if(_salida == ""){
        _miBotProce.value="Seleccionar Todos";
    }else{
        _miBotProce.value="Procesar";
    }

}

function ProcesarSinCondiciones(){
	//Se comprobo ahora se espera confirmacion del usuario
	var _confirmacion = confirm('Desea finalizar ahora?. Una vez seleccionados los participantes no podran modificarse. \n Recuerde, al no indicar condiciones, se tomaran todos los datos.');
	if(_confirmacion){
		var _temp = document.getElementById("miaccion");
		_temp.value = "procesar";
		_temp = document.getElementById("total_lineas");
		_temp.value = 0;
	  return true;
	}
	return false;
}

function Procesar(){
	hacerPreview();
	if(!validoConteoParentesis()){
		if(conteo_parentesis < 0){
			alert("Existen parentesis que no se han cerrado. Se espera ')' ");
		}
		if(conteo_parentesis > 0){
			alert("Existen parentesis que no han sido abiertos. Se espera '('");
		}
		return false;
	}
	var anular = 0;
	var _temp;
	var _confirmacion = true;
	for(var i=1;i<= linea_actual;i++){
		_temp = document.getElementById("pregunta_"+i);
		if(_temp != null){
			if(_temp.value == -1){
				if(linea_actual == 1){
					//estoy en la primera linea es probable que no me interesen las condiciones, pregunto
					if(confirm("Desea continuar sin establecer condiciones de busqueda?")){
						anular = 1;
						_confirmacion = true;
					}else{
						//alert("Pregunta no seleccionada en la linea "+i);
						_confirmacion = false;
						return false;
					}
				}else{
					alert("Debe indicar la pregunta en la linea "+i);
					return false;
				}
			}
		}
	}
	//Se comprobo ahora se espera confirmacion del usuario
	//var _confirmacion = confirm('Desea finalizar ahora?. Una vez seleccionados los participantes no podran modificarse.');
	if(_confirmacion){
		_temp = document.getElementById("miaccion");
		_temp.value = "procesar";
		_temp = document.getElementById("total_lineas");
		if(anular == 1){
			_temp.value = 0;
		}else{
			_temp.value = linea_actual;
		}
	}
	return _confirmacion;
}

function procesarFormSQL(){
	var _valido = Procesar();
	if(!_valido){
		return false;
	}
	var _id = $("#hacerpreview").attr("value");
	if(_id == 'verdad'){
		var _ventana = window.open("","ventana");
		$("#formgenerasql").attr("target", "ventana");
		document.formgenerasql.submit();
		_ventana.focus();
	}else{
		$("#formgenerasql").attr("target", "_top");
		document.formgenerasql.submit();
	}
	return _valido;
}
$(document).ready(function(){
	$('#cssdropdown li.headlink').hover(
		function() { $('ul', this).css('display', 'block'); },
		function() { $('ul', this).css('display', 'none'); });
});

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
											<!-- <li class="childlink"><a href="admininsobj.do?opcionbase=eliminados"><img src="comunes/imagenes/delete.png" height="18" title="Mostrar s&oacute;lo censos o encuestas eliminados">&nbsp;Eliminados</a></li> //-->
										</ul>
									</li>
									<li class="headlink">
										<a id='link_estudios' href="#" title="Cree, modifique y elimine estudios asociados a un censos, encuestas o colecci&oacute;nes de datos" onmouseover="this.focus();">Estudios</a>
										<ul style="display: none;">
											<!-- <li class="childlink"><a href="estudios.do?opcionbase=crear"><img src="comunes/imagenes/file-add.png" height="18" title="Crear estudio">&nbsp;Crear</a></li>
											<li class="childlink"><a href="estudios.do?opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar estudio">&nbsp;Modificar</a></li>
											<li class="childlink"><a href="estudios.do?opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar estudio">&nbsp;Eliminar</a></li>  //-->
											<li class="childlink"><a href="admininsobj.do?opcionbase=todos&soloaplicar=true"><img src="comunes/imagenes/file-check.png" height="18" title="Aplicar estudio">&nbsp;Aplicar</a></li>
										</ul>
									</li>
									<li class="headlink">
										<a id='link_colecciones_de_datos' href="#" title="Arme, modifique y elimine agrupaci&oacute;n de datos entre censos y/o encuestas" onmouseover="this.focus();">Colecciones de Datos</a>
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

<%
if(!_objetos.isEmpty()){
	Enumeration _contar = _objetos.elements();
	InstanciaObjeto _inst = null;
	%>
<table cellpadding="4" cellspacing="4" class='tablageneradorestudio2' style="max-width:1150px;min-width:1150px;width:1150px">
	<tr>
		<td>
		<h2>Filtrar participantes en el instrumento</h2>
		<form action="generadorsql.do?invitar=<% out.print(request.getParameter("invitar")); %>&soloeste=<% out.print(request.getParameter("soloeste")); %>&accioninvitar=<% out.print(request.getParameter("accioninvitar")); %>" method="post" id="seleccionaform" name="seleccionaform" onsubmit="var _esteSele = document.getElementById('objetoatrabajar').value;if(_esteSele == ''){return false;}">
		<input type="hidden" value="seleccionar" id="accion" name="accion">
		<label>Instrumento:</label>
		<select id="objetoatrabajar" name="objetoatrabajar" onchange="if(this.options[this.selectedIndex].value == ''){return false;}document.seleccionaform.submit();">
		<option value=""
		<%
			if(objetoatrabajar == null){
				out.print(" selected='selected'");
			}
		%>
		>Seleccione...</option>
		<%
			try{
				InstanciaObjeto _miIns = (InstanciaObjeto) sesion.getAttribute("generadorSql_instanciaObjeto");
				String miClase = "";
				String miClasePrint = "";
				Enumeration _misObjetos = _objetos.elements();
				InstanciaObjeto miObj;
				boolean _soloeste = false;
				if(request.getParameter("soloeste") != null){
					_soloeste = Boolean.parseBoolean((String)request.getParameter("soloeste"));
				}
				while(_misObjetos.hasMoreElements()){
					miObj = (InstanciaObjeto)_misObjetos.nextElement();
					if(_soloeste && objetoatrabajar != null && objetoatrabajar.getId() != miObj.getId()){continue;}
					//solo se hace lo siguiente para mostrarlos por grupos: censos, encuestas y relaciones
					if((miObj.getObjetoAsociado().retornaPreguntaClave() == null) && (_miIns.getAcceso()==1)){continue;}
					if((request.getParameter("exclusivo") != null) && (request.getParameter("exclusivo").contains("si")) && (miObj.getId() != objetoatrabajar.getId())){continue;}
					if(!miClase.equals(miObj.getObjetoAsociado().getClass().toString())){
						if(!miClase.equals("")){
							out.print("</optgroup>");
						}

						miClase = miObj.getObjetoAsociado().getClass().toString();

						if(miClase.contains("Censo")){
							miClasePrint = "Censos";
						}
						if(miClase.contains("Encuesta")){
							miClasePrint = "Encuestas";
						}
						if(miClase.contains("Relacion")){
							miClasePrint = "Relaciones";
						}
						%>
						<optgroup label="<% out.print(miClasePrint); %>">
						<%
					}
					out.print("<option value='"+miObj.getId()+"'");
					if(objetoatrabajar != null && objetoatrabajar.getId() == miObj.getId()){
						out.print(" selected='selected'");
					}
					out.println(">"+miObj.getObjeto()+"</option>");
				}
			}catch(Exception e){e.printStackTrace();}
		%>
			</optgroup>
		</select>
		<input type="submit" value="Seleccionar" style="display:none">
		<input type="submit" value="Borrar Todo" style="visibility:visible;" id="_mi_btnresetear"><p />
	</form>
	<%
}

if(objetoatrabajar != null){
	String _accioninvitar = "true";
	if(request.getParameter("accioninvitar") != null){
		_accioninvitar = (String)request.getParameter("accioninvitar");
	}
%>
<form action="generadorsql.do" method="POST" id="formgenerasql" name="formgenerasql">
	<input type="hidden" value="verdad" id="hacerpreview" name="hacerpreview">
	<input type="hidden" value="<% if(objetoatrabajar.getObjetoAsociado().retornaPreguntaComunicacionEmail() != null){out.print(objetoatrabajar.getObjetoAsociado().retornaPreguntaComunicacionEmail().getId());}else{out.print(-1);} %>" id="email_id_invite" name="email_id_invite">
	<input type="hidden" value="<% out.print(_accioninvitar); %>" id="accioninvitar" name="accioninvitar">
	<input type="hidden" value="" id="email_id_invite_text" name="email_id_invite_text">
	<div id="div_1" name="div_1" style="display:block;">
		<script type="text/javascript">
			var midiv = document.getElementById("div_1");
			midiv.appendChild(creaSelectParentesisAbierto(linea_actual, linea_actual));
			midiv.appendChild(creaSelectPreguntas(linea_actual, linea_actual));
			midiv.appendChild(creaSelectOperadores(linea_actual, linea_actual, "novalido"));
			midiv.appendChild(creaSelectRespuestas(linea_actual, linea_actual, "novalido"));
			midiv.appendChild(creaSelectParentesisCerrado(linea_actual, linea_actual));
		</script>
		<span style="display:inline;" id="span_1" name="span_1"><img src="comunes/imagenes/add.png" height="24" onclick="creaNuevaLinea();ruedaLineasAbajo(1);" title="Agrega nueva linea" alt="Agrega nueva linea"><img src="comunes/imagenes/delete.png" height="24" onclick="if(linea_actual > 1){ruedaLineasArriba(1);borraUltimaLinea();}" title="Eliminar l&iacute;nea" alt="Eliminar l&iacute;nea"></span>
		<div id="div_2" name="div_2" style="display:none;">
		</div>
	</div><p />
	<input type="hidden" name="total_lineas" id="total_lineas" value="0">
	<input type="hidden" name="miaccion" id="miaccion" value="nada">
	<input type="hidden" name="objetoatrabajar" id="objetoatrabajar" value="<% out.print(objetoatrabajar.getId()); %>">
	<input id="boton_procesar" type="submit" value="Seleccionar Todos" onclick="return procesarFormSQL();">
</form>
<p/><br /><strong>Vista Preliminar de la Consulta:</strong>
<div id="preview" name="preview" style="max-width:800px;display:block;border-collapse:collapse;border-color: #CCCCCC;border-style:outset;border-width:thin;text-indent:5px;vertical-align:top;">
&nbsp;</div>
<%
}
%>
</td></tr></table>
<iframe name="gToday:contrast:agenda.js" id="gToday:contrast:agenda.js" src="Contrast/ipopeng.htm" style="visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;" scrolling="no" frameborder="0" height="142" width="132">
</iframe>
<%@include file="adminfooter.jsp" %>