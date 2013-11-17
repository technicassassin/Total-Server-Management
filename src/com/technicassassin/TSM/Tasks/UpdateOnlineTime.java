package com.technicassassin.TSM.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMTask;

public class UpdateOnlineTime extends TSMTask{

	@Override
	public void run() {
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			
			TSM.players.get(p.getName()).minutesonline++;
		}
	}
}
