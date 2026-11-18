import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] cadenas = new String[5];

        System.out.println("Introduce 5 cadenas de texto:");
        for (int i = 0; i < cadenas.length; i++) {
            System.out.print("Cadena " + (i + 1) + ": ");
            cadenas[i] = sc.nextLine();
        }

        Arrays.sort(cadenas);

        System.out.println("\nCadenas ordenadas (Arrays.sort):");
        for (String s : cadenas) {
            System.out.println("- " + s);
        }

        sc.close();
    }
}
