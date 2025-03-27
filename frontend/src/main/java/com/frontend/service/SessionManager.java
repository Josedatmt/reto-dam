package com.frontend.service;

import com.frontend.model.Usuario;

public class SessionManager {
    private static String authToken;
    private static Usuario currentUser;

    public static void login(Usuario usuario, String token) {
        currentUser = usuario;
        authToken = token;
    }

    public static void logout() {
        currentUser = null;
        authToken = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null && authToken != null;
    }

    public static Usuario getCurrentUser() {
        return currentUser;
    }

    public static String getToken() {
        return authToken;
    }

    public static boolean isAdmin() {
        return isLoggedIn() && currentUser.getPerfil().getNombre().equals("ADMIN");
    }

    public static boolean isEmpresa() {
        return isLoggedIn() && currentUser.getPerfil().getNombre().equals("EMPRESA");
    }

    public static boolean isUsuario() {
        return isLoggedIn() && currentUser.getPerfil().getNombre().equals("USUARIO");
    }
}