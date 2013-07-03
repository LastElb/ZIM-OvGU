package com.ovgu.zim;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.ovgu.jsondb.JSONConnector;
import com.ovgu.util.*;

@RunWith(RobolectricTestRunner.class)
public class JSONTest {
	private MainActivity mainActivity;
	
	@Before
    public void setUp() throws Exception {
		mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }
	
	@Test
    public void TestForEmptyDatabase() throws Exception {
		JSONConnector.deleteEntries(mainActivity.getApplicationContext());
		assertThat(JSONConnector.getAllEntries(mainActivity.getApplicationContext()).isEmpty(), equalTo(true));
    }
	
	@Test
    public void AddSingleEntry() throws Exception {
		JSONConnector.deleteEntries(mainActivity.getApplicationContext());
		DatabaseEntry dbentry = new DatabaseEntry();
		dbentry.setAnswerTime("11:01");
		dbentry.setContacts("10");
		dbentry.setContactTime("01:00");
		dbentry.setDate("01.01.13");
		dbentry.setTime("11:00");
		dbentry.setUserID("abcde");
		JSONConnector.addEntry(dbentry, mainActivity.getApplicationContext());
		assertThat(JSONConnector.getAllEntries(mainActivity.getApplicationContext()).isEmpty(), equalTo(false));
    }
	
	@Test
    public void CheckSingleEntry() throws Exception {
		JSONConnector.deleteEntries(mainActivity.getApplicationContext());
		DatabaseEntry dbentry = new DatabaseEntry();
		dbentry.setAnswerTime("11:01");
		dbentry.setContacts("10");
		dbentry.setContactTime("01:00");
		dbentry.setDate("01.01.13");
		dbentry.setTime("11:00");
		dbentry.setUserID("abcde");
		JSONConnector.addEntry(dbentry, mainActivity.getApplicationContext());
		
		dbentry = JSONConnector.getAllEntries(mainActivity.getApplicationContext()).get(0);
		assertThat(dbentry.toCSV(), equalTo("abcde;01.01.13;11:00;11:01;0;10;01;00"));
    }
}
