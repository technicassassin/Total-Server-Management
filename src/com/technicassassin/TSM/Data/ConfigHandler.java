package com.technicassassin.TSM.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.technicassassin.TSM.TSM;

public class ConfigHandler {
	
	private TSM pl;
	
	public ConfigHandler(TSM plugin) {
		this.pl = plugin;
	}
	
	public File configfile;
	public YamlConfiguration config;
	
	public String sqladdress;
	public String sqlport;
	public String sqlusername;
	public String sqlpassword;
	public String sqldbname;
	
	public static HashMap<String, String> messages = new HashMap<String, String>();
	
	/*
	 * Creates configuration object and loads
	 * config data.
	 * returns true if this was successful.
	 */
	
	public boolean loadConfig(){
		
		config = new YamlConfiguration();
		try {
			configfile = new File(pl.getDataFolder().getAbsolutePath() + "/config.yml");
			config.load(configfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return false;
		}
		
		loadMessages();
		setupSQL();
		
		return true;
	}
	
	/*
	 * Loads config specified messages.
	 */
	
	public void loadMessages(){
		
		messages.put("ban","messages.ban");
		messages.put("fine", "messages.fine");
		messages.put("jail", "messages.jail");
		messages.put("kick", "messages.kick");
		messages.put("maze", "messages.maze");
		messages.put("mute", "messages.mute");
		messages.put("tempban", "messages.tempban");
		messages.put("warn", "messages.warn");
		messages.put("affect", "messages.affect");
		
	}
	
	/*
	 * Loads config specified sql data.
	 */
	
	public void setupSQL(){
		
		sqladdress = config.getString("SQL.address");
		sqlport = config.getString("SQL.Port");
		sqlusername = config.getString("SQL.Username");
		sqlpassword = config.getString("SQL.Password");
		sqldbname = config.getString("SQL.databasename");
	}
}
