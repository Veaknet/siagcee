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

public class OrdenadorResultadosEstudio implements Comparator<Estudio.NodoResultadoCalculo>{

	public static int COINCIDENCIAS_PREGUNTA_ASCENDENTE = 1;
	public static int COINCIDENCIAS_PREGUNTA_DESCENDENTE = 2;
	public static int RESPUESTA = 1;

	int tipoOrdenacion;

	public OrdenadorResultadosEstudio(){
		tipoOrdenacion = OrdenadorResultadosEstudio.COINCIDENCIAS_PREGUNTA_DESCENDENTE;
	}

	public OrdenadorResultadosEstudio(int _ordenarPor){
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
	public int compare(Estudio.NodoResultadoCalculo p1, Estudio.NodoResultadoCalculo p2){
		if(this.getOrdenarPor() == OrdenadorResultadosEstudio.COINCIDENCIAS_PREGUNTA_ASCENDENTE){
			return ((Integer)p1.cantidadCoincidencias).compareTo((Integer)p2.cantidadCoincidencias);
		}
		if(this.getOrdenarPor() == OrdenadorResultadosEstudio.COINCIDENCIAS_PREGUNTA_DESCENDENTE){
			return ((Integer)p2.cantidadCoincidencias).compareTo((Integer)p1.cantidadCoincidencias);
		}
		if(this.getOrdenarPor() == OrdenadorResultadosEstudio.RESPUESTA){
			return p1._valor.toString().compareTo(p2._valor.toString());
		}
		return 0;
	}
}
