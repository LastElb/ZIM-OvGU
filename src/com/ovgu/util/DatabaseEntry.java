package com.ovgu.util;

/**
 * This class is intended to store temporary data from the user input.
 * @author Igor Lückel
 *
 */
public class DatabaseEntry {
	private String _userid;
	private String _date;
	private String _time;
	private int _delay;
	private int _contacts;
	private int _contacttime;
	
	/**
	 * @return Returns the UserID (Probandencode)
	 */
	public String getUserID(){
		return _userid;
	}
	
	/**
	 * @return Returns the alarmdate with following format: dd.mm.yyyy
	 */
	public String getDate(){
		return _date;
	}
	
	/**
	 * @return Returns the alarmtime with the following format: hh:mm
	 */
	public String getTime(){
		return _time;
	}
	
	/**
	 * @return Returns the delay between alarm and saving the values in minutes
	 */
	public int getDelay(){
		return _delay;
	}
	
	/**
	 * @return Returns the amount of contacts
	 */
	public int getContacs(){
		return _contacts;
	}
	
	/**
	 * @return Returns the amount of time spend with the contacts
	 */
	public int getContactTime(){
		return _contacttime;
	}
	
	/**
	 * Sets a new UserID (Probandencode)
	 * @param value String containing the new UserID
	 */
	public void setUserID(String value){
		_userid=value;
	}
	
	/**
	 * Sets the alarmdate
	 * @param value String containing the alarmdate with the format: dd.mm.yyyy
	 */
	public void setDate(String value){
		_date=value;
	}
	
	/**
	 * Sets the alarmtime
	 * @param value String containing the alarmtime with the format: hh:mm
	 */
	public void setTime(String value){
		_time=value;
	}
	
	/**
	 * Sets delay between the actual alarm and the point of saving the data
	 * @param value Integer containing the amount of minutes
	 */
	public void setDelay(int value){
		_delay=value;
	}
	
	/**
	 * Sets the amount of contacts the participant had
	 * @param value Integer containing the amount of contacts
	 */
	public void setContacts(int value){
		_contacts=value;
	}
	
	/**
	 * Sets the amount of minutes the participant spend with the contacts
	 * @param value Integer containing the amount of minutes
	 */
	public void setContactTime(int value){
		_contacttime=value;
	}

	/**
	 * Use this String in the csv exporter to convert the object into an csv-row
	 * @return Returns a line containing all values in csv-format
	 */
	public String toCSV(){
		return "";
	}
	
	/**
	 * This string should be used as first line in a csv file
	 * @return Returns a header line containing all data fields
	 */
	public static String csvHeader(){
		return "";
	}
}
