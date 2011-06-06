package com.siagcee.logic;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class EstudioPerso extends ObjetoBase{
	protected static EstudioPerso _instance = null;
	protected Administrador admin;
	protected Connection micon;
	protected String _cod = "";
	protected InstanciaObjeto _obj = null;
	protected Objeto _obj_simple = null;
	protected Vector _errores = new Vector();
	protected Enumeration _enuError = null;
	protected HashMap _resultados = new HashMap();
	protected Iterator _enuResul = null;
	protected String _titulo = "";
	protected String _acronimo = "";
	protected int _id = -1;


	public int _usuarioCorriente = -1; //para usar con el haspmap y agregar un resultado único por usuario
	public HashMap _memoriaInternaEstudio = new HashMap();

	protected EstudioPerso(){
		//inicializacion
	}

	public static EstudioPerso getInstance(){
		if(_instance == null){
			_instance = new EstudioPerso();
		}
		return _instance;
	}

	public static void resetInstance(){
		_instance = new EstudioPerso();
	}

	public String get_acronimo() {
		return _acronimo;
	}

	public void set_acronimo(String _acronimo) {
		this._acronimo = _acronimo;
	}

	public String get_titulo() {
		return _titulo;
	}

	public void set_titulo(String _titulo) {
		this._titulo = _titulo;
	}

	public int get_id() {
		return _id;
	}

	protected void set_id(int _id) {
		this._id = _id;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Connection getMicon() {
		return micon;
	}

	public void setMicon(Connection micon) {
		this.micon = micon;
	}

	public String get_cod() {
		return _cod;
	}

	public void set_cod(String _cod) {
		this._cod = _cod+"\n";
	}

	public InstanciaObjeto get_obj() {
		return _obj;
	}

	public void set_obj(InstanciaObjeto _obj) {
		this._obj = _obj;
		set_obj_simple(_obj.getObjetoAsociado());
	}

	public Objeto get_obj_simple() {
		return _obj_simple;
	}

	public void set_obj_simple(Objeto _obj) {
		this._obj_simple = _obj;
	}

	public void addError(String __error){
		_errores.add((String)__error);
	}

	public String nextError(){
		try{
			return ((String)_enuError.nextElement());
		}catch(Exception e){
			return "";
		}
	}

	public boolean hayErrores(){
		return !_errores.isEmpty();
	}

	public String firstError(){
		_enuError = _errores.elements();
		return nextError();
	}

	public void addResultado(String __resul){
		try{
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			_resultados.put(_usuarioCorriente, df.format(__resul));
		}catch (Exception e1){
			e1.printStackTrace();
			try{
				DecimalFormat df = new DecimalFormat("###################");
				_resultados.put(_usuarioCorriente, df.format(Long.valueOf((String) __resul)));
			}catch (Exception e12){
				e12.printStackTrace();
				try{
					DecimalFormat df = new DecimalFormat("################.###############");
					_resultados.put(_usuarioCorriente, df.format(Double.valueOf((String) __resul)));
				}catch (Exception e13){
					e13.printStackTrace();
					try{
						_resultados.put(_usuarioCorriente, (String) __resul);
					}catch (Exception e14){
						e14.printStackTrace();
					}
				}
			}
		}
	}

	public boolean hayMasResultados(){
		return _enuResul.hasNext();
	}

	public String nextResultado(){
		try{
			return ((((Map.Entry)_enuResul.next()).getValue()).toString());
		}catch(Exception e){
			return null;
		}
	}

	public boolean hayResultado(){
		return !_resultados.isEmpty();
	}

	public String firstResultado(){
		_enuResul = _resultados.entrySet().iterator();
		return nextResultado();
	}

	public HashMap getHashMapResultados(){
		return this._resultados;
	}

	public void ejecutaEstudio(){
		if(this.get_id() != -1){
			ejecutaEstudio(this.get_cod());
		}
	}

	public void ejecutaEstudio(String _cod){
		set_cod(_cod);
		if(this.get_obj() == null){
			this.addError("No se ha indicado el Objeto a estudiar");
			return;
		}
		try{
			//valido la sintaxis
			ANTLRStringStream input = new ANTLRStringStream(get_cod());
			SigeceneGramLexer lexer = new SigeceneGramLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SigeceneGramParser parser = new SigeceneGramParser(tokens);
			SigeceneGramParser.prog_return r = parser.prog();

			CommonTree t = (CommonTree)r.getTree();
			CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
			SigeceneTree walker = new SigeceneTree(nodes);
			//si no sustituyo las variables por sus correspondientes datos.. fallará porque memoria interna está vacía
			//walker.prog();

			//recorro cada usuario para obtener respuestas
			try{
				if(!hayErrores()){
					PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT DISTINCT ON(elaborado_por) elaborado_por FROM respuestas WHERE id_instancia_objetos = ?");
					pstmt.setInt(1, this.get_obj().getId());
					ResultSet rs = pstmt.executeQuery();
					//tengo todos los usuarios participantes
					while(rs.next()){
						this._usuarioCorriente = rs.getInt("elaborado_por");
						_memoriaInternaEstudio = new HashMap();

						//agrego las respuestas de este usuario a la memoria interna
						Enumeration _enuPreg = this.get_obj().getObjetoAsociado().getPreguntas().elements();
						InstanciaPregunta _insPregTemp;
						while(_enuPreg.hasMoreElements()){
							_insPregTemp = (InstanciaPregunta)_enuPreg.nextElement();
							Vector respuestas = Respuesta.todasRespuestas(new Encuestado(this.getConexion(), this._usuarioCorriente), this.getConexion(), _insPregTemp, this.get_obj());
							Enumeration _enuResp = respuestas.elements();
							while(_enuResp.hasMoreElements()){
								Respuesta _resp = (Respuesta)_enuResp.nextElement();
								if(_insPregTemp.getTipoPregunta() == 1){
									_memoriaInternaEstudio.put("["+_insPregTemp.getAcronimo()+"]", _resp.getRespuestaCerrada().getRespuesta());
								}
								if(_insPregTemp.getTipoPregunta() == 2){
									_memoriaInternaEstudio.put("["+_insPregTemp.getAcronimo()+"]", _resp.getRespuestaCerrada().getRespuesta());
								}
								if(_insPregTemp.getTipoPregunta() == 30 || _insPregTemp.getTipoPregunta() == 100){
									_memoriaInternaEstudio.put("["+_insPregTemp.getAcronimo()+"]", _resp.getRespuestaAbiertaTexto());
								}
								if(_insPregTemp.getTipoPregunta() == 31){
									_memoriaInternaEstudio.put("["+_insPregTemp.getAcronimo()+"]", String.valueOf(_resp.getRespuestaAbiertaInt()));
								}
								if(_insPregTemp.getTipoPregunta() == 32){
									_memoriaInternaEstudio.put("["+_insPregTemp.getAcronimo()+"]", String.valueOf(_resp.getRespuestaAbiertaDouble()));
								}
								if(_insPregTemp.getTipoPregunta() == 33){
									Date _tmp = _resp.getRespuestaAbiertaDate();
									SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
									_memoriaInternaEstudio.put("["+_insPregTemp.getAcronimo()+"]", df.format(_tmp));
								}
							}
						}

						input = new ANTLRStringStream(get_cod());
						lexer = new SigeceneGramLexer(input);
						tokens = new CommonTokenStream(lexer);
						parser = new SigeceneGramParser(tokens);
						r = parser.prog();
						t = (CommonTree)r.getTree();
						nodes = new CommonTreeNodeStream(t);
						walker = new SigeceneTree(nodes);
						walker.prog();
					}
				}else{
					return;
				}
			}catch (Exception e) {
				this.addError("Error iniciando ejecucion de estudio");
				e.printStackTrace();
			}

		}catch(RecognitionException gramEx){
			gramEx.printStackTrace();
		}catch (Exception gramEx){
			gramEx.printStackTrace();
		}
	}

	public void guardar(){
		if((!(this.get_cod()).equals("")) && (this.get_obj_simple() != null) && (this.get_obj_simple().getId() != -1)){
			try {
				if(getCargadaDeBD()){
					//ejecuto UPDATE
					PreparedStatement pstmt = getConexion().prepareStatement("UPDATE estudios SET codigo = ?, id_pool_objetos = ?, titulo = ?, descripcion = ? WHERE id_estudios = ? AND creado_por = ?");
					pstmt.setString(1, this.get_cod());
					pstmt.setInt(2, this.get_obj_simple().getId());
					pstmt.setString(3, this.get_titulo());
					pstmt.setString(4, this.get_acronimo());
					pstmt.setInt(5, this.get_id());
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
					pstmt.setString(2, this.get_cod());
					pstmt.setInt(3, this.getUsuario().getUsuarioId());
					pstmt.setInt(4, this.get_obj_simple().getId());
					pstmt.setString(5, this.get_titulo());
					pstmt.setString(6, this.get_acronimo());
					pstmt.execute();
					this.setCargadaDeBD(true);
					this.set_id(siguiente);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void cargar(int _id){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.getConexion().prepareStatement("SELECT * FROM estudios WHERE id_estudios = ?");
			pstmt.setInt(1, _id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this._id = _id;
				this._cod = rs.getString("codigo");
				this._titulo = rs.getString("titulo");
				this._acronimo = rs.getString("descripcion");
				this.cargadaDeBD = true;
				this._obj_simple = Objeto.retornaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_pool_objetos"));
			}else{
				this._id = -1;
				this._cod = "";
				this._titulo = "";
				this._acronimo = "";
				this.cargadaDeBD = false;
				this._obj_simple = null;
				this._obj = null;
			}
		}
		catch (Exception e){
			e.printStackTrace();
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
					_misEstudios.add(rs.getInt("id_estudios"));
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		return _misEstudios;
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
			pstmt.setInt(1, this.get_id());
			pstmt.setInt(2, this.getUsuario().getUsuarioId());
			pstmt.execute();
			resetInstance();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InstanciaPregunta retornaPreguntaAsociada(){
		Enumeration _enu = InstanciaPregunta.todasPreguntasInstanciadas(this.getUsuario(), this.getConexion(), this.get_obj_simple(), true).elements();
		InstanciaPregunta _temp;
		while(_enu.hasMoreElements()){
			_temp = (InstanciaPregunta)_enu.nextElement();
			if((_temp.getEstudioAsociado() != null) && (_temp.getEstudioAsociado() == this.get_id())){
				return _temp;
			}
		}
		return null;
	}

	//FUNCIONES PARA FUNCIONAMIENTO INTERNO DEL ESTUDIO
	//las variables vienen: [acronimo]
	public String getContarField(String _campo){
		Enumeration _enu = InstanciaPregunta.todasPreguntasInstanciadas(this.getUsuario(), this.getConexion(), this.get_obj_simple()).elements();
		while(_enu.hasMoreElements()){
			InstanciaPregunta _insPre = (InstanciaPregunta)_enu.nextElement();
			String _acrno = "["+_insPre.getAcronimo()+"]";
			if(_campo.equals(_acrno)){
				//consegui la pregunta
				return String.valueOf((Respuesta.todasRespuestas(null, this.getConexion(), _insPre, this.get_obj())).size());
			}
		}
		return "0";
	}

	public String getPromedioField(String _campo){
		double res = 0;
		try{
			res = Double.parseDouble(this.getSumatoriaField(_campo)) / Double.parseDouble(this.getContarField(_campo));
		}catch (Exception _res){}
		return String.valueOf(res);
	}

	public String getSumatoriaField(String _campo){
		double resultado = 0;
		Enumeration _enu = InstanciaPregunta.todasPreguntasInstanciadas(this.getUsuario(), this.getConexion(), this.get_obj_simple()).elements();
		while(_enu.hasMoreElements()){
			InstanciaPregunta _insPre = (InstanciaPregunta)_enu.nextElement();
			String _acrno = "["+_insPre.getAcronimo()+"]";
			if(_campo.equals(_acrno)){
				//consegui la pregunta
				_enu = Respuesta.todasRespuestas(null, this.getConexion(), _insPre, this.get_obj()).elements();
				while(_enu.hasMoreElements()){
					Respuesta _resp = (Respuesta)_enu.nextElement();
					try{
						if(_insPre.getTipoPregunta() == 31){
							resultado = resultado + Double.parseDouble(String.valueOf(_resp.getRespuestaAbiertaInt()));
						}else if(_insPre.getTipoPregunta() == 32){
							resultado = resultado + _resp.getRespuestaAbiertaDouble();
						}else if(_insPre.getTipoPregunta() == 1){
							resultado = resultado + Double.parseDouble(_resp.getRespuestaCerrada().getRespuesta());
						}else{
							resultado = resultado + Double.parseDouble(_resp.getRespuestaAbiertaTexto());
						}
					}catch (Exception res){}
				}
				break;
			}
		}
		return String.valueOf(resultado);
	}

	public String getMaxField(String _campo){
		double resultado = Double.MIN_VALUE;
		Date resultado2 = null;
		String resultado3 = "";
		String resultadoFinal = "";
		Enumeration _enu = InstanciaPregunta.todasPreguntasInstanciadas(this.getUsuario(), this.getConexion(), this.get_obj_simple()).elements();
		while(_enu.hasMoreElements()){
			InstanciaPregunta _insPre = (InstanciaPregunta)_enu.nextElement();
			String _acrno = "["+_insPre.getAcronimo()+"]";
			if(_campo.equals(_acrno)){
				//consegui la pregunta
				_enu = Respuesta.todasRespuestas(null, this.getConexion(), _insPre, this.get_obj()).elements();
				while(_enu.hasMoreElements()){
					Respuesta _resp = (Respuesta)_enu.nextElement();
					try{
						if(_insPre.getTipoPregunta() == 31){
							if(Double.parseDouble(String.valueOf(_resp.getRespuestaAbiertaInt())) > resultado){
								resultado = Double.parseDouble(String.valueOf(_resp.getRespuestaAbiertaInt()));
							}
						}else if(_insPre.getTipoPregunta() == 32){
							if(_resp.getRespuestaAbiertaDouble() > resultado){
								resultado = _resp.getRespuestaAbiertaDouble();
							}
						}else if(_insPre.getTipoPregunta() == 33){
							if(resultado2 == null){
								resultado2 = _resp.getRespuestaAbiertaDate();
							}else{
								if(_resp.getRespuestaAbiertaDate().compareTo(resultado2) > 0){
									resultado2 = _resp.getRespuestaAbiertaDate();
								}
							}
						}else if(_insPre.getTipoPregunta() == 1){
							if(_resp.getRespuestaCerrada().getRespuesta().compareTo(resultado3) > 0){
								resultado3 = _resp.getRespuestaCerrada().getRespuesta();
							}
						}else if(_insPre.getTipoPregunta() == 100 || _insPre.getTipoPregunta() == 30){
							if(_resp.getRespuestaAbiertaTexto().compareTo(resultado3) > 0){
								resultado3 = _resp.getRespuestaAbiertaTexto();
							}
						}else{
							if(Double.parseDouble(_resp.getRespuestaAbiertaTexto()) > resultado){
								resultado = Double.parseDouble(_resp.getRespuestaAbiertaTexto());
							}
						}
					}catch (Exception res){}
				}
				if(_insPre.getTipoPregunta() == 33){
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					resultadoFinal = String.valueOf(df.format(resultado2));
				}else if(_insPre.getTipoPregunta() == 31 || _insPre.getTipoPregunta() == 32){
					resultadoFinal = String.valueOf(resultado);
				}else{
					resultadoFinal = resultado3;
				}
				break;
			}
		}
		return resultadoFinal;
	}

	public String getMinField(String _campo){
		double resultado = Double.MAX_VALUE;
		Date resultado2 = null;
		String resultado3 = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
		String resultadoFinal = "";
		Enumeration _enu = InstanciaPregunta.todasPreguntasInstanciadas(this.getUsuario(), this.getConexion(), this.get_obj_simple()).elements();
		while(_enu.hasMoreElements()){
			InstanciaPregunta _insPre = (InstanciaPregunta)_enu.nextElement();
			String _acrno = "["+_insPre.getAcronimo()+"]";
			if(_campo.equals(_acrno)){
				//consegui la pregunta
				_enu = Respuesta.todasRespuestas(null, this.getConexion(), _insPre, this.get_obj()).elements();
				while(_enu.hasMoreElements()){
					Respuesta _resp = (Respuesta)_enu.nextElement();
					try{
						if(_insPre.getTipoPregunta() == 31){
							if(Double.parseDouble(String.valueOf(_resp.getRespuestaAbiertaInt())) < resultado){
								resultado = Double.parseDouble(String.valueOf(_resp.getRespuestaAbiertaInt()));
							}
						}else if(_insPre.getTipoPregunta() == 32){
							if(_resp.getRespuestaAbiertaDouble() < resultado){
								resultado = _resp.getRespuestaAbiertaDouble();
							}
						}else if(_insPre.getTipoPregunta() == 33){
							if(resultado2 == null){
								resultado2 = _resp.getRespuestaAbiertaDate();
							}else{
								if(_resp.getRespuestaAbiertaDate().compareTo(resultado2) < 0){
									resultado2 = _resp.getRespuestaAbiertaDate();
								}
							}
						}else if(_insPre.getTipoPregunta() == 1){
							if(_resp.getRespuestaCerrada().getRespuesta().compareTo(resultado3) < 0){
								resultado3 = _resp.getRespuestaCerrada().getRespuesta();
							}
						}else if(_insPre.getTipoPregunta() == 100 || _insPre.getTipoPregunta() == 30){
							if(_resp.getRespuestaAbiertaTexto().compareTo(resultado3) < 0){
								resultado3 = _resp.getRespuestaAbiertaTexto();
							}
						}else{
							if(Double.parseDouble(_resp.getRespuestaAbiertaTexto()) < resultado){
								resultado = Double.parseDouble(_resp.getRespuestaAbiertaTexto());
							}
						}
					}catch (Exception res){}
				}
				if(_insPre.getTipoPregunta() == 33){
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					resultadoFinal = String.valueOf(df.format(resultado2));
				}else if(_insPre.getTipoPregunta() == 31 || _insPre.getTipoPregunta() == 32){
					resultadoFinal = String.valueOf(resultado);
				}else{
					resultadoFinal = resultado3;
				}
				break;
			}
		}
		return resultadoFinal;
	}

}
