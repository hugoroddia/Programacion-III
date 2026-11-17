public class Producto {
    public static final float IVA = 0.04f; // IVA prefijado (4%)

    private String nombre;
    private float precioSinIVA;
    private float cantidadKg;

    public Producto(String nombre, float precioSinIVA) {
        this.nombre = nombre;
        this.precioSinIVA = precioSinIVA;
        this.cantidadKg = 0.0f;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecioSinIVA() {
        return precioSinIVA;
    }

    public void setPrecioSinIVA(float precioSinIVA) {
        this.precioSinIVA = precioSinIVA;
    }

    public float getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(float cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public float getPrecioKgConIVA() {
        return this.precioSinIVA * (1.0f + IVA);
    }

    public float getPrecioTotalConIVA() {
        return getPrecioKgConIVA() * this.cantidadKg;
    }
}