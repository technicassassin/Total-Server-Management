package com.technicassassin.TSM.Data;

import java.sql.*;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.Analytics.SQLHandlerAnalytics;
import com.technicassassin.TSM.Assist.SQLHandlerAssist;
import com.technicassassin.TSM.Punishment.Punishment;
import com.technicassassin.TSM.Punishment.SQLHandlerPunish;

@SuppressWarnings("unused")
public class MainSQLHandler {
	
	public TSM pl;
	public SQLHandlerPunish sqlpunish;
	public SQLHandlerAssist sqlassist;
	public SQLHandlerAnalytics sqlanalytics;
	
	public String address;
	public String port;
	public String user;
	public String pass;
	public String databasename;
	public Connection con;
	
	public MainSQLHandler (String add, String po, String u, String pa, String dname, TSM plugin){
		
		pl = plugin;
		
		add = address;
		po = port;
		u = user;
		pa = pass;
		dname = databasename;
		
		sqlpunish = new SQLHandlerPunish(address, port, user, pass, databasename, pl);
		sqlassist = new SQLHandlerAssist(address, port, user, pass, databasename, pl);
		sqlanalytics = new SQLHandlerAnalytics(address, port, user, pass, databasename, pl);
		
		try {
			
			if (!con.isValid(5)) {
				
				establishConnection();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	/*
	 * Attempts to create a connection to the SQL database
	 * returns true if the connection was established successfully.
	 */
	
	public boolean establishConnection(){
		
		System.out.println("----------------------------------------------------------");
		System.out.println("---  Attempting to establish  the database connection  ---");
		System.out.println("----------------------------------------------------------");
		
		for (int n = 1; n == 11; n++) {
		
			try {
	
				Class.forName("com.mysql.jdbc.Driver");
				String connection = "jdbc:mysql://" + address + ":" + port + "/" + databasename;
				con = DriverManager.getConnection(connection, user, pass);
				
				if (con.isValid(5)){
					return true;
				}
				
			} catch (ClassNotFoundException e) {
				
				pl.getLogger().severe("----------------------------------------------------------");
				pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
				pl.getLogger().severe("----------------------------------------------------------");
				e.printStackTrace();
				return false;
				
			} catch (SQLException e) {

				pl.getLogger().severe("----------------------------------------------------------");
				pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
				pl.getLogger().severe("----------------------------------------------------------");
				e.printStackTrace();
				return false;
			}
			
			System.out.println("----------------------------------------------------------");
			System.out.println("---------------      Attempt " + n + " failed      ---------------");
			System.out.println("----------------------------------------------------------");
		}
		
		try {
			
			if (con.isValid(5)){
				
				pl.getLogger().severe("----------------------------------------------------------");
				pl.getLogger().severe("---------------     SQL CONNECT FAILED     ---------------");
				pl.getLogger().severe("----------------------------------------------------------");
				return false;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * Returns true if there is a valid connection
	 * to the sql database.
	 */
	
	public boolean checkConnection(){
		
		try {
			
			if ((con == null) || !(con.isValid(2))){
				
				if (!establishConnection()){
					
					return false;
				}
			}
			
		} catch (SQLException e) {
			
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------         SQL  ERROR         ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/*
	 * Creates table with the specified column headings
	 * and the specified name.
	 * Returns true if the table was created successfully.
	 */
	
	public boolean createTable(String columns, String tablename){
		
		if(!checkConnection()){
			return false;
		}
		
		try {
			
			PreparedStatement createtable = con.prepareStatement("CREATE TABLE " + 
							 tablename + " (" + columns + ");");
			
			if (!createtable.execute()){
				
				pl.getLogger().severe("----------------------------------------------------------");
				pl.getLogger().severe("---------------    ERROR CREATING TABLE    ---------------");
				pl.getLogger().severe("----------------------------------------------------------");
			}
			
			
		} catch (SQLException e) {
			
			pl.getLogger().severe("----------------------------------------------------------");
			pl.getLogger().severe("---------------    ERROR CREATING TABLE    ---------------");
			pl.getLogger().severe("----------------------------------------------------------");
			e.printStackTrace();
			
		} 
		
		return true;
	}
	
	/*
	 * Removes record with the specified EventID from the
	 * specified table.
	 * Returns true if the record was removed successfully.
	 */
	
	public boolean removeRecord(int id, String type){
		
		try {
			
			if(!con.prepareStatement("DELETE FROM " + type + 
					" WHERE EventID = " + id).execute()){
				return false;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
