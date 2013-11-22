package com.ovgu.zim;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.espian.showcaseview.ShowcaseView;
import com.espian.showcaseview.targets.ViewTarget;
import com.ovgu.util.*;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The AlarmActivity is a ScreenActivity and is shown after the smartphone user
 * tabs the notification in the notifican bar. The user can enter his amount of
 * contacts and the spend time with them.
 * 
 * @author Igor Lueckel
 * 
 */
public class AlarmActivity extends SherlockActivity {

	private AlarmViewModel _viewmodel;
	private ShowcaseView showcaseView;
	private int counter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);

		// Initializes a new instance of the AlarmViewModel and does some
		// startup actions.
		_viewmodel = new AlarmViewModel(this);
		_viewmodel.setInternalAlarmTimes();
		_viewmodel.setLastAlarmTimeToUI();
		_viewmodel.appendTextListener();

		// We want to display the next alarm time to the user. As extra
		// information
		int nextAlarm = AlarmSetter.nextAlarmHour(this);
		TextView textview = (TextView) findViewById(R.id.TextViewNextAlarmDescription);
		textview.setText(this.getString(R.string.NextAlarmP1) + " "
				+ Integer.toString(nextAlarm)
				+ this.getString(R.string.NextAlarmP2));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		final MenuItem item = menu
				.add(this.getString(R.string.save))
				.setIcon(R.drawable.ic_content_save_light)
				.setShowAsActionFlags(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		// Set up the ShowcaseView
		if (ApplicationValues.getValue("ShowAlarmActivityHelper",
				this.getApplicationContext()) == "") {
			counter = 0;
			ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
			co.hideOnClickOutside = false;
			co.block = true;
			co.fadeInDuration = 1000;
			co.fadeOutDuration = 1000;
			co.shotType = ShowcaseView.TYPE_ONE_SHOT;
			final SherlockActivity activity = this;
			showcaseView = ShowcaseView
					.insertShowcaseView(
							new ViewTarget(findViewById(R.id.EditTextContacts)),
							this,
							"Anzahl der Kontakte",
							"Legen Sie zunächst fest, wie viele Kontakte Sie im vergangenem Zeitraum hatten. Tippen Sie auf das Textfeld oder benutzen Sie die Shortcuts.",
							co);
			showcaseView.overrideButtonClick(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					showcaseView.hide();
					showcaseView = ShowcaseView
							.insertShowcaseView(
									new ViewTarget(
											findViewById(R.id.EditTextContactTime)),
									activity,
									"Kontaktzeit",
									"Legen Sie fest, wie viele Minuten Sie mit den Kontakten im vergangenem Zeitraum verbracht haben. Tippen Sie auf das Textfeld oder benutzen Sie die Shortcuts.");
					showcaseView
							.overrideButtonClick(new View.OnClickListener() {
								@Override
								public void onClick(View view) {
									switch (counter) {
									case 0:
										showcaseView.setShowcase(
												new ViewTarget(
														findViewById(item
																.getItemId())),
												true);
										showcaseView
												.setText(
														"Speichern",
														"Wenn Sie alle Werte eingetragen haben, tippen Sie auf das Speichern-Symbol oder nutzen die Zurück-Funktion Ihres Smartphones.");
										break;
									case 1:
										showcaseView.hide();
										break;
									}
									counter++;
								}
							});
				}
			});
			ApplicationValues.setValue("ShowAlarmActivityHelper", "false",
					this.getApplicationContext());
		}

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// If this callback does not handle the item click,
		// onPerformDefaultAction
		// of the ActionProvider is invoked. Hence, the provider encapsulates
		// the
		// complete functionality of the menu item.
		// ---
		// We have to ensure that the minutes inserted in the textbox are not
		// more than the actually time left since the last alarm.
		if (_viewmodel.getIsEnteredTimeCorrect()) {
			_viewmodel.saveAndExit();
		} else {
			Toast.makeText(this, "Eingabe überprüfen", Toast.LENGTH_SHORT)
					.show();
		}
		return true;
	}

	/**
	 * Increases the contact time in the edittext by 1
	 * 
	 * @param view
	 */
	public void increaseContacts1(View view) {
		EditText textfield = (EditText) findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		long newValue;
		try {
			newValue = Long.parseLong(number) + 1;
		} catch (Exception e) {
			newValue = 1;
		}
		textfield.setText(Long.toString(newValue));
	}

	/**
	 * Increases the contact count in the edittext by 5
	 * 
	 * @param view
	 */
	public void increaseContacts5(View view) {
		EditText textfield = (EditText) findViewById(R.id.EditTextContacts);
		String number = textfield.getText().toString();
		long newValue;
		try {
			newValue = Long.parseLong(number) + 5;
		} catch (Exception e) {
			newValue = 5;
		}
		textfield.setText(Long.toString(newValue));
	}

	/**
	 * Increases the contact time in the edittext by 5
	 * 
	 * @param view
	 */
	public void increaseContactsTime5(View view) {
		EditText textfield = (EditText) findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		long newValue;
		try {
			newValue = Long.parseLong(number) + 5;
		} catch (Exception e) {
			newValue = 5;
		}
		textfield.setText(Long.toString(newValue));
	}

	/**
	 * Increases the contact time in the edittext by 15
	 * 
	 * @param view
	 */
	public void increaseContactsTime15(View view) {
		EditText textfield = (EditText) findViewById(R.id.EditTextContactTime);
		String number = textfield.getText().toString();
		long newValue;
		try {
			newValue = Long.parseLong(number) + 15;
		} catch (Exception e) {
			newValue = 15;
		}
		textfield.setText(Long.toString(newValue));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBackPressed() {
		if (_viewmodel.getIsEnteredTimeCorrect()) {
			_viewmodel.saveAndExit();
		} else {
			Toast.makeText(this, "Eingabe überprüfen", Toast.LENGTH_SHORT)
					.show();
		}
	}

}
