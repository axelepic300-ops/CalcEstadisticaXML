package mx.unison;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Locale;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Calculadora {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Uso: java -jar calculadora-estadistica-xml.jar <archivo.xml> <variable1> [variable2]");
            System.exit(1);
        }

        Path archivo = Path.of(args[0]);
        if (!Files.isRegularFile(archivo) || !Files.isReadable(archivo)) {
            System.err.println("Error: no se puede abrir el archivo XML: " + archivo);
            System.exit(2);
        }

        try {
            VariablesHandler lector = new VariablesHandler();
            ArrayList<Double> primera = lector.obtenerVariable(archivo, args[1]);
            imprimirEstadisticas(args[1], primera);

            if (args.length == 3) {
                ArrayList<Double> segunda = lector.obtenerVariable(archivo, args[2]);
                imprimirEstadisticas(args[2], segunda);
                if (primera.size() != segunda.size()) {
                    throw new IllegalArgumentException("Las variables tienen distinta cantidad de valores; no se puede calcular la correlación.");
                }
                System.out.printf(Locale.US, "Correlación entre %s y %s: %.4f%n",
                        args[1], args[2], Estadisticas.correlacion(primera, segunda));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error al leer el documento XML: " + e.getMessage());
            System.exit(3);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(4);
        }
    }

    private static void imprimirEstadisticas(String nombre, ArrayList<Double> datos) {
        System.out.println("Estadísticas de " + nombre + " (n=" + datos.size() + "):");
        System.out.printf(Locale.US, "Media: %.4f%n", Estadisticas.media(datos));
        System.out.printf(Locale.US, "Desviación estándar: %.4f%n", Estadisticas.desviacionEstandar(datos));
        System.out.printf(Locale.US, "Varianza: %.4f%n", Estadisticas.varianza(datos));
        System.out.printf(Locale.US, "Máximo: %.4f%n", Estadisticas.maximo(datos));
        System.out.printf(Locale.US, "Mínimo: %.4f%n", Estadisticas.minimo(datos));
    }
}
