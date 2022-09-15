package org.amithalpert.Tools;



public class BoxCollider {

    public double x;
    public double y;
    public float width, height;


    public BoxCollider(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public BoxCollider set (float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        return this;
    }


    public double getX () {
        return x;
    }


    public BoxCollider setX (double x) {
        this.x = x;

        return this;
    }


    public double getY () {
        return y;
    }


    public BoxCollider setY (double y) {
        this.y = y;

        return this;
    }


    public float getWidth () {
        return width;
    }


    public BoxCollider setWidth (float width) {
        this.width = width;

        return this;
    }


    public float getHeight () {
        return height;
    }


    public BoxCollider setHeight (float height) {
        this.height = height;

        return this;
    }



    public BoxCollider setPosition (float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }


    public BoxCollider setSize (float width, float height) {
        this.width = width;
        this.height = height;

        return this;
    }


    public BoxCollider setSize (float sizeXY) {
        this.width = sizeXY;
        this.height = sizeXY;

        return this;
    }

    public boolean overlaps (BoxCollider r) {
        return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
    }

}
