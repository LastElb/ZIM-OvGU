package com.swe.zim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//This resets the preferences while debugging
		//in usage-environment you have to comment this 2 lines
		SharedPreferences preferences = getSharedPreferences("appPreferences", 0);
		preferences.edit().clear().commit();
				
				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(this, PreferenceActivity.class);
		startActivity(intent);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Save")
            .setIcon(R.drawable.ic_compose_inverse)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		return super.onCreateOptionsMenu(menu);
	}
}
