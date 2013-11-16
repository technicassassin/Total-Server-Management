package com.technicassassin.TSM.Listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.technicassassin.TSM.TSM;

public class LogoutListener implements Listener {
	
	TSM plugin;
	
	public LogoutListener(TSM pl){
		
		plugin = pl;
	}
	
	public void onLogout(PlayerQuitEvent e){
		
		TSM.players.remove(e.getPlayer().getName());
		
	}
}
