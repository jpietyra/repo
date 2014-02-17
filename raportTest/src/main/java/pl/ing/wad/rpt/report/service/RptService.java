package pl.ing.wad.rpt.report.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import pl.ing.wad.rpt.report.ReportStaticValues;
import pl.ing.wad.rpt.report.dao.ReportBaseDAO;

public class RptService extends ReportBaseDAO {

	ReportStaticValues reportStaticValues = ReportStaticValues
			.getInstance();

	public RptService() {
		super();
	}

	public void startService() throws Exception {

		String reportSql = "";
		Number orderId = 0;
		
		//na potrzeby testow
		{
			Connection connection = getConnection();
			Statement st = connection.createStatement();
			st.executeUpdate("update RPT_ORDERED SET ORD_OST_ID = 1");
		}
		
		while (reportStaticValues.isCanRun()) {

			Thread.sleep(2000);
			
			try {
				Connection connection = getConnection();
				Statement st = connection.createStatement();
				System.out.println("Proba pobrania zamowienia z bazy danych.");
				
				//ResultSet rs = st.executeQuery("select * from RPT_ORDERED where rownum = 1");//pobieramy jeden wiersz do przetworzenia
				ResultSet rs = st.executeQuery("select * from RPT_ORDERED where ORD_OST_ID = 1 and rownum = 1");//pobieramy jeden wiersz do przetworzenia
				if(rs != null || rs.next()) {
					reportSql = rs.getString("ORD_QUERY_LONG");
					orderId = rs.getInt("ORD_ID");
					String header = rs.getString("ORD_HEADER");
					String generator = rs.getString("ORD_GENERATOR");
					System.out.println("Przetwarzeanie zamowienia o id = " + orderId.toString());
					st.executeUpdate("update RPT_ORDERED SET ORD_OST_ID = 4 WHERE ORD_ID = "  + orderId);
					//st.executeUpdate("update RPT_ORDERED SET ORD_OST_ID = 4 WHERE ORD_ID = "  + orderId);
					connection.commit();
					if("DEFAULT".equals(generator) || generator == null ){
					new RptWorker().generateReport(reportSql,orderId, header);
					}
					else if("XFILES".equals(generator)){
						new RptWorkerXfiles().generateReport(reportSql,orderId, header);
					}
				} else {
					System.out.println("Brak zamowien...zamykam serwis batchowy");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
