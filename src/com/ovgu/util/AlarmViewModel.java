package com.ovgu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.ovgu.zim.AlarmActivity;
import com.ovgu.zim.R;

public class AlarmViewModel {
	private AlarmActivity _parent;
	private Date currentAlarmTime;
	private Date lastAlarmTime;
	private boolean _isTimeCorrect = true;
	
	/**
	 * Creates a new instance of AlarmViewModel
	 * @param parent A instance of AlarmActivity
	 */
	public AlarmViewModel(AlarmActivity parent){
		_parent=parent;
	}
	
	/**
	 * Creates a instance of DatabaseEntry and saves it directly to the database
	 */
	public void saveAndExit(){
		SimpleDateFormat datetimeformat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
		SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm", Locale.getDefault());  
		SharedPreferences settings = _parent.getSharedPreferences("alarmValues", 0);
		SharedPreferences.Editor editor = settings.edit();
		
	    editor.putString("lastSavedAlarm", datetimeformat.format(Calendar.getInstance().getTime()));
		editor.commit();
		
		EditText et = (EditText)_parent.findViewById(R.id.EditTextContacts);
		
		DatabaseEntry data = new DatabaseEntry();
		data.setAnswerTime(timeformat.format(Calendar.getInstance().getTime()));
		data.setTime(timeformat.format(currentAlarmTime));
		data.setUserID("");
		data.setContacts(et.getText().toString());
		
		// Transform the minutes into the format hh:mm
		et = (EditText)_parent.findViewById(R.id.EditTextContactTime);
		Calendar contacttime = Calendar.getInstance();
		contacttime.set(2013, 1, 1, 0, 0, 0);
		contacttime.add(Calendar.MINUTE, Integer.parseInt(et.getText().toString()));
		
		data.setContactTime(timeformat.format(contacttime.getTime()));
		data.setDate(dateformat.format(currentAlarmTime));
	}
	
	/**
	 * Displays the last Alarmtime on the first panel on the UI
	 */
	public void setLastAlarmTimeToUI(){
		TextView textfield = (TextView)_parent.findViewById(R.id.EditTextContacts);
		textfield.setText(_parent.getString(R.string.RelevantTimeInterval1) + " " + _parent.getString(R.string.RelevantTimeInterval2));
	}
	
	/**
	 * Catches the currentAlarmTime and lastAlarmTime from the intent and sharedpreferences
	 */
	public void setInternalAlarmTimes(){
		Intent i = _parent.getIntent();		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());  
		try {
			currentAlarmTime = format.parse(i.getStringExtra("currentAlarmTime"));
		} catch (ParseException e) {
			// A parsing error appeared. We set the current alarm time to now
			currentAlarmTime = Calendar.getInstance().getTime();
		    e.printStackTrace();
		}
		
		SharedPreferences settings = _parent.getSharedPreferences("alarmValues", 0);
		if (settings.getString("lastSavedAlarm", "").length()>0){
			try {
				lastAlarmTime = format.parse(settings.getString("lastSavedAlarm", ""));
			} catch (ParseException e) {
				// A parsing error appeared. We set the time to midnight.
				Calendar cal = Calendar.getInstance();
				cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				lastAlarmTime = cal.getTime();
			}
		}else{
			// No previous alarms aka the first alarm. We set the time to midnight.
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			lastAlarmTime = cal.getTime();
		}
	}
	
	/**
	 * This TextChangedListener checks if the minutes input is not greater than the possible minutes (time between last and current alarm)
	 */
	public void appendTextListener(){
		final EditText textMessage = (EditText)_parent.findViewById(R.id.EditTextContactTime);
	    textMessage.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	checkContactsTime();
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	private void checkContactsTime(){
		EditText textMessage = (EditText)_parent.findViewById(R.id.EditTextContactTime);
		Calendar lastAlarm = Calendar.getInstance();
    	lastAlarm.setTime(lastAlarmTime);
    	Calendar currentAlarm = Calendar.getInstance();
    	
    	// We create a new calendar and set the time to the difference between the lastAlarmTime and the Current Time
    	Calendar difference = Calendar.getInstance();
    	difference.setTimeInMillis(currentAlarm.getTimeInMillis()-lastAlarm.getTimeInMillis());
    	
    	Calendar inputTime = Calendar.getInstance();
    	inputTime.setTimeInMillis(0);
    	String mininput = textMessage.getText().toString();
    	boolean istimetobig=false;
    	if (mininput.length()==0)
    		mininput="0";
    	try{
    		inputTime.add(Calendar.MINUTE, Integer.parseInt(mininput));
    	}catch (Exception e){
    		istimetobig=true;
    	}
    	
    	
    	TextView warning = (TextView)_parent.findViewById(R.id.TextViewWrongTime);
    	if (inputTime.getTimeInMillis()>difference.getTimeInMillis() || istimetobig==true)
    	{
    		textMessage.setTextColor(Color.RED);
    		warning.setVisibility(TextView.VISIBLE);
    		_isTimeCorrect = false;
    	}else{
    		warning.setVisibility(TextView.GONE);
    		textMessage.setTextColor(Color.BLACK);
    		_isTimeCorrect = true;
    	}
	}

	public boolean getIsEnteredTimeCorrect(){
		return _isTimeCorrect;
	}
}
