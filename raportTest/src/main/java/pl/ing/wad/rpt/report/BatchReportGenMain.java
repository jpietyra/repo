package pl.ing.wad.rpt.report;

import pl.ing.wad.rpt.report.service.DeleteService;
import pl.ing.wad.rpt.report.service.RptService;

public class BatchReportGenMain {

	public static void main(String[] args) {

		try {

			if (args.length != 1) {
				throw new Exception("B³êdna iloœæ argumentów wywo³ania apliakcji!");
			}

			new ReportConfigs().loadConfiguration(args[0]);
			System.out.println("Startuje serwis batchowy kasowania raportow RPT");
			ReportStaticValues reportStaticValues = ReportStaticValues.getInstance();
			System.setProperty("java.security.auth.login.config", reportStaticValues.getAppParmValue("JaasFilePath"));
			new DeleteService().startService();
			System.out.println("Startuje serwis batchowy raportow RPT");
			new RptService().startService();
			System.out.println("Serwis batchowy raportow RPT zatrzymany...CU later :]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
