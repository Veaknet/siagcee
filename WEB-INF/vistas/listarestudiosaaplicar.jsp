<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Collections" %>
<%@include file="admininicio.jsp" %>

<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 21/01/2010
 * Hora: 11:42:59 PM
-->

<script type="text/javascript" charset="UTF-8">
function validarEntero(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
	var er = /^[-]*[0-9]+$/;
	if(mielem.value.match(er) && mielem.value > 0){
		return true;
	}else{
		alert("Solo pueden ingresarse numeros enteros mayores a 0.");
		mielem.value = "1";
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

var array_estudios = new Array();

function seleccionardato(_elem){
	var _id = _elem.value;
	var _selec_datos = $("#datovisualizacion");
	var _selec_datos_txt = $("#datovisualizaciontxt");
	var _selec_datos2 = $("#datovisualizacion2");
	var _selec_datos_txt2 = $("#datovisualizaciontxt2");

	if(($("#idestudio").val() != '_default_histo') && ($("#idestudio").val() != '_default_menor_que') && ($("#idestudio").val() != '_default_mayor_que') && ($("#idestudio").val() != '_default_entre')){
		_id = -1;
	}

	_selec_datos.unbind("click");
	_selec_datos2.unbind("click");
	_selec_datos.attr("readonly", false);
	_selec_datos2.attr("readonly", false);

	if(_id == -1){
		_selec_datos.css("display","none");
		_selec_datos_txt.css("display","none");
		_selec_datos2.css("display","none");
		_selec_datos_txt2.css("display","none");
	}else{
		for(var i=0;i<_totalDatosInstr;i++){
			if(_id == _listaDatos[i]["id"]){
				if(($("#idestudio").val() == '_default_histo') && (_listaDatos[i]["tipopregunta"] == 31 || _listaDatos[i]["tipopregunta"] == 32)){
					_selec_datos.val("1");
					_selec_datos.css("display","block");
					_selec_datos_txt.text("Cantidad de Intervalos:");
					_selec_datos_txt.css("display","block");
					_selec_datos2.css("display","none");
					_selec_datos_txt2.css("display","none");
				}else if($("#idestudio").val() == '_default_menor_que' || $("#idestudio").val() == '_default_mayor_que'){
					_selec_datos_txt.text("Valor:");
					_selec_datos.val("");
					_selec_datos.css("display","block");
					_selec_datos_txt.css("display","block");
					_selec_datos2.css("display","none");
					_selec_datos2.val("");
					_selec_datos_txt2.css("display","none");
					if(_listaDatos[i]["tipopregunta"] == 33){
						_selec_datos.click(function(){
							if(self.gfPop)gfPop.fPopCalendar(document.getElementById("datovisualizacion"));
						});
						_selec_datos.attr("readonly", true);
					}
				}else if($("#idestudio").val() == '_default_entre'){
					_selec_datos_txt.text("Mayor a:");
					_selec_datos.val("");
					_selec_datos2.val("");
					_selec_datos.css("display","block");
					_selec_datos_txt.css("display","block");
					_selec_datos2.css("display","block");
					_selec_datos_txt2.css("display","block");
					_selec_datos_txt2.text("Menor a:");
					if(_listaDatos[i]["tipopregunta"] == 33){
						_selec_datos.click(function(){
							if(self.gfPop)gfPop.fPopCalendar(document.getElementById("datovisualizacion"));
						});
						_selec_datos2.click(function(){
							if(self.gfPop)gfPop.fPopCalendar(document.getElementById("datovisualizacion2"));
						});
						_selec_datos.attr("readonly", true);
						_selec_datos2.attr("readonly", true);
					}
				}else{
					_selec_datos.css("display","none");
					_selec_datos_txt.css("display","none");
					_selec_datos2.css("display","none");
					_selec_datos_txt2.css("display","none");
				}
				break;
			}else{
				continue;
			}
		}
	}
}

function seleccionar(_elem){
	var _id = _elem.value;
	var _selec_datos = $("#datoseleccionado");
	var _selec_datos_txt = $("#datoseleccionadotxt");
	var _grafic = $("#tipo_grafico");
	var _grafic_txt = $("#tipo_grafico_txt");
	if(_id.indexOf('_default_histo') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","block");
		_grafic.css("display","block");
	}else if(_id.indexOf('_default_promedio') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else if(_id.indexOf('_default_sumatoria') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else if(_id.indexOf('_default_maximo') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else if(_id.indexOf('_default_minimo') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else if(_id.indexOf('_default_listar') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else if(_id.indexOf('_default_mayor_que') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else if(_id.indexOf('_default_menor_que') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else if(_id.indexOf('_default_entre') === 0){
		_selec_datos.css("display","block");
		_selec_datos_txt.css("display","block");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}else{
		_selec_datos.css("display","none");
		_selec_datos_txt.css("display","none");
		_grafic_txt.css("display","none");
		_grafic.css("display","none");
	}
	var _descr = document.getElementById('descripcion');
	var _boto = document.getElementById('enviar');
	_boto.disabled = false;

	for(var i=0;i<array_estudios.length;i++){
		if(_id == array_estudios[i]['id']){
			_descr.innerHTML = "<h4>Estudio:"+array_estudios[i]['titulo']+"</h4>Descripci&oacute;n:<br />"+array_estudios[i]['descripcion'];
		}
	}
	seleccionardato(document.getElementById('datoseleccionado'));
}

function validarForm(){
	var _id = $("#idestudio").val();
	var _selec_datos = $("#datoseleccionado").val();
	var _inputData = $("#datovisualizacion");
	var _inputData2 = $("#datovisualizacion2");
	var _idForm = $("#accion");

	if(_id == '-1'){
		alert("No ha indicado el estudio a aplicar");
		return false;
	}else if(_id.indexOf('_default_histo') == 0){
		if(_selec_datos != -1){
			var tipo_Pre = '30';
			for(var i=0;i<_totalDatosInstr;i++){
				if(_selec_datos == _listaDatos[i]["id"]){
					tipo_Pre = _listaDatos[i]["tipopregunta"];
					break;
				}else{
					continue;
				}
			}
			if(tipo_Pre == '31' || tipo_Pre == '32'){
				if(validarEntero("datovisualizacion")){
					_idForm.val('aplicarhistograma');
					return true;
				}else{
					return false;
				}
			}else{
				_idForm.val('aplicarhistograma');
				return true;
			}
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else if(_id.indexOf('_default_promedio') == 0){
		if(_selec_datos != -1){
			_idForm.val('aplicarpromedio');
			return true;
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else if(_id.indexOf('_default_sumatoria') == 0){
		if(_selec_datos != -1){
			_idForm.val('aplicarsumatoria');
			return true;
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else if(_id.indexOf('_default_maximo') == 0){
		if(_selec_datos != -1){
			_idForm.val('aplicarmaximo');
			return true;
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else if(_id.indexOf('_default_minimo') == 0){
		if(_selec_datos != -1){
			_idForm.val('aplicarminimo');
			return true;
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else if(_id.indexOf('_default_listar') == 0){
		if(_selec_datos != -1){
			_idForm.val('aplicarlistar');
			return true;
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else if(_id.indexOf('_default_menor_que') == 0 || _id.indexOf('_default_mayor_que') == 0){
		if(_selec_datos != -1){
			if(_inputData.val() != ''){
				if(_id.indexOf('_default_menor_que') == 0){
					_idForm.val('aplicarmenorque');
				}else{
					_idForm.val('aplicarmayorque');
				}
				return true;
			}else{
				alert("No ha indicado el valor sobre el que se realizara el estudio");
				_inputData.focus();
				return false;
			}
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else if(_id.indexOf('_default_entre') == 0){
		if(_selec_datos != -1){
			if(_inputData.val() != ''){
				if(_inputData2.val() != ''){
					_idForm.val('aplicarentre');
					return true;
				}else{
					alert("No ha indicado el valor máximo para realizar la comparación");
					_inputData2.focus();
					return false;
				}
			}else{
				alert("No ha indicado el valor mínimo para realizar la comparación");
				_inputData.focus();
				return false;
			}
		}else{
		  alert("No ha indicado el campo sobre el que se realizara el estudio");
		  return false;
		}
	}else{
		return true;
	}
	return false;
}

</script>

<%
String tieneResultado = "no";
if (request.getAttribute("mostrarResultado") != null) {
	tieneResultado = (String)request.getAttribute("mostrarResultado");
}

Vector _miResultados = new Vector();
if(request.getAttribute("resultados") != null){
	_miResultados = (Vector)request.getAttribute("resultados");
}

Vector _listaDeEstudios = new Vector();
if(request.getAttribute("listadoEstudios") != null){
	_listaDeEstudios = (Vector)request.getAttribute("listadoEstudios");
	Collections.sort(_listaDeEstudios, new OrdenadorEstudiosPerson(OrdenadorEstudiosPerson.TITULO));
}

Vector _listaDeDatos = new Vector();
if(request.getAttribute("listadoDatos") != null){
  _listaDeDatos = (Vector)request.getAttribute("listadoDatos");
  Collections.sort(_listaDeDatos, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.ACRONIMO));
}

InstanciaObjeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (InstanciaObjeto)request.getAttribute("objetoatrabajar");
}
%>

<%@include file="adminheader.jsp" %>

<script type="text/javascript">
var _listaDatos = new Array();
var _totalDatosInstr = 0;
 <%
	if(!_listaDeDatos.isEmpty()){
		Enumeration _enu2 = _listaDeDatos.elements();
		InstanciaPregunta _prg = null;
		int _cont = 0;
		while (_enu2.hasMoreElements()){
			_prg = (InstanciaPregunta)_enu2.nextElement();
			out.println("_listaDatos["+_cont+"] = new Array();");
			out.println("_listaDatos["+_cont+"]['id'] = '"+_prg.getId()+"';");
			out.println("_listaDatos["+_cont+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_prg.getAcronimo(), "'", "\"")+"';");
			out.println("_listaDatos["+_cont+"]['tipopregunta'] = '"+_prg.getTipoPregunta()+"';");
			out.println("_listaDatos["+_cont+"]['ordenpregunta'] = '"+_prg.getOrden()+"';");
			_cont++;
		}
		out.println("_totalDatosInstr = "+_cont+";");
	}
 %>

	$(document).ready(function(){
		$("#link_estudios").css("color","red");
		seleccionar(document.getElementById('idestudio'));
	});
</script>
<%
if(tieneResultado.equals("si")){
	%>
<style type="text/css">
<!--
td.value {
	background-image: url(comunes/imagenes/gridline58.gif);
	background-repeat: repeat-x;
	background-position: left top;
	border-left: 1px solid #e5e5e5;
	border-right: 1px solid #e5e5e5;
	padding:0;
	border-bottom: none;
	background-color:transparent;
	min-width:400px;
	max-width:400px;
}
td.conestilo {
	padding: 4px 6px;
	border-bottom:1px solid #e5e5e5;
	border-left:1px solid #e5e5e5;
	background-color:#fff;
}
td.value img {
	vertical-align: middle;
	margin: 5px 5px 5px 0;
}
th {
	text-align: left;
	vertical-align:top;
}
td.last {
	border-bottom:1px solid #e5e5e5;
}
td.first {
	border-top:1px solid #e5e5e5;
}
.auraltext
{
   position: absolute;
   font-size: 0;
   left: -1000px;
}
table.resultados {
	background-image:url(comunes/imagenes/bg_fade.png);
	background-repeat:repeat-x;
	background-position:left top;
	max-width: 1000px;
	min-width: 500px;
}
caption {
	font-size:90%;
	font-style:italic;
}
-->
</style>

<table cellspacing="4" cellpadding="4" class="tablasecundaria">
	<tr>
		<td valign="top" align="left">
		<%
		if(_miResultados.isEmpty()){
			out.print("No se obtuvieron resultados.<br />");
		}else{
			//muestro gráfico
			if(request.getAttribute("imagen") != null){
				if(request.getAttribute("imagen").equals("")){
					out.println("<center>Error generando el gr&aacute;fico correspondiente... Intente con otro estilo.</center>");
				}else{
					out.println("<center><a href='"+((String)request.getAttribute("imagen"))+"' target='_blank'><img src='"+((String)request.getAttribute("imagen"))+"' height='450' width='800' /></a></center>");
				}
			}else{
				out.println("<center>Error generando el gr&aacute;fico correspondiente...</center>");
			}
		}
		%>
		</td>
	</tr>
</table>
<%
}else{
%>
<table cellspacing="4" cellpadding="4" class="tablasecundaria">
	<tr>
		<td valign="top" align="left">
		<h2>Aplicar Estudio.</h2>
		<h4>Instrumento: <% out.print(objetoatrabajar.getObjeto());%></h4>
    </td>
  </tr>
  <tr>
    <td valign="top" align="left">
      <%
      if(objetoatrabajar != null){
      %>
        <form action="aplicadorestudios" method="post" onsubmit="return validarForm();">
          <input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
          <input type="hidden" value="seleccionarestudio" id="accion" name="accion">
          <table cellpadding="4" cellspacing="4">
            <tr>
              <td style="text-align:right;width:200px">
                <label>Estudio a Aplicar:</label>
              </td>
              <td style="text-align:left;">
                <select id="idestudio" name="idestudio" onchange="seleccionar(this);">
                  <option value="-1">Seleccione...</option>
                  <optgroup label="Estudios Predefinidos">
                    <option value="_default_histo">Histograma de Frecuencia</option>
					  <option value="_default_listar">Listar Dato</option>
				    <option value="_default_promedio">Promedio</option>
					  <option value="_default_sumatoria">Sumatoria</option>
					  <option value="_default_maximo">M&aacute;ximo</option>
					  <option value="_default_minimo">M&iacute;nimo</option>
					  <option value="_default_mayor_que">Mayor que</option>
					  <option value="_default_menor_que">Menor que</option>
					  <option value="_default_entre">Entre valores definidos</option>
                  </optgroup>
                  <%
                    if(!_listaDeEstudios.isEmpty()){
                      %>
                        <optgroup label="Estudios Creados por el Usuario">
                      <%
                    }
                    if(!_listaDeEstudios.isEmpty()){
                      Enumeration _enu = _listaDeEstudios.elements();
                      EstudioPerso _est = null;
                      while(_enu.hasMoreElements()){
                        _est = (EstudioPerso)_enu.nextElement();
                        out.println("<option value='"+_est.get_id()+"'>"+_est.get_titulo()+"</option>");
                      }
                    }
                    if(!_listaDeEstudios.isEmpty()){
                      %>
                        </optgroup>
                      <%
                    }
                  %>
                </select>
              </td>
            </tr>
            <tr>
              <td style="text-align:right;width:200px">
                <label id="datoseleccionadotxt" style="display:none">Sobre el Campo:</label>
              </td>
              <td style="text-align:left;">
                <select id="datoseleccionado" name="datoseleccionado" onchange="seleccionardato(this);" style="display:none;">
                  <option value="-1">Seleccione...</option>
						<%
						if(!_listaDeDatos.isEmpty()){
							Enumeration _enu = _listaDeDatos.elements();
							InstanciaPregunta _prg = null;
							while(_enu.hasMoreElements()){
								_prg = (InstanciaPregunta)_enu.nextElement();
								out.println("<option value='"+_prg.getId()+"'>"+_prg.getAcronimo()+"</option>");
							}
						}
						%>
                </select>
              </td>
            </tr>
			  <tr>
				  <td style="text-align:right;">
					  <label id="tipo_grafico_txt" style="display:none">Tipo de Gr&aacute;fico:</label>
				  </td>
				  <td style="text-align:left;">
					  <select id="tipo_grafico" name="tipo_grafico" style="display:none">
						  <option value="barras_horizontales" selected="selected">Barras Horizontales</option>
						  <option value="barras_verticales">Barras Verticales</option>
						  <option value="torta">Torta</option>
						  <option value="lineas_horizontales">Linea Vertical</option>
						  <option value="lineas_verticales">Linea Horizontal</option>
					  </select>
				  </td>
			  </tr>
            <tr>
              <td style="text-align:right;width:200px">
                <label id="datovisualizaciontxt" style="display:none">Cantidad de Intervalos:</label>
              </td>
              <td style="text-align:left;">
                <input size="8" type="text" value="1" name="datovisualizacion" id="datovisualizacion" style="display:none">
              </td>
            </tr>
			  <tr>
				<td style="text-align:right;width:200px">
				  <label id="datovisualizaciontxt2" style="display:none">Valor m&aacute;ximo:</label>
				</td>
				<td style="text-align:left;">
				  <input size="8" type="text" value="1" name="datovisualizacion2" id="datovisualizacion2" style="display:none">
				</td>
			  </tr>
            <tr>
              <td>
                &nbsp;
              </td>
              <td style="text-align:left;">
                <input type="submit" value="Ejecutar" id="enviar" name="enviar"><p />
				<input type="button" value="Crear Nuevo Estudio" id="nuevoEst" name="nuevoEst" onclick="javascript:window.location='crearestudioperso?objetoatrabajar=<% out.print(objetoatrabajar.getId()); %>'"><p />
              </td>
            </tr>
          </table>
        </form>
      <p />
      <%
      }else{
        out.println("No se indic&oacute; una estructura con la que se pueda trabajar.");
      }
      %>
    </td>
  </tr>
</table>
<iframe name='gToday:contrast:agenda.js' id='gToday:contrast:agenda.js' src='Contrast/ipopeng.htm' style='visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;' scrolling='no' frameborder='0' height='142' width='132'>
</iframe>

<%
}
%>
<%@include file="adminfooter.jsp" %>
