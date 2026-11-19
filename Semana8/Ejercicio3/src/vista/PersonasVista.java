package vista;

import java.util.Scanner;
import controlador.PersonasControlador;
import modelo.Persona;

public class PersonasVista {
    private PersonasControlador controlador;
    private Scanner lector;

    public PersonasVista() {
        this.lector = new Scanner(System.in);
    }

    public void establecerControlador(PersonasControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMensajeCargaInicial(int cantidadCargada) {
        System.out.println("=== INICIO DE APLICACIÓN ===");
        if (cantidadCargada > 0) {
            System.out.println("Se han cargado " + cantidadCargada + " personas desde datos.txt en el Escritorio.");
        } else if (cantidadCargada == 0) {
            System.out.println("El archivo datos.txt existe en el Escritorio, pero no contiene registros válidos.");
        } else {
            System.out.println("No se encontró el archivo datos.txt en el Escritorio. Se inicia con la lista vacía.");
        }
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== GESTIÓN DE PERSONAS (MVC) ===");
            System.out.println("1. Importar datos desde fichero TSV del Escritorio");
            System.out.println("2. Mostrar tabla de personas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            String opcion = lector.nextLine().trim();

            switch (opcion) {
                case "1":
                    importarFicheroTsv();
                    break;
                case "2":
                    mostrarTablaPersonas();
                    break;
                case "0":
                    salir = confirmarSalida();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        System.out.println("Programa finalizado.");
        lector.close();
    }

    private void importarFicheroTsv() {
        System.out.print("Introduce el nombre del archivo TSV en el Escritorio: ");
        String nombreFichero = lector.nextLine().trim();
        if (nombreFichero.isEmpty()) {
            System.out.println("Error: El nombre del archivo no puede estar vacío.");
            return;
        }

        int importados = controlador.importarDesdeTsvEscritorio(nombreFichero);
        if (importados < 0) {
            System.out.println("Error: No se pudo abrir o leer el fichero \"" + nombreFichero + "\" en el Escritorio.");
        } else if (importados == 0) {
            System.out.println("Aviso: No se encontraron registros válidos en \"" + nombreFichero + "\".");
        } else {
            System.out.println("Se han importado " + importados + " personas correctamente.");
        }
    }

    private void mostrarTablaPersonas() {
        if (controlador.estaVacia()) {
            System.out.println("No hay personas registradas para mostrar.");
            return;
        }

        System.out.println("\n" + Persona.obtenerSeparador());
        System.out.println(Persona.obtenerCabecera());
        System.out.println(Persona.obtenerSeparador());
        for (Persona p : controlador.obtenerPersonas()) {
            System.out.println(p.obtenerFila());
        }
        System.out.println(Persona.obtenerSeparador());
    }

    private boolean confirmarSalida() {
        System.out.print("¿Está seguro de que desea salir? (S/N): ");
        String respuesta = lector.nextLine().trim();
        return respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("SI");
    }
}