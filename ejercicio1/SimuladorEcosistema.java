package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimuladorEcosistema {
    // Clase abstracta Organismo
    static abstract class Organismo {
        private double energia;

        public Organismo(double energiaInicial) {
            this.energia = energiaInicial;
        }

        public boolean estaVivo() {
            return energia > 0;
        }

        public double getEnergia() {
            return energia;
        }

        public void setEnergia(double energia) {
            this.energia = energia;
        }

        public abstract void realizarAccion(Ecosistema ecosistema);
    }

    // Clase Planta
    static class Planta extends Organismo {
        public Planta() {
            super(100); // Energía inicial
        }

        @Override
        public void realizarAccion(Ecosistema ecosistema) {
            setEnergia(getEnergia() + 5); // Genera energía con luz solar
        }
    }

    // Clase Herbívoro
    static class Herbívoro extends Organismo {
        public Herbívoro() {
            super(50); // Energía inicial
        }

        @Override
        public void realizarAccion(Ecosistema ecosistema) {
            List<Planta> plantas = ecosistema.getPlantas();
            if (!plantas.isEmpty()) {
                Planta planta = plantas.get(0); // Simplificación: come la primera planta disponible
                double energiaObtenida = planta.getEnergia() * 0.1;
                setEnergia(getEnergia() + energiaObtenida);
                planta.setEnergia(planta.getEnergia() - energiaObtenida);
            }
        }
    }

    // Clase Carnívoro
    static class Carnívoro extends Organismo {
        public Carnívoro() {
            super(70); // Energía inicial
        }

        @Override
        public void realizarAccion(Ecosistema ecosistema) {
            List<Herbívoro> herbivoros = ecosistema.getHerbivoros();
            if (!herbivoros.isEmpty()) {
                Herbívoro presa = herbivoros.get(0); // Simplificación: caza al primer herbívoro disponible
                double energiaObtenida = presa.getEnergia() * 0.2;
                setEnergia(getEnergia() + energiaObtenida);
                presa.setEnergia(presa.getEnergia() - energiaObtenida);
            }
        }
    }

    // Clase Ecosistema
    static class Ecosistema {
        private List<Organismo> organismos = new ArrayList<>();

        public void agregarOrganismo(Organismo organismo) {
            organismos.add(organismo);
        }

        public List<Planta> getPlantas() {
            return organismos.stream()
                    .filter(o -> o instanceof Planta)
                    .map(o -> (Planta) o)
                    .collect(Collectors.toList());
        }

        public List<Herbívoro> getHerbivoros() {
            return organismos.stream()
                    .filter(o -> o instanceof Herbívoro)
                    .map(o -> (Herbívoro) o)
                    .collect(Collectors.toList());
        }

        public void simularCiclo() {
            List<Organismo> aEliminar = new ArrayList<>();

            // Ejecuta acciones de cada organismo
            for (Organismo organismo : organismos) {
                if (!organismo.estaVivo()) continue;
                organismo.realizarAccion(this);
            }

            // Elimina organismos muertos
            for (Organismo organismo : organismos) {
                if (!organismo.estaVivo()) {
                    aEliminar.add(organismo);
                }
            }
            organismos.removeAll(aEliminar);
        }
    }

    // Programa principal
    public static void main(String[] args) {
        Ecosistema ecosistema = new Ecosistema();
        ecosistema.agregarOrganismo(new Planta());
        ecosistema.agregarOrganismo(new Herbívoro());
        ecosistema.agregarOrganismo(new Carnívoro());

        System.out.println("INICIO DEL ECO-SISTEMA");
        imprimirEstadoEcosistema(ecosistema);

        for (int i = 0; i < 5; i++) {
            System.out.println("\n--- CICLO " + (i + 1) + " ---");
            ecosistema.simularCiclo();
            imprimirEstadoEcosistema(ecosistema);
        }
    }

    private static void imprimirEstadoEcosistema(Ecosistema ecosistema) {
        for (Organismo organismo : ecosistema.organismos) {
            System.out.println(
                    organismo.getClass().getSimpleName() +
                            " - Energía: " + organismo.getEnergia() +
                            " | VIVO: " + organismo.estaVivo()
            );
        }
    }
}
