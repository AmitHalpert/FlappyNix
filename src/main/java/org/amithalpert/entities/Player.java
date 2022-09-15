package org.amithalpert.entities;

import org.amithalpert.Screens.GamePanel;
import org.amithalpert.Tools.BoxCollider;
import org.amithalpert.Tools.GameObject;
import org.amithalpert.Tools.KeyboardHandling;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {

    private GamePanel gp;
    private KeyboardHandling keyH;
    private Image Idle;
    private boolean once = false;
    private boolean isJumping = false;


    public Player(GamePanel gp, KeyboardHandling keyH, int width, int height, double x, double y) {
        super(width, height, x, y);
        this.gp = gp;
        this.keyH = keyH;
        boxCollider = new BoxCollider(8, 16, width, height);


        // set player parameters
        speed = 4;


        // set player textures and animations
        Idle = new ImageIcon("src/main/resources/nix.png").getImage();

    }



    public void update(BoxCollider ground, double deltaTime){


        userInput(deltaTime);

        // updates player position
        x += velX;
        y += velY;
        boxCollider.x =  x;
        boxCollider.y =  y;



        // apply gravity
        velY += 1 * deltaTime;


        if(boxCollider.overlaps(ground)) {
            velY = 0;
        }

        velX = 0;
    }



    public void draw(Graphics2D g2){

        g2.setColor(Color.red);

        g2.fillRect((int) boxCollider.x, (int) boxCollider.y, (int) boxCollider.width, (int) boxCollider.height);

        g2.drawImage(Idle , (int) x, (int) y, (int) width, (int) height, null);

    }




    private void userInput(double deltaTime){
        if(keyH.upPressed){
            if(!isJumping){
                velY = -15 * deltaTime;
                isJumping = true;
            }
        }
        if(keyH.rightPressed){
            velX = speed * deltaTime;
        }
        if(keyH.leftPressed){
            velX = -speed * deltaTime;
        }


        if(!keyH.upPressed){
            isJumping = false;
        }
    }


}
