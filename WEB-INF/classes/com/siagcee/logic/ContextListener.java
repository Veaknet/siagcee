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

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class ContextListener implements ServletContextListener{

	Connection _cnn = null;

	public void contextInitialized(ServletContextEvent evento){
		ServletContext sc = evento.getServletContext();
		Connection micon = this.obtenerConexion();
		sc.setAttribute("conexion", micon);
	}

	public Connection obtenerConexion() {
		try {
			String URL_DB = "jdbc:postgresql://127.0.0.1:5432/db_siagcee";
			Class.forName("org.postgresql.Driver");
			_cnn = DriverManager.getConnection(URL_DB,"usr_siagcee","siagcee");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _cnn;
	}

	public void contextDestroyed(ServletContextEvent evento){
		try {
			_cnn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}