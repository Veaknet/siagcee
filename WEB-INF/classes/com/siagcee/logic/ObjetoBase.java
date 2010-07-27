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

public class ObjetoBase {
	protected Connection miConexion;
	protected Usuario usuario;
	protected boolean cargadaDeBD;
	protected boolean publico;

	public ObjetoBase(){
		miConexion = null;
		usuario = null;
		cargadaDeBD = false;
		publico = true;
	}
	
	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public ObjetoBase(Usuario _usuario, Connection _miConexion) {
		miConexion = _miConexion;
		usuario = _usuario;
		cargadaDeBD = false;
		publico = true;
	}

	public void setUsuario(Usuario _usuario){
		usuario = _usuario;
	}

	public void setConexion(Connection _miConexion){
		miConexion = _miConexion;
	}

	public void setCargadaDeBD(boolean _cargadaDeBD){
		cargadaDeBD = _cargadaDeBD;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public Connection getConexion(){
		return miConexion;
	}

	public boolean getCargadaDeBD(){
		return cargadaDeBD;
	}
}
