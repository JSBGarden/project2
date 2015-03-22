package com.project.remoteclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.project.remoteclient.process.ClientSocket;
import com.project.remoteclient.process.MouseClientProcess;
import com.project.remoteprotocol.global.Buttons;
import com.project.remoteprotocol.global.Events;


public class Intro extends Activity {
	ClientSocket client;
	int port=8081;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		client=new ClientSocket();
		Button connect;
		final EditText ipa;
		connect=(Button)findViewById(R.id.btnConnectPC);
		ipa=(EditText)findViewById(R.id.txtIpAddress);
		connect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				client.connect(ipa.getText().toString(), port);
				Intent i=new Intent(Intro.this,Menu.class);
				startActivity(i);
				// TODO Auto-generated method stub
				
			}
		});
	}}