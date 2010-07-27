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
public class Nodo{
	String contenido;
	int tipoContenido;
	Nodo hijoDerecho;
	Nodo hijoIzquierdo;
	Nodo padre;

	public static int OPERADOR_ARITMETICO_UNARIO = 1;
	public static int OPERADOR_ARITMETICO_BINARIO = 2;
	public static int OPERADOR_MIXTO = 3;
	public static int OPERADOR_LOGICO = 4;

	public static int OPERANDO_PREGUNTA = 10;
	public static int OPERANDO_RESPUESTA_CERRADA = 20;
	public static int OPERANDO_CONSTANTE = 30;
	public static int OPERANDO_VALOR_SOLICITADO = 40;

	public Nodo() {
		this.contenido = "";
		this.hijoDerecho = null;
		this.hijoIzquierdo = null;
		this.padre = null;
	}

	public Nodo(String contenido, Nodo hijoDerecho, Nodo hijoIzquierdo, Nodo padre) {
		this.contenido = contenido;
		this.hijoDerecho = hijoDerecho;
		this.hijoIzquierdo = hijoIzquierdo;
		this.padre = padre;
	}

	public int getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(int _tipoContenido) {
		this.tipoContenido = _tipoContenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String _contenido) {
		this.contenido = _contenido;
	}

	public Nodo getHijoDerecho() {
		return hijoDerecho;
	}

	public void setHijoDerecho(Nodo _hijoDerecho) {
		this.hijoDerecho = _hijoDerecho;
		if(_hijoDerecho != null){
			this.getHijoDerecho().setPadre(this);
		}
	}

	public Nodo getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	public void setHijoIzquierdo(Nodo _hijoIzquierdo) {
		this.hijoIzquierdo = _hijoIzquierdo;
		if(_hijoIzquierdo != null){
			this.getHijoIzquierdo().setPadre(this);
		}
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo _padre) {
		this.padre = _padre;
	}
}
