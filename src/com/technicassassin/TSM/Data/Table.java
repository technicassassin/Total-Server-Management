package com.technicassassin.TSM.Data;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	public String[] columnnames =  new String[8];
	
	public List<String[]> data = new ArrayList<String[]>();
	
	public Table(String[] cname, List<String[]> data){
		
		this.data = data;
		columnnames = cname;
	}
}
