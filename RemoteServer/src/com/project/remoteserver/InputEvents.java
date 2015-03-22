package com.project.remoteserver;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import com.project.remoteprotocol.global.Buttons;

public class InputEvents {
	public Robot robot;

	//consturctor
	public InputEvents(Robot robot) {
		this.robot=robot;
	}


	public void keyPress(int key1){
		try {     
			Robot robot = new Robot();
			robot.keyPress(key1);			
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void keyRelease(int key1){
		try {     
			Robot robot = new Robot();
			robot.keyRelease(key1);			
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
	}
	//if single key is clicked
	public void keyClick(int key1){
		try {     
			Robot robot = new Robot();
			robot.keyPress(key1);
			robot.keyRelease(key1);
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
	}

	// if two key are to be clicked as once
	public void keyClick(int key1,int key2){
		try {
			Robot robot = new Robot();
			robot.keyPress(key1);
			robot.keyPress(key2);
			robot.keyRelease(key2);
			robot.keyRelease(key1);
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
	}
	public void mouseMove(int x, int y)	  {
		PointerInfo mousePointInfo = MouseInfo.getPointerInfo();
		Point mousePosition = mousePointInfo.getLocation();
		int x_old = (int) mousePosition.getX();
		int y_old = (int) mousePosition.getY();
		robot.mouseMove(x+x_old, y+y_old); 	
	}
	
	public void mouseClick(int mouse_button)	  {
		robot.mousePress(mouse_button);
		robot.mouseRelease(mouse_button);		
	}

	public void mouseButtonDown(int mouse_button)	  {
		robot.mousePress(mouse_button);				
	}
	public void mouseButtonUp(int mouse_button)	  {
		robot.mouseRelease(mouse_button);				
	}
	
	
	
	
	public void keybordButton(int key1){
		if (key1>=29 && key1<=54){
			keyClick(key1+36);//key1+Buttons.KEY_A-29);
			/*for (int i=1 ;i<90;i++){
				try{
					keyClick(i);				
				}
				catch (Exception e)
				{
					System.out.println(i+"");
				}
			}
			*/
		}
			
	}
	
}
