package org.amithalpert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Player extends Thread implements KeyListener {
	GameScreen panel;

	private int playerWidth = 130, playerHeight = 115;
	private int playerX = 800, playerY;
	private int velX = 0, velY = 0;
	Rectangle hitBox = new Rectangle(0, 0, playerWidth, playerHeight);
	Image Texture;


	public Player(GameScreen panel)
	{
		this.panel=panel;
		Texture = new ImageIcon("src/main/resources/nix.png").getImage();

		start();
	}
	
	public void run()
	{
		while(true)
		{


			updatePlayer();
			velY  += 1.0;


		   try {
			   	Thread.sleep(20);
		   	   }catch (InterruptedException e) {}
				
			panel.repaint();
		}
	}

	private void updatePlayer() {

		//Updates X AND Y Position of the player
		playerX += velX;
		playerY += velY;


		hitBox.setLocation(playerY, playerX);
	}
	
	
	
	public void draw(Graphics g)
	{
		g.drawImage(Texture, playerX,  playerY, playerWidth, playerHeight,null);
	}

	@Override
	public void keyTyped(KeyEvent key) {

	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key != null) {
			switch (key.getKeyCode()){
				case KeyEvent.VK_SPACE:
					velY -= 45.0;
					break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent key) {

	}
}
