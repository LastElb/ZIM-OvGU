package com.ovgu.util;

import com.ovgu.zim.AlarmActivity;
import com.ovgu.zim.R;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Vibrate the mobile phone
	    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(2000);
	    
	    NotificationCompat.Builder mBuilder =
	            new NotificationCompat.Builder(context)
	            .setSmallIcon(R.drawable.ic_social_group)
	            .setContentTitle("ZIM-Wecker")
	            .setContentText("Auswertung der sozialen Kontakte nötig!");
	    // Creates an explicit intent for an Activity in your app
	    Intent resultIntent = new Intent(context, AlarmActivity.class);

	    PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);
	    mBuilder.setContentIntent(resultPendingIntent);
	    mBuilder.setAutoCancel(true);
	    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	    int mId=1000;
		// mId allows you to update the notification later on.
	    mNotificationManager.notify(mId, mBuilder.build());
	}

}
