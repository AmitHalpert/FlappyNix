package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;
import main.GamePanel;

import static main.Game.PORT;


public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if(PORT == 5000) {

			switch (e.getKeyCode()) {
				//		case KeyEvent.VK_W:
				//			gamePanel.getGame().getPlayer().setUp(false);
				//			break;
				case KeyEvent.VK_LEFT:
					gamePanel.getGame().getPlayers().get(0).setLeft(false);
					break;
				//		case KeyEvent.VK_S:
				//			gamePanel.getGame().getPlayer().setDown(false);
				//			break;
				case KeyEvent.VK_RIGHT:
					gamePanel.getGame().getPlayers().get(0).setRight(false);
					break;
				case KeyEvent.VK_UP:
					gamePanel.getGame().getPlayers().get(0).setJump(false);
					break;
			}
		} else if(PORT == 6000) {

			switch (e.getKeyCode()) {
//		case KeyEvent.VK_W:
//			gamePanel.getGame().getPlayer().setUp(true);
//			break;
				case KeyEvent.VK_A:
					gamePanel.getGame().getPlayers().get(1).setLeft(false);
					break;
//		case KeyEvent.VK_S:
//			gamePanel.getGame().getPlayer().setDown(true);
//			break;
				case KeyEvent.VK_D:
					gamePanel.getGame().getPlayers().get(1).setRight(false);
					break;
				case KeyEvent.VK_W:
					gamePanel.getGame().getPlayers().get(1).setJump(false);
					break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {


		if(PORT == 5000) {
			// Player ONE
			switch (e.getKeyCode()) {
//		case KeyEvent.VK_W:
//			gamePanel.getGame().getPlayer().setUp(true);
//			break;
				case KeyEvent.VK_LEFT:
					gamePanel.getGame().getPlayers().get(0).setLeft(true);
					break;
//		case KeyEvent.VK_S:
//			gamePanel.getGame().getPlayer().setDown(true);
//			break;
				case KeyEvent.VK_RIGHT:
					gamePanel.getGame().getPlayers().get(0).setRight(true);
					break;
				case KeyEvent.VK_UP:
					gamePanel.getGame().getPlayers().get(0).setJump(true);
					break;
			}
		} else if (PORT == 6000) {

			// Player ONE
			switch (e.getKeyCode()) {
//		case KeyEvent.VK_W:
//			gamePanel.getGame().getPlayer().setUp(true);
//			break;
				case KeyEvent.VK_A:
					gamePanel.getGame().getPlayers().get(1).setLeft(true);
					break;
//		case KeyEvent.VK_S:
//			gamePanel.getGame().getPlayer().setDown(true);
//			break;
				case KeyEvent.VK_D:
					gamePanel.getGame().getPlayers().get(1).setRight(true);
					break;
				case KeyEvent.VK_W:
					gamePanel.getGame().getPlayers().get(1).setJump(true);
					break;
			}
		}



	}
}