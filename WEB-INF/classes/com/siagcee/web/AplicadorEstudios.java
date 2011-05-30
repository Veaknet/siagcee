package com.siagcee.web;

import com.siagcee.logic.*;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 25/01/2010
 * Hora: 09:52:46 AM
 */
public class AplicadorEstudios extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/listarestudiosaaplicar.jsp");
		if(admin != null){
			String accion = "";
			try{
				InstanciaObjeto _objetoSeleccionado = null;
				if(request.getParameter("objetoatrabajar") != null){
					_objetoSeleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					request.setAttribute("objetoatrabajar", _objetoSeleccionado);
				}else{
					request.setAttribute("objetoatrabajar", null);
				}

				if(request.getParameter("accion") != null){
					accion = (String)request.getParameter("accion");
				}

				if(accion.equals("aplicarhistograma")){
					InstanciaPregunta _dato = null;
					try{
						_dato = new InstanciaPregunta(admin, micon, Integer.parseInt((String)request.getParameter("datoseleccionado")));
					}catch (Exception e1){
						e1.printStackTrace();
					}
					if(_dato != null){
						Graficos _grafica = new Graficos();
						_grafica.set_titulo("Histograma de frecuencia");

						Vector _respuestas = Respuesta.todasRespuestas(null, micon, _dato, _objetoSeleccionado);
						Vector _respuestasFiltradas = new Vector();
						Vector _coincidencias = new Vector();
						if(_dato.getTipoPregunta() == 31 || _dato.getTipoPregunta() == 32){
							//es numerica, entonces se dividirá el conjunto de datos segun especificado
							int _intervalos = 1;
							if(request.getParameter("datovisualizacion") != null){
								_intervalos = Integer.parseInt(request.getParameter("datovisualizacion"));
							}
							if(_dato.getTipoPregunta() == 31){
								long _min = Long.MAX_VALUE;
								long _max = Long.MIN_VALUE;
								Respuesta _resp = null;
								Enumeration _enu = _respuestas.elements();
								while(_enu.hasMoreElements()){
									_resp = (Respuesta)_enu.nextElement();
									if(_resp.getRespuestaAbiertaInt() < _min){
										_min = _resp.getRespuestaAbiertaInt();
									}
									if(_resp.getRespuestaAbiertaInt() > _max){
										_max = _resp.getRespuestaAbiertaInt();
									}
								}
								request.setAttribute("min", _min);
								request.setAttribute("max", _max);
								//agrupo las respuestas
								if(_intervalos > _max - _min + 1){
									//se piden mas intervalos que la cantidad de respuestas posibles, por lo que se tomará 1 como cantidad
									//de elementos por intervalo
									//_intervalos = (_max - _min + 1);
								}
								long _cantInter = Math.round((_max - _min + 1) / _intervalos);
								int[] _array_resul = new int[_intervalos];
								for(int i=0;i < Integer.parseInt(String.valueOf(_intervalos));i++){
									_array_resul[i] = 0;
								}
								_enu = _respuestas.elements();
								while(_enu.hasMoreElements()){
									_resp = (Respuesta)_enu.nextElement();
									long _indice = Math.round(Math.round((_resp.getRespuestaAbiertaInt() - _min + 1) / _cantInter) + (Math.round((_resp.getRespuestaAbiertaInt() - _min + 1) / _cantInter) - ((_resp.getRespuestaAbiertaInt() - _min + 1) / _cantInter)));
									if(_indice < 0){
										_indice = 0;
									}else if(_indice >= _intervalos){
										_indice = _intervalos - 1;
									}
									_array_resul[Integer.parseInt(String.valueOf(_indice))]++;
								}
								DecimalFormat _df = new DecimalFormat("###,###");
								_df.setGroupingSize(3);
								for(int i=0;i < _intervalos;i++){
									if((i + 1) == _intervalos){
										_respuestasFiltradas.add(""+_df.format(_min+(i*_cantInter))+" - "+_df.format(_max));
									}else if(i == 0){
										_respuestasFiltradas.add(""+_df.format(_min)+" - "+_df.format(_min+((i+1)*_cantInter)-1));
									}else{
										_respuestasFiltradas.add(""+_df.format(_min+(i*_cantInter))+" - "+_df.format(_min+((i+1)*_cantInter)-1));
									}
									_coincidencias.add(_array_resul[i]);
								}
							}else{
								double _min = Double.MAX_VALUE;
								double _max = Double.MIN_VALUE;
								Respuesta _resp = null;
								Enumeration _enu = _respuestas.elements();
								while(_enu.hasMoreElements()){
									_resp = (Respuesta)_enu.nextElement();
									if(_resp.getRespuestaAbiertaDouble() < _min){
										_min = _resp.getRespuestaAbiertaDouble();
									}
									if(_resp.getRespuestaAbiertaDouble() > _max){
										_max = _resp.getRespuestaAbiertaDouble();
									}
								}
								request.setAttribute("min", _min);
								request.setAttribute("max", _max);
								//agrupo las respuestas
								if(_intervalos > _max - _min + 1){
									//se piden mas intervalos que la cantidad de respuestas posibles, por lo que se tomará 1 como cantidad
									//de elementos por intervalo
									//_intervalos = (_max - _min + 1);
								}
								long _cantInter = Math.round((_max - _min + 1) / _intervalos);
								int[] _array_resul = new int[_intervalos];
								for(int i=0;i < Integer.parseInt(String.valueOf(_intervalos));i++){
									_array_resul[i] = 0;
								}
								_enu = _respuestas.elements();
								while(_enu.hasMoreElements()){
									_resp = (Respuesta)_enu.nextElement();
									for(int i=0;i < _intervalos;i++){
										if((i + 1) == _intervalos){
											if(_resp.getRespuestaAbiertaDouble() >= (_min+(i*_cantInter)) && _resp.getRespuestaAbiertaDouble() <= _max){
												_array_resul[i]++;
												break;
											}
										}else if(i == 0){
											if(_resp.getRespuestaAbiertaDouble() >= _min && _resp.getRespuestaAbiertaDouble() <= (_min+((i+1)*_cantInter)-0.00000000000001d)){
												_array_resul[i]++;
												break;
											}
										}else{
											if(_resp.getRespuestaAbiertaDouble() >= (_min+(i*_cantInter)) && _resp.getRespuestaAbiertaDouble() <= (_min+((i+1)*_cantInter)-0.0000000000001d)){
												_array_resul[i]++;
												break;
											}
										}
									}
								}
								DecimalFormat _df = new DecimalFormat("###,###.######");
								for(int i=0;i < _intervalos;i++){
									if((i + 1) == _intervalos){
										_respuestasFiltradas.add(""+_df.format(_min+(i*_cantInter))+" - "+_df.format(_max));
									}else if(i == 0){
										_respuestasFiltradas.add(""+_df.format(_min)+" - "+_df.format(_min+((i+1)*_cantInter)-0.00001d));
									}else{
										_respuestasFiltradas.add(""+_df.format(_min+(i*_cantInter))+" - "+_df.format(_min+((i+1)*_cantInter)-0.00001d));
									}
									_coincidencias.add(_array_resul[i]);
								}
							}
							if(request.getParameter("tipo_grafico").equals("barras_horizontales")){
								_grafica.set_tipo_grafico(Graficos.BARRAS_HORIZONTALES);
							}else if(request.getParameter("tipo_grafico").equals("barras_verticales")){
								_grafica.set_tipo_grafico(Graficos.BARRAS_VERTICALES);
							}else if(request.getParameter("tipo_grafico").equals("torta")){
								_grafica.set_tipo_grafico(Graficos.TORTA);
							}else if(request.getParameter("tipo_grafico").equals("lineas_verticales")){
								_grafica.set_tipo_grafico(Graficos.LINEAS_VERTICALES);
							}else if(request.getParameter("tipo_grafico").equals("lineas_horizontales")){
								_grafica.set_tipo_grafico(Graficos.LINEAS_HORIZONTALES);
							}

							request.setAttribute("resultados", _respuestasFiltradas);
							request.setAttribute("intervalos", _intervalos);
						}else{
							Respuesta _resp = null;
							Enumeration _enu = _respuestas.elements();
							Enumeration _enu2 = _respuestasFiltradas.elements();
							while(_enu.hasMoreElements()){
								_resp = (Respuesta)_enu.nextElement();
								if(_dato.getTipoPregunta() == 33){
									_enu2 = _respuestasFiltradas.elements();
									boolean _encontrada = false;
									int i = 0;
									int conteoActual = 0;
									while(_enu2.hasMoreElements()){
										Date _fecha = (Date)_enu2.nextElement();
										if(_fecha.equals(_resp.getRespuestaAbiertaDate())){
											conteoActual = (Integer)_coincidencias.elementAt(i);
											_coincidencias.removeElementAt(i);
											_coincidencias.add(i, (conteoActual + 1));
											_encontrada = true;
											break;
										}
										i++;
									}
									if(!_encontrada){
										_respuestasFiltradas.add(_resp.getRespuestaAbiertaDate());
										_coincidencias.add(1);
									}
								}else if (_dato.getTipoPregunta() == 1 || _dato.getTipoPregunta() == 2){
									_enu2 = _respuestasFiltradas.elements();
									boolean _encontrada = false;
									int i = 0;
									int conteoActual = 0;
									while(_enu2.hasMoreElements()){
										String _respStr = (String)_enu2.nextElement();
										if(_respStr.equals(_resp.getRespuestaCerrada().getRespuesta())){
											conteoActual = (Integer)_coincidencias.elementAt(i);
											_coincidencias.removeElementAt(i);
											_coincidencias.add(i, (conteoActual + 1));
											_encontrada = true;
											break;
										}
										i++;
									}
									if(!_encontrada){
										_respuestasFiltradas.add(_resp.getRespuestaCerrada().getRespuesta());
										_coincidencias.add(1);
									}
								}else{
									_enu2 = _respuestasFiltradas.elements();
									boolean _encontrada = false;
									int i = 0;
									int conteoActual = 0;
									while(_enu2.hasMoreElements()){
										String _respStr = (String)_enu2.nextElement();
										if(_respStr.equals(_resp.getRespuestaAbiertaTexto())){
											conteoActual = (Integer)_coincidencias.elementAt(i);
											_coincidencias.removeElementAt(i);
											_coincidencias.add(i, (conteoActual + 1));
											_encontrada = true;
											break;
										}
										i++;
									}
									if(!_encontrada){
										_respuestasFiltradas.add(_resp.getRespuestaAbiertaTexto());
										_coincidencias.add(1);
									}
								}
							}
							_grafica.set_tipo_grafico(Graficos.TORTA);
							if(request.getParameter("tipo_grafico").equals("barras_horizontales")){
								_grafica.set_tipo_grafico(Graficos.BARRAS_HORIZONTALES);
							}else if(request.getParameter("tipo_grafico").equals("barras_verticales")){
								_grafica.set_tipo_grafico(Graficos.BARRAS_VERTICALES);
							}else if(request.getParameter("tipo_grafico").equals("torta")){
								_grafica.set_tipo_grafico(Graficos.TORTA);
							}else if(request.getParameter("tipo_grafico").equals("lineas_verticales")){
								_grafica.set_tipo_grafico(Graficos.LINEAS_VERTICALES);
							}else if(request.getParameter("tipo_grafico").equals("lineas_horizontales")){
								_grafica.set_tipo_grafico(Graficos.LINEAS_HORIZONTALES);
							}
							request.setAttribute("resultados", _respuestasFiltradas);
						}
						_grafica.set_tipo_dataSet(Graficos.FRECUENCIA);
						_grafica.setDataSetVector(_respuestasFiltradas, _coincidencias);

						String _dir = getServletContext().getRealPath("/")+"comunes/graficos/grafico_"+admin.getUsuarioId()+".jpg";
						JFreeChart _graifoc = _grafica.graficar();
						request.setAttribute("mostrarResultado", "si");
						if(_graifoc != null){
							ChartUtilities.saveChartAsJPEG(new File(_dir), _graifoc, 800, 450);
							request.setAttribute("imagen", "comunes/graficos/grafico_"+admin.getUsuarioId()+".jpg");
						}else{
							//error
							request.setAttribute("imagen", "");
						}
						request.setAttribute("dato", _dato);
					}
				}

				if(accion.equals("seleccionarestudio")){
					if(request.getParameter("idestudio") != null){
						EstudioPerso.resetInstance();
						EstudioPerso.getInstance().setConexion(micon);
						EstudioPerso.getInstance().setAdmin(admin);
						EstudioPerso.getInstance().cargar(Integer.valueOf((String)request.getParameter("idestudio")));
						EstudioPerso.getInstance().set_obj(_objetoSeleccionado);
						request.setAttribute("codigoestudio", EstudioPerso.getInstance().get_cod());
						EstudioPerso.getInstance().ejecutaEstudio();

						Vector _listaTiposDeDatos = _objetoSeleccionado.getObjetoAsociado().getPreguntas(true);

						request.setAttribute("datos", _listaTiposDeDatos);
						request.setAttribute("objetoatrabajar", _objetoSeleccionado);

						request.setAttribute("mostrarguardar", false);
						request.setAttribute("mostrarresultados", true);

						//FALTA ACTUALIZAR LOS CAMPOS!!!!!!

					}
					view = request.getRequestDispatcher("WEB-INF/vistas/crearestudioperso.jsp");
				}

				view.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF8");
		HttpSession sesion = request.getSession();
		Connection micon = (Connection) getServletContext().getAttribute("conexion");
		Administrador admin = (Administrador) sesion.getAttribute("administrador");
    	RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/listarestudiosaaplicar.jsp");
		InstanciaObjeto _objetoSeleccionado = null;
		if(admin != null){
			try{
				if(request.getParameter("objetoatrabajar") != null){
					_objetoSeleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					request.setAttribute("objetoatrabajar", _objetoSeleccionado);

				}else{
					request.setAttribute("objetoatrabajar", null);
				}

				try{
				  request.setAttribute("listadoDatos", _objetoSeleccionado.getObjetoAsociado().getPreguntas(false));
				}catch (Exception e1){
				  request.setAttribute("listadoDatos", null);
				}

				Vector _estudios = EstudioPerso.obtenerEstudiosDeEstructura(admin, micon, _objetoSeleccionado.getObjetoAsociado(), false);

				Vector _listaDeEstudios = new Vector();
				Enumeration _enu = _estudios.elements();
				while(_enu.hasMoreElements()){
					EstudioPerso.getInstance().setAdmin(admin);
					EstudioPerso.getInstance().setConexion(micon);
					EstudioPerso.getInstance().cargar((Integer)_enu.nextElement());
					_listaDeEstudios.add(EstudioPerso.getInstance());
				}
				request.setAttribute("listadoEstudios", _listaDeEstudios);

				view.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
				view = request.getRequestDispatcher("autenticar.do");
				view.forward(request, response);
			}
		}else{
			//sesion no iniciada
			view = request.getRequestDispatcher("autenticar.do");
			view.forward(request, response);
		}
	}

}

