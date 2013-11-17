package com.technicassassin.TSM.Punishment.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.technicassassin.TSM.TSM;
import com.technicassassin.TSM.TSMCommand;
import com.technicassassin.TSM.Util;
import com.technicassassin.TSM.Tasks.RecordPunishment;

public class Affect extends TSMCommand{
	
	public Affect(TSM pl) {
		super(pl);
	}
	
	Util util = new Util();

	public void onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		Player target = Bukkit.getServer().getPlayer(args[0]);
		int length = 0;
		PotionEffect effect = null;
		
		if(args.length == 0){
			
			sender.sendMessage("Effects you can apply:(SLOWNESS,SLOWDIG,BLINDNESS,CONFUSION)");
			return;
			
		}else if(args.length != 3){
			
			sender.sendMessage("Usage: /affect [target] [effect type] [length(minutes)]");
			return;
			
		}else if(!target.isOnline()||target == null){
			
			sender.sendMessage("You cannot apply effects to offline players.");
			return;
		}
		
		try{
			length = Integer.parseInt(args[2]);
			
		} catch (NumberFormatException e){
			
			sender.sendMessage("Usage: /affect [target] [effect type] [length(minutes)]");
			e.printStackTrace();
			return;
		}
		
		length = util.minutesToTicks(length);
		
		switch(args[1].toUpperCase()){
		
		case "SLOWNESS":
			
			effect = new PotionEffect(PotionEffectType.SLOW, length, 0, false);
			
		case "SLOWDIG":
			
			effect = new PotionEffect(PotionEffectType.SLOW_DIGGING, length, 0, false);
			
		case "BLINDNESS":
			
			effect = new PotionEffect(PotionEffectType.BLINDNESS, length, 0, false);
			
		case "CONFUSION":
			
			effect = new PotionEffect(PotionEffectType.CONFUSION, length, 0, false);
			
		}
		
		if(effect == null){
			
			sender.sendMessage("Adding effect failed.");
			sender.sendMessage("Usage: /affect [target] [effect type] [length(minutes)] (reason)");
			sender.sendMessage("Effects you can apply:(SLOWNESS,SLOWDIG,BLINDNESS,CONFUSION)");
			return;
			
		}else if(target.addPotionEffect(effect)){
			
			sender.sendMessage("Effect: " + PotionEffectType.SLOW.toString() + " was added to " +
					target.getName());
			
			target.sendMessage("Effect: " + PotionEffectType.SLOW.toString() + ", was added to you for: "
					+ args[2] + " minutes, by staff member: " + sender.getName());
			
			new RecordPunishment
			(
					target.getName(),
					"affect " + effect.toString(),
					util.ticksToMinutes(length),
					getReason(3,args),
					0,
					sender.getName(),
					plugin.util.getTimeStamp()
					
			).runTaskAsynchronously(plugin);
			
		} else {
			
			sender.sendMessage("Adding effect failed.");
			return;
		}
	}
}
