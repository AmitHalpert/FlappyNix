package org.amithalpert.entity;

import org.amithalpert.Screens.GamePanel;
import org.amithalpert.Tools.GameObject;
import org.amithalpert.Tools.KeyboardHandling;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject {

    private GamePanel gp;
    private KeyboardHandling keyH;
    private Image Idle;

    public Player(GamePanel gp, KeyboardHandling keyH, int width, int height, int x, int y) {
        super(width, height, x, y);
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerTextures();
    }

    private void setDefaultValues(){

        speed = 4;

    }

    private void getPlayerTextures(){


        Idle = new ImageIcon("src/main/resources/nix.png").getImage();

    }




    public void update(){

        if(keyH.upPressed){
            y -= speed;
        }
        else if (keyH.downPressed){
            y += speed;
        }
        else if(keyH.rightPressed){
            x += speed;
        }
        else if(keyH.leftPressed){
            x -= speed;
        }

    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.red);

        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        g2.drawImage(Idle ,x, y, width, height, null);

    }



}
