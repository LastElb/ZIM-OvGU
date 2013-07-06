package com.ovgu.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This class gets called when the android smartphones reboots. We set the alarms as they were reset after shutting down the device
 * @author Igor Lueckel
 *
 */
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
