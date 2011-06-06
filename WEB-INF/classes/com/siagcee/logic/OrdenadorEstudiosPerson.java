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

public class OrdenadorEstudiosPerson implements Comparator<EstudioPerso> {

	public static int TITULO = 1;
	public static int ID_ESTUDIO = 2;

	int tipoOrdenacion;

	public OrdenadorEstudiosPerson(){
		tipoOrdenacion = OrdenadorEstudiosPerson.TITULO;
	}

	public OrdenadorEstudiosPerson(int _ordenarPor){
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
	public int compare(EstudioPerso p1, EstudioPerso p2){
		if(this.getOrdenarPor() == OrdenadorEstudiosPerson.TITULO){
			return p1.get_titulo().compareTo(p2.get_titulo());
		}
		if(this.getOrdenarPor() == OrdenadorEstudiosPerson.ID_ESTUDIO){
			return ((Integer)p1.get_id()).compareTo((Integer)p2.get_id());
		}
		return 0;
	}
}