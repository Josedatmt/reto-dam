package com.frontend.services;

import com.frontend.config.ApiClient;
import com.frontend.models.Empresa;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.List;

public class EmpresaService {
    private static final Gson gson = new Gson();
    private static final String ADMIN_CREDENTIALS = "Basic " +
            Base64.getEncoder().encodeToString("usuario1:password1!".getBytes());

    public static List<Empresa> getAll() throws Exception {
        String json = ApiClient.send(
                "GET",
                "/empresa",
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Empresa>>(){}.getType());
    }

    public static Empresa create(Empresa empresa) throws Exception {
        String json = ApiClient.send(
                "POST",
                "/empresa",
                gson.toJson(empresa),
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, Empresa.class);
    }
}