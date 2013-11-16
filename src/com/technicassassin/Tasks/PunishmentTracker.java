package com.technicassassin.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMTask;

public class PunishmentTracker extends TSMTask {

	@Override
	public void run() {
		
		/*
		 * For the players online that have been punished
		 * check if they should still be jailed if they
		 * shouldn't tp them to spawn and remove them from
		 * the hashmap.
		 */
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			
			if(TSM.players.get(p.getName()).timedpunishment){
				
				
			}
		}
	}
}
