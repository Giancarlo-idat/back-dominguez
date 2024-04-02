package com.store.dominguez.util.generator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class NumeroGuiaGenerator {
    private static final String PREFIJO_GUIA_SALIDA = "GS-";
    private static final String PREFIJO_NUMERO_SALIDA = "SAL-";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final AtomicInteger contadorGuia = new AtomicInteger(0);
    private static final AtomicInteger contadorSalida = new AtomicInteger(0);

    public static String generarNumeroGuiaSalida() {
        String fechaActual = LocalDate.now().format(FORMATTER);
        int numero = contadorGuia.incrementAndGet();
        String numeroFormateado = String.format("%04d", numero); // Asegura que el número tenga cuatro dígitos
        return PREFIJO_GUIA_SALIDA + fechaActual + "-" + numeroFormateado;
    }

    public static String generarNumeroSalida() {
        String fechaActual = LocalDate.now().format(FORMATTER);
        int numero = contadorSalida.incrementAndGet();
        String numeroFormateado = String.format("%04d", numero); // Asegura que el número tenga cuatro dígitos
        return PREFIJO_NUMERO_SALIDA + fechaActual + "-" + numeroFormateado;
    }

    public static void reiniciarContadores() {
        contadorGuia.set(0);
        contadorSalida.set(0);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        System.out.println("Número de guía de salida: " + generarNumeroGuiaSalida());
        System.out.println("Número de salida: " + generarNumeroSalida());
    }
}
