package org.amithalpert.entity;

import org.amithalpert.Screens.GamePanel;
import org.amithalpert.Tools.GameObject;
import org.amithalpert.Tools.KeyboardHandling;

public class Player extends GameObject {


    GamePanel gp;
    KeyboardHandling KeyH;

    public Player(GamePanel gp, KeyboardHandling keyH, float width, float height, float x, float y) {
        super(width, height, x, y);

        this.gp = gp;
        this.KeyH = keyH;

    }
}
