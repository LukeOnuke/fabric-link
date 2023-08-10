package com.lukeonuke.web;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class DataService {

    private static DataService instance;
    private final HttpClient client;
    private final Gson gson;

    private DataService() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        this.gson = new Gson();
    }

    public static DataService getInstance() {
        if (instance == null) instance = new DataService();
        return instance;
    }

    public DataModel getPlayerData(String guildId, String uuid) throws IOException, InterruptedException {
        String url = "https://link.samifying.com/api/user/" + guildId + "/" + uuid;
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> rsp = client.send(req, HttpResponse.BodyHandlers.ofString());

        // Generating response based of status codes
        if (rsp.statusCode() != 200) {
            throw new VerificationException("You are not verified", rsp.statusCode());
        }
        // Response is OK
        return gson.fromJson(rsp.body(), DataModel.class);
    }
}
