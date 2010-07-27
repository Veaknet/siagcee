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

public class OrdenadorInstanciaObjetos implements Comparator<InstanciaObjeto> {

	public static int OBJETO = 1;
	public static int ID_INSTANCIA_OBJETO = 2;
	public static int CLASS = 3;
	public static int FECHA_INICIO = 4;
	public static int FECHA_INICIO_INV = 5;
	public static int FECHA_CIERRE = 6;
	public static int FECHA_CIERRE_INV = 7;

	int tipoOrdenacion;

	public OrdenadorInstanciaObjetos(){
		tipoOrdenacion = OrdenadorInstanciaObjetos.CLASS;
	}

	public OrdenadorInstanciaObjetos(int _ordenarPor){
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
	public int compare(InstanciaObjeto p1, InstanciaObjeto p2){
		if(this.getOrdenarPor() == OrdenadorInstanciaObjetos.OBJETO){
			return p1.getObjeto().toLowerCase().compareTo(p2.getObjeto().toLowerCase());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaObjetos.CLASS){
			return p1.getObjetoAsociado().getClass().toString().compareTo(p2.getObjetoAsociado().getClass().toString());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaObjetos.ID_INSTANCIA_OBJETO){
			return ((Integer)p1.getId()).compareTo((Integer)p2.getId());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaObjetos.FECHA_INICIO){
			return (p1.getFechaInicio()).compareTo(p2.getFechaInicio());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaObjetos.FECHA_CIERRE){
			return (p1.getFechaCierre()).compareTo(p2.getFechaCierre());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaObjetos.FECHA_INICIO_INV){
			return (p2.getFechaInicio()).compareTo(p1.getFechaInicio());
		}
		if(this.getOrdenarPor() == OrdenadorInstanciaObjetos.FECHA_CIERRE_INV){
			return (p2.getFechaCierre()).compareTo(p1.getFechaCierre());
		}
		return 0;
	}
}