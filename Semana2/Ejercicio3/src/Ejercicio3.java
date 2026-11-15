import es.usal.progiii.tools.Esdia;

public class Ejercicio3 {
    public static void main(String[] args) {
        int n;

        do {
            n = Esdia.readInt("Introduce un número entero N mayor que cero: ");
            if (n <= 0) {
                System.err.println("Error: el valor de N debe ser mayor que cero.");
            }
        } while (n <= 0);

        float suma = 0.0f;
        for (int i = 1; i <= n; i++) {
            float numero = Esdia.readFloat("Introduce el número " + i + ": ");
            suma += numero;
        }

        float media = suma / n;
        System.out.printf("La media aritmética es: %.2f%n", media);
    }
}