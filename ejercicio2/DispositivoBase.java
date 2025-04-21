package ejercicio2;

public abstract class DispositivoBase implements Dispositivo {
    private boolean estaEncendido = false;

    @Override
    public void encender() throws ExcepcionDispositivo {
        if (estaEncendido) {
            throw new ExcepcionDispositivo("El dispositivo ya está encendido");
        }
        estaEncendido = true;
        System.out.println("Encendido");
    }

    @Override
    public void apagar() {
        if (!estaEncendido) {
            System.out.println("El dispositivo ya está apagado");
            return;
        }
        estaEncendido = false;
        System.out.println("Apagado");
    }

    @Override
    public String reportarEstado() {
        return "Estado: " + (estaEncendido ? "Encendido" : "Apagado") + " | " + estadoEspecifico();
    }

    // Método abstracto para estado específico
    protected abstract String estadoEspecifico();
}