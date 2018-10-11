package com.tamargo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //hecho malamente bastante a lo bruto sin llegar a resolver como transformar numeros
        //decimales negativos en los demás tipos de valores ni como transformar hexadecimal en decimal
        //he creado dos proyectos, este contiene el ejercicio y el otro apuntes de las transformaciones

        //creamos todas las variables que vaya a usar para las conversiones, algunas incluso sobrarán puesto
        //que son variables traidas del otro proyecto donde aprendí a realizar las conversiones
        int numero = 0;
        int decimal = 0;
        int exponente = 0;
        int resto = 0;
        int x = 0;
        int digito = 0;
        int z = 0;
        boolean esBinario = false;
        int numIntroducidos = 0;
        int a = 0; //numero 1
        int b = 0; //numero 2
        int respuesta = 0; //respuesta de las operaciones de a y b

        //imagino que este ejercicio sería mucho más cómodo hacerlo creando funciones y llamándolas

        char eleccion = 'j';

        do {

            System.out.println("Elige el tipo de dato que vas a introducir según la lista");
            System.out.println("a) Binario.");
            System.out.println("b) Decimal.");
            System.out.println("c) Octal.");
            System.out.println("d) Hexadecimal.");
            System.out.print("Introduce la opción: ");

            eleccion = br.readLine().toLowerCase().charAt(0);

            //hacemos un switch donde recogeremos los datos y los transformaremos todos a decimal
            switch (eleccion) {

                case 'a':
                    System.out.print("Introduce el valor binario (valor nº" + (numIntroducidos + 1) + "): ");

                    do {
                        try {
                            numero = Integer.parseInt(br.readLine());
                            esBinario = true;
                            z = numero; //guardamos el número para utilizarlo al final
                            x = numero; //utilizamos x como auxiliar para guardar el valor
                            while (x != 0) {
                                digito = x % 10;
                                if (digito != 0 && digito != 1) {
                                    esBinario = false; //no es binario
                                }
                                x = x / 10;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("No has introducido un valor numérico binario ¡Introduce un número binario!");
                            ;
                        }

                    } while (!esBinario);

                    exponente = 0; //necesario ir aumentando el exponente de 2 por cada lugar que avancemos
                    decimal = 0; //resultado en base decimal
                    while (numero != 0) { //reduciremos el número hasta llegar a 0
                        digito = numero % 10; //
                        decimal = decimal + digito * (int) Math.pow(2, exponente); //elevamos dos a la variable exponente que irá aumentando
                        exponente++;
                        numero = numero / 10; //quitamos una cifra;
                    }
                    break;


                case 'b':
                    decimal = Integer.MAX_VALUE;
                    do {
                        System.out.print("Introduce el valor decimal (valor nº" + (numIntroducidos + 1) + "): ");
                        try {
                            decimal = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("No has introducido un número.");
                        }
                    } while (decimal == Integer.MAX_VALUE);
                    break;


                case 'c':
                    decimal = 0; //resultado en decimal
                    resto = 0; //guardamos el resto aquí y trabajamos con él
                    exponente = 0; //volvemos a utilizar exponente que una vez más irá creciendo progresivamente

                    System.out.print("Introduce el valor Octal (valor nº" + (numIntroducidos + 1) + "): ");
                    numero = Integer.parseInt(br.readLine());

                    x = numero; //utilizamos x como auxiliar para trabajar sobre ella

                    while (x != 0) {
                        resto = x % 10;
                        decimal = decimal + resto * (int) Math.pow(8, exponente);
                        x = x / 10;
                        exponente++;
                    }
                    break;

                case 'd':
                    String hexadecimalHtD;
                    int decimalHtD = 0;
                    int longitudHtD;
                    char comprobacionLetrasHtD;
                    int valorCaracterHtD = 0;

                    System.out.print("Introduce el valor hexadecimal (valor nº" + (numIntroducidos + 1) + "): ");
                    hexadecimalHtD = br.readLine();
                    longitudHtD = hexadecimalHtD.length();

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
                    decimal = decimalHtD;
                    break;

                default:
                    System.out.println("Error, vuelve a introducir una opción válida del menú.");
                    break;

            }

            if (numIntroducidos == 0) {
                a = decimal;
            } else {
                b = decimal;
            }

            if (eleccion == 'a' || eleccion == 'b' || eleccion == 'c' || eleccion == 'd') {
                numIntroducidos++;
            }

            System.out.println(""); //espacios para dejar más limpio y claro el programa

        } while (numIntroducidos < 2);

        System.out.println(""); //espacios para dejar más limpio y claro el programa

        do {

            System.out.println("Ahora elige una operación del menú:");
            System.out.println("Procura que la operación no de un resultado negativo para evitar errores.");
            System.out.println("a) Sumar.");
            System.out.println("b) Restar.");
            System.out.println("c) Multiplicar.");
            System.out.println("d) Dividir.");
            System.out.print("Introduce la opción: ");

            eleccion = br.readLine().toLowerCase().charAt(0);

            System.out.println(""); //espacio para dejar más limpio y claro el programa

            switch (eleccion) {

                case 'a':
                    respuesta = a + b;
                    break;
                case 'b':
                    respuesta = a - b;
                    break;
                case 'c':
                    respuesta = a * b;
                    break;
                case 'd':
                    respuesta = a / b;
                    break;
                default:
                    System.out.println("Elige una opción válida del menú.");
                    break;
            }
        } while (eleccion != 'a' && eleccion != 'b' && eleccion != 'c' && eleccion != 'd');

        System.out.println("Resultado en decimal: " + respuesta);

        //Transformación respueta a binario:
        numero = -1;
        x = 0;
        String binario = ""; //donde iremos guardando el resultado en base binaria, damos un valor inicial vacío para ir sobreescribiéndolo
        do {
            x = respuesta; //utilizamos x como auxiliar para guardar el valor

            if (x > 0) {
                while (x > 0) {
                    if (x % 2 == 0) {
                        binario = "0" + binario; //"0" + binario lo utilizamos para ir enlazando el binario
                    } else {
                        binario = "1" + binario; //lo mismo aquí
                    }
                    x = x / 2;
                }
            } else if (x == 0) {
                binario = "0";
            } else {
                binario = "no sé trabajar con binarios negativos";
                //aquí faltaría saber transformar números negativos a binarios, creo que es invertir los 0 y 1 y sumarle 1 pero no idea bro
            }
        } while (x < 0);

        System.out.println("Resultado en binario: " + binario);


        //Transformación a octal:
        String metodoToOctal = Integer.toOctalString(respuesta);
        System.out.println("Resultado en octal: " + metodoToOctal);

        //Transformación a hexadecimal:
        String metodoToHexString = Integer.toHexString(respuesta);
        System.out.println("Resultado en hexadecimal: " + metodoToHexString.toUpperCase());

    }
}