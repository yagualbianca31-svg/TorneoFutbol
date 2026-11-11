package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Torneo implements java.io.Serializable {
    private String nombre;
    private List<Equipo> equipos;

    public Torneo(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarEquipo(Equipo equipo) throws NumeroEquiposInvalidoException {
        if (equipos.size() >= 16) {
            throw new NumeroEquiposInvalidoException("No se pueden agregar más de 16 equipos al torneo.");
        }
        equipos.add(equipo);
    }

    public void inicializarEquiposEcuatorianos() {
        equipos.clear();
        equipos.add(new Equipo("Barcelona SC", "Guayaquil"));
        equipos.add(new Equipo("Emelec", "Guayaquil"));
        equipos.add(new Equipo("Liga de Quito", "Quito"));
        equipos.add(new Equipo("Independiente del Valle", "Sangolquí"));
        equipos.add(new Equipo("Universidad Católica", "Quito"));
        equipos.add(new Equipo("Deportivo Cuenca", "Cuenca"));
        equipos.add(new Equipo("Aucas", "Quito"));
        equipos.add(new Equipo("Macará", "Ambato"));
        equipos.add(new Equipo("Técnico Universitario", "Ambato"));
        equipos.add(new Equipo("El Nacional", "Quito"));
        equipos.add(new Equipo("Delfín SC", "Manta"));
        equipos.add(new Equipo("Mushuc Runa", "Ambato"));
        equipos.add(new Equipo("Orense SC", "Machala"));
        equipos.add(new Equipo("Imbabura SC", "Ibarra"));
        equipos.add(new Equipo("Cumbayá FC", "Quito"));
        equipos.add(new Equipo("Libertad FC", "Loja"));
    }

    public void simularJornadaAleatoria() throws SorteoInvalidoException {
        if (equipos.size() != 16) {
            throw new SorteoInvalidoException("El torneo debe tener exactamente 16 equipos para simular partidos.");
        }

        // Simulación sencilla de partidos entre los equipos de la lista
        for (int i = 0; i < equipos.size(); i += 2) {
            Equipo local = equipos.get(i);
            Equipo visitante = equipos.get(i + 1);

            int golesLocal = (int) (Math.random() * 4); // entre 0 y 3 goles
            int golesVisitante = (int) (Math.random() * 4);

            local.incrementarPartidosJugados();
            visitante.incrementarPartidosJugados();

            local.sumarGolesFavor(golesLocal);
            local.sumarGolesContra(golesVisitante);

            visitante.sumarGolesFavor(golesVisitante);
            visitante.sumarGolesContra(golesLocal);

            if (golesLocal > golesVisitante) {
                local.sumarPuntos(3);
            } else if (golesLocal < golesVisitante) {
                visitante.sumarPuntos(3);
            } else {
                local.sumarPuntos(1);
                visitante.sumarPuntos(1);
            }
        }
        
        // Mezclar un poco la lista para la siguiente simulación
        Collections.shuffle(equipos);
    }

    public List<Equipo> getTablaPosiciones() {
        // Ordenar por puntos (descendente), luego por diferencia de goles
        equipos.sort((e1, e2) -> {
            int compPuntos = Integer.compare(e2.getPuntos(), e1.getPuntos());
            if (compPuntos != 0) {
                return compPuntos;
            }
            return Integer.compare(e2.getDiferenciaGoles(), e1.getDiferenciaGoles());
        });
        return equipos;
    }
    public static void guardarTorneo(Torneo torneo, String archivo) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(archivo))) {
            oos.writeObject(torneo);
            System.out.println("¡Torneo guardado con éxito!");
        } catch (java.io.IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static Torneo cargarTorneo(String archivo) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(archivo))) {
            Torneo t = (Torneo) ois.readObject();
            System.out.println("¡Torneo cargado con éxito!");
            return t;
        } catch (java.io.IOException | ClassNotFoundException e) {
            System.out.println("No hay archivo previo.");
            return null;
        }
    }
} 