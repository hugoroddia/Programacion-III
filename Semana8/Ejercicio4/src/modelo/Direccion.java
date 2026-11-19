package modelo;

import java.util.Random;

public class Direccion {
    private static final String[] CALLES = {
        "Calle Mayor", "Avenida de Portugal", "Paseo de Canalejas",
        "Calle Toro", "Avenida de Mirat", "Calle Zamora",
        "Paseo de la Estación", "Calle Gran Vía", "Calle Compañía",
        "Avenida Federico Anaya"
    };
    private static final String[] LETRAS = {"A", "B", "C", "D", "E", "F"};

    private String calle;
    private int numero;
    private int piso;
    private String letra;

    public Direccion(String calle, int numero, int piso, String letra) {
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.letra = letra;
    }

    public static Direccion crearAleatoria(Random aleatorio) {
        String calle = CALLES[aleatorio.nextInt(CALLES.length)];
        int numero = aleatorio.nextInt(120) + 1;
        int piso = aleatorio.nextInt(10) + 1;
        String letra = LETRAS[aleatorio.nextInt(LETRAS.length)];
        return new Direccion(calle, numero, piso, letra);
    }

    public String obtenerCalle() {
        return calle;
    }

    public int obtenerNumero() {
        return numero;
    }

    public int obtenerPiso() {
        return piso;
    }

    public String obtenerLetra() {
        return letra;
    }
}