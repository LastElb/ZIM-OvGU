package com.ovgu.zim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockActivity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Check the user preferences (alarmtime,...)
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		// comment the next line if you want to keep the user preferences
		preferences.edit().clear().commit();
		
		//We are checking, if the usercode is set. If not, we show the PreferenceActivity.
		String savedUserID = preferences.getString("preferenceUserID", "");
		if (savedUserID == ""){
			Intent intent = new Intent(this, PreferenceActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 //Used to put dark icons on light action bar
		 return true;
	}
}
