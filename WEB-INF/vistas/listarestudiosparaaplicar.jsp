<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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

var array_estudios = new Array();

function seleccionar(_elem){
	var _id = _elem.value;
	var _descr = document.getElementById('descripcion');
	var _boto = document.getElementById('enviar');
	_boto.disabled = false;

	for(var i=0;i<array_estudios.length;i++){
	  if(_id == array_estudios[i]['id']){
		  _descr.innerHTML = "<h4>Estudio:"+array_estudios[i]['titulo']+"</h4>Descripci&oacute;n:<br />"+array_estudios[i]['descripcion'];
	  }
	}
}

</script>

<%
String tieneResultado = "no";
if (request.getAttribute("mostrarResultado") != null) {
	tieneResultado = (String)request.getAttribute("mostrarResultado");
}

Estudio _estudio = null;
if (request.getAttribute("idestudio") != null) {
	_estudio = (Estudio)request.getAttribute("idestudio");
}

Vector _listaDeEstudios = new Vector();
if(request.getAttribute("listadoEstudios") != null){
	_listaDeEstudios = (Vector)request.getAttribute("listadoEstudios");
	Collections.sort(_listaDeEstudios, new OrdenadorEstudios(OrdenadorEstudios.TITULO));
}

Vector _listaDeDatos = new Vector();
if(request.getAttribute("listadoDatos") != null){
  _listaDeDatos = (Vector)request.getAttribute("listadoDatos");
  Collections.sort(_listaDeDatos, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.PREGUNTA));
}

Vector _listaDeDatosPantalla = new Vector();
if(request.getAttribute("listadoDeDatosPorPantalla") != null){
	_listaDeDatosPantalla = (Vector)request.getAttribute("listadoDeDatosPorPantalla");
}

Vector _miResultados = new Vector();
if(request.getAttribute("resultados") != null){
	_miResultados = (Vector)request.getAttribute("resultados");
}

Vector _miResultadosSinCoincidencias = new Vector();
if(request.getAttribute("resultadosSinCoincidencias") != null){
	_miResultadosSinCoincidencias = (Vector)request.getAttribute("resultadosSinCoincidencias");
}

InstanciaObjeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (InstanciaObjeto)request.getAttribute("objetoatrabajar");
}


if(!tieneResultado.equals("si")){
	if(!_listaDeEstudios.isEmpty()){
		out.println("<script type='text/javascript' charset='utf-8'>");
		Enumeration _enu_ = _listaDeEstudios.elements();
		Estudio _est_ = null;
		int i = 0;
		while(_enu_.hasMoreElements()){
			_est_ = (Estudio)_enu_.nextElement();
			out.println("array_estudios["+i+"] = new Array();");
			out.println("array_estudios["+i+"]['id'] = '"+_est_.getId()+"';");
			out.println("array_estudios["+i+"]['titulo'] = '"+UtilidadesVarias.reemplazarCaracteres(UtilidadesVarias.reemplazarCaracteres(_est_.getTitulo(), "\n", "<br>"), "\"", "\\\"")+"';");
			out.println("array_estudios["+i+"]['descripcion'] = '"+UtilidadesVarias.reemplazarCaracteres(UtilidadesVarias.reemplazarCaracteres(_est_.getDescripcion(), "\n", "<br>"), "\"", "\\\"")+"';");
			i++;
		}
		out.println("</script>");
	}
}

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

<% } %>
<%@include file="adminheader.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#link_estudios").css("color","red");
	});
</script>

<h2>Aplicar Estudio.</h2>
<h4>Instrumento: <% out.print(objetoatrabajar.getObjeto());%></h4>

<%
if(objetoatrabajar != null){
	if(tieneResultado.equals("si")){
		Collections.sort(_miResultados, new OrdenadorResultadosEstudio());
		Enumeration _enu = _miResultados.elements();
		boolean primero = true;
		int _del100porciento = 0;
		int _totalCoincidencias = 0;
		
		while(_enu.hasMoreElements()){
			Estudio.NodoResultadoCalculo _miNodo = (Estudio.NodoResultadoCalculo)_enu.nextElement();
			_totalCoincidencias = _totalCoincidencias + _miNodo.cantidadCoincidencias;
		}

		SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //formatear la fecha para comprobacion interna de la pagina
		SimpleDateFormat _dateFormatVisible = new SimpleDateFormat("dd/MM/yyyy");  //formatear la fecha para la visualizacion del usuario
		_enu = _miResultados.elements();
		if(_miResultados.isEmpty()){
			out.print("No se obtuvieron resultados.<br />");
			/*
		}else	if(_miResultados.size() == 1){
			try{
				Date _date = (Date)((Estudio.NodoResultadoCalculo)_miResultados.elementAt(0))._valor;
				out.print("Se obtuvo el &uacute;nico valor de: <strong>"+_dateFormatVisible.format(_date)+"</strong><br />");
			}catch(Exception eee){
				try{
					Double _t = (Double)((Estudio.NodoResultadoCalculo)_miResultados.elementAt(0))._valor;
					DecimalFormat _df = new DecimalFormat("###############.############");
					out.print("Se obtuvo el &uacute;nico valor de: <strong>"+_df.format(_t)+"</strong><br />");
				}catch(Exception e){
					try{
						out.print("Se obtuvo el &uacute;nico valor de: <strong>"+((Estudio.NodoResultadoCalculo)_miResultados.elementAt(0))._valor+"</strong><br />");
					}catch (Exception ee){
						ee.printStackTrace();
					}
				}
			}
			out.print("<br/>Para un total de: "+((Estudio.NodoResultadoCalculo)_miResultados.elementAt(0)).cantidadCoincidencias+" coincidencias (100%).");
			*/
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
	}else{
		if(!_listaDeDatosPantalla.isEmpty()){
			out.print("<form action='aplicadorestudios' method='POST'>");
			out.print("<input type='hidden' value='ejecutar' id='accion' name='accion'>");
			out.print("<input type='hidden' value='"+objetoatrabajar.getId()+"' id='objetoatrabajar' name='objetoatrabajar'>");
			out.print("<input type='hidden' value='"+_estudio.getId()+"' id='idestudio' name='idestudio'>");
			out.print("<input type='hidden' value='"+request.getParameter("tipo_grafico")+"' id='tipo_grafico' name='tipo_grafico'>");
			out.print("<input type='hidden' value='"+request.getParameter("tipo_infor")+"' id='tipo_infor' name='tipo_infor'>");
			out.print("<table cellpadding='4' cellspacing='4' border='0'>");
			Enumeration _enu = _listaDeDatosPantalla.elements();
			while(_enu.hasMoreElements()){
				DatoSolicitadoEstudio _miNodo = (DatoSolicitadoEstudio)_enu.nextElement();
				out.println("<tr><td align='right'>"+_miNodo.getTitulo()+":</td>");
				if(_miNodo.getTipoDato().getTipoPregunta() < 30){
					//select
					%>
					<td><select id='<% out.print(_miNodo.getAcronimo());%>' name='<% out.print(_miNodo.getAcronimo());%>'>
						<option value="">Seleccione...</option>
					<%
						Enumeration _enu2 = _miNodo.getTipoDato().retornaRespuestasPosibles(true).elements();
						while(_enu2.hasMoreElements()){
							RespuestasPosibles _miRespPos = (RespuestasPosibles)_enu2.nextElement();
							%>
						  <option value="<% out.print(_miRespPos.getId());%>"><% out.print(_miRespPos.getRespuesta());%></option>
							<%
						}
					%>
					</select></td>
					<%
				}else{
					%>
          <td><input type="text" id="<% out.print(_miNodo.getAcronimo());%>" name="<% out.print(_miNodo.getAcronimo());%>"<%
	          if(_miNodo.getTipoDato().getTipoPregunta() == 31){
	            out.print(" value='0' onblur='validarEntero(\""+_miNodo.getAcronimo()+"\");'");
	          }
	          if(_miNodo.getTipoDato().getTipoPregunta() == 32){
	            out.print(" value='0' onblur='validarDouble(\""+_miNodo.getAcronimo()+"\");'");
	          }
	          if(_miNodo.getTipoDato().getTipoPregunta() == 33){
	            out.print(" value='' readonly onclick='if(self.gfPop)gfPop.fPopCalendar(document.getElementById(\""+_miNodo.getAcronimo()+"\"));return false;'");
	          }
		      %>></td>
					<%
				}
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<input type='submit' value='Seleccionar'>");
			out.print("</form>");
		}else{
			if(!_listaDeEstudios.isEmpty()){
				Enumeration _enu = _listaDeEstudios.elements();
				Estudio _est = null;
				%>
		    <form action="aplicadorestudios" method="post">
			    <input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
			    <input type="hidden" value="seleccionarestudio" id="accion" name="accion">
			    <table cellpadding="4" cellspacing="4">
				    <tr>
					    <td style="text-align:right;">
						    <label>Estudio a aplicar:</label>
					    </td>
					    <td style="text-align:left;">
						    <select id="idestudio" name="idestudio" onchange="seleccionar(this);">
							    <option value="-1">Seleccione...</option>
							    <%
							    while(_enu.hasMoreElements()){
								    _est = (Estudio)_enu.nextElement();
								    out.println("<option value='"+_est.getId()+"'>"+_est.getTitulo()+"</option>");
							    }
							    %>
						    </select>
					    </td>
				    </tr>
				    <tr>
					    <td style="text-align:right;">
						    <label>Mostrar:</label>
					    </td>
					    <td style="text-align:left;">
						    <select id="tipo_infor" name="tipo_infor">
							    <option value="frecuencia" selected="selected">Frecuencia</option>
							    <option value="detallado">Gr&aacute;fica</option>
						    </select>
					    </td>
				    </tr>
						<tr>
							<td style="text-align:right;">
								<label>Tipo de Gr&aacute;fico:</label>
							</td>
							<td style="text-align:left;">
								<select id="tipo_grafico" name="tipo_grafico">
									<option value="barras_horizontales" selected="selected">Barras Horizontales</option>
									<option value="barras_verticales">Barras Verticales</option>
									<option value="torta">Torta</option>
									<option value="lineas_horizontales">Linea Vertical</option>
									<option value="lineas_verticales">Linea Horizontal</option>
								</select>
							</td>
						</tr>
				    <tr>
					    <td>
						    &nbsp;
					    </td>
					    <td style="text-align:left;">
						    <input type="submit" value="Ejecutar" id="enviar" name="enviar" disabled="true">
					    </td>
				    </tr>
					</table>
			  </form>
				<div id="descripcion" name="descripcion"></div>
				<%
			}else{
				out.println("No existen estudios asociados a la estructura. Por favor, haga clic <a href='generadorestudios?objetoatrabajar="+objetoatrabajar.getObjetoAsociado().getId()+"'>aqu&iacute;</a> para crear un estudio.");
			}
		}
	}
}else{
	out.println("No se indic&oacute; una estructura con la que se pueda trabajar.");
}
%>
<iframe name='gToday:contrast:agenda.js' id='gToday:contrast:agenda.js' src='Contrast/ipopeng.htm' style='visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;' scrolling='no' frameborder='0' height='142' width='132'>
</iframe>
<%@include file="adminfooter.jsp" %>
