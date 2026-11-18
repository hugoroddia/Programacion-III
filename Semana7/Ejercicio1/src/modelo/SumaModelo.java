package modelo;

public class SumaModelo {
    private int numero1;
    private int numero2;
    private int resultado;
    private boolean numerosLeidos;
    private boolean sumaCalculada;

    public SumaModelo() {
        this.numerosLeidos = false;
        this.sumaCalculada = false;
    }

    public void establecerNumeros(int numero1, int numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.numerosLeidos = true;
        this.sumaCalculada = false;
    }

    public boolean calcularSuma() {
        if (!numerosLeidos) {
            return false;
        }
        this.resultado = this.numero1 + this.numero2;
        this.sumaCalculada = true;
        return true;
    }

    public boolean estanNumerosLeidos() {
        return numerosLeidos;
    }

    public boolean estaSumaCalculada() {
        return sumaCalculada;
    }

    public int obtenerNumero1() {
        return numero1;
    }

    public int obtenerNumero2() {
        return numero2;
    }

    public int obtenerResultado() {
        return resultado;
    }
}