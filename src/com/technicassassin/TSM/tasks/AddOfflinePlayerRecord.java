package com.technicassassin.TSM.tasks;

import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMTask;
import com.technicassassin.TSM.Analytics.SQLHandlerAnalytics;
import com.technicassassin.TSM.Assist.SQLHandlerAssist;
import com.technicassassin.TSM.Punishment.SQLHandlerPunish;

public class AddOfflinePlayerRecord extends TSMTask {
	
	volatile String offender;
	volatile Player enforcer;
	volatile String type;
	volatile int length;
	volatile String description;
	
	public AddOfflinePlayerRecord
	(
			TSM plugin, 
			String offender,
			
			/*
			 * for the purposes of the plugin length
			 * of punishments will be in minutes.
			 */
			int length, 
			
			Player enforcer,
			
			/*
			 * punishment, assistance request or
			 * action recorded by analytics.
			 */
			String type,
			
			/*
			 * Just reason for punishment for now
			 */
			String description
	)
	{
		this.pl = plugin;
		this.offender = offender;
		this.enforcer = enforcer;
		this.type = type;
		this.length = length;
		this.description = description;
	}
	
	@Override
	public void run() {
		
		switch(type){
		
		case"punish":
			
			
			if(!sqlpunish.addOfflinePlayerRecordPunish
			(
					offender, 
					"mute", 
					length, 
					description, 
					enforcer.getName()
			)){
				enforcer.sendMessage("Muting the offline player failed.");
			}
			
		case"analytics":
			
			
			
		case"assist":
			
			
			
		}
		
	}

}
