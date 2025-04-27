package com.frontend.services;

import com.frontend.config.ApiClient;
import com.frontend.models.Categoria;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.List;

public class CategoriaService {
    private static final Gson gson = new Gson();
    private static final String ADMIN_CREDENTIALS = "Basic " +
            Base64.getEncoder().encodeToString("usuario1:password1!".getBytes());

    public static List<Categoria> getAll() throws Exception {
        String json = ApiClient.send(
                "GET",
                "/admin/categorias",
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Categoria>>(){}.getType());
    }

    public static void create(Categoria categoria) throws Exception {
        ApiClient.send(
                "POST",
                "/admin/categorias",
                gson.toJson(categoria),
                ADMIN_CREDENTIALS
        );
    }

    public static void delete(Long id) throws Exception {
        ApiClient.send(
                "DELETE",
                "/admin/categorias/" + id,
                null,
                ADMIN_CREDENTIALS
        );
    }
}