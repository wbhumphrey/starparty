/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import org.newdawn.slick.Image;

/**
 *
 * @author Tyler
 */
public class ImageLoader {
  public String path;
  private Image image;
  
  public Image getImage(){
    image = (image == null ? load(path) : image);
    return image;
  }
            
  public static Image load(String image) {
    try {
      return new Image("resources/" + image);
    } catch (Exception e) {
      System.out.println("Error loading image: resources/" + image);
      e.printStackTrace();
    }
    
    return null;
  }
}
