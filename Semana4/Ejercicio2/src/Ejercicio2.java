public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            Usuario usuario = Usuario.crearDesdeArray(args);

            System.out.println("+-----------------+------------+------------+----------+");
            System.out.printf("| %-15s | %10s | %10s | %8s |%n", "Nombre", "Peso (kg)", "Altura (m)", "IMC");
            System.out.println("+-----------------+------------+------------+----------+");
            System.out.printf("| %-15s | %10.2f | %10.2f | %8.2f |%n",
                    usuario.getNombre(),
                    usuario.getPeso(),
                    usuario.getAltura(),
                    usuario.calcularIMC());
            System.out.println("+-----------------+------------+------------+----------+");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Ejemplo de uso: java Ejercicio2 Carlos 74.5 1.78");
        }
    }
}