package com.technicassassin.TSM.Punishment.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMCommand;
import com.technicassassin.TSM.TSMPlayer;
import com.technicassassin.TSM.Data.ConfigHandler;
import com.technicassassin.Tasks.AddOfflinePlayerRecord;

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
			
			BukkitTask task = new AddOfflinePlayerRecord
				(
						plugin, 
						args[0],
						Integer.parseInt(args[2]),
						(Player)sender, 
						"mute",
						reason
						
				).runTaskAsynchronously(plugin);
			
			return;
		}
		
		p.sendMessage(ConfigHandler.messages.get("mute") + " " + reason);
		
		TSM.players.get(args[0]).cantalk = false;
	}
}
