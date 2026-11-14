import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el primer número: ");
        int num1 = teclado.nextInt();

        System.out.print("Introduce el segundo número: ");
        int num2 = teclado.nextInt();

        int suma = num1 + num2;
        System.out.println("La suma de los dos números es: " + suma);

        teclado.close();
    }
}
