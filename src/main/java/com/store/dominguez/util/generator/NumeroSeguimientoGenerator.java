package com.store.dominguez.util.generator;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class NumeroSeguimientoGenerator {

    private static final AtomicInteger counter = new AtomicInteger(1);

    public static String generarNumeroSeguimiento() {
        int numeroAleatorio = new Random().nextInt(1000000); // Número aleatorio entre 0 y 999999
        String sufijoIncremental = String.format("%06d", counter.getAndIncrement()); // Sufijo incremental de 6 dígitos

        return numeroAleatorio + sufijoIncremental;
    }

    public static void main(String[] args) {
        System.out.println(generarNumeroSeguimiento());
    }
}

