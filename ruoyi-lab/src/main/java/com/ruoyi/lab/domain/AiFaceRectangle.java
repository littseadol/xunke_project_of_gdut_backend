package com.ruoyi.lab.domain;

public class AiFaceRectangle {
    private String content;
    private int x;
    private int y;
    private int width;
    private int height;

    public AiFaceRectangle() {}

    public AiFaceRectangle(String content, int x, int y, int width, int height) {
        this.content = content;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
