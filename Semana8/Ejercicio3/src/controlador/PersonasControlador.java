package controlador;

import java.util.ArrayList;
import modelo.Persona;
import modelo.PersonasModelo;
import vista.PersonasVista;

public class PersonasControlador {
    private PersonasModelo modelo;
    private PersonasVista vista;

    public PersonasControlador(PersonasModelo modelo, PersonasVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        int cargados = modelo.importarDesdeTsvEscritorio("datos.txt");
        vista.mostrarMensajeCargaInicial(cargados);
        vista.mostrarMenuPrincipal();
    }

    public int importarDesdeTsvEscritorio(String nombreFichero) {
        return modelo.importarDesdeTsvEscritorio(nombreFichero);
    }

    public ArrayList<Persona> obtenerPersonas() {
        return modelo.obtenerPersonas();
    }

    public boolean estaVacia() {
        return modelo.estaVacia();
    }
}