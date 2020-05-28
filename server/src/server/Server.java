package server;
// Java implementation of Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 

import java.io.*; 
//import java.text.*; 
import java.util.*; 
import java.net.*; 

// Server class 
public class Server 
{ 
	public static Thread t ;
	public static ServerSocket ss;
	public static int exit = 0;
	
	public static void main(String[] args) throws IOException 
	{ 
		// server is listening on port 5056 
		
		ss = new ServerSocket(9876); 
		
		//LinkedList<ClientDB> _clientDB = new LinkedList<ClientDB>();
		
		
		boolean running = true;
		int clientcount= 0;
		LinkedList<ClientHandler> _clientList = new LinkedList<ClientHandler>();
		
		
		// running infinite loop for getting 
		// client request 
		while (running) 
		{ 
			Socket s = null; 
			
			try
			{ 
				// socket object to receive incoming client requests 
				s = ss.accept(); 
				clientcount ++;
				System.out.println("A new client is connected : " + s); 
			
			
				System.out.println("Assigning new thread for this client"+s.getLocalAddress().getHostAddress()); 

				// create a new thread object 
				ClientHandler aux = new ClientHandler(0,clientcount,s); 
				_clientList.add(aux);
				t = aux;
				long tid= t.getId();
				_clientList.getLast().setThreadId(tid);
				

				// Invoking the start() method 
				t.start(); 
				if (exit ==1)
				{
					running = false;
				}
				
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
		t = null;
		ss.close();
	} 
	
	public void stopThread(Thread t)
	{
		t = null;
	}
	
} 
