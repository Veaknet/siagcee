package com.siagcee.logic;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 17/01/2010
 * Hora: 12:49:01 AM
 */

public class CursoEjecucionNodo{
	//inicialmente es secuencial...
	CursoEjecucionNodo hijos = null;
	CursoEjecucionNodo hermanos = null;
	Nodo arbolOperaciones = null;
	int tipoNodo = TIPO_NO_ESTABLECIDO;

	public static int TIPO_NO_ESTABLECIDO = 0;
	public static int TIPO_CONDICIONAL = 1;
	public static int TIPO_ARITMETICO = 2;

	public CursoEjecucionNodo(){
		hijos = null;
		hermanos = null;
		arbolOperaciones = null;
	}

	public CursoEjecucionNodo(Nodo _arbolOperaciones, int _tipo) {
		this.arbolOperaciones = _arbolOperaciones;
		this.tipoNodo = _tipo;
		hijos = null;
		hermanos = null;
	}

	public CursoEjecucionNodo getHijo() {
		return hijos;
	}

	public void addHijo(CursoEjecucionNodo _hijo) {
		if(this.hijos != null){
			this.hijos.addHermano(_hijo);
		}else{
			this.hijos = _hijo;
		}
	}

	public CursoEjecucionNodo getHermano() {
		return hermanos;
	}

	public void addHermano(CursoEjecucionNodo _hermano) {
		if(this.hermanos != null){
			this.hermanos.addHermano(_hermano);
		}else{
			this.hermanos = _hermano;
		}
	}

	public Nodo getArbolOperaciones() {
		return arbolOperaciones;
	}

	public void setArbolOperaciones(Nodo _arbolOperaciones) {
		this.arbolOperaciones = _arbolOperaciones;
	}

	public int getTipoNodo() {
		return tipoNodo;
	}

	public void setTipoNodo(int _tipoNodo) {
		this.tipoNodo = _tipoNodo;
	}

	public void add(CursoEjecucionNodo _miNodo, int nivel){
		if(this.getHermano() != null){

		}
		if(nivel > 0){
			if(this.getHermano() != null){
				this.getHermano().add(_miNodo, nivel);
			}else{
				if(this.getHijo() != null){
					this.getHijo().add(_miNodo, nivel - 1);
				}else{
					this.addHijo(_miNodo);
				}
			}
		}else{
			this.addHermano(_miNodo);
		}
	}
}
