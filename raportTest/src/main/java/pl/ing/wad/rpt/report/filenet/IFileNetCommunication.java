package pl.ing.wad.rpt.report.filenet;

import java.io.FileInputStream;

public interface IFileNetCommunication {
	
	public static final String DOCUMENT_CLASS_NAME = "";
		
	public String createDocument(String pathToSystemFile) throws Exception;
	
}
