package com.siagcee.logic;

import java.util.Comparator;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 24/11/2009
 * Hora: 01:21:48 PM
 */

public class OrdenadorUsuarios implements Comparator<Usuario> {

	public static int ID_USUARIO = 1;
	public static int USUARIO = 2;
	public static int TIPO_USUARIO = 3;

	int tipoOrdenacion;

	public OrdenadorUsuarios(){
		tipoOrdenacion = OrdenadorUsuarios.USUARIO;
	}

	public OrdenadorUsuarios(int _ordenarPor){
		tipoOrdenacion = _ordenarPor;
	}

	public void setOrdenarPor(int _ordenarPor){
		tipoOrdenacion = _ordenarPor;
	}

	public int getOrdenarPor(){
		return tipoOrdenacion;
	}

	//return -1 p1 menor a p2
	//return 0  son iguales
	//return 1  p2 menor a p1
	public int compare(Usuario p1, Usuario p2){
		if(this.getOrdenarPor() == OrdenadorUsuarios.USUARIO){
			return p1.getUsername().compareTo(p2.getUsername());
		}
		if(this.getOrdenarPor() == OrdenadorUsuarios.ID_USUARIO){
			return ((Integer)p1.getUsuarioId()).compareTo(p2.getUsuarioId());
		}
		return 0;
	}
	//return -1 p1 menor a p2
	//return 0  son iguales
	//return 1  p2 menor a p1
	public int compare(Administrador p1, Administrador p2){
		if(this.getOrdenarPor() == OrdenadorUsuarios.TIPO_USUARIO){
			return p1.getTipoUsuario().compareTo(p2.getTipoUsuario());
		}
		return 0;
	}
}