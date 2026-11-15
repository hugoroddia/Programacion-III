package src;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce tu año de nacimiento: ");
        int anioNacimiento = teclado.nextInt();

        System.out.print("Introduce el año actual: ");
        int anioActual = teclado.nextInt();

        int edad = anioActual - anioNacimiento;

        System.out.printf("Tienes %d años.%n", edad);

        teclado.close();
    }
}