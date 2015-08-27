/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.pkg1_ex2timeclient;

import java.io.*;
import java.net.*;

/**
 *
 * @author simon
 */
public class Socket1_ex2TimeClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "Usage: java Socket1_ex2TimeClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket timeSocket = new Socket(hostName, portNumber);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(timeSocket.getInputStream()));) // BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)))
        {

            // Just print out the Time From the server and quit
            System.out.println(in.readLine());

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }

}
