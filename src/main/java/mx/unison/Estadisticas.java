package mx.unison;

import java.util.ArrayList;

public class Estadisticas {
    public static double media(ArrayList<Double> datos) {
        validarDatos(datos);
        double suma = 0;
        for (double valor : datos) {
            suma += valor;
        }
        return suma / datos.size();
    }

    public static double varianza(ArrayList<Double> datos) {
        double media = media(datos);
        double suma = 0;
        for (double valor : datos) {
            suma += Math.pow(valor - media, 2);
        }
        return suma / datos.size();
    }

    public static double desviacionEstandar(ArrayList<Double> datos) {
        return Math.sqrt(varianza(datos));
    }

    public static double maximo(ArrayList<Double> datos) {
        validarDatos(datos);
        double maximo = datos.get(0);
        for (double valor : datos) {
            if (valor > maximo) {
                maximo = valor;
            }
        }
        return maximo;
    }

    public static double minimo(ArrayList<Double> datos) {
        validarDatos(datos);
        double minimo = datos.get(0);
        for (double valor : datos) {
            if (valor < minimo) {
                minimo = valor;
            }
        }
        return minimo;
    }

    public static double correlacion(ArrayList<Double> x, ArrayList<Double> y) {
        validarDatos(x);
        validarDatos(y);
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Las variables deben tener la misma cantidad de valores.");
        }

        double mediaX = media(x);
        double mediaY = media(y);
        double sumaProducto = 0;
        double sumaX = 0;
        double sumaY = 0;
        for (int i = 0; i < x.size(); i++) {
            double diferenciaX = x.get(i) - mediaX;
            double diferenciaY = y.get(i) - mediaY;
            sumaProducto += diferenciaX * diferenciaY;
            sumaX += diferenciaX * diferenciaX;
            sumaY += diferenciaY * diferenciaY;
        }
        if (sumaX == 0 || sumaY == 0) {
            throw new IllegalArgumentException("no se puede calcular la correlación porque una variable no tiene variación.");
        }
        return sumaProducto / Math.sqrt(sumaX * sumaY);
    }

    private static void validarDatos(ArrayList<Double> datos) {
        if (datos == null || datos.isEmpty()) {
            throw new IllegalArgumentException("no hay datos numéricos para calcular.");
        }
    }
}
