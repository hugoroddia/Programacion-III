import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Libro> biblioteca = new HashMap<>();
        int opcion;

        do {
            System.out.println("\n=== BIBLIOTECA (CRUD CON HASHMAP) ===");
            System.out.println("1. Añadir libro (Create)");
            System.out.println("2. Buscar libro por ISBN (Read)");
            System.out.println("3. Listar todos los libros (Read)");
            System.out.println("4. Modificar libro (Update)");
            System.out.println("5. Eliminar libro (Delete)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine().trim();
                    if (biblioteca.containsKey(isbn)) {
                        System.out.println("Error: Ya existe un libro con ese ISBN en la biblioteca.");
                        break;
                    }
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Año de publicación: ");
                    int anio = Integer.parseInt(sc.nextLine());

                    biblioteca.put(isbn, new Libro(isbn, titulo, autor, anio));
                    System.out.println("Libro añadido correctamente.");
                    break;

                case 2:
                    System.out.print("Introduce el ISBN del libro a buscar: ");
                    String isbnBuscar = sc.nextLine().trim();
                    Libro libroEncontrado = biblioteca.get(isbnBuscar);
                    if (libroEncontrado != null) {
                        System.out.println(libroEncontrado);
                    } else {
                        System.out.println("No existe ningún libro con el ISBN: " + isbnBuscar);
                    }
                    break;

                case 3:
                    if (biblioteca.isEmpty()) {
                        System.out.println("La biblioteca está vacía.");
                    } else {
                        System.out.println("\n--- CATÁLOGO DE LA BIBLIOTECA ---");
                        for (Libro l : biblioteca.values()) {
                            System.out.println(l);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Introduce el ISBN del libro a modificar: ");
                    String isbnMod = sc.nextLine().trim();
                    Libro libroMod = biblioteca.get(isbnMod);
                    if (libroMod != null) {
                        System.out.print("Nuevo título (" + libroMod.getTitulo() + "): ");
                        libroMod.setTitulo(sc.nextLine());
                        System.out.print("Nuevo autor (" + libroMod.getAutor() + "): ");
                        libroMod.setAutor(sc.nextLine());
                        System.out.print("Nuevo año (" + libroMod.getAnioPublicacion() + "): ");
                        libroMod.setAnioPublicacion(Integer.parseInt(sc.nextLine()));
                        System.out.println("Libro actualizado correctamente.");
                    } else {
                        System.out.println("No existe ningún libro con el ISBN: " + isbnMod);
                    }
                    break;

                case 5:
                    System.out.print("Introduce el ISBN del libro a eliminar: ");
                    String isbnElim = sc.nextLine().trim();
                    if (biblioteca.remove(isbnElim) != null) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("No existe ningún libro con el ISBN: " + isbnElim);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo de la biblioteca...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}