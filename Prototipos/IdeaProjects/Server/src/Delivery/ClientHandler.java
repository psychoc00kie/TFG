package Delivery;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// ClientHandler class
class ClientHandler extends Thread
{
    DateFormat _date = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat _time = new SimpleDateFormat("hh:mm:ss");
    String token = "";
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run()
    {
        System.out.println("New connection from " + this.s);
        String received;
        String toreturn;
        Boolean stop = true;
        while (stop)
        {
            try {
                //fetch protocol option:

                // receive the answer from client
                received = dis.readUTF();

                if(received.equalsIgnoreCase("Exit\n"))
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // creating Date object
                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                System.out.println("reading:"+received.toString());
                switch (received) {

                    case "cD\n" :       //Courier Departure
                        byte[] byte_hash = new byte[64];
                        for(int i =0;i<64;i++)
                            byte_hash[i]=dis.readByte();
                        String client_hash = new String(byte_hash);
                        System.out.println("This is the Clients hash Code: "+client_hash);
                        String msg = dis.readUTF();
                        /*
                        Code where we call the WEB Servide for the Clients Address and calculate the ET (Estimated time in ms)
                         */

                        int estimatedTime = 5*1000;
                        System.out.println(msg+" Sending: "+ estimatedTime);
                        dos.writeInt(estimatedTime);
                        break;

                    case "Token\n" :
                        this.dos.writeUTF("Token\n");
                        System.out.println("entering token");
                        this.token=dis.readUTF();
                        System.out.println("reading:"+this.token);
                        dos.writeUTF(this.token);
                        dos.writeUTF("\n");
                        System.out.println("the obtained token was:"+this.token);
                        break;

                    case "Time" :
                        toreturn = _time.format(date);
                        dos.writeUTF(toreturn);
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
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