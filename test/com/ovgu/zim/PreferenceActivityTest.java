package com.ovgu.zim;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import com.ovgu.util.AlarmSetter;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class PreferenceActivityTest {

	private PreferenceActivity preferenceActivity;
	
	@Before
    public void setUp() throws Exception {
		preferenceActivity = Robolectric.buildActivity(PreferenceActivity.class).create().get();
    }
	
	@Test
    public void shouldShowMinusOne() throws Exception {
		// Reset preferences
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preferenceActivity);
		preferences.edit().clear().commit();
		
        assertThat(AlarmSetter.nextAlarmHour(preferenceActivity), equalTo(-1));
    }
}
