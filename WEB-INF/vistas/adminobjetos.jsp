<%@page import="com.siagcee.logic.Objeto" %>
<%@ page import="com.siagcee.logic.OrdenadorObjetos" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@include file="admininicio.jsp" %>

<head>
<script type="text/javascript">
function cerrarDiv(){
	var formularioPregunta = document.getElementById("formularioPregunta");
	if(formularioPregunta != null){
		formularioPregunta.style.display = "none";
		var subtitulo = document.getElementById("subtitulo");
		if(subtitulo){
			subtitulo.innerHTML = "";
		}
	}
}

function compruebaNoVacio(elem){
	if(elem){
		if(elem.value != ""){
			return true;
		}
		alert('No ha completado todos los campo requeridos.');
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
	var formulario = document.getElementById("formularioPregunta");
	var accion = document.getElementById("accion");
	var boton = document.getElementById("boton2");
	var valor = document.getElementById("valor");
	var tipopregunta = document.getElementById("tipoobjeto");
	var subtitulo = document.getElementById("subtitulo");
	var divpre = document.getElementById("divpre");
	subtitulo.innerHTML = "Crear Estructura";
	if(divpre != null){
		divpre.style.display = "none";
	}
	accion.value = "insertar";
	boton.value = "Crear Estructura";
	valor.value = "";
	tipopregunta.value = 1;
	$('#boton2').attr('disabled','disabled');
	$("#formularioPregunta").modal();
//	formulario.style.display = "block";
	valor.focus();

}
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

Vector _objetos = new Vector();
if(request.getAttribute("listadoObjetos") != null){
	_objetos = (Vector)request.getAttribute("listadoObjetos");
	Collections.sort(_objetos, new OrdenadorObjetos(OrdenadorObjetos.OBJETO));
	Collections.sort(_objetos, new OrdenadorObjetos(OrdenadorObjetos.CLASS));
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
if(_tipoinstrumento.equals("estructura")){
	_titulo = _titulo+"Estructura Base";
	_elemento = "Estructura";
}else	if(_tipoinstrumento.equals("relacion")){
	_titulo = _titulo+"Colecci&oacute;n de Datos";
	_elemento = "Colecci&oacute;n de Datos";
}else{
	_titulo = _titulo+"Instrumento";
	_elemento = "Instrumento";
}

sesion.setAttribute("dirRetorno", "");
if(!_mensaje.equals("")){
	out.println(_mensaje);
}
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
	function abrirInfo(){
		var _id = $("#objetoseleccionado").attr("value");
		if(_id != ''){
			$('#id_encapsulador').show('slow');
		<% if(_opcionBase.equals("eliminar")){ %>
			var ventana = window.open('mostrarinfo.do?opcionbase=<% out.print(_opcionBase); %>&tipoinstrumento=<% out.print(_tipoinstrumento); %>&accion=mostrarinfodeobjeto&perohaz=eliminar&id='+_id,'mostrarInfo','width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO');
			ventana.focus();
		<% }else{ %>
			var ventana = window.open('mostrarinfo.do?opcionbase=<% out.print(_opcionBase); %>&tipoinstrumento=<% out.print(_tipoinstrumento); %>&accion=mostrarinfodeobjeto&id='+_id,'mostrarInfo','width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO');
			ventana.focus();
		<% } %>
		}
		$("#objetoseleccionado").attr("value","");
	}
</script>

<table cellspacing="2" cellpadding="2" class="tablasecundaria">
	<tr>
		<td valign="top">
			<h2><% out.print(_titulo);%></h2>
			<%
			if(_opcionBase.equals("modificar") || _opcionBase.equals("eliminar") || _opcionBase.equals("revisar") || _opcionBase.equals("crear")){
				if((_objetos != null) && (!_objetos.isEmpty())){
					Enumeration objetosDisponibles = _objetos.elements();
					Objeto miObjeto = null;
					Vector _nuevoVector = new Vector();
					while(objetosDisponibles.hasMoreElements()){
						miObjeto	= (Objeto)objetosDisponibles.nextElement();
						/*if((miObjeto.getPublico() && _opcionBase.equals("modificar")) && (!_tipoinstrumento.equals("relacion"))){
							continue;
						}else if(miObjeto.getPublico() && _opcionBase.equals("crear")){
							continue;
						//}else if(!miObjeto.getPublico() && _opcionBase.equals("revisar")){
						//	continue;
						}else{*/
							if(_tipoinstrumento.equals("estructura")){
								if(miObjeto.getClass().toString().contains("EstructuraBase")){_nuevoVector.add(miObjeto);}
							}
							if(_tipoinstrumento.equals("relacion")){
								if(miObjeto.getClass().toString().contains("Relacion")){_nuevoVector.add(miObjeto);}
							}
							if((!_tipoinstrumento.equals("estructura")) && (!_tipoinstrumento.equals("relacion"))){
								if(!miObjeto.getClass().toString().contains("EstructuraBase") && !miObjeto.getClass().toString().contains("Relacion")){_nuevoVector.add(miObjeto);}
							}
						//}
					}
					if(!_nuevoVector.isEmpty()){
					%>
						<form name="miformulario" action="adminobjetos.do" method="post">
							<input type="hidden" name="accion" value="seleccionar">
							<input type="hidden" name="tipoinstrumento" value="<% out.print(_tipoinstrumento);%>">
							<input type="hidden" name="opcionbase" value="<% out.print(_opcionBase);%>">
							<label>Seleccione <% out.print(_elemento);%>:<br /></label>
							<% if(_opcionBase.equals("eliminar") || _opcionBase.equals("revisar")){ %>
								<select id="objetoseleccionado" name="objetoseleccionado" onchange="abrirInfo();return true;" multiple="multiple" size="<% out.print((_nuevoVector.size()) + 2); %>">
							<% }else{ %>
								<select id="objetoseleccionado" name="objetoseleccionado" onchange="document.miformulario.submit()" multiple="multiple" size="<% out.print(_nuevoVector.size() + 2); %>">
							<% } %>
								<%
								String miClase = "";
								String miClasePrint = "";
								objetosDisponibles = _nuevoVector.elements();
								while(objetosDisponibles.hasMoreElements()){
									miObjeto	= (Objeto)objetosDisponibles.nextElement();

									//solo se hace lo siguiente para mostrarlos por grupos: censos, encuestas y relaciones
									if(!miClase.equals(miObjeto.getClass().toString())){
										if(!miClase.equals("")){
											out.print("</optgroup>");
										}

										miClase = miObjeto.getClass().toString();

										if(miClase.contains("Censo")){
											miClasePrint = "Censos";
										}
										if(miClase.contains("Encuesta")){
											miClasePrint = "Encuestas";
										}
										if(miClase.contains("Relacion")){
											miClasePrint = "Colecciones de Datos";
										}
										if(miClase.contains("EstructuraBase")){
											miClasePrint = "Estructura Base";
										}
										%>
										<optgroup label="<% out.print(miClasePrint); %>">
										<%
									}
								%>
								<option value="<% out.print(miObjeto.getId()); %>"><% out.print(miObjeto.getObjeto()); %>&nbsp;&nbsp;</option>
								<%
								}
								%>
								</optgroup>
							</select>
						</form>
						<p/>
					<%
					}else{
						%>
			      No existe <% out.print(_elemento);%> disponible para <% out.print(_opcionBase);%>.
						<%
					}
				}else{
					%>
					No existe <% out.print(_elemento);%> disponible para <% out.print(_opcionBase);%>.-
					<%
				}
			}else{
				showForm = true;
			}
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
				<h4><span id="subtitulo" name="subtitulo"></span></h4>
				<form action="adminobjetos.do" method="post">
					<input type="hidden" value="-1" name="objetoseleccionado" id="objetoseleccionado">
					<input type="hidden" name="tipoinstrumento" value="<% out.print(_tipoinstrumento);%>">
					<input type="hidden" name="opcionbase" value="<% out.print(_opcionBase);%>">
					<input type="hidden" value="insertar" name="accion" id="accion">
					<label>Nombre:</label><br /><input type="text" value="" name="valor" id="valor" size="40" onkeyup="$('#boton2').removeAttr('disabled');"><p />
					<%
						if(_tipoinstrumento.equals("relacion")){
							%>
							<input type="hidden" value="1" name="visible" id="visible">
							<input type="hidden" value="3" name="tipoobjeto" id="tipoobjeto">
							<%
						}else{
							if(_tipoinstrumento.equals("estructura")){
								%>
								<input type="hidden" value="0" name="visible" id="visible">
								<input type="hidden" value="0" name="tipoobjeto" id="tipoobjeto">
								<%
							}else{
								%>
								<input type="hidden" value="0" name="visible" id="visible">
								<label>Tipo de Instrumento:</label><br />
								<select name="tipoobjeto" id="tipoobjeto" onchange="$('#boton2').removeAttr('disabled');">
									<option value="1" selected="selected">Censo</option>
									<option value="2">Encuesta</option>
								</select><p />
								<%
							}
						}
					%>
					<%	if(_tipoinstrumento.equals("relacion")){	%>
						<input type="submit" value="Crear <% out.print(_elemento);%>" name="boton2" id="boton2" onclick="return compruebaNoVacio(getElementById('valor'))" disabled="disabled">
					<%	}else{ %>
						<input type="submit" value="Crear <% out.print(_elemento);%>" name="boton2" id="boton2" onclick="return compruebaNoVacio(getElementById('valor'))" disabled="disabled">
					<%	} %>
					<input type="reset" id="boton" name="boton" value="Borrar" onclick="$('#valor').focus();">
				</form>
			</div>
		</td>
	</tr>
</table>

<%@include file="adminfooter.jsp" %>
