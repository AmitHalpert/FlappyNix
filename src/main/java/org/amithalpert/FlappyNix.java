package org.amithalpert;
import java.awt.*;


public class FlappyNix extends Thread {
	Player player;
	Rectangle groundCollider = new Rectangle(700, 900, 1200, 1000);
	GameScreen panel;

	public FlappyNix(Player player, GameScreen p) {
		this.player = player;
		this.panel = p;
		start();
	}


	public void draw(Graphics g) {

	}


	public void run() {


		while (true) {


			if (groundCollider.intersects(player.hitBox)) {
				System.out.println("GG");
			}


		}

	}




}
