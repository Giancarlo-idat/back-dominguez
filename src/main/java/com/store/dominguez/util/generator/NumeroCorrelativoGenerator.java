package com.store.dominguez.util.generator;

import java.time.LocalDate;
import java.util.Random;

public class NumeroCorrelativoGenerator {

    public static String generarNumeroCorrelativo() {
        String prefijo = ("BXC-").toUpperCase() + LocalDate.now().getYear();
        Random random = new Random();
        String numeroCorrelativo;

        StringBuilder sb = new StringBuilder(prefijo);
        for (int i = 0; i < 5; i++) {
            sb.append(random.nextInt(10)); // Solo dígitos
        }
        numeroCorrelativo = sb.toString();

        return numeroCorrelativo.toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(generarNumeroCorrelativo());
    }
}


