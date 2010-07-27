<%@include file="admininicio.jsp" %>
<%@include file="adminheader.jsp" %>
<table cellpadding="4" cellspacing="4" style="text-align:right;" class="tablasecundaria">
	<tr>
		<td valign="center" height="200" style="text-align:left;">
			<h3>Bienvenido <% out.println(admin.getNombre().trim());%> a SIGECENE.</h3>
			Por favor, indique que desea realizar en el men&uacute; superior.
			<br /><p />
			<%
			if(request.getAttribute("mensaje") != null){
			  out.print((String)request.getAttribute("mensaje"));
			}
			%>
		</td>
	</tr>
</table>
</center>

<%@include file="adminfooter.jsp" %>
