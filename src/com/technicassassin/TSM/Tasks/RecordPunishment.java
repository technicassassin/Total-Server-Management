package com.technicassassin.TSM.Tasks;

import com.technicassassin.TSM.TSMTask;

public class RecordPunishment extends TSMTask{
	
	volatile String offender, punishment,
					description, enforcer, 
					timestamp;
	
	volatile int length, timeserved;
	
	public RecordPunishment	(
			
			String offender, 
			String punishment, 
			int length, 
			String description,
			int timeserved,
			String enforcer,
			String timestamp
	)
	{
		this.offender = offender;
		this.punishment = punishment;
		this.description = description;
		this.enforcer = enforcer;
		this.timestamp = timestamp;
		this.length = length;
		this.timeserved = timeserved;
	}

	@Override
	public void run() {
		
		pl.sql.sqlpunish.recordPunishment
		(
				offender, 
				punishment, 
				length, 
				description, 
				timeserved, 
				enforcer, 
				timestamp
		);
	}
}
