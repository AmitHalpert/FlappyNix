package org.amithalpert.Tools;

public abstract class GameObject{

    protected float x, y, velX, velY, speed;
    protected float width, height;


    public GameObject(float width, float height, float x, float y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
    }

}
