package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class connection {

    Socket s;
    InetAddress ip;
    DataInputStream input;
    DataOutputStream output;
    Exception e;
    Boolean error_check;


    public connection(InetAddress ip, int type) {
        this.ip = ip;
        if (type == 1) {
            try {
                this.s = new Socket(ip, 5056);
                this.input = new DataInputStream(s.getInputStream());
                this.output = new DataOutputStream(s.getOutputStream());
            } catch (Exception e) {
                this.e = e;
                if (this.e != null)
                    this.error_check = false;
                else
                    this.error_check = true;
            }
        } else if (type == 0) {
            try {
                this.s = new Socket(ip, 10005);
                this.input = new DataInputStream(s.getInputStream());
                this.output = new DataOutputStream(s.getOutputStream());
            } catch (Exception e) {
                this.e = e;
                if (this.e != null)
                    this.error_check = false;
                else
                    this.error_check = true;
            }
        }


    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public Boolean getError_check() {
        return error_check;
    }

    public void setError_check(Boolean error_check) {
        this.error_check = error_check;
    }

    public boolean checkConnectionStatus() {

        if (this.e != null) {
            this.error_check = false;
            e.printStackTrace();
        } else
            this.error_check = true;

        return this.error_check;
    }

    public boolean sendToken(String token) {
        boolean run = true;
        String input;
        boolean result = false;
        try {
            this.output.writeUTF("Token\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        while (true) {
            try {
                input = this.input.readUTF();

                if (input.equalsIgnoreCase("Token\n")) {

                    this.output.writeUTF(token + "\n");
                    input = this.input.readUTF();
                    System.out.println("received: " + input);
                    if (input.equals(token + "\n")) {
                        System.out.println("token successfully sent!");
                        break;
                    } else if (!input.equals(token + "\n")) {
                        System.out.println("not equal");
                    }

                }
            } catch (Exception e) {
                this.e = e;

            }
        }

        return errorcheck(this.e);
    }

    public int courierDepart(String client_hash){
        int et = 0;
        try {
            output.writeUTF("cD\n");
            byte[] byte_hash = client_hash.getBytes();
            String test = new String(byte_hash);
            //System.out.println(test);
            output.write(byte_hash);
            output.writeUTF("Expecting Estimated Delivery Time\n");
            et =input.read();

            System.out.println("Estimated time for the delivery is: "+et);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return et;
    }

    public boolean errorcheck(Exception e) {
        if (e != null)
            return false;
        else
            return true;
    } 

    public boolean startAuthentication(){
        String _input = "";
        byte code=(byte)1;
        try {
            System.out.println("start");
            this.output.writeUTF("Start"+"\n");

            while ( code !=(byte) 1111){
                code = input.readByte();
                System.out.println(code);
            }
            System.out.println(code);
            if(code == (byte)1111)
                return true;
            if(code == (byte)1001)
                return false;

            System.out.println("exit");

        } catch (IOException ioException) {
            ioException.printStackTrace();
            if((code != (byte)1111)||(code != (byte)1001))
                System.out.println("Wrong code");
        }

        return true;
    }

    public void protocol() {
        try {
            Scanner scn = new Scanner(System.in);


            while (true) {
                System.out.println(this.input.readUTF());
                String tosend = scn.nextLine();
                this.output.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if (tosend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = input.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            //dis.close();
            //dos.close();
        } catch (Exception e) {

            //e.printStackTrace();
            String msg = e.getMessage();
            if (msg.equalsIgnoreCase("Connection refused: connect"))
                System.out.println("conection fail true");

        }
    }

    public boolean closeConnection() {
        try {
            this.output.writeUTF("Exit\n");
            this.output.close();
            this.input.close();
            this.s.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return true;
    }
}
