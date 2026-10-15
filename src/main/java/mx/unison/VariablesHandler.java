package mx.unison;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class VariablesHandler {
    public ArrayList<Double> obtenerVariable(Path archivo, String nombre)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        fabrica.setFeature("http://xml.org/sax/features/external-general-entities", false);
        fabrica.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        fabrica.setXIncludeAware(false);
        fabrica.setExpandEntityReferences(false);

        Document documento = fabrica.newDocumentBuilder().parse(archivo.toFile());
        NodeList elementos = documento.getElementsByTagName(nombre);
        if (elementos.getLength() == 0) {
            throw new IllegalArgumentException("la variable '" + nombre + "' no existe en el XML.");
        }

        ArrayList<Double> datos = new ArrayList<>();
        for (int i = 0; i < elementos.getLength(); i++) {
            String texto = elementos.item(i).getTextContent().trim();
            try {
                datos.add(Double.parseDouble(texto));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("la variable '" + nombre + "' contiene un valor no numérico: '" + texto + "'.");
            }
        }
        return datos;
    }
}
