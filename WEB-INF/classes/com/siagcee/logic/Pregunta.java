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
import java.util.Enumeration;
import java.util.Vector;

public class Pregunta extends ObjetoBase{

	int idPregunta;
	String pregunta;
	Vector misRespuestas;
	int tipoPregunta;
	//1 seleccion simple
	//2 seleccion multiple
	//30 abierta texto
	//31 abierta numerica sin decimales
	//32 abierta numerica con decimales
	//33 abierta numerica fecha
	//100 usa estudio

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public Pregunta(Usuario _usuario, Connection _miConexion) {
		super(_usuario, _miConexion);
		pregunta = new String("");
		misRespuestas = new Vector();
		idPregunta = -1;
		tipoPregunta = 30;
		publico = true;
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public Pregunta(Usuario _usuario, Connection _miConexion, int _idPregunta) {
		super(_usuario, _miConexion);
		pregunta = new String("");
		misRespuestas = new Vector();
		idPregunta = _idPregunta;
		tipoPregunta = 30;
		publico = true;
		this.recargarPreguntaDeBD();
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public Pregunta(Usuario _usuario, Connection _miConexion, String _pregunta) {
		super(_usuario, _miConexion);
		misRespuestas = new Vector();
		if(!_pregunta.equals("")){
			pregunta = _pregunta;
		}
		tipoPregunta = 30;
		publico = true;
		this.ingresaABd();
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public Pregunta(Usuario _usuario, Connection _miConexion, String _pregunta, int _tipoPregunta) {
		super(_usuario, _miConexion);
		misRespuestas = new Vector();
		if(!_pregunta.equals("")){
			pregunta = _pregunta;
		}
		tipoPregunta = _tipoPregunta;
		publico = true;
		this.ingresaABd();
	}

	public boolean getPublico() {
		return publico;
	}

	public void setPublico(boolean _publico) {
		publico = _publico;
		this.ingresaABd();
	}

	//obtengo el ID de la pregunta
	public int getId() {
		return idPregunta;
	}

	//indico el ID de la pregunta
	private void setId(int _idPregunta) {
		idPregunta = _idPregunta;
	}

	//obtengo el tipo de pregunta
	public int getTipoPregunta() {
		return tipoPregunta;
	}

	//indico el tipo de pregunta
	public void setTipoPregunta(int _tipoPregunta) {
		tipoPregunta = _tipoPregunta;
		this.ingresaABd();
	}

	//indico el texto de la pregunta
	public void setPregunta(String _pregunta) {
		if(!_pregunta.equals("")){
			pregunta = _pregunta;
			this.ingresaABd();
		}
	}

	//elimina la pregunta incluyendo la BD
	public void delPregunta() {
		if(this.esEditable()){
			//antes de eliminar la pregunta debemos eliminar las respuestas asociadas.
			this.retornaRespuestasPosibles();
			try {
				Enumeration _misResp = this.misRespuestas.elements();
				while(_misResp.hasMoreElements()){
					RespuestasPosibles temp = (RespuestasPosibles)_misResp.nextElement();
					temp.delRespuesta();
				}
				PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM pool_preguntas WHERE id_pool_preguntas = ? AND creado_por = ?");
				pstmt.setInt(1, this.getId());
				pstmt.setInt(2, this.getUsuario().getUsuarioId());
				pstmt.execute();
				tipoPregunta = 30;
				pregunta = new String("");
				misRespuestas = new Vector();
				idPregunta = -1;
				publico = true;
				this.setCargadaDeBD(false);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//retorno el texto de la pregunta
	public String getPregunta() {
		return pregunta;
	}

	//retorna un vector de RespuestasPosibles para esta pregunta
	public Vector retornaRespuestasPosibles(boolean _soloPublico){
		misRespuestas = RespuestasPosibles.obtenerRespuestas(this.getUsuario(), this.getConexion(),this, _soloPublico);
		return misRespuestas;
	}

	//retorna un vector de RespuestasPosibles para esta pregunta
	public Vector retornaRespuestasPosibles(){
		misRespuestas = RespuestasPosibles.obtenerRespuestas(this.getUsuario(), this.getConexion(),this);
		return misRespuestas;
	}

	//a un objeto RespuestasPosibles le asocio la pregunta padre a la que pertenece
	public void agregarRespuesta(RespuestasPosibles _respuesta){
		_respuesta.asociarPregunta(this);
		misRespuestas.add(_respuesta);
	}

	//agrego una nueva respuesta para esta pregunta
	//solo se indica el texto de la respuesta
	public void agregarRespuesta(String _respuesta){
		RespuestasPosibles temp = new RespuestasPosibles(this.getUsuario(),this.getConexion());
		temp.setRespuesta(_respuesta, this);
		misRespuestas.add(temp);
	}

	//retorna un objeto RespuestasPosibles dado el ID de la respuesta deseada.
	public RespuestasPosibles retornarRespuesta(int _respuestaId){
		this.retornaRespuestasPosibles();
		try{
			Enumeration _misResp = this.misRespuestas.elements();
			while(_misResp.hasMoreElements()){
				RespuestasPosibles temp = (RespuestasPosibles)_misResp.nextElement();
				if(temp.getId() == _respuestaId){
					return temp;
				}
			}
		}catch(Exception e){return null;}
		return null;
	}

	//eliminar una respuesta dado el objeto donde esta definida
	public void delRespuesta(RespuestasPosibles _respuesta){
		_respuesta.delRespuesta();
		if(!misRespuestas.isEmpty()){
			this.misRespuestas.remove(_respuesta);
		}
	}

	//eliminar una respuesta dado su ID
	public void delRespuesta(int _respuestaId){
		Enumeration _misResp = this.misRespuestas.elements();
		while(_misResp.hasMoreElements()){
			RespuestasPosibles temp = (RespuestasPosibles)_misResp.nextElement();
			if(temp.getId() == _respuestaId){
				temp.delRespuesta();
				this.misRespuestas.remove(temp);
				break;
			}
		}
	}

	//indica si esta Pregunta puede ser modificada
	public boolean esEditable(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM pool_preguntas WHERE id_pool_preguntas = ? AND id_pool_preguntas NOT IN(SELECT id_pool_preguntas FROM instancia_preguntas)");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()){
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	//recarga el objeto desde la BD tomando en cuenta el ID de la pregunta
	public void recargarPreguntaDeBD() {
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM pool_preguntas WHERE id_pool_preguntas = ?");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.pregunta = rs.getString("nombre_amistoso");
				this.tipoPregunta = rs.getInt("tipo_pregunta");
				this.setId(rs.getInt("id_pool_preguntas"));
				this.publico = rs.getBoolean("visible");
				this.setCargadaDeBD(true);
			}else{
				this.pregunta = "";
				this.tipoPregunta = 30;
				this.publico = true;
				this.setId(-1);
				this.setCargadaDeBD(false);
			}
		}
		catch (Exception e) {
		}
	}

	//dado los datos que se tienen en el objeto se actualiza o se inserta en la BD
	private void ingresaABd(){
		//si no se ha provisto de un nombre amistoso para la pregunta no es posible insertarla en la BD
		if(!(this.getPregunta()).equals("")){
			try {
				if (getCargadaDeBD()) {
					//ejecuto UPDATE
					if(this.esEditable()){
						PreparedStatement pstmt = getConexion().prepareStatement("UPDATE pool_preguntas SET nombre_amistoso = ? , tipo_pregunta = ? , visible = ? WHERE id_pool_preguntas = ? AND creado_por = ?");
						pstmt.setString(1, this.getPregunta());
						pstmt.setInt(2, this.getTipoPregunta());
						pstmt.setBoolean(3, this.getPublico());
						pstmt.setInt(4, this.getId());
						pstmt.setInt(5, this.getUsuario().getUsuarioId());
						pstmt.execute();
					}
				}else {
					//ejecuto INSERT
					PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_pool_preguntas') AS numero");
					ResultSet _rs = _pstmt.executeQuery();
					_rs.next();
					int siguiente = _rs.getInt("numero");

					PreparedStatement pstmt = getConexion().prepareStatement("INSERT INTO pool_preguntas(id_pool_preguntas, nombre_amistoso, tipo_pregunta, creado_por, visible) VALUES (?, ?, ?, ?, ?)");
					pstmt.setInt(1, siguiente);
					pstmt.setString(2, this.getPregunta());
					pstmt.setInt(3, this.getTipoPregunta());
					pstmt.setInt(4, this.getUsuario().getUsuarioId());
					pstmt.setBoolean(5, this.getPublico());
					pstmt.execute();
					this.setCargadaDeBD(true);
					this.setId(siguiente);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//retorna un Vector con todas las preguntas disponibles dado un usuario
	//_cargaSelectiva = 0   --> todas
	//_cargaSelectiva = 1   --> libres (no instanciadas)
	//_visible solo muestra las preguntas que esten marcadas como visibles por el creador, caso contrario las trae todas
	//_soloPropias solo mostrara las preguntas que sean del usuario (para editarlas) o todas las comunes (para utilzarlas o instanciarlas)
	public static Vector todasPreguntas(Usuario _usuario, Connection _miConexion, int _cargaSelectiva, boolean _visible, boolean _soloPropias){
		Vector _lista = new Vector();
		ResultSet rs = null;
		Pregunta ObjPregTemp = null;

		rs = obtenerDataPoolPreguntas(_usuario, _miConexion, _cargaSelectiva, _visible, _soloPropias);
		try {
			while (rs.next()){
				ObjPregTemp = new Pregunta(_usuario, _miConexion);
				ObjPregTemp.idPregunta = rs.getInt("id_pool_preguntas");
				ObjPregTemp.tipoPregunta = rs.getInt("tipo_pregunta");
				ObjPregTemp.pregunta = rs.getString("nombre_amistoso");
				ObjPregTemp.publico = rs.getBoolean("visible");
				ObjPregTemp.cargadaDeBD = true;
				_lista.add(ObjPregTemp);
			}
		}
		catch (Exception e) {
			return new Vector();
		}
		return _lista;
	}

	//retorna un Vector con todas las preguntas disponibles dado un usuario editables o no editables
	public static Vector todasPreguntas(Usuario _usuario, Connection _miConexion, boolean _visible, boolean _soloPropias){
		return todasPreguntas(_usuario, _miConexion, 0, _visible, _soloPropias);
	}

	//retorna un Resulset de todas las preguntas disponibles para un usuario
	//metodo usado mayormente por todasPreguntas(int, Connection)
	//_cargaSelectiva = 0   --> todas
	//_cargaSelectiva = 1   --> libres (no instanciadas)
	//_visible solo muestra las preguntas que esten marcadas como visibles por el creador, caso contrario las trae todas
	//_soloPropias solo mostrara las preguntas que sean del usuario (para editarlas) o todas las comunes (para utilzarlas o instanciarlas)
	private static ResultSet obtenerDataPoolPreguntas(Usuario _usuario, Connection _miConexion, int _cargaSelectiva, boolean _visible, boolean _soloPropias){
		ResultSet rs = null;
		String addSQL = "";
		if(_visible){
			 addSQL = addSQL+" AND visible = true";
		}
		try {
			PreparedStatement pstmt = null;
			if(_cargaSelectiva == 0){
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_preguntas WHERE creado_por = ? "+addSQL);
				if(!_soloPropias){
					pstmt = _miConexion.prepareStatement("SELECT * FROM pool_preguntas WHERE (creado_por = ?  OR creado_por IN (SELECT id_administradores FROM administradores WHERE tipo_administrador = 'superadministrador')) "+addSQL);
				}
				pstmt.setInt(1, _usuario.getUsuarioId());
			}else if(_cargaSelectiva == 1){
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_preguntas WHERE creado_por = ?  AND id_pool_preguntas NOT IN(SELECT id_pool_preguntas FROM instancia_preguntas) "+addSQL);
				if(!_soloPropias){
					pstmt = _miConexion.prepareStatement("SELECT * FROM pool_preguntas WHERE (creado_por = ?  OR creado_por IN (SELECT id_administradores FROM administradores WHERE tipo_administrador = 'superadministrador')) AND id_pool_preguntas NOT IN(SELECT id_pool_preguntas FROM instancia_preguntas) "+addSQL);
				}
				pstmt.setInt(1, _usuario.getUsuarioId());
			}else{
				//por defecto todas
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_preguntas WHERE creado_por = ? "+addSQL);
				if(!_soloPropias){
					pstmt = _miConexion.prepareStatement("SELECT * FROM pool_preguntas WHERE (creado_por = ?  OR creado_por IN (SELECT id_administradores FROM administradores WHERE tipo_administrador = 'superadministrador')) "+addSQL);
				}
				pstmt.setInt(1, _usuario.getUsuarioId());
			}
			rs = pstmt.executeQuery();
		}catch (Exception e) {
			return null;
		}
		return rs;
	}

	//obtiene el conjunto de preguntas distintas y únicas usadas en un objeto
	//devuelve Vector de Pregunta
	public static Vector preguntasDeObjeto(Usuario _usuario, Connection _miConexion, Objeto _miObjeto){
	  Vector _retorno = new Vector();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = _miConexion.prepareStatement("SELECT DISTINCT ON (id_pool_preguntas) pool_preguntas.* FROM pool_preguntas WHERE id_pool_preguntas IN (SELECT id_pool_preguntas FROM instancia_preguntas WHERE id_pool_objetos = ?)");
			pstmt.setInt(1, _miObjeto.getId());
			rs = pstmt.executeQuery();
			Pregunta ObjPregTemp = null;
			while (rs.next()){
				ObjPregTemp = new Pregunta(_usuario, _miConexion);
				ObjPregTemp.idPregunta = rs.getInt("id_pool_preguntas");
				ObjPregTemp.tipoPregunta = rs.getInt("tipo_pregunta");
				ObjPregTemp.pregunta = rs.getString("nombre_amistoso");
				ObjPregTemp.publico = rs.getBoolean("visible");
				ObjPregTemp.cargadaDeBD = true;
				_retorno.add(ObjPregTemp);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new Vector();
		}
		return _retorno;
	}
}
