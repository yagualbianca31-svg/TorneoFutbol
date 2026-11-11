package modelo;

public class Equipo {
    private String nombre;
    private String ciudad;
    private int puntos;
    private int golesFavor;
    private int golesContra;
    private int partidosJugados;

    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.puntos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.partidosJugados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void sumarGolesFavor(int goles) {
        this.golesFavor += goles;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void sumarGolesContra(int goles) {
        this.golesContra += goles;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void incrementarPartidosJugados() {
        this.partidosJugados++;
    }

    public int getDiferenciaGoles() {
        return this.golesFavor - this.golesContra;
    }

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ") - Pts: " + puntos + " | GF: " + golesFavor + " | GC: " + golesContra + " | Dif: " + getDiferenciaGoles();
    }
}