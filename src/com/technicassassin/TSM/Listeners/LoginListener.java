package com.technicassassin.TSM.Listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMPlayer;
import com.technicassassin.TSM.TSMStaff;
import com.technicassassin.TSM.Tasks.LoadPlayerData;

public class LoginListener implements Listener{
	
	TSM plugin;
	
	public LoginListener(TSM pl){
		
		plugin = pl;
	}
	
	public void onLogin(PlayerLoginEvent e){
		
		
		
		String pname = e.getPlayer().getName();
		
		if(e.getPlayer().hasPermission("TSM.Staff")){
			
			TSM.staff.put(pname, new TSMStaff(pname, plugin));
			
			new LoadPlayerData(this.plugin, TSM.players.get(pname)).runTaskAsynchronously(this.plugin);
			
		}else{
			
			TSM.players.put(pname, new TSMPlayer(pname, plugin));
			
			new LoadPlayerData(this.plugin, TSM.staff.get(pname)).runTaskAsynchronously(this.plugin);
		}
	}
}
