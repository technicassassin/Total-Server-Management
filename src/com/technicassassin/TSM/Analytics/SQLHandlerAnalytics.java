package com.technicassassin.TSM.Analytics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.Data.MainSQLHandler;

public class SQLHandlerAnalytics extends MainSQLHandler {
	
	TSM pl;
	
	String ptablename = "analyticspdata";
	String stablename = "analyticssdata";
	
	public String pcolumns = "'EventID' int NOT NULL," +
							"'Player' varchar(16) NOT NULL," +
							"UNIQUE (EventID)," +
							"PRIMARY KEY (EventID);";
	
	public String scolumns = "'EventID' int NOT NULL," +
							"'Server' varchar(16) NOT NULL," +
							"UNIQUE (EventID)," +
							"PRIMARY KEY (EventID);";

	public SQLHandlerAnalytics(String add, String po, String u, String pa, String dname,
			TSM plugin) {
		super(add, po, u, pa, dname, plugin);
		createTable(pcolumns, ptablename);
		createTable(scolumns, stablename);
		pl = plugin;
	}
	
	/*
	 * Grabs all the data about a player from the analytics table
	 * and returns it in a form that can be sent to the requestor.
	 */
	
	public List<String[]> getPlayerDataAnalytics(String pname){
		
		List<String[]> data = new ArrayList<String[]>();
		
		if(!checkConnection()){
			return null;
		}
		
		try {
			
			PreparedStatement getdata = con.prepareStatement("SELECT * FROM " + ptablename + " WHERE Player= "
					+ pname + ";");
			
			ResultSet punishdata = getdata.executeQuery();
			
			while (punishdata.next()){
				
				String[] event = new String[7];
				
				event[0] = (Integer.toString(punishdata.getInt("EventID")));
				event[1] = (punishdata.getString("Offender"));
				event[2] = (punishdata.getString("Punishment"));
				event[3] = (Integer.toString(punishdata.getInt("Length")));
				event[4] = (punishdata.getString("Description"));
				event[5] = (Integer.toString(punishdata.getInt("TimeServed")));
				event[6] = (punishdata.getString("Enforcer"));
				event[7] = (punishdata.getString("TimeStamp"));
				
			}
			return data;
			
		} catch (SQLException e) {
			
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			e.printStackTrace();
			
			return null;
		}
	}
	
	public void addEvent(){
		
	}
}
