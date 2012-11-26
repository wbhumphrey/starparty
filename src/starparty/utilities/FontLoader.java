/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import java.awt.Font;
import java.io.File;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 *
 * @author Tyler
 */
public class FontLoader {
  public static UnicodeFont load(String name, int size) {
    UnicodeFont font = null;
    
    try {
      Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/" + name));
      baseFont.deriveFont(java.awt.Font.PLAIN, 12);
      
      font = new UnicodeFont(baseFont.deriveFont(java.awt.Font.PLAIN, size));
      font.addAsciiGlyphs();
      font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
      font.loadGlyphs();
    } catch (Exception e) {
      System.out.println("Error loading font: " + name);
      e.printStackTrace();
    }
    
    return font;
  }
}
