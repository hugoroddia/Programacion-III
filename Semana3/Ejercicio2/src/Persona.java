public class Persona {
    private String nombre;
    private float altura;
    private float peso;

    // Constructor sin parámetros con valores por defecto
    public Persona() {
        this.nombre = "Desconocido";
        this.altura = 0.0f;
        this.peso = 0.0f;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
