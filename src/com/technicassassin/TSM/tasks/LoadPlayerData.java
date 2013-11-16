package com.technicassassin.TSM.tasks;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMPlayer;
import com.technicassassin.TSM.TSMTask;

import org.bukkit.scheduler.BukkitRunnable;

public class LoadPlayerData extends TSMTask {
	
	volatile TSMPlayer p;
	
	public LoadPlayerData(TSM plugin, TSMPlayer player){
		
		this.pl = plugin;
		this.p = player;
	}

	@Override
	public void run() {
		
		p.loadData();
	}

}
