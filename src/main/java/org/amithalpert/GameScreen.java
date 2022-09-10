package org.amithalpert;
import java.awt.*;
import javax.swing.*;

public class GameScreen extends JPanel
{
	Player player;
	FlappyNix nix;
	Image worldTexture;

	
	public GameScreen()
	{
		worldTexture = (new ImageIcon("src/main/resources/background.png")).getImage();
		player = new Player(this);
		nix = new FlappyNix(player, this);


		addKeyListener(player);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g)	
	{
		super.paintComponent(g);
		
		g.drawImage(worldTexture,0,0,getWidth(),getHeight(),null);

		g.setColor(Color.red);
		g.drawRect(nix.groundCollider.x , nix.groundCollider.y , nix.groundCollider.width, nix.groundCollider.height);
		g.drawRect(player.hitBox.x, player.hitBox.y , player.hitBox.width, player.hitBox.height);
		

		player.draw(g);
	}



	public static void main(String[] args) 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		JFrame frame = new JFrame("FlappyNix");
		GameScreen gameScreen = new GameScreen();
		frame.add(gameScreen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenSize.width, screenSize.height);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setFocusable(false);

	}
}
