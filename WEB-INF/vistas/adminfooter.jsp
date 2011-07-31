				  </center>
				</td>
			</tr>
		</table>
		</center>
		<div class="encapsulador" id="id_encapsulador" onclick="$('#id_encapsulador').hide('slow')">
			<div class="fondo">
				<div class="mensaje_alerta">
					<p><img src="comunes/imagenes/loading2.gif" title="Cargando... por favor, espere un momento.">&nbsp;Cargando... por favor, espere un momento.</p>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="comunes/javascript/jquery.qtip.js"></script>
	<script type="text/javascript">
		$('img[title]').qtip({
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
		$('td[title]').qtip({
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
		$('span[title]').qtip({
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
	</script>
</html>
