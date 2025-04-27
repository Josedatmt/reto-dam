package com.frontend.services;

import com.frontend.config.ApiClient;
import com.frontend.dto.LoginDTO;
import com.frontend.models.Perfil;
import com.frontend.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Base64;
import java.util.List;

public class UsuarioService {
    private static final Gson gson = new Gson();


    private static final String ADMIN_CREDENTIALS = "Basic " +
            Base64.getEncoder().encodeToString("usuario1:password1!".getBytes());

    public static Usuario login(String email, String password) throws Exception {
        LoginDTO loginDTO = new LoginDTO(email, password);
        String jsonBody = gson.toJson(loginDTO);

        String response = ApiClient.send(
                "POST",
                "/auth/login",
                jsonBody,
                null
        );

        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        Usuario usuario = new Usuario();
        usuario.setEmail(jsonResponse.get("email").getAsString());
        usuario.setUsername(jsonResponse.get("username").getAsString());


        Perfil perfil = new Perfil();
        perfil.setNombre(jsonResponse.get("rol").getAsString());
        usuario.getPerfiles().add(perfil);

        return usuario;
    }
    public static void update(Usuario usuario) throws Exception {
        ApiClient.send(
                "PUT",
                "/usuario/" + usuario.getUsername(),
                gson.toJson(usuario),
                ADMIN_CREDENTIALS
        );
    }

    public static List<Usuario> getAll() throws Exception {
        String json = ApiClient.send(
                "GET",
                "/usuarios",
                null,
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, List.class);
    }

    public static Usuario create(Usuario usuario) throws Exception {
        String json = ApiClient.send(
                "POST",
                "/auth/register",
                gson.toJson(usuario),
                ADMIN_CREDENTIALS
        );
        return gson.fromJson(json, Usuario.class);
    }
}