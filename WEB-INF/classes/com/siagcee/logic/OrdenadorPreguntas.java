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


import java.util.Comparator;

public class OrdenadorPreguntas implements Comparator<Pregunta>{

	public static int PREGUNTA = 1;
	public static int TIPO_PREGUNTA = 2;
	public static int ID_PREGUNTA = 3;

	int tipoOrdenacion;

	public OrdenadorPreguntas(){
		tipoOrdenacion = OrdenadorPreguntas.PREGUNTA;
	}

	public OrdenadorPreguntas(int _ordenarPor){
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
	public int compare(Pregunta p1, Pregunta p2){
		if(this.getOrdenarPor() == OrdenadorPreguntas.PREGUNTA){
			return p1.getPregunta().toLowerCase().compareTo(p2.getPregunta().toLowerCase());
		}
		if(this.getOrdenarPor() == OrdenadorPreguntas.TIPO_PREGUNTA){
			return ((Integer)p1.getTipoPregunta()).compareTo((Integer)p2.getTipoPregunta());
		}
		if(this.getOrdenarPor() == OrdenadorPreguntas.ID_PREGUNTA){
			return ((Integer)p1.getId()).compareTo((Integer)p2.getId());
		}
		return 0;
	}
}
