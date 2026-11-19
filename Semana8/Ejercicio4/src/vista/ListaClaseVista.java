package vista;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import controlador.ListaClaseControlador;
import modelo.DatosDeAlumno;
import modelo.DatosPersonales;
import modelo.Direccion;

public class ListaClaseVista {
    private ListaClaseControlador controlador;
    private Scanner lector;

    public ListaClaseVista() {
        this.lector = new Scanner(System.in);
    }

    public void establecerControlador(ListaClaseControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenuPrincipal() {
        String opcion;
        do {
            System.out.println("\n=== LISTA DE CLASE (MVC) ===");
            System.out.println("1 Volver a crear la lista de alumnos");
            System.out.println("2 Mostrar tabla de alumnos");
            System.out.println("3 Exportar datos personales (CSV)");
            System.out.println("4 Exportar direcciones (CSV)");
            System.out.println("0 Salir");
            System.out.print("Elige una opción: ");
            opcion = lector.nextLine().trim();

            switch (opcion) {
                case "1":
                    controlador.regenerarListaAlumnos();
                    System.out.println("Se ha generado una nueva lista de "
                            + controlador.obtenerAlumnos().size() + " alumnos aleatorios.");
                    break;
                case "2":
                    mostrarTablaAlumnos();
                    break;
                case "3":
                    exportarDatosPersonales();
                    break;
                case "4":
                    exportarDirecciones();
                    break;
               case "0":
                System.out.println("Saliendo de Lista de Clase...");
                break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("0"));

        lector.close();
    }

    private void mostrarTablaAlumnos() {
        String sep = "+-----+------------+--------------+----------------------+------+------------------------+------+------+-------+";
        System.out.println("\n" + sep);
        System.out.printf("| %-3s | %-10s | %-12s | %-20s | %4s | %-22s | %4s | %4s | %-5s |%n",
                "Nº", "NIF", "Nombre", "Apellidos", "Edad", "Calle", "Núm.", "Piso", "Letra");
        System.out.println(sep);

        int num = 1;
        for (DatosDeAlumno alumno : controlador.obtenerAlumnos()) {
            DatosPersonales dp = alumno.obtenerDatosPersonales();
            Direccion dir = alumno.obtenerDireccion();
            System.out.printf("| %3d | %-10s | %-12s | %-20s | %4d | %-22s | %4d | %4d |   %-3s |%n",
                    num++,
                    dp.obtenerNif(),
                    dp.obtenerNombre(),
                    dp.obtenerApellidos(),
                    dp.obtenerEdad(),
                    dir.obtenerCalle(),
                    dir.obtenerNumero(),
                    dir.obtenerPiso(),
                    dir.obtenerLetra());
        }
        System.out.println(sep);
    }

    private void exportarDatosPersonales() {
        try {
            Path ruta = controlador.exportarDatosPersonalesCsv();
            System.out.println("Datos personales exportados correctamente en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar datos personales: " + e.getMessage());
        }
    }

    private void exportarDirecciones() {
        try {
            Path ruta = controlador.exportarDireccionesCsv();
            System.out.println("Direcciones exportadas correctamente en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar direcciones: " + e.getMessage());
        }
    }
}