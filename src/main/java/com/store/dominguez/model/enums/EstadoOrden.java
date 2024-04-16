package com.store.dominguez.model.enums;

public enum EstadoOrden {
    PENDIENTE,
    PROCESADA,
    COMPLETADA,
    CANCELADA;

    public static boolean isValid(String value) {
        String upperValue = value.toUpperCase();
        return upperValue.equals(PENDIENTE.name()) || upperValue.equals(PROCESADA.name()) || upperValue.equals(COMPLETADA.name()) || upperValue.equals(CANCELADA.name());
    }
}
