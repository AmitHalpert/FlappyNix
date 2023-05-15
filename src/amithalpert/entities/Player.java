package entities;


import static tools.Constants.GameLoopConstants.*;
import static tools.Constants.PlayerConstants.*;
import static tools.HelpMethods.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;
import tools.LoadSave;

public class Player extends Entity{

	private float velX = 0f;
	private float velY = 0f;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	private int flipX = 0;
	private int flipW = 1;

	// Jumping / Gravity
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.30f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;

	private boolean inAir = false;


	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();


		initHitbox(x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE));
	}


	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset) + flipX, (int) (hitbox.y - yDrawOffset), width * flipW, height, null);
		//drawHitbox(g);
		//g.setColor(Color.RED);

	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}

		}

	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;

		if (inAir) {
			if (velY < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}

		if (attacking)
			playerAction = ATTACK_1;

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	@Override
	public void run() {

		double timePerUpdate = 1000000000.0 / UPS_SET;
		long previousTime = System.nanoTime();
		double deltaU = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;

			previousTime = currentTime;

			// update player
			if (deltaU >= 1) {
				updatePos();
				updateAnimationTick();
				setAnimation();
				deltaU--;
			}
		}
	}

	private void updatePos() {


		moving = false;

		if (jump)
			jump();

		if (!inAir){
			if ((!left && !right) || (right && left)){
				return;
			}
		}

		// resets player X velocity
		velX = 0;

		// moves player axis and flips according to which side is player facing?
		if (left) {
			velX -= playerSpeed;
			flipX = width;
			flipW = -1;
		}
		if (right) {
			velX += playerSpeed;
			flipX = 0;
			flipW = 1;
		}



		if (!inAir)
			if (!IsEntityOnFloor(hitbox, lvlData))
				inAir = true;


		if (inAir) {
			// vertical tile collision detection
			if (CanMoveHere(hitbox.x, hitbox.y + velY, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += velY;
				velY += gravity;
				updateXPos(velX);
			} else {
				// vertical tile collision detection
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, velY);
				if (velY > 0)
					resetInAir();
				else
					velY = fallSpeedAfterCollision;
				updateXPos(velX);
			}

		} else
			updateXPos(velX);
		moving = true;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		velY = jumpSpeed;
	}

	private void resetInAir() {
		inAir = false;
		velY = 0;
	}

	private void updateXPos(float velX) {
		if (CanMoveHere(hitbox.x + velX, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += velX;


		} else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, velX);
		}

	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}




	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}


	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public boolean isInAir() {
		return inAir;
	}

	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

}