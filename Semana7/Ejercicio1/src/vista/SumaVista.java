package vista;

import java.util.Scanner;
import controlador.SumaControlador;

public class SumaVista {
    private SumaControlador controlador;
    private Scanner lector;

    public SumaVista() {
        this.lector = new Scanner(System.in);
    }

    public void establecerControlador(SumaControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenuPrincipal() {
        String opcion;
        do {
            System.out.println("\n=== SUMA DE ENTEROS (MVC) ===");
            System.out.println("1. Leer los números");
            System.out.println("2.- Calcular la suma");
            System.out.println("3. Mostrar el resultado");
            System.out.println("0.- Salir");
            System.out.print("Elige una opción: ");
            opcion = lector.nextLine().trim();

            switch (opcion) {
                case "1":
                    leerNumeros();
                    break;
                case "2":
                    calcularSuma();
                    break;
                case "3":
                    mostrarResultado();
                    break;
               case "0":
                mostrarMensajeDespedida();
            break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("0"));;

        lector.close();
    }

    private void leerNumeros() {
        try {
            System.out.print("Introduce el primer número entero: ");
            int n1 = Integer.parseInt(lector.nextLine().trim());
            System.out.print("Introduce el segundo número entero: ");
            int n2 = Integer.parseInt(lector.nextLine().trim());

            controlador.guardarNumeros(n1, n2);
            System.out.println("Números guardados correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes introducir números enteros válidos.");
        }
    }

    private void calcularSuma() {
        if (controlador.calcularSuma()) {
            System.out.println("Suma calculada correctamente.");
        } else {
            System.out.println("Error: Primero debes leer los números (opción 1).");
        }
    }

    private void mostrarResultado() {
        if (controlador.estaSumaCalculada()) {
            System.out.printf("Resultado: %d + %d = %d%n",
                    controlador.obtenerNumero1(),
                    controlador.obtenerNumero2(),
                    controlador.obtenerResultado());
        } else if (!controlador.estanNumerosLeidos()) {
            System.out.println("Error: Primero debes leer los números (opción 1) y calcular la suma (opción 2).");
        } else {
            System.out.println("Error: Aún no se ha calculado la suma (opción 2).");
        }
    }

    public void mostrarMensajeDespedida() {
        System.out.println("Saliendo del programa...");
    }
}