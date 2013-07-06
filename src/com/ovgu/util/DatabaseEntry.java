package com.ovgu.util;

import java.text.ParseException;

/**
 * This class is intended to store temporary data from the user input.
 * @author Igor Lueckel
 *
 */
public class DatabaseEntry {
	private String _userid;
	private String _date;
	private String _time;
	private String _answertime;
	private String _contacts;
	private String _contacttime;
	
	/**
	 * @return Returns the UserID (Probandencode)
	 */
	public String getUserID(){
		return _userid;
	}
	
	/**
	 * @return Returns the alarmdate with following format: dd.mm.yy
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
	 * @return Returns the time (hh:mm) when the user saved the data
	 */
	public String getAnswerTime(){
		return _answertime;
	}
	
	/**
	 * @return Returns the amount of contacts
	 */
	public String getContacts(){
		return _contacts;
	}
	
	/**
	 * @return Returns the amount of time spend with the contacts in the format hh:mm
	 */
	public String getContactTime(){
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
	 * @param value String containing the alarmdate with the format: dd.mm.yy
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
	 * Set -77 if the user does not anwserd it
	 * @param value String containing the amount of time spend with the contacts (hh:mm)
	 */
	public void setAnswerTime(String value){
		_answertime=value;
	}
	
	/**
	 * Sets the amount of contacts the participant had
	 * Set -77 if the user does not anwserd it
	 * @param value String containing the amount of contacts
	 */
	public void setContacts(String value){
		_contacts=value;
	}
	
	/**
	 * Sets the amount of minutes the participant spend with the contacts
	 * Set -77 if the user does not anwserd it
	 * @param value String containing the amount of time spend with the contacts (format hh:mm)
	 */
	public void setContactTime(String value){
		_contacttime=value;
	}

	/**
	 * Use this String in the csv exporter to convert the object into an csv-row
	 * @return Returns a line containing all values in csv-format
	 * @throws ParseException Date or time is in the wrong format
	 */
	public String toCSV(){
		String id=getUserID();
		String date=getDate();
		String time=getTime();
		String answertime;
		String abort;
		String contacts;
		String contactshour;
		String contactsmin;
		
		if (!getAnswerTime().equals("-77")){
			answertime = getAnswerTime();
			abort="0";
			contacts = getContacts();
			contactshour = getContactTime().split(":")[0];
			contactsmin = getContactTime().split(":")[1];
		}else{
			answertime=getAnswerTime();
			abort="1";
			contacts=answertime;
			contactshour=answertime;
			contactsmin=answertime;
		}
	    		
		return id+";"+date+";"+time+";"+answertime+";"+abort+";"+contacts+";"+contactshour+";"+contactsmin;
	}
	
	/**
	 * This string should be used as first line in a csv file
	 * @return Returns a header line containing all data fields
	 */
	public static String csvHeader(){
		return "Code;Datum;Alarmzeit;Antwortzeit;Abbruch;Kontakte;Stunden;Minuten";
	}
}
