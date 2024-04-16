package com.store.dominguez.util.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.store.dominguez.model.enums.EstadoOrden;

import java.io.IOException;

public class EstadoOrdenDeserializer extends StdDeserializer<EstadoOrden> {

    public EstadoOrdenDeserializer() {
        super(EstadoOrdenDeserializer.class);
    }

    @Override
    public EstadoOrden deserialize(JsonParser jsonParser, DeserializationContext dbContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.asText().toLowerCase();
        for (EstadoOrden estadoOrden : EstadoOrden.values()) {
            if (estadoOrden.name().toLowerCase().equals(value)) {
                return estadoOrden;
            }
        }
        throw new IOException("Valor de estado de orden no válido: " + value);
    }


}
