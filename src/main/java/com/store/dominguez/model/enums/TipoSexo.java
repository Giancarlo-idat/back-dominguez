package com.store.dominguez.model.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.store.dominguez.util.json.TipoSexoDeserializer;

@JsonDeserialize(using = TipoSexoDeserializer.class)
public enum TipoSexo {

    MASCULINO,
    FEMENINO,
    OTROS;

    public static boolean isValid(String value) {
        String upperValue = value.toUpperCase();
       return upperValue.equals(MASCULINO.name()) || upperValue.equals(FEMENINO.name()) || upperValue.equals(OTROS.name());
    }

}

