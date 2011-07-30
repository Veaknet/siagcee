<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Enumeration" %>
<%@include file="admininicio.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		//whizzywig();
	});
</script>
<body>
	<table cellpadding="0" cellspacing="0" class="tablaprincipal">
		<tr>
			<td valign="middle" align="left" style="border-top: burlywood ridge thick;border-left: burlywood ridge thick;border-width:3px;max-width:320px;width:320px">
				<img src="comunes/imagenes/siagcee_logo.png" alt="SIGECENE">
			</td>
		</tr>
		<tr>
			<td valign="top" align="left">
				<table cellpadding="4" cellspacing="4" class="tablasecundariatitulo hideprint" style="width:950px;min-width:950px;max-width:950px;">
					<tr>
						<td style="text-align:left" valign="top" >
							<h2>Informaci&oacute;n</h2>
						</td>
					</tr>
				</table>


<%

InstanciaObjeto _miObj = (InstanciaObjeto)sesion.getAttribute("generadorSql_instanciaObjeto");

InstanciaObjeto _objetoatrabajar = null;
Vector _listadoPreguntas = new Vector();
if(request.getAttribute("objetoatrabajar")!=null){
	_objetoatrabajar = (InstanciaObjeto)request.getAttribute("objetoatrabajar");
	_listadoPreguntas = (Vector)_objetoatrabajar.getObjetoAsociado().getPreguntas(true);
	Collections.sort(_listadoPreguntas, new OrdenadorInstanciaPreguntas());
}

String _sqlFinal = "";
if(request.getAttribute("generadorSql_sqlFinal")!=null){
	_sqlFinal = (String)request.getAttribute("generadorSql_sqlFinal");
}

Vector _respuestas = new Vector();
if(request.getAttribute("respuestas") != null){
	_respuestas = (Vector)request.getAttribute("respuestas");
}

Vector _preguntas = new Vector();
if(request.getAttribute("preguntas") != null){
	_preguntas = (Vector)request.getAttribute("preguntas");
}

String _accioninvitar = "true";
if(request.getParameter("accioninvitar") != null){
	_accioninvitar = (String)request.getParameter("accioninvitar");
}

	%>
<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
			<h3>Vista Preliminar de Encuestados Seleccionados.</h3>
<%
	if(_respuestas.isEmpty()){
		out.println("La consulta no arroj&oacute; resultados.<br /><p /><a href='#' onclick='window.close();'>Cerrar esta ventana y regresar.</a>");
	}else{
%>
	<form action="sqlpreview" method="post" target="_top" id="formPreguntas" class="hideprint">
		<input type="hidden" value="<% out.print(_sqlFinal); %>" id="generadorSql_sqlFinal" name="generadorSql_sqlFinal">
		<input type="hidden" value="<% out.print(_objetoatrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
		<input type="hidden" value="<% out.print(_accioninvitar); %>" id="accioninvitar" name="accioninvitar">
        <input type="hidden" value="" id="accionextra" name="accionextra">
        <label>Indique que datos desea revisar:</label><br />
		<select multiple="multiple" id="preguntasdeseadas" name="preguntasdeseadas" size="8">
			<%
				Enumeration _enu = _listadoPreguntas.elements();
				InstanciaPregunta _pregAct = null;
                String _strListadoPreguntasDeseadas  = "";
                while(_enu.hasMoreElements()){
					_pregAct = (InstanciaPregunta)_enu.nextElement();
					out.print("<option value='"+_pregAct.getId()+"'");
                    if(request.getParameter("preguntasdeseadas") != null){
                        String[] _listadoPreguntasDeseadas = request.getParameterValues("preguntasdeseadas");
                        if(_listadoPreguntasDeseadas != null){
                            for(int p = 0; p < _listadoPreguntasDeseadas.length; ++p){
                                String _miParam = _listadoPreguntasDeseadas[p];
                                if(Integer.valueOf(_miParam) == _pregAct.getId()){
                                    out.print(" selected='selected' ");
                                }
                            }
                        }
                    }
                    out.print(">"+_pregAct.getAcronimo()+"</option>");
				}
			%>
		</select><br />
		<input type="submit" value="Revisar" onclick="$('#formPreguntas').attr('action', 'sqlpreview');$('#accionextra').val('');if($('#preguntasdeseadas').attr('value') == ''){alert('Debe indicar las preguntas que desea revisar');return false;}return true;">
	</form>
<%
	if(!_preguntas.isEmpty()){
%>
	<table id="_myTable" class="tablesorter" style="min-width:940px;max-width:940px;width:940px">
		<thead>
			<tr>
			<%
				_enu = _preguntas.elements();
				_pregAct = null;
				while(_enu.hasMoreElements()){
					_pregAct = (InstanciaPregunta)_enu.nextElement();
					out.print("<th title='clic aqu&iacute; para ordenar por: "+_pregAct.getAcronimo()+"'>"+_pregAct.getAcronimo()+"</th>");
				}
			%>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					_enu = _respuestas.elements();
					Respuesta _respAct = null;
					int id_usuario = -1;
					while(_enu.hasMoreElements()){
						//para cada usuario
						_respAct = (Respuesta)_enu.nextElement();
						//caso base... partimos de ningun usuario
						if(id_usuario != _respAct.getElaborado_por()){
							//otro usuario
							id_usuario = _respAct.getElaborado_por();
							if(id_usuario != -1){out.println("</tr><tr>");}
						}else{
							//mismo usuario que el anterior... ya procesado
							continue;
						}
						//para cada pregunta busco su correspondiente respuesta
						Enumeration _enu2 = _preguntas.elements();
						_pregAct = null;

						while(_enu2.hasMoreElements()){
							//para cada pregunta
							_pregAct = (InstanciaPregunta)_enu2.nextElement();
							Enumeration _enuInterno = _respuestas.elements();
							Respuesta _respActInterna = null;
							boolean _respEncon = false;
							boolean concatena = false;
							out.print("<td>");
							while(_enuInterno.hasMoreElements()){
								//para cada respuesta donde cuadre usuario y pregunta
								_respActInterna = (Respuesta)_enuInterno.nextElement();
								if((_respActInterna.getElaborado_por() == id_usuario) && (_respActInterna.getInstanciaPregunta().getId() == _pregAct.getId())){
									_respEncon = true;
									try{
										if(_pregAct.getTipoPregunta() == 1){
											out.print(_respActInterna.getRespuestaCerrada().getRespuesta());
										}else	if(_pregAct.getTipoPregunta() == 2){
											if(concatena){
												out.print("<br />");
											}
											out.print("-&nbsp;"+_respActInterna.getRespuestaCerrada().getRespuesta());
											concatena = true;
										}else	if(_pregAct.getTipoPregunta() == 30 || _pregAct.getTipoPregunta() == 100){
											out.print(_respActInterna.getRespuestaAbiertaTexto());
										}else	if(_pregAct.getTipoPregunta() == 31){
											out.print(_respActInterna.getRespuestaAbiertaInt());
										}else	if(_pregAct.getTipoPregunta() == 32){
											out.print(_respActInterna.getRespuestaAbiertaDouble());
										}else	if(_pregAct.getTipoPregunta() == 33){
											String[] _respDate = _respActInterna.getRespuestaAbiertaDate().toString().split("-");
											out.print(_respDate[2]+"-"+_respDate[1]+"-"+_respDate[0]);
										}
									}catch (Exception e1){out.print("Error cargando esta respuesta");}
									//break;
								}
							}
							if(!_respEncon){
								out.print("&nbsp;");
							}
							out.print("</td>");
						}
					}
				%>
			</tr>
		</tbody>
	</table>
	<% if(_accioninvitar.equals("true")) { %>
	<form action="#" class="hideprint">
		<label>Indique cu&aacute;l es el campo de correo electr&oacute;nico a utilizar para invitar a los participantes:</label><br />
		<select id='email_id_invite' name="email_id_invite" onchange="if(this.value == '-1'){$('#containerParaMensaje').css('display', 'none');}else{$('#containerParaMensaje').css('display', 'block');}">
			<%
            boolean setFirst = true;
            boolean sinEmailInviacion = false;
			_enu = _listadoPreguntas.elements();
			_pregAct = null;
			while(_enu.hasMoreElements()){
				_pregAct = (InstanciaPregunta)_enu.nextElement();
                if(setFirst){
                    if(_pregAct.getPadre().retornaPreguntaComunicacionEmail() == null){
                        out.print("<option value='-1' selected='selected'>Sin indicar email</option>");
                        sinEmailInviacion = true;
                    }else{
                        out.print("<option value='-1'>Sin indicar email.</option>");
                    }
                    setFirst = !setFirst;
                }
				if(_pregAct.isCampo_comunicacion_email()){
					out.print("<option value='"+_pregAct.getId()+"' selected='selected'>"+_pregAct.getAcronimo()+"</option>");
				}else{
					out.print("<option value='"+_pregAct.getId()+"'>"+_pregAct.getAcronimo()+"</option>");
				}
			}
			SimpleDateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			%>
		</select><p />
		<% if(_miObj != null){ %>
        <div id="containerParaMensaje" <%if(sinEmailInviacion){out.print("style='display:none'");}else{out.print("style='display:block'");}%>>
		<label>Mensaje a ser colocado en el correo a enviar:</label><br />
		<textarea rows="10" cols="120" id="email_id_invite_text" name="email_id_invite_text">
			Se le invita cordialmente a participar en <% if(_miObj.getObjetoAsociado().getClass().toString().contains("Censo")){out.print("el censo: ");}else{out.print("la encuesta: ");} out.print(_miObj.getObjeto());%>.<br />
			Iniciando el: <% out.print(_dateFormat.format(_miObj.getFechaInicio()));%> y finaliza el: <% out.print(_dateFormat.format(_miObj.getFechaCierre()));%><br />
		</textarea>
		<script language="JavaScript" type="text/javascript">
	    makeWhizzyWig("email_id_invite_text");
		</script><p /></div>
		<% }else{ %>
			 <input type="hidden" value="" id="email_id_invite_text" name="email_id_invite_text">
		<% } %>
		<button value="Procesar estos usuarios" onclick="window.opener.document.getElementById('id_encapsulador').style.display = 'block';window.opener.document.getElementById('hacerpreview').value='falso';window.opener.document.getElementById('email_id_invite').value=document.getElementById('email_id_invite').value;window.opener.document.getElementById('email_id_invite_text').value=document.getElementById('email_id_invite_text').value;window.opener.document.getElementById('formgenerasql').target = '_top';window.opener.document.getElementById('formgenerasql').submit();window.opener.document.getElementById('formgenerasql').action = '#';window.close();">Aceptar y procesar estos usuarios</button>
		<button value="Cerrar esta ventana" onclick="window.close();">Cancelar y cerrar esta ventana</button>
	</form>
	<% }else if(_accioninvitar.equals("halftrue")) { %>
	<form action="#" class="hideprint">
		<input type="hidden" value="-1" name="email_id_invite" id="email_id_invite">
		<input type="hidden" value="" name="email_id_invite_text" id="email_id_invite_text">
		<button value="Procesar estos usuarios" onclick="window.opener.document.getElementById('id_encapsulador').style.display = 'block';window.opener.document.getElementById('hacerpreview').value='falso';window.opener.document.getElementById('email_id_invite').value=document.getElementById('email_id_invite').value;window.opener.document.getElementById('email_id_invite_text').value=document.getElementById('email_id_invite_text').value;window.opener.document.getElementById('formgenerasql').target = '_top';window.opener.document.getElementById('formgenerasql').submit();window.opener.document.getElementById('formgenerasql').action = '#';window.close();">Aceptar y procesar estos usuarios</button>
		<button value="Cerrar esta ventana" onclick="window.close();">Cancelar y cerrar esta ventana</button>
	</form>
	<% }else{ %>
    <div style="padding-left:10px;float:left;text-align:right" class="hideprint">
        <a href="javascript:void(null);" onclick="$('#formPreguntas').attr('action', 'archivo.pdf');$('#accionextra').val('exportapdf');$('#formPreguntas').submit();"><img height="46" src="comunes/imagenes/pdf.png" alt="Exportar estos datos a PDF" title="Exportar estos datos a PDF"/></a>
        &nbsp;&nbsp;<a href="javascript:void(null);" onclick="$('#formPreguntas').attr('action', 'archivo.docx');$('#accionextra').val('exportaword');$('#formPreguntas').submit();"><img height="46" src="comunes/imagenes/word.png" alt="Exportar estos datos a Word" title="Exportar estos datos a Word"/></a>
        &nbsp;&nbsp;<a href="javascript:void(null);" onclick="$('#formPreguntas').attr('action', 'archivo.xls');$('#accionextra').val('exportaexcel');$('#formPreguntas').submit();"><img height="46" src="comunes/imagenes/excel.png" alt="Exportar estos datos a excel" title="Exportar estos datos a excel"/></a>
		&nbsp;&nbsp;<a href="javascript:void(null);" onclick="$('.qtip').hide();self.print();"><img height="46" src="comunes/imagenes/print.png" alt="Imprimir" title="Imprimir"/></a>
    </div>
	<% } %>
	<br />
</td></tr></table>
<script type="text/javascript">
	$(document).ready(function(){
		//$("#myTable").tablesorter();
	 });
</script>

<%
		}
	}
%>

<p /><button class="hideprint" value="Cerrar esta ventana" onclick="window.close();">Cerrar esta ventana</button>
<%@include file="adminfooter.jsp" %>