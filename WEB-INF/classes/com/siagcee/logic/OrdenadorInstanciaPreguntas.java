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

public class OrdenadorInstanciaPreguntas implements Comparator<InstanciaPregunta>{

	public static int ORDEN_PREGUNTA = 1;
	public static int PREGUNTA = 2;
	public static int ID_INSTANCIA_PREGUNTA = 3;
	public static int TIPO_PREGUNTA = 4;
	public static int ACRONIMO = 5;

	int tipoOrdenacion;

	public OrdenadorInstanciaPreguntas(){
		tipoOrdenacion = OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA;
	}

	public OrdenadorInstanciaPreguntas(int _ordenarPor){
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
	public int compare(InstanciaPregunta p1, InstanciaPregunta p2){
		if(this.getOrdenarPor() == OrdenadorInstanciaPreguntas.PREGUNTA){
			return p1.getTextoPregunta().toLowerCase().compareTo(p2.getTextoPregunta().toLowerCase());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaPreguntas.ACRONIMO){
			return p1.getAcronimo().toLowerCase().compareTo(p2.getAcronimo().toLowerCase());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaPreguntas.ORDEN_PREGUNTA){
			return ((Integer)p1.getOrden()).compareTo((Integer)p2.getOrden());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaPreguntas.ID_INSTANCIA_PREGUNTA){
			return ((Integer)p1.getId()).compareTo((Integer)p2.getId());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaPreguntas.TIPO_PREGUNTA){
			return ((Integer)p1.getTipoPregunta()).compareTo((Integer)p2.getTipoPregunta());
		}
		return 0;
	}

	
}
