package com.technicassassin.TSM.Punishment.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.technicassassin.TSM.TSMCommand;
import com.technicassassin.TSM.Data.ConfigHandler;

public class Warn extends TSMCommand{

	public void onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(args.length < 2){
			
			sender.sendMessage("Usage: /mute [player] [reason]");
			return;
		}
		
		String reason = getReason(2, args);
		
		Bukkit.getServer().getPlayer(args[0]).sendMessage(ConfigHandler.messages.get("mute") + " " + reason);
	}
}
