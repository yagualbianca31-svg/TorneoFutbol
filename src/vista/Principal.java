package vista;

import controlador.ControladorTorneo;
import modelo.Equipo;
import modelo.SorteoInvalidoException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControladorTorneo controlador = new ControladorTorneo("Liga Pro Ecuador");

        int opcion = 0;
        do {
            System.out.println("\n--- TORNEO DE FÚTBOL ECUATORIANO ---");
            System.out.println("1. Ver tabla de posiciones");
            System.out.println("2. Simular una jornada (fecha)");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- TABLA DE POSICIONES ACTUAL ---");
                        int pos = 1;
                        for (Equipo e : controlador.obtenerTablaPosiciones()) {
                            System.out.println(pos + ". " + e);
                            pos++;
                        }
                        break;
                    case 2:
                        try {
                            controlador.simularJornada();
                            System.out.println("\n¡Jornada simulada con éxito!");
                            System.out.println("Seleccione la opción 1 para ver cómo quedó la tabla.");
                        } catch (SorteoInvalidoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Saliendo del torneo! Gracias por usar el sistema.");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar entrada incorrecta
            }
        } while (opcion != 3);
        scanner.close();
    }
}