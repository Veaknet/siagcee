package com.siagcee.logic;

import java.util.Comparator;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 06/12/2009
 * Hora: 11:08:58 PM
 */
public class OrdenadorObjetos implements Comparator<Objeto> {

	public static int OBJETO = 1;
	public static int ID_OBJETO = 2;
	public static int CLASS = 3;

	int tipoOrdenacion;
	
	public OrdenadorObjetos(){
		tipoOrdenacion = OrdenadorObjetos.CLASS;
	}

	public OrdenadorObjetos(int _ordenarPor){
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
	public int compare(Objeto p1, Objeto p2){
		if(this.getOrdenarPor() == OrdenadorObjetos.OBJETO){
			return p1.getObjeto().toLowerCase().compareTo(p2.getObjeto().toLowerCase());
		}
		if(this.getOrdenarPor() == OrdenadorObjetos.CLASS){
			return ((p1.getClass()).toString()).compareTo(p2.getClass().toString());
		}
		if(this.getOrdenarPor() == OrdenadorObjetos.ID_OBJETO){
			return ((Integer)p1.getId()).compareTo((Integer)p2.getId());
		}
		return 0;
	}


}
