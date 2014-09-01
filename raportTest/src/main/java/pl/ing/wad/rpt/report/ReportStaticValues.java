package pl.ing.wad.rpt.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import pl.ing.wad.rpt.report.dao.ReportBaseDAO;

public class ReportStaticValues extends ReportBaseDAO {

    private static ReportStaticValues instance = null;
    private static HashMap<String, String> PARMS_MAP = new HashMap<String, String>();
    private boolean canRun = true;

    public boolean isCanRun() {
		return canRun;
	}

	public void setCanRun(boolean canRun) {
		this.canRun = canRun;
	}

	@Override
    protected Connection getConnection() throws Exception {
        return super.getConnection();
    }

    public static ReportStaticValues getInstance() {
        if (instance == null) {
            instance = new ReportStaticValues();
        }
        return instance;
    }

    public String getAppParmValue(String mapKey) {
        try {
        	return PARMS_MAP.get(mapKey).toString();
        }
        catch (Exception ex) {
            return "b/d";
        }
    }

    public void loadAppParameters() throws Exception {

        try(Connection conn = getConnection();
        		Statement statement = conn.createStatement(); 
        		ResultSet rs =statement.executeQuery("SELECT PRM_NAME, PRM_VALUE FROM RPT_PARAMETERS")) {

        	if( rs != null){
	            while (rs.next()) {
	                PARMS_MAP.put(rs.getString("PRM_NAME"), rs.getString("PRM_VALUE"));
	            }
        	}
        } catch (Exception ex) {
            throw ex;
        } 
    }
}
