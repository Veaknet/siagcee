<%@page session="true" %>
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

String idObj = "";
if(request.getAttribute("idobjetodestino") != null){
    idObj = (String)request.getAttribute("idobjetodestino");
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
                <form action="subirexcel" enctype="multipart/form-data" method="POST">
                    Indique el archivo a subir: <input type="file" name="file1" size="40"><p />
                    <input type="hidden" value="<% out.print(idObj); %>" id="idobjetodestino" name="idobjetodestino">
                    <input type="Submit" value="Cargar archivo"><br >
                </form>
				<p /><br >Puede descargar el archivo base en excel para carga de datos haciendo clic <a href="sqlpreview?accionextra=exportaexcel&plantilla=1&opcionmadre=si&objetoatrabajar=<% out.print(idObj); %>">aqu&iacute;</a>.
            </td>
        </tr>
    </table>

<%
}else{
%>
<table cellspacing="4" cellpadding="4" class="tablasecundaria">
	<tr>
		<td valign="top" align="center">
			<% out.print("<p />"+_mensaje+"<p />"); %>
		</td>
	</tr>
</table>
<%
}

%>

<%@include file="adminfooter.jsp" %>