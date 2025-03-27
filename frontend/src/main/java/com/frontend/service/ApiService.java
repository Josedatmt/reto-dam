package com.frontend.service;

import com.frontend.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

public class ApiService {
    private final String apiUrl;
    private final Gson gson = new Gson();

    public ApiService() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
            this.apiUrl = prop.getProperty("api.url");
        } catch (IOException ex) {
            throw new RuntimeException("Error loading configuration", ex);
        }
    }

    // Empresas
    public List<Empresa> getEmpresas() throws IOException {
        return getListFromApi("/empresas", new TypeToken<List<Empresa>>(){}.getType());
    }

    public Empresa getEmpresaById(int id) throws IOException {
        return getFromApi("/empresas/" + id, Empresa.class);
    }

    public boolean saveEmpresa(Empresa empresa) throws IOException {
        return postToApi("/empresas", empresa);
    }

    public boolean deleteEmpresa(int id) throws IOException {
        return deleteFromApi("/empresas/" + id);
    }

    // Vacantes
    public List<Vacante> getVacantes() throws IOException {
        return getListFromApi("/vacantes", new TypeToken<List<Vacante>>(){}.getType());
    }

    public Vacante getVacanteById(int id) throws IOException {
        return getFromApi("/vacantes/" + id, Vacante.class);
    }

    public boolean saveVacante(Vacante vacante) throws IOException {
        return postToApi("/vacantes", vacante);
    }

    public boolean cancelarVacante(int id) throws IOException {
        return putToApi("/vacantes/" + id + "/cancelar", null);
    }

    // Solicitudes
    public List<Solicitud> getSolicitudes() throws IOException {
        return getListFromApi("/solicitudes", new TypeToken<List<Solicitud>>(){}.getType());
    }

    public boolean crearSolicitud(Solicitud solicitud, File archivo) throws IOException {
        return uploadFile("/solicitudes", solicitud, archivo);
    }

    public boolean asignarSolicitud(int id) throws IOException {
        return putToApi("/solicitudes/" + id + "/asignar", null);
    }

    public boolean cancelarSolicitud(int id) throws IOException {
        return putToApi("/solicitudes/" + id + "/cancelar", null);
    }

    // Usuarios
    public List<Usuario> getUsuarios() throws IOException {
        return getListFromApi("/usuarios", new TypeToken<List<Usuario>>(){}.getType());
    }

    public boolean crearUsuario(Usuario usuario) throws IOException {
        return postToApi("/usuarios", usuario);
    }

    public boolean actualizarUsuario(Usuario usuario) throws IOException {
        return putToApi("/usuarios/" + usuario.getUsername(), usuario);
    }

    public boolean toggleUsuario(String username, int enabled) throws IOException {
        return putToApi("/usuarios/" + username + "/toggle?enabled=" + enabled, null);
    }

    // Categorías
    public List<Categoria> getCategorias() throws IOException {
        return getListFromApi("/categorias", new TypeToken<List<Categoria>>(){}.getType());
    }

    public boolean saveCategoria(Categoria categoria) throws IOException {
        return postToApi("/categorias", categoria);
    }

    public boolean deleteCategoria(int id) throws IOException {
        return deleteFromApi("/categorias/" + id);
    }

    // llamadas API
    private <T> T getFromApi(String endpoint, Class<T> type) throws IOException {
        HttpURLConnection conn = createConnection("GET", endpoint);
        return parseResponse(conn, type);
    }

    private <T> List<T> getListFromApi(String endpoint, Type type) throws IOException {
        HttpURLConnection conn = createConnection("GET", endpoint);
        return parseListResponse(conn, type);
    }

    private boolean postToApi(String endpoint, Object data) throws IOException {
        HttpURLConnection conn = createConnection("POST", endpoint);
        sendData(conn, data);
        return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    private boolean putToApi(String endpoint, Object data) throws IOException {
        HttpURLConnection conn = createConnection("PUT", endpoint);
        sendData(conn, data);
        return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    private boolean deleteFromApi(String endpoint) throws IOException {
        HttpURLConnection conn = createConnection("DELETE", endpoint);
        return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    private boolean uploadFile(String endpoint, Object data, File file) throws IOException {

        return true;
    }

    private HttpURLConnection createConnection(String method, String endpoint) throws IOException {
        URL url = new URL(apiUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + SessionManager.getToken());
        conn.setDoInput(true);
        if (method.equals("POST")) {
            conn.setDoOutput(true);
        }
        return conn;
    }

    private void sendData(HttpURLConnection conn, Object data) throws IOException {
        if (data != null) {
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = gson.toJson(data).getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }
    }

    private <T> T parseResponse(HttpURLConnection conn, Class<T> type) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            return gson.fromJson(br, type);
        }
    }

    private <T> List<T> parseListResponse(HttpURLConnection conn, Type type) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            return gson.fromJson(br, type);
        }
    }
}