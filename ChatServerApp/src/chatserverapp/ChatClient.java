/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverapp;
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author stefan
 */
public class ChatClient {
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    
    public ChatClient (String serverName, int serverPort)
    {
        System.out.println("Establishing connection. please wait ...");
        try 
        {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        }
        catch(UnknownHostException uhe)
        {
            System.out.println("Host Unknown: " + uhe.getMessage());  
        }
        catch (IOException ioe)
        {
            System.out.println("Unexpected Exception: " + ioe.getMessage());
        }
        String line = "";
        while (!line.equals(".bye"))
        {
            try
            {
                System.out.println("You: ");
                Scanner keyboard = new Scanner(System.in);
                line = keyboard.nextLine();
                streamOut.writeUTF(line);
                streamOut.flush();
            }
            catch (IOException ioe)
            {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
    }
    public void start() throws IOException
    {
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }
    public void strop()
    {
        try
        {
            if (console != null) console.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        }
        catch (IOException ioe)
        {
            System.out.println("Error cloasing ...");
        }
        
    }
       public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter desired chat port: ");
        Scanner keyboard = new Scanner(System.in);
        int port = keyboard.nextInt();
        System.out.println( "Opening Chat client on port " + port);
        ChatClient client = new ChatClient("localHost", port);
        System.out.println("System End.");      
    }
}
