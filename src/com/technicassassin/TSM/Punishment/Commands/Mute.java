package com.technicassassin.TSM.Punishment.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMCommand;
import com.technicassassin.TSM.Data.ConfigHandler;
import com.technicassassin.TSM.Tasks.RecordPunishment;

public class Mute extends TSMCommand{
	
	public Mute(TSM pl) {
		super(pl);
	}
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		//Check input
		try{
			Integer.parseInt(args[2]);
			
			if(args.length < 2){
				
				sender.sendMessage("Usage: /mute [Offender] [length in minutes] {reason}");
			}
			
		}catch(Exception e){
			sender.sendMessage("Usage: /mute [Offender] [length in minutes] {reason}");
			return;
		}
		
		String reason = "";
		
		if(args.length > 2){
			
			reason = getReason(3, args);
		}
		
		Player p = Bukkit.getServer().getPlayer(args[0]);
		
		if(!p.isOnline() || p == null){
			
			sender.sendMessage("Player not online, attempting to  mute offline player...");
			
			new RecordPunishment
			(
					p.getName(),
					"mute",
					Integer.parseInt(args[1]),
					getReason(2,args),
					0,
					sender.getName(),
					plugin.util.getTimeStamp()
					
			).runTaskAsynchronously(plugin);
			
			return;
		}
		
		p.sendMessage(ConfigHandler.messages.get("mute") + " " + reason);
		
		TSM.players.get(args[0]).cantalk = false;
	}
}
