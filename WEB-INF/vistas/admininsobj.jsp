<%@page session="true" import="com.siagcee.logic.InstanciaObjeto" %>
<%@ page import="com.siagcee.logic.OrdenadorInstanciaObjetos" %>
<%@ page import="com.siagcee.logic.Relacion" %>
<%@ page import="com.siagcee.logic.UtilidadesVarias" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@include file="admininicio.jsp" %>

<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 14/12/2009
 * Hora: 09:52:10 AM
-->

<script type="text/javascript" charset="UTF8">

function revisarOpciones(elem){
	if(elem.value != -1){
		window.location = elem.value;
	}
}

</script>
<%@include file="adminheader.jsp" %>
<%

Vector _miIns = null;
if(request.getAttribute("objetosInstanciados") != null){
	_miIns = (Vector)request.getAttribute("objetosInstanciados");
	Collections.sort(_miIns, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.FECHA_CIERRE_INV));
}

String _mostrarOpcion = "todos";
if(request.getParameter("opcionbase") != null){
	_mostrarOpcion = (String)request.getParameter("opcionbase");
}
if(request.getAttribute("opcionbase") != null){
	_mostrarOpcion = (String)request.getAttribute("opcionbase");
}

int _hightlight = -1;
if(request.getAttribute("highlight") != null){
	_hightlight = (Integer)request.getAttribute("highlight");
}
%>
<script type="text/javascript">
	$(document).ready(function(){
	<% if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){%>
		$("#link_instrumentos_generados").css("color","red");
	<% }else{ %>
		$("#link_estudios").css("color","red");
	<% } %>
	});
</script>

<table class="tablasecundaria" cellpadding="4" cellspacing="4">
	<tr>
		<td>
			<h2>Instrumentos Publicados</h2>

<%
boolean todoVacio = true;
if(_miIns != null){
	Enumeration _misInstanciados = _miIns.elements();
	InstanciaObjeto miObj;
	boolean vacio = true;
	boolean primerFinalizado = true;
	boolean primerPendiente = true;
	boolean primerEjecucion = true;
	boolean primerColeccion = true;

	sesion.setAttribute("retornoDireccion", "admininsobj.do");

	//para que pueda hacer las comparaciones es necesario formatear la fecha y comparar contra String. el tipo Date al realizar
	//comparaciones toma un valor Long que no coincide con la BD debido a que varia con la hora
	//del computador. mientras el dato que viene de BD se mantiene estático. Así para un mismo día conseguimos datos no deseados.
	SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //formatear la fecha para comprobacion interna de la pagina
	SimpleDateFormat _dateFormatVisible = new SimpleDateFormat("dd/MM/yyyy");  //formatear la fecha para la visualizacion del usuario
	Date _hoy = new Date();

	Vector misRelaciones = new Vector();
	Vector misFinalizados = new Vector();
	Vector misPendientes = new Vector();
	Vector misEnEjecucion = new Vector();
	Vector misEliminados = new Vector();

	while(_misInstanciados.hasMoreElements()){
		miObj = (InstanciaObjeto)_misInstanciados.nextElement();
		if(miObj.isEliminado()){
		  misEliminados.add(miObj);
		}else{
			if(_dateFormat.format(_hoy).compareTo(_dateFormat.format(miObj.getFechaInicio())) < 0){
				misPendientes.add(miObj);
			}else if(_dateFormat.format(_hoy).compareTo(_dateFormat.format(miObj.getFechaCierre())) < 0){
				misEnEjecucion.add(miObj);
			}else{
				//si es una Relacion va al final.
				if(miObj.getObjetoAsociado().getClass().toString().equals(Relacion.class.toString())){
					misRelaciones.add(miObj);
					continue;
				}
				misFinalizados.add(miObj);
			}
		}
	}

	Collections.sort(misEnEjecucion, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.FECHA_INICIO));
	Collections.sort(misFinalizados, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.FECHA_INICIO));
	Collections.sort(misPendientes, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.FECHA_INICIO));
	Collections.sort(misEliminados, new OrdenadorInstanciaObjetos(OrdenadorInstanciaObjetos.FECHA_INICIO));

	if(_mostrarOpcion.equals("todos") || _mostrarOpcion.equals("pendientes")){
		_misInstanciados = misPendientes.elements();
		if(misPendientes.isEmpty() && _mostrarOpcion.equals("pendientes")){
		  out.println("No existen instrumentos pendientes.");
		}
		while(_misInstanciados.hasMoreElements()){
			miObj = (InstanciaObjeto)_misInstanciados.nextElement();
			vacio = false;
			if(primerPendiente){
				primerPendiente = false;
				out.println("<h4>Instrumentos Pendientes</h4>");
				%>
				<table class="reporte" cellpadding="4" cellspacing="4" border="1" style="max-width:950px;width:950px;" width="950">
					<tr class="reporteTR">
						<th style="max-width:70px;" width="70" valign="top">
							Iniciar&aacute;
						</th>
						<th style="max-width:70px;" width="70" valign="top">
							Finalizar&aacute;
						</th>
						<th valign="top" width="100%">
							Instrumento
						</th>
						<% if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){%>
						<th style="max-width:50px;" valign="top" width="50">
							Detalle
						</th>
						<th style="max-width:175px;" valign="top" width="175">
							Invitar a Nuevos Usuarios
						</th>
						<th style="max-width:65px;" valign="top" width="65">
							Modificar
						</th>
						<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
						<th style="max-width:45px;" valign="top" width="45">
							Borrar
						</th>
						<% } %>
                        <!-- <th style="max-width:50px;" valign="top" width="50">
                            Enlace
                        </th> //-->
						<th style="max-width:50px;" valign="top" width="50">
							Carga desde Excel
						</th>
						<th style="max-width:60px;" valign="top" width="60">
							Campos Editables
						</th>
						<th style="max-width:55px;" valign="top" width="55">
							Aplicar Estudio
						</th>
						<% }else{ %>
						<th style="max-width:70px;" valign="top" width="70">
							Seleccionar
						</th>
						<% } %>
					</tr>
				<%
			}
			String ini;
			String fin;
			try{
				ini = _dateFormatVisible.format(miObj.getFechaInicio());
			}catch(Exception e){
				e.printStackTrace();
				ini = _dateFormatVisible.format(_hoy);
			}
			try{
				fin = _dateFormatVisible.format(miObj.getFechaCierre());
			}catch(Exception e){
				e.printStackTrace();
				fin = _dateFormatVisible.format(_hoy);
			}

			if((_hightlight > -1) && (_hightlight == miObj.getId())){
				out.println("<tr class='selectedTR'><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}else{
				out.println("<tr><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}
			if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){
				out.println("<td align='center'><img height='24' src='comunes/imagenes/file-review.png' title='Revisar Preguntas del Instrumento' alt='Revisar Preguntas del Instrumento' onclick='window.open(\"mostrarinfo.do?accion=mostrarinfodeobjeto&id="+miObj.getObjetoAsociado().getId()+"\", \"mostrarInfo\" , \"width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO\");'></td>");
				if(_dateFormat.format(_hoy).compareTo(miObj.getFechaCierre().toString()) < 0){
					out.println("<td align='center'><select name='invitando"+miObj.getId()+"' id='invitando"+miObj.getId()+"' onchange='revisarOpciones(this);'><option value='-1'>Seleccione...</option><option value='admininsobj.do?accioninvitar=true&invitar="+miObj.getId()+"'>De otro instrumento</option>");
					if(miObj.getAcceso() == 0){
						out.println("<option value='correos_a_invitar.do?_hightlight="+miObj.getId()+"'>Especificando emails</option>");
					}
					out.println("</select></td>");
				}
				out.println("<td align='center'><a href='#'><img src='comunes/imagenes/refresh.png' onclick='if(confirm(\"Desea modificar el instrumento:\\n"+miObj.getObjeto()+"?\")){window.location=\"implementobjeto.do?solo="+miObj.getId()+"\";return true;}else{return false;}' height='24' alt='Modifica este instrumento' title='Modifica este instrumento' /></a></td>");
				if(admin.getTipoUsuario().equals("superadministrador")){
					out.println("<td align='center'><a href='#'><img src='comunes/imagenes/delete.png' onclick='if(confirm(\"Seguro que desea eliminar el instrumento:\\n"+miObj.getObjeto()+"\\n y todas las respuestas asociadas?.\")){window.location=\"admininsobj.do?opcionbase="+_mostrarOpcion+"&eliminar="+miObj.getId()+"\";}else{return false;}' height='24' alt='Eliminar este instrumento y todas las respuestas que posea' title='Eliminar este instrumento y todas las respuestas que posea' /></a></td>");
				}
				//out.println("<td align='center'><a href='indexadminmenu.do?desdeindex=false&opcionprincipal=estudios&estructura="+miObj.getObjetoAsociado().getId()+"'><img src='comunes/imagenes/barchart.png' alt='Administrar Estudios' title='Administrar Estudios' height='24' /></a></td>");
				//out.println("<td align='center'><a href='"+UtilidadesVarias.dominioWeb+"enlace?"+miObj.getIdPublico()+"' target='_blank'><img height='24' title='"+UtilidadesVarias.dominioWeb+"enlace?"+miObj.getIdPublico()+"' alt='"+UtilidadesVarias.dominioWeb+"enlace?"+miObj.getIdPublico()+"' src='comunes/imagenes/world_link.png'></a></td>");
				out.println("<td align='center'><a href='subirexcel?objetoatrabajar="+miObj.getId()+"'><img height='24' title='' alt='Cargar datos desde excel' src='comunes/imagenes/excel.png'></a></td>");
				out.println("<td align='center'><a href='seteditables?objetoatrabajar="+miObj.getId()+"'><img height='24' title='' alt='Establecer campos editables y opcionales para el encuestado' src='comunes/imagenes/modificar.png'></td>");
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Aplicar Estudio' title='Aplicar Estudio' height='24' /></a></td>");
			}else{
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Seleccionar' title='Seleccionar' height='24' /></a></td>");
			}
			out.println("</tr>");
			todoVacio=false;
		}
		if(!vacio){
			out.println("</table>");
			vacio = true;
		}
	}

	if(_mostrarOpcion.equals("todos") || _mostrarOpcion.equals("enejecucion")){
		_misInstanciados = misEnEjecucion.elements();
		if(misEnEjecucion.isEmpty() && _mostrarOpcion.equals("enejecucion")){
		  out.println("No existen instrumentos en ejecuci&oacute;n.");
		}
		while(_misInstanciados.hasMoreElements()){
			miObj = (InstanciaObjeto)_misInstanciados.nextElement();
			vacio = false;
			if(primerEjecucion){
				primerEjecucion = false;
				out.println("<h4>Instrumentos En Ejecuci&oacute;n</h4>");
				%>
				<table class="reporte" cellpadding="4" cellspacing="4" border="1" style="max-width:950px;width:950px;" width="950">
					<tr class="reporteTR">
						<th style="max-width:70px;" width="70" valign="top">
							Iniciado
						</th>
						<th style="max-width:70px;" width="70" valign="top">
							Finalizar&aacute;
						</th>
						<th valign="top" width="100%">
							Instrumento
						</th>
						<% if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){%>
						<th style="max-width:50px;" valign="top" width="50">
							Detalle
						</th>
						<th style="max-width:175px;" valign="top" width="175">
							Invitar a Nuevos Usuarios
						</th>
						<th style="max-width:65px;" valign="top" width="65">
							Modificar
						</th>
						<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
						<th style="max-width:45px;" valign="top" width="45">
							Borrar
						</th>
						<% } %>
                        <th style="max-width:50px;" valign="top" width="50">
                            Enlace
                        </th>
						<!-- <th style="max-width:50px;" valign="top" width="50">
							Carga desde Excel
						</th>
						<th style="max-width:50px;" valign="top" width="50">
							Campos Editables
						</th>//-->
						<th style="max-width:55px;" valign="top" width="55">
							Revisar Datos
						</th>
						<th style="max-width:55px;" valign="top" width="55">
							Aplicar Estudio
						</th>
						<% }else{ %>
						<th style="max-width:70px;" valign="top" width="70">
							Seleccionar
						</th>
						<% } %>
					</tr>
				<%
			}
			String ini;
			String fin;
			try{
				ini = _dateFormatVisible.format(miObj.getFechaInicio());
			}catch(Exception e){
				e.printStackTrace();
				ini = _dateFormatVisible.format(_hoy);
			}
			try{
				fin = _dateFormatVisible.format(miObj.getFechaCierre());
			}catch(Exception e){
				e.printStackTrace();
				fin = _dateFormatVisible.format(_hoy);
			}

			if((_hightlight > -1) && (_hightlight == miObj.getId())){
				out.println("<tr class='selectedTR'><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}else{
				out.println("<tr><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}
			if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){
				out.println("<td align='center'><img height='24' src='comunes/imagenes/file-review.png' title='Revisar Preguntas del Instrumento' alt='Revisar Preguntas del Instrumento' onclick='window.open(\"mostrarinfo.do?accion=mostrarinfodeobjeto&id="+miObj.getObjetoAsociado().getId()+"\", \"mostrarInfo\" , \"width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO\");'></td>");
				if(_dateFormat.format(_hoy).compareTo(miObj.getFechaCierre().toString()) < 0){
					out.println("<td align='center'><select name='invitando"+miObj.getId()+"' id='invitando"+miObj.getId()+"' onchange='revisarOpciones(this);'><option value='-1'>Seleccione...</option><option value='admininsobj.do?accioninvitar=true&invitar="+miObj.getId()+"'>De otro instrumento</option>");
					if(miObj.getAcceso() == 0){
						out.println("<option value='correos_a_invitar.do?_hightlight="+miObj.getId()+"'>Especificando emails</option>");
					}
					out.println("</select></td>");
				}
				out.println("<td align='center'><a href='#'><img src='comunes/imagenes/refresh.png' onclick='if(confirm(\"Desea modificar el instrumento:\\n"+miObj.getObjeto()+"?\")){window.location=\"implementobjeto.do?solo="+miObj.getId()+"\";return true;}else{return false;}' height='24' alt='Modifica este instrumento' title='Modifica este instrumento' /></a></td>");
				if(admin.getTipoUsuario().equals("superadministrador")){
					out.println("<td align='center'><a href='#'><img src='comunes/imagenes/delete.png' onclick='if(confirm(\"Seguro que desea eliminar el instrumento:\\n"+miObj.getObjeto()+"\\n y todas las respuestas asociadas?.\")){window.location=\"admininsobj.do?opcionbase="+_mostrarOpcion+"&eliminar="+miObj.getId()+"\";}else{return false;}' height='24' alt='Eliminar este instrumento y todas las respuestas que posea' title='Eliminar este instrumento y todas las respuestas que posea' /></a></td>");
				}
				//out.println("<td align='center'><a href='indexadminmenu.do?desdeindex=false&opcionprincipal=estudios&estructura="+miObj.getObjetoAsociado().getId()+"'><img src='comunes/imagenes/barchart.png' alt='Administrar Estudios' title='Administrar Estudios' height='24' /></a></td>");
				out.println("<td align='center'><a href='"+UtilidadesVarias.dominioWeb+"enlace?"+miObj.getIdPublico()+"' target='_blank'><img height='24' title='"+UtilidadesVarias.dominioWeb+"enlace?"+miObj.getIdPublico()+"' alt='"+UtilidadesVarias.dominioWeb+"enlace?"+miObj.getIdPublico()+"' src='comunes/imagenes/world_link.png'></a></td>");
				//out.println("<td align='center'><a href='subirexcel?objetoatrabajar="+miObj.getId()+"'><img height='24' title='' alt='Cargar datos desde excel' src='comunes/imagenes/excel.png'></a></td>");
				//out.println("<td align='center'><a href='seteditables?objetoatrabajar="+miObj.getId()+"'><img height='24' title='' alt='Establecer campos editables y opcionales para el encuestado' src='comunes/imagenes/modificar.png'></td>");
				out.println("<td align='center'><a href='admininsobj.do?objetoatrabajar="+miObj.getId()+"&accion=seleccionar&soloeste=true&accioninvitar=false&invitar="+miObj.getId()+"'><img src='comunes/imagenes/tabulador.png' alt='Revisar todas las respuestas' title='Revisar todas las respuestas' height='24' /></a></td>");
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Aplicar Estudio' title='Aplicar Estudio' height='24' /></a></td>");
			}else{
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Seleccionar' title='Seleccionar' height='24' /></a></td>");
			}
			out.println("</tr>");
			todoVacio=false;
		}
		if(!vacio){
			out.println("</table>");
			vacio = true;
		}
	}

	if(_mostrarOpcion.equals("todos") || _mostrarOpcion.equals("finalizados")){
		_misInstanciados = misFinalizados.elements();
		if(misFinalizados.isEmpty() && _mostrarOpcion.equals("finalizados")){
		  out.println("No existen instrumentos finalizados.");
		}
		while(_misInstanciados.hasMoreElements()){
			miObj = (InstanciaObjeto)_misInstanciados.nextElement();
			vacio = false;
			if(primerFinalizado){
				primerFinalizado = false;
				out.println("<h4>Instrumentos Finalizados</h4>");
				%>
				<table class="reporte" id='tabla_finalizado' cellpadding="4" cellspacing="4" border="1" style="max-width:950px;width:950px;" width="950">
					<tr class="reporteTR">
						<th style="max-width:70px;" width="70" valign="top">
							Iniciado
						</th>
						<th style="max-width:70px;" width="70" valign="top">
							Finaliz&oacute;
						</th>
						<th valign="top" width="100%">
							Instrumento
						</th>
						<% if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){%>
						<th style="max-width:50px;" valign="top" width="50">
							Detalle
						</th>
						<th style="max-width:60px;" valign="top" width="45">
							Reenviar
						</th>
						<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
						<th style="max-width:45px;" valign="top" width="45">
							Borrar
						</th>
						<% } %>
						<th style="max-width:55px;" valign="top" width="55">
							Revisar Datos
						</th>
						<th style="max-width:55px;" valign="top" width="55">
							Aplicar Estudio
						</th>
						<% }else{ %>
						<th style="max-width:70px;" valign="top" width="70">
							Seleccionar
						</th>
						<% } %>
						<!-- <th style="max-width:50px;" valign="top" width="50">
							Carga desde Excel
						</th> //-->
					</tr>
				<%
			}
			String ini;
			String fin;
			try{
				ini = _dateFormatVisible.format(miObj.getFechaInicio());
			}catch(Exception e){
				e.printStackTrace();
				ini = _dateFormatVisible.format(_hoy);
			}
			try{
				fin = _dateFormatVisible.format(miObj.getFechaCierre());
			}catch(Exception e){
				e.printStackTrace();
				fin = _dateFormatVisible.format(_hoy);
			}

			if((_hightlight > -1) && (_hightlight == miObj.getId())){
				out.println("<tr class='selectedTR'><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}else{
				out.println("<tr><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}
			if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){
				out.println("<td align='center'><img height='24' src='comunes/imagenes/file-review.png' title='Revisar Preguntas del Instrumento' alt='Revisar Preguntas del Instrumento' onclick='window.open(\"mostrarinfo.do?accion=mostrarinfodeobjeto&id="+miObj.getObjetoAsociado().getId()+"\", \"mostrarInfo\" , \"width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO\");'></td>");
				if(_dateFormat.format(_hoy).compareTo(miObj.getFechaCierre().toString()) < 0){
					out.println("<td align='center'><select name='invitando"+miObj.getId()+"' id='invitando"+miObj.getId()+"' onchange='revisarOpciones(this);'><option value='-1'>Seleccione...</option><option value='admininsobj.do?accioninvitar=true&invitar="+miObj.getId()+"'>De otro instrumento</option>");
					if(miObj.getAcceso() == 0){
						out.println("<option value='correos_a_invitar.do?_hightlight="+miObj.getId()+"'>Especificando emails</option>");
					}
					out.println("</select></td>");
				}
				if(_dateFormat.format(_hoy).compareTo(miObj.getFechaCierre().toString()) >= 0){
					out.println("<td align='center'><a href='#'><img src='comunes/imagenes/refresh.png' onclick='if(confirm(\"Desea volver a publicar el instrumento:\\n"+miObj.getObjeto()+" y actualizar sus datos?\")){window.location=\"implementobjeto.do?solo="+miObj.getId()+"\";return true;}else{return false;}' height='24' alt='Republicar este instrumento y actualizar sus datos' title='Republicar este instrumento y actualizar sus datos' /></a></td>");
				}else{
					out.println("<td align='center'>-</td>");
				}
				if(admin.getTipoUsuario().equals("superadministrador")){
					out.println("<td align='center'><a href='#'><img src='comunes/imagenes/delete.png' onclick='if(confirm(\"Seguro que desea eliminar el instrumento:\\n"+miObj.getObjeto()+"\\n y todas las respuestas asociadas?.\")){window.location=\"admininsobj.do?opcionbase="+_mostrarOpcion+"&eliminar="+miObj.getId()+"\";}else{return false;}' height='24' alt='Eliminar este instrumento y todas las respuestas que posea' title='Eliminar este instrumento y todas las respuestas que posea' /></a></td>");
				}
				//out.println("<td align='center'><a href='indexadminmenu.do?desdeindex=false&opcionprincipal=estudios&estructura="+miObj.getObjetoAsociado().getId()+"'><img src='comunes/imagenes/barchart.png' alt='Administrar Estudios' title='Administrar Estudios' height='24' /></a></td>");
				out.println("<td align='center'><a href='admininsobj.do?objetoatrabajar="+miObj.getId()+"&accion=seleccionar&soloeste=true&accioninvitar=false&invitar="+miObj.getId()+"'><img src='comunes/imagenes/tabulador.png' alt='Revisar todas las respuestas' title='Revisar todas las respuestas' height='24' /></a></td>");
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Aplicar Estudio' title='Aplicar Estudio' height='24' /></a></td>");
			}else{
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Seleccionar' title='Seleccionar' height='24' /></a></td>");
			}
			//out.println("<td align='center'><a href='subirexcel?objetoatrabajar="+miObj.getId()+"'><img height='24' title='' alt='Cargar datos desde excel' src='comunes/imagenes/excel.png'></a></td>");
			out.println("</tr>");
			todoVacio=false;
		}
		if(!vacio){
			out.println("</table>");
			vacio = true;
		}
	}

	if((_mostrarOpcion.equals("todos") || _mostrarOpcion.equals("relaciones"))){
		_misInstanciados = misRelaciones.elements();
		if(misRelaciones.isEmpty() && _mostrarOpcion.equals("todos")){
		  //out.println("No existen colecciones de datos.");
		}
		while(_misInstanciados.hasMoreElements()){
			miObj = (InstanciaObjeto)_misInstanciados.nextElement();
			vacio = false;
			if(primerColeccion){
				primerColeccion = false;
				out.println("<h4>Colecciones de datos</h4>");
				%>
				<table class="reporte" id='tabla_relaciones' cellpadding="4" cellspacing="4" border="1" style="max-width:950px;width:950px;" width="950">
					<tr class="reporteTR">
						<th valign="top" width="100%">
							Colecci&oacute;n
						</th>
						<% if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){%>
						<th style="max-width:50px;" valign="top" width="50">
							Detalle
						</th>
						<th style="max-width:60px;" valign="top" width="45">
							Publicar
						</th>
						<th style="max-width:45px;" valign="top" width="45">
							Borrar
						</th>
						<!-- <th style="max-width:50px;" valign="top" width="50">
							Carga desde Excel
						</th> //-->
						<th style="max-width:55px;" valign="top" width="55">
							Revisar Datos
						</th>
						<th style="max-width:55px;" valign="top" width="55">
							Aplicar Estudio
						</th>
						<% }else{ %>
						<th style="max-width:70px;" valign="top" width="70">
							Seleccionar
						</th>
						<% } %>
					</tr>
				<%
			}

			if((_hightlight > -1) && (_hightlight == miObj.getId())){
				out.println("<tr class='selectedTR'><td valign='top'>"+miObj.getObjeto()+"</td>");
			}else{
				out.println("<tr><td valign='top'>"+miObj.getObjeto()+"</td>");
			}
			if((request.getParameter("soloaplicar") == null) || !request.getParameter("soloaplicar").equals("true")){
				out.println("<td align='center'><img height='24' src='comunes/imagenes/file-review.png' title='Revisar Preguntas de la Colecci&oacute;n' alt='Revisar Preguntas de la Colecci&oacute;n' onclick='window.open(\"mostrarinfo.do?accion=mostrarinfodeobjeto&id="+miObj.getObjetoAsociado().getId()+"\", \"mostrarInfo\" , \"width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO\");'></td>");
				out.println("<td align='center'><a href='#'><img src='comunes/imagenes/refresh.png' onclick='if(confirm(\"Desea publicar la colecci&oacute;n :\\n"+miObj.getObjeto()+" y actualizar sus datos?\")){window.location=\"implementobjeto.do?solo="+miObj.getId()+"\";return true;}else{return false;}' height='24' alt='Publicar esta colecci&oacute;n y actualizar todas las respuestas que posea' title='Publicar esta colecci&oacute;n y actualizar todas las respuestas que posea' /></a></td>");
				out.println("<td align='center'><a href='#'><img src='comunes/imagenes/delete.png' onclick='if(confirm(\"Seguro que desea eliminar la colecci&oacute;n:\\n"+miObj.getObjeto()+"\\n y todas las respuestas asociadas?.\")){window.location=\"admininsobj.do?opcionbase="+_mostrarOpcion+"&eliminar="+miObj.getId()+"\";}else{return false;}' height='24' alt='Eliminar esta colecci&oacute;n y todas las respuestas que posea' title='Eliminar esta colecci&oacute;n y todas las respuestas que posea' /></a></td>");
				//out.println("<td align='center'><a href='indexadminmenu.do?desdeindex=false&opcionprincipal=estudios&estructura="+miObj.getObjetoAsociado().getId()+"'><img src='comunes/imagenes/barchart.png' alt='Administrar Estudios' title='Administrar Estudios' height='24' /></a></td>");
				//out.println("<td align='center'><a href='subirexcel?objetoatrabajar="+miObj.getId()+"'><img height='24' title='' alt='Cargar datos desde excel' src='comunes/imagenes/excel.png'></a></td>");
				out.println("<td align='center'><a href='admininsobj.do?objetoatrabajar="+miObj.getId()+"&accion=seleccionar&soloeste=true&accioninvitar=false&invitar="+miObj.getId()+"'><img src='comunes/imagenes/tabulador.png' alt='Revisar todas las respuestas' title='Revisar todas las respuestas' height='24' /></a></td>");
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Aplicar Estudio' title='Aplicar Estudio' height='24' /></a></td>");
			}else{
				out.println("<td align='center'><a href='aplicadorestudios?objetoatrabajar="+miObj.getId()+"'><img src='comunes/imagenes/piechart.png' alt='Seleccionar' title='Seleccionar' height='24' /></a></td>");
			}
			out.println("</tr>");
			todoVacio=false;
		}
		if(!vacio){
			out.println("</table>");
			vacio = true;
		}
	}

	if(_mostrarOpcion.equals("eliminados")){
		_misInstanciados = misEliminados.elements();
		if(misEliminados.isEmpty()){
		  out.println("No existen instrumentos eliminados.");
		}
		while(_misInstanciados.hasMoreElements()){
			miObj = (InstanciaObjeto)_misInstanciados.nextElement();
			vacio = false;
			if(primerFinalizado){
				primerFinalizado = false;
				out.println("<h4>Instrumentos Eliminados</h4>");
				%>
				<table class="reporte" id='tabla_finalizado' cellpadding="4" cellspacing="4" border="1" style="max-width:950px;width:950px;" width="950">
					<tr class="reporteTR">
						<th style="max-width:70px;" width="70" valign="top">
							Iniciado
						</th>
						<th style="max-width:70px;" width="70" valign="top">
							Finaliz&oacute;
						</th>
						<th valign="top">
							Instrumento
						</th>
						<th style="max-width:50px;" valign="top" width="50">
							Detalle
						</th>
						<th style="max-width:70px;" valign="top" width="45">
							Recuperar
						</th>
						<th style="max-width:55px;" valign="top" width="55">
							Revisar Datos
						</th>
                        <!--
                        <th style="max-width:50px;" valign="top" width="50">
                            Enlace
                        </th>
                        //-->
					</tr>
				<%
			}
			String ini;
			String fin;
			try{
				ini = _dateFormatVisible.format(miObj.getFechaInicio());
			}catch(Exception e){
				e.printStackTrace();
				ini = _dateFormatVisible.format(_hoy);
			}
			try{
				fin = _dateFormatVisible.format(miObj.getFechaCierre());
			}catch(Exception e){
				e.printStackTrace();
				fin = _dateFormatVisible.format(_hoy);
			}

			if((_hightlight > -1) && (_hightlight == miObj.getId())){
				out.println("<tr class='selectedTR'><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}else{
				out.println("<tr><td valign='top'>"+ini+"</td><td valign='top'>"+fin+"</td><td valign='top'>"+miObj.getObjeto()+"</td>");
			}
			out.println("<td align='center'><img height='24' src='comunes/imagenes/file-review.png' title='Revisar Preguntas del Instrumento' alt='Revisar Preguntas del Instrumento' onclick='window.open(\"mostrarinfo.do?accion=mostrarinfodeobjeto&id="+miObj.getObjetoAsociado().getId()+"\", \"mostrarInfo\" , \"width=700,height=500,scrollbars=YES,resizable=YES,Location=NO,Menubar=NO,Titlebar=No,Toolbar=NO\");'></td>");
			out.println("<td align='center'><a href='#'><img src='comunes/imagenes/refresh.png' onclick='if(confirm(\"Seguro que desea recuperar el instrumento:\\n"+miObj.getObjeto()+"\\n y todas las respuestas asociadas?.\")){window.location=\"admininsobj.do?opcionbase="+_mostrarOpcion+"&recuperar="+miObj.getId()+"\";}else{return false;}' height='24' alt='Recuperar este instrumento y todas las respuestas que posea' title='Recuperar este instrumento y todas las respuestas que posea' /></a></td>");
			out.println("<td align='center'><a href='admininsobj.do?objetoatrabajar="+miObj.getId()+"&accion=seleccionar&soloeste=true&accioninvitar=false&invitar="+miObj.getId()+"'><img src='comunes/imagenes/tabulador.png' alt='Revisar todas las respuestas' title='Revisar todas las respuestas' height='24' /></a></td>");
			//out.println("<td align='center'><a href='"+UtilidadesVarias.dominioWeb+"enlace?"+miObj.getIdPublico()+"' target='_blank'><img height='24' title='' alt='Enlace' src='comunes/imagenes/world_link.png'></a></td></tr>");
			todoVacio=false;
		}
		if(!vacio){
			out.println("</table>");
			vacio = true;
		}
	}

}
			if(todoVacio && _mostrarOpcion.equals("todos")){
				%>
					No existen instrumentos.<p />
				<%
			}
			if(_miIns.isEmpty() && _mostrarOpcion.equals("pendientes")){
				%>
					No existen instrumentos pendientes.<p />
				<%
			}
			if(_miIns.isEmpty() && _mostrarOpcion.equals("enejecucion")){
				%>
					No existen instrumentos en ejecuci&oacute;n.<p />
				<%
			}
			if(_miIns.isEmpty() && _mostrarOpcion.equals("finalizados")){
				%>
					No existen instrumentos finalizados.<p />
				<%
			}
			if(_miIns.isEmpty() && _mostrarOpcion.equals("relaciones")){
				%>
					No existen colecciones de datos.<p />
				<%
			}
			%>
		</td>
	</tr>
</table>
<script type="text/javascript" >
	$(document).ready(function(){
		<% if(_mostrarOpcion.equals("todos") || _mostrarOpcion.equals("finalizados")){ %>
			$("#tabla_finalizado").show();
			$("#tit_finalizado").show();
		<% } %>
	});
</script>

<%@include file="adminfooter.jsp" %>