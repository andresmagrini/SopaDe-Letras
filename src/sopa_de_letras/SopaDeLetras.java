/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopa_de_letras;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author andres-PC
 */
public class SopaDeLetras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int size = 20;
        int cantidadPalabras = 10;

        String[] palabras = new String[cantidadPalabras];

        for (int i = 0; i < palabras.length; i++) {
            System.out.print("Ingrese palabra " + (i + 1) + ": ");
            palabras[i] = input.next();

            while (palabras[i].length() < 3 || palabras[i].length() > 7) {
                System.out.print("La palabra debe tener entre 3 y 5 caracteres. Ingrese palabra " + (i + 1) + ": ");
                palabras[i] = input.next();
            }
        }

        char[][] sopa = new char[size][size];
        Random random = new Random();

        for (int i = 0; i < cantidadPalabras; i++) {
            String palabra = palabras[i];
            int posicionInicial = random.nextInt(size - palabra.length() + 1);
            int posicionVertical = random.nextInt(size - palabra.length() + 1);
            int direccion = random.nextInt(3); // 0: horizontal, 1: vertical, 2: diagonal
            boolean invertida = random.nextBoolean();
            if (invertida) {
                palabra = new StringBuilder(palabra).reverse().toString();
            }

            boolean ubicado = false;
            while (!ubicado) {
                boolean sobrescribe = false;
                for (int j = 0; j < palabra.length(); j++) {
                    int fila = posicionVertical;
                    int columna = posicionInicial;
                    switch (direccion) {
                        case 0: // horizontal
                            columna += j;
                            break;
                        case 1: // vertical
                            fila += j;
                            break;
                        default: // diagonal
                            fila += j;
                            columna += j;
                            break;
                    }
                    if (fila >= size || columna >= size) {
                        break;
                    }
                    if (sopa[fila][columna] != 0 && sopa[fila][columna] != palabra.charAt(j)) {
                        sobrescribe = true;
                        break;
                    }
                }
                if (sobrescribe) {
                    posicionInicial = random.nextInt(size - palabra.length() + 1);
                    posicionVertical = random.nextInt(size - palabra.length() + 1);
                    direccion = random.nextInt(3);
                    invertida = random.nextBoolean();
                    if (invertida) {
                        palabra = new StringBuilder(palabra).reverse().toString();
                    }
                } else {
                    ubicado = true;
                    for (int j = 0; j < palabra.length(); j++) {
                        int fila = posicionVertical;
                        int columna = posicionInicial;
                        switch (direccion) {
                            case 0: // horizontal
                                columna += j;
                                break;
                            case 1: // vertical
                                fila += j;
                                break;
                            default: // diagonal
                                fila += j;
                                columna += j;
                                break;
                        }
                        sopa[fila][columna] = palabra.charAt(j);
                    }
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (sopa[i][j] == 0) {
                    sopa[i][j] = (char) (random.nextInt(26) + 97);
                }
                System.out.print(sopa[i][j] + " ");
            }
            System.out.println();
        }
    }
}
