package com.siagcee.web;

import com.siagcee.logic.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import java.io.File;
import org.jfree.chart.plot.*;
import java.io.*;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import java.util.Enumeration;

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
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/listarestudiosparaaplicar.jsp");
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

				if(accion.equals("ejecutar")){
					Estudio _miEstudio = null;
					try{
						_miEstudio = new Estudio(admin, micon, Integer.parseInt((String)request.getParameter("idestudio")));
						_miEstudio.validarCodigo();
					}catch(Exception eee){_miEstudio = null;}
					Vector _respuestasDadas = new Vector();
					Enumeration _enu = _miEstudio.getListadoDatosSolicitados().elements();
					while(_enu.hasMoreElements()){
						DatoSolicitadoEstudio _miData = (DatoSolicitadoEstudio)_enu.nextElement();
						if(request.getParameter(_miData.getAcronimo()) != null){
							_miData.setRespuesta((String)request.getParameter(_miData.getAcronimo()));
						}else{
							_miData.setRespuesta("");
						}
						_respuestasDadas.add(_miData);
					}
					_miEstudio.aplicarEstudio(_objetoSeleccionado, _respuestasDadas);
					request.setAttribute("mostrarResultado", "si");
					request.setAttribute("idestudio", _miEstudio);
					request.setAttribute("resultados", _miEstudio.getResultadosEstudio());
					request.setAttribute("resultadosSinCoincidencias", _miEstudio.getResultadosEstudioSinCoincidencias());

					Graficos _grafica = new Graficos();
					_grafica.set_titulo(_miEstudio.getTitulo());

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

					if(request.getParameter("tipo_infor").equals("frecuencia")){
						_grafica.set_tipo_dataSet(Graficos.FRECUENCIA);
						_grafica.setDataSet(_miEstudio.getResultadosEstudio());
					}else if(request.getParameter("tipo_infor").equals("detallado")){
						_grafica.set_tipo_dataSet(Graficos.DETALLADO);
						_grafica.setDataSet(_miEstudio.getResultadosEstudioSinCoincidencias());
					}

					String _dir = getServletContext().getRealPath("/")+"comunes\\graficos\\grafico_"+admin.getUsuarioId()+".jpg";
					JFreeChart _graifoc = _grafica.graficar();
					if(_graifoc != null){
						ChartUtilities.saveChartAsJPEG(new File(_dir), _graifoc, 800, 450);
						request.setAttribute("imagen", "comunes\\graficos\\grafico_"+admin.getUsuarioId()+".jpg");
					}else{
						//error
						request.setAttribute("imagen", "");
					}

					//termino
				}else if(accion.equals("seleccionarestudio")){
					//compruebo si tiene valores a solicitar por pantalla... caso contrario solo ejecuto
					Estudio _miEstudio = null;
					if(request.getParameter("idestudio") != null){
						_miEstudio = new Estudio(admin, micon, Integer.valueOf((String)request.getParameter("idestudio")));
						_miEstudio.validarCodigo();
					}
					request.setAttribute("idestudio", _miEstudio);
					Vector _datosPorPantalla = _miEstudio.getListadoDatosSolicitados();

					if(_datosPorPantalla.isEmpty()){
						//antes debo crear la pregunta que almacenara los resultados
						InstanciaPregunta _insPregTemp = null;
						//ejecuto
						_miEstudio.aplicarEstudio(_objetoSeleccionado, new Vector());
						request.setAttribute("mostrarResultado", "si");
						request.setAttribute("resultados", _miEstudio.getResultadosEstudio());
						request.setAttribute("resultadosSinCoincidencias", _miEstudio.getResultadosEstudioSinCoincidencias());
						request.setAttribute("listadoDeDatosPorPantalla", null);


						Graficos _grafica = new Graficos();
						_grafica.set_titulo(_miEstudio.getTitulo());

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

						if(request.getParameter("tipo_infor").equals("frecuencia")){
							_grafica.set_tipo_dataSet(Graficos.FRECUENCIA);
							_grafica.setDataSet(_miEstudio.getResultadosEstudio());
						}else if(request.getParameter("tipo_infor").equals("detallado")){
							_grafica.set_tipo_dataSet(Graficos.DETALLADO);
							_grafica.setDataSet(_miEstudio.getResultadosEstudioSinCoincidencias());
						}

						String _dir = getServletContext().getRealPath("/")+"comunes\\graficos\\grafico_"+admin.getUsuarioId()+".jpg";
						JFreeChart _graifoc = _grafica.graficar();
						if(_graifoc != null){
							ChartUtilities.saveChartAsJPEG(new File(_dir), _graifoc, 800, 450);
							request.setAttribute("imagen", "comunes\\graficos\\grafico_"+admin.getUsuarioId()+".jpg");
						}else{
							//error
							request.setAttribute("imagen", "");
						}

					}else{
						request.setAttribute("mostrarResultado", "no");
						request.setAttribute("listadoDeDatosPorPantalla", _datosPorPantalla);
					}
					request.setAttribute("estudioACargar", _miEstudio);
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
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/listarestudiosparaaplicar.jsp");
		InstanciaObjeto _objetoSeleccionado = null;
		if(admin != null){
			try{
				if(request.getParameter("objetoatrabajar") != null){
					_objetoSeleccionado = new InstanciaObjeto(admin, micon, Integer.parseInt((String)request.getParameter("objetoatrabajar")));
					request.setAttribute("objetoatrabajar", _objetoSeleccionado);

				}else{
					request.setAttribute("objetoatrabajar", null);
				}

				request.setAttribute("listadoEstudios", Estudio.obtenerEstudiosDeEstructura(admin, micon, _objetoSeleccionado.getObjetoAsociado(), false));

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

