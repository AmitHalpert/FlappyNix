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
    private boolean once = false;
    private boolean isJumping = false;


    public Player(GamePanel gp, KeyboardHandling keyH, int width, int height, int x, int y) {
        super(width, height, x, y);
        this.gp = gp;
        this.keyH = keyH;
        boxCollider = new Rectangle(8, 16, width, height);


        // set player parameters
        speed = 4;


        // set player textures and animations
        Idle = new ImageIcon("src/main/resources/nix.png").getImage();

    }



    public void update(Rectangle ground, double deltaTime){


        userInput(deltaTime);

        // updates player position
        x += velX;
        y += velY;
        boxCollider.x = (int) x;
        boxCollider.y = (int) y;



        // apply gravity
        velY += 1 * deltaTime;


        if(OnCollisionEnter(boxCollider, ground)) {
            velY = 0;
        }



        velX = 0;
    }



    public void draw(Graphics2D g2){

        g2.setColor(Color.red);

        g2.fillRect(boxCollider.x, boxCollider.y, boxCollider.width, boxCollider.height);

        g2.drawImage(Idle , (int) x, (int) y, width, height, null);

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



    private boolean OnCollisionEnter(Rectangle rect1, Rectangle rect2){

        return rect1.x < rect2.x + rect2.width &&
                rect1.x + rect1.width > rect2.x &&
                rect1.y < rect2.y + (rect2.height + 3) &&
                (rect1.height + 3) + rect1.y > rect2.y;

    }



}
