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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Vector;

public class Objeto extends ObjetoBase{

	int idObjeto;
	String objeto;
	Vector misPreguntas;   //Vector de instanciaPregunta

	public Objeto(){
		super();
		this.idObjeto = -1;
		this.objeto = "";
		this.misPreguntas = new Vector();
		this.publico = true;
	}

	//constructor usuario y conexion debe proveerse
	public Objeto(Usuario _usuario, Connection _miConexion){
		super(_usuario, _miConexion);
		this.idObjeto = -1;
		this.objeto = "";
		this.misPreguntas = new Vector();
		this.publico = true;
	}

	//constructor usuario y conexion debe proveerse
	protected Objeto(Usuario _usuario, Connection _miConexion, int _idObjeto){
		super(_usuario, _miConexion);
		this.idObjeto = _idObjeto;
		this.objeto = "";
		this.misPreguntas = new Vector();
		this.publico = true;
	}

	//constructor usuario y conexion debe proveerse
	public Objeto(Usuario _usuario, Connection _miConexion, String _objeto){
		super(_usuario, _miConexion);
		this.idObjeto = -1;
		this.objeto = _objeto;
		this.misPreguntas = new Vector();
		this.publico = true;
	}

	//retorna nombre amigable del objeto
	public String getObjeto(){
		return this.objeto;	
	}

	public boolean getPublico() {
		return publico;
	}

	public void setPublico(boolean _publico) {
		publico = _publico;
	}

	//indica nombre amigable del objeto
	public void setObjeto(String _objeto){
		this.objeto = _objeto;
	}

	//retorna Vector de InstanciaPregunta asociadas
	public Vector getPreguntas(boolean _soloPublicas){
		this.misPreguntas = InstanciaPregunta.todasPreguntasInstanciadas(this.getUsuario(), this.getConexion(), this, _soloPublicas);
		return this.misPreguntas;
	}

	//retorna Vector de InstanciaPregunta asociadas
	public Vector getPreguntas(){
		this.misPreguntas = InstanciaPregunta.todasPreguntasInstanciadas(this.getUsuario(), this.getConexion(), this);
		return this.misPreguntas;
	}

	//asocia una InstanciaPregunta a el Objeto
	public void agregarInstanciaPregunta(InstanciaPregunta _pregunta){
		_pregunta.setPadre(this);
		this.misPreguntas.add(_pregunta);
	}

	//establece un tipo de Objeto y retorna una instancia del nuevo tipo de dato.
	public Objeto setTipo(int _tipoObjeto){
		try {
			if (getCargadaDeBD()) {
				//ejecuto UPDATE
				if(this.esEditable()){
					PreparedStatement pstmt = getConexion().prepareStatement("UPDATE pool_objetos SET tipo_objeto = ? WHERE id_pool_objetos = ?");
					pstmt.setInt(1, _tipoObjeto);
					pstmt.setInt(2, this.getId());
					pstmt.execute();
					Objeto _tmp = Objeto.retornaObjeto(this.getUsuario(), this.getConexion(), this.getId());
					return _tmp;
				}
			}
		}catch(Exception e){return null;}
		return null;
	}

	//get ID del objeto
	public int getId(){
		return this.idObjeto;
	}

	//set ID del objeto
	protected void setId(int _id){
		this.idObjeto = _id;
	}

	//indica si este Objeto puede ser modificado
	public boolean esEditable(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM pool_objetos WHERE id_pool_objetos = ? AND ((id_pool_objetos NOT IN(SELECT id_pool_objetos FROM instancia_objetos) AND tipo_objeto <> 3) OR tipo_objeto = 3)");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			return false;
		}
		return false;
	}

	//retorna un vector de estudios asociados a la estructura
	public Vector estudiosAsociados(boolean _paraEdicion){
		return Estudio.obtenerEstudiosDeEstructura(this.getUsuario(), this.getConexion(), this, _paraEdicion);
	}

	//retorna un vector de estudios asociados a la estructura
	public void eliminarEstudiosAsociados(boolean _forazarDeleteTodos){
		Estudio.delEstudios(this.getUsuario(), this.getConexion(), this, _forazarDeleteTodos);
	}

	public void delObjeto(){
		delObjeto(false);
	}

	//elimina la pregunta incluyendo la BD
	public void delObjeto(boolean forzar){
		if(this.esEditable() || forzar){
			//antes de eliminar el objeto debemos eliminar las instancias de preguntas asociadas.
			this.getPreguntas();
			try {
				Enumeration _misPreg = this.misPreguntas.elements();
				while(_misPreg.hasMoreElements()){
					InstanciaPregunta temp = (InstanciaPregunta)_misPreg.nextElement();
					temp.delPregunta();
				}
				this.eliminarEstudiosAsociados(true);
				PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM pool_objetos WHERE id_pool_objetos = ? AND creado_por = ?");
				pstmt.setInt(1, this.getId());
				pstmt.setInt(2, this.getUsuario().getUsuarioId());
				pstmt.execute();
				this.idObjeto = 0;
				this.objeto = new String("");
				this.misPreguntas = new Vector();
				this.publico = true;
				this.setCargadaDeBD(false);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//dado los datos que se tienen en el objeto se actualiza o se inserta en la BD
	//debido a que pudieran tener caracteristicas distintas los censos y encuestas
	//esta funcion debe ser implementada en las clases herederas
	private void ingresaABd(){
	}

	//carga el objeto desde BD dado su ID
	//debe ser implementado por las clases herederas
	private void recargaDeBd(){

	}

	//devuelve el campo_clave_unico
	public InstanciaPregunta retornaPreguntaClave(){
		return retornaPreguntaClave(false);
	}

	//devuelve el campo_identificador
	public InstanciaPregunta retornaPreguntaIdentificador(){
		return retornaPreguntaIdentificador(false);
	}

	//devuelve el campo_comunicacion_telefono2
	public InstanciaPregunta retornaPreguntaComunicacionTelefono2(){
		return retornaPreguntaComunicacionTelefono2(false);
	}

	//devuelve el campo_comunicacion_telefono
	public InstanciaPregunta retornaPreguntaComunicacionTelefono(){
		return retornaPreguntaComunicacionTelefono(false);
	}

	//devuelve el campo_comunicacion_email
	public InstanciaPregunta retornaPreguntaComunicacionEmail(){
		return retornaPreguntaComunicacionEmail(false);
	}

	public InstanciaPregunta retornaPreguntaClave(boolean _publica){
		return InstanciaPregunta.preguntaConBanderaActivada(this.getUsuario(), this.getConexion(), this, _publica, "campo_clave_unico");
	}

	//devuelve el campo_identificador
	public InstanciaPregunta retornaPreguntaIdentificador(boolean _publica){
		return InstanciaPregunta.preguntaConBanderaActivada(this.getUsuario(), this.getConexion(), this, _publica, "campo_identificador");
	}

	//devuelve el campo_comunicacion_telefono2
	public InstanciaPregunta retornaPreguntaComunicacionTelefono2(boolean _publica){
		return InstanciaPregunta.preguntaConBanderaActivada(this.getUsuario(), this.getConexion(), this, _publica, "campo_comunicacion_telefono2");
	}

	//devuelve el campo_comunicacion_telefono
	public InstanciaPregunta retornaPreguntaComunicacionTelefono(boolean _publica){
		return InstanciaPregunta.preguntaConBanderaActivada(this.getUsuario(), this.getConexion(), this, _publica, "campo_comunicacion_telefono");
	}

	//devuelve el campo_comunicacion_email
	public InstanciaPregunta retornaPreguntaComunicacionEmail(boolean _publica){
		return InstanciaPregunta.preguntaConBanderaActivada(this.getUsuario(), this.getConexion(), this, _publica, "campo_comunicacion_email");
	}

	//retorna un Vector con todas los objetos disponibles dado un usuario
	//_cargaSelectiva = 0   --> todas
	//_cargaSelectiva = 1   --> libres (no instanciadas)
	//_visible solo muestra los objetos que esten marcadas como visibles por el creador, caso contrario las trae todas
	//_soloPropias solo mostrara los objetos que sean del usuario (para editarlas) o false todas las comunes (para utilzarlas o instanciarlas)
	public static Vector todosObjetos(Usuario _usuario, Connection _miConexion, int _cargaSelectiva, boolean _visible, boolean _soloPropias){
		Vector _lista = new Vector();
		_lista.addAll(EstructuraBase.todosObjetos(_usuario, _miConexion, _cargaSelectiva, _visible, _soloPropias));
		_lista.addAll(Censo.todosObjetos(_usuario, _miConexion, _cargaSelectiva, _visible, _soloPropias));
		_lista.addAll(Encuesta.todosObjetos(_usuario, _miConexion, _cargaSelectiva, _visible, _soloPropias));
		_lista.addAll(Relacion.todosObjetos(_usuario, _miConexion, _cargaSelectiva, _visible, _soloPropias));
		return _lista;
	}

	//retorna un Vector con todas las preguntas disponibles dado un usuario editables o no editables
	//_visible solo muestra los objetos que esten marcadas como visibles por el creador, caso contrario las trae todas
	//_soloPropias solo mostrara las preguntas que sean del usuario (para editarlas) o todas las comunes (para utilzarlas o instanciarlas)
	public static Vector todosObjetos(Usuario _usuario, Connection _miConexion, boolean _visible, boolean _soloPropias){
		return todosObjetos(_usuario, _miConexion, 0, _visible, _soloPropias);
	}

	//debido a que no es posible instanciar directamente Objeto, porque no tendría Sentido.
	//se ofrece este método estático que retornara la instancia adecuada del Objeto segun su ID
	//hasta el momento solo existen 2 tipos Censo y Encuesta
	public static Objeto retornaObjeto(Usuario _usuario, Connection _miConexion, int _idObjeto){
		Objeto _temp = null;
		_temp = new EstructuraBase(_usuario, _miConexion, _idObjeto);
		if((_temp == null) || (_temp.getObjeto().equals(""))){
			_temp = new Censo(_usuario, _miConexion, _idObjeto);
			//no existe censo con ese ID se intenta con Encuesta
			if((_temp == null) || (_temp.getObjeto().equals(""))){
				_temp = new Encuesta(_usuario, _miConexion, _idObjeto);
				//no existe Encuesta con ese ID se intenta con Relacion
				if((_temp == null) || (_temp.getObjeto().equals(""))){
					_temp = new Relacion(_usuario, _miConexion, _idObjeto);
				}
			}
		}
		return _temp;
	}
}
