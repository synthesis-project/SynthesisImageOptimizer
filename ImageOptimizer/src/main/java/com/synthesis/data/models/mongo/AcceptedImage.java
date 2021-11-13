package com.synthesis.data.models.mongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class AcceptedImage {

    private ObjectId id;

    public AcceptedImage() {
    }

    public AcceptedImage(ObjectId id, String guid, String url, String author, String plantName, int height, int width) {
        this.id = id;
        this.guid = guid;
        this.url = url;
        this.author = author;
        this.plantName = plantName;
        this.height = height;
        this.width = width;
    }

    @Field("id")
    private String guid;

    private String url;

    private String author;

    private String plantName;

    private int height;

    private int width;

    public ObjectId getId() {
        return id;
    }

    public String guid() {
        return guid;
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

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return String.format(
            "AcceptedImage[id=%s, guid=%s, url=%s, author=%s, plantName=%s, height=%s, width=%s]", 
            id, guid, url, author, plantName, height, width);
    }
    
}
