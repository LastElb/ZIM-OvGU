package com.ovgu.zim;


import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

@SuppressWarnings("unused")
public class PreferenceActivity extends SherlockPreferenceActivity  {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		final String changedWakeTime = this.getString(R.string.PreferenceWakeTimeSet);
		final String changedUserID = this.getString(R.string.PreferenceUserIDSet);
		//This is called when the UserID changed
		this.findPreference("preferenceUserID").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
            	//Delete whitespace
            	newValue=newValue.toString().toLowerCase(Locale.GERMAN).trim();
            	//Create a regex
            	Pattern p = Pattern.compile("((?:[a-ü][a-ü]+))",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                Matcher m = p.matcher(newValue.toString());
            	if (m.matches()&& newValue.toString().length()==5){
            		preference.setSummary(changedUserID + " " + newValue.toString());
                    SetDoneIcon(preference);
                    _isIDSet=true;
                    return true;
            	}
            	_isIDSet=false;
                return false;
            }
        });
		
		//The following four constructs get called when the selected alarm time changes
		this.findPreference("WakeTime1").setOnPreferenceChangeListener(
				new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime1Set = true;
						return true;
					}
				});
		
		this.findPreference("WakeTime2").setOnPreferenceChangeListener(
				new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime2Set = true;
						return true;
					}
				});
		
		this.findPreference("WakeTime3").setOnPreferenceChangeListener(
				new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime3Set = true;
						return true;
					}
				});

		this.findPreference("WakeTime4").setOnPreferenceChangeListener(
				new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime4Set = true;
						return true;
					}
				});
		
		//Ringtone
		this.findPreference("RingtonePreference").setOnPreferenceChangeListener(
				new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(newValue.toString());
						SetDoneIcon(preference);
						_isRingtoneSet = true;
						return true;
					}
				});
		
		
		//Vibration
		final String VibrationSummary=this.getString(R.string.PreferenceVibrationDescription);
		final String NoVibrationSummary=this.getString(R.string.PreferenceNoVibrationDescription);
		this.findPreference("CheckBoxVibration")
				.setOnPreferenceChangeListener(
						new OnPreferenceChangeListener() {
							@Override
							public boolean onPreferenceChange(Preference preference, Object newValue) {
								if (newValue=="true"){
									preference.setSummary(VibrationSummary);
								}else{
									preference.setSummary(NoVibrationSummary);
								}
								return true;
							}
						});
	}
	
	@SuppressLint("NewApi")
	private void SetDoneIcon(Preference preference){
		if (android.os.Build.VERSION.SDK_INT>android.os.Build.VERSION_CODES.HONEYCOMB){
        	preference.setIcon(R.drawable.ic_navigation_accept);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add("Save")
				.setIcon(R.drawable.ic_content_save)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}
	
	private boolean _isIDSet = false;
	private boolean _isWakeTime1Set = false;
	private boolean _isWakeTime2Set = false;
	private boolean _isWakeTime3Set = false;
	private boolean _isWakeTime4Set = false;
	private boolean _isRingtoneSet = false;

}
