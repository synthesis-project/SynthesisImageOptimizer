package com.synthesis.data.models.dataOContent;

public class Content {
    private String id;
    private Attributes attributes;

    public Content() {}

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
