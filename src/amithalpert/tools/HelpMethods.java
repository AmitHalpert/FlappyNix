package tools;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {

	/*
	public static boolean HorizontalCollisionBetweenPlayers(){
		return Game.getPlayers().get(0).getHitbox().intersects(Game.getPlayers().get(1).getHitbox());
	}
	public static boolean VerticalCollisionBetweenPlayers(){

		if(Game.getPlayers().get(0).getPlayerCollider().intersects(Game.getPlayers().get(1).getHitbox()) || Game.getPlayers().get(1).getPlayerCollider().intersects(Game.getPlayers().get(0).getHitbox())){
			return true;
		}
		else{
			return false;
		}
	}

	 */


	// Checks 4 rect points for collision
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!IsTile(x, y, lvlData)){
			if (!IsTile(x + width, y + height, lvlData)){
				if (!IsTile(x + width, y, lvlData)){
					if (!IsTile(x, y + height, lvlData)){
						return true;
					}
				}
			}
		}
		return false;
	}

	// Checks if colliding with map tile and screen size
	private static boolean IsTile(float x, float y, int[][] lvlData) {
		if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;

		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;

		int value = lvlData[(int) yIndex][(int) xIndex];

		if (value >= 48 || value < 0 || value != 11)
			return true;
		return false;
	}

	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
		if (xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		} else
			// Left
			return currentTile * Game.TILES_SIZE;
	}

	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			// Falling - touching floor
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		} else
			// Jumping
			return currentTile * Game.TILES_SIZE;

	}

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		// Check the pixel below bottomleft and bottomright
		if (!IsTile(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
			if (!IsTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
				return false;
		return true;

	}

}