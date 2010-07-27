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

<%
Objeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (Objeto)request.getAttribute("objetoatrabajar");
}


%>

<script type="text/javascript" charset="utf-8">
var array_estudios = new Array();

function seleccionar(_elem){
	var _id = _elem.value;
	var _descr = document.getElementById('descripcion');
	var _boto = document.getElementById('enviar');
	_boto.disabled = false;

	_descr.innerHTML = "";

	if(_id == -1){
		_descr.style.display = "none";
		_boto.disabled = true;
	}
	
	//var _span = document.getElementById('span_delete');
	//_span.innerHTML = "";

	for(var i=0;i<array_estudios.length;i++){
	  if(_id == array_estudios[i]['id']){
		  var _temp2 = document.createElement("a");
		  _temp2.alt = "Eliminar Estudio";
		  _temp2.innerHTML = "Haga clic aqu&iacute; para eliminar este estudio";
		  _temp2.title = "Eliminar Estudio";
		  _temp2.href = "generadorestudios?accion=delete&objetoatrabajar=<%out.print(objetoatrabajar.getId());%>&idestudio="+array_estudios[i]['id'];
		  _temp2.onclick = function(){
			  return confirm("Realmente desea eliminar este estudio? esta accion no puede devolverse.");
		  }
		  var _mitemp = document.createElement("div");
		  _mitemp.style.display = "block";
		  _mitemp.innerHTML = "<h4>"+array_estudios[i]['titulo']+"</h4>"+array_estudios[i]['descripcion'];
		  _descr.appendChild(_mitemp);
		  var _espacio = document.createElement("br");
		  _descr.appendChild(_espacio);
		  _descr.appendChild(_temp2);
		  _descr.style.display = "block";
	  }
	}
	$('a[title]').qtip({
		style: {
			name: 'green',
			tip: true,
			color: 'black'
		},
		border: {
			 width: 7,
			 radius: 5,
			 color: '#A2D959'
		},
		hide:{ when: { event: 'mouseout'}},
		position: {
			corner: {
			 target: 'topMiddle',
			 tooltip: 'bottomMiddle'
			}
		 }
	});
}

</script>

<%@include file="adminheader.jsp" %>

<%

if (request.getAttribute("resultado") != null) {
	out.println(request.getAttribute("resultado") + "<br />");
}

Vector _listaDeEstudios = new Vector();
if(request.getAttribute("listadoEstudios") != null){
	_listaDeEstudios = (Vector)request.getAttribute("listadoEstudios");
	Collections.sort(_listaDeEstudios, new OrdenadorEstudios(OrdenadorEstudios.TITULO));
}
	
if(objetoatrabajar != null){
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

String _opcionBase = "crear";
if(request.getParameter("opcionbase")!=null){
	_opcionBase = request.getParameter("opcionbase");
}

String _titulo = "";	
if(_opcionBase.equals("crear")){
	_titulo = "Crear Estudio";
}else if(_opcionBase.equals("modificar")){
	_titulo = "Modificar Estudio";
}else if(_opcionBase.equals("eliminar")){
	_titulo = "Eliminar Estudio";
}else if(_opcionBase.equals("revisar")){
	_titulo = "Revisar Estudio";
}
%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#link_estudios").css("color","red");
	});
</script>

<%
if(objetoatrabajar != null){
	if(!_listaDeEstudios.isEmpty()){
		Enumeration _enu = _listaDeEstudios.elements();
		Estudio _est = null;
		%>
		<table cellspacing="4" cellpadding="4" class='tablasecundaria'>
			<tr>
				<td valign="top">
					<h2><% out.print(_titulo); %></h2>
					<h4>Instrumento Sobre el que se Trabaja:<br /><% out.print(objetoatrabajar.getObjeto());%></h4>
					<label>Seleccione un estudio</label>
					<form action="generadorestudios" method="post">
						<input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
						<input type="hidden" value="seleccionarestudio" id="accion" name="accion">
						<input type="hidden" value="<% out.print(request.getParameter("opcionbase")); %>" id="opcionbase" name="opcionbase">
						<select id="idestudio" name="idestudio" onchange="parentNode.submit();" multiple="multiple" size="<% out.print(_listaDeEstudios.size() + 1); %>">
							<%
							while(_enu.hasMoreElements()){   
								_est = (Estudio)_enu.nextElement();
								out.println("<option value='"+_est.getId()+"'>"+_est.getTitulo()+"&nbsp;&nbsp;</option>");
							}
							%>
						</select>
						<!--// <input type="submit" value="<% out.print(_titulo);%>" id="enviar" name="enviar" disabled="true"> //-->
					</form>
				</td>
		</tr>
		<tr>
			<td>
				<div id="descripcion" name="descripcion" style="border: 1px solid darkslategray; padding: 10px;width:500px; display:none"></div>
			</td>
		</tr>
		</table>
		<%
	}else{
		out.println("No existen estudios asociados a la estructura.");
	}
}else{
	out.println("No se indic&oacute; una estructura con la que se pueda trabajar.");
}
%>

<%@include file="adminfooter.jsp" %>
