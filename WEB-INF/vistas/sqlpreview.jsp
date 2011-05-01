<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
				<img src="comunes/imagenes/siagcee_logo.png" alt="">
			</td>
		</tr>
		<tr>
			<td valign="top" align="left">
				<table cellpadding="4" cellspacing="4" class="tablasecundariatitulo" style="width:950px;min-width:950px;max-width:950px;">
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
	_listadoPreguntas = (Vector)_objetoatrabajar.getObjetoAsociado().getPreguntas();
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
	<form action="sqlpreview" method="post" target="_top" id="formPreguntas">
		<input type="hidden" value="<% out.print(_sqlFinal); %>" id="generadorSql_sqlFinal" name="generadorSql_sqlFinal">
		<input type="hidden" value="<% out.print(_objetoatrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
		<input type="hidden" value="<% out.print(_accioninvitar); %>" id="accioninvitar" name="accioninvitar">
        <input type="hidden" value="" id="accionextra" name="accionextra">
        <label>Indique que preguntas desea revisar:</label><br />
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
		<input type="submit" value="Revisar" onclick="$('#accionextra').val('');if($('#preguntasdeseadas').attr('value') == ''){alert('Debe indicar las preguntas que desea revisar');return false;}return true;">
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
				out.print("<th title='clic aqu&iacute; para ordenar por: "+_pregAct.getTextoPregunta()+"' style='max-width:140px;'>"+_pregAct.getTextoPregunta()+"</th>");
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
					while(_enuInterno.hasMoreElements()){
						//para cada respuesta donde cuadre usuario y pregunta
						_respActInterna = (Respuesta)_enuInterno.nextElement();
						if((_respActInterna.getElaborado_por() == id_usuario) && (_respActInterna.getInstanciaPregunta().getId() == _pregAct.getId())){
							_respEncon = true;
							try{
								if(_pregAct.getTipoPregunta() < 30){
									out.print("<td>"+_respActInterna.getRespuestaCerrada().getRespuesta()+"</td>");
								}else	if(_pregAct.getTipoPregunta() == 30){
									out.print("<td>"+_respActInterna.getRespuestaAbiertaTexto()+"</td>");
								}else	if(_pregAct.getTipoPregunta() == 31){
									out.print("<td>"+_respActInterna.getRespuestaAbiertaInt()+"</td>");
								}else	if(_pregAct.getTipoPregunta() == 32){
									out.print("<td>"+_respActInterna.getRespuestaAbiertaDouble()+"</td>");
								}else	if(_pregAct.getTipoPregunta() == 33){
									String[] _respDate = _respActInterna.getRespuestaAbiertaDate().toString().split("-");
									out.print("<td>"+_respDate[2]+"-"+_respDate[1]+"-"+_respDate[0]+"</td>");
								}
							}catch (Exception e1){out.print("<td>Error cargando esta respuesta</td>");}
							break;
						}
					}
					if(!_respEncon){
						out.print("<td>No Sabe / No Responde</td>");
					}
				}
			}
		%>
	</tr>
	</tbody>
	</table>
	<% if(_accioninvitar.equals("true")) {%>
	<form action="#">
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
		<button value="Procesar estos usuarios" onclick="window.opener.document.getElementById('id_encapsulador').style.display = 'block';window.opener.document.getElementById('hacerpreview').value='falso';window.opener.document.getElementById('email_id_invite').value=document.getElementById('email_id_invite').value;window.opener.document.getElementById('email_id_invite_text').value=document.getElementById('email_id_invite_text').value;window.opener.document.getElementById('formgenerasql').target = '_top';window.opener.document.getElementById('formgenerasql').submit();window.close();">Aceptar y procesar estos usuarios</button>
		<button value="Cerrar esta ventana" onclick="window.close();">Cancelar y cerrar esta ventana</button>
	</form>		
	<% }else{ %>
	<button value="Cerrar esta ventana" onclick="window.close();">Cerrar esta ventana</button>
    <div style="padding-right:50px;float:right;text-align:right"><a href="javascript:void(null);" onclick="$('#accionextra').val('exportaexcel');$('#formPreguntas').submit();"><img height="32" src="comunes/imagenes/excel.png" alt="Exportar estos datos a excel" title="Exportar estos datos a excel"/></a></div>
	<% } %>
	<br />
</td></tr></table>			

<script type="text/javascript">
	$(document).ready(function(){
		$("#myTable").tablesorter();
	 });
</script>

<%
		}
	}
%>

<%@include file="adminfooter.jsp" %>