package vista;

import java.util.ArrayList;
import java.util.Scanner;
import controlador.QuinielaControlador;
import modelo.Partido;

public class QuinielaVista {
    private QuinielaControlador controlador;
    private Scanner lector;

    public QuinielaVista() {
        this.lector = new Scanner(System.in);
    }

    public void establecerControlador(QuinielaControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarResultadoCarga(boolean cargadoOk) {
        System.out.println("\n=== CARGA INICIAL DESDE FICHERO ===");
        System.out.println("Ruta buscada: " + controlador.obtenerRutaFicheroEquipos());
        if (cargadoOk) {
            System.out.println("Se han cargado " + controlador.obtenerPartidos().size() + " partidos correctamente.");
        } else {
            System.out.println("Aviso: No se pudieron cargar partidos válidos.");
        }

        ArrayList<String> errores = controlador.obtenerErroresCarga();
        if (!errores.isEmpty()) {
            System.out.println("Incidencias detectadas en el fichero:");
            for (String err : errores) {
                System.out.println("  - " + err);
            }
        }
    }

    public void mostrarMenuPrincipal() {
        String opcion;
        do {
            System.out.println("\n=== QUINIELA MVC (VERSIÓN 2 - FICHERO) ===");
            System.out.println("1. Recargar partidos desde equipos.txt");
            System.out.println("2. Introducir resultados");
            System.out.println("3. Mostrar quiniela");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = lector.nextLine().trim();

            switch (opcion) {
                case "1":
                    boolean ok = controlador.cargarPartidosDesdeFichero();
                    mostrarResultadoCarga(ok);
                    break;
                case "2":
                    solicitarResultados();
                    break;
                case "3":
                    mostrarQuiniela();
                    break;
                case "0":
                    System.out.println("Saliendo de la quiniela...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("0"));

        lector.close();
    }

    private void solicitarResultados() {
        if (!controlador.hayPartidos()) {
            System.out.println("Error: No hay partidos cargados. Revisa equipos.txt y usa la opción 1.");
            return;
        }

        ArrayList<Partido> partidos = controlador.obtenerPartidos();
        System.out.println("\n--- INTRODUCCIÓN DE RESULTADOS ---");
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            try {
                System.out.println("Partido " + (i + 1) + ": "
                        + p.obtenerEquipoLocal().obtenerNombre() + " vs "
                        + p.obtenerEquipoVisitante().obtenerNombre());
                System.out.print("  Goles " + p.obtenerEquipoLocal().obtenerNombre() + ": ");
                int gLocal = Integer.parseInt(lector.nextLine().trim());
                System.out.print("  Goles " + p.obtenerEquipoVisitante().obtenerNombre() + ": ");
                int gVisitante = Integer.parseInt(lector.nextLine().trim());

                if (gLocal < 0 || gVisitante < 0) {
                    System.out.println("Error: Los goles no pueden ser negativos. Repite este partido.");
                    i--;
                    continue;
                }
                controlador.registrarResultado(i, gLocal, gVisitante);
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce números enteros válidos. Repite este partido.");
                i--;
            }
        }
        System.out.println("Resultados guardados correctamente.");
    }

    private void mostrarQuiniela() {
        if (!controlador.hayPartidos()) {
            System.out.println("No hay partidos disponibles para mostrar.");
            return;
        }

        ArrayList<Partido> partidos = controlador.obtenerPartidos();
        String sep = "+-----+----------------------+----------------------+-----------+-------+";
        System.out.println("\n" + sep);
        System.out.printf("| %-3s | %-20s | %-20s | %-9s | %-5s |%n",
                "Nº", "Local", "Visitante", "Marcador", "Signo");
        System.out.println(sep);

        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            String marcador = p.tieneResultadoRegistrado()
                    ? p.obtenerGolesLocal() + " - " + p.obtenerGolesVisitante()
                    : "Pendiente";
            System.out.printf("| %3d | %-20s | %-20s | %-9s |   %-3s |%n",
                    (i + 1),
                    p.obtenerEquipoLocal().obtenerNombre(),
                    p.obtenerEquipoVisitante().obtenerNombre(),
                    marcador,
                    p.obtenerSignoQuiniela());
        }
        System.out.println(sep);
    }
}