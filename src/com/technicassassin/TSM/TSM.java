package com.technicassassin.TSM;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.technicassassin.TSM.Analytics.Analytics;
import com.technicassassin.TSM.Assist.Assist;
import com.technicassassin.TSM.Data.ConfigHandler;
import com.technicassassin.TSM.Data.MainSQLHandler;
import com.technicassassin.TSM.Punishment.Punishment;
import com.technicassassin.Tasks.PunishmentTracker;
import com.technicassassin.Tasks.UpdateOnlineTime;

public final class TSM extends JavaPlugin {
	
	static public HashMap<String, TSMPlayer> players = new HashMap<String, TSMPlayer>();
	static public HashMap<String, TSMStaff> staff = new HashMap<String, TSMStaff>();
	
	public final Analytics analytics = new Analytics();
	public final Assist assist = new Assist();
	public final Punishment punish = new Punishment();
	public final ConfigHandler config = new ConfigHandler(this);
	public final Util util = new Util();
	
	public final MainSQLHandler sql = new MainSQLHandler(config.sqladdress, config.sqlport, 
			config.sqlusername, config.sqlpassword, config.sqldbname, this);
	
	public final String version = this.getDescription().getVersion();
	
	BukkitTask onlinetime;
	BukkitTask punishment;
	
	@Override
    public void onEnable(){
		
		System.out.println("---------------   Total Server Management   ---------------");
		
		System.out.println("---------------       Version: " + version + "        ---------------");
		
		if(!config.loadConfig()){
			
			this.getLogger().severe("----------------------------------------------------------");
			this.getLogger().severe("---------------   CONFIG FAILED TO LOAD    ---------------");
			this.getLogger().severe("----------------------------------------------------------");
		}
		
		onlinetime = new UpdateOnlineTime().runTaskTimerAsynchronously(this, 1200, 1200);
		punishment = new PunishmentTracker().runTaskTimerAsynchronously(this, 1200, 1200);
	}
		
    @Override
    public void onDisable() {
        
    	onlinetime.cancel();
    	punishment.cancel();
    	
    	System.out.println("---------------   Total Server Management   ---------------");
    	System.out.println("---------------       Version: " + version + "        ---------------");
    	System.out.println("---------------       PLUGIN DISABLED       ---------------");
    }
}