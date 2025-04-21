package ejercicio4;
import java.util.List;
import java.util.ArrayList;
public class SistemaGestionAcademica {
    public static void main(String[] args) {
        // Crear participantes
        Estudiante estudiante = new Estudiante("E1", "Ana");
        estudiante.agregarCalificacion(4.5);
        estudiante.agregarCalificacion(3.8);

        Profesor profesor = new Profesor("P1", "Juan", 3000, 180);

        Administrativo administrativo = new Administrativo("A1", "Luis", 2500, "RRHH", 4.5);

        // Mostrar desempeño
        System.out.println("ESTUDIANTE: " + estudiante);
        System.out.println("Desempeño: " + estudiante.evaluarDesempeno());

        System.out.println("\nPROFESOR: " + profesor);
        System.out.println("Desempeño: " + profesor.evaluarDesempeno());

        System.out.println("\nADMINISTRATIVO: " + administrativo);
        System.out.println("Desempeño: " + administrativo.evaluarDesempeno());
    }

    // Clase base abstracta para todos los participantes
    static abstract class Participante {
        private String id;
        private String nombre;

        public Participante(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        // Método abstracto para desempeño
        public abstract double evaluarDesempeno();

        @Override
        public String toString() {
            return "ID: " + id + " | Nombre: " + nombre;
        }
    }

    // Estudiante
    static class Estudiante extends Participante {
        private List<Double> calificaciones;

        public Estudiante(String id, String nombre) {
            super(id, nombre);
            this.calificaciones = new ArrayList<>();
        }

        public void agregarCalificacion(double calificacion) {
            if (calificacion < 0) {
                throw new IllegalArgumentException("¡Calificación negativa no permitida!");
            }
            calificaciones.add(calificacion);
        }

        public double getPromedio() {
            if (calificaciones.isEmpty()) return 0;
            return calificaciones.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        }

        @Override
        public double evaluarDesempeno() {
            return getPromedio();
        }

        @Override
        public String toString() {
            return super.toString() + " | Promedio: " + getPromedio();
        }
    }

    // Profesor
    static class Profesor extends Participante {
        private double salario;
        private double horasTrabajadas;

        public Profesor(String id, String nombre, double salario, double horasTrabajadas) {
            super(id, nombre);
            if (salario < 0) throw new IllegalArgumentException("¡Salario negativo!");
            if (horasTrabajadas < 0) throw new IllegalArgumentException("¡Horas trabajadas no válidas!");
            this.salario = salario;
            this.horasTrabajadas = horasTrabajadas;
        }

        @Override
        public double evaluarDesempeno() {
            return horasTrabajadas; // Ejemplo: horas como desempeño
        }

        @Override
        public String toString() {
            return super.toString() + " | Salario: " + salario + " | Horas: " + horasTrabajadas;
        }
    }

    // Administrativo
    static class Administrativo extends Participante {
        private double salario;
        private String departamento;
        private double eficiencia;

        public Administrativo(String id, String nombre, double salario, String departamento, double eficiencia) {
            super(id, nombre);
            if (salario < 0) throw new IllegalArgumentException("¡Salario negativo!");
            if (eficiencia < 0 || eficiencia > 5) {
                throw new IllegalArgumentException("¡Eficiencia debe estar entre 0 y 5!");
            }
            this.salario = salario;
            this.departamento = departamento;
            this.eficiencia = eficiencia;
        }

        @Override
        public double evaluarDesempeno() {
            return eficiencia; // Ejemplo: eficiencia como desempeño
        }

        @Override
        public String toString() {
            return super.toString() + " | Salario: " + salario + " | Departamento: " + departamento + " | Eficiencia: " + eficiencia;
        }
    }
}