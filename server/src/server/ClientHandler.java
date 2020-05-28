package server;

import menu.*;
import comunication.*;

import menu.action;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Scanner;


class ClientHandler extends Thread 
{ 
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	action a = new action();
	public int clientId;
	 ObjectInputStream in = null; 
	 ObjectOutputStream out= null; 
	final Socket s; 
	public long threadId;
	public Scanner scan = new Scanner(System.in); //scanner tester for menus.

	// Constructor 
	public ClientHandler(long tid, int id,Socket s) 
	{ 
		this.threadId = tid;
		this.clientId = id;
		this.s = s; 
		this.a = new action(1,"",false,0);
		try {
			
			in = new ObjectInputStream(this.s.getInputStream());
			out = new ObjectOutputStream(this.s.getOutputStream());
			
		}catch(IOException e ) {
			
		}
	} 

	public int getClientId() {
		return clientId;
	}

	public long getThreadId() {
		return threadId;
	}

	public void setThreadId(long tid) {
		threadId = tid;
	}


	@Override
	public void run() 
	{ 
		
		
		Menu _menu = new Menu();
		
				
		action to_send = new action();
		action received = new action();
		
		
		
		try {
			
			out.writeObject(a);
			out.flush();
			
			while(true) {
				
				try {
					received = (action) in.readObject();
					
				}catch(IOException | ClassNotFoundException e) {
					
				}
				
				
				
				
				to_send = _menu.execute(received);
				
				//chec if the program has reached the end
				
				if(to_send.getActionId()<4)
					break;
				
				//sends menu execution to the box
				out.writeObject(to_send);
				out.flush();
				
				//catches box input
				
				
				
			}
		}catch(IOException e )
		{
			e.printStackTrace();
		}
		
		
		try
		{ 
			// closing resources 
			this.out.close(); 
			this.in.close(); 

		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
	} 
} 



// old code

/* comment to try more basic method
while (true) 
{ 
	
	try { 

		// Ask user what he wants 
		dos.writeUTF("What do you want?[Date | Time]..\n"+ 
				"Type Exit to terminate connection."); 

		// receive the answer from client 
		received = dis.readUTF(); 
		out = _menu.execute(Integer.parseInt(received));

		if(received.equals("Exit")) 
		{ 
			System.out.println("Client " + this.s + " sends exit..."); 
			System.out.println("Closing this connection."); 
			this.s.close(); 
			System.out.println("Connection closed"); 
			break; 
		} 

		System.out.println(out);
		//creating Date object 
		Date date = new Date(); 


		dos.writeUTF("this what the output of the menu :"
				+"\n"+out);

		// write on output stream based on the 
		// answer from the client


		switch (received) { 

		case ("Date") : 
			toreturn = fordate.format(date); 
		dos.writeUTF(toreturn); 
		break; 

		case "Stop" : 

			toreturn = fortime.format(date); 
			dos.writeUTF(toreturn); 
			break; 

		default: 
			dos.writeUTF("Invalid input"); 
			break; 
			
		} 
	} catch (IOException e) { 
		e.printStackTrace(); 
	} 
} */
