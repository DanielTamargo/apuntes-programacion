package com.tamargo;

public class Main {

    public static void main(String[] args) {

        //Figura nº1
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                System.out.print("*");
            } else {
                System.out.print(" *");
            }
        }

        //Separación entre figuras
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Figura nº2
        for (int i = 0; i < 5; i++) {
            System.out.println("*****");
        }

        //Separación entre figuras
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Figura nº3
        for (int i = 0; i < 5; i++) {
            for (int n = 0; n <= i; n++) {
                System.out.print("*");
            }
            System.out.println("");
        }

        //Separación entre figuras
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Figura nº4
        int x = 5;
        for (int i = 0; i < 5; i++) {
            for (int n = 0; n < x; n++) {
                System.out.print("*");
            }
            System.out.println("");
            x--;
        }

        //Separación entre figuras
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Figura nº5
        x = 0; //ya hemos definido x para la figura anterior
        for (int i = 0; i < 5; i++) {
            for (int n = 0; n < x; n++) {
                System.out.print(" ");
            }
            System.out.println("*");
            x++;
        }

        //Separación entre figuras
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Figura nº6
        for (int i = 0; i < 5; i++) {
            if (i == 0 || i == 4) {
                System.out.println("*****");
            } else {
                System.out.println("*   *");
            }
        }
    }
}
