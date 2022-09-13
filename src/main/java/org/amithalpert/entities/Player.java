package org.amithalpert.entities;

import org.amithalpert.Screens.GamePanel;
import org.amithalpert.Tools.GameObject;
import org.amithalpert.Tools.KeyboardHandling;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {

    private GamePanel gp;
    private KeyboardHandling keyH;
    private Image Idle;


    public Player(GamePanel gp, KeyboardHandling keyH, int width, int height, int x, int y) {
        super(width, height, x, y);
        this.gp = gp;
        this.keyH = keyH;
        body = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        setPlayerTextures();
    }


    public void update(){

        userInput();


        velY += 3;


        x += velX;
        y += velY;
        body.x = x;
        body.y = y;



        velX = 0;
        velY = 0;
    }


    private void setDefaultValues(){

        speed = 4;

    }

    private void setPlayerTextures(){

        Idle = new ImageIcon("src/main/resources/nix.png").getImage();

    }




    private void userInput(){
        if(keyH.upPressed){
            velY = -speed;
        }
        if (keyH.downPressed){
            velY = speed;
        }
        if(keyH.rightPressed){
            velX = speed;
        }
        if(keyH.leftPressed){
            velX = -speed;
        }
    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.red);

        g2.fillRect(body.x, body.y, width, height);

        g2.drawImage(Idle ,x, y, gp.tileSize, gp.tileSize, null);

    }



}
