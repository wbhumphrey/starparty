/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Tyler
 */
public class NameGenerator {
  private static Random r = new Random();
  private static Map<String, String[]> nameCache = new HashMap<String, String[]>();
  
  public static String generate(String type, String organization) {
    String id = type + "*" + organization;
    String[] names = nameCache.get(id);
    
    if (names == null) {
      try {
        FileReader fileReader = new FileReader("resources/interstellar_object/" + type + "/" + organization + ".txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        
        bufferedReader.close();
        nameCache.put(id, names = lines.toArray(new String[lines.size()]));
      } catch (Exception e) {
        System.out.println("Error reading in ship names: " + organization);
        e.printStackTrace();
      }
    }
    
    return names[r.nextInt(names.length)];
  }
}
