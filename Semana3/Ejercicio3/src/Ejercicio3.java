import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Persona sinDatos = new Persona();
        if (sinDatos.calcularIMC() < 0) {
            System.out.println("Aviso: No se puede calcular el IMC de '" + sinDatos.getNombre()
                    + "' porque altura o peso no tienen valores válidos.\n");
        }

        Persona p1 = new Persona();
        Persona p2 = new Persona();
        Persona p3 = new Persona();
        Persona[] personas = { p1, p2, p3 };

        for (int i = 0; i < personas.length; i++) {
            System.out.println("--- Datos de la persona " + (i + 1) + " ---");
            System.out.print("Nombre: ");
            personas[i].setNombre(sc.nextLine());

            System.out.print("Altura en metros: ");
            personas[i].setAltura(Float.parseFloat(sc.nextLine()));

            System.out.print("Peso en kg: ");
            personas[i].setPeso(Float.parseFloat(sc.nextLine()));
        }

        System.out.println("\n--- Resultados ---");
        Persona masAlta = p1;
        Persona masPesada = p1;

        for (Persona p : personas) {
            float imc = p.calcularIMC();
            if (imc < 0) {
                System.out.printf("%s -> Datos no válidos para calcular IMC.%n", p.getNombre());
            } else {
                System.out.printf("%s -> IMC: %.2f%n", p.getNombre(), imc);
            }

            if (p.getAltura() > masAlta.getAltura()) {
                masAlta = p;
            }
            if (p.getPeso() > masPesada.getPeso()) {
                masPesada = p;
            }
        }

        System.out.printf("%nLa persona más alta es %s (%.2f m).%n",
                masAlta.getNombre(), masAlta.getAltura());
        System.out.printf("La persona que más pesa es %s (%.2f kg).%n",
                masPesada.getNombre(), masPesada.getPeso());

        sc.close();
    }
}