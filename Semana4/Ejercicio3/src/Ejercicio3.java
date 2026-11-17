import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Producto[] productos = new Producto[2];
        String[] nombres = { "Manzanas", "Peras" };

        System.out.println("=== APERTURA DE JORNADA: PRECIOS DEL DÍA (SIN IVA) ===");
        for (int i = 0; i < productos.length; i++) {
            System.out.print("Introduce el precio/kg sin IVA de " + nombres[i] + " (€): ");
            float precioSinIva = Float.parseFloat(sc.nextLine());
            productos[i] = new Producto(nombres[i], precioSinIva);
        }

        int numCliente = 1;
        String opcion;

        do {
            System.out.println("\n=== COMPRA DEL CLIENTE " + numCliente + " ===");
            for (Producto p : productos) {
                System.out.print("Cantidad de " + p.getNombre() + " (kg): ");
                p.setCantidadKg(Float.parseFloat(sc.nextLine()));
            }

            float totalFactura = 0.0f;
            String separador = "+------------+----------+-------------------------+------------+";

            System.out.println("\n" + separador);
            System.out.println(String.format("| %-10s | %-47d |", "Cliente", numCliente));
            System.out.println(separador);

            for (Producto p : productos) {
                float subtotal = p.getPrecioTotalConIVA();
                totalFactura += subtotal;

                String colKg = String.format("%.2f kg", p.getCantidadKg());
                String colPrecioKg = String.format("precio Kg con IVA %.2f", p.getPrecioKgConIVA());
                String colTotal = String.format("%.2f€", subtotal);

                System.out.println(String.format("| %-10s | %8s | %-23s | %10s |",
                        p.getNombre(), colKg, colPrecioKg, colTotal));
            }

            System.out.println(separador);
            System.out.println(String.format("| %-47s | %10s |",
                    "Total con IVA", String.format("%.2f€", totalFactura)));
            System.out.println(separador);

            numCliente++;

            System.out.print("\n¿Desea atender a otro cliente? (s/n): ");
            opcion = sc.nextLine().trim();
        } while (opcion.equalsIgnoreCase("s"));

        System.out.println("Jornada finalizada.");
        sc.close();
    }
}