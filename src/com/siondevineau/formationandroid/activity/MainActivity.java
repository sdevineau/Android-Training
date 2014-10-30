package com.siondevineau.formationandroid.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.siondevineau.formationandroid.R;
import com.siondevineau.formationandroid.service.GPSService;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getName();

	public static final String EXTRA_MESSAGE_TEST_KEY = "extra-message-key";
	public static final String EXTRA_MESSAGE_TEST_VALUE = "extra-message-VALUE";

	private TextView nameTextView;

	private TextView emailTextView;

	private TextView birthTextView;

	private Button cancelButton;

	private Button validateButton;

	private Intent intent;

	private Intent gpsServiceIntent;

	private ServiceConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// do the set content view after the super call
		setContentView(R.layout.activity_main);
		initializeView();

//		OnClickListener onclick = new OnClickValidateButton();
//		validateButton.setOnClickListener(onclick);
//
//		OnClickListener onclickCancel = new OnClickCancelButton();
//		cancelButton.setOnClickListener(onclickCancel);
		Log.d(TAG, "Just before launchLocationService. Oncreate");
		launchLocationService();
		Log.d(TAG, "Just after launchLocationService. Oncreate");



	}
	
	@Override
	protected void onStart() {
		Log.d(TAG, "Just before launchLocationService. Onstart");

		launchLocationService();
		Log.d(TAG, "Just after launchLocationService. Onstart");

		super.onStart();

	}
	
	public void stop() { 
		super.onStop();
		 stopService(gpsServiceIntent); 
		 unbindService(conn); 
		}

	private void initializeView() {
		nameTextView = (TextView) findViewById(R.id.name_edit);
		emailTextView = (TextView) findViewById(R.id.email_edit);
		birthTextView = (TextView) findViewById(R.id.birthdate_edit);
		cancelButton = (Button) findViewById(R.id.cancel_form);
		validateButton = (Button) findViewById(R.id.validate_form);

	}

	private class OnClickValidateButton implements OnClickListener {

		@Override
		public void onClick(View v) {
//			Log.i(TAG, "onClick");
//			nameTextView.setText("");
//			emailTextView.setText("");
//			birthTextView.setText("");
//
//			launchActivity();

		}

	}

	private class OnClickCancelButton implements OnClickListener {

		@Override
		public void onClick(View v) {
//			Log.i(TAG, "onClick");
//			nameTextView.setText("");
//			emailTextView.setText("");
//			birthTextView.setText("");
//
//			launchActivity();

		}

	}

	public void launchActivity() {

		intent = new Intent(this, SecondActivity.class);
		intent.putExtra("name", nameTextView.getText().toString());
		intent.putExtra("email", emailTextView.getText().toString());
		intent.putExtra("birth", birthTextView.getText().toString());

		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// launche the location service

	public void launchLocationService() {
		Log.d("OnClickCancelButton", "Inside launch location service");
		gpsServiceIntent = new Intent(this, GPSService.class);
		conn = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.d(TAG, "onServiceDisconnected");
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				Log.d(TAG, "onServiceConnected");
			}
		};
		bindService(gpsServiceIntent, conn, Service.BIND_AUTO_CREATE);

	}
	
	
}
