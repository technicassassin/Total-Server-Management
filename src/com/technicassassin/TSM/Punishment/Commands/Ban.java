package com.technicassassin.TSM.Punishment.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMCommand;
import com.technicassassin.TSM.Tasks.RecordPunishment;

public class Ban extends TSMCommand{

	public Ban(TSM pl) {
		super(pl);
	}

	public void onCommand(CommandSender sender, Command cmd, String label, String[] args){
			
		@SuppressWarnings("unused")
		boolean reasonset = false;
		Player offender = Bukkit.getServer().getPlayer(args[0]);
		
		/*
		 * TODO: Check if player has ever joined the server.
		 */
		
		if(offender == null){
			
			sender.sendMessage("This player has never joined the server.");
			return;
		}
		
		if(offender.isOnline()){
			
			if(args.length < 2){
				
				offender.kickPlayer("You have been banned from the server by " + 
						sender.getName() + ", Attempt to log in later to see the reason" +
						" you were banned.");
				
			}else{
				
				offender.kickPlayer("You have been banned from the server by " + 
						sender.getName() + " for " + getReason(1,args));
				
				reasonset = true;
			}
			

		}
		
		new RecordPunishment
		(
				offender.getName(),
				"ban",
				-1,
				getReason(1,args),
				0,
				sender.getName(),
				plugin.util.getTimeStamp()
				
		).runTaskAsynchronously(plugin);
		
		/*
		 * TODO:
		 * If staff member has not set a reason
		 * create a task which reminds them to
		 * set one
		 */
		
		sender.sendMessage(args[0] + " banned.");
	}
}
