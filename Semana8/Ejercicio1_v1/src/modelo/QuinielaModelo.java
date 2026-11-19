package modelo;

import java.util.ArrayList;

public class QuinielaModelo {
    private ArrayList<Partido> partidos;

    public QuinielaModelo() {
        this.partidos = new ArrayList<>();
    }

    public void reiniciarPartidos() {
        this.partidos.clear();
    }

    public void agregarPartido(String nombreLocal, String nombreVisitante) {
        Equipo local = new Equipo(nombreLocal);
        Equipo visitante = new Equipo(nombreVisitante);
        this.partidos.add(new Partido(local, visitante));
    }

    public boolean registrarResultado(int indice, int golesLocal, int golesVisitante) {
        if (indice < 0 || indice >= partidos.size()) {
            return false;
        }
        partidos.get(indice).establecerResultado(golesLocal, golesVisitante);
        return true;
    }

    public ArrayList<Partido> obtenerPartidos() {
        return partidos;
    }

    public boolean hayPartidos() {
        return !partidos.isEmpty();
    }
}