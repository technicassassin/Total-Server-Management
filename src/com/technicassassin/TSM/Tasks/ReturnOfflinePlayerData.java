package com.technicassassin.TSM.Tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMTask;
import com.technicassassin.TSM.Data.Table;

public class ReturnOfflinePlayerData extends TSMTask  {
	
	volatile List<String[]> data = new ArrayList<String[]>();
	
	volatile String target;
	volatile Player requestor;
	volatile String type;
	
	public ReturnOfflinePlayerData
	(
			String target,
			Player requestor, 
			String type
	)
	{	
		this.target = target;
		this.requestor = requestor;
		this.type = type;
	}

	@Override
	public void run() {
		
		switch(type){
		
		case "punish":
			
			data = sqlpunish.getPlayerDataPunish(target);
			
			checkData(data);
			
			String[] columnnames = new String[6];
			
			columnnames[0] = "Event ID";
			columnnames[1] = "Punishment";
			columnnames[2] = "Length";
			columnnames[3] = "Enforcer";
			columnnames[4] = "Time Served";
			columnnames[5] = "Timestamp";
			
			requestor.sendMessage(pl.util.tableFactory(new Table(columnnames, data), 12));
		}
	}
	
	public void checkData(List<String[]> data){
		
		if(data.get(0) == null){
			requestor.sendMessage("No records for the requested player.");
		}
	}
}