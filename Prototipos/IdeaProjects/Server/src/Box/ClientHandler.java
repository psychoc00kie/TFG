package Box;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

                    case "Start\n" :
                        dos.writeUTF("Start scanning\n");
                        long start = System.currentTimeMillis();
                        int sec = 5;    // how many seconds we give the scanner.
                        long end = start + sec*1000;
                        Random rand = new Random();    // random probability of scanning we set to 90%
                        byte result = (byte) 1001;  // set to fail scan by default
                        while (System.currentTimeMillis() < end) {      //We emulate the scanning time
                            // Some expensive operation on the item.

                            if(rand.nextInt(100)<2) {
                                result = (byte)1111;
                                System.out.println("Successful Scan");
                                break;
                            } else
                                System.out.println("Keep Scanning");
                        }
                        dos.write((byte)1111);
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