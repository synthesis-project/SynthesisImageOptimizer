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
    private String inputPlant;
    private String dataOContentEndpoint;
    private String dataOApiKey;

    public String getMongoUri() {return mongoUri;}
    public String getApplicationName() {return applicationName;}
    public String getInputPlant() {return inputPlant;}
    public String getDataOContentEndpoint() {return dataOContentEndpoint;}
    public String getDataOApiKey() {return dataOApiKey;}


    public Config() {
        File configFile = new File(this.configFile);

        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            //Configuration properties
            this.mongoUri = props.getProperty("mongoUri");
            this.applicationName = props.getProperty("ApplicationName");
            this.dataOContentEndpoint = props.getProperty("dataOContentUrl");
            this.dataOApiKey = props.getProperty("dataOCmsApiKey");
            
            //Input params
            this.inputPlant = System.getProperty("inputPlant");

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
