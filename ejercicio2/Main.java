package ejercicio2;

public class Main {
    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        Smartphone smartphone = new Smartphone();
        BombillaInteligente bombilla = new BombillaInteligente();

        System.out.println("ESTADO INICIAL:");
        System.out.println(laptop.reportarEstado());
        System.out.println(smartphone.reportarEstado());
        System.out.println(bombilla.reportarEstado());

        // Encender dispositivos
        try {
            laptop.encender();
            smartphone.encender();
            bombilla.encender();
        } catch (Dispositivo.ExcepcionDispositivo e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Intentar cargar mientras están encendidos (debería fallar)
        try {
            laptop.cargar();
        } catch (Recargable.ExcepcionCarga e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }

        // Apagar y cargar
        smartphone.apagar();
        try {
            smartphone.cargar();
        } catch (Recargable.ExcepcionCarga e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Sobrecarga (ejemplo)
        try {
            for (int i = 0; i < 6; i++) {
                smartphone.cargar();
            }
        } catch (Recargable.ExcepcionCarga e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nESTADO FINAL:");
        System.out.println(laptop.reportarEstado());
        System.out.println(smartphone.reportarEstado());
        System.out.println(bombilla.reportarEstado());
    }
}