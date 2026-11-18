package vista;

import java.util.Scanner;
import controlador.AlmacenesControlador;
import modelo.AlmacenesModelo;

public class AlmacenesVista {
    private AlmacenesControlador controlador;
    private Scanner lector;

    public AlmacenesVista() {
        this.lector = new Scanner(System.in);
    }

    public void establecerControlador(AlmacenesControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenuPrincipal() {
        String opcion;
        do {
            System.out.println("\n=== GESTIÓN DE ALMACENES (MVC) ===");
            System.out.println("1. Leer tabla de ventas");
            System.out.println("2. Leer tabla de precios");
            System.out.println("3.- Calcular ingresos totales");
            System.out.println("4. Mostrar resultados");
            System.out.println("0.- Salir");
            System.out.print("Elige una opción: ");
            opcion = lector.nextLine().trim();

            switch (opcion) {
                case "1":
                    leerTablaVentas();
                    break;
                case "2":
                    leerTablaPrecios();
                    break;
                case "3":
                    calcularIngresos();
                    break;
                case "4":
                    mostrarResultados();
                    break;
                case "0":
                System.out.println("Saliendo de la aplicación...");
                break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("0"));

        lector.close();
    }

    private void leerTablaVentas() {
        int[][] ventas = new int[AlmacenesModelo.NUM_ALMACENES][AlmacenesModelo.NUM_PRODUCTOS];
        try {
            System.out.println("\n--- LECTURA DE VENTAS (UNIDADES) ---");
            for (int i = 0; i < AlmacenesModelo.NUM_ALMACENES; i++) {
                System.out.println("Almacén " + (i + 1) + ":");
                for (int j = 0; j < AlmacenesModelo.NUM_PRODUCTOS; j++) {
                    System.out.print("  Unidades vendidas Producto " + (j + 1) + ": ");
                    int unidades = Integer.parseInt(lector.nextLine().trim());
                    if (unidades < 0) {
                        System.out.println("Error: Las unidades no pueden ser negativas.");
                        return;
                    }
                    ventas[i][j] = unidades;
                }
            }
            controlador.guardarVentas(ventas);
            System.out.println("Tabla de ventas guardada correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce valores enteros válidos.");
        }
    }

    private void leerTablaPrecios() {
        double[] precios = new double[AlmacenesModelo.NUM_PRODUCTOS];
        try {
            System.out.println("\n--- LECTURA DE PRECIOS POR UNIDAD ---");
            for (int j = 0; j < AlmacenesModelo.NUM_PRODUCTOS; j++) {
                System.out.print("Precio Producto " + (j + 1) + " (EUR): ");
                double precio = Double.parseDouble(lector.nextLine().trim());
                if (precio < 0) {
                    System.out.println("Error: El precio no puede ser negativo.");
                    return;
                }
                precios[j] = precio;
            }
            controlador.guardarPrecios(precios);
            System.out.println("Tabla de precios guardada correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce valores numéricos válidos.");
        }
    }

    private void calcularIngresos() {
        if (!controlador.estanVentasLeidas() && !controlador.estanPreciosLeidos()) {
            System.out.println("Error: No se pueden calcular los ingresos. Debes leer antes las ventas (opción 1) y los precios (opción 2).");
            return;
        }
        if (!controlador.estanVentasLeidas()) {
            System.out.println("Error: No se pueden calcular los ingresos. Falta leer la tabla de ventas (opción 1).");
            return;
        }
        if (!controlador.estanPreciosLeidos()) {
            System.out.println("Error: No se pueden calcular los ingresos. Falta leer la tabla de precios (opción 2).");
            return;
        }

        if (controlador.calcularIngresosTotales()) {
            System.out.println("Ingresos totales calculados correctamente.");
        }
    }

    private void mostrarResultados() {
        if (!controlador.estanIngresosCalculados()) {
            System.out.println("Error: No se pueden mostrar resultados. Asegúrate de haber leído ventas y precios, y ejecutado la opción 3.");
            return;
        }

        int[][] ventas = controlador.obtenerVentas();
        double[] precios = controlador.obtenerPrecios();
        double[] ingresosAlmacen = controlador.obtenerIngresosPorAlmacen();
        double total = controlador.obtenerIngresosTotales();

        String separador = "+------------+--------------+--------------+-----------------+";
        System.out.println("\nPrecios unitarios -> Producto 1: " + String.format("%.2f EUR", precios[0])
        + " | Producto 2: " + String.format("%.2f EUR", precios[1]));
        System.out.println(separador);
        System.out.printf("| %-10s | %12s | %12s | %15s |%n",
                "Almacén", "Prod. 1 (ud)", "Prod. 2 (ud)", "Ingresos");
        System.out.println(separador);

        for (int i = 0; i < AlmacenesModelo.NUM_ALMACENES; i++) {
            System.out.printf("| %-10s | %12d | %12d | %13.2f \u20AC |%n",
                    "Almacén " + (i + 1),
                    ventas[i][0],
                    ventas[i][1],
                    ingresosAlmacen[i]);
        }

        System.out.println(separador);
        System.out.printf("| %-40s | %11.2f EUR |%n", "INGRESOS TOTALES", total);
        System.out.println(separador);
    }
}
    