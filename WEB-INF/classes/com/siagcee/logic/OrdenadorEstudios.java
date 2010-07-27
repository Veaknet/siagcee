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

public class OrdenadorEstudios implements Comparator<Estudio> {

	public static int TITULO = 1;
	public static int ID_ESTUDIO = 2;

	int tipoOrdenacion;

	public OrdenadorEstudios(){
		tipoOrdenacion = OrdenadorEstudios.TITULO;
	}

	public OrdenadorEstudios(int _ordenarPor){
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
	public int compare(Estudio p1, Estudio p2){
		if(this.getOrdenarPor() == OrdenadorEstudios.TITULO){
			return p1.getTitulo().compareTo(p2.getTitulo());
		}
		if(this.getOrdenarPor() == OrdenadorEstudios.ID_ESTUDIO){
			return ((Integer)p1.getId()).compareTo((Integer)p2.getId());
		}
		return 0;
	}
}