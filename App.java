// < Librerias >
import java.util.Scanner;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class App {
    // < Entrada de datos mediante escáner. >
    static Scanner sc = new Scanner(System.in);
    static Random r = new Random();

    // < Redireccionar al menú principal. >
    public static void go_to() {
        char volver;

        System.out.print("¿Deseas volver al menu principal?: (Y/N): ");
        volver = sc.next().charAt(0);

        if (volver == 'y' || volver == 'Y') {
            Menu();
        } else if (volver == 'n' || volver == 'N') {
            System.exit(0);
        } else {
            System.out.println("[>] Error, has introducido mal una opcion.");
            System.exit(0);
        }
    }

    // < Interfaz del error 1. >
    public static void err1() {
        System.out.println("""
                |
                |
                |
                |
                |
                |
                ---------------------
                    """);
    }

    // < Interfaz del error 2. >
    public static void err2() {
        System.out.println("""
                |
                |  O
                |
                |
                |
                |
                ---------------------
                    """);
    }

    // < Interfaz del error 3. >
    public static void err3() {
        System.out.println("""
                |
                |  O
                |  |
                |  |
                |
                |
                ---------------------
                    """);
    }

    // < Interfaz del error 4. >
    public static void err4() {
        System.out.println("""
                |
                |  O
                |  |
                |  |
                | / \\
                |
                ---------------------
                    """);
    }

    // < Interfaz del error 5. >
    public static void err5() {
        System.out.println("""
                |
                |  O
                | /|\\
                |  |
                | / \\
                |
                ---------------------

                ¡P E R D I S T E!
                    """);
    }

    // < Menú principal >
    public static void Menu() {
        int opcion, n, pos = 0, intentos = 0;

        LimpiarConsola();
        System.out.println("\n[*************** MENU ***************]\n");
        System.out.println(
                "1.- Juego del ahorcado\n2.- Creditos del programa\n3.- Reglas del juego\n4.- Pronóstico del tiempo.\n5.- Fecha y hora actuales.\n6.- Apagar la PC.\n7.- Salir del programa.\n[************************************]");
        System.out.print("\n> Ingrese una opcion para continuar: ");
        opcion = sc.nextInt();

        if (opcion == 1) {
            LimpiarConsola();
            System.out.println("\n|-|-|-|- ¡BIENVENIDO AL JUEGO! |-|-|-|-\n¡Recuerda leer las reglas!\n");
            System.out.print("> Ingrese cuantas palabras quiere ingresar: ");
            n = sc.nextInt();
            sc.nextLine();

            String[] palabras = new String[n];

            for (int i = 0; i < palabras.length; i++) {
                System.out.print("> Ingrese la/una nueva palabra: ");
                palabras[i] = sc.nextLine();
            }

            int elemento_random = r.nextInt(palabras.length);

            char[] aciertos = new char[palabras[elemento_random].length()];
            boolean letra_encontrada = false;
            int relleno_letras = 0;

            // # El juego se repite durante N Intentos
            LimpiarConsola();
            while (true) {
                StringBuilder letra = new StringBuilder();
                System.out.print("\nPALABRA A ADIVINAR: ");

                // # Algoritmo para sustituir letra por guíon
                for (int i = 0; i < aciertos.length; i++) {
                    if (aciertos[i] != 0x00) {
                        letra.append(aciertos[i]);
                    } else {
                        letra.append("_");
                    }
                }

                System.out.println(letra);

                // # El usuario juega una Letra
                System.out.print("[-] Ingrese una letra: ");
                char letra_ingresada = sc.next().charAt(0);

                // # Buscar si la letra ingresada existe en la palabra oculta
                for (int t = 0; t < palabras[elemento_random].length(); t++) {

                    if (aciertos[t] != 0x00) {
                        continue;
                    }

                    if (palabras[elemento_random].charAt(t) == letra_ingresada) {
                        aciertos[t] = palabras[elemento_random].charAt(t);
                        letra_encontrada = true;
                        relleno_letras++;
                        break;
                    }
                }

                /*
                 * > Pregunta si ya todas las letras fueron rellenadas con 1 entonces si es asi
                 * finaliza el juego
                 * aciertos: La cantidad de letras de la palabra
                 * relleno_letras: La cantidad de letras que acierta el jugador en la palabra
                 * 
                 * Ejemplo
                 * aciertos = 9
                 * relleno_letras = x (las letras que lleve acertando el jugador actualmente)
                 * 
                 * N i c a r a g u a
                 * 1 1 1 1 1 1 1 1 1
                 */

                if (relleno_letras == aciertos.length) {
                    System.out.println("[#] Has ganado el juego!");
                    go_to();
                    break;
                }

                if (letra_encontrada == true) {
                    System.out.println("[#] Has encontrado una letra.");
                } else {
                    intentos++;
                    if (intentos == 1) {
                        err1();
                    } else if (intentos == 2) {
                        err2();
                    } else if (intentos == 3) {
                        err3();
                    } else if (intentos == 4) {
                        err4();
                    } else if (intentos == 5) {
                        err5();
                        go_to();
                    }
                }
            }

        } else if (opcion == 2) {
            Creditos();
        } else if (opcion == 3) {
            Reglas();
        } else if (opcion == 4) {
            pronostico();
        } else if (opcion == 5) {
            fecha_hora();
        } else if (opcion == 6) {
            try {
                apagar_pc();
            } catch (RuntimeException e) {
                System.out.println("[* No se pudo apagar la PC, error: " + e + " *]");
            } catch (IOException e) {
                System.out.println("[# Error!: " + e + " *]");
            }
        } else if (opcion == 7) {
            System.out.println("[# Gracias por haber utilizado este programa! #]");
            System.exit(0);
        }
    }

    // < Obtener fecha y hora actual del PC. >
    public static void fecha_hora() {
        LimpiarConsola();
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();

        System.out.println("[# La hora actual es: " + hora + " *]\n" + "[# La fecha actual es: " + fecha + " *]\n");

        go_to();
    }

    // < Método para apagar la PC. >
    public static void apagar_pc() throws RuntimeException, IOException {
        String comando_apagar;
        String sistema_operativo = System.getProperty("os.name");

        if ("Linux".equals(sistema_operativo) || "Mac OS X".equals(sistema_operativo)) {
            comando_apagar = "shutdown -h now";
        } else if (sistema_operativo.contains("Windows")) {
            comando_apagar = "shutdown.exe -s -t 0";
        } else {
            throw new RuntimeException("[* El sistema operativo no pertenece a Linux, Windows o Mac OS X *]");
        }

        Runtime.getRuntime().exec(comando_apagar);
        System.exit(0);
    }

    // < Obtener el pronostico del tiempo. >
    public static void pronostico() {
        LimpiarConsola();

        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(
                    "https://www.accuweather.com/es/uy/reducto/349262/daily-weather-forecast/349262?day=23%22");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        go_to();
    }

    // < Créditos del programa. >
    public static void Creditos() {
        LimpiarConsola();
        System.out.println(
                "\n[******* CREDITOS DEL PROGRAMA *******]\n- Trabajo realizado por: Diego Breeze\n[************************************]");
        go_to();
    }

    // < Reglas del Ahorcado. >
    public static void Reglas() {
        LimpiarConsola();
        System.out.println("\n|-|-|-|- REGLAS DEL JUEGO |-|-|-|-");
        System.out.println(
                """
                        Turno:
                        - Muñeco: El muñeco se dibuja en 5 partes (cabeza, tronco y extremidades), por lo que el adivinador tiene 5 posibilidades de fallar.
                        - Adivinar la Palabra: El jugador puede intentar adivinar la palabra o frase secreta.
                        -- Si acierta la palabra, entonces el programa completa la solución en la consola.
                        -- Si no acierta la palabra, entonces el programa dibujará una parte del muñeco.

                        Fin de la partida:
                        - GANA el adivinador si descubre la palabra o frase secreta.
                        - PIERDE el avidinador si se dibuja el muñeco completo en la horca.
                            """);

        go_to();
    }

    public static void main(String[] args) {
        Menu();
    }

    // < Método para limpiar la consola (cls). >
    public static void LimpiarConsola() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
        }
    }
}
