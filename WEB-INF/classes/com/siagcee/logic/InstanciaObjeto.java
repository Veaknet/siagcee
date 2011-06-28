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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class InstanciaObjeto extends ObjetoBase{

	int id;
	String objeto;
	String idPublico;
	Date fecha_inicio;
	Date fecha_cierre;
	Objeto objetoAsociado;
	InstanciaObjeto poblacion_asociada;
	boolean eliminado;
	int tipoAcceso;  //0 = publico (cualquiera llena el instrumento)  1 = restringido (solo los escogidos lo llenan)

	public InstanciaObjeto(){
		super();
		id = -1;
		tipoAcceso = 0;
		objeto = "";
		idPublico = "";
		fecha_inicio = null;
		fecha_cierre = null;
		objetoAsociado = null;
		poblacion_asociada = null;
		eliminado = false;
		this.setIdPublico(this.generaIdPublico());
		this.cargadaDeBD = false;
	}

	public InstanciaObjeto(Usuario _usuario, Connection _miConexion){
		super(_usuario, _miConexion);
		id = -1;
		tipoAcceso = 0;
		objeto = "";
		idPublico = "";
		fecha_inicio = null;
		fecha_cierre = null;
		objetoAsociado = null;
		eliminado = false;
		poblacion_asociada = null;
		this.setIdPublico(this.generaIdPublico());
		this.cargadaDeBD = false;
	}

	public InstanciaObjeto(Usuario _usuario, Connection _miConexion, String _objeto){
		super(_usuario, _miConexion);
		id = -1;
		tipoAcceso = 0;
		objeto = _objeto.trim();
		idPublico = "";
		fecha_inicio = null;
		fecha_cierre = null;
		poblacion_asociada = null;
		eliminado = false;
		objetoAsociado = null;
		this.cargadaDeBD = false;
		this.setIdPublico(this.generaIdPublico());
		this.ingresaABd();
	}

	public InstanciaObjeto(Usuario _usuario, Connection _miConexion, int _id){
		super(_usuario, _miConexion);
		id = _id;
		tipoAcceso = 0;
		objeto = "";
		idPublico = "";
		fecha_inicio = null;
		fecha_cierre = null;
		objetoAsociado = null;
		poblacion_asociada = null;
		eliminado = false;
		this.cargadaDeBD = false;
		this.recargaDeBd(new Date(), 0);
	}

	//selectivo
	//1 = carga solo cuando la fecha indicada este entre inicio o cierre
	//0 = carga sin importar cierre o inicio
	public InstanciaObjeto(Usuario _usuario, Connection _miConexion, int _id, int _selectivo){
		super(_usuario, _miConexion);
		id = _id;
		tipoAcceso = 0;
		objeto = "";
		idPublico = "";
		fecha_inicio = null;
		fecha_cierre = null;
		objetoAsociado = null;
		eliminado = false;
		poblacion_asociada = null;
		this.cargadaDeBD = false;
		this.recargaDeBd(new Date(), _selectivo);
	}

	public InstanciaObjeto(Usuario _usuario, Connection _miConexion, String _objeto, Objeto _objAsoc){
		super(_usuario, _miConexion);
		id = -1;
		tipoAcceso = 0;
		objeto = _objeto.trim();
		idPublico = "";
		fecha_inicio = null;
		fecha_cierre = null;
		eliminado = false;
		objetoAsociado = _objAsoc;
		poblacion_asociada = null;
		this.cargadaDeBD = false;
		this.setIdPublico(this.generaIdPublico());
		this.ingresaABd();
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean _eliminado) {
		this.eliminado = _eliminado;
		this.ingresaABd();
	}

	public InstanciaObjeto getPoblacion_asociada() {
		return poblacion_asociada;
	}//retorna el tipo de acceso al objeto

	public void setPoblacion_asociada(InstanciaObjeto _poblacion_asociada) {
		this.poblacion_asociada = _poblacion_asociada;
		this.ingresaABd();
	}

	public int getAcceso(){
		return this.tipoAcceso;
	}

	//indica el tipo de acceso al objeto
	public void setAcceso(int _acceso){
		this.tipoAcceso = _acceso;
		this.ingresaABd();
	}

	//indicamos nombre del objeto
	public void setObjeto(String _objeto){
		this.objeto = _objeto.trim();
		this.ingresaABd();
	}

	//obtenemos nombre del objeto
	public String getObjeto(){
		return this.objeto.trim();
	}

	//dado un String se crea un objeto Date...
	//Lanza una exception
	public void setFechaInicio(String _fecha){
		if(_fecha.equals("")){
			this.fecha_inicio = new Date();
		}else{
			SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
			try{
				this.fecha_inicio = _temp.parse(_fecha);
			}catch(Exception e){
				//e.printStackTrace();
				this.fecha_inicio = null;
			}
		}
		this.ingresaABd();
	}

	//se indica un objeto Date para la fecha en que inicia el objeto
	public void setFechaInicio(Date _fecha){
		this.fecha_inicio = _fecha;
		this.ingresaABd();
	}

	//se obtiene un objeto Date para la fecha en que inicia el objeto
	public Date getFechaInicio(){
		return this.fecha_inicio;
	}

	//dado un String se crea un objeto Date...
	//Lanza una exception
	public void setFechaCierre(String _fecha){
		if(_fecha.equals("")){
			this.fecha_cierre = new Date();
		}else{
			SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
			try{
				this.fecha_cierre = _temp.parse(_fecha);
			}catch(Exception e){
				//e.printStackTrace();
				this.fecha_cierre = null;
			}
		}
		this.ingresaABd();
	}

	//se indica un objeto Date para la fecha en que cierra el objeto
	public void setFechaCierre(Date _fecha){
		this.fecha_cierre = _fecha;
		this.ingresaABd();
	}

	//se obtiene un objeto Date para la fecha en que cierra el objeto
	public Date getFechaCierre(){
		return this.fecha_cierre;
	}

	//obtenemos el Objeto instanciado
	public void asociaObjeto(Objeto _objAsoc){
		this.objetoAsociado = _objAsoc;
		this.ingresaABd();
	}

	//indicamos el Objeto que estamos instanciando
	public Objeto getObjetoAsociado(){
		return this.objetoAsociado;
	}

	protected void setId(int _id){
		this.id = _id;
	}

	//ID interno del objeto
	public int getId(){
		return this.id;
	}

	//ingresamos un String que identifica al objeto
	//principalmente usado para enlaces web ej:  censo.do?id=aaasig8432nsjnvnaiq7827
	public void setIdPublico(String _id){
		_id = _id.trim();
		if(_id.equals("")){
			this.idPublico = this.generaIdPublico();
			return;
		}
		this.idPublico = _id;
		if(this.cargadaDeBD){
			this.ingresaABd();
		}
	}

	//obtenemos un String que identifica al objeto
	//principalmente usado para enlaces web ej:  censo.do?id=aaasig8432nsjnvnaiq7827
	public String getIdPublico(){
		return this.idPublico.trim();
	}

	//selectivo
	//1 = carga solo cuando la fecha indicada este entre inicio o cierre
	//0 = carga sin importar cierre o inicio
	public void recargaDeBd(Date _fecha, int _selectivo){
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			if(_selectivo == 1){
				pstmt = this.getConexion().prepareStatement("SELECT * FROM instancia_objetos WHERE id_instancia_objetos = ? AND fecha_cierre >= ? AND fecha_inicio <= ?");
				pstmt.setInt(1, this.getId());
				pstmt.setDate(2, new java.sql.Date(_fecha.getTime()));
				pstmt.setDate(3, new java.sql.Date(_fecha.getTime()));
			}else{
				pstmt = this.getConexion().prepareStatement("SELECT * FROM instancia_objetos WHERE id_instancia_objetos = ?");
				pstmt.setInt(1, this.getId());
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.objeto = rs.getString("objeto").trim();
				this.id = rs.getInt("id_instancia_objetos");
				this.idPublico = rs.getString("identificador_publico").trim();
				this.tipoAcceso = rs.getInt("tipo_acceso");
				fecha_inicio = new Date(rs.getDate("fecha_inicio").getTime());
				fecha_cierre = new Date(rs.getDate("fecha_cierre").getTime());
				this.eliminado = rs.getBoolean("eliminado");
				objetoAsociado = Objeto.retornaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_pool_objetos"));
				poblacion_asociada = new InstanciaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("poblacion_asociada"));
				this.setCargadaDeBD(true);
			}else{
				id = -1;
				this.tipoAcceso = 0;
				objeto = "";
				idPublico = "";
				this.setIdPublico(this.generaIdPublico());
				fecha_inicio = null;
				fecha_cierre = null;
				this.eliminado = false;
				objetoAsociado = null;
				poblacion_asociada = null;
				this.setCargadaDeBD(false);
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public void ingresaABd(){
		//si no se ha provisto de un nombre amistoso para el objeto no es posible insertarla en la BD
		PreparedStatement _pstmt, pstmt;
		if((!(this.getObjeto()).equals("")) && (this.getObjetoAsociado() != null) && (this.getFechaInicio() != null) && (this.getFechaCierre() != null)){
			try {
				if (getCargadaDeBD()) {
					//ejecuto UPDATE
					if(this.esEditable()){
						pstmt = getConexion().prepareStatement("UPDATE instancia_objetos SET objeto = ? , fecha_cierre = ? , fecha_inicio = ? , id_pool_objetos = ? , identificador_publico = ? , tipo_acceso = ?, poblacion_asociada = ?, eliminado = ? WHERE id_instancia_objetos = ?");
						pstmt.setString(1, this.getObjeto().trim());
						pstmt.setDate(2, new java.sql.Date(this.getFechaCierre().getTime()));
						pstmt.setDate(3, new java.sql.Date(this.getFechaInicio().getTime()));
						pstmt.setInt(4, this.getObjetoAsociado().getId());
						pstmt.setString(5, this.getIdPublico().trim());
						pstmt.setInt(6, this.getAcceso());
						if(this.getPoblacion_asociada() != null){
							pstmt.setInt(7, this.getPoblacion_asociada().getId());
						}else{
							if(this.getObjetoAsociado().getClass().toString().contains("Encuesta")){
								return;
							}else{
								pstmt.setInt(7, -1);
							}
						}
						pstmt.setBoolean(8, this.isEliminado());
						pstmt.setInt(9, this.getId());
						pstmt.execute();
					}
				}else{
					//ejecuto INSERT
					_pstmt = getConexion().prepareStatement("SELECT nextval('seq_instancia_objetos') AS numero");
					ResultSet _rs = _pstmt.executeQuery();
					_rs.next();
					int siguiente = _rs.getInt("numero");

					pstmt = getConexion().prepareStatement("INSERT INTO instancia_objetos(id_instancia_objetos, objeto, fecha_cierre, fecha_inicio, id_pool_objetos, identificador_publico, creado_por, tipo_acceso, poblacion_asociada, eliminado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					pstmt.setInt(1, siguiente);
					pstmt.setString(2, this.getObjeto().trim());
					pstmt.setDate(3, new java.sql.Date(this.getFechaCierre().getTime()));
					pstmt.setDate(4, new java.sql.Date(this.getFechaInicio().getTime()));
					pstmt.setInt(5, this.getObjetoAsociado().getId());
					pstmt.setString(6, this.getIdPublico().trim());
					pstmt.setInt(7, this.getUsuario().getUsuarioId());
					pstmt.setInt(8, this.getAcceso());
					if(this.getPoblacion_asociada() != null){
						pstmt.setInt(9, this.getPoblacion_asociada().getId());
					}else{
						pstmt.setInt(9, -1);
					}
					pstmt.setBoolean(10, this.isEliminado());
					pstmt.execute();
					this.cargadaDeBD = true;
					this.setId(siguiente);
				}
			}catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	//indica si esta InstanciaObjeto puede ser modificada
	public boolean esEditable(){
		SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(getObjetoAsociado().getClass().toString().contains("Censo")){
			return ((_dateFormat.format(new Date()).compareTo(this.getFechaCierre().toString()) >= 0) || (_dateFormat.format(new Date()).compareTo(this.getFechaInicio().toString()) < 0));
		}else{
			return (_dateFormat.format(new Date()).compareTo(this.getFechaInicio().toString()) < 0);
		}
		//return this.getFechaInicio().getTime() > (new Date()).getTime();
	}

	//elimina la instancia de pregunta incluyendo la BD
	//explicitamente debe indicarse el eliminar una instancia que no sea editable
	public void delInstanciaObjeto(boolean forzarDelete) {
		if(this.esEditable() || forzarDelete){
			try {
				//elimino accesosEncuestados
				AccesosEncuestados.delAccesosEncuestados(this.getConexion(), this);

				//elimino las respuestas primero
				Respuesta.delRespuesta(this.getConexion(), this);

				PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM instancia_objetos WHERE id_instancia_objetos = ?");
				pstmt.setInt(1, getId());
				pstmt.execute();
				pstmt = getConexion().prepareStatement("DELETE FROM usuarios WHERE id_poblacion = ?");
				pstmt.setInt(1, getId());
				pstmt.execute();
				this.fecha_inicio = null;
				this.fecha_cierre = null;
				this.objeto = "";
				this.objetoAsociado = null;
				this.poblacion_asociada = null;
				this.id = -1;
				this.idPublico = "";
				this.setIdPublico(this.generaIdPublico());
				this.setCargadaDeBD(false);
			}catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	//retorna todas las instancias de objetos
	//_publico es verdad para declarar que los encuestados estan intentando acceder a las instancias
	//selectivo
	//1 = carga solo cuando la fecha indicada este entre inicio o cierre
	//0 = carga sin importar cierre o inicio

	public static Vector todosObjetosInstanciados(Usuario _usuario, Connection _miConexion, boolean _publico, int _selectivo){
		return todosObjetosInstanciados(_usuario, _miConexion, _publico, _selectivo, false);
	}

	public static Vector todosObjetosInstanciados(Usuario _usuario, Connection _miConexion, boolean _publico, int _selectivo, boolean eliminados){
		Vector _lista = new Vector();
		ResultSet rs = null;
		InstanciaObjeto ObjTemp = null;

		rs = obtenerDataInstanciaObjeto(_usuario, _miConexion, _publico, _selectivo);
		try {
			while (rs.next()){
				if((eliminados && !rs.getBoolean("eliminado")) || (!eliminados && rs.getBoolean("eliminado"))){
					continue;
				}
				if(_publico){
					Administrador _user = new Administrador(_miConexion, rs.getInt("creado_por"));
					ObjTemp = new InstanciaObjeto(_user, _miConexion);
				}else{
					ObjTemp = new InstanciaObjeto(_usuario, _miConexion);
				}
				ObjTemp.objeto = rs.getString("objeto").trim();
				ObjTemp.fecha_inicio = rs.getDate("fecha_inicio");
				ObjTemp.fecha_cierre = rs.getDate("fecha_cierre");
				ObjTemp.id = rs.getInt("id_instancia_objetos");
				ObjTemp.idPublico = rs.getString("identificador_publico").trim();
				ObjTemp.tipoAcceso = rs.getInt("tipo_acceso");
				ObjTemp.eliminado = rs.getBoolean("eliminado");
				ObjTemp.objetoAsociado = Objeto.retornaObjeto(_usuario,_miConexion, rs.getInt("id_pool_objetos"));
				ObjTemp.poblacion_asociada = new InstanciaObjeto(_usuario,_miConexion, rs.getInt("poblacion_asociada"));
				ObjTemp.cargadaDeBD = true;
				_lista.add(ObjTemp);
			}
		}
		catch (Exception e) {
			return new Vector();
		}
		return _lista;
	}

	public static Vector todosObjetosInstanciados(Usuario _usuario, Connection _miConexion){
		return todosObjetosInstanciados(_usuario, _miConexion, false, 0);
	}

	//retorna un Resulset de todas las instancias de objetos disponibles para un usuario
	//_publico es verdad para declarar que los encuestados estan intentando acceder a las instancias
	//selectivo
	//1 = carga solo cuando la fecha indicada este entre inicio o cierre
	//0 = carga sin importar cierre o inicio
	private static ResultSet obtenerDataInstanciaObjeto(Usuario _usuario, Connection _miConexion, boolean _publico, int _selectivo){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = null;
			if(_publico || _usuario.getTipoUsuario().equals("superadministrador")){
				if(_selectivo == 1){
					pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_objetos WHERE fecha_cierre > ? AND fecha_inicio <= ?");
					pstmt.setDate(1, new java.sql.Date((new Date()).getTime()));
					pstmt.setDate(2, new java.sql.Date((new Date()).getTime()));
				}else{
					pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_objetos");
				}
			}else{
				if(_selectivo == 1){
					pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_objetos WHERE creado_por = ? AND fecha_cierre > ? AND fecha_inicio <= ?");
					pstmt.setInt(1, _usuario.getUsuarioId());
					pstmt.setDate(2, new java.sql.Date((new Date()).getTime()));
					pstmt.setDate(3, new java.sql.Date((new Date()).getTime()));
				}else{
					pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_objetos WHERE creado_por = ?");
					pstmt.setInt(1, _usuario.getUsuarioId());
				}
			}
			rs = pstmt.executeQuery();
		}catch (Exception e) {
			return null;
		}
		return rs;
	}

	//puede no indicarse la pregunta. Lo cual devolvería todas las respuestas de esta instancia
	public Vector obtenerRespuestas(InstanciaPregunta _pregunta){
		try{
			return Respuesta.todasRespuestas(this.getUsuario(), this.getConexion(), _pregunta, this);
		}catch(Exception e){
			return new Vector();
		}
	}

	public static int cargaInsObj(String _id, Connection _miConexion){
		int _miIns = -1;
		ResultSet rs = null;
		try{
			PreparedStatement pstmt = null;
			pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_objetos WHERE identificador_publico = ?");
			pstmt.setString(1, _id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				_miIns = rs.getInt("id_instancia_objetos");
			}
		}catch(Exception e){
			return -1;
		}
		return _miIns;
	}

	protected String generaIdPublico(){
		String _idPublico = "";
		_idPublico = String.valueOf(Math.random());
		_idPublico = _idPublico.replace('.', 'd');
		_idPublico = _idPublico.replace('0', 'i');
		return _idPublico;
	}

}
