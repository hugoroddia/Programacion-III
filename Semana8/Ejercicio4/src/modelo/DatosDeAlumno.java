package modelo;

import java.util.Random;
import java.util.Set;

public class DatosDeAlumno {
    private DatosPersonales datosPersonales;
    private Direccion direccion;

    public DatosDeAlumno(DatosPersonales datosPersonales, Direccion direccion) {
        this.datosPersonales = datosPersonales;
        this.direccion = direccion;
    }

    public static DatosDeAlumno crearAleatorio(Random aleatorio, Set<String> nifsUsados) {
        DatosPersonales dp = DatosPersonales.crearAleatorio(aleatorio, nifsUsados);
        Direccion dir = Direccion.crearAleatoria(aleatorio);
        return new DatosDeAlumno(dp, dir);
    }

    public DatosPersonales obtenerDatosPersonales() {
        return datosPersonales;
    }

    public Direccion obtenerDireccion() {
        return direccion;
    }
}