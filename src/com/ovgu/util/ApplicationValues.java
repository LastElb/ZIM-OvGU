package com.ovgu.util;

import java.util.Locale;

import android.content.*;
import android.preference.PreferenceManager;

/**
 * This class manages some values used over the whole app
 * @author Igor Lückel
 *
 */
public class ApplicationValues {
	
	/**
	 * Returns the UserID (Probandencode) from the preference page. If the value is not set yet, it rreturns an empty string.
	 * @param context The current application context
	 * @return Returns a String with the current UserID. The String is empty if the value is not set yet.
	 */
	public static String getUserID(Context context){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString("preferenceUserID", "");
	}
	
	/**
	 * This methode sets the UserID (Probandencode) to the preferences. The String-value will be trimmed and lower-cased.
	 * @param value The UserID
	 * @param context The current application context
	 */
	public static void setUserID(String value, Context context){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		preferences.edit().putString("preferenceUserID", value.toLowerCase(Locale.GERMANY).trim()).commit();
	}
	
	/**
	 * This function returns the last time when the app had an alarm. The value is empty at the beginning
	 * @param context The current application context
	 * @return The String has the format "dd.MM.yy HH.mm"
	 */
	public static String getLastAlarmTime(Context context){
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		return settings.getString("lastAlarmTime", "");
	}
	
	/**
	 * This function returns the time, when the current alarm should happen.
	 * @param context The current application context
	 * @return The String has the format "dd.MM.yy HH.mm"
	 */
	public static String getCurrentAlarmTime(Context context) {
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		return settings.getString("currentAlarmTime", "");
	}

	/**
	 * This function returns the time, when the user reacted to an alarm the last time
	 * @param context The current application context
	 * @return The String has the format "dd.MM.yy HH.mm"
	 */
	public static String getLastAnswerTime(Context context) {
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		return settings.getString("lastAnsweredAlarm", "");
	}
	
	/**
	 * This function returns the time from the last intentional alarm time the user answered
	 * @param context The current application context
	 * @return The String has the format "dd.MM.yy HH.mm"
	 */
	public static String getLastSavedAlarmTime(Context context) {
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		return settings.getString("lastSavedAlarm", "");
	}
	
	/**
	 * This function returns the last time when the app had an alarm. The value is empty at the beginning
	 * @param context The current application context
	 * @param value The String has the format "dd.MM.yy HH.mm"
	 */
	public static void setLastAlarmTime(String value, Context context){
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		settings.edit().putString("lastAlarmTime", value).commit();
	}
	
	/**
	 * This function returns the time, when the current alarm should happen.
	 * @param context The current application context
	 * @param value The String has the format "dd.MM.yy HH.mm"
	 */
	public static void setCurrentAlarmTime(String value, Context context) {
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		settings.edit().putString("currentAlarmTime", value).commit();
	}

	/**
	 * This function returns the time, when the user reacted to an alarm the last time
	 * @param context The current application context
	 * @param value The String has the format "dd.MM.yy HH.mm"
	 */
	public static void setLastAnswerTime(String value, Context context) {
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		settings.edit().putString("lastAnsweredAlarm", value).commit();
	}
	
	/**
	 * This function returns the time from the alarm, the user answered last (alarmtime, not anwertime)
	 * @param context The current application context
	 * @param value The String has the format "dd.MM.yy HH.mm"
	 */
	public static void setLastSavedAlarmTime(String value, Context context) {
		SharedPreferences settings = context.getSharedPreferences("alarmValues", 0);
		settings.edit().putString("lastSavedAlarm", value).commit();
	}
}
