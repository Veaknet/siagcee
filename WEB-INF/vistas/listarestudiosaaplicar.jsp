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

<script type="text/javascript" charset="UTF-8">
function validarEntero(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
  var er = /^[-]*[0-9]+$/;
  if(mielem.value.match(er) && mielem.value > 0){
	  return true;
  }else{
	  alert("Solo pueden ingresarse numeros enteros mayores a 0.");
	  mielem.value = "1";
	  mielem.focus();
	  return false;
  }
}

function validarDouble(_nombreElem){
	var mielem = document.getElementById(_nombreElem);
  var er = /^[-+]?[0-9]+(\.[0-9]+)?$/;
  if(mielem.value.match(er)){
	  return true;
  }else{
	  alert("Solo pueden ingresarse numeros con o sin decimales.");
	  mielem.value = "0.0";
	  mielem.focus();
	  return false;
  }
}

var array_estudios = new Array();

function seleccionardato(_elem){
  var _id = _elem.value;
  var _selec_datos = $("#datovisualizacion");
  var _selec_datos_txt = $("#datovisualizaciontxt");

  if(_id == -1){
    _selec_datos.css("display","none");
    _selec_datos_txt.css("display","none");
  }else{
	for(var i=0;i<_totalDatosInstr;i++){
	  if(_id == _listaDatos[i]["id"]){
		if(_listaDatos[i]["tipopregunta"] == 31 || _listaDatos[i]["tipopregunta"] == 32){
		  _selec_datos.css("display","block");
		  _selec_datos_txt.css("display","block");
		}else{
		  _selec_datos.css("display","none");
		  _selec_datos_txt.css("display","none");
		}
		break;
	  }else{
		continue;
	  }
	}
  }
}

function seleccionar(_elem){
	var _id = _elem.value;
  var _selec_datos = $("#datoseleccionado");
  var _selec_datos_txt = $("#datoseleccionadotxt");
  if(_id.indexOf('_default_') == 0){
    _selec_datos.css("display","block");
    _selec_datos_txt.css("display","block");

  }else{
    _selec_datos.css("display","none");
    _selec_datos_txt.css("display","none");
  }

	var _descr = document.getElementById('descripcion');
	var _boto = document.getElementById('enviar');
	_boto.disabled = false;

	for(var i=0;i<array_estudios.length;i++){
	  if(_id == array_estudios[i]['id']){
		  _descr.innerHTML = "<h4>Estudio:"+array_estudios[i]['titulo']+"</h4>Descripci&oacute;n:<br />"+array_estudios[i]['descripcion'];
	  }
	}
}

function validarForm(){
  var _id = $("#idestudio").val();
  var _selec_datos = $("#datoseleccionado").val();

  if(_id == '-1'){
    alert("No ha indicado el estudio a aplicar");
    return false;
  }else if(_id.indexOf('_default_') == 0){
    if(_selec_datos != -1){
      var tipo_Pre = 30;
      for(var i=0;i<_totalDatosInstr;i++){
        if(_id == _listaDatos[i]["id"]){
          tipo_Pre = _listaDatos[i]["tipopregunta"];
          break;
        }else{
          continue;
        }
      }
      if(tipo_Pre == 31 || tipo_Pre == 32){
        return validarEntero("datovisualizacion");
      }else{
        return true;
      }
    }else{
      alert("No ha indicado el campo sobre el que se realizara el estudio");
      return false;
    }
  }else{
    return true;
  }
  alert("Debe revisar los datos provistos para continuar.");
  return false;
}

</script>

<%
Vector _listaDeEstudios = new Vector();
if(request.getAttribute("listadoEstudios") != null){
	_listaDeEstudios = (Vector)request.getAttribute("listadoEstudios");
	Collections.sort(_listaDeEstudios, new OrdenadorEstudios(OrdenadorEstudios.TITULO));
}

Vector _listaDeDatos = new Vector();
if(request.getAttribute("listadoDatos") != null){
  _listaDeDatos = (Vector)request.getAttribute("listadoDatos");
  Collections.sort(_listaDeDatos, new OrdenadorInstanciaPreguntas(OrdenadorInstanciaPreguntas.PREGUNTA));
}

InstanciaObjeto objetoatrabajar = null;
if(request.getAttribute("objetoatrabajar") != null){
	objetoatrabajar = (InstanciaObjeto)request.getAttribute("objetoatrabajar");
}
%>

<%@include file="adminheader.jsp" %>

<script type="text/javascript">
  var _listaDatos = new Array();
  var _totalDatosInstr = 0;
 <%
  if(!_listaDeDatos.isEmpty()){
    Enumeration _enu2 = _listaDeDatos.elements();
    InstanciaPregunta _prg = null;
    int _cont = 0;
    while (_enu2.hasMoreElements()){
      _prg = (InstanciaPregunta)_enu2.nextElement();
      out.println("_listaDatos["+_cont+"] = new Array();");
      out.println("_listaDatos["+_cont+"]['id'] = '"+_prg.getId()+"';");
      out.println("_listaDatos["+_cont+"]['pregunta'] = '"+UtilidadesVarias.reemplazarCaracteres(_prg.getAcronimo(), "'", "\"")+"';");
      out.println("_listaDatos["+_cont+"]['tipopregunta'] = '"+_prg.getTipoPregunta()+"';");
      out.println("_listaDatos["+_cont+"]['ordenpregunta'] = '"+_prg.getOrden()+"';");
      _cont++;
    }
    out.println("_totalDatosInstr = "+_cont+";");
  }
 %>

	$(document).ready(function(){
		$("#link_estudios").css("color","red");
	});
</script>

<table cellspacing="4" cellpadding="4" class="tablasecundaria">
	<tr>
		<td valign="top" align="left">
      <h2>Aplicar Estudio.</h2>
      <h4>Instrumento: <% out.print(objetoatrabajar.getObjeto());%></h4>
    </td>
  </tr>
  <tr>
    <td valign="top" align="left">
      <%
      if(objetoatrabajar != null){
      %>
        <form action="aplicadorestudios" method="post" onsubmit="return validarForm();">
          <input type="hidden" value="<% out.print(objetoatrabajar.getId()); %>" id="objetoatrabajar" name="objetoatrabajar">
          <input type="hidden" value="seleccionarestudio" id="accion" name="accion">
          <table cellpadding="4" cellspacing="4">
            <tr>
              <td style="text-align:right;width:150px">
                <label>Estudio a aplicar:</label>
              </td>
              <td style="text-align:left;">
                <select id="idestudio" name="idestudio" onchange="seleccionar(this);">
                  <option value="-1">Seleccione...</option>
                  <optgroup label="Estudios predefinidos">
                    <option value="_default_histo">Histograma de frecuencia</option>
                  </optgroup>
                  <%
                    if(!_listaDeEstudios.isEmpty()){
                      %>
                        <optgroup label="Estudios creados por el usuario">
                      <%
                    }
                    if(!_listaDeEstudios.isEmpty()){
                      Enumeration _enu = _listaDeEstudios.elements();
                      Estudio _est = null;
                      while(_enu.hasMoreElements()){
                        _est = (Estudio)_enu.nextElement();
                        out.println("<option value='"+_est.getId()+"'>"+_est.getTitulo()+"</option>");
                      }
                    }
                    if(!_listaDeEstudios.isEmpty()){
                      %>
                        </optgroup>
                      <%
                    }
                  %>
                </select>
              </td>
            </tr>
            <tr>
              <td style="text-align:right;width:150px">
                <label id="datoseleccionadotxt" style="display:none">campo deseado:</label>
              </td>
              <td style="text-align:left;">
                <select id="datoseleccionado" name="datoseleccionado" onchange="seleccionardato(this);" style="display:none;">
                  <option value="-1">Seleccione...</option>
                  <%
                  if(!_listaDeDatos.isEmpty()){
                    Enumeration _enu = _listaDeDatos.elements();
                    InstanciaPregunta _prg = null;
                    while(_enu.hasMoreElements()){
                      _prg = (InstanciaPregunta)_enu.nextElement();
                      out.println("<option value='"+_prg.getId()+"'>"+_prg.getAcronimo()+"</option>");
                    }
                  }
                  %>
                </select>
              </td>
            </tr>
            <tr>
              <td style="text-align:right;width:150px">
                <label id="datovisualizaciontxt" style="display:none">Cantidad de intervalos:</label>
              </td>
              <td style="text-align:left;">
                <input size="8" type="text" value="1" id="datovisualizacion" onchange="return validarEntero('datovisualizacion');" style="display:none">
              </td>
            </tr>
            <tr>
              <td>
                &nbsp;
              </td>
              <td style="text-align:left;">
                <input type="submit" value="Ejecutar" id="enviar" name="enviar">
              </td>
            </tr>
          </table>
        </form>
      <p />
      Haga clic <a href='generadorestudios?objetoatrabajar=<% out.print(objetoatrabajar.getObjetoAsociado().getId()); %>'>aqu&iacute;</a> para crear un nuevo estudio.
      <%
      }else{
        out.println("No se indic&oacute; una estructura con la que se pueda trabajar.");
      }
      %>
    </td>
  </tr>
</table>
<%@include file="adminfooter.jsp" %>
