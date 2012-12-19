package starparty.tactical;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.newdawn.slick.*;
import starparty.Sector;
import starparty.Station;
import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.library.Weapon;
import starparty.utilities.FontLoader;
import starparty.utilities.InterstellarObjectRepository;

public class Tactical extends Station {
  Sector sector;
  
  Collection<InterstellarObject> interstellarObjects = new ArrayList<InterstellarObject>();
  List<Weapon> weapons = new ArrayList<Weapon>();
  Player player;
  Image background;
  Radar radar;
  WeaponManager weaponManager;
  FiringControls firingControls;
  InterstellarObjectStatus objectStatus;
  
  Target target = new Target();
  // Global styles
  public static Color basicColor;
  public static Color backgroundColor;
  public static UnicodeFont titleFont;
  public static UnicodeFont basicFont;
  public static UnicodeFont smallFont;

  public Tactical(Sector sector) {
    this.sector = sector;
  }

  @Override
  public void draw(Graphics g) {
    g.drawImage(background, 0, 0);
    radar.draw(g);
    weaponManager.draw(g);
    firingControls.draw(g);
    objectStatus.draw(g);
  }

  @Override
  public void init(GameContainer gc) throws SlickException {
    gc.getGraphics().setAntiAlias(true);

    basicColor = new Color(100, 151, 244);
    backgroundColor = Color.black;

    basicFont = FontLoader.load("TCM_____.TTF", 30);
    titleFont = FontLoader.load("TCM_____.TTF", 35, true);
    smallFont = FontLoader.load("TCM_____.TTF", 15);
    
    player = sector.player;
            
    interstellarObjects = sector.interstellarObjects;

    firingControls = new FiringControls(player.ship, target);
    firingControls.setLocation(107, 473);
    firingControls.setSize(450, 214);

    weaponManager = new WeaponManager(player.ship.weapons, target);
    weaponManager.setLocation(120, 100);
    weaponManager.setSize(300, 600);

    objectStatus = new InterstellarObjectStatus(target);
    objectStatus.setLocation(608, 540);
    objectStatus.setSize(400, 146);

    radar = new Radar(player.ship, interstellarObjects, target);
    radar.setLocation(608, 96);
    radar.setSize(400, 400);

    background = new Image("resources/tactical/background.jpg");
  }

  @Override
  public void update(int delta) {
  }

  @Override
  public void keyPressed(int key, char c) {
    switch(key){
      case Input.KEY_UP:
        player.ship.getLocation().x += 25;
        break;
      case Input.KEY_DOWN:
        player.ship.getLocation().x -= 25;
        break;
      case Input.KEY_LEFT:
        player.ship.direction.addDegrees(45);
        break;
      case Input.KEY_RIGHT:
        player.ship.direction.addDegrees(-45);
        break;
    }
  }

  @Override
  public void mousePressed(int button, int x, int y) {
    weaponManager.click(x, y);
    firingControls.click(x, y);
    radar.click(x, y);
  }
}
