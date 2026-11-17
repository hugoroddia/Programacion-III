import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el número de alumnos del curso: ");
        int numAlumnos = Integer.parseInt(sc.nextLine());

        if (numAlumnos <= 0) {
            System.err.println("Error: El número de alumnos debe ser mayor que cero.");
            sc.close();
            return;
        }

        Alumno[] alumnos = new Alumno[numAlumnos];

        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("\n--- Alumno " + (i + 1) + " ---");
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Nota Parcial 1 (10%): ");
            float p1 = Float.parseFloat(sc.nextLine());

            System.out.print("Nota Parcial 2 (10%): ");
            float p2 = Float.parseFloat(sc.nextLine());

            System.out.print("Nota Examen Final (80%): ");
            float ef = Float.parseFloat(sc.nextLine());

            alumnos[i] = new Alumno(nombre, p1, p2, ef);
        }

        float sumaP1 = 0.0f;
        float sumaP2 = 0.0f;
        float sumaFinal = 0.0f;
        float sumaNotaCurso = 0.0f;

        String separador = "+------------------+--------------+--------------+------------+------------+";
        System.out.println("\n" + separador);
        System.out.printf("| %-16s | %12s | %12s | %10s | %10s |%n",
                "Alumno", "Parcial 1", "Parcial 2", "Final", "Nota Final");
        System.out.println(separador);

        for (Alumno a : alumnos) {
            float notaFinal = a.calcularNotaFinal();
            sumaP1 += a.getParcial1();
            sumaP2 += a.getParcial2();
            sumaFinal += a.getExamenFinal();
            sumaNotaCurso += notaFinal;

            System.out.printf("| %-16s | %12.2f | %12.2f | %10.2f | %10.2f |%n",
                    a.getNombre(), a.getParcial1(), a.getParcial2(), a.getExamenFinal(), notaFinal);
        }

        System.out.println(separador);
        System.out.printf("| %-16s | %12.2f | %12.2f | %10.2f | %10.2f |%n",
                "MEDIA CLASE",
                sumaP1 / numAlumnos,
                sumaP2 / numAlumnos,
                sumaFinal / numAlumnos,
                sumaNotaCurso / numAlumnos);
        System.out.println(separador);

        sc.close();
    }
}