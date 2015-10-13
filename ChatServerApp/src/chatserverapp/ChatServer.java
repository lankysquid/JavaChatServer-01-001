/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverapp;

import java.io.DataInputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.*;

/**
 *
 * @author stefan
 */
public class ChatServer{
    
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream streamIn = null;
    
    public ChatServer (int port)
    {
       try
       {
           System.out.println("Binding to port " + port + ", please wait ...");
           server = new ServerSocket(port);
           System.out.println("Server started: " + server
                + "\nWating for client ...");
           socket = server.accept();
           System.out.println("Client accepted: " + socket);
           open();
           boolean done = false;
           while (!done)
           {
               try
               {
                   String line = streamIn.readUTF();
                   System.out.println("Server: " +line);
                   done = line.equals(".bye");
               }
               catch (IOException ioe)
               {
                   done = true;
               }
            }
           close();
       }
       catch(IOException ioe)
       {
           System.out.println(ioe);
       }
        
    }
    public void open() throws IOException
    {
        streamIn = new DataInputStream (new BufferedInputStream(socket.getInputStream()));
        
    }
    public void close() throws IOException
    {
        if(socket != null) socket.close();
        if(streamIn != null) streamIn.close();
    }
   
}
