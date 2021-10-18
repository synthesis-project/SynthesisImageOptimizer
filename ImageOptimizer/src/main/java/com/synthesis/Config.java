package com.synthesis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private String configFile = "application.properties";
    private String mongoUri;
    private String applicationName;

    public String getMongoUri() {return mongoUri;}
    public String getApplicationName() {return applicationName;}


    public Config() {
        File configFile = new File(this.configFile);

        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            this.mongoUri = props.getProperty("mongoUri");
            this.applicationName = props.getProperty("ApplicationName");

            System.out.print("Host name is: " + mongoUri);
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
            // file does not exist
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
            // I/O error
        }

    }

}
