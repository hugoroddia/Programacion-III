package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonasModelo {
    private ArrayList<Persona> personas;

    public PersonasModelo() {
        this.personas = new ArrayList<>();
    }

    public Path obtenerRutaEscritorio(String nombreFichero) {
        return Paths.get(System.getProperty("user.home"), "Desktop", nombreFichero);
    }

    public int importarDesdeTsvEscritorio(String nombreFichero) {
        Path ruta = obtenerRutaEscritorio(nombreFichero);
        if (!Files.exists(ruta) || Files.isDirectory(ruta)) {
            return -1;
        }

        int cargados = 0;
        try {
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            for (String linea : lineas) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] campos = linea.split("\t");
                if (campos.length < 3) {
                    continue;
                }
                try {
                    String nombre = campos[0].trim();
                    String telefono = campos[1].trim();
                    double peso = Double.parseDouble(campos[2].trim().replace(",", "."));
                    personas.add(new Persona(nombre, telefono, peso));
                    cargados++;
                } catch (NumberFormatException e) {
                    // Ignora posibles cabeceras o filas con peso no numérico
                }
            }
            return cargados;
        } catch (IOException e) {
            return -1;
        }
    }

    public ArrayList<Persona> obtenerPersonas() {
        return personas;
    }

    public boolean estaVacia() {
        return personas.isEmpty();
    }
}   
