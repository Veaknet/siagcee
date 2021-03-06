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

public class InstanciaPregunta extends ObjetoBase{

	enum CAMPOS {TEXTO_PREGUNTA, ACRONIMO};
	int idInstanciaPregunta;
	int ordenPregunta;
	String pregunta;
	String acronimo;
	Pregunta miPregunta;
	Objeto miPadre;
	Integer estudioAsociado;
	boolean campo_clave_unico;
	boolean campo_identificador;
	boolean campo_comunicacion_email;
	boolean campo_comunicacion_telefono;
	boolean campo_comunicacion_telefono2;

	public InstanciaPregunta(){
		super();
		this.idInstanciaPregunta = -1;
		this.ordenPregunta = 0;
		this.pregunta = "";
		this.acronimo = "";
		this.miPregunta = null;
		this.miPadre = null;
		this.estudioAsociado = -1;
		this.campo_clave_unico = false;
		this.campo_identificador = false;
		this.campo_comunicacion_email = false;
		this.campo_comunicacion_telefono = false;
		this.campo_comunicacion_telefono2 = false;
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	public InstanciaPregunta(Usuario _usuario, Connection _miConexion){
		super(_usuario, _miConexion);
		this.idInstanciaPregunta = -1;
		this.ordenPregunta = 0;
		this.pregunta = "";
		this.acronimo = "";
		this.miPregunta = null;
		this.miPadre = null;
		this.estudioAsociado = -1;
		this.campo_clave_unico = false;
		this.campo_identificador = false;
		this.campo_comunicacion_email = false;
		this.campo_comunicacion_telefono = false;
		this.campo_comunicacion_telefono2 = false;
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	//se carga la instanciapregunta
	public InstanciaPregunta(Usuario _usuario, Connection _miConexion, int _idInstanciaPregunta){
		super(_usuario, _miConexion);
		this.idInstanciaPregunta = _idInstanciaPregunta;
		this.ordenPregunta = 0;
		this.pregunta = "";
		this.acronimo = "";
		this.miPregunta = null;
		this.miPadre = null;
		this.estudioAsociado = -1;
		this.campo_clave_unico = false;
		this.campo_identificador = false;
		this.campo_comunicacion_email = false;
		this.campo_comunicacion_telefono = false;
		this.campo_comunicacion_telefono2 = false;
		this.recargarInstanciaPreguntaDeBD();
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	//no se guarda aun el la BD por no indicar la Pregunta que se instancia
	public InstanciaPregunta(Usuario _usuario, Connection _miConexion, String _pregunta){
		super(_usuario, _miConexion);
		this.idInstanciaPregunta = -1;
		this.ordenPregunta = 0;
		this.pregunta = _pregunta.trim();
		this.acronimo = _pregunta.trim();
		this.miPregunta = null;
		this.miPadre = null;
		this.estudioAsociado = -1;
		this.campo_clave_unico = false;
		this.campo_identificador = false;
		this.campo_comunicacion_email = false;
		this.campo_comunicacion_telefono = false;
		this.campo_comunicacion_telefono2 = false;
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	//no se guarda aun el la BD por no indicar la Pregunta que se instancia
	public InstanciaPregunta(Usuario _usuario, Connection _miConexion, String _pregunta, String _acronimo){
		super(_usuario, _miConexion);
		this.idInstanciaPregunta = -1;
		this.ordenPregunta = 0;
		this.pregunta = _pregunta.trim();
		this.acronimo = _acronimo.trim();
		this.miPregunta = null;
		this.miPadre = null;
		this.estudioAsociado = -1;
		this.campo_clave_unico = false;
		this.campo_identificador = false;
		this.campo_comunicacion_email = false;
		this.campo_comunicacion_telefono = false;
		this.campo_comunicacion_telefono2 = false;
	}

	//obligatoriamente indicar enlace a base de datos y id usuario que esta administrando el objeto
	//al ser válido el objeto se guarda en la BD.
	//Pregunta debe ser cargadaDeBD true  para poder guardar InstanciaPregunta
	public InstanciaPregunta(Usuario _usuario, Connection _miConexion, String _pregunta, Pregunta _miPregunta, Objeto _padre){
		super(_usuario, _miConexion);
		this.idInstanciaPregunta = -1;
		this.ordenPregunta = 0;
		this.pregunta = _pregunta.trim();
		this.acronimo = _pregunta.trim();
		this.miPregunta = _miPregunta;
		this.miPadre = _padre;
		this.cargadaDeBD = false;
		this.estudioAsociado = -1;
		this.campo_clave_unico = false;
		this.campo_identificador = false;
		this.campo_comunicacion_email = false;
		this.campo_comunicacion_telefono = false;
		this.campo_comunicacion_telefono2 = false;
		this.ingresaABd();
	}

	//clona una instancia de pregunta
	public InstanciaPregunta(InstanciaPregunta _insPreg){
		super(_insPreg.getUsuario(), _insPreg.getConexion());
		this.idInstanciaPregunta = -1;
		this.cargadaDeBD = false;
		this.pregunta = _insPreg.getTextoPregunta();
		this.acronimo = _insPreg.getAcronimo();
		this.miPadre = null;  //no puede tener padre para que sea editable cuando se genere esta nueva instancia. el padre se asocia luego
		this.miPregunta = _insPreg.getPreguntaAsociada();
		this.ordenPregunta = _insPreg.getOrden();
		this.estudioAsociado = _insPreg.getEstudioAsociado();
		this.campo_clave_unico = _insPreg.isCampo_clave_unico();
		this.campo_identificador = _insPreg.isCampo_identificador();
		this.campo_comunicacion_email = _insPreg.isCampo_comunicacion_email();
		this.campo_comunicacion_telefono = _insPreg.isCampo_comunicacion_telefono();
		this.campo_comunicacion_telefono2 = _insPreg.isCampo_comunicacion_telefono2();
	}

	public boolean isCampo_comunicacion_telefono2(){
		return campo_comunicacion_telefono2;
	}

	public String getAcronimo() {
		return acronimo.trim();
	}

	public void setAcronimo(String _acronimo) {
		_acronimo = _acronimo.trim();
		if(!_acronimo.trim().equals("")){
			this.acronimo = _acronimo.trim();
		}else{
			this.acronimo = this.pregunta.trim();
		}
		this.ingresaABd();
	}

	public void setCampo_comunicacion_telefono2(boolean _campo_comunicacion_telefono2) {
		this.campo_comunicacion_telefono2 = _campo_comunicacion_telefono2;
		if(_campo_comunicacion_telefono2){
			this.setCampo_comunicacion_email(false);
		}
		this.ingresaABd();
	}

	public boolean isCampo_clave_unico() {
		return campo_clave_unico;
	}

	public void setCampo_clave_unico(boolean _campo_clave_unico){
		if(this.getPadre().getPublico() && this.getPadre().getClass().toString().contains("Instrumento")){
			return;
		}
		if(_campo_clave_unico){
			//todos los demas en false
			Enumeration _ee = this.getPadre().getPreguntas(true).elements();
			while(_ee.hasMoreElements()){
				InstanciaPregunta nuevaInstaPre = (InstanciaPregunta)_ee.nextElement();
				nuevaInstaPre.setCampo_clave_unico(false);
			}
		}
		this.campo_clave_unico = _campo_clave_unico;
		this.ingresaABd();
	}

	public boolean isCampo_identificador() {
		return campo_identificador;
	}

	public void setCampo_identificador(boolean _campo_identificador) {
		if(_campo_identificador){
			//todos los demas en false
			Enumeration _ee = this.getPadre().getPreguntas(true).elements();
			while(_ee.hasMoreElements()){
				InstanciaPregunta nuevaInstaPre = (InstanciaPregunta)_ee.nextElement();
				nuevaInstaPre.setCampo_identificador(false);
			}
		}
		this.campo_identificador = _campo_identificador;
		this.ingresaABd();
	}

	public boolean isCampo_comunicacion_email() {
		return campo_comunicacion_email;
	}

	public void setCampo_comunicacion_email(boolean _campo_comunicacion_email) {
		this.campo_comunicacion_email = _campo_comunicacion_email;
		if(_campo_comunicacion_email){
			//todos los demas en false
			Enumeration _ee = this.getPadre().getPreguntas(true).elements();
			while(_ee.hasMoreElements()){
				InstanciaPregunta nuevaInstaPre = (InstanciaPregunta)_ee.nextElement();
				nuevaInstaPre.setCampo_comunicacion_email(false);
			}
			this.setCampo_comunicacion_telefono(false);
			this.setCampo_comunicacion_telefono2(false);
		}
		this.ingresaABd();
	}

	public boolean isCampo_comunicacion_telefono() {
		return campo_comunicacion_telefono;
	}

	public void setCampo_comunicacion_telefono(boolean _campo_comunicacion_telefono) {
		this.campo_comunicacion_telefono = _campo_comunicacion_telefono;
		if(_campo_comunicacion_telefono){
			this.setCampo_comunicacion_email(false);
		}
		this.ingresaABd();
	}

	public Integer getEstudioAsociado() {
		return estudioAsociado;
	}

	public boolean setEstudioAsociado(EstudioPerso _estudioAsociado) {
		//tipo pregunta 100 entonces si permito uso de estudios
		if(this.getTipoPregunta() == 100){
			this.estudioAsociado = _estudioAsociado.get_id();
			this.ingresaABd();
			return true;
		}
		return false;
	}

	public boolean setEstudioAsociado(Integer _estudioAsociado) {
		//tipo pregunta 100 entonces si permito uso de estudios
		if(this.getTipoPregunta() == 100){
			this.estudioAsociado = _estudioAsociado;
			this.ingresaABd();
			return true;
		}
		return false;
	}

	//asocia el padre el esta Instancia de Pregunta
	public void setPadre(Objeto _padre){
		if(this.miPadre != _padre){
			this.miPadre = _padre;
			this.ingresaABd();
		}
	}

	//obtien el padre el esta Instancia de Pregunta
	public Objeto getPadre(){
		return this.miPadre;
	}

	//establece el texto visible a los usuarios de la pregunta
	public void setTextoPregunta(String _pregunta){
		_pregunta = _pregunta.trim();
		if(!_pregunta.trim().equals("")){
			this.pregunta = _pregunta.trim();
			if(this.acronimo.trim().equals("")){
				this.acronimo = _pregunta;
			}
		}
		this.ingresaABd();
	}

	//texto visible a los usuarios de pregunta
	public String getTextoPregunta(){
		return this.pregunta.trim();
	}

	//establece el texto visible a los usuarios de la pregunta
	public void setOrden(int _orden){
		this.ordenPregunta = _orden;
		this.ingresaABd();
	}

	//texto visible a los usuarios de pregunta
	public int getOrden(){
		return this.ordenPregunta;
	}

	//id de la instancia de pregunta
	private void setId(int _idPregunta){
		this.idInstanciaPregunta = _idPregunta;
	}

	//id de la instancia de pregunta
	public int getId(){
		return this.idInstanciaPregunta;
	}

	//establezco la pregunta que será instanciada (asociada desde este objeto)
	public void asociarPregunta(Pregunta _pregunta){
		if(_pregunta != this.miPregunta){
			this.miPregunta = _pregunta;
			this.ingresaABd();
		}
	}

	//obtengo la pregunta que instanciada (asociada desde este objeto)
	public Pregunta getPreguntaAsociada(){
		return this.miPregunta;
	}

	//respuestas posibles para esta pregunta
	public Vector respuestasPosibles(){
		if(this.miPregunta != null){
			return this.miPregunta.retornaRespuestasPosibles();
		}else{
			return new Vector();
		}
	}

	//tipo de pregunta 1=seleccion simple 2=seleccion multiple 30=abierta
	public int getTipoPregunta(){
		if(this.getPreguntaAsociada() != null){
			return this.getPreguntaAsociada().getTipoPregunta();
		}else{
			return -1;
		}
	}

	//indica si esta InstanciaPregunta puede ser modificada
	public boolean esEditable(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM instancia_preguntas WHERE id_instancia_preguntas = ? AND id_pool_objetos NOT IN(SELECT id_pool_objetos FROM instancia_objetos WHERE id_pool_objetos NOT IN (SELECT id_pool_objetos FROM pool_objetos WHERE tipo_objeto = 3))");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()){
				return true;
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		return false;
	}

	//revisa las duplicaciones de un campo indicado y agrega el prefijo 1º, 2º, 3º, .... Nº al campo
	private void ValidaRepeticionCampo(int repeticionesEncontradas, CAMPOS campo){
		Enumeration _enu = this.getPadre().getPreguntas().elements();
		String _prefix = "";
		String _prefixSig = "";
		if(repeticionesEncontradas > 1){
			_prefix = repeticionesEncontradas+"º ";
		}
		repeticionesEncontradas++;
		_prefixSig = repeticionesEncontradas+"º ";
		while(_enu.hasMoreElements()){
			InstanciaPregunta _temp = (InstanciaPregunta)_enu.nextElement();
			if(_temp.getId() == this.getId()){
				continue;
			}
			if(campo == CAMPOS.ACRONIMO){
				if(this.getAcronimo().equals(_prefix+_temp.getAcronimo())){
					//reviso si la siguiente tambien se encuentra
					ValidaRepeticionCampo(repeticionesEncontradas, campo);
					if(this.getAcronimo().equals(_prefix+_temp.getAcronimo())){
						//si no se encuentra, entonces no ha cambiado la condicion de duplicacion, lo cambio
						this.acronimo = _prefixSig+_temp.getAcronimo();
					}
					return;
				}
			}else if(campo == CAMPOS.TEXTO_PREGUNTA){
				if(this.getTextoPregunta().equals(_prefix+_temp.getTextoPregunta())){
					//reviso si la siguiente tambien se encuentra
					ValidaRepeticionCampo(repeticionesEncontradas, campo);
					if(this.getTextoPregunta().equals(_prefix+_temp.getTextoPregunta())){
						//si no se encuentra, entonces no ha cambiado la condicion de duplicacion, lo cambio
						this.pregunta = _prefixSig+_temp.getTextoPregunta();
					}
					return;
				}
			}
		}
	}

	//dado los datos que se tienen en el objeto se actualiza o se inserta en la BD
	private void ingresaABd(){
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		//si no se ha provisto de un nombre amistoso para la pregunta no es posible insertarla en la BD
		if((!(this.getTextoPregunta()).equals("")) && (this.getPreguntaAsociada() != null) && (this.getPadre() != null)){
			ValidaRepeticionCampo(1, CAMPOS.TEXTO_PREGUNTA);
			ValidaRepeticionCampo(1, CAMPOS.ACRONIMO);
			try{
				pstmt = this.getConexion().prepareStatement("SELECT * FROM instancia_preguntas WHERE orden_pregunta = ? AND id_pool_objetos = ? AND id_instancia_preguntas <> ?");
				pstmt.setInt(1, this.getOrden());
				pstmt.setInt(2, this.getPadre().getId());
				pstmt.setInt(3, this.getId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					//alguien mas con mi ordenpregunta... entonces los subo a todos
					pstmt = getConexion().prepareStatement("UPDATE instancia_preguntas SET orden_pregunta = orden_pregunta + 1 WHERE orden_pregunta >= ? AND id_pool_objetos = ? AND id_instancia_preguntas <> ?");
					pstmt.setInt(1, this.getOrden());
					pstmt.setInt(2, this.getPadre().getId());
					pstmt.setInt(3, this.getId());
					pstmt.execute();
				}
				if (this.getCargadaDeBD()){
					//ejecuto UPDATE
					if(this.getCargadaDeBD()){
						pstmt = getConexion().prepareStatement("UPDATE instancia_preguntas SET pregunta = ? , orden_pregunta = ? , id_pool_preguntas = ? , id_pool_objetos = ?, id_estudios = ?, campo_clave_unico = ?, campo_identificador = ?, campo_comunicacion_email = ?, campo_comunicacion_telefono = ?, campo_comunicacion_telefono2 = ? , acronimo = ? WHERE id_instancia_preguntas = ?");
						pstmt.setString(1, this.getTextoPregunta());
						pstmt.setInt(2, this.getOrden());
						pstmt.setInt(3, this.getPreguntaAsociada().getId());
						pstmt.setInt(4, this.getPadre().getId());
						int idEstudio = -1;
						if(this.getEstudioAsociado() != -1){
						  idEstudio = this.getEstudioAsociado();
						}
						pstmt.setInt(5, idEstudio);
						pstmt.setBoolean(6, this.isCampo_clave_unico());
						pstmt.setBoolean(7, this.isCampo_identificador());
						pstmt.setBoolean(8, this.isCampo_comunicacion_email());
						pstmt.setBoolean(9, this.isCampo_comunicacion_telefono());
						pstmt.setBoolean(10, this.isCampo_comunicacion_telefono2());
						pstmt.setString(11, this.getAcronimo());
						pstmt.setInt(12, this.getId());
						pstmt.execute();
					}
				}else{
					//ejecuto INSERT
					PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_instancia_preguntas') AS numero");
					ResultSet _rs = _pstmt.executeQuery();
					_rs.next();
					int siguiente = _rs.getInt("numero");

					pstmt = getConexion().prepareStatement("INSERT INTO instancia_preguntas(id_instancia_preguntas, pregunta, orden_pregunta, creado_por, id_pool_objetos, id_pool_preguntas, id_estudios, campo_clave_unico, campo_identificador, campo_comunicacion_email, campo_comunicacion_telefono, campo_comunicacion_telefono2, acronimo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					pstmt.setInt(1, siguiente);
					pstmt.setString(2, this.getTextoPregunta());
					pstmt.setInt(3, this.getOrden());
					pstmt.setInt(4, this.getUsuario().getUsuarioId());
					pstmt.setInt(5, this.getPadre().getId());
					pstmt.setInt(6, this.getPreguntaAsociada().getId());
					int idEstudio = -1;
					if(this.getEstudioAsociado() != -1){
					  idEstudio = this.getEstudioAsociado();
					}
					pstmt.setInt(7, idEstudio);
					pstmt.setBoolean(8, this.isCampo_clave_unico());
					pstmt.setBoolean(9, this.isCampo_identificador());
					pstmt.setBoolean(10, this.isCampo_comunicacion_email());
					pstmt.setBoolean(11, this.isCampo_comunicacion_telefono());
					pstmt.setBoolean(12, this.isCampo_comunicacion_telefono2());
					pstmt.setString(13, this.getAcronimo());
					pstmt.execute();
					this.setCargadaDeBD(true);
					this.setId(siguiente);
				}
			}catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	//elimina la instancia de pregunta incluyendo la BD
	public void delPregunta() {
		if(this.esEditable()){
			try {
				PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM instancia_preguntas WHERE id_instancia_preguntas = ?");
				pstmt.setInt(1, getId());
				pstmt.execute();
				this.ordenPregunta = 0;
				this.pregunta = "";
				this.acronimo = "";
				this.pregunta = null;
				this.idInstanciaPregunta = -1;
				this.miPadre = null;
				this.setEstudioAsociado(-1);
				this.setCargadaDeBD(false);
				this.campo_clave_unico = false;
				this.campo_identificador = false;
				this.campo_comunicacion_email = false;
				this.campo_comunicacion_telefono = false;
				this.campo_comunicacion_telefono2 = false;
			}
			catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	//recarga el objeto desde la BD tomando en cuenta el ID de la pregunta
	public void recargarInstanciaPreguntaDeBD(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM instancia_preguntas WHERE id_instancia_preguntas = ?");
			pstmt.setInt(1, this.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.pregunta = rs.getString("pregunta").trim();
				this.acronimo = rs.getString("acronimo").trim();
				this.ordenPregunta = rs.getInt("orden_pregunta");
				this.setId(rs.getInt("id_instancia_preguntas"));
				this.campo_clave_unico = rs.getBoolean("campo_clave_unico");
				this.campo_identificador = rs.getBoolean("campo_identificador");
				this.campo_comunicacion_email = rs.getBoolean("campo_comunicacion_email");
				this.campo_comunicacion_telefono = rs.getBoolean("campo_comunicacion_telefono");
				this.campo_comunicacion_telefono2 = rs.getBoolean("campo_comunicacion_telefono2");
				if(this.miPregunta != null){
					if(this.miPregunta.getId() != rs.getInt("id_pool_preguntas")){
						//creo un nuevo objeto cuando ya este esta asociado
						this.miPregunta = new Pregunta(this.getUsuario(), this.getConexion(), rs.getInt("id_pool_preguntas"));
					}
				}else{
					this.miPregunta = new Pregunta(this.getUsuario(), this.getConexion(), rs.getInt("id_pool_preguntas"));
				}
				if(this.miPadre != null){
					if(this.miPadre.getId() != rs.getInt("id_pool_objetos")){
						//creo un nuevo objeto cuando ya este esta asociado
						this.miPadre = new Objeto(this.getUsuario(), this.getConexion(), rs.getInt("id_pool_objetos"));
					}
				}else{
					this.miPadre = new Objeto(this.getUsuario(), this.getConexion(), rs.getInt("id_pool_objetos"));
				}
				this.estudioAsociado = rs.getInt("id_estudios");
				this.setCargadaDeBD(true);
			}else{
				this.pregunta = "";
				this.acronimo = "";
				this.ordenPregunta = 0;
				this.setId(-1);
				this.miPregunta = null;
				this.setEstudioAsociado(-1);
				this.setCargadaDeBD(false);
				this.campo_clave_unico = false;
				this.campo_identificador = false;
				this.campo_comunicacion_email = false;
				this.campo_comunicacion_telefono = false;
				this.campo_comunicacion_telefono2 = false;
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
	}

	//retorna todas las instancias de preguntas para un objeto padre (censo, encuesta, etc)
	//si _soloPublicas entonces solo buscara las preguntas para el encuestado
	//caso contrario busca las preguntas disponibles para edicion
	public static Vector todasPreguntasInstanciadas(Usuario _usuario, Connection _miConexion, Objeto _padre){
		return todasPreguntasInstanciadas(_usuario, _miConexion, _padre, false);
	}

	//retorna todas las instancias de preguntas para un objeto padre (censo, encuesta, etc)
	//si _soloPublicas entonces solo buscara las preguntas para el encuestado
	//caso contrario busca las preguntas disponibles para edicion
	public static Vector todasPreguntasInstanciadas(Usuario _usuario, Connection _miConexion, Objeto _padre, boolean _soloPublicas){
		Vector _lista = new Vector();
		ResultSet rs = null;
		InstanciaPregunta ObjPregTemp = null;

		rs = obtenerDataInstanciaPreguntas(_usuario, _miConexion, _padre, _soloPublicas);
		try {
			while (rs.next()){
				ObjPregTemp = new InstanciaPregunta(_usuario, _miConexion);
				ObjPregTemp.pregunta = rs.getString("pregunta").trim();
				ObjPregTemp.acronimo = rs.getString("acronimo").trim();
				ObjPregTemp.idInstanciaPregunta = rs.getInt("id_instancia_preguntas");
				ObjPregTemp.miPadre = _padre;
				ObjPregTemp.ordenPregunta = rs.getInt("orden_pregunta");
				ObjPregTemp.miPregunta = new Pregunta(_usuario, _miConexion, rs.getInt("id_pool_preguntas"));
				ObjPregTemp.campo_clave_unico = rs.getBoolean("campo_clave_unico");
				ObjPregTemp.campo_identificador = rs.getBoolean("campo_identificador");
				ObjPregTemp.campo_comunicacion_email = rs.getBoolean("campo_comunicacion_email");
				ObjPregTemp.campo_comunicacion_telefono = rs.getBoolean("campo_comunicacion_telefono");
				ObjPregTemp.campo_comunicacion_telefono2 = rs.getBoolean("campo_comunicacion_telefono2");
				ObjPregTemp.estudioAsociado = rs.getInt("id_estudios");
				ObjPregTemp.cargadaDeBD = true;
				_lista.add(ObjPregTemp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Vector();
		}
		return _lista;
	}

	//retorna un Resulset de todas las preguntas disponibles para un usuario
	private static ResultSet obtenerDataInstanciaPreguntas(Usuario _usuario, Connection _miConexion, Objeto _padre, boolean _soloPublicas){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = null;
			if(_soloPublicas){
				pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_preguntas WHERE id_pool_objetos = ? ORDER BY orden_pregunta");
				pstmt.setInt(1, _padre.getId());
			}else{
				if(_usuario.getTipoUsuario().equals("superadministrador")){
					pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_preguntas WHERE id_pool_objetos = ? ORDER BY orden_pregunta");
					pstmt.setInt(1, _padre.getId());
				}else{
					pstmt = _miConexion.prepareStatement("SELECT * FROM instancia_preguntas WHERE creado_por = ? AND id_pool_objetos = ? ORDER BY orden_pregunta");
					pstmt.setInt(1, _usuario.getUsuarioId());
					pstmt.setInt(2, _padre.getId());
				}
			}
			rs = pstmt.executeQuery();
		}catch (Exception e) {
			return null;
		}
		return rs;
	}

	public static InstanciaPregunta preguntaConBanderaActivada(Usuario _usuario, Connection _miConexion, Objeto _padre, boolean _soloPublicas, String _bandera){
		InstanciaPregunta _pregunta = null;
		ResultSet rs = null;

		rs = obtenerDataInstanciaPreguntas(_usuario, _miConexion, _padre, _soloPublicas);
		try {
			while (rs.next()){
				if(_bandera.equals("campo_clave_unico") && (!rs.getBoolean("campo_clave_unico"))){
					continue;
				}
				if(_bandera.equals("campo_identificador") && (!rs.getBoolean("campo_identificador"))){
					continue;
				}
				if(_bandera.equals("campo_comunicacion_email") && (!rs.getBoolean("campo_comunicacion_email"))){
					continue;
				}
				if(_bandera.equals("campo_comunicacion_telefono") && (!rs.getBoolean("campo_comunicacion_telefono"))){
					continue;
				}
				if(_bandera.equals("campo_comunicacion_telefono2") && (!rs.getBoolean("campo_comunicacion_telefono2"))){
					continue;
				}
				_pregunta = new InstanciaPregunta(_usuario, _miConexion);
				_pregunta.pregunta = rs.getString("pregunta").trim();
				_pregunta.acronimo = rs.getString("acronimo").trim();
				_pregunta.idInstanciaPregunta = rs.getInt("id_instancia_preguntas");
				_pregunta.miPadre = _padre;
				_pregunta.ordenPregunta = rs.getInt("orden_pregunta");
				_pregunta.miPregunta = new Pregunta(_usuario, _miConexion, rs.getInt("id_pool_preguntas"));
				_pregunta.campo_clave_unico = rs.getBoolean("campo_clave_unico");
				_pregunta.campo_identificador = rs.getBoolean("campo_identificador");
				_pregunta.campo_comunicacion_email = rs.getBoolean("campo_comunicacion_email");
				_pregunta.campo_comunicacion_telefono = rs.getBoolean("campo_comunicacion_telefono");
				_pregunta.campo_comunicacion_telefono2 = rs.getBoolean("campo_comunicacion_telefono2");
				_pregunta.estudioAsociado = rs.getInt("id_estudios");
				_pregunta.cargadaDeBD = true;
				break;
			}
		}
		catch (Exception e) {
			return null;
		}
		return _pregunta;
	}
}
