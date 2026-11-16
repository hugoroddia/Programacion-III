public class Ejercicio4 {
    public static void main(String[] args) {
        Person p1 = new Person(); 

        System.out.printf("Valores finales tras el constructor -> Nombre: %s, Altura: %.2f m, Peso: %.2f kg%n",
                p1.getNombre(), p1.getAltura(), p1.getPeso());
    }
}