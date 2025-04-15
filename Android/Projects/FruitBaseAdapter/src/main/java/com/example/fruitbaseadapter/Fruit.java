package com.example.fruitbaseadapter;

public class Fruit {
    private final int imageId;  // 水果图片资源ID
    private final String name;  // 水果名称

    public Fruit(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}