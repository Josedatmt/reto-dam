package com.frontend.service;

import com.frontend.model.Usuario;
import com.google.gson.Gson;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class AuthService {
    private final String authUrl;
    private final Gson gson = new Gson();

    public AuthService() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
            this.authUrl = prop.getProperty("api.url") + "/auth";
        } catch (IOException ex) {
            throw new RuntimeException("Error loading configuration", ex);
        }
    }

    public Usuario login(String username, String password) throws IOException {
        String requestBody = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);

        URL url = new URL(authUrl + "/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                Usuario usuario = gson.fromJson(br, Usuario.class);
                String token = conn.getHeaderField("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    SessionManager.login(usuario, token.substring(7));
                }
                return usuario;
            }
        }
        return null;
    }

    public boolean register(Usuario usuario) throws IOException {
        URL url = new URL(authUrl + "/register");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = gson.toJson(usuario).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return conn.getResponseCode() == HttpURLConnection.HTTP_CREATED;
    }

    public boolean cambiarPassword(String username, String oldPassword, String newPassword) throws IOException {
        String requestBody = String.format("{\"oldPassword\":\"%s\",\"newPassword\":\"%s\"}", oldPassword, newPassword);

        URL url = new URL(authUrl + "/" + username + "/change-password");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + SessionManager.getToken());
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}