package com.project.remoteserver;

import java.awt.AWTError;
import java.awt.Event;
import java.awt.Robot;
import java.awt.event.AWTEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.project.remoteprotocol.global.Events;




public class ServerConsole    {

	static Robot robot=null;
	static InputEvents inpuItEvents=null;
	public static void main(String[] args) throws IOException {
		    
		try{
			robot= new Robot();
			inpuItEvents=new InputEvents(robot);


		ServerSocket listener= new ServerSocket(8081);
		//listener.setSoTimeout(10000);
		InetAddress IP=InetAddress.getLocalHost();
		System.out.println("Server IP Address="+(IP.getHostAddress()));
		System.out.println("WATING FOR CLIENT ");

		try{
			while(true){
				new ClientDealer(listener.accept()).start();				
			}

		}
		finally{
			listener.close();
		}
	}		
		catch(Exception e){}
		
	}

	private static class ClientDealer extends Thread {
		private Socket socket;

		public ClientDealer(Socket socket){
			this.socket=socket;			 
		}			


		public void run() {
			try{
				System.out.println("client found");
				BufferedReader in =new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				System.out.println("start");
				while (true){
					try{
					String input=in.readLine();
					System.out.println(input);
					if (input == null ) {
						break;
					}
					String data[]=input.split(",");
					int key1=Integer.parseInt(data[1]);
				
					
					switch (Integer.parseInt(data[0]))
					{
					
					case Events.SINGLE_BUTTON_PRESS:					
						try{
						inpuItEvents.keyClick(key1);
						}catch(Exception e){e.printStackTrace();}
						break;
					case Events.MOUSE_MOVE:						
						int mouse_x = key1;
						int mouse_y = Integer.parseInt(data[2]);												
							inpuItEvents.mouseMove(mouse_x,mouse_y);						
						break;
					case Events.MOUSE_CLICK:						
						inpuItEvents.mouseClick(key1);
						break;
					case Events.BUTTON_PRESS:
						inpuItEvents.keyPress(key1);
						break;
						
					case Events.BUTTON_RELEASE:
						inpuItEvents.keyRelease(key1);
						break;
						
					case Events.MOUSE_BUTTON_DOWN:
						inpuItEvents.mouseButtonDown(key1);
						break;
						
					case Events.MOUSE_BUTTON_UP:
						inpuItEvents.mouseButtonUp(key1);
						break;
					
					case Events.COMBINATION_BUTTON_PRESS:
						int key2=Integer.parseInt(data[2]);
						inpuItEvents.keyClick(key1,key2);
						break;
					case Events.KEYBORD_KEY_DOWN:
						inpuItEvents.keybordButton(key1);
						break;
					};
					}catch (Exception e){e.printStackTrace();}
							
					
					

				}
			} catch(Exception e){
				e.printStackTrace();
			} finally{
				try {
					
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

