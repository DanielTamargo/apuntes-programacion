package com.tamargo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

     double precio;
     double pago;
     int vueltas = 0;

        do {
            System.out.print("Introduce el precio: ");
            precio = Double.parseDouble(br.readLine()); //recibimos el precio en euros

            System.out.print("Introduce el pago: ");
            pago = Double.parseDouble(br.readLine()); //recibimos el pago en euros

            pago = pago * 100; //lo pasamos a céntimos
            precio = precio * 100; //lo pasamos a céntimos

            if (pago % 5 == 0 && precio % 5 == 0) {
                if (pago < precio){
                    System.out.println("El dinero introducido es insuficiente");
                }
                if (pago == precio){
                    System.out.println("Pago exacto, sin vueltas");
                }
                if (pago > precio){
                    vueltas = (int)pago - (int)precio;
                }
            } else {
                System.out.println("Introduce un valor válido");
            }
        } while (pago < precio || pago % 5 != 0 || precio % 5 != 0);

        int cambios;

        cambios = vueltas / 200; //cuantas monedas de 2 euros devolvemos
        System.out.println("Monedas de 2 euros: " + cambios); //mostramos cuantas monedas de 2 euros devolvemos
        vueltas = vueltas % 200; //restamos las monedas de 2 euros

        cambios = vueltas / 100;
        System.out.println("Monedas de 1 euro: " + cambios);
        vueltas = vueltas % 100;

        cambios = vueltas / 50;
        System.out.println("Monedas de 50 cent: " + cambios);
        vueltas = vueltas % 50;

        cambios = vueltas / 20;
        System.out.println("Monedas de 20 cent: " + cambios);
        vueltas = vueltas % 20;

        cambios = vueltas / 10;
        System.out.println("Monedas de 10 cent: " + cambios);
        vueltas = vueltas % 10;

        cambios = vueltas / 5;
        System.out.println("Monedas de 5 cent: " + cambios);

    }
}
