package com.technicassassin.TSM.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.technicassassin.TSM.TSM;

public class ChatListener implements Listener{
	
	TSM plugin;
	
	public ChatListener(TSM pl){
		
		plugin = pl;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e){
		
		if(!TSM.players.get(e.getPlayer().getName()).cantalk){
			
			e.setCancelled(true);
			e.getPlayer().sendMessage("You have been muted, " +
					"you must wait until you can talk again.");
		}
	}
}
