package com.siagcee.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class PreguntaEditable extends ObjetoBase{
	private InstanciaObjeto _InsObjeto = null;
	private InstanciaPregunta _InsPregunta = null;
	private boolean noRequerida = false;
	private int _id = -1;

	public PreguntaEditable(Usuario _usuario, Connection _miConexion){
		super(_usuario, _miConexion);
		_InsObjeto = null;
		_InsPregunta = null;
		noRequerida = false;
	}

	public PreguntaEditable(Usuario _usuario, Connection _miConexion, InstanciaObjeto _insObj, InstanciaPregunta _insPreg){
		super(_usuario, _miConexion);
		_InsObjeto = _insObj;
		_InsPregunta = _insPreg;
		noRequerida = false;
		this.recarga();
		if(!this.getCargadaDeBD()){
			_InsObjeto = _insObj;
			_InsPregunta = _insPreg;
			noRequerida = false;
			this.guarda();
		}
	}

	public PreguntaEditable(Usuario _usuario, Connection _miConexion, int __id){
		super(_usuario, _miConexion);
		this._id = __id;
		noRequerida = false;
		this.carga();
	}

	public InstanciaObjeto get_InsObjeto() {
		return _InsObjeto;
	}

	public void set_InsObjeto(InstanciaObjeto _InsObjeto) {
		this._InsObjeto = _InsObjeto;
		this.guarda();
	}

	public InstanciaPregunta get_InsPregunta() {
		return _InsPregunta;
	}

	public void set_InsPregunta(InstanciaPregunta _InsPregunta) {
		this._InsPregunta = _InsPregunta;
		this.guarda();
	}

	public int get_id() {
		return _id;
	}

	protected void set_id(int _id) {
		this._id = _id;
	}

	public boolean isRequerida() {
		return !this.isNoRequerida();
	}

	public boolean isNoRequerida() {
		return noRequerida;
	}

	public void setRequerida(boolean noRequerida) {
		this.setNoRequerida(!noRequerida);
		this.guarda();
	}

	public void setNoRequerida(boolean noRequerida) {
		this.noRequerida = noRequerida;
		this.guarda();

	}

	public static Vector retornaTodasEditables(Usuario _usuario, Connection _miConexion, InstanciaObjeto _idObj){
		Vector _pregEdit = new Vector();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = null;
			pstmt = _miConexion.prepareStatement("SELECT id_campos_editables FROM campos_editables WHERE id_instancia_objetos = ? ORDER BY id_campos_editables");
			pstmt.setInt(1, _idObj.getId());
			rs = pstmt.executeQuery();
			try {
				PreguntaEditable _prgEdit;
				while (rs.next()){
					_prgEdit = new PreguntaEditable(_usuario, _miConexion, rs.getInt("id_campos_editables"));
					_pregEdit.add(_prgEdit);
				}
			}catch (Exception e1){
				//e1.printStackTrace();
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
		return _pregEdit;
	}

	public static Vector retornaTodasOpcionales(Usuario _usuario, Connection _miConexion, InstanciaObjeto _idObj){
		Vector _pregEdit = new Vector();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = null;
			pstmt = _miConexion.prepareStatement("SELECT id_campos_editables FROM campos_editables WHERE id_instancia_objetos = ? AND no_requerida = true ORDER BY id_campos_editables");
			pstmt.setInt(1, _idObj.getId());
			rs = pstmt.executeQuery();
			try {
				PreguntaEditable _prgEdit;
				while (rs.next()){
					_prgEdit = new PreguntaEditable(_usuario, _miConexion, rs.getInt("id_campos_editables"));
					_pregEdit.add(_prgEdit);
				}
			}catch (Exception e1){
				//e1.printStackTrace();
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
		return _pregEdit;
	}

	public void delPregunta(){
		if(this.getCargadaDeBD()){
			try {
				PreparedStatement pstmt = getConexion().prepareStatement("DELETE FROM campos_editables WHERE id_campos_editables = ?");
				pstmt.setInt(1, this.get_id());
				pstmt.execute();
				this.set_id(-1);
				this.set_InsPregunta(null);
				this.set_InsObjeto(null);
				this.setRequerida(false);
				this.setCargadaDeBD(false);
			}
			catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	public void delPreguntasEditables(){
		delPreguntasEditables(this.getUsuario(), this.getConexion(), this.get_InsObjeto());
		delPregunta();
	}

	public static void delPreguntasEditables(Usuario _usuario, Connection _miConexion, InstanciaObjeto _idObj){
		try {
			PreparedStatement pstmt = _miConexion.prepareStatement("DELETE FROM campos_editables WHERE id_instancia_objetos= ?");
			pstmt.setInt(1, _idObj.getId());
			pstmt.execute();
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
	}

	protected void recarga(){
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = null;
			pstmt = this.getConexion().prepareStatement("SELECT * FROM campos_editables WHERE id_instancia_objetos = ? AND id_instancia_preguntas = ?");
			pstmt.setInt(1, this.get_InsObjeto().getId());
			pstmt.setInt(2, this.get_InsPregunta().getId());
			rs = pstmt.executeQuery();
			try {
				if (rs.next()){
					this._InsObjeto = new InstanciaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_objetos"));
					this._InsPregunta = new InstanciaPregunta(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_preguntas"));
					this.noRequerida = rs.getBoolean("no_requerida");
					this.setCargadaDeBD(true);
				}else{
					this._id = -1;
					this.noRequerida = false;
					this.setCargadaDeBD(false);
				}
			}catch (Exception e1){
				e1.printStackTrace();
				this._id = -1;
				this.noRequerida = false;
				this.setCargadaDeBD(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			this._id = -1;
			this.noRequerida = false;
			this.setCargadaDeBD(false);
		}
	}

	protected void carga(){
		if(this._id != -1){
			ResultSet rs = null;
			try {
				PreparedStatement pstmt = null;
				pstmt = this.getConexion().prepareStatement("SELECT * FROM campos_editables WHERE id_campos_editables = ?");
				pstmt.setInt(1, this.get_id());
				rs = pstmt.executeQuery();
				try {
					if (rs.next()){
						this._InsObjeto = new InstanciaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_objetos"));
						this._InsPregunta = new InstanciaPregunta(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_preguntas"));
						this.noRequerida = rs.getBoolean("no_requerida");
						this.setCargadaDeBD(true);
					}else{
						this._id = -1;
						this.noRequerida = false;
						this.setCargadaDeBD(false);
					}
				}catch (Exception e1){
					//e1.printStackTrace();
					this._id = -1;
					this.noRequerida = false;
					this.setCargadaDeBD(false);
				}
			}catch (Exception e) {
				//e.printStackTrace();
				this._id = -1;
				this.noRequerida = false;
				this.setCargadaDeBD(false);
			}
		}
	}

	protected void guarda(){
		if(this.get_InsObjeto() != null && this.get_InsPregunta() != null){
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try{
				if (this.getCargadaDeBD()){
					//ejecuto UPDATE
					pstmt = getConexion().prepareStatement("UPDATE campos_editables SET id_instancia_objetos = ? , id_instancia_preguntas = ? , no_requerida = ? WHERE id_campos_editables = ?");
					pstmt.setInt(1, this.get_InsObjeto().getId());
					pstmt.setInt(2, this.get_InsPregunta().getId());
					pstmt.setBoolean(3, this.isNoRequerida());
					pstmt.setInt(4, this.get_id());
					pstmt.execute();
				}else{
					//ejecuto INSERT
					PreparedStatement _pstmt = getConexion().prepareStatement("SELECT nextval('seq_campos_editables') AS numero");
					ResultSet _rs = _pstmt.executeQuery();
					_rs.next();
					int siguiente = _rs.getInt("numero");

					pstmt = getConexion().prepareStatement("INSERT INTO campos_editables(id_campos_editables, id_instancia_preguntas, id_instancia_objetos, no_requerida) VALUES (?, ?, ?, ?)");
					pstmt.setInt(1, siguiente);
					pstmt.setInt(2, this.get_InsPregunta().getId());
					pstmt.setInt(3, this.get_InsObjeto().getId());
					pstmt.setBoolean(4, this.isNoRequerida());
					pstmt.execute();
					this.setCargadaDeBD(true);
					this.set_id(siguiente);
				}
			}catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
}
