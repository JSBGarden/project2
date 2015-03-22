package com.project.remoteclient;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{
	
	String list[][]={{"MouseActivity","PowerPointRemoteActivity","VlcRemote","Test"},{"Mouse and Keyboard","Power-Point Remote","vlc Remote","Test"}};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1 ,list[1]));
	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String loadlist=list[0][position];
		try{	
		Class myclass=Class.forName("com.project.remoteclient."+loadlist);
		Intent myIntent=new Intent(Menu.this,myclass);
		startActivity(myIntent);
		//startActivityForResult(myIntent, 6);
			
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			}
		}
		

	

}
