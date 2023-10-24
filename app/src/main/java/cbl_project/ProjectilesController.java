package cbl_project;

import java.awt.Graphics2D;
import java.util.*;

import com.google.common.base.Objects;

public class ProjectilesController {
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<RocketToYellow> rocketsToYellow = new ArrayList<RocketToYellow>();
    List<RocketToRed> rocketsToRed = new ArrayList<RocketToRed>();
    GamePanel gp;
    KeyHandler keyH;
    Collider collider;
    

    public ProjectilesController(GamePanel gp, KeyHandler keyH, Collider collider) {
        this.gp = gp;
        this.keyH = keyH;
        collider.bullets = this.bullets;
        collider.rocketsToYellow = this.rocketsToYellow;
        collider.rocketsToRed = this.rocketsToRed;
        this.collider = collider;
    }

    void addBullet(GamePanel gp, KeyHandler keyH, int xStart, int yStart, int angleStart, int ownerId) {
        gp.playSoundEffect(4);
        bullets.add(new Bullet(gp, keyH, xStart, yStart, angleStart, ownerId));
    }

    void redLaunchRocket(GamePanel gp, KeyHandler keyH, int xStart, int yStart, int angleStart, int owner) {
        gp.playSoundEffect(6);
        rocketsToYellow.add(new RocketToYellow(gp, keyH, xStart, yStart, angleStart, owner));
    }

    void yellowLaunchRocket(GamePanel gp, KeyHandler keyH, int xStart, int yStart, int angleStart, int owner) {
        gp.playSoundEffect(6);
        rocketsToRed.add(new RocketToRed(this.gp, this.keyH, xStart, yStart, angleStart, owner));
    }
    void deleteProjectiles(){
        int i =0;
        while(i<bullets.size()){
            if(!bullets.get(i).insideMap()){
                bullets.remove(i);
                continue;
            }
            i++;
        }

        i =0;
        while(i<rocketsToRed.size()){
            if(!rocketsToRed.get(i).insideMap()){
                rocketsToRed.remove(i);
                continue;
            }
            i++;
        } 

        i =0;
        while(i<rocketsToYellow.size()){
            if(!rocketsToYellow.get(i).insideMap()){
                rocketsToYellow.remove(i);
                continue;
            }
            i++;
        } 
    }
    void updateProjectiles() {
        deleteProjectiles();
        System.out.println(rocketsToRed.size());
        for (Bullet bullet : bullets) {
            bullet.update();
        }
        for (RocketToRed rocket : rocketsToRed) {
            rocket.update();
        }
        for (RocketToYellow rocket : rocketsToYellow) {
            rocket.update();
        }
    }

    void drawProjectiles(Graphics2D g2) {
        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }
        for (RocketToRed rocket : rocketsToRed) {
            rocket.draw(g2);
        }
        for (RocketToYellow rocket : rocketsToYellow) {
            rocket.draw(g2);
        }
    }
}
