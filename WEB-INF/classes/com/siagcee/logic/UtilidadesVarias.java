package com.siagcee.logic;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javadocx.CreateDocx;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

	public static String dominioWeb = "http://debian6:8080/siagcee/";

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

	public static boolean generaExcel(String _nombre_archivo, Vector _preguntas, Vector _respuestas, InstanciaObjeto _obj, boolean _plantilla){
		return generaExcel(_nombre_archivo, _preguntas, _respuestas, _obj.getObjeto(), _plantilla);
	}

	public static boolean generaExcel(String _nombre_archivo, Vector _preguntas, Vector _respuestas, InstanciaObjeto _obj){
		return generaExcel(_nombre_archivo, _preguntas, _respuestas, _obj.getObjeto(), false);
	}

	public static boolean generaExcel(String _nombre_archivo, Vector _preguntas, Vector _respuestas, String _obj){
		return generaExcel(_nombre_archivo, _preguntas, _respuestas, _obj, false);
	}

	public static boolean generaPDF(String _nombre_archivo, Vector _preguntas, Vector _respuestas, InstanciaObjeto _obj){
		return generaPDF(_nombre_archivo, _preguntas, _respuestas, _obj.getObjeto());
	}

	public static boolean generaWord(String _nombre_archivo, Vector _preguntas, Vector _respuestas, InstanciaObjeto _obj){
		return generaWord(_nombre_archivo, _preguntas, _respuestas, _obj.getObjeto());
	}

    public static boolean generaExcel(String _nombre_archivo, Vector _preguntas, Vector _respuestas, String _obj, boolean _plantilla){
        WritableWorkbook workbook = null;
        String titulo = _obj;
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
				if((_pregAct.getTipoPregunta() < 30 || _pregAct.getTipoPregunta() >= 100) && _plantilla){
					continue;
				}
                WritableFont times14font = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD);
                WritableCellFormat times14format = new WritableCellFormat (times14font);
                times14format.setBorder(Border.ALL, jxl.format.BorderLineStyle.MEDIUM, Colour.BLACK);
                times14format.setWrap(true);
                times14format.setAlignment(Alignment.LEFT);
                times14format.setVerticalAlignment(VerticalAlignment.TOP);
                times14format.setBackground(Colour.SEA_GREEN);
 				WritableCellFeatures cellFeatures = new WritableCellFeatures();
				if(_pregAct.getTipoPregunta() == 33){
					cellFeatures.setComment("Formato aceptado para fechas: dd-mm-aaaa (dd: dia; mm: mes; aaaa: año");
				}
				if(_pregAct.getTipoPregunta() == 32){
					cellFeatures.setComment("Formato aceptado para este campo: números con decimales separados por coma(,)");
				}
				if(_pregAct.getTipoPregunta() == 31){
					cellFeatures.setComment("Formato aceptado para este campo: números sin decimales");
				}
				if(_pregAct.getTipoPregunta() == 30){
					cellFeatures.setComment("Cualquier valor es aceptado para este campo");
				}

                Label label = new Label(Col, Fil, _pregAct.getAcronimo(), times14format);
				label.setCellFeatures(cellFeatures);
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

                while(_enu2.hasMoreElements() && !_plantilla){
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
                                if(_pregAct.getTipoPregunta() < 30 && !_plantilla){
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
									times14format = new WritableCellFormat (DateFormats.FORMAT2);
									times14format.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.GREY_80_PERCENT);
                                    String[] _respDate = _respActInterna.getRespuestaAbiertaDate().toString().split("-");
                                    Label label = new Label(Col, Fil, _respDate[2]+"-"+_respDate[1]+"-"+_respDate[0], times14format);
                                    hoja.addCell(label);
                                    Col++;
								}else	if(_pregAct.getTipoPregunta() >= 100 && !_plantilla){
									Label label = new Label(Col, Fil, _respActInterna.getRespuestaAbiertaTexto(), times14format);
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
                        Label label = new Label(Col, Fil, "");
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

    public static boolean generaPDF(String _nombre_archivo, Vector _preguntas, Vector _respuestas, String _obj){
        Document documento = new Document(PageSize.LEGAL.rotate());
        String titulo = _obj;
        boolean _resul = false;
        try{
            PdfWriter.getInstance(documento, new FileOutputStream(_nombre_archivo));
            documento.open();


            Paragraph _titulo = new Paragraph(titulo, FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 18));
            _titulo.setAlignment(Paragraph.ALIGN_CENTER);
            _titulo.setSpacingAfter(6.0f);
            documento.add(_titulo);

            PdfPTable table = new PdfPTable(_preguntas.size());
            table.setWidthPercentage(100);

            //recorro cada pregunta
            InstanciaPregunta _pregAct;
            Enumeration _enu = _preguntas.elements();
            com.itextpdf.text.Font f = new com.itextpdf.text.Font();
            f.setSize(14f);
            f.setColor(BaseColor.BLACK);
            while(_enu.hasMoreElements()){
                _pregAct = (InstanciaPregunta)_enu.nextElement();

                PdfPCell cell = new PdfPCell(new Phrase(_pregAct.getAcronimo(), f));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setExtraParagraphSpace(2f);
                // the cell object
                table.addCell(cell);
            }

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
                                    table.addCell(_respActInterna.getRespuestaCerrada().getRespuesta());
                                }else	if(_pregAct.getTipoPregunta() == 30){
                                    table.addCell(_respActInterna.getRespuestaAbiertaTexto());
                                }else	if(_pregAct.getTipoPregunta() == 31){
                                    table.addCell(String.valueOf(_respActInterna.getRespuestaAbiertaInt()));
                                }else	if(_pregAct.getTipoPregunta() == 32){
                                    table.addCell(String.valueOf(_respActInterna.getRespuestaAbiertaDouble()));
                                }else	if(_pregAct.getTipoPregunta() == 33){
                                    String[] _respDate = _respActInterna.getRespuestaAbiertaDate().toString().split("-");
                                    table.addCell(_respDate[2]+"-"+_respDate[1]+"-"+_respDate[0]);
								}else	if(_pregAct.getTipoPregunta() <= 100){
									table.addCell(_respActInterna.getRespuestaAbiertaTexto());
                                }
                            }catch (Exception e1){
                                table.addCell("Error cargando esta respuesta");
                                break;
                            }
                        }
                    }
                    if(!_respEncon){
                        table.addCell("");
                    }
                }
            }

            documento.add(table);
            documento.close();

            _resul = true;

        }catch (IOException ioe){
            System.out.println("Error creando el archivo pdf.");
            ioe.printStackTrace();
            _resul = false;
        }catch (Exception e){
            System.out.println("Error no especificado.");
            e.printStackTrace();
            _resul = false;
        }
        return _resul;
    }

    public static boolean generaWord(String _nombre_archivo, Vector _preguntas, Vector _respuestas, String _obj){
        String titulo = _obj;

        boolean _resul = false;
        try{
            CreateDocx docx = new CreateDocx("docx");

            ArrayList valuesTable = new ArrayList();
            ArrayList row1 = new ArrayList();

            ArrayList row2 = new ArrayList();

            //recorro cada pregunta
            InstanciaPregunta _pregAct;
            Enumeration _enu = _preguntas.elements();
            while(_enu.hasMoreElements()){
                _pregAct = (InstanciaPregunta)_enu.nextElement();

                row1.add(_pregAct.getAcronimo());
            }
            valuesTable.add(row1);

            boolean iniciando = true;
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
                    if(!iniciando){
                        valuesTable.add(row2);
                    }else{
                        iniciando = !iniciando;
                    }
                    row2 = new ArrayList();
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
                                if(_pregAct.getTipoPregunta() < 30){
                                    row2.add(_respActInterna.getRespuestaCerrada().getRespuesta());
                                }else	if(_pregAct.getTipoPregunta() == 30){
                                    row2.add(_respActInterna.getRespuestaAbiertaTexto());
                                }else	if(_pregAct.getTipoPregunta() == 31){
                                    row2.add(String.valueOf(_respActInterna.getRespuestaAbiertaInt()));
                                }else	if(_pregAct.getTipoPregunta() == 32){
                                    row2.add(String.valueOf(_respActInterna.getRespuestaAbiertaDouble()));
                                }else	if(_pregAct.getTipoPregunta() == 33){
                                    String[] _respDate = _respActInterna.getRespuestaAbiertaDate().toString().split("-");
                                    row2.add(_respDate[2]+"-"+_respDate[1]+"-"+_respDate[0]);
								}else	if(_pregAct.getTipoPregunta() >= 100){
									row2.add(_respActInterna.getRespuestaAbiertaTexto());
                                }
                            }catch (Exception e1){
                                row2.add("Error cargando esta respuesta");
                                break;
                            }
                        }
                    }
                    if(!_respEncon){
                        row2.add("");
                    }
                }
            }

            if(!iniciando){
                valuesTable.add(row2);
            }

            HashMap paramsTable = new HashMap();
            paramsTable.put("border", "single");
            paramsTable.put("border_sz", "12");
            paramsTable.put("font", "Times New Roman");

            docx.addTable(valuesTable, paramsTable);
            docx.createDocx(_nombre_archivo);

            _resul = true;

        }catch (Exception e){
            System.out.println("Error no especificado.");
            e.printStackTrace();
            _resul = false;
        }
        return _resul;
    }

    public static boolean cargaExcel(File archivo, InstanciaObjeto _obj, Administrador admin, Connection micon){
        Workbook workbook = null;
        String titulo = _obj.getObjeto();
        boolean _resul = false;
        try{
            workbook = Workbook.getWorkbook(new File(archivo.getAbsolutePath()));
            Sheet sheet = workbook.getSheet(0);
            int Col = 0;
            int Fil = 0;    //La primera fila define cantidad de preguntas a cargar
            int CantPreguntas = 0;

            int _preguntaClave = -1;
            Cell celda = null;
            String valor = "";
            while(true){
                try{
                    celda = sheet.getCell(Col,Fil);
                    valor = celda.getContents();
                    if(valor.equals("")){
                        Fil++;
                        Col = 0;
                        break;
                    }
                    Col++;
                    CantPreguntas++;
                }catch(Exception ee1){
					ee1.printStackTrace();
                    Fil++;
                    Col = 0;
                    break;
                }
            }

            InstanciaPregunta _pregAct;
            Enumeration _enu = _obj.getObjetoAsociado().getPreguntas(true).elements();
            Vector _preguntas = new Vector();
            Col = 0;
            while(_enu.hasMoreElements()){
                _pregAct = (InstanciaPregunta)_enu.nextElement();
				if(_pregAct.getTipoPregunta() == 100 || _pregAct.getTipoPregunta() < 30){
					continue;
				}
                if(_pregAct.isCampo_clave_unico()){
                    _preguntaClave = Col;
                }
                Col++;
                _preguntas.add(_pregAct);
            }

            Respuesta _resp = null;
            Encuestado _encu = null;
            while(true){
                boolean todosVacios = true;
                _enu = _preguntas.elements();
                if(_preguntaClave == -1){
                    _encu = new Encuestado(micon, String.valueOf(_obj.getId()), "");
                }else{
                    try{
                        celda = sheet.getCell(_preguntaClave, Fil);
                        valor = celda.getContents();
                        _encu = new Encuestado(micon, String.valueOf(_obj.getId()), valor);
                    }catch(Exception ee3){
						ee3.printStackTrace();
                        _encu = new Encuestado(micon, String.valueOf(_obj.getId()), "");
                    }
                }

                for(int col=0;col < CantPreguntas;col++){
                    _pregAct = (InstanciaPregunta)_enu.nextElement();
                    try{
                        celda = sheet.getCell(col,Fil);
                        valor = celda.getContents();
                    }catch(Exception eee2){
						eee2.printStackTrace();
                        valor = "";
                    }
                    if(!(valor.trim().isEmpty() && valor.trim().equals(""))){
                        todosVacios = false;
                    }
					if(valor.equals("")){continue;}
					_resp = new Respuesta(_encu, micon);
				    _resp.asociarInstanciaObjeto(_obj);
					_resp.asociarInstanciaPregunta(_pregAct);

                    if(_pregAct.getTipoPregunta() == 30){
                        _resp.setRespuesta(valor);
                    }else if(_pregAct.getTipoPregunta() == 31){
                        if(valor.equals("")){ valor = "";}
						try{
							_resp.setRespuesta(Long.parseLong(valor));
						}catch (Exception excp){
							excp.printStackTrace();
						}
                    }else if(_pregAct.getTipoPregunta() == 32){
                        if(valor.equals("")){ valor = "";}
						try{
							_resp.setRespuesta(Double.parseDouble(valor));
						}catch (Exception excp){
							excp.printStackTrace();
						}
                    }else if(_pregAct.getTipoPregunta() == 33){
						SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
						Date today = null;
						try {
							today = df.parse(valor);
						} catch (ParseException e) {
							df = new SimpleDateFormat("dd-MM-yy");
							today = null;
							try {
								today = df.parse(valor);
							} catch (ParseException e1) {
								df = new SimpleDateFormat("dd/MM/yyyy");
								today = null;
								try {
									today = df.parse(valor);
								} catch (ParseException e21) {
									df = new SimpleDateFormat("dd/MM/yy");
									today = null;
									try {
										today = df.parse(valor);
									} catch (ParseException e41) {
										e41.printStackTrace();
									}
								}
							}
						}
						_resp.setRespuesta(today);
                    }
                }
                if(todosVacios){
                    //al ser vacio quiere decir que sus respuestas todas eran vacias y por lo tanto no existe
                    Respuesta.delRespuestasDeUsuario(_encu, micon, _obj);
                    _encu.delUsuario();
                    break;
                }
                Fil++;
            }
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
