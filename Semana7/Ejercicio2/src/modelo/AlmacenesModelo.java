package modelo;

public class AlmacenesModelo {
    public static final int NUM_ALMACENES = 5;
    public static final int NUM_PRODUCTOS = 2;

    private int[][] ventas;
    private double[] precios;
    private double[] ingresosPorAlmacen;
    private double ingresosTotales;

    private boolean ventasLeidas;
    private boolean preciosLeidos;
    private boolean ingresosCalculados;

    public AlmacenesModelo() {
        this.ventas = new int[NUM_ALMACENES][NUM_PRODUCTOS];
        this.precios = new double[NUM_PRODUCTOS];
        this.ingresosPorAlmacen = new double[NUM_ALMACENES];
        this.ventasLeidas = false;
        this.preciosLeidos = false;
        this.ingresosCalculados = false;
    }

    public void establecerVentas(int[][] nuevasVentas) {
        for (int i = 0; i < NUM_ALMACENES; i++) {
            for (int j = 0; j < NUM_PRODUCTOS; j++) {
                this.ventas[i][j] = nuevasVentas[i][j];
            }
        }
        this.ventasLeidas = true;
        this.ingresosCalculados = false;
    }

    public void establecerPrecios(double[] nuevosPrecios) {
        for (int j = 0; j < NUM_PRODUCTOS; j++) {
            this.precios[j] = nuevosPrecios[j];
        }
        this.preciosLeidos = true;
        this.ingresosCalculados = false;
    }

    public boolean calcularIngresosTotales() {
        if (!ventasLeidas || !preciosLeidos) {
            return false;
        }

        double sumaTotal = 0.0;
        for (int i = 0; i < NUM_ALMACENES; i++) {
            double ingresoAlmacen = 0.0;
            for (int j = 0; j < NUM_PRODUCTOS; j++) {
                ingresoAlmacen += ventas[i][j] * precios[j];
            }
            ingresosPorAlmacen[i] = ingresoAlmacen;
            sumaTotal += ingresoAlmacen;
        }

        this.ingresosTotales = sumaTotal;
        this.ingresosCalculados = true;
        return true;
    }

    public boolean estanVentasLeidas() {
        return ventasLeidas;
    }

    public boolean estanPreciosLeidos() {
        return preciosLeidos;
    }

    public boolean estanIngresosCalculados() {
        return ingresosCalculados;
    }

    public int[][] obtenerVentas() {
        return ventas;
    }

    public double[] obtenerPrecios() {
        return precios;
    }

    public double[] obtenerIngresosPorAlmacen() {
        return ingresosPorAlmacen;
    }

    public double obtenerIngresosTotales() {
        return ingresosTotales;
    }
}