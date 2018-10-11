package com.tamargo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //he creado dos proyectos, este contiene apuntes de las transformaciones y el otro el ejercicio


        //Transformar número decimal a binario

        int numero = -1;
        int x = 0;
        String binario = ""; //donde iremos guardando el resultado en base binaria, damos un valor inicial vacío para ir sobreescribiéndolo
        do {
            System.out.print("Introduce un número: ");
            try {

                numero = Integer.parseInt(br.readLine());
                x = numero; //utilizamos x como auxiliar para guardar el valor

                if (numero > 0) {
                    while (numero > 0) {
                        if (numero % 2 == 0) {
                            binario = "0" + binario; //"0" + binario lo utilizamos para ir enlazando el binario
                        } else {
                            binario = "1" + binario; //lo mismo aquí
                        }
                        numero = numero / 2;
                    }
                } else if (numero == 0) {
                    binario = "0";
                } else {
                    binario = "Error, introduce un número positivo.";
                }
                System.out.println("El número " + x + " convertido a binario es: " + binario);

            } catch (NumberFormatException e) {
                System.out.println("¡No has introducido un número!");
                System.out.println("");
            }

        } while (numero < 0);


        //Transformar binario a decimal

        int digito, z;
        boolean esBinario = false;

        //comprobamos que sea binario
        do {
            System.out.print("Introduce un número binario: ");
            numero = Integer.parseInt(br.readLine()); //no hago un trycatch confirmando que es un número para no complicar el codigo y entenderlo mejor
            esBinario = true;
            z = numero; //guardamos el número para mostrarlo al final
            x = numero; //utilizamos x como auxiliar para guardar el valor
            while (x != 0) {
                digito = x % 10;
                if (digito != 0 && digito != 1) {
                    esBinario = false; //no es binario
                }
                x = x / 10;
                //es como el ejercicio de los cambios, vas trabajando con restos entre 10 para ver si el resto es 1 o 0
                //y sigues diviendo entre 10 para quitarle una cifra cada vez
                //utilizamos digito para comprobar si ese digito es un 0 o 1 (binario)
                //utilizamos x como auxiliar para ir comprobando cada cifra sin alterar el numero
            }
        } while (!esBinario);

        //cuando comprobamos que es un número binario procedemos a transformarlo
        //la conversión se hace multiplicando cada número por * elevado a un exponente que irá aumentando conforme avance números

        int exponente = 0; //necesario ir aumentando el exponente de 2 por cada lugar que avancemos
        int decimal = 0; //resultado en base decimal
        while (numero != 0) { //reduciremos el número hasta llegar a 0
            digito = numero % 10; //
            decimal = decimal + digito * (int) Math.pow(2, exponente); //elevamos dos a la variable exponente que irá aumentando
            exponente++;
            numero = numero / 10; //quitamos una cifra;
        }
        System.out.println("El número binario " + z + " convertido a decimal es: " + decimal);


        // Transformación de Decimal a Octal (2 métodos)

        System.out.print("Introduce un número: ");
        decimal = Integer.parseInt(br.readLine());

        //Método con toOctalString()
        String metodoToOctal = Integer.toOctalString(decimal);
        System.out.println("El número decimal " + decimal + " convertido a octal es: " + metodoToOctal);

        //Método con array
        String octal = "";
        char digitosOctal[] = {'0', '1', '2', '3', '4', '5', '6', '7'};
        digito = decimal; //guardamos el número para mostrarlo al final y trabajamos sobre decimal
        x = decimal; //usamos x como auxiliar para trabajar con los restos

        while (x > 0) {
            decimal = x % 8; //trabajamos con el resto de x
            octal = digitosOctal[decimal] + octal;
            x = x / 8; //reducimos x
        }
        System.out.println("El número decimal " + digito + " convertido a octal es: " + octal);


        //Transformación Octal a Decimal

        decimal = 0; //resultado en decimal
        int resto = 0; //guardamos el resto aquí
        exponente = 0; //volvemos a utilizar exponente que una vez más irá creciendo progresivamente

        System.out.print("Introduce un número Octal: ");
        numero = Integer.parseInt(br.readLine());

        x = numero; //utilizamos x como auxiliar para trabajar sobre ella

        while (x != 0) {
            resto = x % 10;
            decimal = decimal + resto * (int) Math.pow(8, exponente);
            x = x / 10;
            exponente++;
        }
        System.out.println("El número octal " + numero + " convertido a decimal es: " + decimal);


        //Transformación Decimal a Hexadecimal (2 métodos)

        System.out.print("Introduce un número: ");
        decimal = Integer.parseInt(br.readLine());

        //Método toHexString()
        String metodoToHexString = Integer.toHexString(decimal);
        System.out.println("El número decimal " + decimal + " convertido a hexadecimal es: " + metodoToHexString.toUpperCase());

        //Método con array

        x = decimal; //utilizamos x como auxiliar para trabajar sobre ella y así no sobreescribir la variable decimal
        resto = 0; //utilizamos resto para ir utilizandolo con el array
        String hexadecimal = ""; //donde iremos acumulando el resultado en base hexadecimal, damos un valor inicial vacío para ir sobreescribiéndolo
        char hexadecimalValores[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        while (x > 0) {
            resto = x % 16;
            hexadecimal = hexadecimalValores[resto] + hexadecimal;
            x = x / 16;
        }
        System.out.println("El número decimal " + decimal + " convertido a hexadecimal es: " + hexadecimal);


        //Transformación Hexadecimal a Decimal

        //vamo' a intentarlo, versión totalmente improvisada
        //algunas variables seguramente sobrarán pero las dejaré para entender como lo fui planteando

        int restoHtD;
        int exponenteHtD;
        String hexadecimalHtD;
        String auxiliarStringHtD;
        int auxiliarHtD;
        int decimalHtD = 0;
        int longitudHtD;
        char comprobacionLetrasHtD;
        int valorCaracterHtD = 0;

        System.out.println("Introduce el número hexadecimal");
        hexadecimalHtD = br.readLine();
        longitudHtD = hexadecimalHtD.length();
        auxiliarStringHtD = hexadecimalHtD;

        int exponenteHtDevolucionInversa = longitudHtD - 1;

        for (int i = 0; i < longitudHtD; i++) {
            comprobacionLetrasHtD = hexadecimalHtD.toUpperCase().charAt(i);

            switch (comprobacionLetrasHtD) {
                case '0':
                    valorCaracterHtD = 0;
                    break;
                case '1':
                    valorCaracterHtD = 1;
                    break;
                case '2':
                    valorCaracterHtD = 2;
                    break;
                case '3':
                    valorCaracterHtD = 3;
                    break;
                case '4':
                    valorCaracterHtD = 4;
                    break;
                case '5':
                    valorCaracterHtD = 5;
                    break;
                case '6':
                    valorCaracterHtD = 6;
                    break;
                case '7':
                    valorCaracterHtD = 7;
                    break;
                case '8':
                    valorCaracterHtD = 8;
                    break;
                case '9':
                    valorCaracterHtD = 9;
                    break;
                case 'A':
                    valorCaracterHtD = 10;
                    break;
                case 'B':
                    valorCaracterHtD = 11;
                    break;
                case 'C':
                    valorCaracterHtD = 12;
                    break;
                case 'D':
                    valorCaracterHtD = 13;
                    break;
                case 'E':
                    valorCaracterHtD = 14;
                    break;
                case 'F':
                    valorCaracterHtD = 15;
                    break;
                default:
                    System.out.println("Error el programa no funcionará correctamente.");
            }
            decimalHtD = decimalHtD + valorCaracterHtD * (int) Math.pow(16, exponenteHtDevolucionInversa);
            exponenteHtDevolucionInversa--;
        }

        System.out.println("El número hexadecimal " + hexadecimalHtD + " convertido a decimal es: " + decimalHtD);

        //desactivamos esto porque no iba a funcionar puesto que como leo los caracteres al revés el exponente ha de ir inversamente
        // while (x != 0) {
        //restoHtD = auxiliarHtD % 10;
        // decimalHtD = decimalHtD + restoHtD * (int)Math.pow(16,exponente);
        //auxiliarHtD= auxiliarHtD / 10;
        //exponente++;
        //}

        // ¿? Hace falta crear más clases y llamarlas como funciones ¿?
        // No sé hacerlo










        /*
        Fuentes consultadas:
        - Transformar binario a decimal: http://puntocomnoesunlenguaje.blogspot.com/2014/11/pasar-de-binario-decimal-en-java.html
        - Transformar decimal a binario: http://www.eljavatar.com/2014/04/Como-Convertir-un-Numero-Decimal-a-Binario-en-Java.html
        - Transformar decimal a octal: https://masqueprogramar.wordpress.com/2018/05/04/decimal-octal-java/
        - Transformar octal a decimal: https://codescracker.com/java/program/java-program-convert-octal-to-decimal.htm
        - Transformar decimal a hexadecimal: https://beginnersbook.com/2014/07/java-program-to-convert-decimal-to-hexadecimal/
        - Transformar hexadecimal a decimal:
         */
    }
}