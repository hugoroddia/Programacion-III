package modelo;

public class Factura {
    private String concepto;
    private double descuento;
    private String fecha;
    private double importe;
    private String nif;
    private String nombreCliente;
    private String direccion;
    private double iva;

    public Factura(String concepto, double descuento, String fecha, double importe,
                   String nif, String nombreCliente, String direccion, double iva) {
        this.concepto = concepto;
        this.descuento = descuento;
        this.fecha = fecha;
        this.importe = importe;
        this.nif = nif;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.iva = iva;
    }

    public double calcularImporteFinal() {
        return importe * (1.0 - descuento) * (1.0 + iva);
    }

    public String obtenerConcepto() {
        return concepto;
    }

    public double obtenerDescuento() {
        return descuento;
    }

    public String obtenerFecha() {
        return fecha;
    }

    public double obtenerImporte() {
        return importe;
    }

    public String obtenerNif() {
        return nif;
    }

    public String obtenerNombreCliente() {
        return nombreCliente;
    }

    public String obtenerDireccion() {
        return direccion;
    }

    public double obtenerIva() {
        return iva;
    }
}