package com.technicassassin.TSM;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.technicassassin.TSM.Analytics.SQLHandlerAnalytics;
import com.technicassassin.TSM.Assist.SQLHandlerAssist;
import com.technicassassin.TSM.Data.Table;
import com.technicassassin.TSM.Punishment.SQLHandlerPunish;
import com.technicassassin.TSM.Tasks.LoadOfflinePlayerData;

public class TSMPlayer {
	
	TSM plugin;
	
	SQLHandlerPunish sqlpunish;
	SQLHandlerAssist sqlassist;
	SQLHandlerAnalytics sqlanalytics;
	
	public volatile List<String[]> punishdata = new ArrayList<String[]>();
	public volatile List<String[]> analyticsdata = new ArrayList<String[]>();
	public volatile List<String[]> assistdata = new ArrayList<String[]>();

	public Player player;
	String pname;
	
	public boolean cantalk;
	public int minutesonline;
	public boolean timedpunishment;
	
	public TSMPlayer(String name, TSM pl){
		
		plugin = pl;
		
		player = Bukkit.getServer().getPlayer(name);
		pname = player.getName();
	}
	
	/*
	 * Loads all the data held about the player from
	 * the sql table to the object for quick access.
	 */
	
	public void loadData(){
		
		punishdata = sqlpunish.getPlayerDataPunish(pname);
		analyticsdata = sqlanalytics.getPlayerDataAnalytics(pname);
		assistdata = sqlassist.getPlayerDataAssist(pname);
		
	}
	
	/*
	 * Sends all data about a player in the specified table to the
	 * requestor.
	 */
	
	public void sendPlayerData(String targetstr, Player requestor, String type){
		
		Player target = Bukkit.getServer().getPlayer(targetstr);
		
		if(target == null){
			
			requestor.sendMessage("Player not online, Loading offline player data...");
			
			new LoadOfflinePlayerData
			(
					plugin, 
					targetstr, 
					requestor, 
					type
					
			).runTaskAsynchronously
			(
					plugin
			);
			
			
		} else {
			
			String[] columnnames = new String[6];
			
			columnnames[0] = "Event ID";
			columnnames[1] = "Punishment";
			columnnames[2] = "Length";
			columnnames[3] = "Enforcer";
			columnnames[4] = "Time Served";
			columnnames[5] = "Timestamp";
			
			requestor.sendMessage(plugin.util.tableFactory(new Table(columnnames, punishdata), 12));
		}
	}

}
