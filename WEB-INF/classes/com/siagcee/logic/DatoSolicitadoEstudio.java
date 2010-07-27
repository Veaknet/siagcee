package com.siagcee.logic;

/**
* Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 17/01/2010
 * Hora: 10:27:44 AM
 */
public class DatoSolicitadoEstudio {
	Pregunta tipoDato;
	String acronimo;
	String titulo;
	String respuesta;

	public DatoSolicitadoEstudio() {
		this.tipoDato = null;
		this.acronimo = "";
		this.titulo = "";
	}

	public DatoSolicitadoEstudio(Pregunta _tipoDato, String _acronimo, String _titulo) {
		this.tipoDato = _tipoDato;
		this.acronimo = _acronimo;
		this.titulo = _titulo;
	}

	public Pregunta getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(Pregunta _tipoDato) {
		this.tipoDato = _tipoDato;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String _acronimo) {
		this.acronimo = _acronimo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String _titulo) {
		this.titulo = _titulo;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String _respuesta) {
		this.respuesta = _respuesta;
	}
}
