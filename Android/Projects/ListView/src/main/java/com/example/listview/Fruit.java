package com.example.listview;

/**
 * 水果数据模型类
 */
public class Fruit {
    private int imageId; // 水果图片资源ID
    private String name; // 水果名称
    private String description; // 水果描述
    private String[] comments; // 水果评论

    public Fruit(int imageId, String name, String description, String[] comments) {
        this.imageId = imageId;
        this.name = name;
        this.description = description;
        this.comments = comments;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }
}