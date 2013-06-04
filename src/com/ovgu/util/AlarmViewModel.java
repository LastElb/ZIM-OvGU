package com.ovgu.util;

import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovgu.zim.R;

public class AlarmViewModel {
	SherlockActivity _parent;
	public AlarmViewModel(SherlockActivity parent){
		_parent=parent;
	}
		
	public void saveAndExit(){
		
	}
	
	public void setLastAlarmTimeToUI(){
		TextView textfield = (TextView)_parent.findViewById(R.id.EditTextContacts);
		textfield.setText(_parent.getString(R.string.RelevantTimeInterval1) + " " + _parent.getString(R.string.RelevantTimeInterval2));
	}
}
