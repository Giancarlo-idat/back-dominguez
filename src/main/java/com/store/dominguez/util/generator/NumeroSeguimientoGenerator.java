package com.store.dominguez.util.generator;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class NumeroSeguimientoGenerator {

    public static String generarNumeroSeguimiento() {
        Set<String> numeroSeguimientoExistente = new HashSet<>();
        Random random = new Random();
        String numeroSeguimiento;

        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(random.nextInt(20));
            }
            numeroSeguimiento = sb.toString();
        } while (!numeroSeguimientoExistente.add(numeroSeguimiento));

        return numeroSeguimiento;
    }
}
