package org.amithalpert.Screens;

import org.amithalpert.Tools.KeyboardHandling;
import org.amithalpert.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    int FPS = 60;

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyboardHandling key = new KeyboardHandling();
    Thread gameThread;
    Player player = new Player(this, key, 100, 100, 0, 0);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
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
                update();
                repaint();

                deltaTime--;
            }


       }
    }


    public void update(){
        player.update();

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        player.draw((Graphics2D) g2);


        g2.dispose();
    }


}
