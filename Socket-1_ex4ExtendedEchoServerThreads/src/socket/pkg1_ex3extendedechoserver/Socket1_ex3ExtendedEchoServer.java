/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.pkg1_ex3extendedechoserver;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author simon
 */
public class Socket1_ex3ExtendedEchoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        
        while (true) {
            new ServerTask(serverSocket.accept()).start();

        }
    }
}
