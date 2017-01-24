package com.example;

/**
 * Created by tbradfute on 1/24/17.
 */
public class Widget {
    private String color;
    private int x;
    private int y;

    public Widget() {
    }

    public Widget(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
