package com.frontend.services;

import com.frontend.config.ApiClient;
import com.frontend.models.Perfil;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.List;

public class PerfilService {
    private static final Gson gson = new Gson();
    private static final String ADMIN_CREDENTIALS = "Basic " +
            Base64.getEncoder().encodeToString("usuario1:password1!".getBytes());

    public static List<Perfil> getAll() throws Exception {
        String json = ApiClient.send(
                "GET",
                "/perfil",
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Perfil>>(){}.getType());
    }

    public static Perfil getById(Long id) throws Exception {
        String json = ApiClient.send(
                "GET",
                "/perfil/" + id,
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, Perfil.class);
    }
}