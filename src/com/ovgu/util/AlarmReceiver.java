package com.ovgu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.ovgu.jsondb.JSONConnector;
import com.ovgu.zim.AlarmActivity;
import com.ovgu.zim.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

/**
 * This class gets called if an alarm occures. It plays a sound or vibrates
 * (depends on user preferences) and creates a new notification.
 * 
 * @author Igor Lueckel
 * 
 */
public class AlarmReceiver extends BroadcastReceiver {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm",
				Locale.getDefault());
		Calendar now = Calendar.getInstance();
		String currentAlarmTime = format.format(now.getTime());

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(context, AlarmActivity.class);
		resultIntent.putExtra("currentAlarmTime", currentAlarmTime);

		// Set the last Alarm
		ApplicationValues.setLastAlarmTime(
				ApplicationValues.getCurrentAlarmTime(context), context);
		ApplicationValues.setCurrentAlarmTime(currentAlarmTime, context);

		String lastAlarmTime = ApplicationValues.getLastAlarmTime(context);
		String lastAnsweredAlarm = ApplicationValues
				.getLastSavedAlarmTime(context);

		if (!lastAlarmTime.equals(lastAnsweredAlarm)) {
			// The last event was not saved. Save the object with the -77's here
			DatabaseEntry data = new DatabaseEntry();
			data.setContacts("-77");
			data.setContactTime("-77");
			data.setAnswerTime("-77");
			data.setDate(lastAlarmTime.split(" ")[0]);
			data.setTime(lastAlarmTime.split(" ")[1]);
			data.setUserID(ApplicationValues.getUserID(context));
			JSONConnector.addEntry(data, context);
		}

		PendingIntent resultPendingIntent = PendingIntent.getActivity(context,
				0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// Creates a new notification
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context)
				.setSmallIcon(R.drawable.ic_social_group)
				.setContentTitle("ZIM-Alarm")
				.setContentText("Auswertung der sozialen Kontakte nötig!")
				.setAutoCancel(true)
				.setLights(0xffef7c00, 500, 500)
				.setContentIntent(resultPendingIntent);

		if (preferences.getBoolean("CheckBoxVibration", false) == true) {
			mBuilder.setVibrate(new long[] { 500, 500, 500 });
		}
		if (preferences.getBoolean("RingtonePreference", false) == true) {
			mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
		}

		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		int mId = 1000;
		Notification notification = mBuilder.build();
		
		// mId allows you to update the notification later on.
		mNotificationManager.notify(mId, notification);
	}
}
