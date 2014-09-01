package pl.ing.wad.rpt.report.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pl.ing.wad.rpt.report.ReportStaticValues;
import pl.ing.wad.rpt.report.dao.ReportBaseDAO;
import pl.ing.wad.rpt.report.filenet.FileNetCommunication;

public class DeleteService extends ReportBaseDAO {

	ReportStaticValues reportStaticValues = ReportStaticValues.getInstance();

	public DeleteService() {
		super();
	}

	public void startService() throws Exception {

		boolean deleteReports = true;
		
		System.out.println("Start kasowania starych raportow.");
		while (deleteReports) {

			Thread.sleep(2000);

			PreparedStatement st = null;
			ResultSet rs = null;

			try (Connection connection = getConnection()) {

				// pobieramy jeden wiersz do przetworzenia
				st = connection
						.prepareStatement("select ORD_RESULT_PATH from RPT_ORDERED where ORD_OST_ID = 2 and ORD_EXP_DATE < trunc(SYSDATE)  and rownum = 1");

				if (st == null) {
					throw new Exception("Wystapil blad przygotowania zapytania!");
				}

				rs = st.executeQuery();

				if (rs != null && rs.next()) {
					String resultPath = rs.getString("ORD_RESULT_PATH");
					Number orderId = rs.getInt("ORD_ID");
					FileNetCommunication fileNetCommunication = new FileNetCommunication();
					fileNetCommunication.deleteDocument(resultPath);
					System.out.println("Kasowanie zamowienia o id = " + orderId);
					st.executeUpdate("delete from RPT_ORDERED WHERE ORD_ID = " + orderId);
					connection.commit();

				} else {
					System.out.println("Brak raportow do usuniecia ...zamykam serwis batchowy");
					deleteReports = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			}
		}

	}

}
