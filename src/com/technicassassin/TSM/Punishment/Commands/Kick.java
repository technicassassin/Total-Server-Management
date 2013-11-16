package com.technicassassin.TSM.Punishment.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMCommand;
import com.technicassassin.TSM.Data.ConfigHandler;
import com.technicassassin.Tasks.AddKickRecord;

public class Kick extends TSMCommand{
	
	private TSM plugin;
	
	public Kick(TSM plugin){
		
		this.plugin = plugin;
	}
	
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		Player p = Bukkit.getServer().getPlayer(args[0]);
		
		if(!p.isOnline() || p == null){
			sender.sendMessage("Player is offline, they cannot be kicked.");
		}
		
		String reason = getReason(2, args);
		
		p.kickPlayer(ConfigHandler.messages.get("kick") + " " + reason);
		
		new AddKickRecord
			(
					args[0],
					reason,
					(Player)sender
					
			).runTaskAsynchronously(plugin);
	}
}
