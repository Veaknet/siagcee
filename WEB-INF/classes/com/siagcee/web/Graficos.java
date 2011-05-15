package com.siagcee.web;

import com.siagcee.logic.Estudio;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;


/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 14/04/2010
 * Hora: 03:42:16 PM
 */
public class Graficos {

	public static int FRECUENCIA = 0;
	public static int DETALLADO = 1;

	public static int BARRAS_HORIZONTALES = 0;
	public static int BARRAS_VERTICALES = 1;
	public static int TORTA = 2;
	public static int LINEAS_HORIZONTALES = 3;
	public static int LINEAS_VERTICALES = 4;

	String _titulo = "";
	int _tipo_grafico = BARRAS_HORIZONTALES;
	AbstractDataset _dataset = null;
	int _tipo_dataSet = FRECUENCIA;

	public Graficos(){

	}

	public String get_titulo() {
		return _titulo;
	}

	public void set_titulo(String _titulo) {
		this._titulo = _titulo;
	}

	public int get_tipo_grafico() {
		return _tipo_grafico;
	}

	public void set_tipo_grafico(int _tipo_grafico) {
		this._tipo_grafico = _tipo_grafico;
	}

	public int get_tipo_dataSet() {
		return _tipo_dataSet;
	}

	public void set_tipo_dataSet(int _tipoDataSet) {
		this._tipo_dataSet = _tipoDataSet;
	}

	public void setDataSetVector(Vector _datos, Vector _coincidencias){
		try{
			SimpleDateFormat _dateFormatVisible = new SimpleDateFormat("dd/MM/yyyy");  //formatear la fecha para la visualizacion del usuario
			Enumeration _enu = _datos.elements();
			Enumeration _enu2 = _coincidencias.elements();
			if(this._tipo_grafico == Graficos.BARRAS_HORIZONTALES || this._tipo_grafico == Graficos.BARRAS_VERTICALES){
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					while(_enu.hasMoreElements()){
						Object _nodo = (Object)_enu.nextElement();
						int coincidencias = (Integer)_enu2.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){
								try{
									Long _t = (Long)_nodo;
									DecimalFormat _df = new DecimalFormat("####################");
									_respuesta = _df.format(_t);
								}catch (Exception eee){
									_respuesta = (String)_nodo;
								}
							}
						}
						dataset.setValue(coincidencias, "Respuestas", _respuesta);
					}
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					int contador = 1;
					while(_enu.hasMoreElements()){
						Object _nodo = (Object)_enu.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){
								try{
									Long _t = (Long)_nodo;
									DecimalFormat _df = new DecimalFormat("####################");
									_respuesta = _df.format(_t);
								}catch (Exception eee){
									_respuesta = (String)_nodo;
								}
							}
						}
						dataset.setValue((Number)Double.parseDouble(_respuesta), "Respuestas", contador);
						contador++;
					}
				}
				_dataset = dataset;

			}else if(this._tipo_grafico == Graficos.LINEAS_HORIZONTALES || this._tipo_grafico == Graficos.LINEAS_VERTICALES){
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();

					if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
						Object _nodo;
						while(_enu.hasMoreElements()){
							_nodo = (Object)_enu.nextElement();
							int coincidencias = (Integer)_enu2.nextElement();
							String _respuesta = "";
							try{
								Date _fecha = (Date)_nodo;
								_respuesta = _dateFormatVisible.format(_fecha);
							}catch (Exception e1){
								try{
									Double _t = (Double)_nodo;
									DecimalFormat _df = new DecimalFormat("###############.###############");
									_respuesta = _df.format(_t);
								}catch (Exception ee){
									try{
										Long _t = (Long)_nodo;
										DecimalFormat _df = new DecimalFormat("####################");
										_respuesta = _df.format(_t);
									}catch (Exception eee){
										_respuesta = (String)_nodo;
									}
								}
							}
							dataset.setValue(coincidencias, "Respuestas", _respuesta);
						}
					}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
						int contador = 1;
						while(_enu.hasMoreElements()){
							Object _nodo = (Object)_enu.nextElement();
							String _respuesta = "";
							try{
								Date _fecha = (Date)_nodo;
								_respuesta = _dateFormatVisible.format(_fecha);
							}catch (Exception e1){
								try{
									Double _t = (Double)_nodo;
									DecimalFormat _df = new DecimalFormat("###############.###############");
									_respuesta = _df.format(_t);
								}catch (Exception ee){
									try{
										Long _t = (Long)_nodo;
										DecimalFormat _df = new DecimalFormat("####################");
										_respuesta = _df.format(_t);
									}catch (Exception eee){
										_respuesta = (String)_nodo;
									}
								}
							}
							dataset.setValue((Number)Double.parseDouble(_respuesta), "Respuestas", contador);
							contador++;
						}
					}
					_dataset = dataset;

			}else if(this._tipo_grafico == Graficos.TORTA){

				DefaultPieDataset dataset = new DefaultPieDataset();
				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					Object _nodo;
					while(_enu.hasMoreElements()){
						int coincidencias = (Integer)_enu2.nextElement();
						_nodo = (Object)_enu.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){
								try{
									Long _t = (Long)_nodo;
									DecimalFormat _df = new DecimalFormat("####################");
									_respuesta = _df.format(_t);
								}catch (Exception eee){
									_respuesta = (String)_nodo;
								}
							}
						}
						dataset.setValue(_respuesta, coincidencias);
					}
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					Object _nodo;
					while(_enu.hasMoreElements()){
						_nodo = (Object)_enu.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){
								try{
									Long _t = (Long)_nodo;
									DecimalFormat _df = new DecimalFormat("####################");
									_respuesta = _df.format(_t);
								}catch (Exception eee){
									_respuesta = (String)_nodo;
								}
							}
						}
						dataset.setValue(_respuesta, 1);
					}
				}
				_dataset = dataset;

			}
		}catch (Exception e){e.printStackTrace();_dataset = null;}
	}

	public void setDataSet(Vector _datos){
		try{
			SimpleDateFormat _dateFormatVisible = new SimpleDateFormat("dd/MM/yyyy");  //formatear la fecha para la visualizacion del usuario
			Enumeration _enu = _datos.elements();
			if(this._tipo_grafico == Graficos.BARRAS_HORIZONTALES || this._tipo_grafico == Graficos.BARRAS_VERTICALES){
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					Estudio.NodoResultadoCalculo _nodo;
					while(_enu.hasMoreElements()){
						_nodo = (Estudio.NodoResultadoCalculo)_enu.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo._valor;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo._valor;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){_respuesta = (String)_nodo._valor;}
						}

						dataset.setValue(_nodo.cantidadCoincidencias, "Respuestas", _respuesta);
					}
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					int contador = 1;
					Estudio.NodoResultadoCalculoSinCoincidencias _nodo;
					while(_enu.hasMoreElements()){
						_nodo = (Estudio.NodoResultadoCalculoSinCoincidencias)_enu.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo._valor;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo._valor;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){_respuesta = (String)_nodo._valor;}
						}
						dataset.setValue((Number)Double.parseDouble(_respuesta), "Respuestas", contador);
						contador++;
					}
				}
				_dataset = dataset;

			}else if(this._tipo_grafico == Graficos.LINEAS_HORIZONTALES || this._tipo_grafico == Graficos.LINEAS_VERTICALES){
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();

					if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
						Estudio.NodoResultadoCalculo _nodo;
						while(_enu.hasMoreElements()){
							_nodo = (Estudio.NodoResultadoCalculo)_enu.nextElement();
							String _respuesta = "";
							try{
								Date _fecha = (Date)_nodo._valor;
								_respuesta = _dateFormatVisible.format(_fecha);
							}catch (Exception e1){
								try{
									Double _t = (Double)_nodo._valor;
									DecimalFormat _df = new DecimalFormat("###############.###############");
									_respuesta = _df.format(_t);
								}catch (Exception ee){_respuesta = (String)_nodo._valor;}
							}

							dataset.setValue(_nodo.cantidadCoincidencias, "Respuestas", _respuesta);
						}
					}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
						int contador = 1;
						Estudio.NodoResultadoCalculoSinCoincidencias _nodo;
						while(_enu.hasMoreElements()){
							_nodo = (Estudio.NodoResultadoCalculoSinCoincidencias)_enu.nextElement();
							String _respuesta = "";
							try{
								Date _fecha = (Date)_nodo._valor;
								_respuesta = _dateFormatVisible.format(_fecha);
							}catch (Exception e1){
								try{
									Double _t = (Double)_nodo._valor;
									DecimalFormat _df = new DecimalFormat("###############.###############");
									_respuesta = _df.format(_t);
								}catch (Exception ee){_respuesta = (String)_nodo._valor;}
							}
							dataset.setValue((Number)Double.parseDouble(_respuesta), "Respuestas", contador);
							contador++;
						}
					}
					_dataset = dataset;

			}else if(this._tipo_grafico == Graficos.TORTA){

				DefaultPieDataset dataset = new DefaultPieDataset();
				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					Estudio.NodoResultadoCalculo _nodo;
					while(_enu.hasMoreElements()){
						_nodo = (Estudio.NodoResultadoCalculo)_enu.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo._valor;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo._valor;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){_respuesta = (String)_nodo._valor;}
						}
						dataset.setValue(_respuesta, _nodo.cantidadCoincidencias);
					}
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					Estudio.NodoResultadoCalculoSinCoincidencias _nodo;
					while(_enu.hasMoreElements()){
						_nodo = (Estudio.NodoResultadoCalculoSinCoincidencias)_enu.nextElement();
						String _respuesta = "";
						try{
							Date _fecha = (Date)_nodo._valor;
							_respuesta = _dateFormatVisible.format(_fecha);
						}catch (Exception e1){
							try{
								Double _t = (Double)_nodo._valor;
								DecimalFormat _df = new DecimalFormat("###############.###############");
								_respuesta = _df.format(_t);
							}catch (Exception ee){_respuesta = (String)_nodo._valor;}
						}
						dataset.setValue(_respuesta, 1);
					}
				}
				_dataset = dataset;

			}
		}catch (Exception e){e.printStackTrace();_dataset = null;}

	}

	private AbstractDataset getDataSet(){
		return this._dataset;
	}

	public JFreeChart graficar(){
		JFreeChart chart = null;
		try{
			if(this._tipo_grafico == Graficos.BARRAS_HORIZONTALES){

				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					chart = ChartFactory.createBarChart3D(this.get_titulo(), "Respuestas", "Frecuencia", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.HORIZONTAL, false, true, false);
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					chart = ChartFactory.createBarChart3D(this.get_titulo(), "Encuestado", "Respuesta", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.HORIZONTAL, false, true, false);
				}

			}else if(this._tipo_grafico == Graficos.BARRAS_VERTICALES){

				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					chart = ChartFactory.createBarChart3D(this.get_titulo(), "Respuestas", "Frecuencia", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.VERTICAL, false, true, false);
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					chart = ChartFactory.createBarChart3D(this.get_titulo(), "Encuestado", "Respuesta", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.VERTICAL, false, true, false);
				}

			}else if(this._tipo_grafico == Graficos.LINEAS_HORIZONTALES){

				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					chart = ChartFactory.createLineChart(this.get_titulo(), "Respuestas", "Frecuencia", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.HORIZONTAL, false, true, false);
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					chart = ChartFactory.createLineChart(this.get_titulo(), "Encuestado", "Respuesta", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.HORIZONTAL, false, true, false);
				}

			}else if(this._tipo_grafico == Graficos.LINEAS_VERTICALES){

				if(this.get_tipo_dataSet() == Graficos.FRECUENCIA){
					chart = ChartFactory.createLineChart(this.get_titulo(), "Respuestas", "Frecuencia", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.VERTICAL, false, true, false);
				}else if(this.get_tipo_dataSet() == Graficos.DETALLADO){
					chart = ChartFactory.createLineChart(this.get_titulo(), "Encuestado", "Respuesta", (DefaultCategoryDataset)this.getDataSet(), PlotOrientation.VERTICAL, false, true, false);
				}

			}else if(this._tipo_grafico == Graficos.TORTA){

				chart = ChartFactory.createPieChart3D(this.get_titulo(), (DefaultPieDataset)this.getDataSet(), true, true, false);

				PiePlot3D plot = (PiePlot3D) chart.getPlot();
				plot.setStartAngle(290);
				plot.setDepthFactor(0.1f);
				plot.setDirection(Rotation.CLOCKWISE);
				plot.setForegroundAlpha(0.5f);

			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		return chart;
	}
}

