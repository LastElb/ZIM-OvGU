package com.ovgu.zim;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * 
 * @author Igor Lückel
 *
 */
public class AlarmActivity extends SherlockActivity {
	
	/**
     * {@inheritDoc}
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		getSupportActionBar().setTitle("ZIM - Auswertung");
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(this.getString(R.string.save))
				.setIcon(R.drawable.ic_content_save)
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
        
        return true;
    }
	
	/**
	 * Increases the contact time in the edittext by 1
	 * @param view
	 */
	public void increaseContacts1(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		int newValue=Integer.parseInt(number)+1;
		textfield.setText(Integer.toString(newValue));
	}
	
	/**
	 * Increases the contact count in the edittext by 5
	 * @param view
	 */
	public void increaseContacts5(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		int newValue=Integer.parseInt(number)+5;
		textfield.setText(Integer.toString(newValue));
	}
	
	/**
	 * Increases the contact time in the edittext by 5
	 * @param view
	 */
	public void increaseContactsTime5(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		int newValue=Integer.parseInt(number)+5;
		textfield.setText(Integer.toString(newValue));
	}
	
	/**
	 * Increases the contact time in the edittext by 15
	 * @param view
	 */
	public void increaseContactsTime15(View view){
		EditText textfield = (EditText)findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		int newValue=Integer.parseInt(number)+15;
		textfield.setText(Integer.toString(newValue));
	}
}
