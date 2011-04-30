package com.siagcee.logic;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 11/12/2009
 * Hora: 09:00:03 AM
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.Servlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class AccesosEncuestados extends ObjetoBase{ 
	
	private int idInterno;
	private Encuestado miEncuestado;
	private InstanciaObjeto miObjeto;

	public AccesosEncuestados(){
		super();
		idInterno = -1;
		miEncuestado = null;
		miObjeto = null;
		this.cargadaDeBD = false;
	}
	
	public AccesosEncuestados(Usuario _usuario, Connection _miConexion){
		super(_usuario, _miConexion);
		idInterno = -1;
		miEncuestado = null;
		miObjeto = null;
		this.cargadaDeBD = false;
	}

	public AccesosEncuestados(Usuario _usuario, Connection _miConexion, int _id){
		super(_usuario, _miConexion);
		idInterno = _id;
		miEncuestado = null;
		miObjeto = null;
		this.cargadaDeBD = false;
		this.cargaDeBd();
	}

	public AccesosEncuestados(Usuario _usuario, Connection _miConexion, Encuestado _miEnc){
		super(_usuario, _miConexion);
		idInterno = -1;
		miEncuestado = _miEnc;
		miObjeto = null;
		this.cargadaDeBD = false;
	}

	public AccesosEncuestados(Connection _miConexion, InstanciaObjeto _miIns){
		super();
		this.setConexion(_miConexion);
		idInterno = -1;
		miEncuestado = null;
		miObjeto = _miIns;
		this.cargadaDeBD = false;
	}
	
	public AccesosEncuestados(Connection _miConexion, Encuestado _miEnc, InstanciaObjeto _miIns){
		super();
		this.setConexion(_miConexion);
		idInterno = -1;
		miEncuestado = _miEnc;
		miObjeto = _miIns;
		this.cargadaDeBD = false;
		this.cargaDeBd();
	}

	public Encuestado getEncuestado(){
		return this.miEncuestado;
	}

	public void setEncuestado(Encuestado _miEnc){
		this.miEncuestado = _miEnc;
		this.cargaDeBd();
	}

	public InstanciaObjeto getObjetoAsociado(){
		return this.miObjeto;
	}

	public void setObjetoAsociado(InstanciaObjeto _miObj){
		this.miObjeto = _miObj;
		this.cargaDeBd();
	}

	public int getId(){
		return this.idInterno;
	}

	private void setId(int _id){
		this.idInterno = _id;
	}

	//comprueba si fue asociado el usuario a dicho objeto
	//no comprueba si el objeto es de tipo publico o privado
	//en caso de ser de tipo publico esta comprobacion no debiera importar mucho para validar acceso
	public boolean compruebaAcceso(){
		try{
			if(!this.getCargadaDeBD()){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	protected void cargaDeBd(){
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		if(this.getId() != -1){
			try{
				pstmt = this.getConexion().prepareStatement("SELECT * FROM accesos_encuestados WHERE id_accesos_encuestados = ?");
				pstmt.setInt(1, this.getId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					this.miObjeto = new InstanciaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_objetos"));
					this.miEncuestado = new Encuestado(this.getConexion(), rs.getInt("id_usuarios"));
					this.setId(rs.getInt("id_accesos_encuestados"));
					this.setCargadaDeBD(true);
				}else{
					this.setId(-1);
					this.miObjeto = null;
					this.miEncuestado = null;
					this.setCargadaDeBD(false);
				}
			}
			catch (Exception e) {e.printStackTrace();}
		}else{
			if(this.getEncuestado() != null && this.getObjetoAsociado() != null){
				try{
					pstmt = this.getConexion().prepareStatement("SELECT * FROM accesos_encuestados WHERE id_usuarios = ? AND id_instancia_objetos = ?");
					pstmt.setInt(1, this.getEncuestado().getUsuarioId());
					pstmt.setInt(2, this.getObjetoAsociado().getId());
					rs = pstmt.executeQuery();
					if (rs.next()) {
						this.miObjeto = new InstanciaObjeto(this.getUsuario(), this.getConexion(), rs.getInt("id_instancia_objetos"));
						this.miEncuestado = new Encuestado(this.getConexion(), rs.getInt("id_usuarios"));
						this.setId(rs.getInt("id_accesos_encuestados"));
						this.setCargadaDeBD(true);
					}else{
						this.setId(-1);
						this.miObjeto = null;
						this.miEncuestado = null;
						this.setCargadaDeBD(false);
					}
				}
				catch (Exception e) {e.printStackTrace();}
			}else{
				this.setCargadaDeBD(false);
			}
		}
	}

	//eliminar completamente este acceso de la BD
	public void delAccesosEncuestados(){
		try{
			PreparedStatement pstmt = this.getConexion().prepareStatement("DELETE FROM accesos_encuestados WHERE id_accesos_encuestados = ?");
			pstmt.setInt(1, this.getId());
			pstmt.execute();
			this.idInterno = -1;
			this.cargadaDeBD = false;
			this.miObjeto = null;
			this.miEncuestado = null;
		}catch(Exception e){e.printStackTrace();}
	}

	//eliminar completamente este acceso de la BD
	public static void delAccesosEncuestados(Connection _micon, InstanciaObjeto _obj){
		try{
			PreparedStatement pstmt = _micon.prepareStatement("DELETE FROM accesos_encuestados WHERE id_instancia_objetos = ?");
			pstmt.setInt(1, _obj.getId());
			pstmt.execute();
		}catch(Exception e){e.printStackTrace();}
	}

	//inserta en la BD la nueva condicion de acceso a objetos
	//devuelve el nuevo AccesosEncuestados con el usuario y objetoAsociado
	//o null si fallo el ingreso
	public static AccesosEncuestados IngresarEncuestado(Encuestado _enc, InstanciaObjeto _obj, Connection _micon){
		if((_enc != null) && (_obj != null)){
			try{
				PreparedStatement pstmt = _micon.prepareStatement("INSERT INTO accesos_encuestados(id_instancia_objetos, id_usuarios) VALUES (?, ?)");
				pstmt.setInt(1, _obj.getId());
				pstmt.setInt(2, _enc.getUsuarioId());
				pstmt.execute();
				AccesosEncuestados _t = new AccesosEncuestados(_micon, _enc, _obj);
				if(_t.getCargadaDeBD()){
					return _t;
				}else{
					return null;
				}
			}catch(Exception e){e.printStackTrace(); return null;}
		}
		return null;
	}

	public static Vector IngresarEncuestadosEnNuevoObjeto(Connection _micon, String _SQL, InstanciaObjeto _id_objeto_destino, int _idPreguntaEmail, String _textoEmail){
		Vector _mivector = new Vector();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		if(!_SQL.equals("")){
			try{
				pstmt = _micon.prepareStatement(_SQL+ " AND (id_instancia_preguntas = ?)");
				pstmt.setInt(1, _idPreguntaEmail);
				rs = pstmt.executeQuery();
				boolean _primero = true;

				//*** DOMINIO WEB ***//
				String dominioweb = UtilidadesVarias.dominioWeb;
				while(rs.next()){
					Encuestado _e = new Encuestado(_micon, rs.getInt("elaborado_por"));
					Encuestado _e2 = new Encuestado(_micon, String.valueOf(_id_objeto_destino.getId()), _e.getCampoClave());
					AccesosEncuestados _ae = IngresarEncuestado(_e2, _id_objeto_destino, _micon);
					if(_ae != null){
						_mivector.addElement(_ae);
					}
					if(_idPreguntaEmail > -1){
						PreparedStatement pstmt2 = _micon.prepareStatement("SELECT * FROM respuestas WHERE elaborado_por = ? AND id_instancia_objetos = ? AND id_instancia_preguntas = ?");
						pstmt2.setInt(1, rs.getInt("elaborado_por"));
						pstmt2.setInt(2, rs.getInt("id_objeto_fuente"));
						pstmt2.setInt(3, _idPreguntaEmail);
						ResultSet rs2 = pstmt2.executeQuery();
						if(rs2.next()){
							try{
								Respuesta _re = new Respuesta(new Encuestado(_micon, rs.getInt("elaborado_por")), _micon, rs2.getInt("id_respuestas"));
								if(_re.getInstanciaPregunta().getTipoPregunta() == 30 || _re.getInstanciaPregunta().getTipoPregunta() == 100){
									InstanciaObjeto _miIns = new InstanciaObjeto(_e, _micon, rs.getInt("id_objeto_fuente"));
									if(_primero){
										_textoEmail = _textoEmail+"\n<br /><p />\n"+"<a target='_blank' href=\""+UtilidadesVarias.dominioWeb+"autenticarusuario.do?identificador_publico="+_id_objeto_destino.getIdPublico()+"\">"+_miIns.getObjeto()+"</a>";
									}
									UtilidadesVarias.enviarMailSinAutenticacion(_re.getRespuestaAbiertaTexto(), "Usted esta cordialmente invitado a participar.", _textoEmail, "SIGECENE <sigecene@sigecene.com>", "", "mail.cantv.net");
								}else{
									break;
								}
							}catch (Exception eee){
								eee.printStackTrace();
							}
						}
					}
					_primero = false;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return new Vector();
			}
		}
		return _mivector;
	}

	//a traves de un SQL que debe contener los campos: id_usuarios y id_objeto_fuente en los resultados
	//id_usuarios y id_objeto_fuente en los resultados OBLIGATORIO
	public static Vector IngresarEncuestadosEnNuevoObjeto(Connection _micon, String _SQL, InstanciaObjeto _id_objeto_destino){
		return IngresarEncuestadosEnNuevoObjeto(_micon, _SQL, _id_objeto_destino, -1, "");
	}

	//retorna vector con accesosEncuestados asociados a _idObjeto
	public static Vector RetornaAccesosEncuestados(Usuario _usuario, Connection _micon, InstanciaObjeto _idObjeto){
		Vector _miVector = new Vector();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		AccesosEncuestados _ae;
		try{
			pstmt = _micon.prepareStatement("SELECT * FROM accesos_encuestados WHERE id_instancia_objetos = ?");
			pstmt.setInt(1, _idObjeto.getId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				_ae = new AccesosEncuestados(_usuario, _micon, rs.getInt("id_accesos_encuestados"));
				_miVector.add(_ae);
			}else{
				//no hay nada
			}
		}catch(Exception e){
			e.printStackTrace();
			return new Vector();
		}
		return _miVector;
	}
}
