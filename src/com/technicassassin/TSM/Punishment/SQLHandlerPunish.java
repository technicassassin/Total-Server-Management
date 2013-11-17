package com.technicassassin.TSM.Punishment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.Data.MainSQLHandler;

public class SQLHandlerPunish extends MainSQLHandler {
	
	TSM pl;
	
	final String tablename = "punishdata";
	
	public String columns = "'EventID' int NOT NULL," +
							"'Offender' varchar(16) NOT NULL," +
							"'Punishment' varchar(16)," +
							"'Length' int," +
							"'Description' text," +
							"'TimeServed' int" +
							"'Enforcer' varchar(16) NOT NULL," +
							"'TimeStamp' varchar(15)" +
							"UNIQUE (EventID)," +
							"PRIMARY KEY (EventID);";
	
	public SQLHandlerPunish
	(
			String add, 
			String po, 
			String u, 
			String pa, 
			String dname,
			TSM plugin
	) 
	{
		super(add, po, u, pa, dname, plugin);
		createTable(columns, tablename);
		pl = plugin;
	}
	
	/*
	 * Grabs all the data about a player from the Punish table
	 * and returns it in a form that can be sent to the requestor.
	 */
	
	public List<String[]> getPlayerDataPunish(String pname){
		
		List<String[]> data = new ArrayList<String[]>();
		
		if(!checkConnection()){
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			return data;
		}
		
		try {
			
			PreparedStatement getdata = con.prepareStatement("SELECT * FROM " + tablename + " WHERE Offender= "
					+ pname );
			
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
				
				data.add(event);
			}
			
			return data;
			
		} catch (SQLException e) {
			
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			e.printStackTrace();
			return data;
			
		} 
		
		
	}

	/*
	 * Adds a punishment record to someone who is offline.
	 * Returns true if the record was successfully created.
	 */

	public boolean addOfflinePlayerRecordPunish
	(
			String offender, 
			String punishment, 
			int length,
			String description,
			String enforcer
	)
	{
		
		if(!checkConnection()){
			
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			return false;
		}
		
		/*
		 * TODO:	NEED TO CHECK IF OFFENDER 
		 * 			HAS LOGGED ONTO THE SERVER
		 * 			IN THE LAST SIX MONTHS
		 */
		
		try{
			
			PreparedStatement addRecord = con.prepareStatement
					(
							"INSERT INTO " + tablename + "(Offender," +
							"Punishment,Length,Description,Enforcer " +
							"VALUES" + "(" + offender + "," +
							punishment + "," + length + "," +
							description + "," + enforcer + ");"
					);
			
			addRecord.execute();
			
		}catch(SQLException e){
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * Add a punishment record to a kicked player.
	 * Returns true if the record was successfully created.
	 */
	
	public boolean addKickRecord
	(
			String offender,
			String description,
			String enforcer
	){
		
		if(!checkConnection()){
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			return false;
		}
		
		try{
			
			PreparedStatement addRecord = con.prepareStatement
					(
							"INSERT INTO " + tablename + "(Offender," +
							"Punishment,Description,Enforcer " +
							"VALUES" + "(" + offender + "," +
							"kick" + "," + description + 
							"," + enforcer + ");"
					);
		
		}catch(SQLException e){
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	public boolean recordPunishment
	(
			String offender, 
			String punishment, 
			int length, 
			String description,
			int timeserved,
			String enforcer,
			String timestamp
	)
	{
		
		if(!checkConnection()){
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			return false;
		}
		
		try {
			
			con.prepareStatement
					(
							"INSERT INTO " + tablename + " (offender, punishment, length, " +
							"description, timeserved, enforcer, timestamp) VALUES (" + offender
							+ ", " + punishment + ", " + length + ", " + description + ", " +
							timeserved + ", " + enforcer + ", " + timestamp + ");"
					).execute();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
