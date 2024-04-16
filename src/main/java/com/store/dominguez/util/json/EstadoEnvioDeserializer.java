package com.store.dominguez.util.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.store.dominguez.model.enums.EstadoEnvio;

import java.io.IOException;

public class EstadoEnvioDeserializer extends StdDeserializer<EstadoEnvio> {


    public EstadoEnvioDeserializer() {
        super(EstadoEnvio.class);
    }

    @Override
    public EstadoEnvio deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.asText().toLowerCase();
        for (EstadoEnvio estadoEnvio : EstadoEnvio.values()) {
            if (estadoEnvio.name().toLowerCase().equals(value)) {
                return estadoEnvio;
            }
        }
        throw new IOException("Valor de estado de envío no válido: " + value);
    }
}
