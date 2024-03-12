package com.store.dominguez.util.generator;


import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Random;

@Component
public class IdGenerator {

    private static final Random random = new Random();
    public static String generarID(String campo1, String campo2) {

        String textASCII = formatoASCII(campo1.trim());
        String text2ASCII = formatoASCII(campo2.trim());

        String text1 = obtenerParteId(textASCII);
        String text2 = obtenerParteId(text2ASCII);
        String cadenaAleatoria = generarCadenaAleatoria(6);
        return (text1 + "-").toUpperCase() + (text2 + "-") + cadenaAleatoria;
    }
    private static String obtenerParteId(String texto) {
        if (texto.length() >= 3) {
            return texto.substring(0, 3).toLowerCase().trim();
        } else {
            return texto.toLowerCase().trim();
        }
    }

    private static String formatoASCII(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }


    //public static void reiniciarCorrelativos() { correlativos.clear(); }
    private static String generarCadenaAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder cadenaAleatoria = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            cadenaAleatoria.append(caracteres.charAt(indice));
        }
        return cadenaAleatoria.toString();
    }

}
