// Java implementation for a client 
// Save file as Client.java 

package client;

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
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
			Socket s = new Socket(ip, 5056); 
	
			// obtaining input and out streams 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			
			//Sending to the server Box information details to subscribe as a client. 
			
			InetAddress machineIp = InetAddress.getLocalHost();
			System.out.println("this is the machines ip address:"+machineIp.getHostAddress() +"\n");
			/*
			String info = machineIp.getHostAddress();
			System.out.println("enter users name:");
			info = info + ","+scn.nextLine();
			System.out.println("enter users country");
			info = info + ","+scn.nextLine();
			dos.writeUTF(info);
			*/
			
			// the following loop performs the exchange of 
			// information between client and client handler 
			while (true) 
			{
				
				System.out.println(dis.readUTF()); 
				String tosend = scn.nextLine(); 
				dos.writeUTF(tosend); 
				
				// If client sends exit,close this connection 
				// and then break from the while loop 
				if(tosend.equals("Exit")) 
				{ 
					System.out.println("Closing this connection : " + s); 
					s.close(); 
					System.out.println("Connection closed"); 
					break; 
				} 
				
				// printing date or time as requested by client 
				String received = dis.readUTF(); 
				System.out.println(received); 
			} 
			
			// closing resources 
			scn.close(); 
			dis.close(); 
			dos.close(); 
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
	} 
} 
