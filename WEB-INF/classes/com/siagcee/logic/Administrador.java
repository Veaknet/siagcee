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

public class Administrador extends Usuario{

	String tipoUsuario;
	String nombre;
	String email;

	public Administrador(){
		super();
		tipoUsuario = "";
		nombre = "";
		email = "";
	}

	public Administrador(Connection _miConexion){
		super(_miConexion);
		tipoUsuario = "";
		nombre = "";
		email = "";
	}

	public Administrador(Connection _miConexion, String _usuario, String _clave){
		super(_miConexion, _usuario, _clave);
		tipoUsuario = "";
		nombre = "";
		email = "";
		Verificar(_miConexion, _usuario, _clave, true);
	}

	public Administrador(Connection _miConexion, int _id){
		super(_miConexion);
		this.setUsuarioId(_id);
		tipoUsuario = "";
		nombre = "";
		email = "";
		cargaUsuario();
	}

	//_valida = true hace validacion, false crea el usuario en la BD
	public Administrador(Connection _miConexion, String _usuario, String _clave, boolean _valida){
		super(_miConexion, _usuario, _clave);
		tipoUsuario = "";
		nombre = "";
		email = "";
		Verificar(_miConexion, _usuario, _clave, _valida);
	}

	public boolean Verificar(Connection _miConexion, String _usuario, String _clave, boolean _valida){
		if(_valida){
			return iniciarSesion(_usuario, _clave);
		}else{
			ingresaUsuario(_usuario, _clave);
			return iniciarSesion(_usuario, _clave);
		}
	}

	public void setTipoUsuario(String _tipoUsuario){
		tipoUsuario = _tipoUsuario;
		ingresaUsuario(this.getUsername(), this.getClave());
	}

	public String getTipoUsuario(){
		return tipoUsuario;
	}

	public void actualizaBD(){
		ingresaUsuario(this.Username, this.clave);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String _nombre) {
		this.nombre = _nombre;
		ingresaUsuario(this.Username, this.clave);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String _email) {
		this.email = _email;
		ingresaUsuario(this.Username, this.clave);
	}

	public void ingresaUsuario(String _usuario, String _clave){
		try {
			if (this.getValidado()) {
				//ejecuto UPDATE
				PreparedStatement pstmt;
				if(_clave.equals("")){
					pstmt = getConexion().prepareStatement("UPDATE administradores SET tipo_administrador = ?, email=?, nombre=?  WHERE usuario = ?");
					pstmt.setString(2, email);
					pstmt.setString(3, nombre);
					if(this.tipoUsuario.equals("")){
						pstmt.setString(1, this.tipoUsuario);
						this.tipoUsuario = "regular";
					}else{
						pstmt.setString(1, this.tipoUsuario);
						this.tipoUsuario = "superadministrador";
					}
					pstmt.setString(4, _usuario);
				}else{
					pstmt = getConexion().prepareStatement("UPDATE administradores SET clave = ?, email=?, nombre=? ,tipo_administrador = ? WHERE usuario = ?");
					pstmt.setString(1, _clave);
					pstmt.setString(2, email);
					pstmt.setString(3, nombre);
					if(this.tipoUsuario.equals("")){
						pstmt.setString(4, this.tipoUsuario);
						this.tipoUsuario = "regular";
					}else{
						pstmt.setString(4, this.tipoUsuario);
						this.tipoUsuario = "superadministrador";
					}
					pstmt.setString(5, _usuario);
				}
				pstmt.execute();
			}else{
				if(!_usuario.equals("")){
					//ejecuto INSERT
					PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_administradores') AS numero");
					ResultSet _rs = _pstmt.executeQuery();
					_rs.next();
					int siguiente = _rs.getInt("numero");

					PreparedStatement pstmt = getConexion().prepareStatement("INSERT INTO administradores(id_administradores, usuario, clave, tipo_administrador, nombre, email) VALUES (?, ?, ?, ?, ?, ?)");
					pstmt.setInt(1, siguiente);
					pstmt.setString(2, _usuario);
					pstmt.setString(3, _clave);
					if(this.tipoUsuario.equals("")){
						pstmt.setString(4, this.tipoUsuario);
						this.tipoUsuario = "regular";
					}else{
						pstmt.setString(4, this.tipoUsuario);
						this.tipoUsuario = "superadministrador";
					}
					pstmt.setString(5, nombre);
					pstmt.setString(6, email);
					pstmt.execute();
					this.setCargadaDeBD(true);
					this.setUsuarioId(siguiente);
					this.Username = _usuario;
					this.clave = _clave;
				}
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public boolean iniciarSesion(String _usuario, String _clave){
		try{
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM administradores where usuario = ? and clave = ?");
			pstmt.setString(1, _usuario);
			pstmt.setString(2, _clave);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				this.usuarioId = rs.getInt("id_administradores");
				this.nombre = rs.getString("nombre");
				this.email = rs.getString("email");
				this.Username = _usuario;
				this.clave = _clave;
				this.tipoUsuario = rs.getString("tipo_administrador");
				this.validado = true;
				return true;
			}	else {
				this.validado = false;
				return false;
			}
		}catch(Exception e){
			//e.printStackTrace();
			this.validado = false;
			return false;
		}
	}

	public void cargaUsuario(){
		try{
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM administradores where id_administradores = ?");
			pstmt.setInt(1, this.getUsuarioId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				this.usuarioId = rs.getInt("id_administradores");
				this.Username = rs.getString("usuario");
				this.nombre = rs.getString("nombre");
				this.email = rs.getString("email");
				this.clave = rs.getString("clave");
				this.tipoUsuario = rs.getString("tipo_administrador");
				this.validado = true;
			}	else {
				this.usuarioId = -1;
				this.Username = "";
				this.nombre = "";
				this.email = "";
				this.clave = "";
				this.tipoUsuario = "";
				this.validado = false;
			}
		}catch(Exception e){
			//e.printStackTrace();
			this.validado = false;
		}
	}

	public void delUsuario(){
		try{
			PreparedStatement pstmt = this.getConexion().prepareStatement("DELETE FROM administradores where id_administradores = ?");
			pstmt.setInt(1, this.getUsuarioId());
			pstmt.execute();
			this.usuarioId = -1;
			this.Username = "";
			this.nombre = "";
			this.email = "";
			this.clave = "";
			this.tipoUsuario = "";
			this.validado = false;
		}catch(Exception e){
			//e.printStackTrace();
			this.validado = false;
		}
	}

	public static Vector dameTodos(Connection micon){
		Vector _usuarios = new Vector();
		try{
			PreparedStatement pstmt = micon.prepareStatement("SELECT * FROM administradores");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Administrador _temp = new Administrador(micon);
				_temp.usuarioId = rs.getInt("id_administradores");
				_temp.Username = rs.getString("usuario");
				_temp.nombre = rs.getString("nombre");
				_temp.email = rs.getString("email");
				_temp.clave = rs.getString("clave");
				_temp.tipoUsuario = rs.getString("tipo_administrador");
				_temp.validado = true;
				_usuarios.add(_temp);
			}
		}catch(Exception e){
			//e.printStackTrace();
			return new Vector();
		}
		return _usuarios;
	}
}
