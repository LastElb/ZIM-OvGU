package com.ovgu.jsondb;


import java.io.*;
import java.util.*;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ovgu.util.DatabaseEntry;

/**
 * This class is wrapper to store items of {@code DatabaseEntry} to an local database
 * @author Igor Lückel
 *
 */
public class JSONConnector {
	private static String FILENAME = "jsondb";
	/**
	 * Adds an entry to the local json file
	 * @param entry The DatabaseEntry with data from the user
	 * @throws IOException 
	 */
	public static void addEntry(DatabaseEntry entry, Context ctx){
	    com.google.gson.Gson gson = new GsonBuilder().registerTypeAdapter(DatabaseEntry.class, new DBEntrySerializer())
	            .create();
	    String jsondata = gson.toJson(entry)+"\n";
	    FileOutputStream fos;
		try {
			fos = ctx.openFileOutput(FILENAME, Context.MODE_APPEND);
			fos.write(jsondata.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}


	/**
	 * This function reads the local json file and converts all json objects into java objects. If there are no saved datas the function returns an empty list
	 * @return Returns a list of all entries saved on the phone
	 * @param ctx The current application context
	 */
	public static List<DatabaseEntry> getAllEntries(Context ctx){
		FileInputStream fis;
		try {
			fis = ctx.openFileInput(FILENAME);
		} catch (Exception e) {
			// There is no existing file
			return new LinkedList<DatabaseEntry>();
		}
		
		InputStreamReader inputStreamReader = new InputStreamReader(fis);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	    String line;
	    
	    LinkedList<DatabaseEntry> output = new LinkedList<DatabaseEntry>();
		GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(DatabaseEntry.class, new DBEntryDeserializer());
	    Gson gson = gsonBuilder.create();
	    
	    try {
			while ((line = bufferedReader.readLine()) != null) {
				output.add(gson.fromJson(line, DatabaseEntry.class));
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	/**
	 * This methode deletes the local json files. All datas saved in it will be lost
	 */
	public static void deleteEntries(Context ctx){
		ctx.deleteFile(FILENAME);
	}
}
