package com.synthesis;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.synthesis.data.DataOCmsUtil;
import com.synthesis.data.MongoUtil;
import com.synthesis.data.models.dataOContent.Content;

public class App 
{
    public static void main( String[] args )
    {
        Config conf = new Config();
        System.out.println("Running " + conf.getApplicationName() + " for input " + conf.getInputPlant() + "...");
        DataOCmsUtil c = new DataOCmsUtil();
        Content[] content = c.getContent();
        MongoUtil.getAllAcceptedImages();
        // String collections[] = MongoUtil.getCollectionNames();
        // for (String string : collections) {
        //     System.out.println(string);
        // }
    }
}
