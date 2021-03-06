package com.ovgu.util;

import java.io.*;

import com.ovgu.jsondb.JSONConnector;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

/**
 * This class is a warpper for exporting datasets into a csv file in the public accessable storage 
 * @author Igor Lueckel
 *
 */
public class CSVExporter {
	
	/**
	 * Saves and converts all entries of the JSON database into the csv format.
	 * The file named by the user ID is saved into a folder called "ZIM". 
	 * @param context The current application context
	 */
	public void exportAsCsv(Context context) {
		String columnString = DatabaseEntry.csvHeader();
		String combinedString = columnString + "\n";

		for (DatabaseEntry item : JSONConnector.getAllEntries(context))
			combinedString = combinedString + item.toCSV() + "\n";

		File file = null;
		File root = Environment.getExternalStorageDirectory();
		if (root.canWrite()) {
			File dir = new File(root.getAbsolutePath() + "/ZIM");
			dir.mkdirs();
			
			file = new File(dir, ApplicationValues.getUserID(context) + ".csv");
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(file, false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				out.write(combinedString.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
				Toast.makeText(context.getApplicationContext(), "Erfolgreich gespeichert", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
