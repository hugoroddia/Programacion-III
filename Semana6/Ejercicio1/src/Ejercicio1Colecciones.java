import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1Colecciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Coche> stock = new ArrayList<>();
        int opcion;

        do {
            System.out.println("\n=== CONCESIONARIO (VERSIÓN ARRAYLIST) ===");
            System.out.println("1. Añadir coche (Create)");
            System.out.println("2. Listar todos los coches (Read)");
            System.out.println("3. Buscar coche por matrícula (Read)");
            System.out.println("4. Modificar coche (Update)");
            System.out.println("5. Eliminar coche (Delete)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Matrícula: ");
                    String mat = sc.nextLine().trim().toUpperCase();
                    if (buscarPorMatricula(stock, mat) != null) {
                        System.out.println("Error: Ya existe un coche con esa matrícula.");
                        break;
                    }
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Precio (EUR): ");
                    double precio = Double.parseDouble(sc.nextLine());

                    stock.add(new Coche(mat, marca, modelo, precio));
                    System.out.println("Coche añadido correctamente.");
                    break;

                case 2:
                    if (stock.isEmpty()) {
                        System.out.println("No hay coches en el stock.");
                    } else {
                        System.out.println("\n--- STOCK DISPONIBLE ---");
                        for (Coche c : stock) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Introduce la matrícula a buscar: ");
                    Coche encontrado = buscarPorMatricula(stock, sc.nextLine().trim());
                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("No se encontró ningún coche con esa matrícula.");
                    }
                    break;

                case 4:
                    System.out.print("Introduce la matrícula del coche a modificar: ");
                    Coche aModificar = buscarPorMatricula(stock, sc.nextLine().trim());
                    if (aModificar != null) {
                        System.out.print("Nueva marca (" + aModificar.getMarca() + "): ");
                        aModificar.setMarca(sc.nextLine());
                        System.out.print("Nuevo modelo (" + aModificar.getModelo() + "): ");
                        aModificar.setModelo(sc.nextLine());
                        System.out.print("Nuevo precio (" + aModificar.getPrecio() + "): ");
                        aModificar.setPrecio(Double.parseDouble(sc.nextLine()));
                        System.out.println("Coche actualizado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún coche con esa matrícula.");
                    }
                    break;

                case 5:
                    System.out.print("Introduce la matrícula del coche a eliminar: ");
                    Coche aEliminar = buscarPorMatricula(stock, sc.nextLine().trim());
                    if (aEliminar != null) {
                        stock.remove(aEliminar);
                        System.out.println("Coche eliminado del stock.");
                    } else {
                        System.out.println("No se encontró ningún coche con esa matrícula.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static Coche buscarPorMatricula(ArrayList<Coche> lista, String matricula) {
        for (Coche c : lista) {
            if (c.getMatricula().equalsIgnoreCase(matricula)) {
                return c;
            }
        }
        return null;
    }
}