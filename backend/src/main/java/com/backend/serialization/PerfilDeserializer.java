package com.backend.serialization;
import com.backend.model.Perfil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerfilDeserializer extends JsonDeserializer<List<Perfil>> {
    @Override
    public List<Perfil> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return new ArrayList<>();
    }
}
