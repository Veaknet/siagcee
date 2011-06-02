<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@include file="admininicio.jsp" %>

<script type="text/javascript" charset="UTF-8">
var array_censos = new Array();

function compruebaPoblacion(){
	var _poblacion = document.getElementById("divPoblacion");
	var _poblacionselect = document.getElementById("poblacion");
	if(_poblacion && _poblacionselect){
		if(_poblacion.style.display == "none"){
			return true;
		}else{
			if(_poblacionselect.value != -1){
				return true;
			}else{
				alert("Es necesario que indique la poblacion asociada para generar este instrumento.");
				_poblacionselect.focus();
				return false;
			}
		}
	}else{
		alert("Ha ocurrido un error importante detectando la poblacion asociada para este nuevo instrumento.");
		_poblacionselect.focus();
		return false;
	}
}

function revisarSiCenso(elem){
	if(elem){
		if(elem.value != -1){
			var ocultar = false;
			for(var i=0;i < array_censos.length;i++){
				if(elem.value == array_censos[i]){
					$("#divPoblacion").hide();
					ocultar = true;
					break;
				}
			}
			if(!ocultar){
				$("#divPoblacion").show();
			}
		}else{
			$("#divPoblacion").hide();
		}
	}else{
		$("#divPoblacion").hide();
	}
}

function revisarEstructura(){
  var _select = document.getElementById("objeto_asociado");
	window.open("mostrarinfo.do?accion=mostrarinfodeobjeto&id="+_select.value , "mostrarInfo" , "width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");
}

function revisarInfoEstructuras(elem){
	var _info = document.getElementById("infoEstructuras");
	if(elem){
		if(elem.value != -1){
			_info.style.display = 'inline';
		}else{
			_info.style.display = 'none';
		}
	}else{
		_info.style.display = 'inline';
	}
}

function cerrarDiv(){
	var formularioPregunta = document.getElementById("miDiv");
	if(formularioPregunta != null){
		formularioPregunta.style.display = "none";
	}
}

function compruebaSeleccionado(elem){
	if(elem){
		if(elem.value != -1){
			return true;
		}
		alert('Debe indicar una estructura para este instrumento.');
		elem.focus();
		return false;
	}else{
		return false;
	}
}

function compruebaNoVacioNoLimitativo(elem){
	if(elem){
		if(elem.value != "" || elem.disabled){
			return true;
		}
		return confirm("No ha indicado ningun valor para la fecha de "+elem.id+". Se tomara el dia de hoy por defecto. Esta seguro de querer hacer esto?");
	}
	return false;
}

function compruebaNoVacio(elem){
	if(elem){
		if(elem.value != ""){
			return true;
		}
		alert('Debe indicar un titulo.');
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
	var formularioPregunta = document.getElementById("miDiv");
	var subtitulo = document.getElementById("subtitulo");
	var miid = document.getElementById("id");
	var objeto = document.getElementById("objeto");
	var tipoacceso = document.getElementById("tipo_acceso");
	var tipoinvitacion = document.getElementById("span_tipo_invitacion");
	var objetoasociado = document.getElementById("objeto_asociado");
	var inicio = document.getElementById("inicio");
	var cierre = document.getElementById("cierre");
	var boton = document.getElementById("miboton");
	var accion = document.getElementById("accion");
	var _tipoinvitacion = document.getElementById("tipo_invitacion");
	var _info = document.getElementById("infoEstructuras");

	_info.style.display = 'none';
	tipoinvitacion.style.display = "inline";
	accion.value = "insertar";
	subtitulo.innerHTML = "Generar Instrumento";
	miid.value = -1;
	objeto.value = "";
	inicio.value = "";
	cierre.value = "";
	tipoacceso.value = 0;
	objetoasociado.value = "-1";
	objetoasociado.disabled = false;
	boton.value = "Generar";
	if(_tipoinvitacion.options.length == 2){
		var _temp = new Option("Sin invitacion", "3");
		_tipoinvitacion.options[_tipoinvitacion.options.length] = _temp;
	}
	if(formularioPregunta != null){
		formularioPregunta.style.display = "block";
	}
	objeto.focus();
}
function habilitarUpdateDiv(_id, _objeto, _inicio, _cierre, _tipoacceso, _objetoasociado, _habilitaCambioDeEstructura){
	var formularioPregunta = document.getElementById("miDiv");
	//var subtitulo = document.getElementById("subtitulo");
	var miid = document.getElementById("id");
	var objeto = document.getElementById("objeto");
	var tipoacceso = document.getElementById("tipo_acceso");
	var tipoinvitacion = document.getElementById("span_tipo_invitacion");
	var objetoasociado = document.getElementById("objeto_asociado");
	var inicio = document.getElementById("inicio");
	var cierre = document.getElementById("cierre");
	var boton = document.getElementById("miboton");
	var accion = document.getElementById("accion");
	var _info = document.getElementById("infoEstructuras");

	_info.style.display = 'inline';
	if(_habilitaCambioDeEstructura == 1){
		 objetoasociado.disabled = true;
	}else{
		objetoasociado.disabled = false;
	}
	tipoinvitacion.style.display = "none";
	accion.value = "actualizar";
	//subtitulo.innerHTML = "Modificar Instrumento";
	miid.value = _id;
	objeto.value = _objeto;
	var __fecha = _inicio.split("-");
	inicio.value = __fecha[2]+"-"+__fecha[1]+"-"+__fecha[0];
	__fecha = _cierre.split("-");
	cierre.value = __fecha[2]+"-"+__fecha[1]+"-"+__fecha[0];
	tipoacceso.value = _tipoacceso;
	tipoacceso.disabled = true;
	objetoasociado.value = _objetoasociado;
	objetoasociado.disabled = false;
	boton.value = "Guardar Cambios";
	if(formularioPregunta != null){
		formularioPregunta.style.display = "block";
	}
	objeto.focus();
}

function modificarTipoInvitacion(){
	var tipoinvitacion = document.getElementById("span_tipo_invitacion");
	var _tipoinvitacion = document.getElementById("tipo_invitacion");
	var _tipoacceso = document.getElementById("tipo_acceso");
    var _inicio = document.getElementById("inicio");
    var _cierre = document.getElementById("cierre");
	var _temp;
	if(_tipoacceso.value == "0"){
        _inicio.disabled = false;
        _cierre.disabled = false;
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _temp = new Option("A traves de otro instrumento", "1");
        _tipoinvitacion.options[_tipoinvitacion.options.length] = _temp;
		_temp = new Option("Correo electronico manualmente", "2");
		_tipoinvitacion.options[_tipoinvitacion.options.length] = _temp;
		_temp = new Option("No invitar", "3");
		_tipoinvitacion.options[_tipoinvitacion.options.length] = _temp;
    }else if(_tipoacceso.value == "1"){
        _inicio.disabled = false;
        _cierre.disabled = false;
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _temp = new Option("A traves de otro instrumento", "1");
        _tipoinvitacion.options[_tipoinvitacion.options.length] = _temp;
	}else{
        _inicio.disabled = true;
		_inicio.value = '';
        _cierre.disabled = true;
		_cierre.value = '';
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _tipoinvitacion.remove(_tipoinvitacion.options.length - 1);
        _temp = new Option("Cargando desde excel", "4");
        _tipoinvitacion.options[_tipoinvitacion.options.length] = _temp;
	}
}

</script>

<%
boolean showForm = false;

if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}

Vector _objetos = (Vector)request.getAttribute("objetosDisponibles");
Collections.sort(_objetos, new OrdenadorObjetos(OrdenadorObjetos.CLASS));

Vector _instanciados = (Vector)request.getAttribute("objetosInstanciados");
Collections.sort(_instanciados, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.CLASS));

%>
<script type="text/javascript">
var unico_contador = 0;
<%
Enumeration _misInstanciados99 = _objetos.elements();
Objeto miObj99;
while(_misInstanciados99.hasMoreElements()){
	miObj99 = (Objeto)_misInstanciados99.nextElement();
	if(!miObj99.getClass().toString().contains("Censo")){
		continue;
	}
	%>
	array_censos[unico_contador] = <% out.print(miObj99.getId());%>;
	unico_contador++;
	<%
}
%>
</script>

<%@include file="adminheader.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#link_generar_instrumentos").css("color","red");
	});
</script>

<table cellspacing="4" cellpadding="4" class="tablasecundaria">
	<tr>
		<td valign="top" align="left">
			<h2>Publicar Instrumento</h2>
				<form action="implementobjeto.do?soloeste=false&accioninvitar=true" method="post" name="formimplementar" id="formimplementar">
					<input type="hidden" name="id" id="id" value="-1"><p />
					<input type="hidden" name="accion" id="accion" value="insertar"><p />
					<label>t&iacute;tulo:</label><br /><input type="text" name="objeto" id="objeto" value="" size="50"><p />
					<label>Tipo de acceso:</label><br /><select name="tipo_acceso" id="tipo_acceso" onchange="modificarTipoInvitacion();">
						<option value="0" selected="selected">P&uacute;blico</option>
						<option value="1">Restringido</option>
                        <option value="2">Uso interno (carga de datos desde excel)</option>
					</select><p />
					<span name="span_tipo_invitacion" id="span_tipo_invitacion">
						<label>Seleccionar o invitar usuarios:</label><br /><select name="tipo_invitacion" id="tipo_invitacion">
							<option value="1">A Traves De Otro Instrumento</option>
							<option value="2">Cargando Correo Electronico Manualmente</option>
							<option value="3" selected="selected">No Invitar</option>
						</select>
					</span>
					<p />
					<label>Instrumento base a utilizar:</label><br />
					<select id="objeto_asociado" name="objeto_asociado" onchange="revisarInfoEstructuras(this);">
					<option value="-1" selected="selected">Seleccione...</option>
					<%
						try{
							String miClase = "";
							String miClasePrint = "";
							Enumeration _misObjetos = _objetos.elements();
							Objeto miObj;
							while(_misObjetos.hasMoreElements()){
								miObj = (Objeto)_misObjetos.nextElement();
								//if(miObj.getClass().toString().contains("Relacion")){continue;}
								//solo se hace lo siguiente para mostrarlos por grupos: censos, encuestas y relaciones
								if(miObj.getClass().toString().contains("EstructuraBase")){
									continue;
								}
								if(!miClase.equals(miObj.getClass().toString())){
									if(!miClase.equals("")){
										out.print("</optgroup>");
									}

									miClase = miObj.getClass().toString();

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
								out.println("<option value='"+miObj.getId()+"'>"+miObj.getObjeto()+"</option>");
							}
						}catch(Exception e){e.printStackTrace();}
					%>
						</optgroup>
					</select>
					<span style="display:none;" id="infoEstructuras"><a href="#" title="Revisar Instrumento Seleccionado" alt="Revisar Instrumento Seleccionado" onclick="$('#id_encapsulador').show('slow');revisarEstructura();"><img src="comunes/imagenes/search.png" height="18">Revisar Instrumento Seleccionado</a></span><p />
					<label>Fecha de Inicio: </label><input gtbfieldid="1" class="plain" id="inicio"  name="inicio" value="" size="9" readonly="readonly" onkeyup="if(self.gfPop)gfPop.fStartPop(document.formimplementar.inicio,document.formimplementar.cierre);return false;" onclick="if(self.gfPop)gfPop.fStartPop(document.formimplementar.inicio,document.formimplementar.cierre);return false;">&nbsp;&nbsp;&nbsp;
					<label>Fecha de Cierre: </label><input gtbfieldid="2" class="plain" id="cierre" name="cierre" value="" size="9" readonly="readonly" onkeyup="if(self.gfPop)gfPop.fEndPop(document.formimplementar.inicio,document.formimplementar.cierre);return false;" onclick="if(self.gfPop)gfPop.fEndPop(document.formimplementar.inicio,document.formimplementar.cierre);return false;">
					<p />
					<input type="submit" value="Publicar" name="miboton" id="miboton" onclick="return (compruebaNoVacio(getElementById('objeto'))&&compruebaSeleccionado(getElementById('objeto_asociado'))&&compruebaNoVacioNoLimitativo(getElementById('cierre'))&&compruebaNoVacioNoLimitativo(getElementById('inicio')));">
					<input type="reset" value="Borrar">
				</form>
			</div>
		</td>
	</tr>
</table>
<%
	try{
	int _idUnicoObjeto = -1;
	if(request.getParameter("solo") != null){
		_idUnicoObjeto = Integer.valueOf((String)request.getParameter("solo"));
	}
	Enumeration _misInstanciadosxx = _instanciados.elements();
	InstanciaObjeto miObjxx;
	while(_misInstanciadosxx.hasMoreElements()){
		miObjxx = (InstanciaObjeto)_misInstanciadosxx.nextElement();
		if(_idUnicoObjeto != -1){
			if(miObjxx.getId() == _idUnicoObjeto){
				%>
				<script type="text/javascript">
					$(document).ready(function(){
						habilitarUpdateDiv(<% out.print(miObjxx.getId()); %>,'<% out.print(miObjxx.getObjeto()); %>','<% out.print(miObjxx.getFechaInicio()); %>', '<% out.print(miObjxx.getFechaCierre()); %>', <% out.print(miObjxx.getAcceso()); %>, <% out.print(miObjxx.getObjetoAsociado().getId()); %>, <% if(miObjxx.getFechaCierre().compareTo(new Date()) <= -1){out.print(1);}else{out.print(2);} %>);
						$("#objeto_asociado").attr('disabled',true);;
					});
				</script>
				<%
			}
		}
	}
}catch(Exception e){e.printStackTrace();}
%>
<iframe name="gToday:contrast:agenda.js" id="gToday:contrast:agenda.js" src="Contrast/ipopeng.htm" style="visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;" scrolling="no" frameborder="0" height="142" width="132">
</iframe>
<script type="text/javascript">
	$(document).ready(function(){
		$("#objeto").focus();
	});
</script>
<%@include file="adminfooter.jsp" %>

