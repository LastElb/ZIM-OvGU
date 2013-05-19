package com.swe.zim;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class PreferenceActivity extends SherlockPreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		addPreferencesFromResource(R.xml.preference);
		
		// To listen to an update we create a lambda expression catching the new value
		this.findPreference("preferenceUserID").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary("Der festgelegte Probandencode lautet: "+newValue.toString());
                return true;
            }
        });
		
		this.findPreference("preferenceWTList1").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary("Die App erinnert um  "+newValue.toString()+ "");
                return true;
            }
        });
		
		this.findPreference("preferenceWTList2").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary("Die App erinnert um  "+newValue.toString()+ "");
                return true;
            }
        });
		
		this.findPreference("preferenceWTList3").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary("Die App erinnert um  "+newValue.toString()+ "");
                return true;
            }
        });
		
		this.findPreference("preferenceWTList4").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary("Die App erinnert um  "+newValue.toString()+ "");
                return true;
            }
        });
		
		this.findPreference("preferenceWakeTone").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary("Alarmton:  "+newValue.toString());
                return true;
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Save")
        .setIcon(R.drawable.ic_compose_inverse)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	
		return super.onCreateOptionsMenu(menu);
	}
}
