package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class ListaClaseModelo {
    public static final int TOTAL_ALUMNOS = 20;

    private ArrayList<DatosDeAlumno> alumnos;
    private HashSet<String> nifsUsados;
    private Random aleatorio;

    public ListaClaseModelo() {
        this.alumnos = new ArrayList<>();
        this.nifsUsados = new HashSet<>();
        this.aleatorio = new Random();
        generarListaAlumnos();
    }

    public void generarListaAlumnos() {
        alumnos.clear();
        nifsUsados.clear();
        for (int i = 0; i < TOTAL_ALUMNOS; i++) {
            alumnos.add(DatosDeAlumno.crearAleatorio(aleatorio, nifsUsados));
        }
    }

    public Path obtenerCarpetaSalida() {
        return Paths.get(System.getProperty("user.home"), "Desktop", "datos");
    }

    public Path exportarDatosPersonalesCsv() throws IOException {
        Path carpeta = obtenerCarpetaSalida();
        Files.createDirectories(carpeta);
        Path rutaCsv = carpeta.resolve("datos_personales.csv");

        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("NIF,Nombre,Apellidos,Edad");
        for (DatosDeAlumno alumno : alumnos) {
            DatosPersonales dp = alumno.obtenerDatosPersonales();
            lineas.add(String.join(",",
                    dp.obtenerNif(),
                    dp.obtenerNombre(),
                    dp.obtenerApellidos(),
                    String.valueOf(dp.obtenerEdad())));
        }

        Files.write(rutaCsv, lineas, StandardCharsets.UTF_8);
        return rutaCsv;
    }

    public Path exportarDireccionesCsv() throws IOException {
        Path carpeta = obtenerCarpetaSalida();
        Files.createDirectories(carpeta);
        Path rutaCsv = carpeta.resolve("direcciones.csv");

        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Calle,Numero,Piso,Letra");
        for (DatosDeAlumno alumno : alumnos) {
            Direccion dir = alumno.obtenerDireccion();
            lineas.add(String.join(",",
                    dir.obtenerCalle(),
                    String.valueOf(dir.obtenerNumero()),
                    String.valueOf(dir.obtenerPiso()),
                    dir.obtenerLetra()));
        }

        Files.write(rutaCsv, lineas, StandardCharsets.UTF_8);
        return rutaCsv;
    }

    public ArrayList<DatosDeAlumno> obtenerAlumnos() {
        return alumnos;
    }
}