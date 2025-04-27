package com.frontend.services;

import com.frontend.config.ApiClient;
import com.frontend.models.Solicitud;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.List;

public class SolicitudService {
    private static final Gson gson = new Gson();
    private static final String ADMIN_CREDENTIALS = "Basic " +
            Base64.getEncoder().encodeToString("usuario1:password1!".getBytes());

    public static List<Solicitud> getByUser(Long userId) throws Exception {
        String json = ApiClient.send(
                "GET",
                "/Solicitud/usuario/" + userId,
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Solicitud>>(){}.getType());
    }

    public static void cancel(Long id) throws Exception {
        ApiClient.send(
                "PATCH",
                "/Solicitud/" + id + "/cancelar",
                null,
                ADMIN_CREDENTIALS
        );
    }
}