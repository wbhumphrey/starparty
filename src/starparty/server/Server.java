/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brad
 */
public class Server {

  public static int port = 4444;
  public static InetAddress address;

  static {
    try {
      address = Inet4Address.getByName("localhost");
    } catch (UnknownHostException ex) {
      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(-1);
    }
  }

  public static void main(String[] args) throws IOException {
    //server
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
      System.out.println("Server started");
      
      while(true){
        Socket clientSocket = serverSocket.accept();
        System.out.println("Accepted Client");
        new Worker().start();
      }
//      PrintWriter out  = new PrintWriter(clientSocket.getOutputStream(), true);
//      BufferedReader in =
//              new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//      String inputLine, outputLine;
      
//      while((inputLine = in.readLine()) != null){
//        System.out.println(inputLine);
//      }
    } catch (IOException e) {
      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
      System.out.println("Could not listen on port: " + port);
      System.exit(-1);
    }
  }
}
