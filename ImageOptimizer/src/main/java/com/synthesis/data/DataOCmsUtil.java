package com.synthesis.data;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;
import com.mongodb.AutoEncryptionSettings.Builder;
import com.synthesis.Config;
import com.synthesis.data.models.ContentModel;
import com.synthesis.data.models.dataOContent.Content;
import com.synthesis.data.models.dataOContent.ImageFields;

public class DataOCmsUtil {

    private Config conf;
    private static final HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    public DataOCmsUtil() {
        conf = new Config();
    }

    public Content[] getContent() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(conf.getDataOContentEndpoint()))
                .headers("Accept", "application/json", "Authorization", "Bearer " + conf.getDataOApiKey())
                .GET()
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Gson gson = new Gson();
            ContentModel model = gson.fromJson(body, ContentModel.class);
            Content[] data = model.getData();
            return data;
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
}

