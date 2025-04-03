package com.example.listview;

/**
 * 水果数据模型类
 */
public class Fruit {
    private final int imageId; // 水果图片资源ID
    private final String name; // 水果名称
    private final String description; // 水果描述
    private final String[] comments; // 水果评论

    public Fruit(int imageId, String name, String description, String[] comments) {
        this.imageId = imageId;
        this.name = name;
        this.description = description;
        this.comments = comments;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getComments() {
        return comments;
    }

}