package com.ovgu.zim;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.espian.showcaseview.ShowcaseView;
import com.ovgu.util.AlarmSetter;
import com.ovgu.util.ApplicationValues;

/**
 * Within the PreferenceActivity the user can change alarmtimes and alarmtypes 
 * @author Igor Lueckel
 *
 */
public class PreferenceActivity extends SherlockPreferenceActivity  {

	private boolean _wasblank = true;
	
	private boolean _isIDSet = false;
	private boolean _isWakeTime1Set = false;
	private boolean _isWakeTime2Set = false;
	private boolean _isWakeTime3Set = false;
	private boolean _isWakeTime4Set = false;
	private boolean _isRingtoneSet = false;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
		setSavedValues();
		createPreferenceChangedListener();
	}
	
	@SuppressWarnings("deprecation")
	private void setSavedValues(){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		// Just set values, if the userid is not empty
		String userid = preferences.getString("preferenceUserID", ""); 
		if(userid.length() != 0){
			// Variables
			final String changedWakeTime = this.getString(R.string.PreferenceWakeTimeSet);
			final String changedUserID = this.getString(R.string.PreferenceUserIDSet);
			
			//UserID
			this.findPreference("preferenceUserID").setSummary(changedUserID + " " + preferences.getString("preferenceUserID", ""));
			this.findPreference("preferenceUserID").setEnabled(false);
			
			//AlarmTimes
			this.findPreference("WakeTime1").setSummary(changedWakeTime + " " + preferences.getString("WakeTime1", "") + ":00 Uhr");
			this.findPreference("WakeTime2").setSummary(changedWakeTime + " " + preferences.getString("WakeTime2", "") + ":00 Uhr");
			this.findPreference("WakeTime3").setSummary(changedWakeTime + " " + preferences.getString("WakeTime3", "") + ":00 Uhr");
			this.findPreference("WakeTime4").setSummary(changedWakeTime + " " + preferences.getString("WakeTime4", "") + ":00 Uhr");
			_wasblank = false;
			
			//Ringtone
			this.findPreference("RingtonePreference").setSummary(preferences.getString("RingtonePreference", ""));
			
			_isIDSet = true; _isRingtoneSet = true; _isWakeTime1Set = true; _isWakeTime2Set = true; _isWakeTime3Set = true; _isWakeTime4Set = true; 
		}
	}
	
	@SuppressWarnings("deprecation")
	private void createPreferenceChangedListener(){
		final String changedWakeTime = this.getString(R.string.PreferenceWakeTimeSet);
		final String changedUserID = this.getString(R.string.PreferenceUserIDSet);
		//This is called when the UserID changed
		this.findPreference("preferenceUserID").setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
            	//Delete whitespace
            	newValue = newValue.toString().toLowerCase(Locale.GERMAN).trim();
            	//Create a regex
            	Pattern p = Pattern.compile("((?:[a-ü][a-ü]+))",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                Matcher m = p.matcher(newValue.toString());
            	if (m.matches()&& newValue.toString().length()==5){
            		preference.setSummary(changedUserID + " " + newValue.toString());
                    SetDoneIcon(preference);
                    _isIDSet=true;
                    return true;
            	}
            	Toast.makeText(getApplicationContext(), "Der Probandencode entspricht nicht den Anforderungen", Toast.LENGTH_SHORT).show();
            	_isIDSet=false;
                return false;
            }
        });
		
		//The following four constructs get called when the selected alarm time changes
		this.findPreference("WakeTime1").setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime1Set = true;
						return true;
					}
				});
		
		this.findPreference("WakeTime2").setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime2Set = true;
						return true;
					}
				});
		
		this.findPreference("WakeTime3").setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime3Set = true;
						return true;
					}
				});

		this.findPreference("WakeTime4").setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(changedWakeTime + " "
								+ newValue.toString() + ":00 Uhr");
						SetDoneIcon(preference);
						_isWakeTime4Set = true;
						return true;
					}
				});
		
		//Ringtone
		this.findPreference("RingtonePreference").setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary(newValue.toString());
						SetDoneIcon(preference);
						_isRingtoneSet = true;
						return true;
					}
				});
		
		//Vibration
		final String VibrationSummary=this.getString(R.string.PreferenceVibrationDescription);
		final String NoVibrationSummary=this.getString(R.string.PreferenceNoVibrationDescription);
		this.findPreference("CheckBoxVibration").setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
							@Override
							public boolean onPreferenceChange(Preference preference, Object newValue) {
								if ((Boolean)newValue==true){
									preference.setSummary(VibrationSummary);
									VibrateSmartphone();
								}else{
									preference.setSummary(NoVibrationSummary);
								}
								return true;
							}
						});
	}
	
	@SuppressLint("NewApi")
	private void SetDoneIcon(Preference preference){
		if (android.os.Build.VERSION.SDK_INT>android.os.Build.VERSION_CODES.HONEYCOMB){
			try{
				preference.setIcon(R.drawable.ic_navigation_accept);
			}catch (Exception e){
				
			}
        }
	}
	
	private void VibrateSmartphone()
	{
		Vibrator vibrator = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(1500);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuItem item = menu.add(this.getString(R.string.save))
							.setIcon(R.drawable.ic_content_save_light)
							.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		
		if (ApplicationValues.getValue("ShowPreferenceActivityHelper", this.getApplicationContext()) == "")
		{
			ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
	        co.hideOnClickOutside = false;
	        co.block = true;
	        co.fadeInDuration = 1000;
	        co.fadeOutDuration = 1000;
	        co.shotType = ShowcaseView.TYPE_ONE_SHOT;
	        ShowcaseView sv = ShowcaseView.insertShowcaseViewWithType(ShowcaseView.ITEM_ACTION_ITEM, item.getItemId() , this, "Einstellungen", "Geben Sie in jeder Zeile Ihre persönlichen Daten bzw. Vorlieben an. Tippen Sie danach auf das freigestellte Symbol.", co);
	        ApplicationValues.setValue("ShowPreferenceActivityHelper", "false", this.getApplicationContext());
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // If this callback does not handle the item click, onPerformDefaultAction
        // of the ActionProvider is invoked. Hence, the provider encapsulates the
        // complete functionality of the menu item.
		// ---
		// We just have one MenuItem in this window, so we can handle everything inside here
		//
		// We are checking if all settings are set. If not, we show a toast.
        if (_isIDSet && _isWakeTime1Set && _isWakeTime2Set && _isWakeTime3Set && _isWakeTime4Set && _isRingtoneSet){
        	int nextAlarm = AlarmSetter.nextAlarmHour(this);
    		AlarmSetter as = new AlarmSetter();
    		
    		if (nextAlarm != -1){
    			if (_wasblank)
    			{
    				Intent i = new Intent("com.ovgu.zim.AlarmActivity");
                	sendBroadcast(i);
    			}
    			as.setAlarms(this);
    		}else{
    			as.deleteAlarms(this);
    		}
    		
    		// We want a string without whitespace on the start and end
    		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    		preferences.edit().putString("preferenceUserID", preferences.getString("preferenceUserID", "").trim().toLowerCase(Locale.GERMAN)).commit();
    		
        	finish();
        }else{
        	Toast.makeText(this, this.getString(R.string.PreferencesNotSet),
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBackPressed() {
		if (!_wasblank){
			int nextAlarm = AlarmSetter.nextAlarmHour(this);
    		AlarmSetter as = new AlarmSetter();
    		
    		if (nextAlarm != -1){
    			as.setAlarms(this);
    		}else{
    			as.deleteAlarms(this);
    		}
    		
			finish();
		}else{
			Toast.makeText(this, "Benutzen Sie den Speichern-Button!", Toast.LENGTH_SHORT).show();
		}
	}
}
