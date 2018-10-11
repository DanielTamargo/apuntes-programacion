package com.tamargo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a = 4, b = 4, c = 4, d = 4, e = 4, f = 4;
        int g = 4, h = 4, i = 4, j = 4, k = 4, l = 4;

        int puntuacion1 = 0;
        int puntuacion2 = 0;

        int contadorTurnos = 1; //lo usamos para saber que jugador juega en cada momento y realizar comprobaciones en base a eso
        int contadorSemillasMovidas = 0; //lo uso para realizar las cosechas multiples sin pasarme y evitar errores
        int totalEnTablero;
        int finJuego = 1; //utilizamos finJuego para finalizar el juego cuando un jugador elimina al otro tras cosechar todas sus semillas
        int comprobacionParcelaNoVacia = 1;

        String jugador1;
        String jugador2;
        String jugadorEnBaseAlTurno;
        String statusJuego = "jugando";

        char eleccion = 'w';
        char ultimoTiro = 'x';
        String comprobacionEleccion = "wrong";

        while (eleccion != 'b') {

            System.out.println("¿Queréis conocer las reglas del juego?");
            System.out.println("a) Sí.");
            System.out.println("b) No.");
            System.out.print("Elección: ");
            eleccion = br.readLine().toLowerCase().charAt(0);

            switch (eleccion) {
                case 'a':
                    System.out.println("Para entender mejor la explicación vamos a poner nombres a las cosas clave:");
                    System.out.println("- El jugador 1 se llama Paco.");
                    System.out.println("- El jugador 2 se llama Lucía.");
                    System.out.println("- Las fichas se llamarán semillas.");
                    System.out.println("- Cada hueco se llamará parcela.");
                    System.out.println("- Se llamará cosecha a cada acción que te permita sacar las semillas.");
                    System.out.println("Aclarado esto, expliquemos el juego.");
                    System.out.println("Hay 2 filas con 6 parcelas cada una.");
                    System.out.println("Cada parcela tiene 4 semillas en ella.");
                    System.out.println("Cada jugador se pondrá a un lado tendiendo en frente una de las dos filas.");
                    System.out.println("Por lo que Paco tendrá enfrente (a, b, c, d, e y f) y Lucía tendrá enfrente (g, h, i, j, k y l");
                    System.out.println("Paco, por ser el jugador 1 comenzará la partida.");
                    System.out.println("Paco cogerá todas las semillas de cualquiera de sus parcelas.");
                    System.out.println("Y las sembrará de 1 en 1 de forma contínua hacia la derecha.");
                    System.out.println("Es decir, si Paco coge las semillas de la parcela A, la parcela A quedará vacía e irá colocando 1 semilla en B, C, D y E");
                    System.out.println("Si Paco hubiera elegido la parcela E, la primera semilla hubiera ido para su propia parcela F, pero las demás hubieran ido a parar a las parcelas de Lucía, siempre siguiendo el sentido 'hacia la derecha'.");
                    System.out.println("Turno de Lucía, por lo que hará lo mismo, eligirá una de sus parcelas donde haya semillas, las cogerá y las irá colocando hacia la derecha.");
                    System.out.println("Aquí llega el sistema de puntos.");
                    System.out.println("Para puntuar, tendrás que realizar 'una cosecha'.");
                    System.out.println("Siempre que al final de tu turno en el último tiro (última semilla movida) haya 2 o 3 semillas en esa parcela y esta parcela sea del bando contrario, se puede cosechar.");
                    System.out.println("Si cosechas, retiras las 2 o 3 semillas de esa parcela y cada semilla pasará a ser 1 punto para el jugador que haya realizado la cosecha.");
                    System.out.println("Cosecha múltiple: si el último tiro (última semilla movida) resulta en una cosecha, puedes observar si los anteriores cumplen esta regla también, siempre en orden inverso al que has ido sembrando las semillas.");
                    System.out.println("Si una parcela tiene 12 o más semillas, se le dará una vuelta entera al tablero, pero muy importante, habrá que saltar la parcela desde donde se empezó.");
                    System.out.println("Si se da el caso de que un jugador se queda sin semillas, por ejemplo Lucía, Paco está obligado a realizar un movimiento que siembre semillas en alguna parcela de Lucía.");
                    System.out.println("Si se da el caso de no poder realizar ese movimiento, el juego termina y todas las semillas de Paco pasan a ser puntos para él.");
                    System.out.println("En otro caso, si Lucía consigue cosechar todas las semillas de Paco en un turno, se termina la partida y se lleva un recuento.");
                    System.out.println("Gana el jugador con más puntos.");
                    System.out.println("");

                    eleccion = 'b';
                case 'b':
                    System.out.println("¡Buena suerte en el juego!");
                    System.out.println("");
                    break;
                default:
                    System.out.println("Elige una de las dos opciones (a/b).");
                    System.out.println("");
                    break;

            }
        }


        System.out.print("Nombre del jugador 1: ");
        jugador1 = br.readLine();
        System.out.print("Nombre del jugador 2: ");
        jugador2 = br.readLine();

        System.out.println("Las parcelas de " + jugador1 + " serán 'A', 'B', 'C', 'D', 'E' y 'F'.");
        System.out.println("Las parcelas de " + jugador2 + " serán 'G', 'H', 'I', 'J', 'K' y 'L'.");
        System.out.println("");

        do {

            if (contadorTurnos % 2 == 0) {
                totalEnTablero = g + h + i + j + k + l;
            } else {
                totalEnTablero = a + b + c + d + e + f;
            }

            System.out.println("Tablero del juego: ");
            System.out.println("" +
                    "     ┌─────────────────────────────────┐\n" +
                    "     │  F │  E │  D │  C │  B │  A │\n" +
                    "┌─────┼─────────────────────────────────┼─────┐\n" +
                    "│    │  " + f + " │  " + e + " │  " + d + " │  " + c + " │  " + b + " │  " + a + " │    │\n" +
                    "│  " + puntuacion1 + " ├─────────────────────────────────┤ " + puntuacion2 + "  │\n" +
                    "│    │  " + g + " │  " + h + " │  " + i + " │  " + j + " │  " + k + " │  " + l + " │    │\n" +
                    "└─────┼─────────────────────────────────┼─────┘\n" +
                    "     │  G │  H │  I │  J │  K │  L │\n" +
                    "     └─────────────────────────────────┘");
            System.out.println("La puntuación de " + jugador1 + " está a la izquierda y la puntuación de " + jugador2 + " está a la derecha.");
            System.out.println("");


            if (contadorTurnos % 2 == 0) { //esto funcionará teniendo en cuenta que cuando un jugador no pueda mover se contará como un turno igualmente
                jugadorEnBaseAlTurno = jugador2;
            } else {
                jugadorEnBaseAlTurno = jugador1;
            }

            comprobacionEleccion = "wrong"; //le damos valor wrong cada vez que entre siempre en el do while

            if (totalEnTablero == 0) { //comprobamos que el jugador tenga semilllas para mover
                System.out.println("Turno nº" + contadorTurnos + ": " + jugadorEnBaseAlTurno + ", no puedes mover puesto que no tienes semillas en ninguna de tus parcelas, turno siguiente.");

                if (contadorTurnos % 2 == 0) { //comprobamos que el juego va a poder continuar utilizando la regla que refiere que el jugador opuesto al que se ha quedado sin semillas está obligado a mover semillas al campo enemigo para seguir con el juego, de lo contario la partida acaba y las semillas se le sumarán

                    if (f < 1 || e < 2 || d < 3 || c < 4 || b < 5 || a < 6) {
                        statusJuego = "fin";
                    } else {
                        System.out.println(jugador1 + "deberás elegir una parcela que pase semillas al bando contrario o el programa no funcionará correctamente.");
                    }

                } else {
                    if (l < 1 || k < 2 || j < 3 || i < 4 || h < 5 || g < 6) {
                        statusJuego = "fin";
                    } else {
                        System.out.println(jugador1 + "deberás elegir una parcela que pase semillas al bando contrario o el programa no funcionará correctamente.");
                    }
                }
                comprobacionEleccion = "ok";
            }

            do {
                System.out.print("Turno nº" + contadorTurnos + ": " + jugadorEnBaseAlTurno + ", elige una parcela de tu lado para mover las semillas: ");
                eleccion = br.readLine().toLowerCase().charAt(0);

                comprobacionParcelaNoVacia = 0; //sustituimos el valor para que no sea 0 y no se salga del bucle cuando elija una respuesta errónea

                if (contadorTurnos % 2 == 0) {
                    switch (eleccion) {
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            comprobacionEleccion = "wrong"; //no sé por que si no pongo esto el jugador dos puede elegir del campo opuesto
                            break;
                        case 'g':
                            comprobacionParcelaNoVacia = g;
                            break;
                        case 'h':
                            comprobacionParcelaNoVacia = h;
                            break;
                        case 'i':
                            comprobacionParcelaNoVacia = i;
                            break;
                        case 'j':
                            comprobacionParcelaNoVacia = j;
                            break;
                        case 'k':
                            comprobacionParcelaNoVacia = k;
                            break;
                        case 'l':
                            comprobacionParcelaNoVacia = l;
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (eleccion) {
                        case 'a':
                            comprobacionParcelaNoVacia = a;
                            break;
                        case 'b':
                            comprobacionParcelaNoVacia = b;
                            break;
                        case 'c':
                            comprobacionParcelaNoVacia = c;
                            break;
                        case 'd':
                            comprobacionParcelaNoVacia = d;
                            break;
                        case 'e':
                            comprobacionParcelaNoVacia = e;
                            break;
                        case 'f':
                            comprobacionParcelaNoVacia = f;
                            break;
                        default:
                            break;
                    }
                }


                if (comprobacionParcelaNoVacia != 0) {
                    comprobacionEleccion = "ok";
                }


                if (!comprobacionEleccion.equalsIgnoreCase("ok")) {

                    if (contadorTurnos % 2 == 0) {
                        totalEnTablero = g + h + i + j + k + l;
                        if (totalEnTablero == 0) {
                            comprobacionEleccion = "ok";
                        } else {
                            System.out.println("Error, introduce una elección válida (una de las 6 parcelas en tu lado que no esté vacía).");
                            System.out.println("");
                        }
                    } else {
                        totalEnTablero = a + b + c + d + e + f;
                        if (totalEnTablero == 0) {
                            comprobacionEleccion = "ok";
                        } else {
                            System.out.println("Error, introduce una elección válida (una de las 6 parcelas en tu lado que no esté vacía).");
                            System.out.println("");
                        }
                    }
                }

            } while (!comprobacionEleccion.equalsIgnoreCase("ok"));


            if (totalEnTablero != 0) {

                switch (eleccion) {

                    case 'a':
                        contadorSemillasMovidas = a;
                        for (int n = 0; n < a; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) { //dejo como máximo un supuesto de que llegan como mucho a 30 semillas en una parcela, podría hacerlo más largo hasta 48 ya que hay un total de 48 semillas pero sería demasiado
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        j = j + 1;
                                                                                                                                                        ultimoTiro = 'j';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    i = i + 1;
                                                                                                                                                    ultimoTiro = 'i';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                h = h + 1;
                                                                                                                                                ultimoTiro = 'h';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            g = g + 1;
                                                                                                                                            ultimoTiro = 'g';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        f = f + 1;
                                                                                                                                        ultimoTiro = 'f';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    e = e + 1;
                                                                                                                                    ultimoTiro = 'e';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                d = d + 1;
                                                                                                                                ultimoTiro = 'd';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            c = c + 1;
                                                                                                                            ultimoTiro = 'c';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        b = b + 1;
                                                                                                                        ultimoTiro = 'b';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    l = l + 1;
                                                                                                                    ultimoTiro = 'l';
                                                                                                                }
                                                                                                            } else {
                                                                                                                k = k + 1;
                                                                                                                ultimoTiro = 'k';
                                                                                                            }
                                                                                                        } else {
                                                                                                            j = j + 1;
                                                                                                            ultimoTiro = 'j';
                                                                                                        }
                                                                                                    } else {
                                                                                                        i = i + 1;
                                                                                                        ultimoTiro = 'i';
                                                                                                    }
                                                                                                } else {
                                                                                                    h = h + 1;
                                                                                                    ultimoTiro = 'h';
                                                                                                }
                                                                                            } else {
                                                                                                g = g + 1;
                                                                                                ultimoTiro = 'g';
                                                                                            }
                                                                                        } else {
                                                                                            f = f + 1;
                                                                                            ultimoTiro = 'f';
                                                                                        }
                                                                                    } else {
                                                                                        e = e + 1;
                                                                                        ultimoTiro = 'e';
                                                                                    }
                                                                                } else {
                                                                                    d = d + 1;
                                                                                    ultimoTiro = 'd';
                                                                                }
                                                                            } else {
                                                                                c = c + 1;
                                                                                ultimoTiro = 'c';
                                                                            }
                                                                        } else {
                                                                            b = b + 1;
                                                                            ultimoTiro = 'b';
                                                                        }
                                                                    } else {
                                                                        l = l + 1;
                                                                        ultimoTiro = 'l';
                                                                    }
                                                                } else {
                                                                    k = k + 1;
                                                                    ultimoTiro = 'k';
                                                                }
                                                            } else {
                                                                j = j + 1;
                                                                ultimoTiro = 'j';
                                                            }
                                                        } else {
                                                            i = i + 1;
                                                            ultimoTiro = 'i';
                                                        }
                                                    } else {
                                                        h = h + 1;
                                                        ultimoTiro = 'h';
                                                    }
                                                } else {
                                                    g = g + 1;
                                                    ultimoTiro = 'g';
                                                }
                                            } else {
                                                f = f + 1;
                                                ultimoTiro = 'f';
                                            }
                                        } else {
                                            e = e + 1;
                                            ultimoTiro = 'e';
                                        }
                                    } else {
                                        d = d + 1;
                                        ultimoTiro = 'd';
                                    }
                                } else {
                                    c = c + 1;
                                    ultimoTiro = 'c';
                                }
                            } else {
                                b = b + 1;
                                ultimoTiro = 'b';
                            }

                        } //fin for movimientoSemillas

                        a = 0;

                        break; //fin case

                    case 'b':
                        contadorSemillasMovidas = b;
                        for (int n = 0; n < b; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        k = k + 1;
                                                                                                                                                        ultimoTiro = 'k';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    j = j + 1;
                                                                                                                                                    ultimoTiro = 'j';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                i = i + 1;
                                                                                                                                                ultimoTiro = 'i';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            h = h + 1;
                                                                                                                                            ultimoTiro = 'h';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        g = g + 1;
                                                                                                                                        ultimoTiro = 'g';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    f = f + 1;
                                                                                                                                    ultimoTiro = 'f';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                e = e + 1;
                                                                                                                                ultimoTiro = 'e';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            d = d + 1;
                                                                                                                            ultimoTiro = 'd';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        c = c + 1;
                                                                                                                        ultimoTiro = 'c';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    a = a + 1;
                                                                                                                    ultimoTiro = 'a';
                                                                                                                }
                                                                                                            } else {
                                                                                                                l = l + 1;
                                                                                                                ultimoTiro = 'l';
                                                                                                            }
                                                                                                        } else {
                                                                                                            k = k + 1;
                                                                                                            ultimoTiro = 'k';
                                                                                                        }
                                                                                                    } else {
                                                                                                        j = j + 1;
                                                                                                        ultimoTiro = 'j';
                                                                                                    }
                                                                                                } else {
                                                                                                    i = i + 1;
                                                                                                    ultimoTiro = 'i';
                                                                                                }
                                                                                            } else {
                                                                                                h = h + 1;
                                                                                                ultimoTiro = 'h';
                                                                                            }
                                                                                        } else {
                                                                                            g = g + 1;
                                                                                            ultimoTiro = 'g';
                                                                                        }
                                                                                    } else {
                                                                                        f = f + 1;
                                                                                        ultimoTiro = 'f';
                                                                                    }
                                                                                } else {
                                                                                    e = e + 1;
                                                                                    ultimoTiro = 'e';
                                                                                }
                                                                            } else {
                                                                                d = d + 1;
                                                                                ultimoTiro = 'd';
                                                                            }
                                                                        } else {
                                                                            c = c + 1;
                                                                            ultimoTiro = 'c';
                                                                        }
                                                                    } else {
                                                                        a = a + 1;
                                                                        ultimoTiro = 'a';
                                                                    }
                                                                } else {
                                                                    l = l + 1;
                                                                    ultimoTiro = 'l';
                                                                }
                                                            } else {
                                                                k = k + 1;
                                                                ultimoTiro = 'k';
                                                            }
                                                        } else {
                                                            j = j + 1;
                                                            ultimoTiro = 'j';
                                                        }
                                                    } else {
                                                        i = i + 1;
                                                        ultimoTiro = 'i';
                                                    }
                                                } else {
                                                    h = h + 1;
                                                    ultimoTiro = 'h';
                                                }
                                            } else {
                                                g = g + 1;
                                                ultimoTiro = 'g';
                                            }
                                        } else {
                                            f = f + 1;
                                            ultimoTiro = 'f';
                                        }
                                    } else {
                                        e = e + 1;
                                        ultimoTiro = 'e';
                                    }
                                } else {
                                    d = d + 1;
                                    ultimoTiro = 'd';
                                }
                            } else {
                                c = c + 1;
                                ultimoTiro = 'c';
                            }
                        } //fin for movimientoSemillas

                        b = 0;

                        break; //fin case

                    case 'c':
                        contadorSemillasMovidas = c;
                        for (int n = 0; n < c; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        l = l + 1;
                                                                                                                                                        ultimoTiro = 'l';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    k = k + 1;
                                                                                                                                                    ultimoTiro = 'k';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                j = j + 1;
                                                                                                                                                ultimoTiro = 'j';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            i = i + 1;
                                                                                                                                            ultimoTiro = 'i';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        h = h + 1;
                                                                                                                                        ultimoTiro = 'h';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    g = g + 1;
                                                                                                                                    ultimoTiro = 'g';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                f = f + 1;
                                                                                                                                ultimoTiro = 'f';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            e = e + 1;
                                                                                                                            ultimoTiro = 'e';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        d = d + 1;
                                                                                                                        ultimoTiro = 'd';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    b = b + 1;
                                                                                                                    ultimoTiro = 'b';
                                                                                                                }
                                                                                                            } else {
                                                                                                                a = a + 1;
                                                                                                                ultimoTiro = 'a';
                                                                                                            }
                                                                                                        } else {
                                                                                                            l = l + 1;
                                                                                                            ultimoTiro = 'l';
                                                                                                        }
                                                                                                    } else {
                                                                                                        k = k + 1;
                                                                                                        ultimoTiro = 'k';
                                                                                                    }
                                                                                                } else {
                                                                                                    j = j + 1;
                                                                                                    ultimoTiro = 'j';
                                                                                                }
                                                                                            } else {
                                                                                                i = i + 1;
                                                                                                ultimoTiro = 'i';
                                                                                            }
                                                                                        } else {
                                                                                            h = h + 1;
                                                                                            ultimoTiro = 'h';
                                                                                        }
                                                                                    } else {
                                                                                        g = g + 1;
                                                                                        ultimoTiro = 'g';
                                                                                    }
                                                                                } else {
                                                                                    f = f + 1;
                                                                                    ultimoTiro = 'f';
                                                                                }
                                                                            } else {
                                                                                e = e + 1;
                                                                                ultimoTiro = 'e';
                                                                            }
                                                                        } else {
                                                                            d = d + 1;
                                                                            ultimoTiro = 'd';
                                                                        }
                                                                    } else {
                                                                        b = b + 1;
                                                                        ultimoTiro = 'b';
                                                                    }
                                                                } else {
                                                                    a = a + 1;
                                                                    ultimoTiro = 'a';
                                                                }
                                                            } else {
                                                                l = l + 1;
                                                                ultimoTiro = 'l';
                                                            }
                                                        } else {
                                                            k = k + 1;
                                                            ultimoTiro = 'k';
                                                        }
                                                    } else {
                                                        j = j + 1;
                                                        ultimoTiro = 'j';
                                                    }
                                                } else {
                                                    i = i + 1;
                                                    ultimoTiro = 'i';
                                                }
                                            } else {
                                                h = h + 1;
                                                ultimoTiro = 'h';
                                            }
                                        } else {
                                            g = g + 1;
                                            ultimoTiro = 'g';
                                        }
                                    } else {
                                        f = f + 1;
                                        ultimoTiro = 'f';
                                    }
                                } else {
                                    e = e + 1;
                                    ultimoTiro = 'e';
                                }
                            } else {
                                d = d + 1;
                                ultimoTiro = 'd';
                            }
                        } //fin for movimientoSemillas

                        c = 0;

                        break; //fin case

                    case 'd':
                        contadorSemillasMovidas = d;
                        for (int n = 0; n < d; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        a = a + 1;
                                                                                                                                                        ultimoTiro = 'a';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    l = l + 1;
                                                                                                                                                    ultimoTiro = 'l';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                k = k + 1;
                                                                                                                                                ultimoTiro = 'k';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            j = j + 1;
                                                                                                                                            ultimoTiro = 'j';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        i = i + 1;
                                                                                                                                        ultimoTiro = 'i';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    h = h + 1;
                                                                                                                                    ultimoTiro = 'h';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                g = g + 1;
                                                                                                                                ultimoTiro = 'g';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            f = f + 1;
                                                                                                                            ultimoTiro = 'f';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        e = e + 1;
                                                                                                                        ultimoTiro = 'e';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    c = c + 1;
                                                                                                                    ultimoTiro = 'c';
                                                                                                                }
                                                                                                            } else {
                                                                                                                b = b + 1;
                                                                                                                ultimoTiro = 'b';
                                                                                                            }
                                                                                                        } else {
                                                                                                            a = a + 1;
                                                                                                            ultimoTiro = 'a';
                                                                                                        }
                                                                                                    } else {
                                                                                                        l = l + 1;
                                                                                                        ultimoTiro = 'l';
                                                                                                    }
                                                                                                } else {
                                                                                                    k = k + 1;
                                                                                                    ultimoTiro = 'k';
                                                                                                }
                                                                                            } else {
                                                                                                j = j + 1;
                                                                                                ultimoTiro = 'j';
                                                                                            }
                                                                                        } else {
                                                                                            i = i + 1;
                                                                                            ultimoTiro = 'i';
                                                                                        }
                                                                                    } else {
                                                                                        h = h + 1;
                                                                                        ultimoTiro = 'h';
                                                                                    }
                                                                                } else {
                                                                                    g = g + 1;
                                                                                    ultimoTiro = 'g';
                                                                                }
                                                                            } else {
                                                                                f = f + 1;
                                                                                ultimoTiro = 'f';
                                                                            }
                                                                        } else {
                                                                            e = e + 1;
                                                                            ultimoTiro = 'e';
                                                                        }
                                                                    } else {
                                                                        c = c + 1;
                                                                        ultimoTiro = 'c';
                                                                    }
                                                                } else {
                                                                    b = b + 1;
                                                                    ultimoTiro = 'b';
                                                                }
                                                            } else {
                                                                a = a + 1;
                                                                ultimoTiro = 'a';
                                                            }
                                                        } else {
                                                            l = l + 1;
                                                            ultimoTiro = 'l';
                                                        }
                                                    } else {
                                                        k = k + 1;
                                                        ultimoTiro = 'k';
                                                    }
                                                } else {
                                                    j = j + 1;
                                                    ultimoTiro = 'j';
                                                }
                                            } else {
                                                i = i + 1;
                                                ultimoTiro = 'i';
                                            }
                                        } else {
                                            h = h + 1;
                                            ultimoTiro = 'h';
                                        }
                                    } else {
                                        g = g + 1;
                                        ultimoTiro = 'g';
                                    }
                                } else {
                                    f = f + 1;
                                    ultimoTiro = 'f';
                                }
                            } else {
                                e = e + 1;
                                ultimoTiro = 'e';
                            }
                        } //fin for movimientoSemillas

                        d = 0;

                        break; //fin case

                    case 'e':
                        contadorSemillasMovidas = e;
                        for (int n = 0; n < e; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        b = b + 1;
                                                                                                                                                        ultimoTiro = 'b';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    a = a + 1;
                                                                                                                                                    ultimoTiro = 'a';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                l = l + 1;
                                                                                                                                                ultimoTiro = 'l';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            k = k + 1;
                                                                                                                                            ultimoTiro = 'k';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        j = j + 1;
                                                                                                                                        ultimoTiro = 'j';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    i = i + 1;
                                                                                                                                    ultimoTiro = 'i';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                h = h + 1;
                                                                                                                                ultimoTiro = 'h';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            g = g + 1;
                                                                                                                            ultimoTiro = 'g';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        f = f + 1;
                                                                                                                        ultimoTiro = 'f';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    d = d + 1;
                                                                                                                    ultimoTiro = 'd';
                                                                                                                }
                                                                                                            } else {
                                                                                                                c = c + 1;
                                                                                                                ultimoTiro = 'c';
                                                                                                            }
                                                                                                        } else {
                                                                                                            b = b + 1;
                                                                                                            ultimoTiro = 'b';
                                                                                                        }
                                                                                                    } else {
                                                                                                        a = a + 1;
                                                                                                        ultimoTiro = 'a';
                                                                                                    }
                                                                                                } else {
                                                                                                    l = l + 1;
                                                                                                    ultimoTiro = 'l';
                                                                                                }
                                                                                            } else {
                                                                                                k = k + 1;
                                                                                                ultimoTiro = 'k';
                                                                                            }
                                                                                        } else {
                                                                                            j = j + 1;
                                                                                            ultimoTiro = 'j';
                                                                                        }
                                                                                    } else {
                                                                                        i = i + 1;
                                                                                        ultimoTiro = 'i';
                                                                                    }
                                                                                } else {
                                                                                    h = h + 1;
                                                                                    ultimoTiro = 'h';
                                                                                }
                                                                            } else {
                                                                                g = g + 1;
                                                                                ultimoTiro = 'g';
                                                                            }
                                                                        } else {
                                                                            f = f + 1;
                                                                            ultimoTiro = 'f';
                                                                        }
                                                                    } else {
                                                                        d = d + 1;
                                                                        ultimoTiro = 'd';
                                                                    }
                                                                } else {
                                                                    c = c + 1;
                                                                    ultimoTiro = 'c';
                                                                }
                                                            } else {
                                                                b = b + 1;
                                                                ultimoTiro = 'b';
                                                            }
                                                        } else {
                                                            a = a + 1;
                                                            ultimoTiro = 'a';
                                                        }
                                                    } else {
                                                        l = l + 1;
                                                        ultimoTiro = 'l';
                                                    }
                                                } else {
                                                    k = k + 1;
                                                    ultimoTiro = 'k';
                                                }
                                            } else {
                                                j = j + 1;
                                                ultimoTiro = 'j';
                                            }
                                        } else {
                                            i = i + 1;
                                            ultimoTiro = 'i';
                                        }
                                    } else {
                                        h = h + 1;
                                        ultimoTiro = 'h';
                                    }
                                } else {
                                    g = g + 1;
                                    ultimoTiro = 'g';
                                }
                            } else {
                                f = f + 1;
                                ultimoTiro = 'f';
                            }

                        } //fin for movimientoSemillas

                        e = 0;

                        break; //fin case

                    case 'f':
                        contadorSemillasMovidas = f;
                        for (int n = 0; n < f; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        c = c + 1;
                                                                                                                                                        ultimoTiro = 'c';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    b = b + 1;
                                                                                                                                                    ultimoTiro = 'b';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                a = a + 1;
                                                                                                                                                ultimoTiro = 'a';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            l = l + 1;
                                                                                                                                            ultimoTiro = 'l';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        k = k + 1;
                                                                                                                                        ultimoTiro = 'k';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    j = j + 1;
                                                                                                                                    ultimoTiro = 'j';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                i = i + 1;
                                                                                                                                ultimoTiro = 'i';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            h = h + 1;
                                                                                                                            ultimoTiro = 'h';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        g = g + 1;
                                                                                                                        ultimoTiro = 'g';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    e = e + 1;
                                                                                                                    ultimoTiro = 'e';
                                                                                                                }
                                                                                                            } else {
                                                                                                                d = d + 1;
                                                                                                                ultimoTiro = 'd';
                                                                                                            }
                                                                                                        } else {
                                                                                                            c = c + 1;
                                                                                                            ultimoTiro = 'c';
                                                                                                        }
                                                                                                    } else {
                                                                                                        b = b + 1;
                                                                                                        ultimoTiro = 'b';
                                                                                                    }
                                                                                                } else {
                                                                                                    a = a + 1;
                                                                                                    ultimoTiro = 'a';
                                                                                                }
                                                                                            } else {
                                                                                                l = l + 1;
                                                                                                ultimoTiro = 'l';
                                                                                            }
                                                                                        } else {
                                                                                            k = k + 1;
                                                                                            ultimoTiro = 'k';
                                                                                        }
                                                                                    } else {
                                                                                        j = j + 1;
                                                                                        ultimoTiro = 'j';
                                                                                    }
                                                                                } else {
                                                                                    i = i + 1;
                                                                                    ultimoTiro = 'i';
                                                                                }
                                                                            } else {
                                                                                h = h + 1;
                                                                                ultimoTiro = 'h';
                                                                            }
                                                                        } else {
                                                                            g = g + 1;
                                                                            ultimoTiro = 'g';
                                                                        }
                                                                    } else {
                                                                        e = e + 1;
                                                                        ultimoTiro = 'e';
                                                                    }
                                                                } else {
                                                                    d = d + 1;
                                                                    ultimoTiro = 'd';
                                                                }
                                                            } else {
                                                                c = c + 1;
                                                                ultimoTiro = 'c';
                                                            }
                                                        } else {
                                                            b = b + 1;
                                                            ultimoTiro = 'b';
                                                        }
                                                    } else {
                                                        a = a + 1;
                                                        ultimoTiro = 'a';
                                                    }
                                                } else {
                                                    l = l + 1;
                                                    ultimoTiro = 'l';
                                                }
                                            } else {
                                                k = k + 1;
                                                ultimoTiro = 'k';
                                            }
                                        } else {
                                            j = j + 1;
                                            ultimoTiro = 'j';
                                        }
                                    } else {
                                        i = i + 1;
                                        ultimoTiro = 'i';
                                    }
                                } else {
                                    h = h + 1;
                                    ultimoTiro = 'h';
                                }
                            } else {
                                g = g + 1;
                                ultimoTiro = 'g';
                            }

                        } //fin for movimientoSemillas

                        f = 0;

                        break; //fin case

                    case 'g':
                        contadorSemillasMovidas = g;
                        for (int n = 0; n < g; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        d = d + 1;
                                                                                                                                                        ultimoTiro = 'd';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    c = c + 1;
                                                                                                                                                    ultimoTiro = 'c';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                b = b + 1;
                                                                                                                                                ultimoTiro = 'b';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            a = a + 1;
                                                                                                                                            ultimoTiro = 'a';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        l = l + 1;
                                                                                                                                        ultimoTiro = 'l';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    k = k + 1;
                                                                                                                                    ultimoTiro = 'k';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                j = j + 1;
                                                                                                                                ultimoTiro = 'j';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            i = i + 1;
                                                                                                                            ultimoTiro = 'i';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        h = h + 1;
                                                                                                                        ultimoTiro = 'h';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    f = f + 1;
                                                                                                                    ultimoTiro = 'f';
                                                                                                                }
                                                                                                            } else {
                                                                                                                e = e + 1;
                                                                                                                ultimoTiro = 'e';
                                                                                                            }
                                                                                                        } else {
                                                                                                            d = d + 1;
                                                                                                            ultimoTiro = 'd';
                                                                                                        }
                                                                                                    } else {
                                                                                                        c = c + 1;
                                                                                                        ultimoTiro = 'c';
                                                                                                    }
                                                                                                } else {
                                                                                                    b = b + 1;
                                                                                                    ultimoTiro = 'b';
                                                                                                }
                                                                                            } else {
                                                                                                a = a + 1;
                                                                                                ultimoTiro = 'a';
                                                                                            }
                                                                                        } else {
                                                                                            l = l + 1;
                                                                                            ultimoTiro = 'l';
                                                                                        }
                                                                                    } else {
                                                                                        k = k + 1;
                                                                                        ultimoTiro = 'k';
                                                                                    }
                                                                                } else {
                                                                                    j = j + 1;
                                                                                    ultimoTiro = 'j';
                                                                                }
                                                                            } else {
                                                                                i = i + 1;
                                                                                ultimoTiro = 'i';
                                                                            }
                                                                        } else {
                                                                            h = h + 1;
                                                                            ultimoTiro = 'h';
                                                                        }
                                                                    } else {
                                                                        f = f + 1;
                                                                        ultimoTiro = 'f';
                                                                    }
                                                                } else {
                                                                    e = e + 1;
                                                                    ultimoTiro = 'e';
                                                                }
                                                            } else {
                                                                d = d + 1;
                                                                ultimoTiro = 'd';
                                                            }
                                                        } else {
                                                            c = c + 1;
                                                            ultimoTiro = 'c';
                                                        }
                                                    } else {
                                                        b = b + 1;
                                                        ultimoTiro = 'b';
                                                    }
                                                } else {
                                                    a = a + 1;
                                                    ultimoTiro = 'a';
                                                }
                                            } else {
                                                l = l + 1;
                                                ultimoTiro = 'l';
                                            }
                                        } else {
                                            k = k + 1;
                                            ultimoTiro = 'k';
                                        }
                                    } else {
                                        j = j + 1;
                                        ultimoTiro = 'j';
                                    }
                                } else {
                                    i = i + 1;
                                    ultimoTiro = 'i';
                                }
                            } else {
                                h = h + 1;
                                ultimoTiro = 'h';
                            }


                        } //fin for movimientoSemillas

                        g = 0;

                        break; //fin case

                    case 'h':
                        contadorSemillasMovidas = h;
                        for (int n = 0; n < h; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        e = e + 1;
                                                                                                                                                        ultimoTiro = 'e';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    d = d + 1;
                                                                                                                                                    ultimoTiro = 'd';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                c = c + 1;
                                                                                                                                                ultimoTiro = 'c';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            b = b + 1;
                                                                                                                                            ultimoTiro = 'b';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        a = a + 1;
                                                                                                                                        ultimoTiro = 'a';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    l = l + 1;
                                                                                                                                    ultimoTiro = 'l';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                k = k + 1;
                                                                                                                                ultimoTiro = 'k';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            j = j + 1;
                                                                                                                            ultimoTiro = 'j';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        i = i + 1;
                                                                                                                        ultimoTiro = 'i';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    g = g + 1;
                                                                                                                    ultimoTiro = 'g';
                                                                                                                }
                                                                                                            } else {
                                                                                                                f = f + 1;
                                                                                                                ultimoTiro = 'f';
                                                                                                            }
                                                                                                        } else {
                                                                                                            e = e + 1;
                                                                                                            ultimoTiro = 'e';
                                                                                                        }
                                                                                                    } else {
                                                                                                        d = d + 1;
                                                                                                        ultimoTiro = 'd';
                                                                                                    }
                                                                                                } else {
                                                                                                    c = c + 1;
                                                                                                    ultimoTiro = 'c';
                                                                                                }
                                                                                            } else {
                                                                                                b = b + 1;
                                                                                                ultimoTiro = 'b';
                                                                                            }
                                                                                        } else {
                                                                                            a = a + 1;
                                                                                            ultimoTiro = 'a';
                                                                                        }
                                                                                    } else {
                                                                                        l = l + 1;
                                                                                        ultimoTiro = 'l';
                                                                                    }
                                                                                } else {
                                                                                    k = k + 1;
                                                                                    ultimoTiro = 'k';
                                                                                }
                                                                            } else {
                                                                                j = j + 1;
                                                                                ultimoTiro = 'j';
                                                                            }
                                                                        } else {
                                                                            i = i + 1;
                                                                            ultimoTiro = 'i';
                                                                        }
                                                                    } else {
                                                                        g = g + 1;
                                                                        ultimoTiro = 'g';
                                                                    }
                                                                } else {
                                                                    f = f + 1;
                                                                    ultimoTiro = 'f';
                                                                }
                                                            } else {
                                                                e = e + 1;
                                                                ultimoTiro = 'e';
                                                            }
                                                        } else {
                                                            d = d + 1;
                                                            ultimoTiro = 'd';
                                                        }
                                                    } else {
                                                        c = c + 1;
                                                        ultimoTiro = 'c';
                                                    }
                                                } else {
                                                    b = b + 1;
                                                    ultimoTiro = 'b';
                                                }
                                            } else {
                                                a = a + 1;
                                                ultimoTiro = 'a';
                                            }
                                        } else {
                                            l = l + 1;
                                            ultimoTiro = 'l';
                                        }
                                    } else {
                                        k = k + 1;
                                        ultimoTiro = 'k';
                                    }
                                } else {
                                    j = j + 1;
                                    ultimoTiro = 'j';
                                }
                            } else {
                                i = i + 1;
                                ultimoTiro = 'i';
                            }

                        } //fin for movimientoSemillas

                        h = 0;

                        break; //fin case

                    case 'i':
                        contadorSemillasMovidas = i;
                        for (int n = 0; n < i; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        f = f + 1;
                                                                                                                                                        ultimoTiro = 'f';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    e = e + 1;
                                                                                                                                                    ultimoTiro = 'e';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                d = d + 1;
                                                                                                                                                ultimoTiro = 'd';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            c = c + 1;
                                                                                                                                            ultimoTiro = 'c';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        b = b + 1;
                                                                                                                                        ultimoTiro = 'b';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    a = a + 1;
                                                                                                                                    ultimoTiro = 'a';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                l = l + 1;
                                                                                                                                ultimoTiro = 'l';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            k = k + 1;
                                                                                                                            ultimoTiro = 'k';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        j = j + 1;
                                                                                                                        ultimoTiro = 'j';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    h = h + 1;
                                                                                                                    ultimoTiro = 'h';
                                                                                                                }
                                                                                                            } else {
                                                                                                                g = g + 1;
                                                                                                                ultimoTiro = 'g';
                                                                                                            }
                                                                                                        } else {
                                                                                                            f = f + 1;
                                                                                                            ultimoTiro = 'f';
                                                                                                        }
                                                                                                    } else {
                                                                                                        e = e + 1;
                                                                                                        ultimoTiro = 'e';
                                                                                                    }
                                                                                                } else {
                                                                                                    d = d + 1;
                                                                                                    ultimoTiro = 'd';
                                                                                                }
                                                                                            } else {
                                                                                                c = c + 1;
                                                                                                ultimoTiro = 'c';
                                                                                            }
                                                                                        } else {
                                                                                            b = b + 1;
                                                                                            ultimoTiro = 'b';
                                                                                        }
                                                                                    } else {
                                                                                        a = a + 1;
                                                                                        ultimoTiro = 'a';
                                                                                    }
                                                                                } else {
                                                                                    l = l + 1;
                                                                                    ultimoTiro = 'l';
                                                                                }
                                                                            } else {
                                                                                k = k + 1;
                                                                                ultimoTiro = 'k';
                                                                            }
                                                                        } else {
                                                                            j = j + 1;
                                                                            ultimoTiro = 'j';
                                                                        }
                                                                    } else {
                                                                        h = h + 1;
                                                                        ultimoTiro = 'h';
                                                                    }
                                                                } else {
                                                                    g = g + 1;
                                                                    ultimoTiro = 'g';
                                                                }
                                                            } else {
                                                                f = f + 1;
                                                                ultimoTiro = 'f';
                                                            }
                                                        } else {
                                                            e = e + 1;
                                                            ultimoTiro = 'e';
                                                        }
                                                    } else {
                                                        d = d + 1;
                                                        ultimoTiro = 'd';
                                                    }
                                                } else {
                                                    c = c + 1;
                                                    ultimoTiro = 'c';
                                                }
                                            } else {
                                                b = b + 1;
                                                ultimoTiro = 'b';
                                            }
                                        } else {
                                            a = a + 1;
                                            ultimoTiro = 'a';
                                        }
                                    } else {
                                        l = l + 1;
                                        ultimoTiro = 'l';
                                    }
                                } else {
                                    k = k + 1;
                                    ultimoTiro = 'k';
                                }
                            } else {
                                j = j + 1;
                                ultimoTiro = 'j';
                            }

                        } //fin for movimientoSemillas

                        i = 0;

                        break; //fin case

                    case 'j':
                        contadorSemillasMovidas = j;
                        for (int n = 0; n < j; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        g = g + 1;
                                                                                                                                                        ultimoTiro = 'g';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    f = f + 1;
                                                                                                                                                    ultimoTiro = 'f';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                e = e + 1;
                                                                                                                                                ultimoTiro = 'e';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            d = d + 1;
                                                                                                                                            ultimoTiro = 'd';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        c = c + 1;
                                                                                                                                        ultimoTiro = 'c';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    b = b + 1;
                                                                                                                                    ultimoTiro = 'b';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                a = a + 1;
                                                                                                                                ultimoTiro = 'a';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            l = l + 1;
                                                                                                                            ultimoTiro = 'l';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        k = k + 1;
                                                                                                                        ultimoTiro = 'k';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    i = i + 1;
                                                                                                                    ultimoTiro = 'i';
                                                                                                                }
                                                                                                            } else {
                                                                                                                h = h + 1;
                                                                                                                ultimoTiro = 'h';
                                                                                                            }
                                                                                                        } else {
                                                                                                            g = g + 1;
                                                                                                            ultimoTiro = 'g';
                                                                                                        }
                                                                                                    } else {
                                                                                                        f = f + 1;
                                                                                                        ultimoTiro = 'f';
                                                                                                    }
                                                                                                } else {
                                                                                                    e = e + 1;
                                                                                                    ultimoTiro = 'e';
                                                                                                }
                                                                                            } else {
                                                                                                d = d + 1;
                                                                                                ultimoTiro = 'd';
                                                                                            }
                                                                                        } else {
                                                                                            c = c + 1;
                                                                                            ultimoTiro = 'c';
                                                                                        }
                                                                                    } else {
                                                                                        b = b + 1;
                                                                                        ultimoTiro = 'b';
                                                                                    }
                                                                                } else {
                                                                                    a = a + 1;
                                                                                    ultimoTiro = 'a';
                                                                                }
                                                                            } else {
                                                                                l = l + 1;
                                                                                ultimoTiro = 'l';
                                                                            }
                                                                        } else {
                                                                            k = k + 1;
                                                                            ultimoTiro = 'k';
                                                                        }
                                                                    } else {
                                                                        i = i + 1;
                                                                        ultimoTiro = 'i';
                                                                    }
                                                                } else {
                                                                    h = h + 1;
                                                                    ultimoTiro = 'h';
                                                                }
                                                            } else {
                                                                g = g + 1;
                                                                ultimoTiro = 'g';
                                                            }
                                                        } else {
                                                            f = f + 1;
                                                            ultimoTiro = 'f';
                                                        }
                                                    } else {
                                                        e = e + 1;
                                                        ultimoTiro = 'e';
                                                    }
                                                } else {
                                                    d = d + 1;
                                                    ultimoTiro = 'd';
                                                }
                                            } else {
                                                c = c + 1;
                                                ultimoTiro = 'c';
                                            }
                                        } else {
                                            b = b + 1;
                                            ultimoTiro = 'b';
                                        }
                                    } else {
                                        a = a + 1;
                                        ultimoTiro = 'a';
                                    }
                                } else {
                                    l = l + 1;
                                    ultimoTiro = 'l';
                                }
                            } else {
                                k = k + 1;
                                ultimoTiro = 'k';
                            }

                        } //fin for movimientoSemillas

                        j = 0;

                        break; //fin case

                    case 'k':
                        contadorSemillasMovidas = k;
                        for (int n = 0; n < k; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        h = h + 1;
                                                                                                                                                        ultimoTiro = 'h';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    g = g + 1;
                                                                                                                                                    ultimoTiro = 'g';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                f = f + 1;
                                                                                                                                                ultimoTiro = 'f';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            e = e + 1;
                                                                                                                                            ultimoTiro = 'e';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        d = d + 1;
                                                                                                                                        ultimoTiro = 'd';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    c = c + 1;
                                                                                                                                    ultimoTiro = 'c';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                b = b + 1;
                                                                                                                                ultimoTiro = 'b';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            a = a + 1;
                                                                                                                            ultimoTiro = 'a';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        l = l + 1;
                                                                                                                        ultimoTiro = 'l';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    j = j + 1;
                                                                                                                    ultimoTiro = 'j';
                                                                                                                }
                                                                                                            } else {
                                                                                                                i = i + 1;
                                                                                                                ultimoTiro = 'i';
                                                                                                            }
                                                                                                        } else {
                                                                                                            h = h + 1;
                                                                                                            ultimoTiro = 'h';
                                                                                                        }
                                                                                                    } else {
                                                                                                        g = g + 1;
                                                                                                        ultimoTiro = 'g';
                                                                                                    }
                                                                                                } else {
                                                                                                    f = f + 1;
                                                                                                    ultimoTiro = 'f';
                                                                                                }
                                                                                            } else {
                                                                                                e = e + 1;
                                                                                                ultimoTiro = 'e';
                                                                                            }
                                                                                        } else {
                                                                                            d = d + 1;
                                                                                            ultimoTiro = 'd';
                                                                                        }
                                                                                    } else {
                                                                                        c = c + 1;
                                                                                        ultimoTiro = 'c';
                                                                                    }
                                                                                } else {
                                                                                    b = b + 1;
                                                                                    ultimoTiro = 'b';
                                                                                }
                                                                            } else {
                                                                                a = a + 1;
                                                                                ultimoTiro = 'a';
                                                                            }
                                                                        } else {
                                                                            l = l + 1;
                                                                            ultimoTiro = 'l';
                                                                        }
                                                                    } else {
                                                                        j = j + 1;
                                                                        ultimoTiro = 'j';
                                                                    }
                                                                } else {
                                                                    i = i + 1;
                                                                    ultimoTiro = 'i';
                                                                }
                                                            } else {
                                                                h = h + 1;
                                                                ultimoTiro = 'h';
                                                            }
                                                        } else {
                                                            g = g + 1;
                                                            ultimoTiro = 'g';
                                                        }
                                                    } else {
                                                        f = f + 1;
                                                        ultimoTiro = 'f';
                                                    }
                                                } else {
                                                    e = e + 1;
                                                    ultimoTiro = 'e';
                                                }
                                            } else {
                                                d = d + 1;
                                                ultimoTiro = 'd';
                                            }
                                        } else {
                                            c = c + 1;
                                            ultimoTiro = 'c';
                                        }
                                    } else {
                                        b = b + 1;
                                        ultimoTiro = 'b';
                                    }
                                } else {
                                    a = a + 1;
                                    ultimoTiro = 'a';
                                }
                            } else {
                                l = l + 1;
                                ultimoTiro = 'l';
                            }

                        } //fin for movimientoSemillas

                        k = 0;

                        break; //fin case

                    case 'l':
                        contadorSemillasMovidas = l;
                        for (int n = 0; n < l; n++) { //este for llevará el movimiento de las semillas
                            if (n > 0) {
                                if (n > 1) {
                                    if (n > 2) {
                                        if (n > 3) {
                                            if (n > 4) {
                                                if (n > 5) {
                                                    if (n > 6) {
                                                        if (n > 7) {
                                                            if (n > 8) {
                                                                if (n > 9) {
                                                                    if (n > 10) {
                                                                        if (n > 11) {
                                                                            if (n > 12) {
                                                                                if (n > 13) {
                                                                                    if (n > 14) {
                                                                                        if (n > 15) {
                                                                                            if (n > 16) {
                                                                                                if (n > 17) {
                                                                                                    if (n > 18) {
                                                                                                        if (n > 19) {
                                                                                                            if (n > 20) {
                                                                                                                if (n > 21) {
                                                                                                                    if (n > 22) {
                                                                                                                        if (n > 23) {
                                                                                                                            if (n > 24) {
                                                                                                                                if (n > 25) {
                                                                                                                                    if (n > 26) {
                                                                                                                                        if (n > 27) {
                                                                                                                                            if (n > 28) {
                                                                                                                                                if (n > 29) {
                                                                                                                                                    if (n > 30) {
                                                                                                                                                        System.out.println("Demasiadas semillas en una misma casilla uwu");
                                                                                                                                                    } else {
                                                                                                                                                        i = i + 1;
                                                                                                                                                        ultimoTiro = 'i';
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    h = h + 1;
                                                                                                                                                    ultimoTiro = 'h';
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                g = g + 1;
                                                                                                                                                ultimoTiro = 'g';
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            f = f + 1;
                                                                                                                                            ultimoTiro = 'f';
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        e = e + 1;
                                                                                                                                        ultimoTiro = 'e';
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    d = d + 1;
                                                                                                                                    ultimoTiro = 'd';
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                c = c + 1;
                                                                                                                                ultimoTiro = 'c';
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            b = b + 1;
                                                                                                                            ultimoTiro = 'b';
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        a = a + 1;
                                                                                                                        ultimoTiro = 'a';
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    k = k + 1;
                                                                                                                    ultimoTiro = 'k';
                                                                                                                }
                                                                                                            } else {
                                                                                                                j = j + 1;
                                                                                                                ultimoTiro = 'j';
                                                                                                            }
                                                                                                        } else {
                                                                                                            i = i + 1;
                                                                                                            ultimoTiro = 'i';
                                                                                                        }
                                                                                                    } else {
                                                                                                        h = h + 1;
                                                                                                        ultimoTiro = 'h';
                                                                                                    }
                                                                                                } else {
                                                                                                    g = g + 1;
                                                                                                    ultimoTiro = 'g';
                                                                                                }
                                                                                            } else {
                                                                                                f = f + 1;
                                                                                                ultimoTiro = 'f';
                                                                                            }
                                                                                        } else {
                                                                                            e = e + 1;
                                                                                            ultimoTiro = 'e';
                                                                                        }
                                                                                    } else {
                                                                                        d = d + 1;
                                                                                        ultimoTiro = 'd';
                                                                                    }
                                                                                } else {
                                                                                    c = c + 1;
                                                                                    ultimoTiro = 'c';
                                                                                }
                                                                            } else {
                                                                                b = b + 1;
                                                                                ultimoTiro = 'b';
                                                                            }
                                                                        } else {
                                                                            a = a + 1;
                                                                            ultimoTiro = 'a';
                                                                        }
                                                                    } else {
                                                                        k = k + 1;
                                                                        ultimoTiro = 'k';
                                                                    }
                                                                } else {
                                                                    j = j + 1;
                                                                    ultimoTiro = 'j';
                                                                }
                                                            } else {
                                                                i = i + 1;
                                                                ultimoTiro = 'i';
                                                            }
                                                        } else {
                                                            h = h + 1;
                                                            ultimoTiro = 'h';
                                                        }
                                                    } else {
                                                        g = g + 1;
                                                        ultimoTiro = 'g';
                                                    }
                                                } else {
                                                    f = f + 1;
                                                    ultimoTiro = 'f';
                                                }
                                            } else {
                                                e = e + 1;
                                                ultimoTiro = 'e';
                                            }
                                        } else {
                                            d = d + 1;
                                            ultimoTiro = 'd';
                                        }
                                    } else {
                                        c = c + 1;
                                        ultimoTiro = 'c';
                                    }
                                } else {
                                    b = b + 1;
                                    ultimoTiro = 'b';
                                }
                            } else {
                                a = a + 1;
                                ultimoTiro = 'a';
                            }

                        } //fin for movimientoSemillas

                        l = 0;

                        break; //fin case

                    case 'n': // n significa no elecion, solo saldrá este caso si la partida va a terminar por tener 0 fichas en un lado y no poder pasarle fichas desde el otro
                        ultimoTiro = 'n';
                        break;

                } //fin switchEleccion

            } //fin else que comprueba que el jugador tiene semillas para mover


            if (contadorTurnos % 2 != 0) {

                do {

                    switch (ultimoTiro) {
                        case 'l':
                            if (contadorSemillasMovidas > 0) {
                                if (l == 2 || l == 3) {
                                    if (contadorTurnos % 2 != 0) {
                                        puntuacion1 = puntuacion1 + l;
                                        l = 0;
                                        ultimoTiro = 'k';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'k':
                            if (contadorSemillasMovidas > 0) {
                                if (k == 2 || k == 3) {
                                    if (contadorTurnos % 2 != 0) {
                                        puntuacion1 = puntuacion1 + k;
                                        k = 0;
                                        ultimoTiro = 'j';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'j':
                            if (contadorSemillasMovidas > 0) {
                                if (j == 2 || j == 3) {
                                    if (contadorTurnos % 2 != 0) {
                                        puntuacion1 = puntuacion1 + j;
                                        j = 0;
                                        ultimoTiro = 'i';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'i':
                            if (contadorSemillasMovidas > 0) {
                                if (i == 2 || i == 3) {
                                    if (contadorTurnos % 2 != 0) {
                                        puntuacion1 = puntuacion1 + i;
                                        i = 0;
                                        ultimoTiro = 'h';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'h':
                            if (contadorSemillasMovidas > 0) {
                                if (h == 2 || h == 3) {
                                    if (contadorTurnos % 2 != 0) {
                                        puntuacion1 = puntuacion1 + h;
                                        h = 0;
                                        ultimoTiro = 'g';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'g':
                            if (contadorSemillasMovidas > 0) {
                                if (g == 2 || g == 3) {
                                    if (contadorTurnos % 2 != 0) {
                                        puntuacion1 = puntuacion1 + g;
                                        g = 0;
                                    }
                                }
                            }
                            ultimoTiro = 'x';
                            break;

                        case 'n': // n significa no elecion, solo saldrá este caso si la partida va a terminar por tener 0 fichas en un lado y no poder pasarle fichas desde el otro
                            ultimoTiro = 'x';
                            break;

                        default:
                            ultimoTiro = 'x';
                            break;

                    }
                } while (ultimoTiro != 'x');
            }

            if (contadorTurnos % 2 == 0) {

                do {

                    switch (ultimoTiro) {
                        case 'f':
                            if (contadorSemillasMovidas > 0) {
                                if (f == 2 || f == 3) {
                                    if (contadorTurnos % 2 == 0) {
                                        puntuacion2 = puntuacion2 + f;
                                        f = 0;
                                        ultimoTiro = 'e';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'e':
                            if (contadorSemillasMovidas > 0) {
                                if (e == 2 || e == 3) {
                                    if (contadorTurnos % 2 == 0) {
                                        puntuacion2 = puntuacion2 + e;
                                        e = 0;
                                        ultimoTiro = 'd';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'd':
                            if (contadorSemillasMovidas > 0) {
                                if (d == 2 || d == 3) {
                                    if (contadorTurnos % 2 == 0) {
                                        puntuacion2 = puntuacion2 + d;
                                        d = 0;
                                        ultimoTiro = 'c';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'c':
                            if (contadorSemillasMovidas > 0) {
                                if (c == 2 || c == 3) {
                                    if (contadorTurnos % 2 == 0) {
                                        puntuacion2 = puntuacion2 + c;
                                        c = 0;
                                        ultimoTiro = 'b';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'b':
                            if (contadorSemillasMovidas > 0) {
                                if (b == 2 || b == 3) {
                                    if (contadorTurnos % 2 == 0) {
                                        puntuacion2 = puntuacion2 + b;
                                        b = 0;
                                        ultimoTiro = 'a';
                                    }
                                } else {
                                    ultimoTiro = 'x';
                                }
                            }
                            contadorSemillasMovidas--;
                            break;

                        case 'a':
                            if (contadorSemillasMovidas > 0) {
                                if (a == 2 || a == 3) {
                                    if (contadorTurnos % 2 == 0) {
                                        puntuacion2 = puntuacion2 + a;
                                        a = 0;
                                    }
                                }
                            }
                            ultimoTiro = 'x';
                            break;

                        case 'n': // n significa no elecion, solo saldrá este caso si la partida va a terminar por tener 0 fichas en un lado y no poder pasarle fichas desde el otro
                            ultimoTiro = 'x';
                            break;

                        default:
                            ultimoTiro = 'x';
                            break;

                    } //fin switch ultimoTiro que controla si se hacen cosechas y si hacer varias
                } while (ultimoTiro != 'x');
            }


            if (!statusJuego.equalsIgnoreCase("fin")) {

                if (contadorTurnos % 2 != 0) { //comprobamos si le hemos dejado a 0 con cosechas
                    totalEnTablero = g + h + i + j + k + l;
                } else {
                    totalEnTablero = a + b + c + d + e + f;
                }
                if (totalEnTablero == 0) {
                    finJuego = totalEnTablero;
                    System.out.println("Partida finalizada, el contrincante se ha quedado sin semillas tras tus cosechas.");
                    System.out.println("Se sumarán las semillas en tu propiedad a tu puntuación.");

                    if (contadorTurnos % 2 == 0) { //obtenemos las semillas que tenga en propiedad el jugador
                        totalEnTablero = g + h + i + j + k + l;
                        puntuacion2 = puntuacion2 + totalEnTablero;
                    } else {
                        totalEnTablero = a + b + c + d + e + f;
                        puntuacion1 = puntuacion1 + totalEnTablero;
                    }

                }
            }

            if (finJuego == 0) {
                statusJuego = "fin";
            } else {
                contadorTurnos++;
            }

        } while (!statusJuego.equalsIgnoreCase("fin"));

        System.out.println(""); //espacio para mostrar el resultado claramente
        System.out.println("Puntuación total:");
        System.out.println(jugador1 + ": " + puntuacion1);
        System.out.println(jugador2 + ": " + puntuacion2);
        System.out.println(""); //espacio para mostrar el mensaje de ganadores claramente


        if (puntuacion1 == puntuacion2) {
            System.out.println("¡Empate! Bien jugado " + jugador1 + " y " + jugador2 + ".");
        } else {
            if (puntuacion1 > puntuacion2) {
                System.out.println("¡" + jugador1 + " ha ganado!");
            } else {
                System.out.println("¡" + jugador2 + " ha ganado!");
            }
        }

        /*
            movimiento semilla siempre +1 hacia la derecha
            el jugador solo puede seleccionar sus parcelas
            saltar la parcela desde la que se comienza si hay 12 o más semillas
            cosechas
            cosechas multiples
            se lleva un recuento
            si al cosechar al jugador opuesto este se ha quedado sin semillas se finaliza partida
	            si se da este caso se suman las semillas que tenga el jugador a su puntuacion
	        la cosecha solo se hace a partir del ultimo tiro
            si el jugador no tiene semillas se le salta el turno
            fin partida cuando deba serlo
            hay un ganador ó un empate
         */
    }
}
