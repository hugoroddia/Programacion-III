package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuinielaModelo {
    private ArrayList<Partido> partidos;
    private ArrayList<String> erroresCarga;

    public QuinielaModelo() {
        this.partidos = new ArrayList<>();
        this.erroresCarga = new ArrayList<>();
    }

    public Path obtenerRutaFicheroEquipos() {
        return Paths.get(System.getProperty("user.home"), "Desktop", "datos", "equipos.txt");
    }

    public boolean cargarPartidosDesdeFichero() {
        partidos.clear();
        erroresCarga.clear();
        Path ruta = obtenerRutaFicheroEquipos();

        if (!Files.exists(ruta)) {
            erroresCarga.add("No se encontró el fichero en: " + ruta.toString());
            return false;
        }

        try {
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            for (int i = 0; i < lineas.size(); i++) {
                String linea = lineas.get(i).trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] equipos = linea.split("-");
                if (equipos.length != 2 || equipos[0].trim().isEmpty() || equipos[1].trim().isEmpty()) {
                    erroresCarga.add("Línea " + (i + 1) + " con formato incorrecto: \"" + linea + "\"");
                    continue;
                }

                partidos.add(new Partido(
                        new Equipo(equipos[0].trim()),
                        new Equipo(equipos[1].trim())
                ));
            }
            return !partidos.isEmpty();
        } catch (IOException e) {
            erroresCarga.add("Error de lectura del fichero: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarResultado(int indice, int golesLocal, int golesVisitante) {
        if (indice < 0 || indice >= partidos.size()) {
            return false;
        }
        partidos.get(indice).establecerResultado(golesLocal, golesVisitante);
        return true;
    }

    public ArrayList<Partido> obtenerPartidos() {
        return partidos;
    }

    public ArrayList<String> obtenerErroresCarga() {
        return erroresCarga;
    }

    public boolean hayPartidos() {
        return !partidos.isEmpty();
    }
}