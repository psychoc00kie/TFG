// Java implementation for a client 
// Save file as Client.java 

package client;



import java.io.*; 
import java.net.*; 
import java.util.Scanner;

import menu.Menu;
import menu.action;

import java.net.InetAddress;

// Client class  Cada cliente hace referencia a un buzon que se encontrara en las casas de los clientes, estos estan conectados aun servidor ,
// que les ira proporcionando comandos segun evolucione el proceso de entrega . 
public class client 
{ 
	
	
	public static void main(String[] args) throws IOException 
	{ 
		try
		{ 
			Scanner scn = new Scanner(System.in); 
			
			// getting localhost ip 
			InetAddress ip = InetAddress.getByName("localhost"); 
	
			// establish the connection with server port 5056 
			Socket s = new Socket(ip, 9876); 
	
			// obtaining input and out streams 
			ObjectInputStream in = new ObjectInputStream(s.getInputStream()); 
			ObjectOutputStream out= new ObjectOutputStream(s.getOutputStream());
			//Sending to the server Box information details to subscribe as a client. 
			
			InetAddress machineIp = InetAddress.getLocalHost();
			System.out.println("this is the machines ip address:"+machineIp.getHostAddress() +"\n");
			
			
			// the following loop performs the exchange of 
			// information between client and client handler 
			
			action received = new action();
			action to_send = new action();
			Menu _menu = new Menu();
			
			while (true) 
			{
				received = (action) in.readObject();
				
				if(received.getActionId()<4)
					break;				
				
				to_send = _menu.execute(received);
				
				
				out.writeObject(to_send);
				out.flush();
				
				
			} 
			
			// closing resources 
			//scn.close(); 
			in.close();
			out.close();
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
	} 
} 
