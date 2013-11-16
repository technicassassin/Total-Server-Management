package com.technicassassin.TSM;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.technicassassin.TSM.Data.Table;

public class Util {
	
	/*
	 * converts ingame ticks to minutes.
	 */

	public int ticksToMinutes(int ticks){
		
		int secs = ticks*60*20;
		
		return secs;
	}
	
	/*
	 * converts minutes to ingame ticks.
	 */
	
	public int minutesToTicks(int secs){
		
		int ticks = secs/60/20;
		
		return ticks;
	}
	
	/*
	 * Returns the current time and date in a readable format.
	 */
	
	public String getTimeStamp(){
		
		return new java.text.SimpleDateFormat
				("dd/MM/yyyy HH:mm:ss").format
				(new java.util.Date (System.currentTimeMillis()*1000));
	}
	
	/*
	 * Converts time in a readable format to milliseconds from the epoch.
	 */
	
	public long readableToEpoch(String date){
		
		try {
			
			return new java.text.SimpleDateFormat
					("dd/MM/yyyy HH:mm:ss").parse
					("01/01/1970 01:00:00").getTime() / 1000;
			
		} catch (ParseException e) {
			
			Log.getLogger().severe("----------------------------------------------------------");
			Log.getLogger().severe("---------------         DATE ERROR         ---------------");
			Log.getLogger().severe("----------------------------------------------------------");
			e.printStackTrace();
			return 0;
			
		}
	}
	
	/*
	 * Converts milliseconds from epoch to a readable time format.
	 */
	
	public String epochToReadable(long epoch){
		
		return new java.text.SimpleDateFormat
				("dd/MM/yyyy HH:mm:ss").format
				(new java.util.Date (epoch*1000));
	}

	/*
	 * adds spaces to a string until the string is a specified lenght.
	 */
	
	public String pad(String original, int total){
		
		int length = original.length();
		
		if(length == total){
			
			return original;
			
		}else if(length > total){
			
			return original.substring(0, total - 2) + original.concat("..");
			
		}else{
			
			int padding = total - length;
			
			for(int n = 0; n < padding; n++){
				
				original = original.concat(" ");
			}
			
			return original;
		}
	}
	
	/*
	 * Given table data and the width of the columns this method
	 * creates and returns a table in a format which is able to be sent to
	 * the player.
	 */
	
	public String[] tableFactory(Table table, int width){
		
		List<String> complete = new ArrayList<String>();
		
		String[] columns =  table.columnnames;
		
		complete.add("--------------------------------------------------------------------------");
		complete.add(
					this.pad((columns[0]),width) +
					this.pad((columns[1]),width) +
					this.pad((columns[2]),width) +
					this.pad((columns[3]),width) +
					this.pad((columns[4]),width) +
					this.pad((columns[5]),width)
				);
		complete.add("--------------------------------------------------------------------------");
		
		List<String[]> tabledata = table.data;
		
		for(int n = 0; n < tabledata.size(); n++){
			
			complete.add(
						this.pad(tabledata.get(n)[0], width) +
						this.pad(tabledata.get(n)[1], width) +
						this.pad(tabledata.get(n)[2], width) +
						this.pad(tabledata.get(n)[3], width) +
						this.pad(tabledata.get(n)[4], width) +
						this.pad(tabledata.get(n)[5], width)
					);
		}
		return null;
	}

}