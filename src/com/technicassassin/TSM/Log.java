package com.technicassassin.TSM;

import java.util.logging.Logger;

public class Log {
	
	static TSM pl;
	
	public Log(TSM pl){
		
		this.pl = pl;
	}
	
	/*
	 * Returns the plugins logger.
	 */
	
	public static Logger getLogger(){
		
		return pl.getLogger();
	}
}
