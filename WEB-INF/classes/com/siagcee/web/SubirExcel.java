package com.siagcee.web;

import com.siagcee.logic.Administrador;
import com.siagcee.logic.InstanciaObjeto;
import com.siagcee.logic.Objeto;
import com.siagcee.logic.UtilidadesVarias;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 05/05/2011
 * Hora: 08:27:25 AM
 */
public class SubirExcel extends HttpServlet {

	private static String TMP_DIR_PATH = "";
	private File tmpDir;
	private File destinationDir;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        HttpSession sesion = request.getSession();
        Connection micon = (Connection) getServletContext().getAttribute("conexion");
        Administrador admin = (Administrador) sesion.getAttribute("administrador");
        //ir a instrumentos publicados como siempre
        //RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");

        if(admin != null){
            try {
                TMP_DIR_PATH = ((File)getServletContext().getAttribute("javax.servlet.context.tempdir")).toString();
                tmpDir = new File(TMP_DIR_PATH);
                if(!tmpDir.isDirectory()) {
                    throw new ServletException(TMP_DIR_PATH + " no es un directorio");
                }

                String realPath = getServletContext().getRealPath("/")+"comunes/subidos/";
                destinationDir = new File(realPath);
                if(!destinationDir.isDirectory()) {
                    throw new ServletException("comunes/subidos/ no es un directorio");
                }

                DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
                fileItemFactory.setSizeThreshold(10*1024*1024); //10 MB
                fileItemFactory.setRepository(tmpDir);
                File file = null;

                ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
                try {
                    List items = uploadHandler.parseRequest(request);
                    Iterator itr = items.iterator();
                    while(itr.hasNext()){
                        FileItem item = (FileItem) itr.next();
                        /*
                         * Handle Form Fields.
                         */
                        if(!item.isFormField()){
                            //Handle Uploaded files.
                            String _tmpName = "";
                            _tmpName = String.valueOf(Math.random());
                            _tmpName = _tmpName.replace('.', 'd');
                            _tmpName = _tmpName.replace('0', 'i');
                            file = new File(destinationDir,_tmpName);
                            item.write(file);
                        }else{
                            InstanciaObjeto _idObj = null;
                            if(item.getFieldName().equals("idobjetodestino")){
                                request.setAttribute("idobjetodestino", item.getString());
                                _idObj = new InstanciaObjeto(admin, micon, Integer.parseInt(item.getString()));
                                if(_idObj != null && _idObj.getId() > 0){
                                    request.setAttribute("highlight", Integer.valueOf(item.getString()));
                                    request.setAttribute("mensaje", "<span style='color:green'>Datos cargados en el instrumento.</span>");
                                    Vector _objetos = Objeto.todosObjetos(admin, micon, 0, true, false);
                                    Vector _instanciados = InstanciaObjeto.todosObjetosInstanciados(admin, micon);

                                    request.setAttribute("objetosDisponibles", _objetos);
                                    request.setAttribute("objetosInstanciados", _instanciados);

                                    view = request.getRequestDispatcher("WEB-INF/vistas/admininsobj.jsp");
                                    //lo cargo
                                    if(!UtilidadesVarias.cargaExcel(file, _idObj, admin, micon)){
                                        request.setAttribute("mensaje", "<span style='color:red'>Error cargando datos en el instrumento.</span>");
                                        view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");
                                    }
                                }else{
                                    request.setAttribute("mensaje", "<span style='color:red'>Error guardando datos en el instrumento.</span>");
                                    view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");
                                }
                            }
                        }
                    }

                }catch(FileUploadException ex){
                    request.setAttribute("mensaje", "<span style='color:red'>Error cargando el archivo.</span>");
                    view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");
                    ex.printStackTrace();
                } catch(Exception ex) {
                    request.setAttribute("mensaje", "<span style='color:red'>Error cargando el archivo</span>");
                    view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");
                    ex.printStackTrace();
                }

                //finalmente lo borro si se puede
                try{
                    if(file.exists() && file.canWrite()){
                        file.delete();
                    }
                }catch (Exception bor){
                    bor.printStackTrace();
                }

                request.setAttribute("loaded", true);

            }catch(Exception e){
                e.printStackTrace();
                request.setAttribute("mensaje", "<span style='color:red'>Error cargando el archivo...</span>");
                view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");
            }finally {
                view.forward(request, response);
            }
        }else{
            //sesion no iniciada
            view = request.getRequestDispatcher("autenticar.do");
            view.forward(request, response);
        }
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF8");
        HttpSession sesion = request.getSession();
        Connection micon = (Connection) getServletContext().getAttribute("conexion");
        Administrador admin = (Administrador) sesion.getAttribute("administrador");
        RequestDispatcher view = request.getRequestDispatcher("autenticar.do");
        if(admin != null){
            try {
                request.setAttribute("idobjetodestino", request.getParameter("objetoatrabajar"));
                view = request.getRequestDispatcher("WEB-INF/vistas/cargaexcel.jsp");

            }catch(Exception e){
                //error voy a pantalla principal de objetos
                view = request.getRequestDispatcher("autenticar.do");
            }finally {
                view.forward(request, response);
            }
        }else{
            //sesion no iniciada
            view = request.getRequestDispatcher("autenticar.do");
            view.forward(request, response);
        }
	}
}