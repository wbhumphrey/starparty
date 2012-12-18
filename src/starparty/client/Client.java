/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import starparty.server.Server;

/**
 *
 * @author brad
 */
public class Client {
  public static void main(String[] args) throws IOException {
    //client
    Socket serverSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    //String namedHost = "127.0.0.1";
    try {
      serverSocket = new Socket(Server.address, Server.port);
//      out = new PrintWriter(serverSocket.getOutputStream(), true);
//      in = new BufferedReader(new InputStreamReader(
//              serverSocket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host: " + Server.address.getHostName());
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to: " + Server.address.getHostName());
      System.exit(1);
    }

//    BufferedReader stdIn = new BufferedReader(
//            new InputStreamReader(System.in));
//    String userInput;
//
//    while ((userInput = stdIn.readLine()) != null) {
//      out.println(userInput);
//      System.out.println("Client: " + userInput);
//    }

//    out.close();
//    in.close();
//    stdIn.close();
    serverSocket.close();

  }
}
