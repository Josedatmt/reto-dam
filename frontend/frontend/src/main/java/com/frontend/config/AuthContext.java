package com.frontend.config;

import java.util.Base64;

public class AuthContext {
    private static String email;
    private static String role;
    private static String username;
    private static String basicAuthHeader;

    public static void login(String email, String password, String role) {
        AuthContext.email = email;
        AuthContext.role = role;
        AuthContext.username = username;
        AuthContext.basicAuthHeader = "Basic " +
                Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
    }

    public static void logout() {
        email = null;
        role = null;
        username = null;
        basicAuthHeader = null;
    }


    public static String getBasicAuthHeader() {
        return basicAuthHeader;
    }

    public static boolean isAdmin() {
        return "ADMIN".equals(role);
    }

    public static String getEmail() {
        return email;
    }

    public static String getUsername() {
        return username;
    }

    public static Long getUserId() {
        return AuthContext.getUserId();
    }
}