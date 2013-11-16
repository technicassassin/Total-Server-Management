package com.technicassassin.TSM.tasks;

import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSMTask;

public class AddKickRecord extends TSMTask {
	
	volatile String offender;
	volatile String description;
	volatile Player enforcer;
	
	public AddKickRecord(String offender, String description, Player sender){
		
		this.offender = offender;
		this.description = description;
		this.enforcer = sender;
	}
	
	@Override
	public void run() {
		
		if(!sqlpunish.addKickRecord
		(
			offender, 
			description, 
			enforcer.getName()
		)){
			enforcer.sendMessage("Recording the kicking of" +
					" the player failed, the player was still kicked" +
					" from the game.");
		}
		
	}

}