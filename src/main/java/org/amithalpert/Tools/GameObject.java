package org.amithalpert.Tools;

import java.awt.*;

public abstract class GameObject{

    protected double x, y, velX, velY, speed;
    protected int width, height;
    protected Rectangle boxCollider;

    public GameObject(int width, int height, int x, int y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
    }





}
