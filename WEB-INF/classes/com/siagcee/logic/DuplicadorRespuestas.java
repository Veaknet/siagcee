package com.siagcee.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.Enumeration;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 18/12/2009
 * Hora: 10:05:08 AM
 */
public class DuplicadorRespuestas extends ObjetoBase{

	public static int CORRECTO = 0;
	public static int ERROR_NO_DEFINIDO_OBJETO_ORIGEN = 1;
	public static int ERROR_NO_DEFINIDO_OBJETO_DESTINO = 2;
	public static int ERROR_NO_DEFINIDA_PREGUNTA_ORIGEN = 3;
	public static int ERROR_NO_DEFINIDA_PREGUNTA_DESTINO = 4;
	public static int ERROR_EXISTE_UN_TEMPORAL_CON_ESE_OBJETO_DESTINO = 5;
	public static int ERROR_NO_CONCUERDAN_CAPACIDADES_DE_VECTORES_PREGUNTAS = 6;
	public static int ERROR_NO_ESPECIFICADO = 99;


	public static int LIMPIAR_TEMPORALES_VIEJOS = 100;  //si existieran se borrarian
	public static int NO_LIMPIAR_TEMPORALES_VIEJOS = 101;  //si existieran NO se borrarian

	int error_actual;
	InstanciaObjeto _objOrigen;
	InstanciaObjeto _objDestino;
	Vector _pregDestino;
	Vector _pregOrigen;
	InstanciaPregunta _preguntaJoinInstrumento;
	InstanciaPregunta _preguntaJoinRelacion;

	public DuplicadorRespuestas(){
		super();
		error_actual = DuplicadorRespuestas.CORRECTO;
		_objDestino = null;
		_objOrigen = null;
		_pregDestino = new Vector();
		_pregOrigen = new Vector();
		_preguntaJoinInstrumento = null;
		_preguntaJoinRelacion = null;
	}

	public DuplicadorRespuestas(Usuario _usuario, Connection _micon){
		super(_usuario, _micon);
		error_actual = DuplicadorRespuestas.CORRECTO;
		_objDestino = null;
		_objOrigen = null;
		_pregDestino = new Vector();
		_pregOrigen = new Vector();
		_preguntaJoinInstrumento = null;
		_preguntaJoinRelacion = null;
	}

	public DuplicadorRespuestas(Usuario _usuario, Connection _micon, InstanciaObjeto _origen, InstanciaObjeto _destino){
		super(_usuario, _micon);
		error_actual = DuplicadorRespuestas.CORRECTO;
		_objDestino = _destino;
		_objOrigen = _origen;
		_pregDestino = new Vector();
		_pregOrigen = new Vector();
		_preguntaJoinInstrumento = null;
		_preguntaJoinRelacion = null;
	}

	public InstanciaObjeto getObjetoOrigen() {
		return _objOrigen;
	}

	public void setObjetoOrigen(InstanciaObjeto _objOrigen) {
		this._objOrigen = _objOrigen;
	}

	public InstanciaObjeto getObjetoDestino() {
		return _objDestino;
	}

	public void setObjetoDestino(InstanciaObjeto _objDestino) {
		this._objDestino = _objDestino;
	}

	public Vector getPreguntasOrigen() {
		return _pregOrigen;
	}

	public void setPreguntasOrigen(Vector _pregOrigen) {
		this._pregOrigen = _pregOrigen;
	}

	public Vector getPreguntasDestino() {
		return _pregDestino;
	}

	public void setPreguntasDestino(Vector _pregDestino) {
		this._pregDestino = _pregDestino;
	}

	public InstanciaPregunta get_preguntaJoinInstrumento() {
		return _preguntaJoinInstrumento;
	}

	public void set_preguntaJoinInstrumento(InstanciaPregunta _preguntaJoinInstrumento) {
		this._preguntaJoinInstrumento = _preguntaJoinInstrumento;
	}

	public InstanciaPregunta get_preguntaJoinRelacion() {
		return _preguntaJoinRelacion;
	}

	public void set_preguntaJoinRelacion(InstanciaPregunta _preguntaJoinRelacion) {
		this._preguntaJoinRelacion = _preguntaJoinRelacion;
	}

	//Devuelve un error si existiera o CORRECTO si bien
	//_sqlCondicional las condiciones que han de cumplirse en alguna tabla para extraer los datos.
	//obligatoriamente esta subconsulta debe retornar un solo campo 'elaborado_por' (encuestados.id_usuarios)
	public int crearTempRespuestas(InstanciaObjeto _origen, InstanciaObjeto _destino, int _limpiarTemporalesViejos, String _sqlCondicional){
		this.setObjetoDestino(_destino);
		this.setObjetoOrigen(_origen);
		return this.crearTempRespuestas(_limpiarTemporalesViejos, _sqlCondicional);
	}

	//Devuelve un error si existiera o CORRECTO si bien
	//_sqlCondicional las condiciones que han de cumplirse en alguna tabla para extraer los datos.
	//obligatoriamente esta subconsulta debe retornar un solo campo 'elaborado_por' (encuestados.id_usuarios)
	public int crearTempRespuestas(int _limpiarTemporalesViejos, String _sqlCondicional){
		PreparedStatement pstmt;
		ResultSet rs;

		if(this.getObjetoDestino() == null){
			return DuplicadorRespuestas.ERROR_NO_DEFINIDO_OBJETO_DESTINO;
		}
		if(this.getObjetoOrigen() == null){
			return DuplicadorRespuestas.ERROR_NO_DEFINIDO_OBJETO_ORIGEN;
		}
		try{
			if(_limpiarTemporalesViejos == DuplicadorRespuestas.LIMPIAR_TEMPORALES_VIEJOS){
				pstmt = this.getConexion().prepareStatement("DELETE FROM respuestas_temp WHERE id_instancia_objetos = ?");
				pstmt.setInt(1, this.getObjetoDestino().getId());
				pstmt.execute();
			}else{
				//se comprueba si ya existe un objeto destino.
				//pudiera ser que existiera si no se cerró la instancia anterior con cierraTempRespuestas()
				pstmt = this.getConexion().prepareStatement("SELECT * FROM respuestas_temp WHERE id_instancia_objetos = ?");
				pstmt.setInt(1, this.getObjetoDestino().getId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					return DuplicadorRespuestas.ERROR_EXISTE_UN_TEMPORAL_CON_ESE_OBJETO_DESTINO;
				}
			}

			Boolean _encontrada = false;
			if(this.get_preguntaJoinInstrumento() != null && this.get_preguntaJoinRelacion() != null){
				//paso las respuestas tambien de la pregunta relacion, si es que ya no se estan pasando
				if(!this.getPreguntasOrigen().isEmpty()){
					//compruebo si ya no la estoy pasando
					Enumeration _enu = this.getPreguntasOrigen().elements();
					while(_enu.hasMoreElements()){
						InstanciaPregunta _insPregunta = (InstanciaPregunta)_enu.nextElement();
						if(_insPregunta.getId() == this.get_preguntaJoinInstrumento().getId()){
							_encontrada = true;
							break;
						}
					}
					if(!_encontrada){
						this.getPreguntasOrigen().add(this.get_preguntaJoinInstrumento());
						this.getPreguntasDestino().add(this.get_preguntaJoinRelacion());
					}
				}
			}

			//paso la data a temp
			if(_sqlCondicional.equals("")){
				//toda la data
				if(!this.getPreguntasOrigen().isEmpty()){
					//hay preguntas que se necesitan filtrar
					pstmt = this.getConexion().prepareStatement("INSERT INTO respuestas_temp(id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date) SELECT ? as id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas IN "+this.ComaSeparatedId(this.getPreguntasOrigen()));
					pstmt.setInt(1, this.getObjetoDestino().getId());
					pstmt.setInt(2, this.getObjetoOrigen().getId());
				}else{
					//todas las preguntas
					pstmt = this.getConexion().prepareStatement("INSERT INTO respuestas_temp(id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date) SELECT ? as id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date FROM respuestas WHERE id_instancia_objetos = ?");
					pstmt.setInt(1, this.getObjetoDestino().getId());
					pstmt.setInt(2, this.getObjetoOrigen().getId());
				}
			}else{
				//solo los que encajen con la subconsulta
				if(!this.getPreguntasOrigen().isEmpty()){
					//hay preguntas que se necesitan filtrar
					pstmt = this.getConexion().prepareStatement("INSERT INTO respuestas_temp(id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date) SELECT ? as id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date FROM respuestas WHERE id_instancia_objetos = ? AND elaborado_por IN ("+_sqlCondicional+") AND id_instancia_preguntas IN "+this.ComaSeparatedId(this.getPreguntasOrigen()));
					pstmt.setInt(1, this.getObjetoDestino().getId());
					pstmt.setInt(2, this.getObjetoOrigen().getId());
				}else{
					//todas las preguntas
					pstmt = this.getConexion().prepareStatement("INSERT INTO respuestas_temp(id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date) SELECT ? as id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date FROM respuestas WHERE id_instancia_objetos = ? AND elaborado_por IN (\"+_sqlCondicional+\")");
					pstmt.setInt(1, this.getObjetoDestino().getId());
					pstmt.setInt(2, this.getObjetoOrigen().getId());
				}
			}
			pstmt.execute();
		}catch(Exception e){
			e.printStackTrace();
			return DuplicadorRespuestas.ERROR_NO_ESPECIFICADO;
		}
		return DuplicadorRespuestas.CORRECTO;
	}

	public int cambiarPreguntaID(){
		if(this.getPreguntasDestino().isEmpty()){
			return DuplicadorRespuestas.ERROR_NO_DEFINIDA_PREGUNTA_DESTINO;
		}
		if(this.getPreguntasOrigen().isEmpty()){
			return DuplicadorRespuestas.ERROR_NO_DEFINIDA_PREGUNTA_ORIGEN;
		}
		if(this.getPreguntasDestino().size() != this.getPreguntasOrigen().size()){
			return DuplicadorRespuestas.ERROR_NO_CONCUERDAN_CAPACIDADES_DE_VECTORES_PREGUNTAS;
		}
		try{
			PreparedStatement pstmt;
			if((this.get_preguntaJoinInstrumento() != null) && (this.get_preguntaJoinRelacion() != null)){
				//debo cambiar id de usuario para las preguntas haciendo un matching con id de usuarios existentes
				pstmt = this.getConexion().prepareStatement("SELECT * FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ? AND elaborado_por IN (SELECT elaborado_por FROM respuestas_temp WHERE id_instancia_objetos = ?)");
				pstmt.setInt(1, this.getObjetoOrigen().getId());
				pstmt.setInt(2, this.get_preguntaJoinInstrumento().getId());
				pstmt.setInt(3, this.getObjetoDestino().getId());

				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					//recorro cada usuario del instrumento para comprobar su existencia
					PreparedStatement _pstmt;
					if(this.get_preguntaJoinInstrumento().getTipoPregunta() < 30){
						_pstmt = this.getConexion().prepareStatement("SELECT respuestas.*, usuarios.* FROM respuestas JOIN usuarios ON respuestas.elaborado_por = usuarios.id_usuarios WHERE respuestas.id_instancia_objetos = ? AND respuestas.id_instancia_preguntas = ? AND usuarios.id_poblacion = ? AND respuestas.id_respuestas_posibles = ?");
					}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 33){
						_pstmt = this.getConexion().prepareStatement("SELECT respuestas.*, usuarios.* FROM respuestas JOIN usuarios ON respuestas.elaborado_por = usuarios.id_usuarios WHERE respuestas.id_instancia_objetos = ? AND respuestas.id_instancia_preguntas = ? AND usuarios.id_poblacion = ? AND respuestas.respuesta_date = ?");
					}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 31){
						_pstmt = this.getConexion().prepareStatement("SELECT respuestas.*, usuarios.* FROM respuestas JOIN usuarios ON respuestas.elaborado_por = usuarios.id_usuarios WHERE respuestas.id_instancia_objetos = ? AND respuestas.id_instancia_preguntas = ? AND usuarios.id_poblacion = ? AND respuestas.respuesta_int = ?");
					}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 32){
						_pstmt = this.getConexion().prepareStatement("SELECT respuestas.*, usuarios.* FROM respuestas JOIN usuarios ON respuestas.elaborado_por = usuarios.id_usuarios WHERE respuestas.id_instancia_objetos = ? AND respuestas.id_instancia_preguntas = ? AND usuarios.id_poblacion = ? AND respuestas.respuesta_float = ?");
					}else{
						_pstmt = this.getConexion().prepareStatement("SELECT respuestas.*, usuarios.* FROM respuestas JOIN usuarios ON respuestas.elaborado_por = usuarios.id_usuarios WHERE respuestas.id_instancia_objetos = ? AND respuestas.id_instancia_preguntas = ? AND usuarios.id_poblacion = ? AND respuestas.respuesta_string = ?");
					}
					_pstmt.setInt(1, this.getObjetoDestino().getId());
					_pstmt.setInt(2, this.get_preguntaJoinRelacion().getId());
					_pstmt.setInt(3, this.getObjetoDestino().getId());
					if(this.get_preguntaJoinInstrumento().getTipoPregunta() < 30){
						_pstmt.setInt(4, rs.getInt("id_respuestas_posibles"));
					}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 33){
						_pstmt.setDate(4, rs.getDate("respuesta_date"));
					}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 31){
						_pstmt.setLong(4, rs.getLong("respuesta_int"));
					}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 32){
						_pstmt.setDouble(4, rs.getDouble("respuesta_float"));
					}else{
						_pstmt.setString(4, rs.getString("respuesta_string"));
					}

					ResultSet _rs = _pstmt.executeQuery();
					PreparedStatement __pstmt;
					if(_rs.next()){
						//usuario existe previamente, lo usamos
						__pstmt = this.getConexion().prepareStatement("UPDATE respuestas_temp SET elaborado_por = ? WHERE id_instancia_objetos = ? AND elaborado_por = ?");
						__pstmt.setInt(1, _rs.getInt("elaborado_por"));
						__pstmt.setInt(2, this.getObjetoDestino().getId());
						__pstmt.setInt(3, rs.getInt("elaborado_por"));
						__pstmt.execute();

					}else{
						//no existe. lo creamos

						PreparedStatement ____pstmt2 = this.getConexion().prepareStatement("SELECT * FROM usuarios WHERE campo_clave = ? AND id_poblacion = ?");
						if(this.get_preguntaJoinInstrumento().getTipoPregunta() < 30){
							____pstmt2.setString(1, String.valueOf(rs.getInt("id_respuestas_posibles")));
						}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 33){
							____pstmt2.setString(1, String.valueOf(rs.getDate("respuesta_date")));
						}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 31){
							____pstmt2.setString(1, String.valueOf(rs.getLong("respuesta_int")));
						}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 32){
							____pstmt2.setString(1, String.valueOf(rs.getDouble("respuesta_float")));
						}else{
							____pstmt2.setString(1, rs.getString("respuesta_string"));
						}
						____pstmt2.setInt(2, this.getObjetoDestino().getId());
						ResultSet _miResultSet = ____pstmt2.executeQuery();

						if(_miResultSet.next()){
							PreparedStatement ____pstmt = this.getConexion().prepareStatement("UPDATE respuestas_temp SET elaborado_por = ? WHERE id_instancia_objetos = ? AND elaborado_por = ?");
							____pstmt.setInt(1, _miResultSet.getInt("id_usuarios"));
							____pstmt.setInt(2, this.getObjetoDestino().getId());
							____pstmt.setInt(3, rs.getInt("elaborado_por"));
							____pstmt.execute();
						}else{
							__pstmt = getConexion().prepareStatement("SELECT nextval('seq_usuarios') AS numero");
							_rs = __pstmt.executeQuery();
							_rs.next();
							int siguiente = _rs.getInt("numero");

							PreparedStatement ____pstmt = this.getConexion().prepareStatement("INSERT INTO usuarios(id_usuarios, campo_clave, id_poblacion) VALUES(?, ?, ?)");
							____pstmt.setInt(1, siguiente);
							if(this.get_preguntaJoinInstrumento().getTipoPregunta() < 30){
								____pstmt.setInt(2, rs.getInt("id_respuestas_posibles"));
							}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 33){
								____pstmt.setDate(2, rs.getDate("respuesta_date"));
							}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 31){
								____pstmt.setLong(2, rs.getLong("respuesta_int"));
							}else if(this.get_preguntaJoinInstrumento().getTipoPregunta() == 32){
								____pstmt.setDouble(2, rs.getDouble("respuesta_float"));
							}else{
								____pstmt.setString(2, rs.getString("respuesta_string"));
							}
							____pstmt.setInt(3, this.getObjetoDestino().getId());
							____pstmt.execute();

							____pstmt = this.getConexion().prepareStatement("UPDATE respuestas_temp SET elaborado_por = ? WHERE id_instancia_objetos = ? AND elaborado_por = ?");
							____pstmt.setInt(1, siguiente);
							____pstmt.setInt(2, this.getObjetoDestino().getId());
							____pstmt.setInt(3, rs.getInt("elaborado_por"));
							____pstmt.execute();
						}
					}
				}               				
			}else{
				//crea a todos los usuarios
				pstmt = this.getConexion().prepareStatement("SELECT * FROM usuarios WHERE id_poblacion = ? AND id_usuarios IN (SELECT elaborado_por as id_usuarios FROM respuestas_temp WHERE id_instancia_objetos = ?)");
				pstmt.setInt(1, this.getObjetoOrigen().getId());
				pstmt.setInt(2, this.getObjetoDestino().getId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					pstmt = this.getConexion().prepareStatement("SELECT * FROM usuarios WHERE campo_clave = ? AND id_poblacion = ?");
					pstmt.setString(1, rs.getString("campo_clave"));
					pstmt.setInt(2, this.getObjetoDestino().getId());
					ResultSet __rs = pstmt.executeQuery();

					if(!__rs.next()){
						PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_usuarios') AS numero");
						ResultSet _rs = _pstmt.executeQuery();
						_rs.next();
						int siguiente = _rs.getInt("numero");

						pstmt = this.getConexion().prepareStatement("INSERT INTO usuarios(id_usuarios, campo_clave, id_poblacion) VALUES(?, ?, ?)");
						pstmt.setInt(1, siguiente);
						pstmt.setString(2, rs.getString("campo_clave"));
						pstmt.setInt(3, this.getObjetoDestino().getId());
						pstmt.execute();

						pstmt = this.getConexion().prepareStatement("UPDATE respuestas_temp SET elaborado_por = ? WHERE id_instancia_objetos = ? AND elaborado_por = ?");
						pstmt.setInt(1, siguiente);
						pstmt.setInt(2, this.getObjetoDestino().getId());
						pstmt.setInt(3, rs.getInt("id_usuarios"));
						pstmt.execute();
					}else{
						pstmt = this.getConexion().prepareStatement("UPDATE respuestas_temp SET elaborado_por = ? WHERE id_instancia_objetos = ? AND elaborado_por = ?");
						pstmt.setInt(1, __rs.getInt("id_usuarios"));
						pstmt.setInt(2, this.getObjetoDestino().getId());
						pstmt.setInt(3, rs.getInt("id_usuarios"));
						pstmt.execute();
					}
				}
			}

			Enumeration _enu = this.getPreguntasOrigen().elements();
			Enumeration _enu2 = this.getPreguntasDestino().elements();
			InstanciaPregunta _insOrigen;
			InstanciaPregunta _insDestino;
			while(_enu.hasMoreElements()){
				_insOrigen = (InstanciaPregunta)_enu.nextElement();
				_insDestino = (InstanciaPregunta)_enu2.nextElement();
				pstmt = this.getConexion().prepareStatement("UPDATE respuestas_temp SET id_instancia_preguntas = ? WHERE id_instancia_preguntas = ? AND id_instancia_objetos = ? AND id_instancia_preguntas NOT IN (SELECT id_instancia_preguntas FROM respuestas WHERE id_instancia_objetos = ?)");
				pstmt.setInt(1, _insDestino.getId());
				pstmt.setInt(2, _insOrigen.getId());
				pstmt.setInt(3, this.getObjetoDestino().getId());
				pstmt.setInt(4, this.getObjetoDestino().getId());
				pstmt.execute();
			}

			try{
				pstmt = this.getConexion().prepareStatement("DELETE FROM respuestas_temp WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ? AND elaborado_por IN (SELECT DISTINCT ON(elaborado_por) elaborado_por FROM respuestas WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ?)");
				pstmt.setInt(1, this.getObjetoDestino().getId());
				pstmt.setInt(2, this.get_preguntaJoinRelacion().getId());
				pstmt.setInt(3, this.getObjetoDestino().getId());
				pstmt.setInt(4, this.get_preguntaJoinRelacion().getId());
				pstmt.execute();
			}catch (Exception e){
			}
		}catch (Exception e){
			e.printStackTrace();
			return DuplicadorRespuestas.ERROR_NO_ESPECIFICADO;
		}
		return DuplicadorRespuestas.CORRECTO;
	}

	public int deTempAProduccion(){
		try{
			PreparedStatement pstmt;
			//paso las preguntas a produccion
			pstmt = this.getConexion().prepareStatement("INSERT INTO respuestas(id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date) SELECT id_instancia_objetos, id_instancia_preguntas, respuesta_string, elaborado_por, fecha_creacion, id_respuestas_posibles, respuesta_int, respuesta_float, respuesta_date FROM respuestas_temp WHERE id_instancia_objetos = ?");
			pstmt.setInt(1, this.getObjetoDestino().getId());
			pstmt.execute();
		}catch (Exception e){
			e.printStackTrace();
			return DuplicadorRespuestas.ERROR_NO_ESPECIFICADO;
		}
		return DuplicadorRespuestas.CORRECTO;
	}


	//para obtener (id1 , id2, id3, id4 )
	public String ComaSeparatedId(Vector _listado){
		StringBuilder _retorno = new StringBuilder();
		Enumeration _lista = _listado.elements();
		InstanciaPregunta _tempPreg = null;
		boolean primero = true;
		while(_lista.hasMoreElements()){
			if(primero){
				_retorno.append("(");
				primero = false;
			}else{
				_retorno.append(",");
			}
			_tempPreg = (InstanciaPregunta)_lista.nextElement();
			_retorno.append(_tempPreg.getId());
		}
		if(!primero){
			_retorno.append(")");
		}
		return new String(_retorno);
	}

	//elimina de Objeto Destino segun sql
	//solo de destino porque se asume que destino es donde se guardará la nueva data.
	//trabajando Relacion siempre como destino
	//y fuente el instrumento.
	//subconsulta sql debe retornar un solo valor elaborado_por
	public int deleteSegunSQL(String _sql){
		PreparedStatement pstmt;
		ResultSet rs;

		if(this.getObjetoDestino() == null){
			return DuplicadorRespuestas.ERROR_NO_DEFINIDO_OBJETO_DESTINO;
		}
		try{
			pstmt = this.getConexion().prepareStatement("DELETE FROM respuestas WHERE id_instancia_objetos = ? AND elaborado_por NOT IN ("+_sql+")");
			pstmt.setInt(1, this.getObjetoDestino().getId());
			pstmt.execute();
		}catch(Exception e){
			e.printStackTrace();
			return DuplicadorRespuestas.ERROR_NO_ESPECIFICADO;
		}
		return DuplicadorRespuestas.CORRECTO;
	}


	//elimina los temporales de la tabla correspondiente.
	//sería lo mejor siempre hacer esto.
	public int cierraTempRespuestas(){
		if(this.getObjetoDestino() == null){
			return DuplicadorRespuestas.ERROR_NO_DEFINIDO_OBJETO_DESTINO;
		}
		try{
			PreparedStatement pstmt = this.getConexion().prepareStatement("DELETE FROM respuestas_temp WHERE id_instancia_objetos = ?");
			pstmt.setInt(1, this.getObjetoDestino().getId());
			pstmt.execute();
		}catch (Exception e){
			e.printStackTrace();
			return DuplicadorRespuestas.ERROR_NO_ESPECIFICADO;
		}
		return DuplicadorRespuestas.CORRECTO;
	}

}
