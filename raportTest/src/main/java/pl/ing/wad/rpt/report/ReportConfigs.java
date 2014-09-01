package pl.ing.wad.rpt.report;

import pl.ing.wad.rpt.report.tools.ConfigLoader;

public class ReportConfigs {

    private static String CONNECTION_STRING = "";
    private static ReportConfigs instance = null;
    private ConfigLoader configLoader = new ConfigLoader();

    public static ReportConfigs getInstance() {
        if (instance == null) {
            instance = new ReportConfigs();
        }
        return instance;
    }

    public void loadConfiguration(String filePath) throws Exception
    {
    	System.out.println("Ladowanie konfiguracji...");
    	
        try
        {
            configLoader.loadConfigFile(filePath);
            CONNECTION_STRING = configLoader.getElementValue("dburl");
            System.out.println("Ladowanie konfiguracji...OK");
        }
        catch (Exception ex)
        {
            throw new Exception("Blad ladowania konfiguracji: "  + ex.toString());
        }
        
        System.out.println("Ladowanie wartosci statycznych...");
        
        try
        {
            ReportStaticValues reportStaticValues = ReportStaticValues.getInstance();
            reportStaticValues.loadAppParameters();
            System.out.println("Ladowanie wartosci statycznych...OK");
        }
        catch (Exception ex)
        {
        	throw new Exception("Blad ladowania wartosci statycznych:\n" + ex.toString());
        }
    }

    public static String getCONNECTION_STRING() {
        return CONNECTION_STRING;
    }
}
