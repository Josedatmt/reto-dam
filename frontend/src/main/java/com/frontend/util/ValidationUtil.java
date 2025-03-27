package com.frontend.util;

import com.frontend.model.Empresa;
import com.frontend.model.Usuario;
import com.frontend.model.Vacante;

public class ValidationUtil {
    public static boolean validarCredenciales(String username, String password) {
        return username != null && !username.trim().isEmpty() &&
                password != null && password.length() >= 6;
    }

    public static boolean validarUsuario(Usuario usuario) {
        return usuario != null &&
                validarCredenciales(usuario.getUsername(), usuario.getPassword()) &&
                usuario.getNombre() != null && !usuario.getNombre().trim().isEmpty() &&
                usuario.getEmail() != null && usuario.getEmail().contains("@");
    }

    public static boolean validarPassword(String password) {
        return password != null && password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*");
    }

    public static boolean validarEmpresa(Empresa empresa) {
        return empresa != null &&
                empresa.getNombre_social() != null && !empresa.getNombre_social().trim().isEmpty() &&
                empresa.getDireccion_fiscal() != null && !empresa.getDireccion_fiscal().trim().isEmpty();
    }

    public static boolean validarVacante(Vacante vacante) {
        return vacante != null &&
                vacante.getNombre() != null && !vacante.getNombre().trim().isEmpty() &&
                vacante.getDescripcion() != null && !vacante.getDescripcion().trim().isEmpty() &&
                vacante.getSalario() > 0;
    }
}