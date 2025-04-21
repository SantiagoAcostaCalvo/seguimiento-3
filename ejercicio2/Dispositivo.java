package ejercicio2;

public interface Dispositivo {
    void encender() throws ExcepcionDispositivo;
    void apagar();
    String reportarEstado();

    class ExcepcionDispositivo extends Exception {
        public ExcepcionDispositivo(String elDispositivoYaEstáEncendido) {
        }
    }
}