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

public class Censo extends Objeto{

	public Censo(){
		super();
	}

	public Censo(Usuario _usuario, Connection _miConexion){
		super(_usuario, _miConexion);
	}

	public Censo(Usuario _usuario, Connection _miConexion, String _censo){
		super(_usuario, _miConexion, _censo);
		this.ingresaABd();
	}

	public Censo(Usuario _usuario, Connection _miConexion, int _idCenso){
		super(_usuario, _miConexion, _idCenso);
		this.recargaDeBd();
	}

	public void setPublico(boolean _publico) {
		super.setPublico(_publico);
		this.ingresaABd();
	}

	//indica nombre amigable del objeto
	public void setObjeto(String _objeto){
		this.objeto = _objeto;
		ingresaABd();
	}

	private void recargaDeBd(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM pool_objetos WHERE id_pool_objetos = ? AND tipo_objeto = 1");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.objeto = rs.getString("nombre_amistoso");
				this.publico = rs.getBoolean("visible");
				this.setCargadaDeBD(true);
			}else{
				this.idObjeto = -1;
				this.objeto = "";
				this.misPreguntas = new Vector();
				this.publico = true;
				this.setCargadaDeBD(false);
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private void ingresaABd() {
		//si no se ha provisto de un nombre amistoso para la pregunta no es posible insertarla en la BD
		if(!(this.getObjeto()).equals("")){
			try {
				if (getCargadaDeBD()) {
					//ejecuto UPDATE
					PreparedStatement pstmt = getConexion().prepareStatement("UPDATE pool_objetos SET nombre_amistoso = ? , tipo_objeto = ?, visible = ? WHERE id_pool_objetos = ?");
					pstmt.setString(1, this.getObjeto());
					pstmt.setInt(2, 1);  //1 = censo
					pstmt.setBoolean(3, this.getPublico());
					pstmt.setInt(4, this.getId());
					pstmt.execute();
				}else{
					//ejecuto INSERT
					PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_pool_objetos') AS numero");
					ResultSet _rs = _pstmt.executeQuery();
					_rs.next();
					int siguiente = _rs.getInt("numero");

					PreparedStatement pstmt = getConexion().prepareStatement("INSERT INTO pool_objetos(id_pool_objetos, nombre_amistoso, tipo_objeto, creado_por, visible) VALUES (?, ?, ?, ?, ?)");
					pstmt.setInt(1, siguiente);
					pstmt.setString(2, this.getObjeto());
					pstmt.setInt(3, 1); //1 = censo
					pstmt.setInt(4, this.getUsuario().getUsuarioId());
					pstmt.setBoolean(5, this.getPublico());
					pstmt.execute();
					this.setCargadaDeBD(true);
					this.setId(siguiente);
				}
			}
			catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	//retorna un Vector con todas los objetos disponibles dado un usuario
	//_cargaSelectiva = 0   --> todas
	//_cargaSelectiva = 1   --> libres (no instanciadas)
	//_visible solo muestra los objetos que esten marcadas como visibles por el creador, caso contrario las trae todas
	//_soloPropias solo mostrara los objetos que sean del usuario (para editarlos) o todas las comunes (para utilzarlos o instanciarlos)
	public static Vector todosObjetos(Usuario _usuario, Connection _miConexion, int _cargaSelectiva, boolean _visible, boolean _soloPropias){
		Vector _lista = new Vector();
		ResultSet rs = null;
		Censo ObjTemp = null;

		rs = obtenerDataPoolObjetos(_usuario, _miConexion, _cargaSelectiva, _visible, _soloPropias);
		try {
			while (rs.next()){
				ObjTemp = new Censo(_usuario, _miConexion);
				ObjTemp.idObjeto = rs.getInt("id_pool_objetos");
				ObjTemp.objeto = rs.getString("nombre_amistoso");
				ObjTemp.publico = rs.getBoolean("visible");
				ObjTemp.cargadaDeBD = true;
				_lista.add(ObjTemp);
			}
		}
		catch (Exception e) {
			return new Vector();
		}
		return _lista;
	}

	//retorna un Vector con todas las preguntas disponibles dado un usuario editables o no editables
	public static Vector todosObjetos(Usuario _usuario, Connection _miConexion, boolean _visible, boolean _soloPropias){
		return todosObjetos(_usuario, _miConexion, 0, _visible, _soloPropias);
	}

	//retorna un Resulset de todos los objetos disponibles para un usuario
	//_cargaSelectiva = 0   --> todas
	//_cargaSelectiva = 1   --> libres (no instanciadas)
	//_visible solo muestra los objetos que esten marcadas como visibles por el creador, caso contrario las trae todas
	//_soloPropias solo mostrara los objetos que sean del usuario (para editarlos) o todas las comunes (para utilzarlos o instanciarlos)
	private static ResultSet obtenerDataPoolObjetos(Usuario _usuario, Connection _miConexion, int _cargaSelectiva, boolean _visible, boolean _soloPropias){
		ResultSet rs = null;
		String addSQL = "";
		if(_visible){
			 addSQL = addSQL+" AND visible = true";
		}

		try {
			PreparedStatement pstmt = null;
			if(_cargaSelectiva == 0){
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_objetos WHERE creado_por = ? AND tipo_objeto = 1 "+addSQL);
				if(!_soloPropias){
					pstmt = _miConexion.prepareStatement("SELECT * FROM pool_objetos WHERE (creado_por = ? OR creado_por IN (SELECT id_administradores FROM administradores WHERE tipo_administrador = 'superadministrador')) AND tipo_objeto = 1 "+addSQL);
				}
				pstmt.setInt(1, _usuario.getUsuarioId());
			}else if(_cargaSelectiva == 1){
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_objetos WHERE creado_por = ? AND tipo_objeto = 1 AND id_pool_objetos NOT IN(SELECT id_pool_objetos FROM instancia_objetos) "+addSQL);
				if(!_soloPropias){
					pstmt = _miConexion.prepareStatement("SELECT * FROM pool_objetos WHERE (creado_por = ? OR creado_por IN (SELECT id_administradores FROM administradores WHERE tipo_administrador = 'superadministrador')) AND tipo_objeto = 1 AND id_pool_objetos NOT IN(SELECT id_pool_objetos FROM instancia_objetos) "+addSQL);
				}
				pstmt.setInt(1, _usuario.getUsuarioId());
			}else{
				//por defecto todas
				pstmt = _miConexion.prepareStatement("SELECT * FROM pool_objetos WHERE creado_por = ? AND tipo_objeto = 1 "+addSQL);
				if(!_soloPropias){
					pstmt = _miConexion.prepareStatement("SELECT * FROM pool_objetos WHERE (creado_por = ? OR creado_por IN (SELECT id_administradores FROM administradores WHERE tipo_administrador = 'superadministrador')) AND tipo_objeto = 1 "+addSQL);
				}
				pstmt.setInt(1, _usuario.getUsuarioId());
			}
			rs = pstmt.executeQuery();
		}catch (Exception e) {
			return null;
		}
		return rs;
	}

}
