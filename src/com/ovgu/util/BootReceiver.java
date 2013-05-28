package com.ovgu.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Boot Receiver launched", Toast.LENGTH_LONG).show();
		AlarmSetter as = new AlarmSetter();
		as.setAlarms(context);
	}
	
}
