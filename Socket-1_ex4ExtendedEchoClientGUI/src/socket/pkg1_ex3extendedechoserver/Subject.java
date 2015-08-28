/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.pkg1_ex3extendedechoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class Subject extends Observable implements Runnable {

    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public Subject(Socket s) {
        socket = s;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to host name");

            System.exit(1);
        }
    }

    @Override
    public void run() {
        // get the next server message
        // if it is a special "UPDATE" notify the observers
        String result = null;
        while (true) {
            try {

                result = in.readLine();
                if (result.equals("QUIT")) {
                    System.exit(0);
                }
            } catch (IOException ex) {
                Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (result != null) {
                setChanged(); // something has happened!
                notifyObservers(result);
            }
        }
    }
}
