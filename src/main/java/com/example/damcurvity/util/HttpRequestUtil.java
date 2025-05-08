package com.example.damcurvity.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequestUtil {

    public static String sendPostRequest(String url, String data) throws Exception {
        URL postUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        connection.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("Failed to send POST request. Response code: " + responseCode);
        }
    }

    public static String sendGetRequest(String url) throws Exception {
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("Failed to send GET request. Response code: " + responseCode);
        }
    }
}
