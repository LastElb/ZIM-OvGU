package com.ovgu.util;

import java.io.*;
import android.os.Environment;

public class CSVExporter {
	public void exportAsCsv() throws IOException {
		String columnString = DatabaseEntry.csvHeader();
		String combinedString = columnString + "\n";

		// for (DatabaseEntry item : SQLiteConnector.getAllEntries())
		// combinedString = combinedString + item.toCSV() + "\n";

		File file = null;
		File root = Environment.getExternalStorageDirectory();
		if (root.canWrite()) {
			File dir = new File(root.getAbsolutePath() + "/ZIM");
			dir.mkdirs();
			file = new File(dir, "Data.csv");
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(file);
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
