<%@page session="true" import="com.siagcee.logic.*" %>
<%@ page import="java.util.*" %>
<%@include file="admininicio.jsp" %>

<!--
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 05/05/2011
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

boolean _loaded = false;
if(request.getAttribute("loaded") != null){
	_loaded = true;
}

String _mensaje = "";
if(request.getAttribute("mensaje") != null){
	_mensaje = (String)request.getAttribute("mensaje");
}

int idObj = -1;
if(request.getAttribute("idobjetodestino") != null){
    idObj = (Integer)request.getAttribute("idobjetodestino");
}

if(!_loaded){
%>
    <table cellspacing="4" cellpadding="4" class="tablasecundaria">
        <tr>
            <td valign="top" align="left">
                <h2>Cargar datos al instrumento desde archivo excel</h2>
                <%
                if(!_mensaje.equals("")){
                    out.print("<p />"+_mensaje+"<p />");
                }
                %>
                Nota: Recuerde descargar haciendo clic <a href="sqlpreview?accionextra=exportaexcel&opcionmadre=si&objetoatrabajar=<% out.print(idObj); %>">aqu&iacute;</a> el archivo excel base para cargar sus datos.
                <form action="subirexcel" enctype="multipart/form-data" method="POST">
                    Indique el archivo a subir: <input type="file" name="file1" size="40"><p />
                    <input type="hidden" value="<% out.print(idObj); %>" id="idobjetodestino" name="idobjetodestino"><br>
                    <input type="Submit" value="Cargar archivo"><br>
                </form>
            </td>
        </tr>
    </table>

<%
}else{
    out.print("Elemento Cargado");
}

%>

<%@include file="adminfooter.jsp" %>