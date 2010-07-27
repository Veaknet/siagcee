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

<body>
	<center>
	<table cellpadding="0" cellspacing="0" class="tablaprincipal">
		<tr>
			<td valign="middle" align="left" style="border-top: burlywood ridge thick;border-left: burlywood ridge thick;border-width:3px;max-width:320px;width:320px">
				<img src="comunes/imagenes/siagcee_logo.png" alt="">
			</td>
		</tr>
		<tr>
			<td valign="top" align="left">
				<table cellpadding="4" cellspacing="4" class="tablasecundariatitulo" style="width:600px;min-width:600px;max-width:600px;">
					<tr>
						<td style="text-align:left" valign="top" >
							<h2>Ayuda</h2>
						</td>
					</tr>
				</table>
				<table cellspacing="4" cellpadding="4" class="tablasecundaria" style="width:600px;min-width:600px;max-width:600px;">
					<tr>
						<td style="text-align:left" valign="top">
			<%
			String _opcion = "";
			if(request.getParameter("tema") != null){
				_opcion = (String)request.getParameter("tema");
			}
			%>
			<%
			if(_opcion.equals("")){
				%>
				<h4>No ha indicado un tema de ayuda.</h4>
				<%
			}
			if(_opcion.equals("tipodedatos")){
				%>
			  <h4>Definici&oacute;n de Datos.</h4>
				En esta secci&oacute;n se administran los datos que se usar&aacute;n para validar el contenido de las respuestas a las preguntas dadas en los cuestionarios.<p />
				Un ejemplo ser&iacute;a el dato "sexo" que tendr&aacute; los valores posibles:<p />
				<ul><li>Masculino.</li></ul>
				<ul><li>Femenino.</li></ul>
				<h4>Detalles de Datos.</h4>
				Para crear o modificar un Dato usted debe proveer un identificador &uacute;nico para el dato.<p />
				Debe tambi&eacute;en indicar si el dato ser&aacute;:<p />
				<ul><strong>De selecci&oacute;n simple:</strong> solo se permitir&aacute; seleccionar una posible respuesta de las ofrecidas.</ul>
				<ul><strong>De selecci&oacute;n simple:</strong> se permitir&aacute; seleccionar mas de una posible respuesta de las ofrecidas.</ul>
				<ul><strong>Tipo abierto para texto:</strong> se permitir&aacute; que el encuestado ingrese cualquier informaci&oacute;n.</ul>
				<ul><strong>Tipo abierto para n&uacute;meros con decimales:</strong> se permitir&aacute; que el encuestado ingrese cualquier n&uacute;mero con decimales.</ul>
				<ul><strong>Tipo abierto para n&uacute;meros sin decimales:</strong> se permitir&aacute; que el encuestado ingrese cualquier n&uacute;mero sin decimales.</ul>
				<ul><strong>Tipo abierto para fecha:</strong> se permitir&aacute; que el encuestado ingrese &uacute;nicamente fechas.</ul>
				<ul><strong>Uso exclusivo para estudios:</strong> no ser&aacute; visible para los encuestados. En este tipo de dato se almacenar&aacute; el resultado de un estudio que posteriormente se indique.</ul>
				La visibilidad se aplica en situaciones donde usted no desea que sea usado el dato que se est&aacute; definiendo, por razones variadas, una de ellas puede ser que el dato no se ha definido en su totalidad.<p />
				Para crear un dato haga clic en <img height="24" src="comunes/imagenes/add.png" alt="nuevo">
				<strong>Nota importante:</strong> S&oacute;lo es posible editar datos mientras no sean usados en las estructuras de instrumentos. En caso que est&eacute;n siendo usadas, no aparecer&aacute;n en la lista. 
				<%
			}
			if(_opcion.equals("valorestipodedatos")){
				%>
				<h4>Posibles Valores de Datos.</h4>
				Para crear, haciendo clic en <img height="24" src="comunes/imagenes/add.png" alt="nuevo">, o modificar, haciendo clic en <img height="24" src="comunes/imagenes/modify.png" alt="modificar">, un posible valor de dato usted debe proveer simplemente los valores para el dato.<p />
				Para eliminar un valor, haga clic en <img height="24" src="comunes/imagenes/delete.png" alt="nuevo">.<p />
				Puede tambi&eacute;n cargar los valores existentes de otro dato. Haciendo clic en <img height="24" src="comunes/imagenes/load.png" alt="Cargar"><p />
				<%
			}
			if(_opcion.equals("cargadeotrotipotipodedatos")){
				%>
				<h4>Cargar Valores de un Dato Existente.</h4>
				Primero debe indicar de cu&aacute;l dato usted desea tomar los valores.<p />
				Posteriormente solo seleccione los valores que desea en la lista bajo el nombre: "<strong>Valores Disponibles de...</strong>"<br />haga clic en <img src="comunes/imagenes/next.png" alt="agregar" height="24"> para agregarlo al dato que est&aacute; creando o modificando.<p />
				Si desea eliminar un valor importado previamente ind&iacute;quelo en la lista bajo el nombre: "<strong>Valores que se agregar&aacute;n a...</strong>"<br />haga clic en <img src="comunes/imagenes/back.png" alt="eliminar" height="24"> para agregarlo al dato que est&aacute; creando o modificando.<p />
			  No se olvide de hacer clic en "Procesar" para finalizar la importaci&oacute;n de valores.
				<%
			}
			if(_opcion.equals("estructuras")){
				%>
				<h4>Estructuraci&oacute;n de Instrumentos.</h4>
				Deben definirse estructuras que luego son usadas por los instrumentos(Censos y Encuestas).<br />
				En las estructuras se definen: <br />
				<ul>El tipo de instrumento: <ul><li>Censo</li></ul><ul><li>Encuesta</li></ul></ul>
				<ul>Nombre de la estructura.</ul>
				La visibilidad se aplica en situaciones donde usted no desea que sea usada la estructura que se est&aacute; definiendo, por razones variadas, una de ellas puede ser que la estructura no se ha definido en su totalidad.<p />
				Para crear una estructura haga clic en <img height="24" src="comunes/imagenes/add.png" alt="nuevo"><p />
				<strong>Nota importante:</strong> S&oacute;lo es posible editar estructuras mientras no sean usadas en los instrumentos. En caso que est&eacute;n siendo usadas, no aparecer&aacute;n en la lista. 
				<%
			}
			if(_opcion.equals("modificacionestructuras")){
				%>
				<h4>Edici&oacute;n de Estructuras de Instrumentos.</h4>
				Puede alterar las caracter&iacute;sticas de la estructura haciendo clic en <img src="comunes/imagenes/edit.png" alt="modificar" height="24">.<br />
				En las estructuras se definen: <br />
				<ul>El tipo de instrumento: Censo o Encuesta.</ul>
				<ul>Nombre de la estructura.</ul>

				La visibilidad se aplica en situaciones donde usted no desea que sea usada la estructura que se est&aacute; definiendo, por razones variadas, una de ellas puede ser que la estructura no se ha definido en su totalidad.<p />
				Para eliminar la estructura haga clic en <img height="24" src="comunes/imagenes/delete.png" alt="eliminar"><p />
				Para crear un estudio para estructura haga clic en <img height="24" src="comunes/imagenes/barchart.png" alt="estudios">
				<h4>Incorporando Preguntas en la Estructura.</h4>
				Para agregar una pregunta a la estructura s&oacute;lo haga clic en <img height="24" src="comunes/imagenes/add.png" alt="agregar">. Posteriormente indique:<br />
				<ul>Texto de la pregunta que leer&aacute;n los encuestados.</ul>
				<ul>N&uacute;mero de la pregunta. Indicar&aacute; el orden de las preguntas en el cuestionario.</ul>
				<ul><a href="ayuda.do?tema=tipodedatos" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Dato</a> que se utilizar&aacute; para validar la pregunta. En caso que el tipo de dato sea de uso exclusivo para estudios, entonces deber&aacute; indicar el estudio que se asocia a dicha pregunta.</ul>
				Puede tambi&eacute;n cargar las preguntas existentes de otra estructura haciendo clic en <img height="24" src="comunes/imagenes/load.png" alt="cargar"><br />
				Se importar&aacute; el texto, el n&uacute;mero de la pregunta y el dato asociado, sin embargo, esto puede ser modificado posteriormente.
				<%
			}
			if(_opcion.equals("cargarestructuras")){
				%>
				<h4>Cargar Preguntas de una Estructura Existente.</h4>
				Primero debe indicar de cu&aacute;l estructura usted desea tomar las preguntas.<p />
				Posteriormente solo seleccione las preguntas que desea en la lista bajo el nombre: "<strong>Preguntas Disponibles de...</strong>"<br />haga clic en <img src="comunes/imagenes/next.png" alt="agregar" height="24"> para agregarlo a la estructura que est&aacute; creando o modificando.<p />
				Si desea eliminar una pregunta importada previamente ind&iacute;quelo en la lista bajo el nombre: "<strong>Preguntas que se agregar&aacute;n a...</strong>"<br />haga clic en <img src="comunes/imagenes/back.png" alt="eliminar" height="24"> para agregarla a la estructura que est&aacute; creando o modificando.<p />
				No se olvide de hacer clic en "Procesar" para finalizar la importaci&oacute;n de preguntas.
				<%
			}
			if(_opcion.equals("instrumento")){
				%>
				<h4>Definici&oacute;n de Instrumentos.</h4>
				Para crear un instrumento haga clic en <img height="24" src="comunes/imagenes/add.png" alt="agregar">. Posteriormente indique:<br />
				<ul><strong>Nombre del instrumento</strong>: T&iacute;tulo que se le dar&aacute; al Censo o Encuesta.</ul>
				<ul><strong>Tipo de acceso</strong>: define c&oacute;mo limita los usuarios que acceden al instrumento:
				<ul><li><strong>P&uacute;blico</strong>: cualquier usuario puede ingresar y contestar el cuestionario.</li></ul>
				<ul><li><strong>Restringido</strong>: &uacute;nicamente los usuarios que se indicar&aacute;n luego podr&aacute;n llenar el cuestionario.</li></ul>
				</ul>
				<ul><strong>Seleccionar usuarios</strong>: permite indicar los usuarios que tendr&aacute;n acceso al cuestionario.
				<ul><li><strong>Otro instrumento:</strong> se obtienen los usuarios(todos o un subconjunto) que participaron en otro instrumento.</li></ul>
				<ul><li><strong>Correo electr&oacute;nico manualmente:</strong> se indican todos los correos electr&oacute;nicos manualmente.</li></ul>
				<ul><li><strong>Sin invitaci&oacute;n:</strong> solo disponible cuando el tipo de acceso es p&uacute;blico. No se envian invitaciones.</li></ul>
				</ul>
				<ul><strong><a href="ayuda.do?tema=estructuras" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Estructura</a></strong>: indica la estructura que se utilizar&aacute;. Dicha estructura contiene las preguntas que se formular&aacute;n.</ul>
				<ul><strong>Inicio y Fin</strong>: indica la fecha que comienza y termina la ejecuci&oacute;n del instrumento.</ul>
				Puede modificar un instrumento ya creado haciendo clic en <img src="comunes/imagenes/modify.png" alt="editar" height="24"> y eliminarlo haciendo clic en <img src="comunes/imagenes/delete.png" alt="eliminar" height="24"><p />
				<strong>Nota importante:</strong> No aparecer&aacute;n en esta pantalla instrumentos que se est&eacute;n ejecutando o ya hayan finalizado. 
				<%
			}
			if(_opcion.equals("instrumentofinalizado")){
				%>
				<h4>Administraci&oacute;n de Instrumentos en Ejecuci&oacute;n o finalizados.</h4>
				Se mostrar&aacute;n tres grupos de instrumentos(cuando aplique):
				<ul><strong>Instrumentos en Ejecuci&oacute;n</strong>: agrupa instrumentos que actualmente se est&eacute;n ejecutando.</ul>
				<ul><strong>Instrumentos Finalizados</strong>: agrupa instrumentos finalizados.</ul>
				<ul><strong><a href="ayuda.do?tema=colecciones" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Colecciones</a></strong>: agrupa colecciones de instrumentos.</ul>
				Es posible eliminar cualquier instrumento o colecci&oacute;n haciendo clic en <img src="comunes/imagenes/delete.png" alt="eliminar" height="24"><p />
				Pueden crearse estudios haciendo clic en <img src="comunes/imagenes/barchart.png" alt="crear estudio" height="24"> y ejecutar un estudio previamente creado haciendo clic en <img src="comunes/imagenes/stats.png" alt="aplicar estudio" height="24">
				<%
			}
			if(_opcion.equals("colecciones")){
				%>
				<h4>Colecciones de Datos.</h4>
				La intenci&oacute;n de las colecciones de datos es agrupar varios instrumentos o estructuras de instrumentos para administrarlos como un todo.<p />
				Es posible agrupar instrumentos (con o sin las respuestas ya obtenidas de los encuestados) con otros instrumentos o con estructuras u otras colecciones.<p />
				Es ilimitada la cantidad de <a href="ayuda.do?tema=instrumento" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">instrumentos</a>, <a href="ayuda.do?tema=estructuras" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">estructuras</a> y colecciones que pueden agruparse.<p />
			  Es posible indicar que preguntas de cada instrumento, estructura o colecci&oacute;n coleccionar.
				Para crear una colecci&oacute;n haga clic en <img src="comunes/imagenes/add.png" alt="crear coleccion" height="24"> e indique:<p />
				<ul><li>Nombre de la colecci&oacute;n.</li></ul>
				<ul><li>Acr&oacute;nimo.</li></ul>
				<%
			}
			if(_opcion.equals("administracolecciones")){
				%>
				<h4>Colecciones de Datos.</h4>
				Es posible eliminar una relaci&oacute;n haciendo clic en <img src="comunes/imagenes/delete.png" alt="eliminar" height="24"> y editar sus caracter&iacute;n haciendo clic en <img src="comunes/imagenes/modify.png" alt="editar" height="24"><p />
				Para agregar preguntas a la colecci&oacute;n debe indicar el <strong><a href="ayuda.do?tema=instrumento" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Instrumento</a></strong> o la <strong><a href="ayuda.do?tema=estructuras" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Estructura de Instrumento</a></strong> de donde se obtendr&aacute;.<p />
				Una vez hecho lo anterior seleccione del listado la(s) pregunta(s) que agregar&aacute; a la colecci&oacute;n.<p />
				Si trabaja con instrumentos, se le presentar&aacute;n un grupo de opciones (dado que los instrumentos poseen respuestas de los encuestados asociados), y son las siguientes:<p />
				<ul><li><strong>Copiando en la Colecci&oacute;n la Estructura del Instrumento</strong></li>
					S&oacute;lo se tomar&aacute; la estructura del instrumento para agregarla a la colecci&oacute;n. Las respuestas asociadas al instrumentos ser&aacute;n obviadas.
				</ul>
				<ul><li><strong>Uniendo todos los datos del Instrumento y la Colecci&oacute;n</strong></li>
					Se tomar&aacute; la estructura del instrumento y las respuestas asociadas para agregarla a la colecci&oacute;n.<br />
					<img src="comunes/imagenes/ayuda/colecciones_union.png" /><br />
				</ul>
				<ul><li><strong>Uniendo todos los datos de la Colecci&oacute;n m&aacute;s aquellos del Instrumento que coincidan en la primera</strong></li>
					Se tomar&aacute; la estructura del instrumento y las respuestas asociadas para agregarla a la colecci&oacute;n. Con la restricci&oacute;n de que se importar&aacute;n &uacute;nicamente las respuestas de los encuestados que existan en la colecci&oacute;n, los dem&aacute;s ser&aacute;n obviados.<br />
					<img src="comunes/imagenes/ayuda/colecciones_left_join.png" /><br />
				</ul>
				<ul><li><strong>Uniendo todos los datos del Instrumento m&aacute;s aquellos de la Colecci&oacute;n que coincidan en la primera</strong></li>
					Se tomar&aacute; la estructura colecci&oacute;n y las respuestas asociadas para agregarla al instrumento. Con la restricci&oacute;n de que se importar&aacute;n &uacute;nicamente las respuestas de los encuestados que existan en el instrumento, los dem&aacute;s ser&aacute;n obviados.<br />
					<img src="comunes/imagenes/ayuda/colecciones_right_join.png" /><br />
				</ul>
				<ul><li><strong>Tomando los datos de la Colecci&oacute;n y del Instrumento que coincidan</strong></li>
					Se une toda la informaci&oacute;n del instrumento y de la colecci&oacute;n. Con la restricci&oacute;n de que se importar&aacute;n &uacute;nicamente las respuestas de los encuestados que existan tanto en el instrumento como en la colecci&oacute;n, los dem&aacute;s ser&aacute;n obviados.<br />
					<img src="comunes/imagenes/ayuda/colecciones_interseccion.png" /><br />
				</ul>
				<ul><li><strong>Seg&uacute;n criterios personalizados se ingresar&aacute;n datos del instrumento en la Colecci&oacute;n</strong></li>
					Se agregar&aacute; la informaci&oacute;n del instrumento a la colecci&oacute;n siempre que los encuestados en el instrumento se encuentren en una subconsulta que se generar&aacute;.
					<img src="comunes/imagenes/ayuda/colecciones_condiciones_especiales.png" /><br />
				</ul>
				<ul><li><strong>Asociando preguntas del instrumento a preguntas existentes de la colecci&oacute;n</strong></li>
					No se agregar&aacute;n preguntas a la colecci&oacute;n, m&aacute;s bien se tomar&aacute; la informaci&oacute;n de cada pregunta del instrumento para agregarlo a preguntas especificadas en la colecci&oacute;n.
				</ul>
				<%
			}
			if(_opcion.equals("relacionesunoauno")){
				%>
				<h4>Administrar Colecciones.</h4>
				Debe indicar la pregunta del <a href="ayuda.do?tema=instrumento" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">instrumento</a> que se asociar&aacute; a una pregunta existente de la colecci&oacute;n.<br />
				De esta manera puede tomarse la informaci&oacute;n del instrumento e importarlo en la colecci&oacute;n en una pregunta espec&iacute;fica.<p />
				Existe la restricci&oacute;n para asociar preguntas que deban utilizar un mismo <a href="ayuda.do?tema=tipodedatos" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">tipo de dato.</a>
				<%
			}
			if(_opcion.equals("administradores")){
				%>
				<h4>Manejar Cuentas de Administradores.</h4>
				Puede crear cuentas de administradores haciendo clic en <img src="comunes/imagenes/add-user.png" alt="agregar" height="24"><p />
				Para eliminar un administrador haga clic en <img src="comunes/imagenes/delete-user.png" alt="eliminar" height="24"><p />
				Para editar su informaci&oacute;n haga clic en <img src="comunes/imagenes/modify.png" alt="modificar" height="24"><p />
				Existe una restricci&oacute;n al eliminar un administrador: no debe poseer elementos creados por &eacute;l.<p />
				Existen dos tipos de administradores:
				<ul><li>Super Administradores<ul>Lo que crean es compartido por todos los dem&aacute;s administradores en el sistema. Pero, en modo s&oacute;lo lectura.</ul></li></ul>
				<ul><li>Administradores Regulares<ul>Lo que crean es privado y no es visible por los dem&aacute;s administradores en el sistema.</ul></li></ul>
				<%
			}
			if(_opcion.equals("estudios")){
				%>
				<h4>Administrar Estudios.</h4>
				Para crear un nuevo estudio haga clic en <img src="comunes/imagenes/add.png" alt="crear" height="24">.<p />
				Puede cargar un estudio existente para modificarlo y luego deber&aacute; obligatoriamente guardarlo con otro nombre.<p />
				Para eliminar un estudio existente, seleccionelo y luego haga clic en <img src="comunes/imagenes/delete.png" alt="eliminar" height="24"><p />
				<%
			}
			if(_opcion.equals("edicionestudios")){
				%>
				<h4>Generaci&oacute;n de Estudios.</h4>
				Los estudios permiten es an&aacute;lisis l&oacute;gico-matem&aacute;tico de las respuestas obtenidas previamente por parte de los encuestados.<p />
				El estudio est&aacute; compuesto esencialmente de un nombre o t&iacute;tulo, una descripci&oacute;n de su funcionamiento y utilidad, y el c&oacute;digo que es el encargado de realizar los c&aacute;lculos.<p />
				El c&oacute;digo puede ser tan complejo o tan simple como se necesite. C&aacute;lculos simples, anidados y/o bifurcaciones.
				Estructura b&aacute;sica del c&oacute;digo:<p />
				<a href="ayuda.do?tema=parametros_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Par&aacute;metros o datos solicitados al momento de ejecuci&oacute;n.</a><br />
				<a href="ayuda.do?tema=bifurcaciones_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Condiciones o bifurcaciones.</a><br />
				<a href="ayuda.do?tema=calculos_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&aacute;lculos.</a><br />
				<a href="ayuda.do?tema=ejemplo_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&oacute;digos de ejemplo.</a><br />
				<%
			}
			if(_opcion.equals("parametros_estudio")){
				%>
				<h4>Par&aacute;metros o Valores de Entrada de los Estudios.</h4>
				Los par&aacute;metros permiten obtener valores al momento de ejecutar un estudio, que le ser&aacute;n solicitados al administrador antes de realizar cualquier c&aacute;lculo.<p />
				Es obligatorio definirlo al inicio del c&oacute;digo, antes que realice cualquier c&aacute;lculo o comprobaci&oacute;n.<p />
				Podemos seleccionar esta opci&oacute;n inicialmente en el combo de selecci&oacute;n de "<strong>Palabras Reservadas</strong>" con el nombre: "<strong>Pedir valor al ejecutar</strong>"<p />
				Inmediatamente se escribir&aacute; en el c&oacute;digo lo siguiente:<p />
				PEDIR EN EJECUCION:<br />
				DE TIPO DE DATO:<br /><br />
				 USAR ACRONIMO:<br /><br />
				 MOSTRAR TITULO:<br /><br />
				FIN.-<p />
				Donde:<br />
				<ul>DE TIPO DE DATO:<ul>se indicar&aacute; el <a href="ayuda.do?tema=tipodedatos" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">tipo de dato</a> a usar. Puede seleccionarlo en el combo de selecci&oacute;n de "<strong>Tipos de Datos</strong>"</ul></ul>
				<ul>USAR ACRONIMO:<ul>nombre interno de este valor solicitado. Necesario para realizar posteriormente los c&aacute;lculos. No use espacios en blanco, letras con tilde ni signos de puntuaci&oacute;n.</ul></ul>
				<ul>MOSTRAR TITULO:<ul>Se indicar&aacute; texto que se le ser&aacute mostrado al ejecutor del estudio al momento del solicitarle el dato. Puede escribir lo que desee, mientras no ocupe m&aacute;s de una l&iacute;nea.</ul></ul>
				Ejemplo:<p />
				PEDIR EN EJECUCION:<br />
					DE TIPO DE DATO:<br />
					 87-Pregunta abierta para n&uacute;meros con decimales<br />
				  USAR ACRONIMO:<br />
					 edad<br />
					MOSTRAR TITULO:<br />
					 Indique la edad que desea usar como tope para realizar los c&aacute;culos:<br />
				FIN.-<p />
				Vea tambi&eacute;n:<br/>
				<a href="ayuda.do?tema=bifurcaciones_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Condiciones o bifurcaciones.</a><br />
				<a href="ayuda.do?tema=calculos_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&aacute;lculos.</a><br />
				<a href="ayuda.do?tema=ejemplo_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&oacute;digos de ejemplo.</a><br />
				<%
			}
			if(_opcion.equals("bifurcaciones_estudio")){
				%>
				<h4>Uso de Bifurcaciones o Condiciones en Estudios.</h4>
				Las bifurcaciones permiten realizar operaciones si algunas condiciones se cumplen. O realizar operaciones con un subconjunto del total de los datos.<p />
				Pueden ser anidadas permitiendo realizar condicionales dentro de otros condicionales.<p />
				Podemos seleccionar esta opci&oacute;n inicialmente en el combo de selecci&oacute;n de "<strong>Palabras Reservadas</strong>" con el nombre: "<strong>Cuando se cumpla con - Realizar - Fin</strong>"<p />
				Inmediatamente se escribir&aacute; en el c&oacute;digo lo siguiente:<p />
				CUANDO SE CUMPLA CON:<br /><br />
				REALIZAR:<br /><br />
				FIN..<p />
				Donde:<br />
			  <ul>CUANDO SE CUMPLA CON:<ul>indicar&aacute; las condiciones que deben cumplirse para que el c&aacute;lculo dentro de la bifurcaci&oacute;n se realice. Debe retornar un resultado l&oacute;gico verdadero o falso. Las operaciones pueden ocupar mas de una l&iacute;nea y pueden usarse par&eacute;ntesis para agrupar.</ul></ul>	
				<ul>REALIZAR:<ul>indicar&aacute; los c&aacute;lculos que se realizar&aacute;n. Puede dentro de este bloque ingresarse una nueva bifurcaci&oacute;n. Las operaciones pueden ocupar mas de una l&iacute;nea y pueden usarse par&eacute;ntesis para agrupar.</ul></ul>
				<strong>Nota importante:</strong> cuando se realicen los <a href="ayuda.do?tema=calculos_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">c&aacute;lculos</a> para obtener los resultados deben colocarse las operaciones dentro de:<p />
				CALCULAR ESTUDIO:<br /><br />
				FIN DEL CALCULO.-<p />
				Ejemplo:<p />
				CUANDO SE CUMPLA CON:<br />
				^0^ <  VALOR: ^unidadestributarias^<br />
				REALIZAR:<br />
				CALCULAR ESTUDIO:<br />
				VALOR: ^unidadestributarias^ * RESPUESTA DADA A PREGUNTA: ^356-Cu&aacute;l es su ingreso promedio anual?^<br />
				FIN DEL CALCULO.-<br />
				FIN..<p />
				Vea tambi&eacute;n:<br/>
				<a href="ayuda.do?tema=parametros_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Par&aacute;metros o datos solicitados al momento de ejecuci&oacute;n.</a><br />
				<a href="ayuda.do?tema=calculos_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&aacute;lculos.</a><br />
				<a href="ayuda.do?tema=ejemplo_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&oacute;digos de ejemplo.</a><br />
				<%
			}
			if(_opcion.equals("calculos_estudio")){
				%>
				<h4>C&aacute;lculos.</h4>
				Aqu&iacute; se realizan los c&aacute;lculos sobre los datos del instrumento.<p />
				Podemos seleccionar esta opci&oacute;n inicialmente en el combo de selecci&oacute;n de "<strong>Palabras Reservadas</strong>" con el nombre: "<strong>Calcular estudio</strong>"<p />
				Inmediatamente se escribir&aacute; en el c&oacute;digo lo siguiente:<p />
				CALCULAR ESTUDIO:<br /><br />
				FIN DEL CALCULO.-<p />
				Donde:<br />
				<ul>CALCULAR ESTUDIO:<ul>se realizan las operaciones matem&aacute;ticas y/o l&oacute;gicas. Las operaciones pueden ocupar mas de una l&iacute;nea y pueden usarse par&eacute;ntesis para agrupar.</ul></ul>
				Ejemplo:<p />
				CALCULAR ESTUDIO:<br />
				VALOR: ^unidadestributarias^ * RESPUESTA DADA A PREGUNTA: ^356-Cu&aacute;l es su ingreso promedio anual?^<br />
				FIN DEL CALCULO.-<p />
				Vea tambi&eacute;n:<br/>
				<a href="ayuda.do?tema=parametros_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Par&aacute;metros o datos solicitados al momento de ejecuci&oacute;n.</a><br />
				<a href="ayuda.do?tema=bifurcaciones_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Condiciones o bifurcaciones.</a><br />
				<a href="ayuda.do?tema=ejemplo_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&oacute;digos de ejemplo.</a><br />
				<%
			}
			if(_opcion.equals("ejemplo_estudio")){
				%>
				<h4>Ejemplos.</h4>
				Para operar los resultados de cada respuesta dada, seleccionela del combo de "<strong>Preguntas</strong>":<p />
				CALCULAR ESTUDIO:<br />
				 RESPUESTA DADA A PREGUNTA: ^356-Cu&aacute;l es su ingreso promedio anual?^ / ^65000^<br />
				FIN DEL CALCULO.-<p />
				Para operar los resultados de un valor solicitado en ejecuci&oacute;n, seleccionelo del combo de "<strong>Valores Solicitados</strong>":<p />
				CALCULAR ESTUDIO:<br />
				 RESPUESTA DADA A PREGUNTA: ^356-Cu&aacute;l es su ingreso promedio anual?^ / Valor: ^unidad_tributaria^<br />
				FIN DEL CALCULO.-<p />
				Para anidar resultados use par&eacute;ntesis:<p />
				CALCULAR ESTUDIO:<br />
				 ( RESPUESTA DADA A PREGUNTA: ^356-Cu&aacute;l es su ingreso promedio anual?^ + Valor: ^Cestatickets^) / Valor: ^unidad_tributaria^<br />
				FIN DEL CALCULO.-<p />
				Si solo se desean los resultados dados a una pregunta dada, debe indicarse &uacute;nicamente la pregunta en cuesti&oacute;n:<p />
				CALCULAR ESTUDIO:<br />
					RESPUESTA DADA A PREGUNTA: ^356-Cu&aacute;l es su ingreso promedio anual?^<br />
				FIN DEL CALCULO.-<p />
				Valores constantes se ingresan entre ^^ :<p />
				CALCULAR ESTUDIO:<br />
					^4,30^<br />
				FIN DEL CALCULO.-<p />
				Valores constantes se ingresan entre ^^ :<p />
				CALCULAR ESTUDIO:<br />
					^SI^<br />
				FIN DEL CALCULO.-<p />
				<p />
				Vea tambi&eacute;n:<br/>
				<a href="ayuda.do?tema=parametros_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Par&aacute;metros o datos solicitados al momento de ejecuci&oacute;n.</a><br />
				<a href="ayuda.do?tema=bifurcaciones_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">Condiciones o bifurcaciones.</a><br />
				<a href="ayuda.do?tema=calculos_estudio" title="Ayuda sobre este t&oacute;pico" alt="ayuda sobre este t&oacute;pico">C&aacute;lculos.</a><br />
				<%
			}
			if(_opcion.equals("aplicarestudios")){
				%>
				<h4>Aplicaci&oacute;n de Estudios.</h4>
				Simplemente seleccione el estudio que desea aplicar al instrumento. Si el estudio es parametrizado se le solicitar&aacute;n unos datos por pantalla.<br />
				Finalmente obtendr&aacute; los resultados.
				<%
			}
			if(_opcion.equals("generadorsql")){
				%>
				<h4>Generador de subconsultas.</h4>
				La principal intenci&oacute;n del generador es filtrar seg&uacute;n par&aacute;metros personalizados un conjunto de datos o usuarios.<br />
				Inicialmente debe indicar el instrumento donde se obtendr&aacute; la data que ser&aacute; filtrada, posteriormente se le mostrar&aacute;n el conjunto de preguntas disponibles para realizar el filtrado.<br />
				Usted podr&aacute; indicar una pregunta y la condici&oacute;n que debe cumplirse para filtrar los datos. Puede anidar las operaciones.<br />
				Si no desea realizar filtrado, sino, que desea toda la data del instrumento, simplemente haga clic en procesar sin realizar operaciones.<p />
				Ejemplos:<p />
				Queremos s&oacute;lo los usuarios que indicaron que su sexo era femenino y adicionalmente tienen un ingreso anual mayor a BsF 2000:<p />
				<img src="comunes/imagenes/ayuda/generadorsql_1.png" alt="generadorsql_1" width="850"><p /><hr />
				Queremos s&oacute;lo los usuarios que indicaron que su sexo era femenino y adicionalmente tienen un ingreso anual mayor a BsF 2000 &oacute; todos los que indicaron sexo masculino:<p />
				<img src="comunes/imagenes/ayuda/generadorsql_2.png" alt="generadorsql_2" width="850"><hr />

				
				<%
			}
			%>
			<div style='text-align:right'><a href="#" onclick="window.close();" title="Cerrar esta ventana" alt="Cerrar esta ventana">Cerrar Ventana</a></div>
		</td>
	</tr>
</table>
<%@include file="adminfooter.jsp" %>
