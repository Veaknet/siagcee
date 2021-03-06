<%@ page import="com.siagcee.logic.InstanciaObjeto" %>
<%@ page import="com.siagcee.logic.InstanciaPregunta" %>
<%@ page import="com.siagcee.logic.RespuestasPosibles" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>

<html>
	<head>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>SIGECENE - Sistema Para La Gestión De Censos, Encuestas y Estudios</title>
		<link type="image/x-icon" href="comunes/imagenes/favicon.ico" rel="icon" />
		<link type="image/x-icon" href="comunes/imagenes/favicon.ico" rel="shortcut icon" />
		<link rel="stylesheet" type="text/css" href="comunes/css/micss.css">
		<!-- IE 6 "fixes" -->
		<!--[if lt IE 7]>
		<link type='text/css' href='comunes/css/basic_ie.css' rel='stylesheet' media='screen' />
		<![endif]-->
		<script type="text/javascript" src="comunes/javascript/jquery.js"></script>
		<script type="text/javascript" src="comunes/javascript/jquery.simplemodal.js"></script>
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

		function validarForm(){
			if($("#campo_clave2")){
				if($("#campo_clave2").val() == ''){
					alert("Debe indicar un valor.");
					$("#campo_clave2").focus();
					return false;
				}else{
					if($("#campo_clave2").attr("type") == 'text'){
						$("#campo_clave").val($("#campo_clave2").val());
						$("#campo_clave2").val("");
					}
					return true;
				}
			}
			return true;
		}
		</script>
	</head>

<%@include file="userheader.jsp" %>

<%
int _opcion = 0;

String _mensaje = "";
if(request.getAttribute("mensaje") != null){
	_mensaje = (String)request.getAttribute("mensaje");
}

String _titulo = "";

InstanciaObjeto seleccionado = null;
if(request.getAttribute("InstanciaSeleccionadaPorId") != null){
	seleccionado = (InstanciaObjeto)request.getAttribute("InstanciaSeleccionadaPorId");
	_opcion = 1;
	_titulo = "Identif&iacute;quese para acceder ";
	if(seleccionado.getObjetoAsociado().getClass().toString().contains("Censo")){
		_titulo = _titulo + "al Censo: ";
	}else{
		_titulo = _titulo + "a la Encuesta: ";
	}
	_titulo = _titulo + seleccionado.getObjeto();
}

Vector _insVector = new Vector();
if(request.getAttribute("insVector") != null){
	_opcion = 0;
	_insVector = (Vector)request.getAttribute("insVector");
	_titulo = "Seleccione el Censo o Encuesta a Responder";
	_titulo = "SIGECENE.";
}
%>
<table cellpadding="4" cellspacing="4" class="tablasecundariatitulo">
	<tr>
		<td style="text-align:left" valign="top">
			<h2><% out.print(_titulo);%></h2>
		</td>
		<td style="text-align:right" valign="bottom" width="36px">&nbsp;
		</td>
	</tr>
</table>


<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
			<%
			out.println(_mensaje+"<p />");
			if(_opcion == 0){
                if(true){
                    out.println("Puede ahora cerrar esta ventana haciendo clic en la 'X' de la ventana o pestaña de su navegador.");
                    out.println("<script>if(opener){alert('Gracias por su participacion.');window.close();}</script>");
                }else{
                    //por petición del tutor estas lineas quedan deshabilitadas
                    if(!_insVector.isEmpty()){
                        Vector _instrumentos = new Vector();
                        Enumeration _enu = _insVector.elements();
                        InstanciaObjeto _insObj = null;
                        %>
                            <form action="autenticarusuario.do" method="get">
                                <label>Censo o Encuesta:</label><br />
                                <select id="identificador_publico" name="identificador_publico" onchange="if(this.value != -1){this.parentNode.submit();}">
                                    <option value="-1">Seleccione...</option>
                                <%
                                while (_enu.hasMoreElements()){
                                    _insObj = (InstanciaObjeto)_enu.nextElement();
                                    if(!(_insObj.getAcceso() == 0 || (request.getParameter("muestratodos") != null))){
                                        //solo muestro los publicos
                                        //los restringidos se acceden por links
                                        continue;
                                    }
                                    %>
                                        <option value="<% out.print(_insObj.getIdPublico()); %>"><% out.print(_insObj.getObjeto()); %></option>
                                    <%
                                }
                                    %>
                                </select>
                            </form>
                            <%
                        }else{
                            out.println("No existen censos o encuestas disponibles. Disculpe.<p />");
							out.println("<script>if(!opener){document.write('Puede ahora cerrar esta ventana haciendo clic en la \"X\" de la ventana o pestaña de su navegador.');}else{document.write('<input type=\"button\" onclick=\"window.close();\" value=\"Cerrar esta ventana\">')}</script>");
                        }
                    }
				}else{
					SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date _hoy = new Date();

					if(_dateFormat.format(_hoy).compareTo(_dateFormat.format(seleccionado.getFechaCierre())) >= 0){
						out.println("Disculpe el censo o encuesta requerido ya ha finalizado.<p />");
						out.println("<script>if(!opener){document.write('Puede ahora cerrar esta ventana haciendo clic en la \"X\" de la ventana o pestaña de su navegador.');}else{document.write('<input type=\"button\" onclick=\"window.close();\" value=\"Cerrar esta ventana\">')}</script>");
					}else if(_dateFormat.format(_hoy).compareTo(_dateFormat.format(seleccionado.getFechaInicio())) < 0){
						out.println("Disculpe el censo o encuesta requerido no ha sido publicado a&uacute;n.<p />");
						out.println("<script>if(!opener){document.write('Puede ahora cerrar esta ventana haciendo clic en la \"X\" de la ventana o pestaña de su navegador.');}else{document.write('<input type=\"button\" onclick=\"window.close();\" value=\"Cerrar esta ventana\">')}</script>");
					}else{
						//solicito campo_clave
						InstanciaPregunta _miPreguntaClave = seleccionado.getObjetoAsociado().retornaPreguntaClave(true);
						if(_miPreguntaClave != null){
							out.println("<form action='autenticarusuario.do' method='post' autocomplete='off'>");
							out.println("<input type='hidden' value='"+seleccionado.getId()+"' id='id_instrumento' name='id_instrumento'>");
							out.println("<label>"+_miPreguntaClave.getTextoPregunta()+"</label><br />");
							if(_miPreguntaClave.getTipoPregunta() == 1){
								out.println("<select id='campo_clave' name='campo_clave'>");
								out.println("<option value=''>Seleccione...</option>");
								try{
									Enumeration _misResp = _miPreguntaClave.getPreguntaAsociada().retornaRespuestasPosibles(true).elements();
									RespuestasPosibles __misResp;
									while(_misResp.hasMoreElements()){
										__misResp = (RespuestasPosibles)_misResp.nextElement();
										out.println("<option value='"+__misResp.getId()+"'>"+__misResp.getRespuesta()+"</option>");
									}
								}catch(Exception e){
									e.printStackTrace();
								}finally{
									out.println("</select>");
								}
							}else if(_miPreguntaClave.getTipoPregunta() == 30){
								out.println("<input type='text' id='campo_clave2' name='campo_clave2'>");
								out.println("<input type='hidden' id='campo_clave' name='campo_clave'>");
							}else if(_miPreguntaClave.getTipoPregunta() == 31){
								out.println("<input type='text' id='campo_clave2' name='campo_clave2' onblur='validarEntero(\"campo_clave2\");'>");
								out.println("<input type='hidden' id='campo_clave' name='campo_clave'>");
							}else if(_miPreguntaClave.getTipoPregunta() == 32){
								out.println("<input type='text' id='campo_clave2' name='campo_clave2' onblur='validarDouble(\"campo_clave2\");'>");
								out.println("<input type='hidden' id='campo_clave' name='campo_clave'>");
							}else if(_miPreguntaClave.getTipoPregunta() == 33){
								out.println("<input type='text' id='campo_clave2' name='campo_clave2' readonly onclick='if(self.gfPop)gfPop.fPopCalendar(document.getElementById(\"campo_clave2\"));return false;'>");
								out.println("<input type='hidden' id='campo_clave' name='campo_clave'>");
							}else{
								out.println("No es posible identificarlo como miembro de la poblaci&oacute;n por error en la definici&oacute;n por parte del administrador. disculpe.");
							}
							out.println("<input type='submit' value='Acceder' onclick='return validarForm();'>");
							out.println("</form>");
						}else{
							out.println("Redireccionando hacia el Censo o la Encuesta.");
							%>
								<form action="autenticarusuario.do" method="post">
									<% out.println("<input type='hidden' value='"+seleccionado.getId()+"' id='id_instrumento' name='id_instrumento'>"); %>
									<a style='display:none' id='link_censo' href='' onclick="if(this.value != -1){this.parentNode.submit();}">aqu&iacute;</a>
								</form>
								<script type="text/javascript">
									$(document).ready(function(){
										$("#link_censo").click();
									});
								</script>
							<%
						}
					}
				}
			%>
		</td>
	</tr>
</table>
<iframe name='gToday:contrast:agenda.js' id='gToday:contrast:agenda.js' src='Contrast/ipopeng.htm' style='visibility: visible; z-index: 999; position: absolute; top: -500px; left: -500px;' scrolling='no' frameborder='0' height='142' width='132'>
</iframe>
<%@include file="adminfooter.jsp" %>
