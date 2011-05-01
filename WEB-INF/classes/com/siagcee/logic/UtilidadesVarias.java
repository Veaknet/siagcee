package com.siagcee.logic;

import jxl.Workbook;
import jxl.format.*;
import jxl.format.Colour;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.Border;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import java.util.Enumeration;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 28/01/2010
 * Hora: 10:48:29 AM
 */
public class UtilidadesVarias {

	public static String dominioWeb = "http://www.ciens.ucv.ve/sigecene/";

	public UtilidadesVarias() {
	}

	public static String reemplazarCaracteres(String _cadenaFuente, String _cadenaDeseada, String _cadenaDeReemplazo){
		StringBuffer _miBuffer = new StringBuffer();
		int fin = _cadenaFuente.indexOf(_cadenaDeseada);

		while(fin >= 0){

			_miBuffer.append(_cadenaFuente.substring(0, fin));
			_miBuffer.append(_cadenaDeReemplazo);
			_cadenaFuente = _cadenaFuente.substring(fin + _cadenaDeseada.length());

			fin = _cadenaFuente.indexOf(_cadenaDeseada);
		}
		_miBuffer.append(_cadenaFuente.substring(0, _cadenaFuente.length()));

		return _miBuffer.toString();
	}

	public static void enviarMailSinAutenticacion(String _destinatarios[], String _titulo, String _mensaje, String _de, String _encabezados, String _smtp_server){
		try{
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "sigecene@gmail.com");
			props.setProperty("mail.smtp.auth", "true");

			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props);

			// Construimos el mensaje
			for (int i = 0; i < _destinatarios.length; i++){
                try{
                    String to = _destinatarios[i];
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(_de));
                    message.addRecipient(
                            Message.RecipientType.TO,
                            new InternetAddress(to));
                    message.setSubject(_titulo);
                    //message.setText(_mensaje);
                    message.setContent(_mensaje, "text/html");
                    // Lo enviamos.
                    Transport t = session.getTransport("smtp");
                    t.connect("sigecene@gmail.com", "_siagcee_");
                    t.sendMessage(message, message.getAllRecipients());

                    // Cierre.
                    t.close();
                }catch (Exception ee){
                    System.out.println("Error enviando mail");
                }
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void enviarMailSinAutenticacion(String _destinatario, String _titulo, String _mensaje, String _de, String _encabezados, String _smtp_server){
        try{
            String _destinatarios[] = {_destinatario};
            enviarMailSinAutenticacion(_destinatarios, _titulo, _mensaje, _de, _encabezados, _smtp_server);
        }catch (Exception e){
            System.out.println("Error enviando mail.");
        }
	}

    public static boolean generaExcel(String _nombre_archivo, Vector _preguntas, Vector _respuestas, InstanciaObjeto _obj){
        WritableWorkbook workbook = null;
        String titulo = _obj.getObjeto();
        boolean _resul = false;
        try{
            workbook = Workbook.createWorkbook(new File(_nombre_archivo));
            WritableSheet hoja = workbook.createSheet(titulo, 0);

            int Col = 0;
            int Fil = 0;

            //recorro cada pregunta
            InstanciaPregunta _pregAct;
            Enumeration _enu = _preguntas.elements();
            while(_enu.hasMoreElements()){
                _pregAct = (InstanciaPregunta)_enu.nextElement();

                WritableFont times14font = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD);
                WritableCellFormat times14format = new WritableCellFormat (times14font);
                times14format.setBorder(Border.ALL, jxl.format.BorderLineStyle.MEDIUM, Colour.BLACK);
                times14format.setWrap(true);
                times14format.setAlignment(Alignment.LEFT);
                times14format.setVerticalAlignment(VerticalAlignment.TOP);
                times14format.setBackground(Colour.SEA_GREEN);

                Label label = new Label(Col, Fil, _pregAct.getAcronimo(), times14format);
                hoja.addCell(label);
                Col++;
            }
            Col = 0;
            
            //recorro cada respuesta
            _enu = _respuestas.elements();
            Respuesta _respAct = null;
            int id_usuario = -1;
            while(_enu.hasMoreElements()){
                //para cada usuario
                _respAct = (Respuesta)_enu.nextElement();
                //caso base... partimos de ningun usuario
                if(id_usuario != _respAct.getElaborado_por()){
                    //otro usuario
                    id_usuario = _respAct.getElaborado_por();
                    Col = 0;
                    Fil++;
                }else{
                    //mismo usuario que el anterior... ya procesado
                    continue;
                }
                //para cada pregunta busco su correspondiente respuesta
                Enumeration _enu2 = _preguntas.elements();
                _pregAct = null;

                while(_enu2.hasMoreElements()){
                    //para cada pregunta
                    _pregAct = (InstanciaPregunta)_enu2.nextElement();
                    Enumeration _enuInterno = _respuestas.elements();
                    Respuesta _respActInterna = null;
                    boolean _respEncon = false;
                    while(_enuInterno.hasMoreElements()){
                        //para cada respuesta donde cuadre usuario y pregunta
                        _respActInterna = (Respuesta)_enuInterno.nextElement();
                        if((_respActInterna.getElaborado_por() == id_usuario) && (_respActInterna.getInstanciaPregunta().getId() == _pregAct.getId())){
                            _respEncon = true;
                            try{
                                WritableCellFormat times14format = new WritableCellFormat ();
                                times14format.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.GREY_80_PERCENT);
                                if(_pregAct.getTipoPregunta() < 30){
                                    Label label = new Label(Col, Fil, _respActInterna.getRespuestaCerrada().getRespuesta(), times14format);
                                    hoja.addCell(label);
                                    Col++;
                                }else	if(_pregAct.getTipoPregunta() == 30){
                                    Label label = new Label(Col, Fil, _respActInterna.getRespuestaAbiertaTexto(), times14format);
                                    hoja.addCell(label);
                                    Col++;
                                }else	if(_pregAct.getTipoPregunta() == 31){
                                    Number number = new Number(Col, Fil, _respActInterna.getRespuestaAbiertaInt(), times14format);
                                    hoja.addCell(number);
                                    Col++;
                                }else	if(_pregAct.getTipoPregunta() == 32){
                                    Number number = new Number(Col, Fil, _respActInterna.getRespuestaAbiertaDouble(), times14format);
                                    hoja.addCell(number);
                                    Col++;
                                }else	if(_pregAct.getTipoPregunta() == 33){
                                    String[] _respDate = _respActInterna.getRespuestaAbiertaDate().toString().split("-");
                                    Label label = new Label(Col, Fil, _respDate[2]+"-"+_respDate[1]+"-"+_respDate[0], times14format);
                                    hoja.addCell(label);
                                    Col++;
                                }
                            }catch (Exception e1){
                                Label label = new Label(Col, Fil, "Error cargando esta respuesta");
                                hoja.addCell(label);
                                Col++;
                                break;
                            }
                        }
                    }
                    if(!_respEncon){
                        Label label = new Label(Col, Fil, "- No Sabe / No Responde -");
                        hoja.addCell(label);
                        Col++;
                    }
                }
            }

            workbook.write();
            workbook.close();
            _resul = true;

        }catch (IOException ioe){
            System.out.println("Error creando el archivo de excel.");
            ioe.printStackTrace();
            _resul = false;
        }catch (Exception e){
            System.out.println("Error no especificado.");
            e.printStackTrace();
            _resul = false;
        }
        return _resul;
    }
}
