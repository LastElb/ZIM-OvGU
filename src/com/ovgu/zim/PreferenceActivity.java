package com.ovgu.zim;


import com.actionbarsherlock.app.SherlockPreferenceActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

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
                preference.setSummary(changedUserID + " " + newValue.toString());
                SetDoneIcon(preference);
                return true;
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

}
