package com.technicassassin.TSM;

import org.bukkit.scheduler.BukkitRunnable;

import com.technicassassin.TSM.Analytics.SQLHandlerAnalytics;
import com.technicassassin.TSM.Assist.SQLHandlerAssist;
import com.technicassassin.TSM.Punishment.SQLHandlerPunish;

public abstract class TSMTask extends BukkitRunnable {
	
	public TSM pl;
	public SQLHandlerPunish sqlpunish;
	public SQLHandlerAssist sqlassist;
	public SQLHandlerAnalytics sqlanalytics;
	
}
