package com.ovgu.zim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.ovgu.util.DatabaseEntry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Igor Lückel
 *
 */
public class AlarmActivity extends SherlockActivity {
	
	private Date currentAlarmTime;
	private Date lastAlarmTime;
	private boolean _isTimeCorrect = true;
	/**
     * {@inheritDoc}
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		setAlarmTimes();
		appendTextListener();
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(this.getString(R.string.save))
				.setIcon(R.drawable.ic_content_save_light)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // If this callback does not handle the item click, onPerformDefaultAction
        // of the ActionProvider is invoked. Hence, the provider encapsulates the
        // complete functionality of the menu item.
		// ---
		// We have to ensure that the minutes inserted in the textbox are not more than the actually time left since the last alarm.
		if (_isTimeCorrect){
			saveData();
		}else{
			Toast.makeText(this, "Eingabe überprüfen", Toast.LENGTH_SHORT).show();
		}
        return true;
    }
	
	/**
	 * Increases the contact time in the edittext by 1
	 * @param view
	 */
	public void increaseContacts1(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+1;
		textfield.setText(Long.toString(newValue));
	}
	
	/**
	 * Increases the contact count in the edittext by 5
	 * @param view
	 */
	public void increaseContacts5(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+5;
		textfield.setText(Long.toString(newValue));
	}
	
	/**
	 * Increases the contact time in the edittext by 5
	 * @param view
	 */
	public void increaseContactsTime5(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+5;
		textfield.setText(Long.toString(newValue));
	}
	
	/**
	 * Increases the contact time in the edittext by 15
	 * @param view
	 */
	public void increaseContactsTime15(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+15;
		textfield.setText(Long.toString(newValue));
	}

	/**
	 * This TextChangedListener checks if the minutes input is not greater than the possible minutes (time between last and current alarm)
	 */
	private void appendTextListener(){
		final EditText textMessage = (EditText)findViewById(R.id.EditTextContactTime);
	    textMessage.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	checkContactsTime();
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	private void checkContactsTime(){
		EditText textMessage = (EditText)findViewById(R.id.EditTextContactTime);
		Calendar lastAlarm = Calendar.getInstance();
    	lastAlarm.setTime(lastAlarmTime);
    	Calendar currentAlarm = Calendar.getInstance();
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
    	
    	
    	TextView warning = (TextView)findViewById(R.id.TextViewWrongTime);
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

	/**
	 * Catches the currentAlarmTime and lastAlarmTime from the intent and sharedpreferences
	 */
	private void setAlarmTimes(){
		Intent i = getIntent();		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());  
		try {
			currentAlarmTime = format.parse(i.getStringExtra("currentAlarmTime"));
		} catch (ParseException e) {
		    e.printStackTrace();  
		}
		SharedPreferences settings = getSharedPreferences("alarmValues", 0);
		if (settings.getString("lastAlarmTime", "").length()>0){
			try {
				lastAlarmTime = format.parse(settings.getString("lastAlarmTime", ""));
			} catch (ParseException e) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(0);
				lastAlarmTime = cal.getTime();
			}
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(0);
			lastAlarmTime = cal.getTime();
		}
	}

	/**
	 * Creates a instance of DatabaseEntry and saves it to the database
	 */
	private void saveData(){
		SimpleDateFormat datetimeformat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
		SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm", Locale.getDefault());  
		SharedPreferences settings = getSharedPreferences("alarmValues", 0);
		SharedPreferences.Editor editor = settings.edit();
		
	    editor.putString("lastSavedAlarm", datetimeformat.format(Calendar.getInstance().getTime()));
		editor.commit();
		
		EditText et = (EditText)findViewById(R.id.EditTextContacts);
		
		DatabaseEntry data = new DatabaseEntry();
		data.setAnswerTime(timeformat.format(Calendar.getInstance().getTime()));
		data.setTime(timeformat.format(currentAlarmTime));
		data.setUserID("");
		data.setContacts(et.getText().toString());
		
		// Transform the minutes into the format hh:mm
		et = (EditText)findViewById(R.id.EditTextContactTime);
		Calendar contacttime = Calendar.getInstance();
		contacttime.set(2013, 1, 1, 0, 0, 0);
		contacttime.add(Calendar.MINUTE, Integer.parseInt(et.getText().toString()));
		
		data.setContactTime(timeformat.format(contacttime.getTime()));
		data.setDate(dateformat.format(currentAlarmTime));
	}
}
