package com.ovgu.zim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import com.ovgu.util.*;

public class MainActivity extends SherlockActivity {
	//private AlarmReceiver mReceiver;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//We are checking, if the usercode is set. If not, we show the PreferenceActivity.
		/*String savedUserID = preferences.getString("preferenceUserID", "");
		if (savedUserID == ""){
			Intent intent = new Intent(this, PreferenceActivity.class);
			startActivity(intent);
		}else{
			AlarmSetter as = new AlarmSetter();
			as.setAlarm(this);
		}*/
	}
	
	@Override
	protected void onPause() {
	   super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 return true;
	}
	
	/**
	 * Starts a new activity with the preference screen
	 */
	public void openPreferences(View view){
		Intent intent = new Intent(this, PreferenceActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Wipes the userdata
	 */
	public void wipeData(View view){
		//Check the user preferences (alarmtime,...)
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		// comment the next line if you want to keep the user preferences
		preferences.edit().clear().commit();
		Toast.makeText(this, "Preferences wiped", Toast.LENGTH_SHORT).show();
	}
}
