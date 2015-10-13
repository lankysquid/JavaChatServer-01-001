
package chatserverapp;

/**
 *
 * @author Stefan Webster
 */
import java.util.Scanner;


public class ChatServerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter desired chat port: ");
        Scanner keyboard = new Scanner(System.in);
        int port = keyboard.nextInt();
        ChatServer server = new ChatServer(port);
        System.out.println("Server open on port " + port);
        System.out.println("System End.");      
    }
}
