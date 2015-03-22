package com.project.remoteclient;


import com.project.remoteclient.process.ClientSocket;
import com.project.remoteclient.process.MouseClientProcess;











import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.remoteprotocol.global.Buttons;
import com.project.remoteprotocol.global.Events;
public class MouseActivity extends Activity {
	ClientSocket client;
	MouseClientProcess mouseClientProcess;
	Button btnLeftClick,btnRightClick;
	ImageButton btnMousepad,btnToggleKeyboard;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
	
		client=new ClientSocket();
		mouseClientProcess= new MouseClientProcess();
		btnMousepad=(ImageButton) findViewById(R.id.btnmouse);		
		btnToggleKeyboard=(ImageButton) findViewById(R.id.btnKeyBoard);
		btnLeftClick=(Button) findViewById(R.id.btnLeftClick);
		btnRightClick=(Button) findViewById(R.id.btnRightClick);
	
	
		btnLeftClick.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = (event.getAction() & MotionEvent.ACTION_MASK);
				switch(action)
				{
				case MotionEvent.ACTION_UP:
					 client.send(Events.MOUSE_BUTTON_UP +","+Buttons.MOUSE_BUTTON_LEFT);
					break;
				case MotionEvent.ACTION_DOWN:
					client.send(Events.MOUSE_BUTTON_DOWN +","+Buttons.MOUSE_BUTTON_LEFT);
					break;
					
				}
				return false;
			}
		});
		btnRightClick.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = (event.getAction() & MotionEvent.ACTION_MASK);
				switch(action)
				{
				case MotionEvent.ACTION_UP:
					 client.send(Events.MOUSE_BUTTON_UP +","+Buttons.MOUSE_BUTTON_RIGHT);
					break;
				case MotionEvent.ACTION_DOWN:
					client.send(Events.MOUSE_BUTTON_DOWN +","+Buttons.MOUSE_BUTTON_RIGHT);
					break;
					
				}
				return false;
			}
		});
		btnToggleKeyboard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, 0);
				
			}
		});
		btnMousepad.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent motionEvent) {
				int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
				 
			mouseClientProcess.updateCoordinate( motionEvent.getX(),  motionEvent.getY());
				     switch (action){				
				     case MotionEvent.ACTION_MOVE:								    
				     case MotionEvent.ACTION_UP:								    
				     case MotionEvent.ACTION_POINTER_UP:								    
				     case MotionEvent.ACTION_CANCEL:		
				    	 
				    	 if (mouseClientProcess.shouldDataBeSend())				
				    	 client.send(Events.MOUSE_MOVE +","+Integer.toString(mouseClientProcess.getX_difference()) + ","+
				    			 		  Integer.toString(mouseClientProcess.getY_difference()));				    	 
				    	 
				    	 
				      break;
				     
				     }
				return false;
			}
		});
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	
	 client.send(Events.KEYBORD_KEY_DOWN+","+keyCode);
	return super.onKeyDown(keyCode, event);
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
}
