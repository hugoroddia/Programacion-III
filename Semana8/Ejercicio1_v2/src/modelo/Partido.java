package modelo;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    private boolean resultadoRegistrado;
    private String signoQuiniela;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultadoRegistrado = false;
        this.signoQuiniela = "-";
    }

    public void establecerResultado(int golesLocal, int golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.resultadoRegistrado = true;

        if (golesLocal > golesVisitante) {
            this.signoQuiniela = "1";
        } else if (golesLocal < golesVisitante) {
            this.signoQuiniela = "2";
        } else {
            this.signoQuiniela = "X";
        }
    }

    public Equipo obtenerEquipoLocal() {
        return equipoLocal;
    }

    public Equipo obtenerEquipoVisitante() {
        return equipoVisitante;
    }

    public int obtenerGolesLocal() {
        return golesLocal;
    }

    public int obtenerGolesVisitante() {
        return golesVisitante;
    }

    public boolean tieneResultadoRegistrado() {
        return resultadoRegistrado;
    }

    public String obtenerSignoQuiniela() {
        return signoQuiniela;
    }
}