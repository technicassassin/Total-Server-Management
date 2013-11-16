package com.technicassassin.TSM;

import org.bukkit.Bukkit;

public class TSMStaff extends TSMPlayer{

	public TSMStaff(String name, TSM pl) {
		super(name, pl);

		plugin = pl;
		
		player = Bukkit.getServer().getPlayer(name);
		pname = player.getName();
	}

	
}
