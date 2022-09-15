package org.amithalpert.Tools;

public abstract class GameObject{

    protected double x, y, velX, velY, speed;
    protected float width, height;
    protected BoxCollider boxCollider;

    public GameObject(int width, int height, double x, double y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
    }





}
