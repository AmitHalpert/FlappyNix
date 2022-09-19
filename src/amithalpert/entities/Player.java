package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int AnimationToPlay = IDLE;

	private enum PlayerState{
		idle,
		moving,
		inAir,
		attack

	}
	PlayerState State;

	private boolean inAir = true;

	private float xSpeed = 0;
	private float ySpeed = 0f;

	private boolean moving = false, attacking = false;
	// controller booleans are modified in KeyboardInputs class
	private boolean left, up, right, down, jump;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;

	// Jumping / Gravity
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);

		loadAnimations();
		initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[AnimationToPlay][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
		drawHitbox(g);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(AnimationToPlay)) {
				aniIndex = 0;
				attacking = false;
				State = PlayerState.idle;
			}

		}

	}

	private void setAnimation() {
		int startAni = AnimationToPlay;

		
		switch (State){
			case attack:
				AnimationToPlay = ATTACK_1;
				break;
			case moving:
				AnimationToPlay = RUNNING;
				break;
			case inAir:
				if (ySpeed < 0)
					AnimationToPlay = JUMP;
				else
					AnimationToPlay = FALLING;
				break;
			default:
				AnimationToPlay = IDLE;
		}



		if (startAni != AnimationToPlay)
			resetAniTick();
	}


	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		State = PlayerState.idle;

		// Controls

		if (jump) {
			State = PlayerState.inAir;
			jump();
		}
		if (!left && !right && !inAir) {
			return;
		}

		xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;



		if (!inAir){
			if (!IsEntityOnFloor(hitbox, lvlData)){
				inAir = true;
				State = PlayerState.inAir;
			}
		}


		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += ySpeed;
				ySpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, ySpeed);
				if (ySpeed > 0)
					resetInAir();
				else
					ySpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		State = PlayerState.moving;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		ySpeed = jumpSpeed;
	}

	private void resetInAir() {
		inAir = false;
		ySpeed = 0;
	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
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

}