package com.tamargo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
        Generar números aleatorios:

        Llamamos a la función "random"
        Random r = new Random();

        El comando para crear número aleatorio es:
        int ejemplo = r.nextInt(50);
        Pero desconozco si en ese caso de genera un número dle 0 al 50 incluyendo
        el 0
        Los números normales son del 1 al 50
        Las 'estrellas' son números del 1 al 12
        Cada boleto son 5 números normales y 2 'estrellas'

        Si el 0 no se tiene en cuenta
        int ejemplo = r.nextInt(50);

        Si el 0 se tiene en cuenta
        int ejemplo = r.nextInt(49) +1;

        Tras realizar pruebas confirmo que el 0 SÍ se tiene en cuenta.
        */

        Random r = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = -1;

        while (x < 1) {
            System.out.print("Introduce cuantos boletos quieres: ");
            try {
                x = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("¡Introduce un número!");
            }
            if (x < 1) {
                System.out.println("Imprime mínimo un boleto.");
                System.out.println("");
            }
        }

        int numero;

        for (int i = 0; i < x; i++) {
            System.out.println("Boleto nº" + (i + 1));
            for (int j = 0; j < 5; j++) {
                numero = r.nextInt(49) + 1;
                System.out.print(numero + " ");
            }
            for (int j = 0; j < 2; j++) {
                numero = r.nextInt(11) + 1;
                if (j == 0) {
                    System.out.print(numero + " ");
                } else {
                    System.out.println(numero + ".");
                }
            }
            System.out.println("");
        }
    }
}

