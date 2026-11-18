public class Persona {
    private String dni;
    private String nombre;
    private int edad;
    private float altura;
    private float peso;

    public Persona(String dni, String nombre, int edad, float altura, float peso) {
        this.dni = dni.toUpperCase();
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
    }

    public float calcularIMC() {
        if (altura <= 0 || peso <= 0) {
            return -1.0f;
        }
        return peso / (altura * altura);
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return String.format("DNI: %-10s | Nombre: %-15s | Edad: %3d | Altura: %.2f m | Peso: %.2f kg | IMC: %.2f",
                dni, nombre, edad, altura, peso, calcularIMC());
    }
}