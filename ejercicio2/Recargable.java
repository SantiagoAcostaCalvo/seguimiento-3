package ejercicio2;

public interface Recargable {
    double getNivelBateria();
    void cargar() throws ExcepcionCarga;

    class ExcepcionCarga extends Exception {
        public ExcepcionCarga(String s) {
        }
    }
}