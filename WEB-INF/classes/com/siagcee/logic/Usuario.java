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

public class Usuario extends ObjetoBase{

	int usuarioId;
	String Username;
	String clave;
	boolean validado;
	
	public Usuario(){
		usuarioId = -1;
		Username = new String("");
		clave = new String("");
		validado = false;
	}

	public Usuario(Connection _miConexion) {
		super();
		this.setConexion(_miConexion);
		usuarioId = -1;
		Username = new String("");
		clave = new String("");
		validado = false;
	}

	public Usuario(Connection _miConexion, String _usuario, String _clave) {
		super();
		this.setConexion(_miConexion);
		usuarioId = -1;
		Username = new String("");
		clave = new String("");
		validado = false;
		iniciarSesion(_usuario, _clave);
	}

	//metodo a ser implementado en las clases heredadas
	public boolean iniciarSesion(String _usuario, String _clave){
		return false;
	}

	public void invalidaUsuario(){
		usuarioId = -1;
		Username = new String("");
		clave = new String("");
		validado = false;
	}

	//usuario validado ?
	public boolean getValidado(){
		return validado;
	}

	//establece si se ha validado el usuario
	protected void setValidado(boolean _valor){
		validado = _valor;
	}

	protected void setUsername(String _username){
		Username = _username;	
	}

	public void setClave(String _clave){
		clave = _clave;	
	}

	protected void setUsuarioId(int _idUsuario){
		usuarioId = _idUsuario;
	}

	public String getUsername(){
		return Username;
	}

	protected String getClave(){
		return clave;
	}

	public int getUsuarioId(){
		return usuarioId;
	}
}
