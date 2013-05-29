package com.ovgu.util;

import java.io.IOException;

import com.ovgu.zim.AlarmActivity;
import com.ovgu.zim.R;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

/**
 * 
 * @author Igor Lückel
 *
 */
public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Vibrate the mobile phone
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		if (preferences.getBoolean("CheckBoxVibration", false) == true){
			Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		    vibrator.vibrate(1500);
		}
		
		String alarms = preferences.getString("RingtonePreference", "default ringtone");
		if (alarms.length()>0){
			Uri uri = Uri.parse(alarms);
		    playSound(context, uri);
		}
	    
	    NotificationCompat.Builder mBuilder =
	            new NotificationCompat.Builder(context)
	            .setSmallIcon(R.drawable.ic_social_group)
	            .setContentTitle("ZIM-Wecker")
	            .setContentText("Auswertung der sozialen Kontakte nötig!");
	    
	    // Android 2.x has normally a light taskbar. And the icon set previous is also light. So we are changing it here to a dark version if needed
	    if (android.os.Build.VERSION.RELEASE.startsWith("2.")){
	    	mBuilder.setSmallIcon(R.drawable.ic_social_group_dark);
	    }
	    
	    
	    // Creates an explicit intent for an Activity in your app
	    Intent resultIntent = new Intent(context, AlarmActivity.class);
	    resultIntent.putExtra("alarmtime", intent.getStringExtra("alarmtime"));
	    PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);
	    mBuilder.setContentIntent(resultPendingIntent);
	    mBuilder.setAutoCancel(true);
	    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	    int mId=1000;
		// mId allows you to update the notification later on.
	    mNotificationManager.notify(mId, mBuilder.build());
	}
	
	private MediaPlayer mMediaPlayer;

	/**
	 * Plays a sound from a Uri
	 * @param context The current context
	 * @param alert Uri to the soundfile
	 */
	private void playSound(Context context, Uri alert) {
		mMediaPlayer = new MediaPlayer();
		try {
			mMediaPlayer.setDataSource(context, alert);
			final AudioManager audioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mMediaPlayer.prepare();
				mMediaPlayer.start();
			}
			
			mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                }
            });
			
			mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
			
		} catch (IOException e) {
			System.out.println("OOPS");
		}
	}

}
