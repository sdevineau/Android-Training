package com.siondevineau.formationandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.siondevineau.formationandroid.R;

public class SecondActivity extends Activity {

	private final String TAG = SecondActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
	
		
		Toast.makeText(this, "libfvpihlk", Toast.LENGTH_SHORT).show();
	}

}
