/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brad
 */
public class Worker extends Thread {

  public Worker() {
  }
  
  public void run(){
    System.out.println("start");
    try {
      sleep(10 * 1000);
    } catch (InterruptedException ex) {
      Logger.getLogger(Worker.class.getName()).log(Level.INFO, null, ex);
    }
    System.out.println("end");
  }
}
