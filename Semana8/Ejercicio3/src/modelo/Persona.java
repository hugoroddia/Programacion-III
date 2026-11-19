package modelo;

public class Persona {
    private String nombre;
    private String telefono;
    private double peso;

    public Persona(String nombre, String telefono, double peso) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.peso = peso;
    }

    public static String obtenerSeparador() {
        return "+----------------------+-----------------+------------+";
    }

    public static String obtenerCabecera() {
        return String.format("| %-20s | %-15s | %10s |", "Nombre", "Teléfono", "Peso (kg)");
    }

    public String obtenerFila() {
        return String.format("| %-20s | %-15s | %10.2f |", nombre, telefono, peso);
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerTelefono() {
        return telefono;
    }

    public double obtenerPeso() {
        return peso;
    }
}