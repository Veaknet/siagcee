	<body>
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function(){
			$('#cssdropdown li.headlink').hover(
				function() { $('ul', this).css('display', 'block'); },
				function() { $('ul', this).css('display', 'none'); });
		});
	</script>
		<center>
		<% if(request.getAttribute("showUpMenu")!=null && request.getAttribute("showUpMenu").equals("no")){}else{ %>
		<table cellpadding="0" cellspacing="0" class="tablaprincipal">
			<tr>
				<td valign="middle" align="left" style="border-top: burlywood ridge thick;border-left: burlywood ridge thick;border-width:3px;max-width:320px;width:320px">
					<a href="autenticar.do"><img src="comunes/imagenes/siagcee_logo.png" alt=""></a>
				</td>
			</tr>
			<tr>
				<td valign="top" align="left">
					<center>
						<center>
						<table cellpadding="0" cellspacing="0" class="tablasecundariatitulo hideprint">
							<tr>
								<td style="text-align: left;">
									<div style="position:absolute; z-index:1000;max-width:1010px;width:1010px;overflow:hidden;text-align:left;">
										<ul id="cssdropdown" style="padding-left:2px">
											<li class="headlink">
												<a href="#" id='link_preguntas' title="Cree, modifique o elimine preguntas que ser&aacute;n usadas en las estructuras base, censos o encuestas" onmouseover="this.focus();">Preguntas</a>
												<ul style="display: none;">
													<li class="childlink"><a href="adminpreguntas.do?opcionbase=crear"><img src="comunes/imagenes/file-add.png" height="18" title="Crear pregunta">&nbsp;Crear</a></li>
													<li class="childlink"><a href="adminpreguntas.do?opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar pregunta">&nbsp;Modificar</a></li>
													<li class="childlink"><a href="adminpreguntas.do?opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar pregunta">&nbsp;Eliminar</a></li>
													<li class="childlink"><a href="adminpreguntas.do?opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar preguntas existentes que no se pueden modificar">&nbsp;Revisar</a></li>
												</ul>
											</li>
											<li class="headlink">
                                                <a href="#" id='link_estructuras' title="Cree, modifique, publique o elimine estructuras que sirven de base para la creaci&oacute;n de censos o encuestas" onmouseover="this.focus();">Estructuras Base</a>
												<ul style="display: none;">
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="18" title="Armar estructura base">&nbsp;Crear</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar estructura base">&nbsp;Modificar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=crear"><img src="comunes/imagenes/file-check.png" height="18" title="Generar estructura base previamente armada">&nbsp;Generar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar estructura base">&nbsp;Eliminar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=estructura&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar estructuras base generadas que no ya no pueden modificarse">&nbsp;Revisar</a></li>
												</ul>
											</li>
											<li class="headlink">
												<a href="#" id='link_instrumentos' title="Cree, modifique, publique o elimine censos o encuestas" onmouseover="this.focus();">Instrumentos Base</a>
												<ul style="display: none;">
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="18" title="Crear censo o encuesta">&nbsp;Crear</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar censo o encuesta">&nbsp;Modificar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=crear"><img src="comunes/imagenes/file-check.png" height="18" title="Generar censo o encuesta previamente armado">&nbsp;Generar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar censo o encuesta">&nbsp;Eliminar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=instrumento&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar censo o encuesta generadas que no ya no pueden modificarse">&nbsp;Revisar</a></li>
												</ul>
											</li>
											<li class="headlink">
												<a id='link_generar_instrumentos' href="implementobjeto.do" title="Publique censos o encuestas previamente generados" onmouseover="this.focus();">Publicar Instrumento</a>
											</li>
											<li class="headlink">
												<a id='link_instrumentos_generados' href="#" title="Revise censos o encuestas ya publicados" onmouseover="this.focus();">Instrumentos Publicados</a>
												<ul style="display: none;">
													<li class="childlink"><a href="admininsobj.do?opcionbase=pendientes"><img src="comunes/imagenes/file-pendiente.png" height="18" title="Mostrar s&oacute;lo censos o encuestas pendientes">&nbsp;Pendientes</a></li>
													<li class="childlink"><a href="admininsobj.do?opcionbase=enejecucion"><img src="comunes/imagenes/file-ejecucion.png" height="18" title="Mostrar s&oacute;lo censos o encuestas actualmente en ejecuci&oacute;n">&nbsp;En Ejecuci&oacute;n</a></li>
													<li class="childlink"><a href="admininsobj.do?opcionbase=finalizados"><img src="comunes/imagenes/file-finalizado.png" height="18" title="Mostrar s&oacute;lo censos o encuestas finalizados">&nbsp;Finalizados</a></li>
													<li class="childlink"><a href="admininsobj.do?opcionbase=todos"><img src="comunes/imagenes/file-todos.png" height="18" title="Mostrar censos o encuestas pendientes, actualmente en ejecuci&oacute;n y finalizados">&nbsp;Todos</a></li>
													<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
														<li class="childlink"><a href="admininsobj.do?opcionbase=eliminados"><img src="comunes/imagenes/delete.png" height="18" title="Mostrar s&oacute;lo censos o encuestas eliminados">&nbsp;Eliminados</a></li>
													<% } 	%>
												</ul>
											</li>
											<li class="headlink">
												<a id='link_estudios' href="#" title="Cree, modifique y elimine estudios asociados a un censos, encuestas o colecci&oacute;nes de datos" onmouseover="this.focus();">Estudios</a>
												<ul style="display: none;">
													<li class="childlink"><a href="estudios.do?opcionbase=crear"><img src="comunes/imagenes/file-add.png" height="18" title="Crear estudio">&nbsp;Crear</a></li>
													<li class="childlink"><a href="admininsobj.do?opcionbase=todos&soloaplicar=true"><img src="comunes/imagenes/file-check.png" height="18" title="Aplicar estudio">&nbsp;Aplicar</a></li>
													<li class="childlink"><a href="estudios.do?opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar estudio">&nbsp;Modificar</a></li>
													<li class="childlink"><a href="estudios.do?opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar estudio">&nbsp;Eliminar</a></li>
												</ul>
											</li>
											<li class="headlink">
												<a id='link_colecciones_de_datos' href="#" title="Arme, modifique y elimine agrupaci&oacute;n de datos entre censos y/o encuestas" onmouseover="this.focus();">Colecciones de Datos</a>
												<ul style="display: none;">
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=armar"><img src="comunes/imagenes/file-add.png" height="18" title="Crear colecci&oacute;n de datos">&nbsp;Crear</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=modificar"><img src="comunes/imagenes/file-edit.png" height="18" title="Modificar colecci&oacute;n de datos">&nbsp;Modificar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=eliminar"><img src="comunes/imagenes/file-delete.png" height="18" title="Eliminar colecci&oacute;n de datos">&nbsp;Eliminar</a></li>
													<li class="childlink"><a href="adminobjetos.do?tipoinstrumento=relacion&opcionbase=revisar"><img src="comunes/imagenes/file-review.png" height="18" title="Revisar colecci&oacute;n de datos">&nbsp;Revisar</a></li>
												</ul>
											</li>
											<li class="headlink">
												<a id='opciones_link' href="#" title="Otras opciones" onmouseover="this.focus();">Opciones</a>
												<ul style="display: none;">
													<li class="childlink">
														<a id='link_msg' href="mensaje.do"><img src="comunes/imagenes/email.png" height="18" title="Env&iacute;e un correo electr&oacute;nico a un conjunto de personas">&nbsp;Mensaje</a>
													</li>
													<li class="childlink">
														<% if(admin.getTipoUsuario().equals("superadministrador")){ %>
															<a id='link_administrar_cuentas' href="cuentas.do"><img src="comunes/imagenes/profile.png" height="18" title="Maneje las cuentas de administradores del sistema">&nbsp;Cuentas</a>
														<% } 	%>
														<% if(admin.getTipoUsuario().equals("regular")){ %>
															<a id='link_administrar_cuentas' href="cuentas.do"><img src="comunes/imagenes/profile.png" height="18" title="Administre sus datos personales">&nbsp;Cuenta</a>
														<% } 	%>
													</li>
													<li class="childlink">
														<a id='link_salir' href="salir.do" onclick="return confirm('Desea realmente abandonar la aplicacion?');"><img src="comunes/imagenes/exit.png" height="18" title="Cierre su sesi&oacute;n y vuelva a la p&aacute;gina inicial">&nbsp;Salir</a>
													</li>
												</ul>
											</li>
										</ul>
									</div>
									<br /><br /><br />
								</td>
							</tr>
						</table>
					<% } %>
