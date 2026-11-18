import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Persona> personas = new ArrayList<>();
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE PERSONAS (CRUD CON ARRAYLIST) ===");
            System.out.println("1. Dar de alta persona (Create)");
            System.out.println("2. Listar todas las personas (Read)");
            System.out.println("3. Buscar persona por DNI (Read)");
            System.out.println("4. Modificar datos de persona (Update)");
            System.out.println("5. Eliminar persona (Delete)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("DNI: ");
                    String dni = sc.nextLine().trim().toUpperCase();
                    if (buscarPorDni(personas, dni) != null) {
                        System.out.println("Error: Ya existe una persona registrada con ese DNI.");
                        break;
                    }
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = Integer.parseInt(sc.nextLine());
                    System.out.print("Altura en metros: ");
                    float altura = Float.parseFloat(sc.nextLine());
                    System.out.print("Peso en kg: ");
                    float peso = Float.parseFloat(sc.nextLine());

                    personas.add(new Persona(dni, nombre, edad, altura, peso));
                    System.out.println("Persona registrada correctamente.");
                    break;

                case 2:
                    if (personas.isEmpty()) {
                        System.out.println("No hay personas registradas.");
                    } else {
                        System.out.println("\n--- LISTADO DE PERSONAS ---");
                        for (Persona p : personas) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Introduce el DNI a buscar: ");
                    Persona pBuscada = buscarPorDni(personas, sc.nextLine().trim());
                    if (pBuscada != null) {
                        System.out.println(pBuscada);
                    } else {
                        System.out.println("No se encontró ninguna persona con ese DNI.");
                    }
                    break;

                case 4:
                    System.out.print("Introduce el DNI de la persona a modificar: ");
                    Persona pMod = buscarPorDni(personas, sc.nextLine().trim());
                    if (pMod != null) {
                        System.out.print("Nuevo nombre (" + pMod.getNombre() + "): ");
                        pMod.setNombre(sc.nextLine());
                        System.out.print("Nueva edad (" + pMod.getEdad() + "): ");
                        pMod.setEdad(Integer.parseInt(sc.nextLine()));
                        System.out.print("Nueva altura (" + pMod.getAltura() + "): ");
                        pMod.setAltura(Float.parseFloat(sc.nextLine()));
                        System.out.print("Nuevo peso (" + pMod.getPeso() + "): ");
                        pMod.setPeso(Float.parseFloat(sc.nextLine()));
                        System.out.println("Datos actualizados correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna persona con ese DNI.");
                    }
                    break;

                case 5:
                    System.out.print("Introduce el DNI de la persona a eliminar: ");
                    Persona pElim = buscarPorDni(personas, sc.nextLine().trim());
                    if (pElim != null) {
                        personas.remove(pElim);
                        System.out.println("Persona eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna persona con ese DNI.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static Persona buscarPorDni(ArrayList<Persona> lista, String dni) {
        for (Persona p : lista) {
            if (p.getDni().equalsIgnoreCase(dni)) {
                return p;
            }
        }
        return null;
    }
}