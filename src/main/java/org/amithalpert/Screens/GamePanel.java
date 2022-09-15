package org.amithalpert.Screens;

import org.amithalpert.Tools.BoxCollider;
import org.amithalpert.Tools.KeyboardHandling;
import org.amithalpert.entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int FPS = 60;

    public final int originalTileSize = 16;
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    BoxCollider ground = new BoxCollider(0, 300, screenWidth, screenHeight);
    KeyboardHandling key = new KeyboardHandling();
    Thread gameThread;
    Player player = new Player(this, key, tileSize, tileSize, 0, 0);



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();

            deltaTime += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;


            if(deltaTime >= 1){
                update(deltaTime);
                repaint();

                deltaTime--;
            }


       }
    }


    public void update(double deltaTime){
        player.update(ground, deltaTime);

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        g2.setColor(Color.BLUE);
        g2.fillRect((int) ground.x, (int) ground.y, (int) ground.width, (int) ground.height);

        player.draw((Graphics2D) g2);

        g2.dispose();
    }


}
