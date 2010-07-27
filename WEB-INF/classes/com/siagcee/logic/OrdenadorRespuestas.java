package com.siagcee.logic;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 16/12/2009
 * Hora: 09:18:53 AM
 */

import java.util.Comparator;

public class OrdenadorRespuestas implements Comparator<Respuesta> {

	public static int ORDEN_PREGUNTA = 1;
	public static int TIPO_PREGUNTA = 2;
	public static int FECHA_CREACION = 3;

	int tipoOrdenacion;

	public OrdenadorRespuestas(){
		tipoOrdenacion = OrdenadorRespuestas.ORDEN_PREGUNTA;
	}

	public OrdenadorRespuestas(int _ordenarPor){
		tipoOrdenacion = _ordenarPor;
	}

	public void setOrdenarPor(int _ordenarPor){
		tipoOrdenacion = _ordenarPor;
	}

	public int getOrdenarPor(){
		return tipoOrdenacion;
	}

	//return -1 r1 menor a r2
	//return 0  son iguales
	//return 1  r2 menor a r1
	public int compare(Respuesta r1, Respuesta r2){
		if(this.getOrdenarPor() == OrdenadorRespuestas.ORDEN_PREGUNTA){
			return ((Integer)r1.getInstanciaPregunta().getOrden()).compareTo((Integer)r2.getInstanciaPregunta().getOrden());
		}
		if(this.getOrdenarPor() == OrdenadorRespuestas.TIPO_PREGUNTA){
			return ((Integer)r1.getInstanciaPregunta().getTipoPregunta()).compareTo((Integer)r2.getInstanciaPregunta().getTipoPregunta());
		}
		if(this.getOrdenarPor() == OrdenadorRespuestas.FECHA_CREACION){
			return ((Integer)r1.getInstanciaPregunta().getId()).compareTo((Integer)r2.getInstanciaPregunta().getId());
		}
		return 0;
	}
}
