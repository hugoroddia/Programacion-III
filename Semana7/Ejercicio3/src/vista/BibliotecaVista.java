package vista;

import java.util.Scanner;
import controlador.BibliotecaControlador;
import modelo.Libro;

public class BibliotecaVista {
    private BibliotecaControlador controlador;
    private Scanner lector;

    public BibliotecaVista() {
        this.lector = new Scanner(System.in);
    }

    public void establecerControlador(BibliotecaControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenuPrincipal() {
        int opcion = -1;
        do {
            System.out.println("\n=== BIBLIOTECA (CRUD MVC CON HASHMAP) ===");
            System.out.println("1. Añadir libro");
            System.out.println("2. Buscar libro por ISBN");
            System.out.println("3. Listar todos los libros");
            System.out.println("4. Modificar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(lector.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    buscarLibro();
                    break;
                case 3:
                    mostrarLibros();
                    break;
                case 4:
                    modificarLibro();
                    break;
                case 5:
                    eliminarLibro();
                    break;
                case 0:
                    System.out.println("Saliendo de la biblioteca...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        lector.close();
    }

    private void agregarLibro() {
        System.out.print("ISBN: ");
        String isbn = lector.nextLine().trim();
        if (controlador.existeLibroPorIsbn(isbn)) {
            System.out.println("Error: Ya existe un libro con ese ISBN.");
            return;
        }

        try {
            System.out.print("Título: ");
            String titulo = lector.nextLine();
            System.out.print("Autor: ");
            String autor = lector.nextLine();
            System.out.print("Año de publicación: ");
            int anio = Integer.parseInt(lector.nextLine().trim());

            Libro nuevo = new Libro(isbn, titulo, autor, anio);
            if (controlador.agregarLibro(nuevo)) {
                System.out.println("Libro añadido correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El año debe ser un número entero.");
        }
    }

    private void buscarLibro() {
        System.out.print("Introduce el ISBN a buscar: ");
        String isbn = lector.nextLine().trim();
        Libro libro = controlador.buscarLibroPorIsbn(isbn);
        if (libro != null) {
            imprimirLibro(libro);
        } else {
            System.out.println("No se encontró ningún libro con el ISBN: " + isbn);
        }
    }

    private void mostrarLibros() {
        if (controlador.estaVacia()) {
            System.out.println("La biblioteca está vacía.");
            return;
        }
        System.out.println("\n--- CATÁLOGO DE LA BIBLIOTECA ---");
        for (Libro libro : controlador.obtenerLibros()) {
            imprimirLibro(libro);
        }
    }

    private void modificarLibro() {
        System.out.print("Introduce el ISBN del libro a modificar: ");
        String isbn = lector.nextLine().trim();
        Libro actual = controlador.buscarLibroPorIsbn(isbn);
        if (actual == null) {
            System.out.println("No se encontró ningún libro con el ISBN: " + isbn);
            return;
        }

        try {
            System.out.print("Nuevo título (" + actual.obtenerTitulo() + "): ");
            String titulo = lector.nextLine();
            System.out.print("Nuevo autor (" + actual.obtenerAutor() + "): ");
            String autor = lector.nextLine();
            System.out.print("Nuevo año (" + actual.obtenerAnioPublicacion() + "): ");
            int anio = Integer.parseInt(lector.nextLine().trim());

            if (controlador.actualizarLibro(isbn, titulo, autor, anio)) {
                System.out.println("Libro actualizado correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El año debe ser un número entero.");
        }
    }

    private void eliminarLibro() {
        System.out.print("Introduce el ISBN del libro a eliminar: ");
        String isbn = lector.nextLine().trim();
        if (controlador.eliminarLibroPorIsbn(isbn)) {
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún libro con el ISBN: " + isbn);
        }
    }

    private void imprimirLibro(Libro libro) {
        System.out.printf("ISBN: %-15s | Título: %-25s | Autor: %-20s | Año: %d%n",
                libro.obtenerIsbn(), libro.obtenerTitulo(), libro.obtenerAutor(), libro.obtenerAnioPublicacion());
    }
}