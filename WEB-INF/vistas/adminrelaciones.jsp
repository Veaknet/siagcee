<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Collections" %>
<%@include file="admininicio.jsp" %>

<head>
<script type="text/javascript" charset="UTF-8">
var array_preguntas_relacion = new Array();
var array_preguntas_instrumento = new Array();

function validaForm(opcionesJoin, campoInstrumento){
	if(opcionesJoin.value != '5' && opcionesJoin.value != '6' ){
		if(campoInstrumento.value == '-1'){
			return confirm('Es necesario que indique los datos para relacionar los instrumentos.\nEn caso de no hacerlo no se podran identificar a los actores.\n\nDesea continuar de todas formas?');
		}else{
			return true;
		}
	}else{
		return true;
	}
}

function actualizarSelect(_elemOrigen, _elemDestino){
	var z = 0;
	if(_elemOrigen.value == -1){
		for (var iiii = _elemDestino.length - 1; iiii>=0; iiii--) {
			_elemDestino.remove(iiii);
		}
		_elemDestino.options[0] = new Option("No existen datos que encajen con el mismo tipo de dato seleccionado", "-1", true, true);
	}
	for(var i=0;i<array_preguntas_relacion.length;i++){
		if(array_preguntas_relacion[i]['id'] == _elemOrigen.value){
			for (var iiiii = _elemDestino.length - 1; iiiii>=0; iiiii--) {
				_elemDestino.remove(iiiii);
			}
			_elemDestino.options[0] = new Option("Seleccione...", "-1", true, true);
			z++;
			for(var j=0;j<array_preguntas_instrumento.length;j++){
				if(array_preguntas_instrumento[j]['tipopregunta'] == array_preguntas_relacion[i]['tipopregunta']){
					_elemDestino.options[z] = new Option(array_preguntas_instrumento[j]['pregunta'], array_preguntas_instrumento[j]['id'], false, false);
					z++;
				}
			}
			if(z == 1){
				for (var iiiiii = _elemDestino.length - 1; iiiiii>=0; iiiiii--) {
					_elemDestino.remove(iiiiii);
				}
				_elemDestino.options[0] = new Option("No existen datos que encajen con el mismo tipo de dato seleccionado", "-1", true, true);
			}
			return true;
		}
	}
}

function cerrarDiv(){
	var formularioPregunta = document.getElementById("formInsertModif");
	if(formularioPregunta != null){
		formularioPregunta.style.display = "none";
	}
}

function compruebaNoVacio(elem){
	if(elem){
		if(elem.value != ""){
			return true;
		}
		alert('Debe indicar un nombre y un acronimo para la Coleccion.');
		elem.focus();
		elem.style.backgroundColor = 'yellow';
		elem.onkeyup = function(){
			elem.style.backgroundColor = '';
		};
		return false;
	}
	return false;
}

function habilitarInsertDiv(){
	var formulario = document.getElementById("formInsertModif");
	var nombre = document.getElementById("nombre");
	$('#formInsertModif').modal();
	//formulario.style.display = "block";
	nombre.focus();
}

function habilitarUpdateDiv(){
	var formulario = document.getElementById("formInsertModif");
	var nombre = document.getElementById("nombre");
	$('#formInsertModif').modal();
	//formulario.style.display = "block";
	nombre.focus();
}

</script>

<%
Relacion _relacionActual = null;
if(request.getAttribute("objetoseleccionado") != null){
	_relacionActual = (Relacion)request.getAttribute("objetoseleccionado");
}

InstanciaObjeto _instrumentoSeleccionado = null;
if(request.getAttribute("instrumentoseleccionado") != null){
	_instrumentoSeleccionado = (InstanciaObjeto)request.getAttribute("instrumentoseleccionado");
}

if(_relacionActual != null){
	if(!_relacionActual.getPreguntas(true).isEmpty()){
		Enumeration _enuPreg1 = _relacionActual.getPreguntas(true).elements();
		InstanciaPregunta _miPregunta = null;
		%>
		<script type="text/javascript" charset="utf-8">
			<%
			int i=0;
			while(_enuPreg1.hasMoreElements()){
				_miPregunta = (InstanciaPregunta)_enuPreg1.nextElement();
				out.println("array_preguntas_relacion["+i+"] = new Array();");
				out.println("array_preguntas_relacion["+i+"]['id'] = '"+_miPregunta.getId()+"';");
				if(_miPregunta.isCampo_clave_unico()){
					out.println("array_preguntas_relacion["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_miPregunta.getAcronimo(), "'", "\"")+" (Pregunta Clave)';");
				}else{
					out.println("array_preguntas_relacion["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_miPregunta.getAcronimo(), "'", "\"")+"';");
				}
				out.println("array_preguntas_relacion["+i+"]['tipopregunta'] = '"+_miPregunta.getTipoPregunta()+"';");
				i++;
			}
			%>
		</script>
		<%
	}
}

if(_instrumentoSeleccionado != null){
	if(!_instrumentoSeleccionado.getObjetoAsociado().getPreguntas(true).isEmpty()){
		Enumeration _enuPreg1 = _instrumentoSeleccionado.getObjetoAsociado().getPreguntas(true).elements();
		InstanciaPregunta _miPregunta = null;
		%>
		<script type="text/javascript" charset="utf-8">
			<%
			int i=0;
			while(_enuPreg1.hasMoreElements()){
			  _miPregunta = (InstanciaPregunta)_enuPreg1.nextElement();
			  out.println("array_preguntas_instrumento["+i+"] = new Array();");
				out.println("array_preguntas_instrumento["+i+"]['id'] = '"+_miPregunta.getId()+"';");
				if(_miPregunta.isCampo_clave_unico()){
					out.println("array_preguntas_instrumento["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_miPregunta.getAcronimo(), "'", "\"")+" (Pregunta Clave)';");
				}else{
					out.println("array_preguntas_instrumento["+i+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_miPregunta.getAcronimo(), "'", "\"")+"';");
				}
				out.println("array_preguntas_instrumento["+i+"]['tipopregunta'] = '"+_miPregunta.getTipoPregunta()+"';");
				i++;
			}
			%>
		</script>
		<%
	}
}
%>
</head>

<%@include file="adminheader.jsp" %>

<%

boolean showForm = false;

Vector _instrumentos = new Vector();
if(request.getAttribute("instrumentos") != null){
	_instrumentos = (Vector)request.getAttribute("instrumentos");
	Collections.sort(_instrumentos, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.OBJETO));
	Collections.sort(_instrumentos, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.CLASS));
}

String _tipoinstrumento = "relacion";

String _opcionBase = "armar";
if(request.getParameter("opcionbase")!=null){
	_opcionBase = request.getParameter("opcionbase");
}

if(request.getAttribute("mensaje") != null) {
	out.println((String)request.getAttribute("mensaje") + "<br />");
}

%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_colecciones_de_datos").css("color","red");
	});
</script>
<%
if(_instrumentoSeleccionado == null){
	if(_instrumentos.isEmpty()){
		%>
    No existen Instrumentos ni colecciones para poder trabajar.<p />
		<%
	}else{
		%>
		<table cellspacing="4" cellpadding="4" class='tablasecundaria'>
			<tr>
				<td valign="top" align="left" style="text-align:left">
					<h2>Selecci&oacute;n de Instrumento a Coleccionar</h2>
					<label>Indique el instrumento de donde se importar&aacute;n los datos para la colecci&oacute;n</label><br />
					<form action="adminrelaciones.do" method="post" name="seleccionform" id="seleccionform">
						<input type="hidden" value="seleccionar" id="accion" name="accion">
						<input type="hidden" value="<% out.print(_relacionActual.getId()); %>" id="objetoseleccionado" name="objetoseleccionado">
						<input type="hidden" value="relacion" id="tipoinstrumento" name="tipoinstrumento">
						<input type="hidden" value="<% out.print(_opcionBase);%>" id="opcionbase" name="opcionbase">
						<select id='instrumentoseleccionado' name="instrumentoseleccionado" onchange="submit();" multiple="multiple" size="<% out.print(_instrumentos.size() + 4); %>">
							<%
								//cargo todos las estructuras e instancias
								Enumeration _enu = _instrumentos.elements();
								InstanciaObjeto _miIns;
								String miClase = "", miClasePrint = "";
								while(_enu.hasMoreElements()){
									_miIns = (InstanciaObjeto)_enu.nextElement();
									//solo se hace lo siguiente para mostrarlos por grupos: censos, encuestas y relaciones
									if(!miClase.equals(_miIns.getObjetoAsociado().getClass().toString())){
										if(!miClase.equals("")){
											out.print("</optgroup>");
										}

										miClase = _miIns.getObjetoAsociado().getClass().toString();

										if(miClase.contains("Censo")){
											miClasePrint = "Censos";
										}
										if(miClase.contains("Encuesta")){
											miClasePrint = "Encuestas";
										}
										if(miClase.contains("Relacion")){
											miClasePrint = "Colecciones";
										}
										%>
										<optgroup label="<% out.print(miClasePrint); %>">
										<%
									}
									out.println("<option value='"+_miIns.getId()+"'>"+_miIns.getObjeto()+"</option>");
								}
							%>
						</select>
					</form>
				</td>
			</tr>
		</table>
		<%
	}
}else{
	//ahora si le mostramos las preguntas disponibles del instrumento
	%>
	<table cellspacing="4" cellpadding="4" class='tablasecundaria'>
		<tr>
			<td valign="top" align="left" style="text-align:left">
				<h2>Coleccionar Datos</h2>
				<%
				Vector _preguntasAMostrar = _instrumentoSeleccionado.getObjetoAsociado().getPreguntas(true);
				Enumeration _enuPreguntas = _preguntasAMostrar.elements();
				if(_enuPreguntas.hasMoreElements()){
					Collections.sort(_preguntasAMostrar, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
					InstanciaPregunta _preguntaActual = null;
					%>
					<form action="adminrelaciones.do" method="post" name="seleccionform" id="seleccionform">
						<input type="hidden" value="relacionarobjetos" id="accion" name="accion">
						<input type="hidden" value="<% out.print(_relacionActual.getId()); %>" id="objetoseleccionado" name="objetoseleccionado">
						<input type="hidden" value="<% out.print(_instrumentoSeleccionado.getId()); %>" id="instrumentoseleccionado" name="instrumentoseleccionado">
						<input type="hidden" value="relacion" id="tipoinstrumento" name="tipoinstrumento">
						<input type="hidden" value="<% out.print(_opcionBase);%>" id="opcionbase" name="opcionbase">
						<label><strong>Seleccione los datos que desea coleccionar:</strong></label><br />
						<select id="preguntasseleccionadas" name="preguntasseleccionadas" multiple size="10">
							<%
							do{
								_preguntaActual = (InstanciaPregunta)_enuPreguntas.nextElement();
								if(_preguntaActual.isCampo_clave_unico()){
									out.println("<option value='"+_preguntaActual.getId()+"'>"+_preguntaActual.getAcronimo()+" (Pregunta Clave)</option>");
								}else{
									out.println("<option value='"+_preguntaActual.getId()+"'>"+_preguntaActual.getAcronimo()+"</option>");
								}
							}while(_enuPreguntas.hasMoreElements());
							%>
						</select><p /><br />
						<% if(!_relacionActual.getPreguntas(false).isEmpty()){ %>
							<label><strong>Indique el dato por el cual se relacionar&aacute;n los instrumentos:</strong></label><br />
							<label>Instrumento 1: </label><span title="<% out.print(UtilidadesVarias.reemplazarCaracteres(_relacionActual.getObjeto(), "\"", "'")); %>" style="display:inline-block;overflow:hidden;min-width:300px;max-width:300px;width:300px;"><% out.print(_relacionActual.getObjeto()); %></span>
								<select id='pregunta_join_relacion' name="pregunta_join_relacion" onchange="actualizarSelect(this, document.getElementById('pregunta_join_instrumento'));">
									<option selected="selected" value="-1">Seleccione...</option>
									<%
									_preguntasAMostrar = _relacionActual.getPreguntas(true);
									_enuPreguntas = _preguntasAMostrar.elements();
									Collections.sort(_preguntasAMostrar, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA));
									do{
										_preguntaActual = (InstanciaPregunta)_enuPreguntas.nextElement();
										out.println("<option value='"+_preguntaActual.getId()+"'>"+_preguntaActual.getAcronimo()+"</option>");
									}while(_enuPreguntas.hasMoreElements());
									%>
								</select>
							<br /><label>Instrumento 2: </label><span title="<% out.print(UtilidadesVarias.reemplazarCaracteres(_instrumentoSeleccionado.getObjeto(), "\"", "'")); %>" style="display:inline-block;overflow:hidden;min-width:300px;max-width:300px;width:300px;"><% out.print(_instrumentoSeleccionado.getObjeto()); %></span>
								<select id='pregunta_join_instrumento' name="pregunta_join_instrumento">
									<option selected="selected" value="-1">Seleccione el dato del instrumento 1 primero...</option>
								</select><br /><p /><br />
						<% } %>
						<label><strong>Indique como desea coleccionar los datos de ambos instrumentos:</strong></label><br />
						<select id="forma_de_relacion_de_datos" name="forma_de_relacion_de_datos">
							<% if(_relacionActual.getPreguntas(false).isEmpty()){ %>
								<option value='1'>Todos los datos seleccionados del instrumento pasar&aacute;n a la colecci&oacute;n.</option>
								<option value='5'>Seg&uacute;n criterios personalizados de b&uacute;squeda.</option>
							<% }else{ %>
								<option value='1'>Todos los datos seleccionados producto de la uni&oacute;n de ambos instrumentos.</option>
								<option value='2'>Todos los datos del instrumento 1 m&aacute;s los datos seleccionados del instrumento 2 que coincidan en el primero.</option>
								<option value='3'>Todos los datos seleccionados del instrumento 2 m&aacute;s los datos del instrumento 1 que coincidan en el primero.</option>
								<option value='4'>Todos los datos seleccionados producto de la intersecci&oacute;n de ambos instrumentos.</option>
								<option value='7'>Todos los datos seleccionados producto del complemento de la intersecci&oacute;n de ambos instrumentos (O Exclusivo).</option>
								<option value='5'>Seg&uacute;n criterios personalizados de b&uacute;squeda.</option>
								<option value="6">Combinando ambos instrumentos, indicando las equivalencias entre los datos seleccionados.</option>
							<% } %>
						</select>&nbsp;<img src="comunes/imagenes/info-about.png" height="24px" title="Haga clic aqu&iacute; para obtener ayuda sobre este tema" onclick="window.open('ayuda.do?tema=administracolecciones#'+$('#forma_de_relacion_de_datos').val(),'ventana');"/><p />
						<% if(!_relacionActual.getPreguntas(false).isEmpty()){ %>
							<input type="submit" value="Procesar" onclick="return validaForm(document.getElementById('forma_de_relacion_de_datos'), document.getElementById('pregunta_join_instrumento'));">
						<% }else{ %>
							<input type="submit" value="Procesar">
						<% } %>
					</form>
					<%
				}else{
					%>El instrumento seleccionado no posee preguntas.<%
				}
				%>
			</td>
		</tr>
	</table>
	<%
}
%>
<%@include file="adminfooter.jsp" %>