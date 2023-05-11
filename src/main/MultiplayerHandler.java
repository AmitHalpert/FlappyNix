package main;

import tools.Coords;
import tools.Server;

import java.util.ArrayList;

public class MultiplayerHandler implements Runnable {


    private Thread gameThread;
    public  ArrayList<Server> servers;

    public MultiplayerHandler() {


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


    public void update(){
        if(servers.get(0).coords != null && servers.get(1).coords != null) {

            Coords coords0 = new Coords(servers.get(0).coords.x, servers.get(0).coords.y);
            servers.get(1).setWriteObject(coords0);


            Coords coords1 = new Coords(servers.get(1).coords.x, servers.get(1).coords.y);
            servers.get(0).setWriteObject(coords1);

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
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;

            }
        }

    }
}
