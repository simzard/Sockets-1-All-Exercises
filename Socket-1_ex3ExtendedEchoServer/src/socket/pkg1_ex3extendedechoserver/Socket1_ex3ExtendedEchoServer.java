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

        Map<String, String> dictionary = new HashMap();
        dictionary.put("hund", "dog");
        dictionary.put("hus", "house");
        dictionary.put("skole", "school");
        dictionary.put("sej", "cool");

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
                ServerSocket serverSocket
                = new ServerSocket(Integer.parseInt(args[0]));
                Socket clientSocket = serverSocket.accept();
                PrintWriter out
                = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));) {
            String inputLine;
            String outputLine = null;
            out.println("Welcome to the extended echo server...try:");
            out.println("UPPER#content - writes content in uppercase");
            out.println("LOWER#content - writes content in lowercase");
            out.println("REVERSE#content - writes the reverse of content");
            out.println("TRANSLATE#danishword - writes english translation");
            out.println("(I'm not very smart! :( )");
            out.println("QUIT - exits");
            out.println("");
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("QUIT")) {
                    out.println("Exiting gracefully...");
                    System.exit(0);

                }
                String[] tokens = inputLine.split("#");
                if (tokens.length != 2) {
                    out.println("Unknown command... Type QUIT to quit");
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
                                out.println("I don't know how to translate '" + content + "'");
                            }
                            break;

                        default:
                            out.println("Unknown command... type QUIT to quit");
                    }

                }
                if (outputLine != null) {
                    out.println(outputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
