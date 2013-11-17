package com.technicassassin.TSM.Punishment;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMPlayer;
import com.technicassassin.TSM.Punishment.Commands.Affect;
import com.technicassassin.TSM.Punishment.Commands.Ban;
import com.technicassassin.TSM.Punishment.Commands.Fine;
import com.technicassassin.TSM.Punishment.Commands.Jail;
import com.technicassassin.TSM.Punishment.Commands.Kick;
import com.technicassassin.TSM.Punishment.Commands.Maze;
import com.technicassassin.TSM.Punishment.Commands.Mute;
import com.technicassassin.TSM.Punishment.Commands.SetReason;
import com.technicassassin.TSM.Punishment.Commands.TempBan;
import com.technicassassin.TSM.Punishment.Commands.Warn;
import com.technicassassin.TSM.Tasks.RemoveRecord;

public class CommandListener implements CommandExecutor{

	@SuppressWarnings("unused")
	private TSM plugin;
	
	private Mute mute;
	private Kick kick;
	private Fine fine;
	private Maze maze;
	private Jail jail;
	private TempBan tempban;
	private Ban ban;
	private Warn warn;
	private Affect affect;
	private SetReason setreason;
	 
	public CommandListener(TSM plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		TSMPlayer tsmplayer = TSM.players.get((Player)sender);
		Player sendr = (Player)sender;
		
		switch(args[0].toUpperCase()){
		
		/*
		* Punishments:
		*/
		
		case "MUTE":
			mute.onCommand(sender, cmd, label, args);
			
		case "KICK":
			kick.onCommand(sender, cmd, label, args);
			
		case "FINE":
			fine.onCommand(sender, cmd, label, args);
			
		case "MAZE":
			maze.onCommand(sender, cmd, label, args);
			
		case "JAIL":
			jail.onCommand(sender, cmd, label, args);
			
		case "TEMPBAN":
			tempban.onCommand(sender, cmd, label, args);
			
		case "BAN":
			ban.onCommand(sender, cmd, label, args);
			
		case "WARN":
			warn.onCommand(sender, cmd, label, args);
			
		case "AFFECT":
			affect.onCommand(sender, cmd, label, args);
			
		/*
		 * Editing and viewing records:
		 */
			
		case "RECORD":
			
			switch(args[1].toUpperCase()){
			
			case "VIEW":
				
				if(args[2] == sender.getName() || args[2].isEmpty()){
					
					if(!sender.hasPermission("TSM.Punish.ViewOwnRecord")){
						sender.sendMessage("You do not have permission to execute this command.");
						return true;
					}
					
					tsmplayer.sendPlayerData(sender.getName(), sendr, "punish");
					return true;
				}
				
				if(!sender.hasPermission("TSM.Punish.ViewOthersRecord")){
					sender.sendMessage("You do not have permission to execute this command.");
					return true;
				}
				
				tsmplayer.sendPlayerData(args[2], sendr, "punish");
				
			case "ERASE":
				
				if(!sender.hasPermission("TSM.Punish.EraseRecords")){
					sender.sendMessage("You do not have permission to execute this command.");
					return true;
				}
				
				try{
					
					new RemoveRecord(Integer.parseInt(args[3]), sendr);
					
				}catch(NumberFormatException e){
					
					sender.sendMessage("Usage: /record erase [RecordID]");
				}
				return true;
				
			case "SETREASON":
				setreason.onCommand(sender, cmd, label, args);
				
			}
		}
		return true;
	}
	
}
