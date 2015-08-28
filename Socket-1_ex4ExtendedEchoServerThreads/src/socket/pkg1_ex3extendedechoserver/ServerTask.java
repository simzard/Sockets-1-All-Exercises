/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.pkg1_ex3extendedechoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class ServerTask extends Thread {

    private static List<PrintWriter> outs = new ArrayList();

    private static Map<String, String> dictionary = new HashMap();

    private static int id = 0;

    static {
        dictionary.put("hund", "dog");
        dictionary.put("hus", "house");
        dictionary.put("skole", "school");
        dictionary.put("sej", "cool");
    }
    
    
    private Socket clientSocket;
    private int number;

    public int getNumber() {
        return number;
    }

    public ServerTask(Socket s) {
        clientSocket = s;
        number = id++;
        // when making a task increment the output streams by one
        try {
            outs.add(new PrintWriter(s.getOutputStream(), true));
            //ins.add(new BufferedReader(new InputStreamReader(allSockets.get(i).getInputStream())));

        } catch (IOException ex) {
            Logger.getLogger(ServerTask.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // send the message to all the connected clients
    public void printAll(String string) {
        for (PrintWriter os : outs) {
            os.println(string);
        }
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));) {
            String inputLine;
            String outputLine = null;
            // send the id to the client - so the client can print its id
            out.println(number);
                        
//            out.println();
//            out.println("Welcome to the extended echo server...try:");
//            out.println("UPPER#content - writes content in uppercase");
//            out.println("LOWER#content - writes content in lowercase");
//            out.println("REVERSE#content - writes the reverse of content");
//            out.println("TRANSLATE#danishword - writes english translation");
//            out.println("(I'm not very smart! :( )");
//            out.println("QUIT - exits");
//            out.println("");
            while ((inputLine = in.readLine()) != null) {
                printAll("c" + number + ": " + inputLine);
                if (inputLine.equals("QUIT")) {
                    printAll("c" + number + " is exiting gracefully...");
                    out.println("QUIT");
                    
                    return;

                }
                String[] tokens = inputLine.split("#");
                if (tokens.length != 2) {
                    
                    printAll("S: Unknown command... Type QUIT to quit");
                    //out.println("Unknown command... Type QUIT to quit");
                } else {

                    String command = tokens[0];
                    String content = tokens[1];

                    switch (command) {
                        case "UPPER":
                            outputLine = content.toUpperCase();
                            break;
                        case "LOWER":
                            outputLine = content.toLowerCase();
                            break;
                        case "REVERSE":
                            outputLine = new StringBuilder(content).reverse().toString();
                            break;
                        case "TRANSLATE":
                            if (dictionary.containsKey(content)) {
                                outputLine = dictionary.get(content);
                            } else {
                       
                                printAll("S: I don't know how to translate '" + content + "'");
                                //out.println("I don't know how to translate '" + content + "'");
                            }
                            break;

                        default:
                            
                            printAll("S: Unknown command... type QUIT to quit");
                        //out.println("Unknown command... type QUIT to quit");
                    }

                }
                if (outputLine != null) {
                    
                    printAll("S: " + outputLine);
                    //out.println(outputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + "or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
