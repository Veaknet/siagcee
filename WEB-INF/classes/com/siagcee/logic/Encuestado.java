package com.siagcee.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 24/11/2009
 * Hora: 01:21:48 PM
 */

/********************************/
//VERSION 2.0
// HECHA PARA LA REVISION DEL PROF MIRABAL.
// Username es considerado la instancia de objeto (poblacion) a la que pertenece el usuario o encuestado
// clave su identificador clave en la poblacion
/********************************/

public class Encuestado extends Usuario{

	public Encuestado(){
		super();
	}

	public Encuestado(Connection _miConexion){
		super(_miConexion);
	}

	public Encuestado(Connection _miConexion, String _idPoblacion, String _campoClave){
		super(_miConexion, _idPoblacion, _campoClave);
		Verificar(_miConexion, _idPoblacion, _campoClave);
	}

	public Encuestado(Connection _miConexion, String _idPoblacion){
		super(_miConexion, _idPoblacion, "");
		Verificar(_miConexion, _idPoblacion, "");
	}

	public Encuestado(Connection _miConexion, int _id){
		super();
		this.setConexion(_miConexion);
		iniciarSesion(_id);
	}

	public InstanciaObjeto getPoblacion(){
		return new InstanciaObjeto(this.getUsuario(), this.getConexion(), Integer.parseInt(this.Username));
	}

	public void setPoblacion(InstanciaObjeto _poblacion){
		this.Username = String.valueOf(_poblacion.getId());
	}

	public void setCampoClave(String _campoClave){
		this.clave = _campoClave;
	}

	public String getCampoClave(){
		return this.clave;
	}

	//si _valida solo se valida
	//caso contrario se registra el usuario y se valida posteriormente
	public boolean Verificar(Connection _miConexion, String _idPoblacion, String _campoClave){
		ingresaUsuario(_idPoblacion, _campoClave);
		return iniciarSesion(_idPoblacion, _campoClave);
	}

	public void actualizaBD(){
		ingresaUsuario(this.Username, this.clave);
	}

	public void ingresaUsuario(String _idPoblacion, String _campoClave){
		try {
			if(!_idPoblacion.equals("")){
				if(Encuestado.existeUsuario(this.getConexion(), _idPoblacion, _campoClave)){
					return;
				}
				//ejecuto INSERT
				PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_usuarios') AS numero");
				ResultSet _rs = _pstmt.executeQuery();
				_rs.next();
				int siguiente = _rs.getInt("numero");

				PreparedStatement pstmt = getConexion().prepareStatement("INSERT INTO usuarios(id_usuarios, id_poblacion, campo_clave) VALUES (?, ?, ?)");
				pstmt.setInt(1, siguiente);
				pstmt.setInt(2, Integer.parseInt(_idPoblacion));
				if(_campoClave.equals("")){
					pstmt.setString(3, "correlativo_anonimo_"+String.valueOf(siguiente));
				}else{
					pstmt.setString(3, _campoClave);
				}
				pstmt.execute();
				this.setCargadaDeBD(true);
				this.setUsuarioId(siguiente);
				this.Username = _idPoblacion;
				this.clave = _campoClave;
			}
		}
		catch (Exception e) {e.printStackTrace();}
	}
	
	public boolean iniciarSesion(String _idPoblacion, String _campoClave){
		try{
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM usuarios where id_poblacion = ? and campo_clave = ?");
			pstmt.setInt(1, Integer.parseInt(_idPoblacion));
			pstmt.setString(2, _campoClave);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				this.usuarioId = rs.getInt("id_usuarios");
				this.Username = _idPoblacion;
				this.clave = _campoClave;
				this.validado = true;
				return true;
			}	else {
				this.validado = false;
				return false;
			}
		}catch(Exception e){e.printStackTrace();
			this.validado = false;
			return false;
		}
	}

	private boolean iniciarSesion(int _id){
		try{
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM usuarios where id_usuarios = ?");
			pstmt.setInt(1, _id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				this.usuarioId = rs.getInt("id_usuarios");
				this.Username = String.valueOf(rs.getInt("id_poblacion"));
				this.clave = rs.getString("campo_clave");
				this.validado = true;
				return true;
			}	else {
				this.validado = false;
				return false;
			}
		}catch(Exception e){e.printStackTrace();
			this.validado = false;
			return false;
		}
	}

	public void delUsuario(){
		try{
            Respuesta.delRespuestasDeUsuario(this.getUsuario(), this.getConexion(), this.getPoblacion());
			PreparedStatement pstmt = this.getConexion().prepareStatement("DELETE FROM usuarios WHERE id_usuarios = ?");
			pstmt.setInt(1, this.getUsuarioId());
			pstmt.execute();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	//indica si un username existe(true) o no(false)
	public static boolean existeUsuario(Connection _micon, String _idPoblacion, String _campoClave){
		try{
			PreparedStatement pstmt = _micon.prepareStatement("SELECT * FROM usuarios WHERE id_poblacion = ? and campo_clave = ?");
			pstmt.setInt(1, Integer.parseInt(_idPoblacion));
			pstmt.setString(2, _campoClave);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}	else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
