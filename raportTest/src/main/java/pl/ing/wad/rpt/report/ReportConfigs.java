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
        try
        {
            configLoader.loadConfigFile(filePath);
            CONNECTION_STRING = configLoader.getElementValue("dburl");
            //System.out.println(CONNECTION_STRING);
            System.out.println("Konfiguracja zaladowana===============");
            System.out.println("Ladowanie wartosci statycznych...");
        }
        catch (Exception ex)
        {
            throw new Exception("Blad ladowania konfiguracji: "  + ex.toString());
        }
        try
        {
            ReportStaticValues reportStaticValues = ReportStaticValues.getInstance();
            reportStaticValues.load_app_parameters();
            System.out.println("Ladowanie wartosci statycznych...OK");
        }
        catch (Exception ex)
        {
            System.out.println("Blad ladowania wartosci statycznych:\n" + ex.toString());
        }
    }

    public static String getCONNECTION_STRING() {
        return CONNECTION_STRING;
    }
}
