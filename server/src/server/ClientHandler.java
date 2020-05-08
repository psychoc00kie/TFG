package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Scanner;


class ClientHandler extends Thread 
{ 
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	public int clientId;
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s; 
	public long threadId;
	public Scanner scan = new Scanner(System.in); //scanner tester for menus.

	// Constructor 
	public ClientHandler(long tid, int id,Socket s, DataInputStream dis, DataOutputStream dos) 
	{ 
		this.threadId = tid;
		this.clientId = id;
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
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
		//int option = 0;
		//String out = "";
		//Menu _menu = new Menu(out, option);
		//String received; 
		//String toreturn; 
		
		try {
			while(true) {
				String clientResponse = scan.nextLine();
				if(clientResponse.contentEquals("exit"))
				{
					this.dis.close(); 
					this.dos.close(); 
					Server.exit = 1;
					break;
				}
				dos.writeUTF(clientResponse);
			}
		}catch(IOException e )
		{
			e.printStackTrace();
		}
		
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