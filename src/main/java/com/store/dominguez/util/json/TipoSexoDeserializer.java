package com.store.dominguez.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.store.dominguez.model.TipoSexo;

import java.io.IOException;

public class TipoSexoDeserializer extends StdDeserializer<TipoSexo> {

    public TipoSexoDeserializer() {
        super(TipoSexo.class);
    }

    @Override
    public TipoSexo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.asText().toLowerCase();
        for (TipoSexo tipoSexo : TipoSexo.values()) {
            if (tipoSexo.name().toLowerCase().equals(value)) {
                return tipoSexo;
            }
        }
        throw new IOException("Valor de sexo no válido: " + value);
    }
}
