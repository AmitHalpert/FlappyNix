package main;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import static tools.HelpMethods.*;

import entities.Player;
import levels.LevelManager;

import static tools.Constants.GameLoopConstants.*;


public class Game implements Runnable {

	private Desktop desktop;
	private GamePanel gamePanel;
	private Thread gameThread;
	private static ArrayList<Player> players;
	private LevelManager levelManager;

	// global constants
	public final static int NUM_PLAYER = 2;
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	public Game() {
		// initializing classes
		levelManager = new LevelManager(this);

		players = new ArrayList<>();
		for(int i = 0; i < NUM_PLAYER; i++){
			players.add(new Player(500 + i * 90, 200, (int) (64 * SCALE), (int) (40 * SCALE)));
			players.get(i).loadLvlData(levelManager.getCurrentLevel().getLevelData());
		}

		gamePanel = new GamePanel(this);
		desktop = new Desktop(gamePanel);
		gamePanel.requestFocus();

		// starting gameThread
		startGameLoop();
	}

	private synchronized void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();

		// start players threads
		for(Player player : players){
			player.start();
		}

		for(Player player : players){
			try {
				player.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void update() {
		levelManager.update();


		///////////
		// players jumping in top of each other collision
		///////////
		Rectangle2D.Float playerHead0 = new Rectangle2D.Float(players.get(0).getHitbox().x + 9, players.get(0).getHitbox().y, players.get(0).getHitbox().width - 14,players.get(0).getHitbox().height - (players.get(0).getY() / 2) + 55);
		Rectangle2D.Float playerHead1 = new Rectangle2D.Float(players.get(1).getHitbox().x + 9, players.get(1).getHitbox().y, players.get(1).getHitbox().width - 14,players.get(1).getHitbox().height - (players.get(1).getY() / 2) + 55);
		// checks for collision between the players heads, to not get stuck mid air
		if(!playerHead0.intersects(playerHead1)) {
			// player 0
			if (players.get(0).getHitbox().intersects(playerHead1)){
				players.get(0).setVelY(0);
				players.get(0).setInAir(false);
			} else if (!IsEntityOnFloor(players.get(0).getHitbox(), levelManager.getCurrentLevel().getLevelData())) {
				players.get(0).setInAir(true);
			}
			// player 1
			if (players.get(1).getHitbox().intersects(playerHead0)){
				players.get(1).setVelY(0);
				players.get(1).setInAir(false);
			} else if (!IsEntityOnFloor(players.get(1).getHitbox(), levelManager.getCurrentLevel().getLevelData())) {
				players.get(1).setInAir(true);
			}
		}



	}

	// paint objects (called by paintComponent in gamePanel)
	public void render(Graphics g) {
		levelManager.draw(g);
		for(Player player : players){
			player.render(g);
		}
	}


	// main game loop
	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

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
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}

	public void windowFocusLost() {
		for(Player player : players){
			player.resetDirBooleans();
		}
	}


	public static ArrayList<Player> getPlayers() {
		return players;
	}

}