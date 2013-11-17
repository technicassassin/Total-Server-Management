package com.technicassassin.TSM.Punishment.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMCommand;
import com.technicassassin.TSM.Data.ConfigHandler;
import com.technicassassin.TSM.Tasks.RecordPunishment;

public class Kick extends TSMCommand{
	
	public Kick(TSM pl) {
		super(pl);
	}
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(args.length < 2){
			sender.sendMessage("You must supply a reason when kicking someone.");
			return;
		}
		
		Player p = Bukkit.getServer().getPlayer(args[0]);
		
		if(!p.isOnline() || p == null){
			sender.sendMessage("Player is offline, they cannot be kicked.");
			return;
		}
		
		String reason = getReason(1, args);
		
		p.kickPlayer(ConfigHandler.messages.get("kick") + " " + reason);
		
		new RecordPunishment
		(
				p.getName(),
				"kick",
				0,
				reason,
				0,
				sender.getName(),
				plugin.util.getTimeStamp()
				
		).runTaskAsynchronously(plugin);
	}
}
