package com.ovgu.zim;

import android.content.Intent;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	private MainActivity mainActivity;
	private TextView preferenceButton;
	
	@Before
    public void setUp() throws Exception {
		mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
		preferenceButton = (TextView) mainActivity.findViewById(R.id.TextView03);
    }
	
	@Test
    public void shouldShowNoAlarmSetText() throws Exception {
        String hello = ((TextView)mainActivity.findViewById(R.id.TextViewNextAlarm)).getText().toString();
        assertThat(hello, equalTo(mainActivity.getString(R.string.NextAlarmNotSet)));
    }
	
	@Test
    public void shouldOpenPreferenceActivity() throws Exception {
		preferenceButton.performClick();
		
		ShadowActivity shadowActivity = Robolectric.shadowOf_(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf_(startedIntent);

        assertThat(shadowIntent.getComponent().getClassName(), equalTo(PreferenceActivity.class.getName()));
    }
}
