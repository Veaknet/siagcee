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

public class Respuesta extends ObjetoBase{

	int id;
	String textoRespuesta;  //caso que sea respuesta abierta
	long intRespuesta;  //caso que sea respuesta abierta
	Double DoubleRespuesta;  //caso que sea respuesta abierta
	java.util.Date dateRespuesta;  //caso que sea respuesta abierta
	InstanciaObjeto insObjeto;
	InstanciaPregunta insPreg;
	RespuestasPosibles respuestaDada;  //caso que sea respuesta cerrada
	int elaborado_por;

	public Respuesta(){
		super();
		this.id = -1;
		this.textoRespuesta = "";
		intRespuesta = 0;
		DoubleRespuesta = 0D;
		dateRespuesta = new java.util.Date();
		this.insObjeto = null;
		this.insPreg = null;
		this.respuestaDada = null;
		this.cargadaDeBD = false;
	}

	public Respuesta(Usuario _usuario, Connection _miConexion){
		super(_usuario, _miConexion);
		this.id = -1;
		this.textoRespuesta = "";
		intRespuesta = 0;
		DoubleRespuesta = 0D;
		dateRespuesta = new java.util.Date();
		this.insObjeto = null;
		this.insPreg = null;
		this.respuestaDada = null;
		this.cargadaDeBD = false;
	}

	public Respuesta(Usuario _usuario, Connection _miConexion, int _id){
		super(_usuario, _miConexion);
		this.id = _id;
		this.textoRespuesta = "";
		intRespuesta = 0;
		DoubleRespuesta = 0D;
		dateRespuesta = new java.util.Date();
		this.insObjeto = null;
		this.insPreg = null;
		this.respuestaDada = null;
		this.cargadaDeBD = false;
		this.recargaDeBd();
	}

	public Respuesta(Respuesta _resp){
		super(_resp.getUsuario(), _resp.getConexion());
		this.id = -1;
		if(_resp.getInstanciaPregunta().getTipoPregunta() == 30 || _resp.getInstanciaPregunta().getTipoPregunta() == 100){
			this.textoRespuesta = (String)_resp.getRespuestaAbierta();
		}else{
			this.textoRespuesta = "";
			if(_resp.getInstanciaPregunta().getTipoPregunta() == 31){
				this.intRespuesta = (Long)_resp.getRespuestaAbierta();
			}else{
				this.intRespuesta = 0;
				if(_resp.getInstanciaPregunta().getTipoPregunta() == 32){
					this.DoubleRespuesta = (Double)_resp.getRespuestaAbierta();
				}else{
					this.DoubleRespuesta = 0D;
					this.intRespuesta = 0;
					if(_resp.getInstanciaPregunta().getTipoPregunta() == 33){
						this.dateRespuesta = (java.util.Date)_resp.getRespuestaAbierta();
					}else{
						this.dateRespuesta = new java.util.Date();
					}
				}
			}
		}
		this.intRespuesta = 0;
		this.DoubleRespuesta = 0D;
		this.dateRespuesta = new java.util.Date();
		this.insObjeto = _resp.getInstanciaObjeto();
		this.insPreg = _resp.getInstanciaPregunta();
		this.respuestaDada = _resp.getRespuestaCerrada();
		this.cargadaDeBD = false;
		this.ingresaABd();
	}

	//establece la respuesta en caso que sea cerrada
	public void setRespuesta(RespuestasPosibles _miRespuesta){
		this.respuestaDada = _miRespuesta;
		this.ingresaABd();
	}

	//establece la respuesta en caso que sea cerrada
	public void setRespuestaCerrada(int _miRespuesta){
		this.respuestaDada = new RespuestasPosibles(this.getUsuario(), this.getConexion(), _miRespuesta);
		this.ingresaABd();
	}

	//establece la respuesta en caso que sea abierta
	public void setRespuesta(String _miRespuesta){
		this.textoRespuesta = _miRespuesta;
		this.ingresaABd();
	}

	//establece la respuesta en caso que sea abierta
	public void setRespuesta(Long _miRespuesta){
		this.intRespuesta = _miRespuesta.longValue();
		this.ingresaABd();
	}

	//establece la respuesta en caso que sea abierta
	public void setRespuesta(long _miRespuesta){
		this.intRespuesta = _miRespuesta;
		this.ingresaABd();
	}

	//establece la respuesta en caso que sea abierta
	public void setRespuesta(Double _miRespuesta){
		this.DoubleRespuesta = _miRespuesta;
		this.ingresaABd();
	}

	//establece la respuesta en caso que sea abierta
	public void setRespuesta(java.util.Date _miRespuesta){
		this.dateRespuesta = _miRespuesta;
		this.ingresaABd();
	}

	//obtiene el objeto respuesta
	public RespuestasPosibles getRespuestaCerrada(){
		return this.respuestaDada;
	}

	//obtiene el objeto respuesta
	public String getRespuestaAbiertaTexto(){
		if(this.getInstanciaPregunta() == null){
			return null;
		}
		if(this.getInstanciaPregunta().getTipoPregunta() == 30 || this.getInstanciaPregunta().getTipoPregunta() == 100){
			return this.textoRespuesta;
		}
		return "";
	}

	//obtiene el objeto respuesta
	public long getRespuestaAbiertaInt(){
		if(this.getInstanciaPregunta() == null){
			return 0;
		}
		if(this.getInstanciaPregunta().getTipoPregunta() == 31){
			return this.intRespuesta;
		}
		return 0;
	}

	//obtiene el objeto respuesta
	public Double getRespuestaAbiertaDouble(){
		if(this.getInstanciaPregunta() == null){
			return 0D;
		}
		if(this.getInstanciaPregunta().getTipoPregunta() == 32){
			return this.DoubleRespuesta;
		}
		return 0D;
	}

	//obtiene el objeto respuesta
	public java.util.Date getRespuestaAbiertaDate(){
		if(this.getInstanciaPregunta() == null){
			return null;
		}
		if(this.getInstanciaPregunta().getTipoPregunta() == 33){
			return this.dateRespuesta;
		}
		return null;
	}

	//obtiene el objeto respuesta
	public Object getRespuestaAbierta(){
		if(this.getInstanciaPregunta() == null){
			return null;
		}
		if(this.getInstanciaPregunta().getTipoPregunta() == 30 || this.getInstanciaPregunta().getTipoPregunta() == 100){
			return (Object)this.textoRespuesta;
		}else{
			if(this.getInstanciaPregunta().getTipoPregunta() == 31){
				return (Object)this.intRespuesta;
			}else{
				if(this.getInstanciaPregunta().getTipoPregunta() == 32){
					return (Object)this.DoubleRespuesta;
				}else{
					if(this.getInstanciaPregunta().getTipoPregunta() == 33){
						return (Object)this.dateRespuesta;
					}
				}
			}
		}
		return null;
	}

	//establece la instanciaPregunta
	public void asociarInstanciaPregunta(InstanciaPregunta _instPre){
		this.insPreg = _instPre;
		this.ingresaABd();
	}

	//establece la instanciaPregunta
	public void asociarInstanciaPregunta(int _instPre){
		this.insPreg = new InstanciaPregunta(this.getUsuario(), this.getConexion(), _instPre);
		this.ingresaABd();
	}

	//obtiene la instanciaPregunta
	public InstanciaPregunta getInstanciaPregunta(){
		return this.insPreg;
	}

	//establece la instanciaObjeto
	public void asociarInstanciaObjeto(InstanciaObjeto _instObj){
		this.insObjeto = _instObj;
		this.ingresaABd();
	}

	//establece la instanciaObjeto
	public void asociarInstanciaObjeto(int _instObj){
		this.insObjeto = new InstanciaObjeto(this.getUsuario(), this.getConexion(), _instObj);
		this.ingresaABd();
	}

	//obtiene la instanciaObjeto
	public InstanciaObjeto getInstanciaObjeto(){
		return this.insObjeto;
	}

	//obtiene el ID
	public int getId(){
		return this.id;
	}

	//establece el ID
	protected void setId(int _id){
		this.id = _id;
	}

	//retorna el tipo de pregunta asociada a esta respuesta
	//si retorna -1 es porque no se asocio ninguna pregunta
	public int getTipoPregunta(){
		if(this.getInstanciaPregunta() != null){
			return this.getInstanciaPregunta().getTipoPregunta();
		}
		return -1;
	}

	public int getElaborado_por() {
		return elaborado_por;
	}

	public void setElaborado_por(int elaborado_por) {
		this.elaborado_por = elaborado_por;
	}

	public void recargaDeBd(){
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = this.getConexion().prepareStatement("SELECT * FROM respuestas WHERE id_respuestas = ?");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.id = rs.getInt("id_respuestas");
				this.elaborado_por = rs.getInt("elaborado_por");
				this.insObjeto = new InstanciaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_objetos"));
				this.insPreg = new InstanciaPregunta(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_preguntas"));
				if(this.getTipoPregunta() >= 30){
					//abierta
					this.textoRespuesta = rs.getString("respuesta_string");
					this.DoubleRespuesta = rs.getDouble("respuesta_float");
					this.intRespuesta = rs.getLong("respuesta_int");
					this.dateRespuesta = rs.getDate("respuesta_date");
				}else{
					//cerrada
					this.respuestaDada = new RespuestasPosibles(this.getUsuario(), this.getConexion(), Integer.parseInt(rs.getString("id_respuestas_posibles")));
				}
				this.cargadaDeBD = true;
			}else{
				this.id = -1;
				this.textoRespuesta = "";
				this.DoubleRespuesta = 0D;
				this.intRespuesta = 0;
				this.dateRespuesta = new java.util.Date();
				this.insObjeto = null;
				this.insPreg = null;
				this.respuestaDada = null;
				this.cargadaDeBD = false;
			}
		}
		catch (Exception e) {e.printStackTrace();}
	}

	public void ingresaABd(){
		//debe indicarse la respuesta, y las instancias de pregunta y objeto
		if(((this.getRespuestaAbierta() != null) || (this.getRespuestaCerrada() != null)) && ((this.getInstanciaObjeto() != null) && (this.getInstanciaPregunta() != null))){
			if(this.getUsuario().getUsuarioId() != -1){
				try {
					if (getCargadaDeBD()) {
						//ejecuto UPDATE
						PreparedStatement pstmt = getConexion().prepareStatement("UPDATE respuestas SET id_instancia_objetos = ? , id_instancia_preguntas = ? , respuesta_string = ? , respuesta_int = ? , respuesta_float = ? , respuesta_date = ? , id_respuestas_posibles = ? WHERE id_respuestas = ?");
						pstmt.setInt(1, this.getInstanciaObjeto().getId());
						pstmt.setInt(2, this.getInstanciaPregunta().getId());
						//abierta
						pstmt.setString(3, this.textoRespuesta);
						pstmt.setLong(4, this.intRespuesta);
						pstmt.setDouble(5, this.DoubleRespuesta);
						pstmt.setDate(6, new java.sql.Date((this.dateRespuesta).getTime()));
						//cerrada
						if(this.getTipoPregunta() < 30){
							pstmt.setInt(7, this.getRespuestaCerrada().getId());
						}else{
							pstmt.setInt(7, -1);
						}
						pstmt.setInt(8, this.getId());
						pstmt.execute();
					}else{
						//Justo antes de insertar nueva respuesta debo eliminar su antecesora si existiera... no pueden haber multiples respuestas para
						//un mismo objeto, misma pregunta y mismo usuario (único caso son las respuestas de preguntas de seleccion multiple). De igual manera se borra
						//para asegurar que las respuestas que estamos ingresando son las correctas. Pero se deja la responsabilidad al que invoque a Respuesta
						if((this.getInstanciaPregunta().getTipoPregunta() == 1) || (this.getInstanciaPregunta().getTipoPregunta() >= 30)){
							PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ? AND elaborado_por = ?");
							pstmt.setInt(1, this.getInstanciaObjeto().getId());
							pstmt.setInt(2, this.getInstanciaPregunta().getId());
							pstmt.setInt(3, this.getUsuario().getUsuarioId());
							pstmt.execute();
						}
						//ejecuto INSERT
						PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_respuestas') AS numero");
						ResultSet _rs = _pstmt.executeQuery();
						_rs.next();
						int siguiente = _rs.getInt("numero");

						PreparedStatement pstmt = getConexion().prepareStatement("INSERT INTO respuestas(id_respuestas, id_instancia_preguntas, id_instancia_objetos, elaborado_por, respuesta_string, respuesta_int, respuesta_float, respuesta_date, id_respuestas_posibles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
						pstmt.setInt(1, siguiente);
						pstmt.setInt(2, this.getInstanciaPregunta().getId());
						pstmt.setInt(3, this.getInstanciaObjeto().getId());
						pstmt.setInt(4, this.getUsuario().getUsuarioId());
						//abierta
						pstmt.setString(5, this.textoRespuesta);
						pstmt.setLong(6, this.intRespuesta);
						pstmt.setDouble(7, this.DoubleRespuesta);
						pstmt.setDate(8, new java.sql.Date((this.dateRespuesta).getTime()));
						//cerrada
						if(this.getTipoPregunta() < 30){
							pstmt.setInt(9, this.getRespuestaCerrada().getId());
						}else{
							pstmt.setInt(9, -1);
						}
						pstmt.execute();
						this.setCargadaDeBD(true);
						this.setId(siguiente);
					}
				}
				catch (Exception e) {e.printStackTrace();}
			}
		}
	}

	//limpia la BD de todas las respuestas del usuario, instancia_objeto e instancia_pregunta
	//es usado solo para preguntas de seleccion multiple
	//en otros casos no se permite su ejecución
	public static void delRespuestasDePreguntaMultiple(Usuario _user, Connection _micon, InstanciaPregunta _insPreg, InstanciaObjeto _insObj) {
		if(_insPreg != null && _insObj != null && _user != null){
			if(_insPreg.getTipoPregunta() == 2){
				//solo seleccion multiple
				//en otros casos deben usarse delRespuesta normalmente.
				try {
					PreparedStatement pstmt = _micon.prepareStatement("DELETE FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ? AND elaborado_por = ?");
					pstmt.setInt(1, _insObj.getId());
					pstmt.setInt(2, _insPreg.getId());
					pstmt.setInt(3, _user.getUsuarioId());
					pstmt.execute();
				}
				catch (Exception e){e.printStackTrace();}
			}
		}
	}

    public static void delRespuestasDeUsuario(Usuario _user, Connection _micon, InstanciaObjeto _insObj) {
        try {
            PreparedStatement pstmt = _micon.prepareStatement("DELETE FROM respuestas WHERE id_instancia_objetos = ? AND elaborado_por = ?");
            pstmt.setInt(1, _insObj.getId());
            pstmt.setInt(2, _user.getUsuarioId());
            pstmt.execute();
        }
        catch (Exception e){e.printStackTrace();}
    }

	//eliminar completamente este acceso de la BD
	public static void delRespuesta(Connection _micon, InstanciaObjeto _obj){
		try{
			PreparedStatement pstmt = _micon.prepareStatement("DELETE FROM respuestas WHERE id_instancia_objetos = ?");
			pstmt.setInt(1, _obj.getId());
			pstmt.execute();
		}catch(Exception e){e.printStackTrace();}
	}

	//eliminar completamente este acceso de la BD
	public static void delRespuesta(Connection _micon, InstanciaObjeto _obj, InstanciaPregunta _insPregunta){
		try{
			PreparedStatement pstmt = _micon.prepareStatement("DELETE FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ?");
			pstmt.setInt(1, _obj.getId());
			pstmt.setInt(2, _insPregunta.getId());
			pstmt.execute();
		}catch(Exception e){e.printStackTrace();}
	}

	//limpia la BD de todas las respuestas del usuario, instancia_objeto e instancia_pregunta asociado a esta respuesta
	//es usado solo para preguntas de seleccion multiple
	//en otros casos no se permite su ejecución
	public void delRespuestasDePreguntaMultiple() {
		if(this.getInstanciaPregunta() != null && this.getInstanciaObjeto() != null && this.getUsuario() != null){
			if(this.getInstanciaPregunta().getTipoPregunta() == 2){
				//solo seleccion multiple
				//en otros casos deben usarse delRespuesta normalmente.
				try {
					PreparedStatement pstmt = this.getConexion().prepareStatement("DELETE FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ? AND elaborado_por = ?");
					pstmt.setInt(1, this.getInstanciaObjeto().getId());
					pstmt.setInt(2, this.getInstanciaPregunta().getId());
					pstmt.setInt(3, this.getUsuario().getUsuarioId());
					pstmt.execute();
				}
				catch (Exception e){e.printStackTrace();}
			}
		}
	}

	//elimina la respuesta incluyendo la BD
	public void delRespuesta() {
		try {
			PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM respuestas WHERE id_respuestas = ?");
			pstmt.setInt(1, getId());
			pstmt.execute();
			this.id = -1;
			this.textoRespuesta = "";
			intRespuesta = 0;
			DoubleRespuesta = 0D;
			dateRespuesta = new java.util.Date();
			this.insObjeto = null;
			this.insPreg = null;
			this.respuestaDada = null;
			this.setCargadaDeBD(false);
		}
		catch (Exception e){e.printStackTrace();}
	}

	//retorna todas las respuestas
	//_usuario ,  _pregunta y _objeto pueden ser null
	//en cuyo caso solo se buscaran donde los objetos sean != a null
	public static Vector todasRespuestas(Usuario _usuario, Connection _miConexion, InstanciaPregunta _pregunta, InstanciaObjeto _objeto){
		Vector _lista = new Vector();
		ResultSet rs = null;
		Respuesta _resp = null;

		rs = obtenerDataRespuestas(_usuario, _miConexion, _pregunta, _objeto);
		try {
			while (rs.next()){
				_resp = new Respuesta(_usuario, _miConexion);
				_resp.insObjeto = new InstanciaObjeto(_usuario, _miConexion, rs.getInt("id_instancia_objetos"));
				_resp.insPreg = new InstanciaPregunta(_usuario, _miConexion, rs.getInt("id_instancia_preguntas"));
				if(_resp.getTipoPregunta() >= 30){
					//abierta
					_resp.textoRespuesta = rs.getString("respuesta_string");
					_resp.DoubleRespuesta = rs.getDouble("respuesta_float");
					_resp.intRespuesta = rs.getLong("respuesta_int");
					_resp.dateRespuesta = rs.getDate("respuesta_date");
				}else{
					//cerrada
					_resp.respuestaDada = new RespuestasPosibles(_usuario,_miConexion,rs.getInt("id_respuestas_posibles"));
				}
				_resp.cargadaDeBD = true;
				_resp.id = rs.getInt("id_respuestas");
				_lista.add(_resp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Vector();
		}
		return _lista;
	}

	//retorna un Resulset de todas las instancias de objetos disponibles para un usuario
	private static ResultSet obtenerDataRespuestas(Usuario _usuario, Connection _miConexion, InstanciaPregunta _pregunta, InstanciaObjeto _objeto){
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			if(_pregunta != null && _objeto != null && _usuario != null){
				//ninguno es null
				pstmt = _miConexion.prepareStatement("SELECT * FROM respuestas WHERE elaborado_por = ? AND id_instancia_objetos = ? AND id_instancia_preguntas = ?");
				pstmt.setInt(1, _usuario.getUsuarioId());
				pstmt.setInt(2, _objeto.getId());
				pstmt.setInt(3, _pregunta.getId());
			}else{
				//solo 1 es null
				if(_pregunta != null && _usuario != null){
					pstmt = _miConexion.prepareStatement("SELECT * FROM respuestas WHERE elaborado_por = ? AND id_instancia_preguntas = ? ");
					pstmt.setInt(1, _usuario.getUsuarioId());
					pstmt.setInt(2, _pregunta.getId());
				}else if(_objeto != null && _usuario != null){
					pstmt = _miConexion.prepareStatement("SELECT * FROM respuestas WHERE elaborado_por = ? AND id_instancia_objetos = ? ");
					pstmt.setInt(1, _usuario.getUsuarioId());
					pstmt.setInt(2, _objeto.getId());
				}else if(_pregunta != null && _objeto != null){
					pstmt = _miConexion.prepareStatement("SELECT * FROM respuestas WHERE id_instancia_preguntas = ? AND id_instancia_objetos = ? ");
					pstmt.setInt(1, _pregunta.getId());
					pstmt.setInt(2, _objeto.getId());
				}else{
					//2 son null
					if(_pregunta != null){
						pstmt = _miConexion.prepareStatement("SELECT * FROM respuestas WHERE id_instancia_preguntas = ? ");
						pstmt.setInt(1, _pregunta.getId());
					}else if(_objeto != null){
						pstmt = _miConexion.prepareStatement("SELECT * FROM respuestas WHERE id_instancia_objetos = ? ");
						pstmt.setInt(1, _objeto.getId());
					}else if(_usuario != null){
						pstmt = _miConexion.prepareStatement("SELECT * FROM respuestas WHERE elaborado_por = ?");
						pstmt.setInt(1, _usuario.getUsuarioId());
					}else{
						//null
						return null;
					}
				}
			}
			rs = pstmt.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
}
