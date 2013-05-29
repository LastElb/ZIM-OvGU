package com.ovgu.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmSetter as = new AlarmSetter();
		as.setAlarms(context);
	}
	
}
