package com.ovgu.util;

import java.util.Calendar;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class AlarmSetter {
	public void setAlarm(SherlockActivity View){
		Intent i = new Intent("com.ovgu.zim.AlarmActivity");
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(View.getApplicationContext(), 234324243, i, 0);
	    AlarmManager alarmManager = (AlarmManager) View.getSystemService(Activity.ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (5 * 1000), pendingIntent);
	    Toast.makeText(View, "Alarm set in " + 5 + " seconds", Toast.LENGTH_LONG).show();
	}
	
	public static int nextAlarmHour(Context context){
		Calendar cal = Calendar.getInstance();
		int currenthour = cal.get(Calendar.HOUR_OF_DAY);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		int alarm1=Integer.parseInt(preferences.getString("WakeTime1", "-1"));
		int alarm2=Integer.parseInt(preferences.getString("WakeTime2", "-1"));
		int alarm3=Integer.parseInt(preferences.getString("WakeTime3", "-1"));
		int alarm4=Integer.parseInt(preferences.getString("WakeTime4", "-1"));
		
		if (alarm1 == -1 || alarm2 == -1 || alarm3 == -1 || alarm4 == -1)
			return -1;
		
		if (alarm4 <= currenthour || currenthour < alarm1)
			return alarm1;
		if (alarm1 <= currenthour && currenthour < alarm2)
			return alarm2;
		if (alarm2 <= currenthour && currenthour < alarm3)
			return alarm3;
		if (alarm3 <= currenthour && currenthour < alarm4)
			return alarm4;
				
		return -1;
	}
}
