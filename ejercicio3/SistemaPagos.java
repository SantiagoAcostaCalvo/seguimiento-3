package ejercicio3;

public class SistemaPagos {
    public static void main(String[] args) {
        // Tarjeta de Crédito (con reintento)
        TarjetaCredito tarjeta = new TarjetaCredito();
        System.out.println("\nPROCESANDO TARJETA DE CRÉDITO");
        tarjeta.procesar(100, "Cliente1", 2);

        // Transferencia Bancaria (éxito)
        TransferenciaBancaria transferencia = new TransferenciaBancaria();
        System.out.println("\nPROCESANDO TRANSFERENCIA BANCARIA");
        transferencia.procesar(200, "Cliente2", 1);

        // Criptomonedas (fallo definitivo)
        Criptomoneda crypto = new Criptomoneda();
        System.out.println("\nPROCESANDO CRIPTOMONEDAS");
        crypto.procesar(150, "Cliente3", 0);
    }

    // Clase abstracta para todos los métodos de pago
    static abstract class Procesador {
        protected abstract double getComision();

        double calcularTotal(double monto) {
            return monto * (1 + getComision());
        }

        void log(String estado, double monto, String destinatario) {
            System.out.println("Transacción: " + destinatario +
                    " | Monto: " + monto +
                    " | Comisión: " + (monto * getComision()) +
                    " | Estado: " + estado);
        }

        boolean procesar(double monto, String destinatario, int reintentos) {
            for (int i = 0; i <= reintentos; i++) {
                if (realizarPago(monto, destinatario)) {
                    log("Éxito", monto, destinatario);
                    return true;
                } else {
                    if (i == reintentos) {
                        log("Fallo definitivo", monto, destinatario);
                        return false;
                    }
                    System.out.println("Reintento " + (i + 1) + "...");
                }
            }
            return false;
        }

        abstract boolean realizarPago(double monto, String destinatario);
    }

    // Métodos de pago concretos
    static class TarjetaCredito extends Procesador {
        @Override
        protected double getComision() {
            return 0.02; // 2% de comisión
        }

        @Override
        boolean realizarPago(double monto, String destinatario) {
            // Simula fallo al 50%
            return Math.random() > 0.5;
        }
    }

    static class TransferenciaBancaria extends Procesador {
        @Override
        protected double getComision() {
            return 0.01; // 1% de comisión
        }

        @Override
        boolean realizarPago(double monto, String destinatario) {
            // Simula fallo al 30%
            return Math.random() > 0.3;
        }
    }

    static class Criptomoneda extends Procesador {
        @Override
        protected double getComision() {
            return 0.05; // 5% de comisión
        }

        @Override
        boolean realizarPago(double monto, String destinatario) {
            // Simula fallo al 20%
            return Math.random() > 0.2;
        }
    }
}