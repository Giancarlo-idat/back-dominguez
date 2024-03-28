package com.store.dominguez.util.generator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumeroCorrelativoGenerator {

    public static String generarNumeroCorrelativo() {
        String prefijo = "BXC-" + LocalDate.now().getYear();
        Set<String> numeroCorrelativoExistente = new HashSet<>();
        Random random = new Random();
        String numeroCorrelativo;

        StringBuilder sb = new StringBuilder(prefijo);
        for (int i = 0; i < 5; i++) {
            sb.append(random.nextInt(10)); // Solo dígitos
        }
        numeroCorrelativo = sb.toString();

        return numeroCorrelativo;
    }

    public static void main(String[] args) {
        System.out.println(generarNumeroCorrelativo());
    }
}


