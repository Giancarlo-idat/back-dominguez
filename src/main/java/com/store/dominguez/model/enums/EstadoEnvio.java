package com.store.dominguez.model.enums;

public enum EstadoEnvio {
    PENDIENTE,
    EN_CAMINO,
    ENTREGADO;

    public static boolean isValid(String value) {
        String upperValue = value.toUpperCase();
        return upperValue.equals(PENDIENTE.name()) || upperValue.equals(EN_CAMINO.name()) || upperValue.equals(ENTREGADO.name());
    }
}
