package pl.ing.wad.rpt.report.filenet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import pl.ing.wad.rpt.report.ReportStaticValues;

public class FileNetCommunication implements IFileNetCommunication {
	
	private ContentEngineSession contentEngineSession = null;
	private ReportStaticValues reportStaticValues = ReportStaticValues.getInstance();

	
	public FileNetCommunication() throws Exception {
		contentEngineSession = new ContentEngineSession();
		contentEngineSession.loginCE(reportStaticValues.getAppParmValue("CE_PASS"), 
				reportStaticValues.getAppParmValue("CE_USER"), 
				reportStaticValues.getAppParmValue("WCM_API_CONFIG_FILE_PATH"));
		//TODO implement
	}
	
	public void deleteReport(String id) {
		//TODO implement
	}
	
	public void testFile() throws Exception {
		//TODO test
	
	}
	
	public void deleteDocument(String id) {
		//TODO implement
	}
	
	public String createDocument(String pathToSystemFile) throws Exception {
		return null;
		//TODO implement
	}	
}
