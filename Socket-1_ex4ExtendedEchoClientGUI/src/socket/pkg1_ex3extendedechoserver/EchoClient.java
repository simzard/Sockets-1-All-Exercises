/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.pkg1_ex3extendedechoserver;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        Socket echoSocket = new Socket(hostName, portNumber);
        new NewJFrame(echoSocket).setVisible(true);
        
        
    }
        
}