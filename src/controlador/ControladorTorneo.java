package controlador;

import modelo.Torneo;
import modelo.Equipo;
import modelo.SorteoInvalidoException;
import java.util.List;

public class ControladorTorneo {
    private Torneo torneo;

    public ControladorTorneo(String nombreTorneo) {
        this.torneo = new Torneo(nombreTorneo);
        this.torneo.inicializarEquiposEcuatorianos();
    }

    public List<Equipo> obtenerTablaPosiciones() {
        return torneo.getTablaPosiciones();
    }

    public void simularJornada() throws SorteoInvalidoException {
        torneo.simularJornadaAleatoria();
    }
}