/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.pkg1_ex1timeserver;

import java.net.*;
import java.io.*;
import java.util.Date;

/**
 *
 * @author simon
 */
public class Socket1_ex1TimeServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Socket1_ex1TimeServer <host address> <port number>");
            System.exit(1);
        }
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        PrintWriter out = null;
        BufferedReader in = null;
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        
        try {
                serverSocket = new ServerSocket();
                serverSocket.bind(new InetSocketAddress(hostName, portNumber));
                clientSocket = serverSocket.accept();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())); 

            // this just prints the current date to the client and shuts down
            out.println(new Date());
            
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            
            } catch (Exception e) {
                
            }
        }
    }

}


