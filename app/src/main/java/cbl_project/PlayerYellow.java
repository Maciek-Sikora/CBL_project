package cbl_project;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


public class PlayerYellow {

    GamePanel gp;
    KeyHandler keyH;
    BufferedImage playerSpirit = null;
    Graphics2D playerGraphic;
    int width = 300;
    int height = 250;
    int x= 300;
    int y= 300;
    int speed = 5;

    public PlayerYellow(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        setUp();
    }

    public BufferedImage rotateImage(BufferedImage img, double degrees){
        double theta = Math.toRadians(degrees);

        AffineTransform tx = new AffineTransform();
        tx.rotate(theta, img.getWidth() / 2, img.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
        return op.filter(img, null);
    }

    void setUp(){
        try {
            playerSpirit = ImageIO.read(getClass().getResourceAsStream("/spaceship_yellow.png"));
            playerSpirit = rotateImage(playerSpirit, 90);

            System.out.println("[INFO] Player templates downloaded succesfuly");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("[ERROR] Image load failure!");
        }
    }
    void update(){
        if(keyH.up){
            y -= speed;
        }
        if(keyH.down){
            y+=speed;
        }
        if(keyH.right){
            x += speed;
        }
        if(keyH.left){
            x-=speed;
        }
    }
    void draw(Graphics2D g2){
        width = gp.getWidth()/15;
        height = gp.getHeight()/15;
        g2.drawImage(playerSpirit, x,y, width,height,null);
    } 

}
