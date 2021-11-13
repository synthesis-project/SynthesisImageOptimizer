package com.synthesis.data;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import com.mongodb.internal.async.SingleResultCallback;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import com.synthesis.Config;

public class MongoUtil {

    private static MongoClient singletonClient = null;

    private MongoUtil() {
        //Make database connection
    }

    public static MongoClient getClient() {
        if(singletonClient == null) {
            Config conf = new Config();
            ConnectionString connectionString = new ConnectionString(conf.getMongoUri());
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
            singletonClient = MongoClients.create(settings);
        }
        return singletonClient;
    }

    public static void closeClient() {
        if(singletonClient == null) 
            return;
        singletonClient.close();
    }

    public static String[] getCollectionNames() {
        List<String> collections = new ArrayList<>();

        MongoClient client = getClient();
        MongoDatabase raw = client.getDatabase("raw");
        // MongoIterable<String> z = raw.listCollectionNames();
        MongoCollection<Document> b = raw.getCollection("accepted_images");
        int cursor = (int) b.countDocuments();
        System.out.println(cursor);
        // while(cursor.hasNext()) {
        //     Document s = cursor.tryNext();
        //     System.out.println(s.toString());
        //     // collections.add(cursor.next());
        // }
        client.close();

        String[] collectionsArr = new String[collections.size()];
        for(int i = 0; i < collections.size(); i++) {
            collectionsArr[i] = collections.get(i);
        }
        return collectionsArr;
    }

    public static void getAllAcceptedImages() {
        List<Document> docs = new ArrayList();
        MongoClient client = getClient();
        MongoDatabase raw = client.getDatabase("raw");
        // MongoIterable<String> z = raw.listCollectionNames();
        MongoCollection<Document> b = raw.getCollection("accepted_images");
        FindIterable<Document> x = b.find();
        MongoCursor<Document> cursor = x.iterator();
        while(cursor.hasNext()) {
            Document d = cursor.tryNext();
            System.out.println(d);
        }
    }

    static SingleResultCallback<Void> callbackWhenFinished = new SingleResultCallback<Void>() {
        @Override
        public void onResult(final Void result, final Throwable t) {
            System.out.println("Operation Finished!");
        }
    };

    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };
    
}
