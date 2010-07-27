<%@include file="admininicio.jsp" %>

<script type="text/javascript" charset="UTF8">
function revisarEstructura(_select){
	window.open("mostrarinfo.do?accion=mostrarinfodeobjeto&id="+_select, "mostrarInfo" , "width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO");
}

function seleccionaTodo(){
	var _deseadas = document.getElementById("listadoPreguntasDeseadas");
	for(var i=_deseadas.length-1; i>=0; i--){
		_deseadas.options[i].selected = true;
	}
}

function agregarPregunta(){
	var _externas = document.getElementById("listadoPreguntasExternas");
	var _deseadas = document.getElementById("listadoPreguntasDeseadas");

  var selIndex = _externas.selectedIndex;
  if (selIndex != -1){
    for(var i=_externas.length-1; i>=0; i--){
      if(_externas.options[i].selected){
	      var newOpt = new Option(_externas.options[i].text, _externas.options[i].value);
        _deseadas.options[_deseadas.length] = newOpt;
        _externas.options[i] = null;
      }
    }
  }
}

function eliminarPregunta(){
	var _externas = document.getElementById("listadoPreguntasExternas");
	var _deseadas = document.getElementById("listadoPreguntasDeseadas");

  var selIndex = _deseadas.selectedIndex;
  if (selIndex != -1){
    for(var i=_deseadas.length-1; i>=0; i--){
      if(_deseadas.options[i].selected){
	      var newOpt = new Option(_deseadas.options[i].text, _deseadas.options[i].value);
        _externas.options[_externas.length] = newOpt;
        _deseadas.options[i] = null;
      }
    }
  }
}

</script>

<%@include file="adminheader.jsp" %>

<%
String _opcionBase = "armar";
if(request.getParameter("opcionbase")!=null){
	_opcionBase = request.getParameter("opcionbase");
}

String _tipoinstrumento = "";
if(request.getParameter("tipoinstrumento")!=null){
	_tipoinstrumento = request.getParameter("tipoinstrumento");
}

if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}
Objeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (Objeto)request.getAttribute("objetoatrabajar");
}

Vector _objetosExternos = new Vector();
if(request.getAttribute("objetosexternos") != null){
	_objetosExternos = (Vector)request.getAttribute("objetosexternos");
}
Collections.sort(_objetosExternos, new OrdenadorObjetos(OrdenadorObjetos.CLASS));

Objeto objetoexterno = null;
if(request.getAttribute("objetoexterno") != null){
	objetoexterno = (Objeto)request.getAttribute("objetoexterno");
}

Vector _listadoPreguntasExternas = new Vector();
if(request.getAttribute("listadoPreguntasExternas") != null){
	_listadoPreguntasExternas = (Vector)request.getAttribute("listadoPreguntasExternas");
}
Collections.sort(_listadoPreguntasExternas, new OrdenadorInstanciaPreguntas());

String _titulo = "";
String _elemento = "";
if(_tipoinstrumento.equals("estructura")){
	_titulo = "Estructura Base";
	_elemento = "estructura";
}else	if(_tipoinstrumento.equals("relacion")){
		_titulo = "Colecci&oacute;n de Datos o Instrumento";
		_elemento = "colecci&oacute;n de datos";
}else{
	_titulo = "Instrumento";
	_elemento = "instrumento";
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
</script>

<table cellspacing="4" cellpadding="4" class="tablasecundaria">
	<tr>
		<td valign="top" align="left" colspan="3">
			<h2>Importar Preguntas desde <% out.print(_titulo); %></h2>
			<h4><span id="subtitulo" name="subtitulo">
			<%
			if(objetoatrabajar != null && objetoexterno == null){
				out.print("Trabajando con: "+objetoatrabajar.getObjeto());
			%>
			</span></h4>
			<div id="formularioObjeto" name="formularioObjeto">
				<form action="adminobjetos3.do" method="post" name="miforma" id="miforma">
					<input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" name="objetoseleccionado" id="objetoseleccionado">
					<input type="hidden" name="tipoinstrumento" value="<% out.print(_tipoinstrumento);%>">
					<input type="hidden" name="opcionbase" value="<% out.print(_opcionBase);%>">
					<input type="hidden" value="cambiarobjetoexterno" name="accionobjeto" id="accionobjeto">
					<% if(_objetosExternos.size() < 1){
						// se establece el limite 1 porque en ese vector también tenemos la estructura actual, que debe ser obviada.
					%>
					No existen <% out.print(_elemento); %>s para poder obtener sus preguntas.
					<% }else{
						try{
							Enumeration objetosExternos = _objetosExternos.elements();
							Vector _nuevoVector = new Vector();
							Objeto obj;
							while(objetosExternos.hasMoreElements()){
								obj = (Objeto)objetosExternos.nextElement();
								if(obj.getId() == objetoatrabajar.getId()){
									continue;
								}
								if(_tipoinstrumento.equals("estructura")){
									if(!obj.getClass().toString().contains("EstructuraBase")){
										continue;
									}
								}
								_nuevoVector.add(obj);
							}

							%>
							Seleccione de que <% out.print(_elemento);%> se importar&aacute;n las preguntas.<p />
							<select name="objetoseleccionadoexterno" id="objetoseleccionadoexterno" onchange="document.miforma.submit()" multiple="multiple" size="<% out.print(_nuevoVector.size() + 4);%>">
							<%
								String miClase = "";
								String miClasePrint = "";
								objetosExternos = _nuevoVector.elements();
								while(objetosExternos.hasMoreElements()){
									obj = (Objeto)objetosExternos.nextElement();
									//solo se hace lo siguiente para mostrarlos por grupos: censos, encuestas y relaciones
									if(!miClase.equals(obj.getClass().toString())){
										if(!miClase.equals("")){
											out.print("</optgroup>");
										}

										miClase = obj.getClass().toString();

										if(miClase.contains("Censo")){
											miClasePrint = "Censos";
										}
										if(miClase.contains("Encuesta")){
											miClasePrint = "Encuestas";
										}
										if(miClase.contains("EstructuraBase")){
											miClasePrint = "Estructuras Bases";
										}
										if(miClase.contains("Relacion")){
											miClasePrint = "Relaciones";
										}
										%>
										<optgroup label="<% out.print(miClasePrint); %>">
										<%
									}
									out.println("<option value='"+obj.getId()+"'>"+obj.getObjeto()+"&nbsp;&nbsp;</option>");
								}
							}catch(Exception e){}
							%>
								</optgroup>
							</select>
					<% } %>
				</form>
			</div>
			<%
			}else{
				if(objetoexterno == null){
					out.print("No ha seleccionado ninguna estructura.<p />");
				}
			}
			if(objetoexterno != null){ %>
				<a href="#" onclick='$("#id_encapsulador").show("slow");revisarEstructura(<% out.print(objetoexterno.getId()); %>);'>Revisar informaci&oacute;n de <% out.print(objetoexterno.getObjeto()); %></a>
			<% } %>
		</td>
	</tr>
	<tr>
		<td width="45%" style="max-width:45%" valign="top" align="left">
		  <% if(objetoexterno != null){ %>
			<h4>Preguntas Disponibles de <% out.print(objetoexterno.getObjeto()); %>:</h4>
			<select name="listadoPreguntasExternas" id="listadoPreguntasExternas" size="8" style="max-width:400px;width:400px;" multiple>
			<%
			try{
				Enumeration preguntasExternas = _listadoPreguntasExternas.elements();
				InstanciaPregunta pre;
				while(preguntasExternas.hasMoreElements()){
					pre = (InstanciaPregunta)preguntasExternas.nextElement();
					out.println("<option value='"+pre.getId()+"'>"+pre.getTextoPregunta()+"</option>");
				}
			}catch(Exception e){}
			%>
			</select>
			<% } %>
		</td>
		<td width="10%" style="max-width:10%" valign="middle" align="center">
			<% if(objetoexterno != null){ %>
			<img src="comunes/imagenes/next.png" alt="agregar" title="agregar" onclick="agregarPregunta()" width="24"> <p /><br />
			<img src="comunes/imagenes/back.png" alt="eliminar" title="eliminar" onclick="eliminarPregunta()" width="24"> <p />
			<% } %>
		</td>
		<td width="45%" style="max-width:45%" valign="top" align="left">
			<% if(objetoexterno != null){ %>
			<h4>Preguntas que se agregar&aacute;n a <% out.print(objetoatrabajar.getObjeto()); %>:</h4>
			<form action="adminobjetos3.do" method="post" name="formListadoPreguntasDeseadas" id="formListadoPreguntasDeseadas">
				<input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" name="objetoseleccionado" id="objetoseleccionado">
				<input type="hidden" value="agregarPreguntas" name="accion" id="accion">
				<input type="hidden" name="tipoinstrumento" value="<% out.print(_tipoinstrumento);%>">
				<input type="hidden" name="opcionbase" value="<% out.print(_opcionBase);%>">
				<select name="listadoPreguntasDeseadas" id="listadoPreguntasDeseadas" size="8" style="max-width:400px;width:400px;" multiple>
				</select><p />
				<input type="submit" value="Procesar" name="miboton2" id="miboton2" onclick="seleccionaTodo();">
			</form>
			<% } %>
		</td>
	</tr>
</table>
<%@include file="adminfooter.jsp" %>
