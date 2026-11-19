package modelo;

import java.util.Random;
import java.util.Set;

public class DatosPersonales {
    private static final String[] NOMBRES = {
        "Carlos", "Lucía", "Javier", "Elena", "Álvaro",
        "Sofía", "Diego", "Paula", "Hugo", "Marta",
        "Adrián", "Clara", "Pablo", "Nerea", "Daniel"
    };
    private static final String[] APELLIDOS = {
        "García", "Martínez", "López", "Sánchez", "González",
        "Rodríguez", "Fernández", "Pérez", "Gómez", "Ruiz",
        "Díaz", "Hernández", "Jiménez", "Moreno", "Álvarez"
    };
    private static final String LETRAS_NIF = "TRWAGMYFPDXBNJZSQVHLCKE";

    private String nombre;
    private String apellidos;
    private int edad;
    private String nif;

    public DatosPersonales(String nombre, String apellidos, int edad, String nif) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nif = nif;
    }

    public static DatosPersonales crearAleatorio(Random aleatorio, Set<String> nifsUsados) {
        String nombre = NOMBRES[aleatorio.nextInt(NOMBRES.length)];
        String ap1 = APELLIDOS[aleatorio.nextInt(APELLIDOS.length)];
        String ap2 = APELLIDOS[aleatorio.nextInt(APELLIDOS.length)];
        String apellidos = ap1 + " " + ap2;
        int edad = aleatorio.nextInt(13) + 18; // Entre 18 y 30 años

        String nif;
        do {
            int numeroNif = 10000000 + aleatorio.nextInt(90000000);
            char letraControl = LETRAS_NIF.charAt(numeroNif % 23);
            nif = numeroNif + "" + letraControl;
        } while (nifsUsados.contains(nif));

        nifsUsados.add(nif);
        return new DatosPersonales(nombre, apellidos, edad, nif);
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerApellidos() {
        return apellidos;
    }

    public int obtenerEdad() {
        return edad;
    }

    public String obtenerNif() {
        return nif;
    }
}