package vista;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import controlador.FacturasControlador;
import modelo.Factura;

public class FacturasVista {
    private FacturasControlador controlador;
    private Scanner lector;

    public FacturasVista() {
        this.lector = new Scanner(System.in);
    }

    public void establecerControlador(FacturasControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarEstadoCarga(boolean cargadas) {
        if (cargadas) {
            System.out.println("Se han cargado " + controlador.obtenerFacturas().size()
                    + " facturas desde: " + controlador.obtenerRutaTsv());
        } else {
            System.out.println("Aviso: No se encontraron facturas en: " + controlador.obtenerRutaTsv());
        }
    }

    public void mostrarMenuPrincipal() {
        String opcion;
        do {
            System.out.println("\n=== GESTIÓN DE FACTURAS TSV (MVC) ===");
            System.out.println("1. Buscar clientes con factura superior a un importe mínimo");
            System.out.println("2. Mostrar listado total de facturas");
            System.out.println("3. Exportar facturas a HTML");
            System.out.println("4. Exportar facturas a CSV");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = lector.nextLine().trim();

            switch (opcion) {
                case "1":
                    buscarClientesPorImporteMinimo();
                    break;
                case "2":
                    mostrarListadoFacturas(controlador.obtenerFacturas(), "LISTADO TOTAL DE FACTURAS");
                    break;
                case "3":
                    exportarHtml();
                    break;
                case "4":
                    exportarCsv();
                    break;
                case "0":
                    System.out.println("Saliendo de gestión de facturas...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("0"));

        lector.close();
    }

    private void buscarClientesPorImporteMinimo() {
        if (!controlador.hayFacturas()) {
            System.out.println("No hay facturas cargadas.");
            return;
        }

        try {
            System.out.print("Introduce el importe mínimo de factura (EUR): ");
            double minimo = Double.parseDouble(lector.nextLine().trim().replace(",", "."));
            ArrayList<Factura> filtradas = controlador.filtrarFacturasMayoresQue(minimo);

            if (filtradas.isEmpty()) {
                System.out.println("Ningún cliente supera el importe de " + String.format("%.2f EUR", minimo));
                return;
            }

            System.out.println("\n--- CLIENTES CON FACTURAS SUPERIORES A " + String.format("%.2f EUR", minimo) + " ---");
            String sep = "+------------+----------------------+---------------------------+-----------------+";
            System.out.println(sep);
            System.out.printf("| %-10s | %-20s | %-25s | %15s |%n",
                    "NIF", "Cliente", "Concepto", "Importe Final");
            System.out.println(sep);
            for (Factura f : filtradas) {
                System.out.printf("| %-10s | %-20s | %-25s | %11.2f EUR |%n",
                        f.obtenerNif(),
                        recortar(f.obtenerNombreCliente(), 20),
                        recortar(f.obtenerConcepto(), 25),
                        f.calcularImporteFinal());
            }
            System.out.println(sep);
        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un número válido.");
        }
    }

    private void mostrarListadoFacturas(ArrayList<Factura> lista, String titulo) {
        if (lista.isEmpty()) {
            System.out.println("No hay facturas para mostrar.");
            return;
        }

        System.out.println("\n--- " + titulo + " ---");
        String sep = "+----------------------+------------+------------+--------------------+--------------+-------+-------+-----------------+";
        System.out.println(sep);
        System.out.printf("| %-20s | %-10s | %-10s | %-18s | %12s | %5s | %5s | %15s |%n",
                "Concepto", "Fecha", "NIF", "Cliente", "Base", "Dto.", "IVA", "Total Final");
        System.out.println(sep);

        for (Factura f : lista) {
            System.out.printf("| %-20s | %-10s | %-10s | %-18s | %8.2f EUR | %5.2f | %5.2f | %11.2f EUR |%n",
                    recortar(f.obtenerConcepto(), 20),
                    f.obtenerFecha(),
                    f.obtenerNif(),
                    recortar(f.obtenerNombreCliente(), 18),
                    f.obtenerImporte(),
                    f.obtenerDescuento(),
                    f.obtenerIva(),
                    f.calcularImporteFinal());
        }
        System.out.println(sep);
    }

    private void exportarHtml() {
        if (!controlador.hayFacturas()) {
            System.out.println("No hay facturas cargadas para exportar.");
            return;
        }
        try {
            Path ruta = controlador.exportarAHtml();
            System.out.println("Archivo HTML generado correctamente en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar a HTML: " + e.getMessage());
        }
    }

    private void exportarCsv() {
        if (!controlador.hayFacturas()) {
            System.out.println("No hay facturas cargadas para exportar.");
            return;
        }
        try {
            Path ruta = controlador.exportarACsv();
            System.out.println("Archivo CSV generado correctamente en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar a CSV: " + e.getMessage());
        }
    }

    private String recortar(String texto, int max) {
        if (texto.length() <= max) {
            return texto;
        }
        return texto.substring(0, max - 3) + "...";
    }
}