package vista;

import java.nio.file.Path;
import java.util.ArrayList;
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

    public void mostrarMensajeCargaInicial(boolean cargado, String error) {
        System.out.println("=== INICIO DE BIBLIOTECA (MVC + PERSISTENCIA) ===");
        if (error != null) {
            System.out.println("Error al leer libros.bin en el Escritorio: " + error);
        } else if (cargado) {
            System.out.println("Se han cargado " + controlador.obtenerLibros().size()
                    + " libros desde libros.bin en el Escritorio.");
        } else {
            System.out.println("Aviso: No se ha encontrado el fichero libros.bin en el Escritorio. Se inicia una colección vacía.");
        }
    }

    public void mostrarMensajeGuardadoFinal(boolean guardado, String detalle) {
        if (guardado) {
            System.out.println("Estado de la biblioteca guardado correctamente en: " + detalle);
        } else {
            System.out.println("Error al guardar libros.bin en el Escritorio: " + detalle);
        }
    }

    public void mostrarMenuPrincipal() {
        int opcion = -1;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL - BIBLIOTECA ===");
            System.out.println("1. Mostrar el listado de libros en formato tabla");
            System.out.println("2. Añadir nuevo libro al modelo");
            System.out.println("3. Eliminar libro de una lista de libros disponibles");
            System.out.println("4. Modificar libro de una lista de libros disponibles");
            System.out.println("5. Exportar libros en formato delimitado por comas CSV");
            System.out.println("6. Exportar libros en formato JSON (libros.json en el escritorio)");
            System.out.println("7. Exportar libros en formato XML (libros.xml en el escritorio)");
            System.out.println("8. Importar libros de un fichero JSON (libros.json en el escritorio)");
            System.out.println("9. Importar libros de un fichero XML (libros.xml en el escritorio)");
            System.out.println("10. Importar libros de un fichero CSV (libros.csv en el escritorio)");
            System.out.println("11. Salir del programa");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(lector.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número entero entre 1 y 11.");
                continue;
            }

            switch (opcion) {
                case 1:
                    mostrarTablaLibros();
                    break;
                case 2:
                    anadirLibro();
                    break;
                case 3:
                    eliminarLibro();
                    break;
                case 4:
                    modificarLibro();
                    break;
                case 5:
                    exportarCsv();
                    break;
                case 6:
                    exportarJson();
                    break;
                case 7:
                    exportarXml();
                    break;
                case 8:
                    importarJson();
                    break;
                case 9:
                    importarXml();
                    break;
                case 10:
                    importarCsv();
                    break;
                case 11:
                    System.out.println("Guardando datos y cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Elige entre 1 y 11.");
            }
        } while (opcion != 11);

        lector.close();
    }

    private void mostrarTablaLibros() {
        if (controlador.estaVacia()) {
            System.out.println("No hay libros registrados en la biblioteca.");
            return;
        }

        ArrayList<Libro> libros = controlador.obtenerLibros();
        String sep = "+-----+--------------------+------------------------------+------------------------+------+";
        System.out.println("\n" + sep);
        System.out.printf("| %-3s | %-18s | %-28s | %-22s | %4s |%n",
                "Nº", "ISBN", "Título", "Autor", "Año");
        System.out.println(sep);

        for (int i = 0; i < libros.size(); i++) {
            Libro l = libros.get(i);
            System.out.printf("| %3d | %-18s | %-28s | %-22s | %4d |%n",
                    (i + 1),
                    recortar(l.obtenerIsbn(), 18),
                    recortar(l.obtenerTitulo(), 28),
                    recortar(l.obtenerAutor(), 22),
                    l.obtenerAnioPublicacion());
        }
        System.out.println(sep);
    }

    private void anadirLibro() {
        System.out.println("\n--- AÑADIR NUEVO LIBRO ---");
        System.out.print("ISBN: ");
        String isbn = lector.nextLine().trim();
        if (isbn.isEmpty()) {
            System.out.println("Error: El ISBN no puede estar vacío.");
            return;
        }

        System.out.print("Título: ");
        String titulo = lector.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("Error: El título no puede estar vacío.");
            return;
        }

        System.out.print("Autor: ");
        String autor = lector.nextLine().trim();
        if (autor.isEmpty()) {
            System.out.println("Error: El autor no puede estar vacío.");
            return;
        }

        try {
            System.out.print("Año de publicación: ");
            int anio = Integer.parseInt(lector.nextLine().trim());

            Libro nuevo = new Libro(isbn, titulo, autor, anio);
            if (controlador.agregarLibro(nuevo)) {
                System.out.println("Libro añadido correctamente.");
            } else {
                System.out.println("Aviso: Ya existe un libro con ese mismo ISBN. No se ha añadido duplicado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El año de publicación debe ser un número entero.");
        }
    }

    private void eliminarLibro() {
        if (controlador.estaVacia()) {
            System.out.println("No hay libros disponibles para eliminar.");
            return;
        }

        mostrarTablaLibros();
        try {
            System.out.print("Elige el número (Nº) del libro a eliminar (0 para cancelar): ");
            int seleccion = Integer.parseInt(lector.nextLine().trim());
            if (seleccion == 0) {
                System.out.println("Operación cancelada.");
                return;
            }

            Libro eliminado = controlador.eliminarLibroPorIndice(seleccion - 1);
            if (eliminado != null) {
                System.out.println("Libro eliminado: \"" + eliminado.obtenerTitulo() + "\" (ISBN: " + eliminado.obtenerIsbn() + ").");
            } else {
                System.out.println("Error: Número de libro fuera de rango.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes introducir un número válido.");
        }
    }

    private void modificarLibro() {
        if (controlador.estaVacia()) {
            System.out.println("No hay libros disponibles para modificar.");
            return;
        }

        mostrarTablaLibros();
        try {
            System.out.print("Elige el número (Nº) del libro a modificar (0 para cancelar): ");
            int seleccion = Integer.parseInt(lector.nextLine().trim());
            if (seleccion == 0) {
                System.out.println("Operación cancelada.");
                return;
            }

            ArrayList<Libro> libros = controlador.obtenerLibros();
            if (seleccion < 1 || seleccion > libros.size()) {
                System.out.println("Error: Número de libro fuera de rango.");
                return;
            }

            Libro actual = libros.get(seleccion - 1);
            System.out.println("Deja el campo en blanco y pulsa Enter si deseas mantener el valor actual.");

            System.out.print("Nuevo título [" + actual.obtenerTitulo() + "]: ");
            String nuevoTitulo = lector.nextLine().trim();
            if (nuevoTitulo.isEmpty()) {
                nuevoTitulo = actual.obtenerTitulo();
            }

            System.out.print("Nuevo autor [" + actual.obtenerAutor() + "]: ");
            String nuevoAutor = lector.nextLine().trim();
            if (nuevoAutor.isEmpty()) {
                nuevoAutor = actual.obtenerAutor();
            }

            System.out.print("Nuevo año [" + actual.obtenerAnioPublicacion() + "]: ");
            String anioTexto = lector.nextLine().trim();
            int nuevoAnio = anioTexto.isEmpty()
                    ? actual.obtenerAnioPublicacion()
                    : Integer.parseInt(anioTexto);

            if (controlador.modificarLibroPorIndice(seleccion - 1, nuevoTitulo, nuevoAutor, nuevoAnio)) {
                System.out.println("Libro modificado correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El valor numérico introducido no es válido.");
        }
    }

    private void exportarCsv() {
        try {
            Path ruta = controlador.exportarCsv();
            System.out.println("Libros exportados a CSV en: " + ruta);
        } catch (Exception e) {
            System.out.println("Error al exportar a CSV: " + e.getMessage());
        }
    }

    private void exportarJson() {
        try {
            Path ruta = controlador.exportarJson();
            System.out.println("Libros exportados a JSON en: " + ruta);
        } catch (Exception e) {
            System.out.println("Error al exportar a JSON: " + e.getMessage());
        }
    }

    private void exportarXml() {
        try {
            Path ruta = controlador.exportarXml();
            System.out.println("Libros exportados a XML en: " + ruta);
        } catch (Exception e) {
            System.out.println("Error al exportar a XML: " + e.getMessage());
        }
    }

    private void importarJson() {
        try {
            int nuevos = controlador.importarJson();
            System.out.println("Importación JSON completada. Nuevos libros añadidos (sin duplicados): " + nuevos);
        } catch (Exception e) {
            System.out.println("Error al importar desde JSON: " + e.getMessage());
        }
    }

    private void importarXml() {
        try {
            int nuevos = controlador.importarXml();
            System.out.println("Importación XML completada. Nuevos libros añadidos (sin duplicados): " + nuevos);
        } catch (Exception e) {
            System.out.println("Error al importar desde XML: " + e.getMessage());
        }
    }

    private void importarCsv() {
        try {
            int nuevos = controlador.importarCsv();
            System.out.println("Importación CSV completada. Nuevos libros añadidos (sin duplicados): " + nuevos);
        } catch (Exception e) {
            System.out.println("Error al importar desde CSV: " + e.getMessage());
        }
    }

    private String recortar(String texto, int max) {
        if (texto.length() <= max) {
            return texto;
        }
        return texto.substring(0, max - 3) + "...";
    }
}