public class Person {
    private String nombre = "Valor en declaración";
    private float altura = 0.0f;
    private float peso = 0.0f;

    public Person() {
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