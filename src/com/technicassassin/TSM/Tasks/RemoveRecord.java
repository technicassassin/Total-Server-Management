package com.technicassassin.TSM.Tasks;

import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSMTask;

public class RemoveRecord extends TSMTask{
	
	Player requestor;
	int id;
	
	public RemoveRecord
	(
			int id,
			Player requestor
	)
	{	
		this.id = id;
		this.requestor = requestor;
	}
	
	@Override
	public void run() {
		
		if(pl.sql.removeRecord(id, "punishdata")){
			
			requestor.sendMessage("The record was successfully removed.");
			
		}else{
			
			requestor.sendMessage("Internal Error.");
		}
	}
}
