
package com.siagcee.logic;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 24/11/2009
 * Hora: 01:21:48 PM
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class RespuestasPosibles extends ObjetoBase{

	private int idRespuesta;
	private String respuesta;


	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public RespuestasPosibles(Usuario _usuario, Connection _miConexion) {
		super(_usuario, _miConexion);
		respuesta = new String("");
		idRespuesta = -1;
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	//si conocemos el ID de la respuesta a cargar se indica de inmediato.
	public RespuestasPosibles(Usuario _usuario, Connection _miConexion, int _idRespuesta) {
		super(_usuario, _miConexion);
		respuesta = new String("");
		idRespuesta = _idRespuesta;
		this.recargarRespuestaDeBD();
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	//si conocemos el ID de la respuesta a cargar se indica de inmediato.
	public RespuestasPosibles(Usuario _usuario, Connection _miConexion, String _respuesta) {
		super(_usuario, _miConexion);
		respuesta = _respuesta;
		idRespuesta = -1;
	}

	//obtengo el ID de la respuesta
	public int getId() {
		return idRespuesta;
	}

	//establezco el ID de la respuesta
	private void setId(int _idRespuesta) {
		idRespuesta = _idRespuesta;
	}

	//texto respuesta
	public String getRespuesta() {
		return respuesta;
	}

	//actualiza la respuesta en la BD.
	//
	//no se ingresa en la BD debido a que no hay padre (pregunta) asociado
	//solo se ingresa el texto en el objeto
	public void setRespuesta(String _respuesta){
		if(this.esEditable()){
			if(!_respuesta.equals("")){
				try{
					if (getCargadaDeBD()) {
						//ejecuto UPDATE
						PreparedStatement pstmt = getConexion().prepareStatement("UPDATE pool_respuestas_posibles SET respuesta = ? WHERE id_pool_respuestas_posibles = ?");
						pstmt.setString(1, _respuesta);
						pstmt.setInt(2, getId());
						pstmt.execute();
					}
					respuesta = _respuesta;
				}catch(Exception e){}
			}
		}
	}

	//si la respuesta que existe en el objeto fue cargada de BD realizo un UPDATE
	//caso contrario implica la realizacion de un INSERT ya que la respuesta no vino de BD
	public void setRespuesta(String _respuesta, Pregunta padre) {
		if((!_respuesta.equals("")) && (padre != null)){
			try {
				if (getCargadaDeBD()) {
					setRespuesta(_respuesta);
				}else {
					//ejecuto INSERT
					PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_pool_respuestas_posibles') AS numero");
					ResultSet _rs = _pstmt.executeQuery();
					_rs.next();
					int siguiente = _rs.getInt("numero");

					PreparedStatement pstmt = getConexion().prepareStatement("INSERT INTO pool_respuestas_posibles(id_pool_respuestas_posibles, id_pool_preguntas, respuesta, creado_por) VALUES (?, ?, ?, ?)");
					pstmt.setInt(1, siguiente);
					pstmt.setInt(2, padre.getId());
					pstmt.setString(3, _respuesta);
					pstmt.setInt(4, getUsuario().getUsuarioId());
					pstmt.execute();
					this.setCargadaDeBD(true);
					this.setId(siguiente);
				}
				respuesta = _respuesta;
			}
			catch (Exception e) {
			}
		}
	}

	//elimina la respuesta incluyendo la BD
	public void delRespuesta() {
		if(this.esEditable()){
			try {
				PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM pool_respuestas_posibles WHERE id_pool_respuestas_posibles = ?");
				pstmt.setInt(1, getId());
				pstmt.execute();
				respuesta = new String("");
				idRespuesta = -1;
				this.setCargadaDeBD(false);
			}
			catch (Exception e) {
			}
		}
	}

	//indica si esta Respuesta puede ser modificada
	public boolean esEditable(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM pool_respuestas_posibles WHERE id_pool_respuestas_posibles = ? AND id_pool_preguntas NOT IN(SELECT id_pool_preguntas FROM instancia_preguntas)");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			return false;
		}
		return false;
	}

	//se asocia una pregunta a esta respuesta
	public void asociarPregunta(Pregunta _padre){
		setRespuesta(this.getRespuesta(), _padre);
	}

	//Cargar un vector de tipo RespuestasPosibles de una pregunta (id preguntaestatica) dada
	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public static Vector obtenerRespuestas(Usuario _usuario, Connection _miConexion, Pregunta padre) {
		return obtenerRespuestas(_usuario, _miConexion, padre, false);
	}

	//Cargar un vector de tipo RespuestasPosibles de una pregunta (id preguntaestatica) dada
	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public static Vector obtenerRespuestas(Usuario _usuario, Connection _miConexion, Pregunta padre, boolean _soloPublico) {
		Vector _lista = new Vector();
		ResultSet rs = null;
		RespuestasPosibles ObjRespTemp = null;

		rs = obtenerDataPoolRespuestas(_usuario, _miConexion, padre, _soloPublico);
		try {
			while(rs.next()) {
				ObjRespTemp = new RespuestasPosibles(_usuario, _miConexion);
				ObjRespTemp.idRespuesta = rs.getInt("id_pool_respuestas_posibles");
				ObjRespTemp.respuesta = rs.getString("respuesta");
				ObjRespTemp.cargadaDeBD = true;
				_lista.add(ObjRespTemp);
			}
		}
		catch (Exception e) {
			return new Vector();
		}
		return _lista;
	}

	//dado un id usuario, la conexion a la BD, y la pregunta, se obtienen todas las respuestas asociadas
	//realmente solo es usado cuando se cargan las respuestas en un Vector
	//desde el metodo obtenerRespuestas(int _idUsuario, Connection _miConexion, int _idPregunta).
	private static ResultSet obtenerDataPoolRespuestas(Usuario _usuario, Connection _miConexion, Pregunta padre, boolean _soloPublico) {
		ResultSet rs = null;
		PreparedStatement pstmt;
		try {
			if(_soloPublico){
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_respuestas_posibles WHERE id_pool_preguntas = ?");
				pstmt.setInt(1, padre.getId());
			}else{
                //pstmt = _miConexion.prepareStatement("SELECT * FROM pool_respuestas_posibles WHERE id_pool_preguntas = ? AND creado_por = ?");
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_respuestas_posibles WHERE id_pool_preguntas = ?");
				pstmt.setInt(1, padre.getId());
				//pstmt.setInt(2, _usuario.getUsuarioId());
			}
			rs = pstmt.executeQuery();
		}
		catch (Exception e) {
			return null;
		}
		return rs;
	}

	//recarga el objeto desde la BD tomando en cuenta el ID de la respuesta
	public void recargarRespuestaDeBD() {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = this.getConexion().prepareStatement("SELECT * FROM pool_respuestas_posibles WHERE id_pool_respuestas_posibles = ?");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.respuesta = rs.getString("respuesta");
				this.setId(rs.getInt("id_pool_respuestas_posibles"));
				this.setCargadaDeBD(true);
			}else{
				this.respuesta = rs.getString("");
				this.setId(-1);
				this.setCargadaDeBD(false);
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println(pstmt.toString());
		}
	}
}