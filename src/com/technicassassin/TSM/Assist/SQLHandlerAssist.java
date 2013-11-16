package com.technicassassin.TSM.Assist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.Data.MainSQLHandler;

public class SQLHandlerAssist extends MainSQLHandler{
	
	TSM pl;
	
	String tablename = "assistdata";
	
	public String columns = "'EventID' int NOT NULL," +
							"'EventType' varchar(16) NOT NULL," +
							"'Player' varchar(16) NOT NULL," +
							"'Rank' varchar(16) NOT NULL," +
							"'Server' varchar(16) NOT NULL," +
							"'Status' " +
							"'TimeStamp' datetime DEFAULT NOW()," +
							"UNIQUE (EventID)," +
							"PRIMARY KEY (EventID);";
	
	public SQLHandlerAssist(String add, String po, String u, String pa, String dname,
			TSM plugin) {
		
		super(add, po, u, pa, dname, plugin);
		createTable(columns, tablename);
		pl = plugin;
	}
	
	/*
	 * Grabs all the data about a player from the Assist table
	 * and returns it in a form that can be sent to the requestor.
	 */
	
	public List<String[]> getPlayerDataAssist(String pname){
		
		List<String[]> data = new ArrayList<String[]>();
		
		if(!checkConnection()){
			return null;
		}
		
		try {
			
			PreparedStatement getdata = con.prepareStatement("SELECT * FROM " + tablename + " WHERE Player= "
					+ pname + ";" );
			
			ResultSet punishdata = getdata.executeQuery();
			
			while (punishdata.next()){
				
				String[] event = new String[7];
				
				event[0] = (Integer.toString(punishdata.getInt("EventID")));
				event[1] = (Integer.toString(punishdata.getInt("EventType")));
				event[2] = (punishdata.getString("Player"));
				event[3] = (punishdata.getString("Rank"));
				event[4] = (punishdata.getString("Server"));
				event[5] = (punishdata.getString("TimeStamp"));
				
				data.add(event);
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
}
