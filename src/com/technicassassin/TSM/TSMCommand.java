package com.technicassassin.TSM;

public class TSMCommand {
	
	public TSM plugin;
	
	public TSMCommand(TSM pl){
		
		plugin = pl;
	}
	
	/* 
	 * This splits the reason from the rest of the arguments
	 * in the command.
	 * Returns a string containing the reason.
	 */
	
    public String getReason(int argument, String[] args) {
    	
    	String reason = "";
    	
		int n = argument;
		
		while(n <= args.length){
			
			reason.concat(args[n]);
			reason.concat(" ");
			n++;
		}
		
		if(reason == "" || reason == " "){
			
			return null;
			
		}else{
			
			return reason;
		}
    }
}
