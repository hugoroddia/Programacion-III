import java.io.Console;

public class Ejercicio2 {
    public static void main(String[] args) {
        Console consola = System.console();

        if (consola == null) {
            System.err.println("No hay consola disponible. Ejecuta el JAR desde la terminal.");
            return;
        }

        String textoNacimiento = consola.readLine("Introduce tu año de nacimiento: ");
        int anioNacimiento = Integer.parseInt(textoNacimiento);

        String textoActual = consola.readLine("Introduce el año actual: ");
        int anioActual = Integer.parseInt(textoActual);

        int edad = anioActual - anioNacimiento;

        consola.printf("Tienes %d años.%n", edad);
    }
}