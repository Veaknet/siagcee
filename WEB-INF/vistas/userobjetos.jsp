<%@page session="true" import="com.siagcee.logic.*" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@include file="userinicio.jsp" %>


<script type="text/javascript" charset="UTF-8">
if (/msie/i.test (navigator.userAgent)) //only override IE
{
	document.nativeGetElementById = document.getElementById;
	document.getElementById = function(id)
	{
		var elem = document.nativeGetElementById(id);
		if(elem)
		{
			//make sure that it is a valid match on id
			if(elem.attributes['id'].value == id)
			{
				return elem;
			}
			else
			{
				//otherwise find the correct element
				for(var i=1;i<document.all[id].length;i++)
				{
					if(document.all[id][i].attributes['id'].value == id)
					{
						return document.all[id][i];
					}
				}
			}
		}
		return null;
	};
}

function validarEntero(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
  var er = /^[-]*[0-9]+$/;
	if(mielem){
		if(mielem.value.match(er) || mielem.value == ''){
			return true;
		}else{
			alert("Solo pueden ingresarse numeros enteros.");
			mielem.value = "";
			mielem.focus();
			return false;
		}
	}
	return false;
}

function validarDouble(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
  var er = /^[-+]?[0-9]+(\.[0-9]+)?$/;
	if(mielem){
		if(mielem.value.match(er) || mielem.value == ''){
			return true;
		}else{
			alert("Solo pueden ingresarse numeros con o sin decimales.");
			mielem.value = "";
			mielem.focus();
			return false;
		}
	}else{
		return false;
	}
}

var _listaDatos = new Array();

function validarModificacionPregClave(){
	var _sufix = '_<% out.print(encuestado.getUsuarioId()); %>';
	if($("#modificacion_pregunta_clave_"+_sufix).val() != $("#modificacion_pregunta_clave2_"+_sufix).val()){
		alert("Al modificar la pregunta clave es necesario que indique por duplicado el nuevo valor.");
		$("#modificacion_pregunta_clave_"+_sufix).focus();
		return false;
	}
	return true;
}

function validarRequeridos(){
	//return true;
	var _sufix = '_<% out.print(encuestado.getUsuarioId()); %>';
	for(var i=0;i<_listaDatos.length;i++){
		if(_listaDatos[i]["tipopregunta"] == 1 || _listaDatos[i]["tipopregunta"] == 2){
			if($("#pregunta_"+_listaDatos[i]['id']+_sufix).val() == '' || $("#pregunta_"+_listaDatos[i]['id']+_sufix).val() == '-1'){
				alert("Es obligatorio responder a la pregunta para concluir:\n\n"+_listaDatos[i]["pregunta"]);
				$("#pregunta_"+_listaDatos[i]['id']+_sufix).focus();
				return false;
			}
		}else{
			if($("#pregunta_"+_listaDatos[i]['id']+_sufix).val() == ''){
				alert("Es obligatorio responder a la pregunta para concluir:\n\n"+_listaDatos[i]["pregunta"]);
				$("#pregunta_"+_listaDatos[i]['id']+_sufix).focus();
				return false;
			}
		}
	}
	return true;
}

<%

String mensaje = "";
if(request.getAttribute("mensaje") != null){
	mensaje = (String)request.getAttribute("mensaje");
	request.setAttribute("mensaje", "");
}

//no utilizado
//Vector _objetos = (Vector)request.getAttribute("objetos");

InstanciaObjeto _miIns = null;
Vector _misRespDadas = new Vector();
if(request.getAttribute("seleccionado") != null){
	_miIns = (InstanciaObjeto)request.getAttribute("seleccionado");
	if(request.getAttribute("respuestasDadas") != null){
		_misRespDadas = (Vector)request.getAttribute("respuestasDadas");
	}
}

/*
//No utilizado desde que se creo editables
Vector preguntasTotales = new Vector();
if(request.getAttribute("preguntasTotales") != null){
	preguntasTotales = (Vector)request.getAttribute("preguntasTotales");
}
*/

Vector preguntasEditables = new Vector();
if(request.getAttribute("preguntasEditables") != null){
	preguntasEditables = (Vector)request.getAttribute("preguntasEditables");
	Enumeration _enu = preguntasEditables.elements();
	int _cont = 0;
	while(_enu.hasMoreElements()){
		PreguntaEditable _miPreg = (PreguntaEditable)_enu.nextElement();
		if(_miPreg.isRequerida() || _miPreg.get_InsPregunta().isCampo_clave_unico()){
			out.println("_listaDatos["+_cont+"] = new Array();");
			out.println("_listaDatos["+_cont+"]['id'] = '"+_miPreg.get_InsPregunta().getId()+"';");
			out.println("_listaDatos["+_cont+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_miPreg.get_InsPregunta().getTextoPregunta(), "'", "\"")+"';");
			out.println("_listaDatos["+_cont+"]['acronimo'] = '"+UtilidadesVarias.reemplazarCaracteres(_miPreg.get_InsPregunta().getAcronimo(), "'", "\"")+"';");
			out.println("_listaDatos["+_cont+"]['tipopregunta'] = '"+_miPreg.get_InsPregunta().getTipoPregunta()+"';");
			out.println("_listaDatos["+_cont+"]['ordenpregunta'] = '"+_miPreg.get_InsPregunta().getOrden()+"';");
			_cont++;
		}
	}
}

%>

</script>

<%@include file="userheader.jsp" %>

<table cellpadding="4" cellspacing="4" class="tablasecundariatitulo">
	<tr>
		<td style="text-align:left" valign="top">
			<h2><% out.println(_miIns.getObjeto()); %></h2>
		</td>
		<td style="text-align:right" valign="bottom" width="36px">
            &nbsp;
		</td>
	</tr>
</table>
<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
<%
if (!mensaje.equals("")) {
	out.println(mensaje + "<p />");
}
if(_miIns == null){
%>
No ha indicado un censo o encuesta para participar.
<%
}else{
	StringBuffer _pregTit = new StringBuffer();  //preguntas titulares inicio de pagina
	StringBuffer _pregResp = new StringBuffer(); //preguntas a responder parte final de la pagina
	StringBuffer _pregRespTemp; //para modificacion de pregunta clave
	StringBuffer _pregRespTemp2; //para modificacion de pregunta clave

	//lanzo el conjunto de preguntas
	out.print("<h4><a name='top'>Responda las preguntas que se indican:</a></h4>");
	%>
	<form action="userobjetos.do" method="post" name="miformulario2" id="miformulario2" autocomplete="off">
		<input type="hidden" name="id_instrumento" id="id_instrumento" value="<% out.print(_miIns.getId());%>">
		<input type="hidden" name="accionaejecutar" id="accionaejecutar" value="insertar">
	<%
	try{
		Vector _temporalPreguntas = _miIns.getObjetoAsociado().getPreguntas(true);
		Collections.sort(_temporalPreguntas, new OrdenadorInstanciaPreguntas());  //ordenado por ordenPregunta
		Collections.sort(_misRespDadas, new OrdenadorRespuestas());     //ordenado por ordenPregunta
		Enumeration _misPreg = _temporalPreguntas.elements();
		InstanciaPregunta miPreg;
		Enumeration _misRespDadasTmp = _misRespDadas.elements();
		Respuesta miRespDada = new Respuesta();
		boolean respEncontrada = false;

		HashMap _editable = new HashMap();
		HashMap _requerida = new HashMap();
		Enumeration _enu = preguntasEditables.elements();
		PreguntaEditable _prEd;
		while(_enu.hasMoreElements()){
			_prEd = (PreguntaEditable)_enu.nextElement();
			_editable.put(_prEd.get_InsPregunta().getId(), true);
			_requerida.put(_prEd.get_InsPregunta().getId(), _prEd.isRequerida());
		}

		String _sufix = "_"+String.valueOf(encuestado.getUsuarioId());
		boolean preguntaRequeridaSinRespuestaEncontrada = false;
		while(_misPreg.hasMoreElements()){
			//para verificar si se encontro una respuesta guardada previamente

			_pregRespTemp = new StringBuffer();
			_pregRespTemp2 = new StringBuffer();

			respEncontrada = false;

			miPreg = (InstanciaPregunta)_misPreg.nextElement();

			//es interna para calculos de estudios.
			if(miPreg.getTipoPregunta() == 100 || (_editable.get(miPreg.getId()) == null && !miPreg.isCampo_clave_unico())){
				continue;
			}

			//titulo de la pregunta
		    //_pregTit.append("<a href='#pregunta_"+miPreg.getId()+"'>"+miPreg.getTextoPregunta()+"</a><p />");
            _pregTit.append(miPreg.getTextoPregunta());

			//muestro la pregunta
			_pregResp.append("<p><label>"+miPreg.getTextoPregunta()+"</label><br />");

			//+++++++++++++++
			//seleccion simple
			//+++++++++++++++
			if(miPreg.getTipoPregunta() == 1){

				//ubico la respuesta correspondiente a la pregunta, si existiera
				while(_misRespDadasTmp.hasMoreElements()){
					miRespDada = (Respuesta)_misRespDadasTmp.nextElement();
					if(miPreg.getId() == miRespDada.getInstanciaPregunta().getId()){
						respEncontrada = true;
						break;
					}
				}

				if(miPreg.isCampo_clave_unico()){
					_pregResp.append("<select id='pregunta_"+miPreg.getId()+_sufix+"' name='pregunta_"+miPreg.getId()+_sufix+"' readonly>");
					_pregRespTemp.append("<select id='modificacion_pregunta_clave_"+_sufix+"' name='modificacion_pregunta_clave_"+_sufix+"'>");
					_pregRespTemp2.append("<select id='modificacion_pregunta_clave2_"+_sufix+"' name='modificacion_pregunta_clave2_"+_sufix+"'>");
				}else{
					_pregResp.append("<select id='pregunta_"+miPreg.getId()+_sufix+"' name='pregunta_"+miPreg.getId()+_sufix+"'>");
				}
				if(!respEncontrada || miRespDada.getRespuestaCerrada().getId() < 0){
					//si no encontré respuesta este option va
					_pregResp.append("<option value='-1' selected='selected'>Seleccione...</option>");
					if(miPreg.isCampo_clave_unico()){
						_pregRespTemp.append("<option value='-1' selected='selected'>Seleccione...</option>");
						_pregRespTemp2.append("<option value='-1' selected='selected'>Seleccione...</option>");
					}
					try{
						if((Boolean)_requerida.get(miPreg.getId())){
							preguntaRequeridaSinRespuestaEncontrada = true;
						}
					}catch (Exception noReq){}
				}else{
					//si encontré respuesta este option va
					//_pregResp.append("<option value='-1'>No s&eacute;/No respondo</option>");
				}

				try{
					Enumeration _misResp = miPreg.getPreguntaAsociada().retornaRespuestasPosibles(true).elements();
					RespuestasPosibles __misResp;
					while(_misResp.hasMoreElements()){
						__misResp = (RespuestasPosibles)_misResp.nextElement();
						if(respEncontrada && (miRespDada.getRespuestaCerrada().getId() == __misResp.getId())){
							_pregResp.append("<option value='"+__misResp.getId()+"' selected='selected'>"+__misResp.getRespuesta()+"</option>");
							if(miPreg.isCampo_clave_unico()){
								_pregRespTemp.append("<option value='"+__misResp.getId()+"' selected='selected'>"+__misResp.getRespuesta()+"</option>");
								_pregRespTemp2.append("<option value='"+__misResp.getId()+"' selected='selected'>"+__misResp.getRespuesta()+"</option>");
							}
							respEncontrada = false;
							miRespDada = new Respuesta();
						}else{
							_pregResp.append("<option value='"+__misResp.getId()+"'>"+__misResp.getRespuesta()+"</option>");
							if(miPreg.isCampo_clave_unico()){
								_pregRespTemp.append("<option value='"+__misResp.getId()+"'>"+__misResp.getRespuesta()+"</option>");
								_pregRespTemp2.append("<option value='"+__misResp.getId()+"'>"+__misResp.getRespuesta()+"</option>");
							}
						}
					}
				}catch(Exception e){e.printStackTrace();
				}finally{
					_pregResp.append("</select>");
					if(miPreg.isCampo_clave_unico()){
						_pregRespTemp.append("</select>");
						_pregRespTemp2.append("</select>");
						_pregResp.append(_pregRespTemp.toString()+_pregRespTemp2.toString());
					}
				}

			//+++++++++++++++
			//seleccion múltiple
			//+++++++++++++++
			}else if(miPreg.getTipoPregunta() == 2){
				Vector _temporalRespuestas = new Vector();
				//ubico la respuesta correspondiente a la pregunta, si existiera
				while(_misRespDadasTmp.hasMoreElements()){
					miRespDada = (Respuesta)_misRespDadasTmp.nextElement();
					if(miPreg.getId() == miRespDada.getInstanciaPregunta().getId()){
						respEncontrada = true;
						//ubico las respuestas en un vector de menor tamaño temporal para recorrer en el momento de identificar las respuestas
						_temporalRespuestas.add(miRespDada);
					}
				}

				if(miPreg.isCampo_clave_unico()){
					_pregResp.append("<select id='pregunta_"+miPreg.getId()+_sufix+"' name='pregunta_"+miPreg.getId()+_sufix+"' multiple readonly>");
					_pregRespTemp.append("<select id='modificacion_pregunta_clave_"+_sufix+"' name='modificacion_pregunta_clave_"+_sufix+"' multiple readonly>");
					_pregRespTemp2.append("<select id='modificacion_pregunta_clave2_"+_sufix+"' name='modificacion_pregunta_clave2_"+_sufix+"' multiple readonly>");
				}else{
					_pregResp.append("<select id='pregunta_"+miPreg.getId()+_sufix+"' name='pregunta_"+miPreg.getId()+_sufix+"' multiple>");
				}
				if(!respEncontrada || miRespDada.getRespuestaCerrada().getId() < 0){
					//si no encontré respuesta este option va
					_pregResp.append("<option value='-1' selected='selected'>Seleccione...</option>");
					try{
						if((Boolean)_requerida.get(miPreg.getId())){
							preguntaRequeridaSinRespuestaEncontrada = true;
						}
					}catch (Exception noReq){}
				}else{
					//si encontré respuesta este option va
					//_pregResp.append("<option value='-1'>No s&eacute;/No respondo</option>");
				}

				try{
					Enumeration _misResp = miPreg.getPreguntaAsociada().retornaRespuestasPosibles(true).elements();
					RespuestasPosibles __misResp;
					while(_misResp.hasMoreElements()){
						__misResp = (RespuestasPosibles)_misResp.nextElement();

						boolean _miEncontrado = false;
						Enumeration _temporalResp = _temporalRespuestas.elements();
						Respuesta _miTemporalRespuestas;
						while(_temporalResp.hasMoreElements()){
							_miTemporalRespuestas = (Respuesta)_temporalResp.nextElement();
							if(__misResp.getId() == _miTemporalRespuestas.getRespuestaCerrada().getId()){
								_miEncontrado = true;
							}
						}
						if(_miEncontrado){
							_pregResp.append("<option value='"+__misResp.getId()+"' selected='selected'>"+__misResp.getRespuesta()+"</option>");
							_pregRespTemp.append("<option value='"+__misResp.getId()+"' selected='selected'>"+__misResp.getRespuesta()+"</option>");
							_pregRespTemp2.append("<option value='"+__misResp.getId()+"' selected='selected'>"+__misResp.getRespuesta()+"</option>");
						}else{
							_pregResp.append("<option value='"+__misResp.getId()+"'>"+__misResp.getRespuesta()+"</option>");
							if(miPreg.isCampo_clave_unico()){
								_pregRespTemp.append("<option value='"+__misResp.getId()+"'>"+__misResp.getRespuesta()+"</option>");
								_pregRespTemp2.append("<option value='"+__misResp.getId()+"'>"+__misResp.getRespuesta()+"</option>");
							}
						}
					}
				}catch(Exception e){e.printStackTrace();
				}finally{
					_pregResp.append("</select>");
					if(miPreg.isCampo_clave_unico()){
						_pregRespTemp.append("</select>");
						_pregRespTemp2.append("</select>");
						_pregResp.append(_pregRespTemp.toString()+_pregRespTemp2.toString());
					}
				}

			//+++++++++++++++
			//preguntas abiertas
			//+++++++++++++++
			}else{

				//ubico la respuesta correspondiente a la pregunta, si existiera
                _misRespDadasTmp = _misRespDadas.elements();
				while(_misRespDadasTmp.hasMoreElements()){
					miRespDada = (Respuesta)_misRespDadasTmp.nextElement();
					if(miPreg.getId() == miRespDada.getInstanciaPregunta().getId()){
						respEncontrada = true;
						break;
					}
				}

				_pregResp.append("<input type='text' name='pregunta_"+miPreg.getId()+_sufix+"' id='pregunta_"+miPreg.getId()+_sufix+"'");

				if(miPreg.isCampo_clave_unico()){
					_pregResp.append(" readonly ");
					_pregRespTemp.append("<input type='text' name='modificacion_pregunta_clave_"+_sufix+"' id='modificacion_pregunta_clave_"+_sufix+"'");
					_pregRespTemp2.append("<input type='text' name='modificacion_pregunta_clave2_"+_sufix+"' id='modificacion_pregunta_clave2_"+_sufix+"'");
				}
				//+++++++++++++++
				//pregunta abierta para texto
				//+++++++++++++++
				if(miPreg.getTipoPregunta() == 30){
					if(!respEncontrada || miRespDada.getRespuestaAbiertaTexto().equals("")){
						//si no encontré respuesta
						_pregResp.append(" value='' size='50'>");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='' size='50'>");
							_pregRespTemp2.append(" value='' size='50'>");
						}
						try{
							if((Boolean)_requerida.get(miPreg.getId())){
								preguntaRequeridaSinRespuestaEncontrada = true;
							}
						}catch (Exception noReq){}
					}else{
						//si encontré respuesta
						_pregResp.append(" value='"+miRespDada.getRespuestaAbiertaTexto()+"' size='50'>");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='"+miRespDada.getRespuestaAbiertaTexto()+"' size='50'>");
							_pregRespTemp2.append(" value='"+miRespDada.getRespuestaAbiertaTexto()+"' size='50'>");
						}
					}

				//+++++++++++++++
				//pregunta abierta para numeros sin decimales
				//+++++++++++++++
				}else if(miPreg.getTipoPregunta() == 31){
					if(!respEncontrada || (miRespDada.getRespuestaAbiertaInt() == 0)){
						//si no encontré respuesta
						_pregResp.append(" value='' onblur='validarEntero(\"pregunta_"+miPreg.getId()+_sufix+"\");'>");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='' onblur='validarEntero(\"modificacion_pregunta_clave_"+_sufix+"\");'>");
							_pregRespTemp2.append(" value='' onblur='validarEntero(\"modificacion_pregunta_clave2_"+_sufix+"\");'>");
						}
						try{
							if((Boolean)_requerida.get(miPreg.getId())){
								preguntaRequeridaSinRespuestaEncontrada = true;
							}
						}catch (Exception noReq){}
					}else{
						//si encontré respuesta
						_pregResp.append(" value='"+String.valueOf(miRespDada.getRespuestaAbiertaInt())+"' onblur='validarEntero(\"pregunta_"+miPreg.getId()+_sufix+"\");'>");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='"+String.valueOf(miRespDada.getRespuestaAbiertaInt())+"' onblur='validarEntero(\"modificacion_pregunta_clave_"+_sufix+"\");'>");
							_pregRespTemp2.append(" value='"+String.valueOf(miRespDada.getRespuestaAbiertaInt())+"' onblur='validarEntero(\"modificacion_pregunta_clave2_"+_sufix+"\");'>");
						}
					}

				//+++++++++++++++
				//pregunta abierta para numeros con decimales
				//+++++++++++++++
				}else if(miPreg.getTipoPregunta() == 32){
					DecimalFormat _df = new DecimalFormat("###############.############");
					if(!respEncontrada || miRespDada.getRespuestaAbiertaDouble() == 0F){
						//si no encontré respuesta
						_pregResp.append(" value='' onblur='validarDouble(\"pregunta_"+miPreg.getId()+_sufix+"\");'>");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='' onblur='validarDouble(\"modificacion_pregunta_clave_"+_sufix+"\");'>");
							_pregRespTemp2.append(" value='' onblur='validarDouble(\"modificacion_pregunta_clave2_"+_sufix+"\");'>");
						}
						try{
							if((Boolean)_requerida.get(miPreg.getId())){
								preguntaRequeridaSinRespuestaEncontrada = true;
							}
						}catch (Exception noReq){}
					}else{
						//si encontré respuesta
						_pregResp.append(" value='"+_df.format(miRespDada.getRespuestaAbiertaDouble())+"' onblur='validarDouble(\"pregunta_"+miPreg.getId()+_sufix+"\");'>");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='"+_df.format(miRespDada.getRespuestaAbiertaDouble())+"' onblur='validarDouble(\"modificacion_pregunta_clave_"+_sufix+"\");'>");
							_pregRespTemp2.append(" value='"+_df.format(miRespDada.getRespuestaAbiertaDouble())+"' onblur='validarDouble(\"modificacion_pregunta_clave2_"+_sufix+"\");'>");
						}
					}

				//+++++++++++++++
				//pregunta abierta para fechas
				//+++++++++++++++
				}else if(miPreg.getTipoPregunta() == 33){
					if(!respEncontrada || miRespDada.getRespuestaAbiertaDate() == null){
						//si no encontré respuesta
						_pregResp.append(" value='' ");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='' ");
							_pregRespTemp2.append(" value='' ");
						}
						try{
							if((Boolean)_requerida.get(miPreg.getId())){
								preguntaRequeridaSinRespuestaEncontrada = true;
							}
						}catch (Exception noReq){}
					}else{
						//si encontré respuesta
						SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

						String[] _respDate = miRespDada.getRespuestaAbiertaDate().toString().split("-");
						_pregResp.append(" value='"+df.format(miRespDada.getRespuestaAbiertaDate())+"' ");
						if(miPreg.isCampo_clave_unico()){
							_pregRespTemp.append(" value='"+df.format(miRespDada.getRespuestaAbiertaDate())+"' ");
							_pregRespTemp2.append(" value='"+df.format(miRespDada.getRespuestaAbiertaDate())+"' ");
						}
					}
					_pregResp.append(" readonly onclick='if(self.gfPop)gfPop.fPopCalendar(document.getElementById(\"pregunta_"+miPreg.getId()+_sufix+"\"));return false;'>");
					if(miPreg.isCampo_clave_unico()){
						_pregRespTemp.append(" readonly onclick='if(self.gfPop)gfPop.fPopCalendar(document.getElementById(\"modificacion_pregunta_clave_"+_sufix+"\"));return false;'>");
						_pregRespTemp2.append(" readonly onclick='if(self.gfPop)gfPop.fPopCalendar(document.getElementById(\"modificacion_pregunta_clave2_"+_sufix+"\"));return false;'>");
					}
				}
				if(miPreg.isCampo_clave_unico()){
					String _newHtml = "<img title='Haga clic aqu&iacute; para modificar la informaci&oacute;n en la pregunta clave' height='24' src='comunes/imagenes/modificar.png' onclick='$(\"#containerforkeypass\").toggle();'><div id='containerforkeypass'>";
					_newHtml += "<table><tr>";
					_newHtml += "<td>Actualice su pregunta clave:</td><td>"+_pregRespTemp.toString()+"</td></tr><tr><td>Confirme la respuesta:</td><td>"+_pregRespTemp2.toString()+"</td>";
					_newHtml += "</tr></table></div>";
					_pregResp.append(_newHtml);
				}
			}
		}

		//si tengo preguntas requeridas sin respuestas en este punto entonces las borro todas, es inestable.
		//por lo general ocurre para la pregunta clave al acceder
		if(preguntaRequeridaSinRespuestaEncontrada){
			Respuesta.delRespuestasDeUsuario(encuestado, encuestado.getConexion(), _miIns);
		}
		//out.println(_pregTit);
		out.println(_pregResp);
		%>
		<p />
		<input type='submit' value='Finalizar' onclick="return validarRequeridos()&&validarModificacionPregClave();">
		</form>
		<iframe name='gToday:contrast:agenda.js' id='gToday:contrast:agenda.js' src='Contrast/ipopeng.htm' style='visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;' scrolling='no' frameborder='0' height='142' width='132'>
		</iframe>
		<%
	}catch(Exception e){e.printStackTrace();}
}
%>
</td></tr></table>
<%@include file="adminfooter.jsp" %>
