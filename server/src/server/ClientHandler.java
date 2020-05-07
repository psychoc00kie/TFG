package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class ClientHandler extends Thread 
{ 
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	public int clientId;
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s; 
	

	// Constructor 
	public ClientHandler(int id,Socket s, DataInputStream dis, DataOutputStream dos) 
	{ 
		this.clientId = id;
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
	} 

	public int getClientId() {
		return clientId;
	}


	@Override
	public void run() 
	{ 
		int option = 0;
		String out = "";
		Menu _menu = new Menu(out, option);
		String received; 
		String toreturn; 
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
						
					case "Time" : 
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
		} 
		
		try
		{ 
			// closing resources 
			this.dis.close(); 
			this.dos.close(); 
			
		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
	} 
} 