package com.synthesis.data.models;

import com.google.gson.Gson;
import com.synthesis.data.models.dataOContent.Content;

public class ContentModel {
    
    private Content[] data;

    public ContentModel() {}


    public Content[] getData() {
        return data;
    }


    public void setData(Content[] data) {
        this.data = data;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String returnVar = String.format("Content Model length: %s...\n first obj: %s", data.length, data[0].getAttributes().getPlant_name());
        return super.toString();
    }
}