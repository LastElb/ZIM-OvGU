package com.ovgu.zim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.espian.showcaseview.ShowcaseView;
import com.espian.showcaseview.targets.ViewTarget;

import com.ovgu.jsondb.JSONConnector;
import com.ovgu.util.*;

//import com.espian.showcaseview.ShowcaseView;

/**
 * The MainActivity is the entrypoint of the app. The user can see the next alarm time, change preferences, export data as csv and wipe the app.
 * @author Igor Lueckel
 *
 */
public class MainActivity extends SherlockActivity {
	
	private ShowcaseView sv;
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		if (ApplicationValues.getValue("ShowMainActivityHelper", this.getApplicationContext()) == "")	{
			ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
	        co.hideOnClickOutside = false;
	        co.block = true;
	        co.fadeInDuration = 1000;
	        co.fadeOutDuration = 1000;
	        co.shotType = ShowcaseView.TYPE_ONE_SHOT;
	        sv = ShowcaseView.insertShowcaseView(R.id.TextView03, this, "Erster Start", "Legen Sie als erstes Ihre Appeinstellungen fest, indem Sie auf den Bereich im freiem Kreis tippen.", co);
	        ApplicationValues.setValue("ShowMainActivityHelper", "false", this.getApplicationContext());
		}
	}
	
	@Override
	protected void onPause() {
	   super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// We have to set the first alarm here
		// We also have to update the GUI with the next alarm time
		int nextAlarm = AlarmSetter.nextAlarmHour(this);
		AlarmSetter as = new AlarmSetter();
		TextView textview = (TextView) findViewById(R.id.TextViewNextAlarm);
		if (nextAlarm == -1){
			textview.setText(this.getString(R.string.NextAlarmNotSet));
			as.deleteAlarms(this);
		}else{
			textview.setText(this.getString(R.string.NextAlarmP1) + " " + Integer.toString(nextAlarm) + this.getString(R.string.NextAlarmP2));
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Send instant Broadcast").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
            	Intent i = new Intent("com.ovgu.zim.AlarmActivity");
            	//sendBroadcast(i);
            	AlarmSetter.SetAlarmIn10Seconds(getApplicationContext());
                return true;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		final Activity activity = this;
		menu.add("Info").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
            public boolean onMenuItemClick(MenuItem item) {
				sv = ShowcaseView.insertShowcaseView(new ViewTarget(findViewById(R.id.TextView03)), activity);
				sv.setShowcase(ShowcaseView.NONE);
				sv.setText( "Info", "Autor: Igor Lückel\nKontakt: igor.lueckel@st.ovgu.de");
                return true;
            }
		}).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
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
	 * Shows an dialoge with password box. If the password is correct, wipeData() gets called. Else a toast is shown.
	 * @param view
	 */
	public void wipeData(View view){
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		final EditText input = new EditText(this);

		input.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);

		alert.setView(input);    //edit text added to alert
		alert.setTitle("Passwortgeschützte Funktion");   //title setted

		// Cancel-button
		alert.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		// Ok button: Check the pw
		alert.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String enteredpw = input.getText().toString();
						if (enteredpw.equals("so210761")){
							wipeData();
						}else{
							Toast.makeText(getApplicationContext(), "Falsches Passwort", Toast.LENGTH_SHORT).show();
						}
						dialog.cancel();
					}
				});
		
		alert.show();
	}
	
	/**
	 * Wipes the userdata
	 */
	private void wipeData(){
		//Check the user preferences (alarmtime,...)
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		// comment the next line if you want to keep the user preferences
		preferences.edit().clear().commit();
		
		SharedPreferences settings = getSharedPreferences("alarmValues", 0);
		settings.edit().clear().commit();
		
		AlarmSetter as = new AlarmSetter();
		as.deleteAlarms(this);
		
		JSONConnector.deleteEntries(this);
		
		Toast.makeText(this, "App zurückgesetzt", Toast.LENGTH_SHORT).show();
		onResume();
	}
	
	public void exportToCSV(View view){
		CSVExporter export = new CSVExporter();
		export.exportAsCsv(this);
	}
}
