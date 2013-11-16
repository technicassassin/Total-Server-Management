package com.technicassassin.TSM.tasks;

import java.util.ArrayList;
import java.util.List;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMPlayer;
import com.technicassassin.TSM.TSMTask;
import com.technicassassin.TSM.Util;
import com.technicassassin.TSM.Analytics.SQLHandlerAnalytics;
import com.technicassassin.TSM.Assist.SQLHandlerAssist;
import com.technicassassin.TSM.Punishment.SQLHandlerPunish;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LoadOfflinePlayerData extends TSMTask  {
	
	volatile List<String[]> punishdata = new ArrayList<String[]>();
	volatile List<String[]> analyticsdata = new ArrayList<String[]>();
	volatile List<String[]> assistdata = new ArrayList<String[]>();
	
	volatile String target;
	volatile Player requestor;
	volatile String type;
	
	public LoadOfflinePlayerData
	(
			TSM plugin, 
			String target,
			Player requestor, 
			String type
	)
	{	
		this.pl = plugin;
		this.target = target;
		this.requestor = requestor;
		this.type = type;
	}

	@Override
	public void run() {
		
		switch(type){
		
		case "punish":
			
			punishdata = sqlpunish.getPlayerDataPunish(target);
			
			if(punishdata.get(0) == null){
				requestor.sendMessage("No records for the requested player.");
			}
			
			int n = 0;
			
			requestor.sendMessage("--------------------------------------------------------------------------");
			requestor.sendMessage("Event ID    Punishment    Length    Enforcer        Time Served  Timestamp");
			requestor.sendMessage("--------------------------------------------------------------------------");
			
			while(n < punishdata.size()){
				
				String[] event = punishdata.get(n);
				
				requestor.sendMessage(pl.util.pad(event[0], 11) + pl.util.pad(event[1], 11) + pl.util.pad(event[2], 11));
				
				n++;
			}
		}
	}

}
