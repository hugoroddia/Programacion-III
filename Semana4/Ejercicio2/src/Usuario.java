public class Usuario {
    private String nombre;
    private float peso;
    private float altura;

    public Usuario(String nombre, float peso, float altura) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
    }

    // Método fábrica estático (Factory Method simple)
    public static Usuario crearDesdeArray(String[] datos) {
        if (datos == null || datos.length != 3) {
            throw new IllegalArgumentException(
                    "Se requieren exactamente 3 argumentos: <nombre> <peso_kg> <altura_m>");
        }

        try {
            String nombre = datos[0];
            float peso = Float.parseFloat(datos[1]);
            float altura = Float.parseFloat(datos[2]);

            if (peso <= 0 || altura <= 0) {
                throw new IllegalArgumentException("El peso y la altura deben ser mayores que cero.");
            }

            return new Usuario(nombre, peso, altura);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El peso y la altura deben ser valores numéricos válidos.");
        }
    }

    public float calcularIMC() {
        return this.peso / (this.altura * this.altura);
    }

    public String getNombre() {
        return nombre;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }
}