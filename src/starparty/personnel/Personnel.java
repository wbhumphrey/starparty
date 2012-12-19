/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.*;
import starparty.Station;
import starparty.library.Schematic;
import starparty.library.ShipInternals;
import starparty.library.Team;
import starparty.utilities.FontLoader;
import starparty.utilities.ImageLoader;

/**
 *
 * @author Tyler
 */
public class Personnel extends Station {
  public static int mouseX;
  public static int mouseY;
  
  public static Color basicColor;
  public static Color backgroundColor;
  public static UnicodeFont titleFont;
  public static UnicodeFont basicFont;
  public static UnicodeFont smallFont;
  
  TeamSelection selectedTeam;
  List<Team> teams;
  NodeSelection selectedNode;
  
  Image background;
  Schematic schematic;
  ShipInternals shipInternals;
  
  TeamControls teamControls;
  PersonnelDisplay personnelDisplay;
  RoomControls roomControls;
  
  public Personnel() {

  }
  
  @Override
  public void init(GameContainer gc) throws SlickException {
    basicColor = new Color(100, 151, 244);
    backgroundColor = Color.black;

    basicFont = FontLoader.load("TCM_____.TTF", 30);
    titleFont = FontLoader.load("TCM_____.TTF", 35, true);
    smallFont = FontLoader.load("TCM_____.TTF", 15);
    
    background = ImageLoader.load("personnel/background.jpg");
    
    Gson gson = new Gson();
    try {
      schematic = gson.fromJson(new FileReader("resources/personnel/schematic.json"), Schematic.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    shipInternals = schematic.getShipInternals();
    
    selectedTeam = new TeamSelection();
    selectedNode = new NodeSelection();
    
    teams = new ArrayList<Team>();
    teams.add(selectedTeam.team = new Team("Admiral Tyler", shipInternals.nodes.get(1)));
    teams.add(new Team("Ensign Brad", shipInternals.nodes.get(1)));
    teams.add(new Team("Ensign Michael", shipInternals.nodes.get(1)));
    teams.add(new Team("Ensign Whitney", shipInternals.nodes.get(1)));
    teams.add(new Team("Ensign Lindsay", shipInternals.nodes.get(1)));
    
    teamControls = new TeamControls();
    teamControls.setLocation(771, 53);
    teamControls.setSize(242, 117);
    teamControls.setShipInternals(shipInternals);
    teamControls.setSelectedNode(selectedNode);
    teamControls.setSelectedTeam(selectedTeam);
    
    personnelDisplay = new PersonnelDisplay(shipInternals, teams);
    personnelDisplay.setLocation(120, 261);
    personnelDisplay.setSize(1000, 700);
    personnelDisplay.setSelectedNode(selectedNode);
    
    roomControls = new RoomControls();
    roomControls.setLocation(93, 553);
    roomControls.setSize(183, 3);
    roomControls.setRooms(shipInternals.rooms.subList(0, 12));
    roomControls.setSelectedNode(selectedNode);
  }

  @Override
  public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    mouseX = newx;
    mouseY = newy;
  }

  @Override
  public void update(int delta) {
    selectedTeam.team.update(delta);
  }

  @Override
  public void draw(Graphics g) {
    g.drawImage(background, 0, 0);

    teamControls.draw(g);
    personnelDisplay.draw(g);
  }

  @Override
  public void mousePressed(int button, int x, int y) {
    teamControls.click(x, y);
    roomControls.click(x, y);
    personnelDisplay.click(x, y);
  }
}
