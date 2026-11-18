import java.util.Scanner;

public class Ejercicio1Arrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int CAPACIDAD_MAX = 10;
        Coche[] stock = new Coche[CAPACIDAD_MAX];
        int numCoches = 0;
        int opcion;

        do {
            System.out.println("\n=== CONCESIONARIO (VERSIÓN ARRAYS) ===");
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
                    if (numCoches >= CAPACIDAD_MAX) {
                        System.out.println("Error: El concesionario está lleno (máx. " + CAPACIDAD_MAX + ").");
                        break;
                    }
                    System.out.print("Matrícula: ");
                    String mat = sc.nextLine().trim().toUpperCase();
                    if (buscarIndice(stock, numCoches, mat) != -1) {
                        System.out.println("Error: Ya existe un coche con esa matrícula.");
                        break;
                    }
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Precio (EUR): ");
                    double precio = Double.parseDouble(sc.nextLine());

                    stock[numCoches] = new Coche(mat, marca, modelo, precio);
                    numCoches++;
                    System.out.println("Coche añadido correctamente.");
                    break;

                case 2:
                    if (numCoches == 0) {
                        System.out.println("No hay coches en el stock.");
                    } else {
                        System.out.println("\n--- STOCK DISPONIBLE (" + numCoches + "/" + CAPACIDAD_MAX + ") ---");
                        for (int i = 0; i < numCoches; i++) {
                            System.out.println(stock[i]);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Introduce la matrícula a buscar: ");
                    int idxBuscar = buscarIndice(stock, numCoches, sc.nextLine().trim());
                    if (idxBuscar != -1) {
                        System.out.println(stock[idxBuscar]);
                    } else {
                        System.out.println("No se encontró ningún coche con esa matrícula.");
                    }
                    break;

                case 4:
                    System.out.print("Introduce la matrícula del coche a modificar: ");
                    int idxMod = buscarIndice(stock, numCoches, sc.nextLine().trim());
                    if (idxMod != -1) {
                        System.out.print("Nueva marca (" + stock[idxMod].getMarca() + "): ");
                        stock[idxMod].setMarca(sc.nextLine());
                        System.out.print("Nuevo modelo (" + stock[idxMod].getModelo() + "): ");
                        stock[idxMod].setModelo(sc.nextLine());
                        System.out.print("Nuevo precio (" + stock[idxMod].getPrecio() + "): ");
                        stock[idxMod].setPrecio(Double.parseDouble(sc.nextLine()));
                        System.out.println("Coche actualizado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún coche con esa matrícula.");
                    }
                    break;

                case 5:
                    System.out.print("Introduce la matrícula del coche a eliminar: ");
                    int idxElim = buscarIndice(stock, numCoches, sc.nextLine().trim());
                    if (idxElim != -1) {
                        for (int i = idxElim; i < numCoches - 1; i++) {
                            stock[i] = stock[i + 1];
                        }
                        stock[numCoches - 1] = null;
                        numCoches--;
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

    private static int buscarIndice(Coche[] array, int cantidad, String matricula) {
        for (int i = 0; i < cantidad; i++) {
            if (array[i].getMatricula().equalsIgnoreCase(matricula)) {
                return i;
            }
        }
        return -1;
    }
}