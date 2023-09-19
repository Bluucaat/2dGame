package entity;

import com.bluu.cat.GamePanel;
import com.bluu.cat.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right_idle.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right_move.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left_idle.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left_move.png"));
            downIdle = ImageIO.read(getClass().getResourceAsStream("/player/cat_down_idle.png"));
            upIdle = ImageIO.read(getClass().getResourceAsStream("/player/cat_up_idle.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 30) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            if (direction.equals("down")) {
                direction = "idleDown";
            } else if (direction.equals("up")) {
                direction = "idleUp";
            } else if (direction.equals("left")) {
                direction = "idleLeft";
            } else if (direction.equals("right")) {
                direction = "idleRight";
            }
        }
    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "idleDown" -> image = downIdle;
            case "idleUp" -> image = upIdle;
            case "idleLeft" -> image = left1;
            case "idleRight" -> image = right1;
        }
        g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
    }
}
