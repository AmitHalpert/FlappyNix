package main;

import tools.Coords;
import tools.Server;

import java.util.ArrayList;

public class Game implements Runnable {


    private Thread gameThread;
    public  ArrayList<Server> servers;

    public Game() {


        servers = new ArrayList<>();
        servers.add(new Server(5000));
        servers.add(new Server(6000));


        gameThread = new Thread(this);
        gameThread.start();


        for(Server s : servers){
            s.start();
        }
        for(Server s : servers){
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / 120;
        double timePerUpdate = 1000000000.0 / 200;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {


            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                if(servers.get(0).coords != null && servers.get(1).coords != null) {
                    System.out.println("PLAYER 0" + servers.get(0).coords.x);
                    System.out.println("PLAYER 1" + servers.get(1).coords.x);
                }
                frames = 0;
                updates = 0;

            }
        }

    }
}
