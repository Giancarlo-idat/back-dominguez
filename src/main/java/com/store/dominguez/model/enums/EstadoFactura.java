package com.store.dominguez.model.enums;

public enum EstadoFactura {
    PENDIENTE,
    PAGADA,
    CANCELADA;

    public static boolean isValid(String value) {
        String upperValue = value.toUpperCase();
        return upperValue.equals(PENDIENTE.name()) || upperValue.equals(PAGADA.name()) || upperValue.equals(CANCELADA.name());
    }
}
