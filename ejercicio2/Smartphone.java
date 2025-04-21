package ejercicio2;

public class Smartphone extends DispositivoBase implements Recargable {
    private double nivelBateria = 100;

    @Override
    protected String estadoEspecifico() {
        return "Batería: " + nivelBateria + "%";
    }

    @Override
    public double getNivelBateria() {
        return nivelBateria;
    }

    @Override
    public void cargar() throws ExcepcionCarga {
        boolean estaEncendido = false;
        if (estaEncendido) {
            throw new ExcepcionCarga("No se puede cargar mientras está encendido");
        }
        if (nivelBateria >= 100) {
            throw new ExcepcionCarga("Batería ya está al 100%");
        }
        nivelBateria = Math.min(nivelBateria + 20, 100);
        System.out.println("Cargando... Nivel actual: " + nivelBateria + "%");
    }
}