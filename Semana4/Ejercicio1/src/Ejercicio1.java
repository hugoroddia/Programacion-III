public class Ejercicio1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: Número de parámetros incorrecto (" + args.length + ").");
            System.err.println("Uso correcto: java Ejercicio1 <numero1> <numero2>");
            return;
        }

        try {
            double num1 = Double.parseDouble(args[0]);
            double num2 = Double.parseDouble(args[1]);
            double suma = num1 + num2;

            System.out.printf("La suma de %.2f + %.2f es: %.2f%n", num1, num2, suma);
        } catch (NumberFormatException e) {
            System.err.println("Error: Ambos argumentos deben ser valores numéricos válidos.");
            System.err.println("Valores recibidos: '" + args[0] + "' y '" + args[1] + "'");
        }
    }
}