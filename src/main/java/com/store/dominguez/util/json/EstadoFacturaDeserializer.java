package com.store.dominguez.util.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.store.dominguez.model.enums.EstadoFactura;

import java.io.IOException;

public class EstadoFacturaDeserializer extends StdDeserializer<EstadoFactura> {

    public EstadoFacturaDeserializer() {
        super(EstadoFactura.class);
    }

    @Override
    public EstadoFactura deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.asText().toLowerCase();
        for (EstadoFactura estadoFactura : EstadoFactura.values()) {
            if (estadoFactura.name().toLowerCase().equals(value)) {
                return estadoFactura;
            }
        }
        throw new IOException("Valor de estado de factura no válido: " + value);
    }

}
