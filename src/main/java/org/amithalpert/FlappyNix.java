package org.amithalpert;// update 1-9-2022
import java.awt.*;

public class FlappyNix extends Thread {
	Player player;
	Rectangle groundCollider = new Rectangle(700, 900, 1200, 1000);
	Rectangle intersection;
	GameScreen panel;

	public FlappyNix(Player player, GameScreen p) {
		this.player = player;
		this.panel = p;
		intersection = groundCollider.intersection(player.hitBox);
		start();
	}


	public void draw(Graphics g) {

	}

	public void run() {


		while (true) {


			if (groundCollider.intersects(player.hitBox)) {
				System.out.println("GG");
				player.velY = -1;
			}


		}

	}




}
