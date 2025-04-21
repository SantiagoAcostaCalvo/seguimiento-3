package ejercicio2;

public class BombillaInteligente extends DispositivoBase {
    private int intensidad = 50; // 0-100

    @Override
    protected String estadoEspecifico() {
        return "Intensidad: " + intensidad + "%";
    }
}