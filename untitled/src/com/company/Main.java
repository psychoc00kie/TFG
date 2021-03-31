package com.company;

import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) {
        try{
            Socket s=new Socket(InetAddress.getByName("192.168.1.100"),65432);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server");
            dout.flush();
            dout.close();
            s.close();
        }catch(Exception e){System.out.println(e);}
    }
}  