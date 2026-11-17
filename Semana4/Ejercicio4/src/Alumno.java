public class Alumno {
    private String nombre;
    private float parcial1;
    private float parcial2;
    private float examenFinal;

    public Alumno(String nombre, float parcial1, float parcial2, float examenFinal) {
        this.nombre = nombre;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
        this.examenFinal = examenFinal;
    }

    public float calcularNotaFinal() {
        return (this.parcial1 * 0.10f) + (this.parcial2 * 0.10f) + (this.examenFinal * 0.80f);
    }

    public String getNombre() {
        return nombre;
    }

    public float getParcial1() {
        return parcial1;
    }

    public float getParcial2() {
        return parcial2;
    }

    public float getExamenFinal() {
        return examenFinal;
    }
}