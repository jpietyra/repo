/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ing.wad.rpt.report.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import pl.ing.wad.rpt.report.ReportConfigs;

public class ReportBaseDAO {

	protected Connection connection = null;
	private static String CONNECTION_STRING = "";
	ReportConfigs reportConfigs = ReportConfigs.getInstance();

	public ReportBaseDAO() {
		super();
		CONNECTION_STRING = ReportConfigs.getCONNECTION_STRING();
	}

	protected Connection getConnection() throws Exception {
		openConnection();
		return connection;
	}

	protected void openConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection(CONNECTION_STRING);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new Exception("Blad otwarcia polaczenia:" + ex.toString());
			}
		}
		return;
	}
}