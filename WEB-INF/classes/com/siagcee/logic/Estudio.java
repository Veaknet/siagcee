package com.siagcee.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 15/01/2010
 * Hora: 03:08:51 PM
 */
public class Estudio extends ObjetoBase{
	String titulo;
	String descripcion;
	int id;
	String codigo;
	boolean codigoValidado;
	Nodo arbol;
	Vector listadoDatosSolicitados;
	Vector respuestasListadoDatosSolicitados;
	Objeto idObjeto;
	Vector Error;
	Vector resultadosCalculos;
	Vector resultadosCalculosSinCoincidencias;
	public class NodoResultadoCalculo{
		public Object _valor = null;
		public int cantidadCoincidencias = 0;
		public NodoResultadoCalculo(){
			_valor = null;
			cantidadCoincidencias = 0;
		}
	}
	public class NodoResultadoCalculoSinCoincidencias{
		public Object _valor = null;
		public int id_usuario = -1;
		public NodoResultadoCalculoSinCoincidencias(){
			_valor = null;
			id_usuario = -1;
		}
	}

	//*******variables globales para la correcta verificacion del codigo... no son accesibles desde afuera, por lo que no existen getter ni setter
	private static int ACCION_COMPILACION = 0;
	private static int ACCION_EJECUCION = 1;
	private static int ACCION_DATOS_SOLICITADOS = 2;

	private static int TIPO_NADA = 0;
	private static int TIPO_OPERANDO = 1;
	private static int TIPO_OPERADOR_BINARIO = 10;
	private static int TIPO_OPERADOR_UNARIO = 11;
	private static int TIPO_CIERRE_OPERADOR_UNARIO = 12;
	private static int TIPO_OPERADOR_MIXTO = 13;  //funciona como binario y unario dependiendo del caso

	String[] _lineas = null;
	int _lineaActual = 0;
	boolean _declarando = true;
	int accion = ACCION_COMPILACION;
	String _lineaARevisar = "";
	int ultimoTipoAceptado = TIPO_NADA;
	int nivel = 0;
	//*******variables globales para la correcta verificacion del codigo... no son accesibles desde afuera, por lo que no existen getter ni setter

	//*******variables globales para la correcta ejecucion del codigo... no son accesibles desde afuera, por lo que no existen getter ni setter
	CursoEjecucionNodo arbolDeEjecucionDeEstudio = null;
	//*******variables globales para la correcta ejecucion del codigo... no son accesibles desde afuera, por lo que no existen getter ni setter



	public Estudio() {
		super();
		id = -1;
		titulo = "";
		descripcion = "";
		codigo = "";
		codigoValidado = false;
		arbol = null;
		idObjeto = null;
		Error = new Vector();
		listadoDatosSolicitados = new Vector();
	}

	public Estudio(Usuario _usuario, Connection _miConexion) {
		super(_usuario, _miConexion);
		id = -1;
		codigo = "";
		titulo = "";
		descripcion = "";
		codigoValidado = false;
		arbol = null;
		idObjeto = null;
		Error = new Vector();
		listadoDatosSolicitados = new Vector();
	}

	public Estudio(Usuario _usuario, Connection _miConexion, int _id) {
		super(_usuario, _miConexion);
		id = _id;
		codigo = "";
		titulo = "";
		descripcion = "";
		codigoValidado = false;
		arbol = null;
		idObjeto = null;
		Error = new Vector();
		listadoDatosSolicitados = new Vector();
		this.cargarDeBD();
	}

	public Estudio(Usuario _usuario, Connection _miConexion, int _id, boolean _validar) {
		super(_usuario, _miConexion);
		id = _id;
		codigo = "";
		titulo = "";
		descripcion = "";
		codigoValidado = false;
		arbol = null;
		idObjeto = null;
		Error = new Vector();
		listadoDatosSolicitados = new Vector();
		this.cargarDeBD();
	}

	public Vector getResultadosEstudio(){
		return this.resultadosCalculos;
	}

	public Vector getResultadosEstudioSinCoincidencias(){
		return this.resultadosCalculosSinCoincidencias;
	}

	public Vector getListadoDatosSolicitados(){
		return new Vector(listadoDatosSolicitados);
	}

	public void setRespuestasListadoDatosSolicitados(Vector _listadoRespuestas){
		this.respuestasListadoDatosSolicitados = _listadoRespuestas;
	}

	private void addListadoDatosSolicitados(DatoSolicitadoEstudio listadoDatosSolicitados) {
		this.listadoDatosSolicitados.add(listadoDatosSolicitados);
	}

	private void resetListadoDatosSolicitados() {
		this.listadoDatosSolicitados = new Vector();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
		this.guardarEnBD();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		this.guardarEnBD();
	}

	public Vector getError() {
		return Error;
	}

	protected void addError(String error) {
		Error.add(error);
	}

	public void resetErrorList(){
		Error = new Vector();
	}

	public Objeto getObjeto() {
		return idObjeto;
	}

	public void setObjeto(Objeto idObjeto) {
		this.idObjeto = idObjeto;
		this.guardarEnBD();
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String _codigo) {
		this.codigo = _codigo;
		this.accion = Estudio.ACCION_COMPILACION;
		if(this.validarCodigo()){
			//solo guardo si es válido
			this.guardarEnBD();
		}
	}

	public boolean isCodigoValidado() {
		return codigoValidado;
	}

	protected void setCodigoValidado(boolean codigoValidado) {
		this.codigoValidado = codigoValidado;
	}

	private boolean esTipoDeDato(String _tipoDeDato){
		String[] _ttt = _tipoDeDato.split("-");
		try{
			Enumeration _enu = this.getObjeto().getPreguntas().elements();
			InstanciaPregunta _preg;
			while(_enu.hasMoreElements()){
				_preg = (InstanciaPregunta)_enu.nextElement();
				if(_preg.getPreguntaAsociada().getId() == Integer.parseInt(_ttt[0])){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private void siguienteLinea(){
		_lineaActual++;
		while(_lineaActual < _lineas.length){
			if(!(_lineas[_lineaActual]).trim().equals("")){
				break;
			}
			_lineaActual++;
		}
	}

	private void lineaPrevia(){
		_lineaActual--;
		while(_lineaActual < _lineas.length){
			if(!(_lineas[_lineaActual]).trim().equals("")){
				break;
			}
			_lineaActual--;
		}
	}

	//retorna la cantidad de ocurrencias de ocurrencia en fraseAExplorar 
	private int contadorOcurrencias(String ocurrencia, String fraseAExplorar){
		String _temporal = fraseAExplorar;
		int contador = 0;
		while (_temporal.indexOf(ocurrencia) > -1) {
      _temporal = _temporal.substring(_temporal.indexOf(ocurrencia)+ocurrencia.length(),_temporal.length());
      contador++;
		}
		return contador;
	}


	//retorna el tipo que viene acontinuacion en un String
	private int queTipoSigue(String _cadena){
		_cadena = _cadena.trim();
		if(_cadena.indexOf("(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("NEGACION DE:(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("CONTAR TOTALES DE:(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("CONTAR COINCIDENCIAS DE:(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("SUMAR VALORES DE:(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("SENO DE:(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("COSENO DE:(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("TANGENTE DE:(") == 0){
			return TIPO_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf(")") == 0){
			return TIPO_CIERRE_OPERADOR_UNARIO;
		}
		if(_cadena.indexOf("+") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("-") == 0){
			return TIPO_OPERADOR_MIXTO;
		}
		if(_cadena.indexOf("*") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("/") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("PARECIDO A:") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("ES IGUAL A:") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("ES DISTINTO A:") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("Y") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("O") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("<=") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("<") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf(">=") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf(">") == 0){
			return TIPO_OPERADOR_BINARIO;
		}
		if(_cadena.indexOf("RESPUESTA CERRADA:") == 0){
			return TIPO_OPERANDO;
		}
		if(_cadena.indexOf("VALOR:") == 0){
			return TIPO_OPERANDO;
		}
		if(_cadena.indexOf("RESPUESTA DADA A PREGUNTA:") == 0){
			return TIPO_OPERANDO;
		}
		if(_cadena.indexOf("^") == 0){
			return TIPO_OPERANDO;
		}
		return TIPO_NADA;
	}

	//comprueba si el tipo _anterior es correctamente el predecesor de _actual (sintaxis)
	//por ejemplo... no es válido 2 ++ 3 ni  2 (* 2)
	private boolean validoOrdenMatematico(int _previo, int _actual){
		if(_actual == TIPO_OPERADOR_UNARIO){
			if(_previo == TIPO_OPERADOR_MIXTO){
				return true;
			}
			if(_previo == TIPO_OPERADOR_BINARIO){
				return true;
			}
			if(_previo == TIPO_NADA){
				return true;
			}
			if(_previo == TIPO_OPERADOR_UNARIO){
				return true;
			}
		}
		if(_actual == TIPO_OPERADOR_BINARIO){
			if(_previo == TIPO_OPERANDO){
				return true;
			}
			if(_previo == TIPO_CIERRE_OPERADOR_UNARIO){
				return true;
			}
		}
		if(_actual == TIPO_OPERANDO){
			if(_previo == TIPO_NADA){
				return true;
			}
			if(_previo == TIPO_OPERADOR_MIXTO){
				return true;
			}
			if(_previo == TIPO_OPERADOR_BINARIO){
				return true;
			}
			if(_previo == TIPO_OPERADOR_UNARIO){
				return true;
			}
		}
		if(_actual == TIPO_OPERADOR_MIXTO){
			if(_previo == TIPO_NADA){
				return true;
			}
			if(_previo == TIPO_OPERADOR_UNARIO){
				return true;
			}
			if(_previo == TIPO_OPERANDO){
				return true;
			}
			if(_previo == TIPO_CIERRE_OPERADOR_UNARIO){
				return true;
			}
		}
		if(_actual == TIPO_CIERRE_OPERADOR_UNARIO){
			if(_previo == TIPO_OPERANDO){
				return true;
			}
		}
		return false;
	}

	//trabaja directamente con _lineaARevisar
	private String obtenStringEntreSeparadores(char _separador){
		String letras = "";
		boolean comenzar = true;

		_lineaARevisar = _lineaARevisar.trim();

		try{
			while(true){
				if(_lineaARevisar.indexOf(_separador) == 0){
					if(comenzar){
						comenzar = false;
					}else{
						_lineaARevisar = _lineaARevisar.substring(1);
						break;
					}
				}else{
					if(!comenzar){
						if((_lineaARevisar.charAt(0) == '\\') && (_lineaARevisar.charAt(1) == _separador)){
							//si consigo \separador  entonces es escape para ingresar separador
							letras = letras + _separador;
							_lineaARevisar = _lineaARevisar.substring(1);
						}else{
							letras = letras+_lineaARevisar.charAt(0);
						}
					}
				}
				_lineaARevisar = _lineaARevisar.substring(1);
			}
			return letras;
		}catch(Exception e){
			e.printStackTrace();
			this.addError("Error leyendo la operaci&oacute;n. Formato incorrecto. Probablemente no se consigui&oacute; con ^");
			this.setCodigoValidado(false);
			return "";
		}
	}
	
	//creo el arbol de operaciones para la linea actual
	//se borrara el arbol cuando se vuelva a llamar a este método
	private Nodo creaNodos(){
		//elimino cualquier espacio en blanco en los extremos
		try{
			_lineaARevisar = _lineaARevisar.trim();

			if(_lineaARevisar.indexOf(")") == 0){
				_lineaARevisar = _lineaARevisar.substring(1).trim();
				if(!validoOrdenMatematico(ultimoTipoAceptado, TIPO_CIERRE_OPERADOR_UNARIO)){
					this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
					this.setCodigoValidado(false);
					return null;
				}
			}else	if(queTipoSigue(_lineaARevisar) == TIPO_OPERADOR_UNARIO){
				if(_lineaARevisar.indexOf("(") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _actual = creaNodos();
						Nodo _padre = creaNodos();
						if(_padre != null){
							_padre.setHijoIzquierdo(_actual);
							return _padre;
						}else{
							return _actual;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("NEGACION DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(13).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("NEGACION");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("CONTAR TOTALES DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(19).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_UNARIO);
						_yo.setContenido("TOTALES");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("CONTAR COINCIDENCIAS DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(25).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_UNARIO);
						_yo.setContenido("COINCIDENCIAS");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("SUMAR VALORES DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(18).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_UNARIO);
						_yo.setContenido("SUMAR");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("PROMEDIAR VALORES DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(22).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_UNARIO);
						_yo.setContenido("PROMEDIAR");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("SENO DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(9).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_UNARIO);
						_yo.setContenido("SENO");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("COSENO DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(11).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_UNARIO);
						_yo.setContenido("COSENO");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("TANGENTE DE:(") == 0){
					_lineaARevisar = _lineaARevisar.substring(13).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_UNARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_UNARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_UNARIO);
						_yo.setContenido("TANGENTE");
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
			}else if(queTipoSigue(_lineaARevisar) == TIPO_OPERADOR_BINARIO){
				if(_lineaARevisar.indexOf("+") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_BINARIO);
						_yo.setContenido("+");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("*") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_BINARIO);
						_yo.setContenido("*");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("/") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_ARITMETICO_BINARIO);
						_yo.setContenido("/");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("<") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("<");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("<=") == 0){
					_lineaARevisar = _lineaARevisar.substring(2).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("<=");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf(">") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido(">");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf(">=") == 0){
					_lineaARevisar = _lineaARevisar.substring(2).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido(">=");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("Y") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("Y");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("O") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("O");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("PARECIDO A:") == 0){
					_lineaARevisar = _lineaARevisar.substring(11).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("PARECIDO");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("ES IGUAL A:") == 0){
					_lineaARevisar = _lineaARevisar.substring(11).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("IGUAL");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("ES DISTINTO A:") == 0){
					_lineaARevisar = _lineaARevisar.substring(14).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_BINARIO)){
						ultimoTipoAceptado = TIPO_OPERADOR_BINARIO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_LOGICO);
						_yo.setContenido("DISTINTO");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
			}else	if(queTipoSigue(_lineaARevisar) == TIPO_OPERADOR_MIXTO){
				if(_lineaARevisar.indexOf("-") == 0){
					_lineaARevisar = _lineaARevisar.substring(1).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERADOR_MIXTO)){
						ultimoTipoAceptado = TIPO_OPERADOR_MIXTO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						Nodo _hijoDere = creaNodos();
						if(_hijoDere != null){
							_yo.setHijoDerecho(_hijoDere);
						}else{
							this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
							this.setCodigoValidado(false);
							return null;
						}
						_yo.setTipoContenido(Nodo.OPERADOR_MIXTO);
						_yo.setContenido("-");
						return _yo;
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
			}else	if(queTipoSigue(_lineaARevisar) == TIPO_OPERANDO){
				if(_lineaARevisar.indexOf("RESPUESTA CERRADA:") == 0){
					_lineaARevisar = _lineaARevisar.substring(18).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERANDO)){
						ultimoTipoAceptado = TIPO_OPERANDO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						String valor = this.obtenStringEntreSeparadores('^');
						String[] variables = valor.split("-");
						_yo.setTipoContenido(Nodo.OPERANDO_RESPUESTA_CERRADA);
						_yo.setContenido(variables[0]);
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("VALOR:") == 0){
					_lineaARevisar = _lineaARevisar.substring(6).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERANDO)){
						ultimoTipoAceptado = TIPO_OPERANDO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						String valor = this.obtenStringEntreSeparadores('^');
						_yo.setTipoContenido(Nodo.OPERANDO_VALOR_SOLICITADO);
						_yo.setContenido(valor);
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("RESPUESTA DADA A PREGUNTA:") == 0){
					_lineaARevisar = _lineaARevisar.substring(27).trim();
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERANDO)){
						ultimoTipoAceptado = TIPO_OPERANDO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						String valor = this.obtenStringEntreSeparadores('^');
						String[] variables = valor.split("-");
						_yo.setTipoContenido(Nodo.OPERANDO_PREGUNTA);
						_yo.setContenido(variables[0]);
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
				if(_lineaARevisar.indexOf("^") == 0){
					if(validoOrdenMatematico(ultimoTipoAceptado, TIPO_OPERANDO)){
						ultimoTipoAceptado = TIPO_OPERANDO;
						Nodo _yo = new Nodo();
						_yo.setHijoIzquierdo(null);
						_yo.setHijoDerecho(null);
						String valor = this.obtenStringEntreSeparadores('^');
						_yo.setTipoContenido(Nodo.OPERANDO_CONSTANTE);
						_yo.setContenido(valor);
						Nodo seraPadre = creaNodos();
						if(seraPadre != null){
							seraPadre.setHijoIzquierdo(_yo);
							return seraPadre;
						}else{
							return _yo;
						}
					}else{
						this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
						this.setCodigoValidado(false);
						return null;
					}
				}
			}else{
				//no reconocido
				this.addError("Error de sintaxis antes de: "+_lineaARevisar.substring(0, 15));
				this.setCodigoValidado(false);
				return null;
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}

	//_tipo = 0 se espera que el resultado sea lógico o aritmético
	//_tipo = 1 se espera que el resultado sea lógico (condicional)
	//_tipo = 2 se espera que el resultado sea numérico (aritmetica)
	private void validarOperacion(int _tipo){
		_lineaARevisar = "";
		ultimoTipoAceptado = TIPO_NADA;
		this.arbol = null;

		while(_lineaActual < _lineas.length){
			if((_lineas[_lineaActual]).trim().equals("REALIZAR:")){
				break;
			}
			if((_lineas[_lineaActual]).trim().equals("FIN DEL CALCULO.-")){
				break;
			}
			_lineaARevisar = _lineaARevisar+(_lineas[_lineaActual]).trim();
			siguienteLinea();
		}
		int _abierto = contadorOcurrencias("(", _lineaARevisar);
		int _cerrado = contadorOcurrencias(")", _lineaARevisar);
		if(_abierto > _cerrado){
			this.addError("Se espera ')' antes de o en l&iacute;nea:"+_lineaActual);
			this.setCodigoValidado(false);
			return;
		}
		if(_abierto < _cerrado){
			this.addError("Se espera '(' antes de o en l&iacute;nea:"+_lineaActual);
			this.setCodigoValidado(false);
			return;
		}
		this.setArbol(creaNodos());
		if(recorreYValidaOperacion(this.getArbol())){
			if((this.getArbol().getTipoContenido() == Nodo.OPERADOR_LOGICO) && (_tipo == 0 || _tipo == 1)){
				//bueno
			}else if((((this.getArbol()).getTipoContenido() == Nodo.OPERADOR_MIXTO)
					|| ((this.getArbol()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_BINARIO)
					|| ((this.getArbol()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_UNARIO)
					|| ((this.getArbol()).getTipoContenido() == Nodo.OPERANDO_CONSTANTE)
					|| ((this.getArbol()).getTipoContenido() == Nodo.OPERANDO_PREGUNTA)
					|| ((this.getArbol()).getTipoContenido() == Nodo.OPERANDO_RESPUESTA_CERRADA)
					|| ((this.getArbol()).getTipoContenido() == Nodo.OPERANDO_VALOR_SOLICITADO)) && (_tipo == 0 || _tipo == 2)){
				//bueno
			}else{
				this.addError("Error en c&aacute;lculo. Tipo de dato resultante no compatible. Antes de l&iacute;nea: "+_lineaActual);
				this.setCodigoValidado(false);
			}
		}else{
			this.setCodigoValidado(false);
			this.addError("La operaci&oacute;n indicada no presenta una estructura correcta. Antes de l&iacute;nea: "+_lineaActual);
		}
	}

	private boolean recorreYValidaOperacion(Nodo _nodo){
		if(_nodo == null){
			return true;
		}
		if((_nodo.getTipoContenido() == Nodo.OPERANDO_CONSTANTE) || (_nodo.getTipoContenido() == Nodo.OPERANDO_PREGUNTA) || (_nodo.getTipoContenido() == Nodo.OPERANDO_RESPUESTA_CERRADA) || (_nodo.getTipoContenido() == Nodo.OPERANDO_VALOR_SOLICITADO)){
			return (_nodo.getHijoDerecho() == null && _nodo.getHijoIzquierdo() == null);
		}
		try{
			boolean _resultado = recorreYValidaOperacion(_nodo.getHijoIzquierdo());
			if(_resultado){
				//si la descendencia izquierda es correcta
				_resultado = recorreYValidaOperacion(_nodo.getHijoDerecho());
				if(_resultado){
					//si la descendencia derecha tambien es correcta
					//reviso el nodo actual y determino validez.
					if((_nodo.getTipoContenido() == Nodo.OPERADOR_ARITMETICO_BINARIO) || (_nodo.getTipoContenido() == Nodo.OPERADOR_ARITMETICO_UNARIO)){
						//como soy aritmetico los hijos tienen que ser obligatoriamente operandos u operadores aritmeticos
						if(((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_CONSTANTE)
								|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_PREGUNTA)
								|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_RESPUESTA_CERRADA)
								|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_VALOR_SOLICITADO)
								|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_UNARIO)
								|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_BINARIO)){
							if(((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_CONSTANTE)
									|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_PREGUNTA)
									|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_RESPUESTA_CERRADA)
									|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_VALOR_SOLICITADO)
									|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_UNARIO)
									|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_BINARIO)){
								return true;
							}
						}
					}else if(_nodo.getTipoContenido() == Nodo.OPERADOR_LOGICO){
						if(_nodo.getContenido().equals("NEGACION")){
							//soy un logico que solo permite calculos anidados con operadores logicos
							return ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERADOR_LOGICO);
						}
						if(_nodo.getContenido().equals("Y") || _nodo.getContenido().equals("O")){
							//soy un logico que solo permite calculos anidados con operadores logicos
							return (((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERADOR_LOGICO) && ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERADOR_LOGICO));
						}
						//cubre los casos IGUAL PARECIDO DISTINTO > >= < <=
						return true;
					}else if(_nodo.getTipoContenido() == Nodo.OPERADOR_MIXTO){
						if(((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_CONSTANTE)
								|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_PREGUNTA)
								|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_RESPUESTA_CERRADA)
								|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERANDO_VALOR_SOLICITADO)
								|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_UNARIO)
								|| ((_nodo.getHijoDerecho()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_BINARIO)){
							if(_nodo.getHijoIzquierdo() != null){
								if(((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_CONSTANTE)
										|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_PREGUNTA)
										|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_RESPUESTA_CERRADA)
										|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERANDO_VALOR_SOLICITADO)
										|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_UNARIO)
										|| ((_nodo.getHijoIzquierdo()).getTipoContenido() == Nodo.OPERADOR_ARITMETICO_BINARIO)){
									return true;
								}
							}else{
								return true;
							}
						}
					}else{
						//contenido no valido...
						return false;
					}
					return false;
				}
			}
			return false;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//proceso de revision de codigo para compilar o validar
	//dependiendo de this.accion
	private void validarSiguiente() throws Exception{
		if(_lineas.length <= _lineaActual){
			//no hay mas lineas
			return;
		}
		if((_lineas[_lineaActual]).trim().equals("PEDIR EN EJECUCION:")){
			siguienteLinea();
			if(_declarando){
				DatoSolicitadoEstudio _DatoPedido = new DatoSolicitadoEstudio();
				if((_lineas[_lineaActual]).trim().equals("DE TIPO DE DATO:")){
					siguienteLinea();
					if((esTipoDeDato((_lineas[_lineaActual]).trim())) && (!(_lineas[_lineaActual]).trim().equals(""))){
						String[] _ttt = (_lineas[_lineaActual]).trim().split("-");
						_DatoPedido.setTipoDato(new Pregunta(this.getUsuario(), this.getConexion(), Integer.parseInt(_ttt[0])));
						siguienteLinea();
						if((_lineas[_lineaActual]).trim().equals("USAR ACRONIMO:")){
							siguienteLinea();
							String[] miTemp = (_lineas[_lineaActual]).trim().split(" ");
							if((!(_lineas[_lineaActual]).trim().equals("")) && (miTemp.length == 1)){
								_DatoPedido.setAcronimo((_lineas[_lineaActual]).trim());
								siguienteLinea();
								if((_lineas[_lineaActual]).trim().equals("MOSTRAR TITULO:")){
									siguienteLinea();
									if(!(_lineas[_lineaActual]).trim().equals("")){
										_DatoPedido.setTitulo((_lineas[_lineaActual]).trim());
										siguienteLinea();
										if((_lineas[_lineaActual]).trim().equals("FIN.-")){
											siguienteLinea();
											this.addListadoDatosSolicitados(_DatoPedido);
											validarSiguiente();
										}else{
											this.addError("Sintaxis incorrecta, se esperaba 'FIN.-' en l&iacute;nea"+(_lineaActual + 1));
											this.setCodigoValidado(false);
										}
									}else{
										this.addError("Debe indicar el t&iacute;tulo en l&iacute;nea: "+(_lineaActual + 1));
										this.setCodigoValidado(false);
									}
								}else{
									this.addError("Sintaxis incorrecta, se esperaba 'MOSTRAR TITULO:' en l&iacute;nea"+(_lineaActual + 1));
									this.setCodigoValidado(false);
								}
							}else{
								this.addError("Debe indicar un acr&oacute;nimo sin espacios en blanco en l&iacute;nea: "+(_lineaActual + 1));
								this.setCodigoValidado(false);
							}
						}else{
							this.addError("Sintaxis incorrecta, se esperaba 'USAR ACRONIMO:' en l&iacute;nea: "+(_lineaActual + 1));
							this.setCodigoValidado(false);
						}
					}else{
						this.addError("No se indic&oacute; correctamente el tipo de dato a usar en l&iacute;nea"+(_lineaActual + 1));
						this.setCodigoValidado(false);
					}
				}else{
					this.addError("Sintaxis incorrecta, se esperaba 'DE TIPO DE DATO:' en l&iacute;nea"+(_lineaActual + 1));
					this.setCodigoValidado(false);
				}
			}else{
				this.addError("No puede pedir datos una vez comenzada la ejecuci&oacute;n del estudio.Error en l&iacute;nea: "+_lineaActual);
				this.setCodigoValidado(false);
			}
		}else	if((_lineas[_lineaActual]).trim().equals("CUANDO SE CUMPLA CON:")){
			if(this.accion == Estudio.ACCION_DATOS_SOLICITADOS){
				_lineaActual = _lineas.length;
				return;
			}
			siguienteLinea();
			_declarando = false;
			//espero operacion logica
			validarOperacion(1);
			if(!this.isCodigoValidado()){
				return;
			}
			CursoEjecucionNodo _miNodo = new CursoEjecucionNodo(this.arbol, CursoEjecucionNodo.TIPO_CONDICIONAL);
			if(this.arbolDeEjecucionDeEstudio == null){
				this.arbolDeEjecucionDeEstudio = _miNodo;
			}else{
				this.arbolDeEjecucionDeEstudio.add(_miNodo, nivel);
			}
			nivel++;
			if((_lineas[_lineaActual]).trim().equals("REALIZAR:")){
				siguienteLinea();
				validarSiguiente();
				if((_lineas[_lineaActual]).trim().equals("FIN..")){
					nivel--;
					siguienteLinea();
					return;
				}else{
					this.addError("Sintaxis incorrecta, se esperaba 'FIN..' en l&iacute;nea: "+(_lineaActual + 1));
					this.setCodigoValidado(false);
				}
			}else{
				this.addError("Sintaxis incorrecta, se esperaba 'REALIZAR:' en l&iacute;nea: "+(_lineaActual + 1));
				this.setCodigoValidado(false);
			}
		}else if((_lineas[_lineaActual]).trim().equals("REALIZAR CALCULO:")){
			if(this.accion == Estudio.ACCION_DATOS_SOLICITADOS){
				_lineaActual = _lineas.length;
				return;
			}
			_declarando = false;
			siguienteLinea();
			//espero operacion cualquiera
			validarOperacion(0);
			if(this.isCodigoValidado()){
				CursoEjecucionNodo _miNodo = new CursoEjecucionNodo(this.arbol, CursoEjecucionNodo.TIPO_NO_ESTABLECIDO);
				if(this.arbolDeEjecucionDeEstudio == null){
					this.arbolDeEjecucionDeEstudio = _miNodo;
				}else{
					this.arbolDeEjecucionDeEstudio.add(_miNodo, nivel);
				}
			}
			if(_lineaActual >= _lineas.length){
				this.addError("Sintaxis incorrecta, se esperaba: 'FIN DEL CALCULO.-' en l&iacute;nea: "+(_lineaActual + 1));
				this.setCodigoValidado(false);
			}
			if((_lineas[_lineaActual]).trim().equals("FIN DEL CALCULO.-")){
				siguienteLinea();
				return;
			}
			this.addError("Sintaxis incorrecta, se esperaba: 'FIN DEL CALCULO.-' en linea: "+(_lineaActual + 1));
			this.setCodigoValidado(false);
		}else	if(((_lineas[_lineaActual]).trim().substring(0, 7)).equals("!#----/")){
			siguienteLinea();
			validarSiguiente();
		}else	if((_lineas[_lineaActual]).trim().equals("FIN..")){
			nivel--;
			siguienteLinea();
			validarSiguiente();
		}
	}

	//se compila el codigo
	public boolean validarCodigo(){
		_lineas = null;
		_lineaActual = 0;
		_declarando = true;
		ultimoTipoAceptado = TIPO_NADA;
		nivel = 0;

		arbolDeEjecucionDeEstudio = null;

		try{
			this.setCodigoValidado(true);
			this.resetErrorList();
			this.resetListadoDatosSolicitados();
			//comienza validacion
			if(!this.getCodigo().trim().equals("")){
				//no es vacio
				_lineas = this.getCodigo().split("\n");
				while(_lineas.length > _lineaActual){
					validarSiguiente();
				}
			}else{
				this.setCodigoValidado(false);
				this.addError("No existe c&oacute;digo a validar.");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			this.setCodigoValidado(false);
			this.addError("Excepci&oacute;n al compilar 1.");
		}
		return this.isCodigoValidado();
	}

	public Object retornaValorOperacion(InstanciaObjeto _insObj, Nodo _miNodo, int _usuario){
		try{
			if(_miNodo.getHijoIzquierdo() == null && _miNodo.getHijoDerecho() == null){
				if(_miNodo.getTipoContenido() == Nodo.OPERANDO_CONSTANTE){
					try{
						SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
						return _temp.parse(_miNodo.getContenido());
					}catch(Exception eee){
						try{
							return Double.parseDouble(_miNodo.getContenido());
						}catch(Exception eeee){
							return _miNodo.getContenido();
						}
					}
				}
				if(_miNodo.getTipoContenido() == Nodo.OPERANDO_VALOR_SOLICITADO){
					Enumeration _enu2 = this.respuestasListadoDatosSolicitados.elements();
					DatoSolicitadoEstudio _miDato2 = null;
					String _respuesta = "";
					int tipo_pregunta = 0;
					while(_enu2.hasMoreElements()){
						_miDato2 = (DatoSolicitadoEstudio)_enu2.nextElement();
						if(_miNodo.getContenido().equals(_miDato2.getAcronimo())){
							_respuesta = _miDato2.getRespuesta();
							if(_miDato2.getTipoDato().getTipoPregunta() == 1 || _miDato2.getTipoDato().getTipoPregunta() == 2){
								tipo_pregunta = 0;
							}else{
								tipo_pregunta = 1;
							}
							break;
						}
					}
					if(tipo_pregunta != 0){
						try{
							SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
							return _temp.parse(_respuesta);
						}catch(Exception eee){
							try{
								return Double.parseDouble(_respuesta);
							}catch(Exception eeee){
								return _respuesta;
							}
						}
					}else{
						return (new RespuestasPosibles(this.getUsuario(), this.getConexion(), Integer.parseInt(_respuesta))).getRespuesta();
					}
				}
				if(_miNodo.getTipoContenido() == Nodo.OPERANDO_RESPUESTA_CERRADA){
					RespuestasPosibles _resp = new RespuestasPosibles(this.getUsuario(), this.getConexion(), Integer.parseInt(_miNodo.getContenido()));
					try{
						SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
						return _temp.parse((String)_resp.getRespuesta());
					}catch(Exception eee){
						try{
							return Double.parseDouble((String)_resp.getRespuesta());
						}catch(Exception eeee){
							return _resp.getRespuesta();
						}
					}
				}
				if(_miNodo.getTipoContenido() == Nodo.OPERANDO_PREGUNTA){
					InstanciaPregunta _insPreg = new InstanciaPregunta(this.getUsuario(), this.getConexion(), Integer.parseInt(_miNodo.getContenido()));
					Vector _miResp = Respuesta.todasRespuestas(new Encuestado(this.getConexion(), _usuario), this.getConexion(), _insPreg, _insObj);
					if(_insPreg.getTipoPregunta() >= 30){
						if(_insPreg.getTipoPregunta() == 100){
							try{
								SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
								return _temp.parse(((Respuesta)_miResp.elementAt(0)).getRespuestaAbiertaTexto());
							}catch(Exception eee){
								try{
									return Double.parseDouble(((Respuesta)_miResp.elementAt(0)).getRespuestaAbiertaTexto());
								}catch(Exception eeee){
									return ((Respuesta)_miResp.elementAt(0)).getRespuestaAbiertaTexto();
								}
							}
						}
						if(_insPreg.getTipoPregunta() == 30){
							return ((Respuesta)_miResp.elementAt(0)).getRespuestaAbiertaTexto();
						}
						if(_insPreg.getTipoPregunta() == 31){
							return new Double(((Respuesta)_miResp.elementAt(0)).getRespuestaAbiertaInt());
						}
						if(_insPreg.getTipoPregunta() == 32){
							return ((Respuesta)_miResp.elementAt(0)).getRespuestaAbiertaDouble();
						}
						if(_insPreg.getTipoPregunta() == 33){
							return ((Respuesta)_miResp.elementAt(0)).getRespuestaAbiertaDate();
						}
					}else{
						try{
							SimpleDateFormat _temp = new SimpleDateFormat("yyyy-MM-dd");
							return _temp.parse(((Respuesta)_miResp.elementAt(0)).getRespuestaCerrada().getRespuesta());
						}catch(Exception eee){
							try{
								return Double.parseDouble(((Respuesta)_miResp.elementAt(0)).getRespuestaCerrada().getRespuesta());
							}catch(Exception eeee){
								return ((Respuesta)_miResp.elementAt(0)).getRespuestaCerrada().getRespuesta();
							}
						}
					}
				}
			}else{
				if(_miNodo.getContenido().equals("+")){
					return (((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)) + ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
				if(_miNodo.getContenido().equals("-")){
					try{
						return (((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)) - ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
					}catch(Exception ee){
						return (0 - ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
					}
				}
				if(_miNodo.getContenido().equals("*")){
					return (((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)) * ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
				if(_miNodo.getContenido().equals("/")){
					return (((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)) / ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
				if(_miNodo.getContenido().equals("SENO")){
					return Math.sin(((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
				if(_miNodo.getContenido().equals("COSENO")){
					return Math.cos(((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
				if(_miNodo.getContenido().equals("TAGENTE")){
					return Math.tan(((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.Error.add("Excepci&oacute;n ejecutando el codigo");
			return null;
		}
		return null;
	}

	public boolean validarCondicionalParaUsuario(InstanciaObjeto _insObj, Nodo _miNodo, int _usuario){
		if(_miNodo.getHijoIzquierdo() == null && _miNodo.getHijoDerecho() == null){
			return true;
		}
		try{
			if(_miNodo.getContenido().equals("DISTINTO")){
				try{
					return !(((Date)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).equals((Date)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}catch(Exception ee){
					try{
						return !(((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).equals((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
					}catch(Exception eee){
						return !(((String)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).equals((String)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
					}
				}
			}
			if(_miNodo.getContenido().equals("IGUAL")){
				try{
					return (((Date)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).equals((Date)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}catch(Exception ee){
					try{
						return (((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).equals((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
					}catch(Exception eee){
						return (((String)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).equals((String)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
					}
				}
			}
			if(_miNodo.getContenido().equals(">")){
				try{
					return ((((Date)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).compareTo((Date)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario))) < 0);
				}catch(Exception ee){
					return ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario) > ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
			}
			if(_miNodo.getContenido().equals(">=")){
				try{
					return ((((Date)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).compareTo((Date)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario))) <= 0);
				}catch(Exception ee){
					return ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario) >= ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
			}
			if(_miNodo.getContenido().equals("<")){
				try{
					return ((((Date)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).compareTo((Date)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario))) > 0);
				}catch(Exception ee){
					return ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario) < ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
			}
			if(_miNodo.getContenido().equals("<=")){
				try{
					return ((((Date)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario)).compareTo((Date)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario))) >= 0);
				}catch(Exception ee){
					return ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoIzquierdo(), _usuario) <= ((Double)retornaValorOperacion(_insObj, _miNodo.getHijoDerecho(), _usuario)));
				}
			}
			if(_miNodo.getContenido().equals("Y")){
				return (validarCondicionalParaUsuario(_insObj, _miNodo.getHijoIzquierdo(), _usuario) && validarCondicionalParaUsuario(_insObj, _miNodo.getHijoDerecho(), _usuario));
			}
			if(_miNodo.getContenido().equals("O")){
				return (validarCondicionalParaUsuario(_insObj, _miNodo.getHijoIzquierdo(), _usuario) || validarCondicionalParaUsuario(_insObj, _miNodo.getHijoDerecho(), _usuario));
			}
			if(_miNodo.getContenido().equals("NEGACION")){
				return !validarCondicionalParaUsuario(_insObj, _miNodo.getHijoDerecho(), _usuario);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.Error.add("Excepci&oacute;n ejecutando el codigo");
			return false;
		}
		return true;
	}

	private void agregaResultado(Object _resultado, int _usuario){
		//solo un resultado por usuario
		Enumeration _enu = resultadosCalculos.elements();
		NodoResultadoCalculo _temp = null;
		while(_enu.hasMoreElements()){
			_temp = (NodoResultadoCalculo)_enu.nextElement();
			if(_temp._valor.equals(_resultado)){
				_temp.cantidadCoincidencias++;
				return;
			}
		}
		_temp = new NodoResultadoCalculo();
		_temp._valor = _resultado;
		_temp.cantidadCoincidencias = 1;
		resultadosCalculos.add(_temp);
		NodoResultadoCalculoSinCoincidencias _temp2 = new NodoResultadoCalculoSinCoincidencias();
		_temp2._valor = _resultado;
		_temp2.id_usuario = _usuario;
		resultadosCalculosSinCoincidencias.add(_temp2);
	}

	/****aplicamos estudio...***/
	private boolean aplicar(InstanciaObjeto _insObj, CursoEjecucionNodo _nodoActual, int _usuarios){
		boolean _resultado = true;
		if(_nodoActual == null){
			return true;
		}
		try{
			if(_nodoActual.getTipoNodo() == CursoEjecucionNodo.TIPO_CONDICIONAL){
				//filtro el total de usuarios dependiendo si cumplen con la condicion
				if(validarCondicionalParaUsuario(_insObj,_nodoActual.getArbolOperaciones(), _usuarios)){
					aplicar(_insObj, _nodoActual.getHijo(), _usuarios);
				}
				//TODO
				//creo que se guinda aqui
				aplicar(_insObj, _nodoActual.getHermano(), _usuarios);
			}else{
				Object _res = retornaValorOperacion(_insObj, _nodoActual.getArbolOperaciones(), _usuarios);
				agregaResultado(_res, _usuarios);
				InstanciaPregunta _insPregTemp = null;
				Enumeration _enuPreg = _insObj.getObjetoAsociado().getPreguntas().elements();
				while(_enuPreg.hasMoreElements()){
					_insPregTemp = (InstanciaPregunta)_enuPreg.nextElement();
					if((_insPregTemp.getEstudioAsociado() != null) && (_insPregTemp.getTipoPregunta() == 100) && (this.getId() == _insPregTemp.getEstudioAsociado().getId())){
						//hay que guardar en esta pregunta el resultado del estudio
						Respuesta _resp = new Respuesta(new Encuestado(this.getConexion(), _usuarios), this.getConexion());
						_resp.asociarInstanciaObjeto(_insObj);
						_resp.asociarInstanciaPregunta(_insPregTemp);
						try{
							_resp.setRespuesta(_res.toString());
						}catch (Exception ee){
							ee.printStackTrace();
							_resp.setRespuesta("");
						}
					}
				}
				_resultado = true;
			}
		}catch(Exception e){
			this.Error.add("Excepci&oacute;n ejecutando el codigo");
			_resultado = false;
		}
		return _resultado;
	}

	/****aplicamos estudio...***/
	public void aplicarEstudio(InstanciaObjeto _insObj, Vector _listadoDatosSolicitados){
		if(this.isCodigoValidado()){

			this.Error = new Vector();                      
			this.resultadosCalculos = new Vector();
			this.resultadosCalculosSinCoincidencias = new Vector();
			this.respuestasListadoDatosSolicitados = _listadoDatosSolicitados;
			//aplico los calculos correspondientes
			//obtener usuarios del instrumento
			try{
				PreparedStatement pstmt = getConexion().prepareStatement("SELECT DISTINCT ON(elaborado_por) elaborado_por FROM respuestas WHERE id_instancia_objetos = ?");
				pstmt.setInt(1, _insObj.getId());
				ResultSet rs = pstmt.executeQuery();
				//tengo todos los usuarios
				while(rs.next()){
					//recorro el arbol de ejecucion
					CursoEjecucionNodo _siguienteNodo = this.arbolDeEjecucionDeEstudio;
					if(!aplicar(_insObj,this.arbolDeEjecucionDeEstudio, rs.getInt("elaborado_por"))){
						//error ejecutando...
						this.Error.add("Error ejecutando...");
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//vuelve a ejecutar la compilacion de parte del codigo para obtener los datos solicitados
	public Vector forzarRecargaDatosSolicitados(){
		this.resetListadoDatosSolicitados();
		this.accion = Estudio.ACCION_DATOS_SOLICITADOS;
		this.validarCodigo();
		return this.getListadoDatosSolicitados();
	}

	public static void delEstudios(Usuario _usuario, Connection _micon, Objeto _miEstructura, boolean forzarDeleteTodos){
		try {
			PreparedStatement pstmt ;
			if(forzarDeleteTodos){
				pstmt = _micon.prepareStatement("DELETE FROM estudios WHERE id_pool_objetos = ?");
				pstmt.setInt(1, _miEstructura.getId());
			}else{
				pstmt = _micon.prepareStatement("DELETE FROM estudios WHERE id_pool_objetos = ? AND creado_por = ?");
				pstmt.setInt(1, _miEstructura.getId());
				pstmt.setInt(2, _usuario.getUsuarioId());
			}
			pstmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delEstudio(){
		try {
			PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM estudios WHERE id_estudios = ? AND creado_por = ?");
			pstmt.setInt(1, this.getId());
			pstmt.setInt(2, this.getUsuario().getUsuarioId());
			pstmt.execute();
			this.id = -1;
			this.codigo = "";
			this.titulo = "";
			this.descripcion = "";
			this.idObjeto = null;
			this.codigoValidado = false;
			this.arbol = null;
			this.cargadaDeBD = false;
			this.resetErrorList();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean tituloDisponible(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM estudios WHERE titulo = ? AND creado_por = ?");
			pstmt.setString(1, this.getTitulo());
			pstmt.setInt(2, this.getUsuario().getUsuarioId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}else{
				return true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	protected void cargarDeBD(){
		ResultSet rs = null;
		this.resetErrorList();
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM estudios WHERE id_estudios = ?");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.codigo = rs.getString("codigo");
				this.titulo = rs.getString("titulo");
				this.descripcion = rs.getString("descripcion");
				this.codigoValidado = true;
				this.arbol = null;
				this.cargadaDeBD = true;
				this.idObjeto = Objeto.retornaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_pool_objetos"));
				//ademas de volver a validar su correcto funcionamiento también cargará en this.getListadoDatosSolicitados el
				//Vector de datos solicitados por pantalla cuando se necesite ejecutarlo
				this.accion = Estudio.ACCION_COMPILACION;
				//this.validarCodigo();
			}else{
				this.id = -1;
				this.codigo = "";
				this.titulo = "";
				this.descripcion = "";
				this.codigoValidado = false;
				this.arbol = null;
				this.cargadaDeBD = false;
				this.idObjeto = null;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void guardarEnBD(){
		if((!(this.getCodigo()).equals("")) && (this.getObjeto() != null) && (this.getObjeto().getId() != -1)){
			try {
				//solo guardo codigo correctamente compilado
				//ó es válido ó no lo es y lo valido en el momento de guardarlo para comprobar
				this.accion = Estudio.ACCION_COMPILACION;
				if(this.isCodigoValidado()){
					if(getCargadaDeBD()){
						//ejecuto UPDATE
						PreparedStatement pstmt = getConexion().prepareStatement("UPDATE estudios SET codigo = ?, id_pool_objetos = ?, titulo = ?, descripcion = ? WHERE id_estudios = ? AND creado_por = ?");
						pstmt.setString(1, this.getCodigo());
						pstmt.setInt(2, this.getObjeto().getId());
						pstmt.setString(3, this.getTitulo());
						pstmt.setString(4, this.getDescripcion());
						pstmt.setInt(5, this.getId());
						pstmt.setInt(6, this.getUsuario().getUsuarioId());
						pstmt.execute();
					}else{
						//ejecuto INSERT
						PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_estudios') AS numero");
						ResultSet _rs = _pstmt.executeQuery();
						_rs.next();
						int siguiente = _rs.getInt("numero");

						PreparedStatement pstmt = getConexion().prepareStatement("INSERT INTO estudios(id_estudios, codigo, creado_por, id_pool_objetos, titulo, descripcion) VALUES (?, ?, ?, ?, ?, ?)");
						pstmt.setInt(1, siguiente);
						pstmt.setString(2, this.getCodigo());
						pstmt.setInt(3, this.getUsuario().getUsuarioId());
						pstmt.setInt(4, this.getObjeto().getId());
						pstmt.setString(5, this.getTitulo());
						pstmt.setString(6, this.getDescripcion());
						pstmt.execute();
						this.setCargadaDeBD(true);
						this.setId(siguiente);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Vector obtenerEstudiosDeEstructura(Usuario _usuario, Connection _micon, Objeto _miEstructura, boolean _paraEdicion){
		Vector _misEstudios = new Vector();
		ResultSet rs = null;
		PreparedStatement pstmt;
		if(_miEstructura != null){
			try {
				if(_paraEdicion){
					pstmt = _micon.prepareStatement("SELECT * FROM estudios WHERE creado_por = ? AND id_pool_objetos = ?");
				}else{
					pstmt = _micon.prepareStatement("SELECT * FROM estudios WHERE (creado_por = ? OR creado_por IN (SELECT id_administradores FROM administradores WHERE tipo_administrador = 'superadministrador')) AND id_pool_objetos = ?");
				}
				pstmt.setInt(1, _usuario.getUsuarioId());
				pstmt.setInt(2, _miEstructura.getId());
				rs = pstmt.executeQuery();

				while(rs.next()){
					Estudio _temp = new Estudio(_usuario, _micon);
					_temp.id = rs.getInt("id_estudios");
					_temp.titulo = rs.getString("titulo");
					_temp.descripcion = rs.getString("descripcion");
					_temp.arbol = null;
					_temp.idObjeto = _miEstructura;
					_temp.setCargadaDeBD(true);
					_temp.setCodigo(rs.getString("codigo"));
					if(_temp.isCodigoValidado()){
						_misEstudios.add(_temp);
					}
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		return _misEstudios;
	}

	private Nodo getArbol() {
		return arbol;
	}

	private void setArbol(Nodo arbol) {
		this.arbol = arbol;
	}
}
