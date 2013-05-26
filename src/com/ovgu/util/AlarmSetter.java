package com.ovgu.util;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Toast;

public class AlarmSetter {
	public void setAlarm(SherlockActivity View){
		Intent i = new Intent("com.ovgu.zim.AlarmActivity");
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(View.getApplicationContext(), 234324243, i, 0);
	    AlarmManager alarmManager = (AlarmManager) View.getSystemService(Activity.ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (5 * 1000), pendingIntent);
	    Toast.makeText(View, "Alarm set in " + 5 + " seconds", Toast.LENGTH_LONG).show();
	}
}
