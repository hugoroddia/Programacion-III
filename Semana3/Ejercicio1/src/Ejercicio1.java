import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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

        Persona masAlta = p1;
        Persona masPesada = p1;

        for (int i = 1; i < personas.length; i++) {
            if (personas[i].getAltura() > masAlta.getAltura()) {
                masAlta = personas[i];
            }
            if (personas[i].getPeso() > masPesada.getPeso()) {
                masPesada = personas[i];
            }
        }

        System.out.printf("%nLa persona más alta es %s (%.2f m).%n",
                masAlta.getNombre(), masAlta.getAltura());
        System.out.printf("La persona que más pesa es %s (%.2f kg).%n",
                masPesada.getNombre(), masPesada.getPeso());

        sc.close();
    }
}
