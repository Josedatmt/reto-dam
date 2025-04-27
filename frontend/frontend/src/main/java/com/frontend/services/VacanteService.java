package com.frontend.services;

import com.frontend.config.ApiClient;
import com.frontend.models.Vacante;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.List;

public class VacanteService {
    private static final Gson gson = new Gson();
    private static final String ADMIN_CREDENTIALS = "Basic " +
            Base64.getEncoder().encodeToString("usuario1:password1!".getBytes());

    public static List<Vacante> getActive() throws Exception {
        String json = ApiClient.send(
                "GET",
                "/vacante/activas",
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Vacante>>(){}.getType());
    }

    public static List<Vacante> getByCompany(Long companyId) throws Exception {
        String json = ApiClient.send(
                "GET",
                "/empresa/vacantes/empresa/" + companyId,
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Vacante>>() {
        }.getType());

    }

    public static List<Vacante> getAll() throws Exception {
        String json = ApiClient.send(
                "GET",
                "/vacante",
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Vacante>>() {
        }.getType());
    }

}