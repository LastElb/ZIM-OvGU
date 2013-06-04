package com.ovgu.zim;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.ovgu.util.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author Igor Lückel
 *
 */
public class AlarmActivity extends SherlockActivity {
	
	private AlarmViewModel _viewmodel;
	/**
     * {@inheritDoc}
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		
		//Initializes a new instance of the AlarmViewModel and does some startup actions.
		_viewmodel = new AlarmViewModel(this);
		_viewmodel.setInternalAlarmTimes();
		_viewmodel.setLastAlarmTimeToUI();
		_viewmodel.appendTextListener();
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(this.getString(R.string.save))
				.setIcon(R.drawable.ic_content_save_light)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
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
		// We have to ensure that the minutes inserted in the textbox are not more than the actually time left since the last alarm.
		if (_viewmodel.getIsEnteredTimeCorrect()){
			_viewmodel.saveAndExit();
		}else{
			Toast.makeText(this, "Eingabe überprüfen", Toast.LENGTH_SHORT).show();
		}
        return true;
    }
	
	/**
	 * Increases the contact time in the edittext by 1
	 * @param view
	 */
	public void increaseContacts1(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+1;
		textfield.setText(Long.toString(newValue));
	}
	
	/**
	 * Increases the contact count in the edittext by 5
	 * @param view
	 */
	public void increaseContacts5(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+5;
		textfield.setText(Long.toString(newValue));
	}
	
	/**
	 * Increases the contact time in the edittext by 5
	 * @param view
	 */
	public void increaseContactsTime5(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+5;
		textfield.setText(Long.toString(newValue));
	}
	
	/**
	 * Increases the contact time in the edittext by 15
	 * @param view
	 */
	public void increaseContactsTime15(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		long newValue=Long.parseLong(number)+15;
		textfield.setText(Long.toString(newValue));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBackPressed() {
		if (_viewmodel.getIsEnteredTimeCorrect()){
			_viewmodel.saveAndExit();
		}else{
			Toast.makeText(this, "Eingabe überprüfen", Toast.LENGTH_SHORT).show();
		}
	}
	
}
