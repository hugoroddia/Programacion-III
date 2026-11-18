public class Coche {
    private String matricula;
    private String marca;
    private String modelo;
    private double precio;

    public Coche(String matricula, String marca, String modelo, double precio) {
        this.matricula = matricula.toUpperCase();
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("Matrícula: %-10s | Marca: %-12s | Modelo: %-12s | Precio: %10.2f EUR",
                matricula, marca, modelo, precio);
    }
}