package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BibliotecaModelo {
    private ArrayList<Libro> libros;

    public BibliotecaModelo() {
        this.libros = new ArrayList<>();
    }

    public Path obtenerRutaEscritorio(String nombreArchivo) {
        return Paths.get(System.getProperty("user.home"), "Desktop", nombreArchivo);
    }

    // --- PERSISTENCIA BINARIA (SERIALIZACIÓN) ---

    @SuppressWarnings("unchecked")
    public boolean cargarDesdeBinario() throws IOException, ClassNotFoundException {
        Path rutaBin = obtenerRutaEscritorio("libros.bin");
        if (!Files.exists(rutaBin)) {
            return false;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(rutaBin))) {
            Object objetoLeido = ois.readObject();
            if (objetoLeido instanceof ArrayList<?>) {
                this.libros = (ArrayList<Libro>) objetoLeido;
            }
            return true;
        }
    }

    public void guardarEnBinario() throws IOException {
        Path rutaBin = obtenerRutaEscritorio("libros.bin");
        if (rutaBin.getParent() != null) {
            Files.createDirectories(rutaBin.getParent());
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(rutaBin))) {
            oos.writeObject(this.libros);
        }
    }

    // --- OPERACIONES CRUD ---

    public ArrayList<Libro> obtenerLibros() {
        return libros;
    }

    public boolean estaVacia() {
        return libros.isEmpty();
    }

    public boolean agregarLibro(Libro libro) {
        if (libros.contains(libro)) {
            return false;
        }
        libros.add(libro);
        return true;
    }

    public Libro eliminarLibroPorIndice(int indice) {
        if (indice < 0 || indice >= libros.size()) {
            return null;
        }
        return libros.remove(indice);
    }

    public boolean modificarLibroPorIndice(int indice, String nuevoTitulo, String nuevoAutor, int nuevoAnio) {
        if (indice < 0 || indice >= libros.size()) {
            return false;
        }
        Libro libro = libros.get(indice);
        libro.establecerTitulo(nuevoTitulo);
        libro.establecerAutor(nuevoAutor);
        libro.establecerAnioPublicacion(nuevoAnio);
        return true;
    }

    // --- EXPORTACIÓN E IMPORTACIÓN CSV ---

    public Path exportarCsv() throws IOException {
        Path rutaCsv = obtenerRutaEscritorio("libros.csv");
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("ISBN,Titulo,Autor,AnioPublicacion");

        for (Libro l : libros) {
            lineas.add(String.join(",",
                    escaparCsv(l.obtenerIsbn()),
                    escaparCsv(l.obtenerTitulo()),
                    escaparCsv(l.obtenerAutor()),
                    String.valueOf(l.obtenerAnioPublicacion())));
        }

        Files.write(rutaCsv, lineas, StandardCharsets.UTF_8);
        return rutaCsv;
    }

    public int importarCsv() throws IOException {
        Path rutaCsv = obtenerRutaEscritorio("libros.csv");
        if (!Files.exists(rutaCsv)) {
            throw new IOException("No se encontró el archivo libros.csv en el Escritorio.");
        }

        List<String> lineas = Files.readAllLines(rutaCsv, StandardCharsets.UTF_8);
        int agregados = 0;

        for (String linea : lineas) {
            if (linea.trim().isEmpty()) {
                continue;
            }
            ArrayList<String> campos = dividirLineaCsv(linea);
            if (campos.size() < 4) {
                continue;
            }
            try {
                String isbn = campos.get(0).trim();
                String titulo = campos.get(1).trim();
                String autor = campos.get(2).trim();
                int anio = Integer.parseInt(campos.get(3).trim());

                if (!isbn.isEmpty() && agregarLibro(new Libro(isbn, titulo, autor, anio))) {
                    agregados++;
                }
            } catch (NumberFormatException e) {
                // Ignora la fila de cabecera
            }
        }
        return agregados;
    }

    private String escaparCsv(String valor) {
        if (valor.contains(",") || valor.contains("\"") || valor.contains("\n")) {
            return "\"" + valor.replace("\"", "\"\"") + "\"";
        }
        return valor;
    }

    private ArrayList<String> dividirLineaCsv(String linea) {
        ArrayList<String> campos = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean enComillas = false;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            if (enComillas) {
                if (c == '"') {
                    if (i + 1 < linea.length() && linea.charAt(i + 1) == '"') {
                        actual.append('"');
                        i++;
                    } else {
                        enComillas = false;
                    }
                } else {
                    actual.append(c);
                }
            } else {
                if (c == '"') {
                    enComillas = true;
                } else if (c == ',') {
                    campos.add(actual.toString());
                    actual.setLength(0);
                } else {
                    actual.append(c);
                }
            }
        }
        campos.add(actual.toString());
        return campos;
    }

    // --- EXPORTACIÓN E IMPORTACIÓN JSON ---

    public Path exportarJson() throws IOException {
        Path rutaJson = obtenerRutaEscritorio("libros.json");
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < libros.size(); i++) {
            Libro l = libros.get(i);
            sb.append("  {\n")
              .append("    \"isbn\": \"").append(escaparJson(l.obtenerIsbn())).append("\",\n")
              .append("    \"titulo\": \"").append(escaparJson(l.obtenerTitulo())).append("\",\n")
              .append("    \"autor\": \"").append(escaparJson(l.obtenerAutor())).append("\",\n")
              .append("    \"anioPublicacion\": ").append(l.obtenerAnioPublicacion()).append("\n")
              .append("  }");
            if (i < libros.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]");

        Files.writeString(rutaJson, sb.toString(), StandardCharsets.UTF_8);
        return rutaJson;
    }

    public int importarJson() throws IOException {
        Path rutaJson = obtenerRutaEscritorio("libros.json");
        if (!Files.exists(rutaJson)) {
            throw new IOException("No se encontró el archivo libros.json en el Escritorio.");
        }

        String contenido = Files.readString(rutaJson, StandardCharsets.UTF_8).trim();
        if (!contenido.startsWith("[") || !contenido.endsWith("]")) {
            throw new IOException("El archivo libros.json no contiene un array JSON válido.");
        }

        String interior = contenido.substring(1, contenido.length() - 1).trim();
        if (interior.isEmpty()) {
            return 0;
        }

        ArrayList<String> objetosJson = extraerObjetosJson(interior);
        int agregados = 0;

        for (String obj : objetosJson) {
            HashMap<String, String> mapa = parsearCamposObjetoJson(obj);
            String isbn = mapa.get("isbn");
            String titulo = mapa.get("titulo");
            String autor = mapa.get("autor");
            String anioStr = mapa.get("anioPublicacion");

            if (isbn == null || titulo == null || autor == null || anioStr == null) {
                throw new IOException("Faltan campos obligatorios en un objeto de libros.json.");
            }

            try {
                int anio = Integer.parseInt(anioStr.trim());
                if (!isbn.trim().isEmpty() && agregarLibro(new Libro(isbn.trim(), titulo, autor, anio))) {
                    agregados++;
                }
            } catch (NumberFormatException e) {
                throw new IOException("El campo anioPublicacion no es un entero válido en libros.json.");
            }
        }
        return agregados;
    }

    private String escaparJson(String texto) {
        return texto.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

    private ArrayList<String> extraerObjetosJson(String texto) throws IOException {
        ArrayList<String> objetos = new ArrayList<>();
        boolean enComillas = false;
        boolean escapado = false;
        int nivelLlaves = 0;
        int inicioObjeto = -1;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (escapado) {
                escapado = false;
                continue;
            }
            if (c == '\\' && enComillas) {
                escapado = true;
                continue;
            }
            if (c == '"') {
                enComillas = !enComillas;
                continue;
            }
            if (!enComillas) {
                if (c == '{') {
                    if (nivelLlaves == 0) {
                        inicioObjeto = i;
                    }
                    nivelLlaves++;
                } else if (c == '}') {
                    nivelLlaves--;
                    if (nivelLlaves == 0 && inicioObjeto != -1) {
                        objetos.add(texto.substring(inicioObjeto + 1, i));
                        inicioObjeto = -1;
                    } else if (nivelLlaves < 0) {
                        throw new IOException("Estructura de llaves mal formada en libros.json.");
                    }
                }
            }
        }
        if (nivelLlaves != 0) {
            throw new IOException("Llaves sin cerrar en libros.json.");
        }
        return objetos;
    }

    private HashMap<String, String> parsearCamposObjetoJson(String cuerpoObjeto) throws IOException {
        HashMap<String, String> mapa = new HashMap<>();
        ArrayList<String> pares = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean enComillas = false;
        boolean escapado = false;

        for (int i = 0; i < cuerpoObjeto.length(); i++) {
            char c = cuerpoObjeto.charAt(i);
            if (escapado) {
                actual.append(c);
                escapado = false;
                continue;
            }
            if (c == '\\' && enComillas) {
                actual.append(c);
                escapado = true;
                continue;
            }
            if (c == '"') {
                enComillas = !enComillas;
                actual.append(c);
                continue;
            }
            if (c == ',' && !enComillas) {
                pares.add(actual.toString().trim());
                actual.setLength(0);
            } else {
                actual.append(c);
            }
        }
        if (actual.length() > 0) {
            pares.add(actual.toString().trim());
        }

        for (String par : pares) {
            if (par.isEmpty()) {
                continue;
            }
            int posDosPuntos = -1;
            enComillas = false;
            escapado = false;
            for (int i = 0; i < par.length(); i++) {
                char c = par.charAt(i);
                if (escapado) {
                    escapado = false;
                    continue;
                }
                if (c == '\\' && enComillas) {
                    escapado = true;
                    continue;
                }
                if (c == '"') {
                    enComillas = !enComillas;
                } else if (c == ':' && !enComillas) {
                    posDosPuntos = i;
                    break;
                }
            }
            if (posDosPuntos == -1) {
                throw new IOException("Par clave-valor inválido en libros.json.");
            }
            String clave = quitarComillasYDesescapar(par.substring(0, posDosPuntos).trim());
            String valor = quitarComillasYDesescapar(par.substring(posDosPuntos + 1).trim());
            mapa.put(clave, valor);
        }
        return mapa;
    }

    private String quitarComillasYDesescapar(String texto) {
        if (texto.startsWith("\"") && texto.endsWith("\"") && texto.length() >= 2) {
            texto = texto.substring(1, texto.length() - 1);
        }
        return texto.replace("\\\"", "\"")
                    .replace("\\\\", "\\")
                    .replace("\\n", "\n")
                    .replace("\\r", "\r")
                    .replace("\\t", "\t");
    }

    // --- EXPORTACIÓN E IMPORTACIÓN XML ---

    public Path exportarXml() throws Exception {
        Path rutaXml = obtenerRutaEscritorio("libros.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element raiz = doc.createElement("biblioteca");
        doc.appendChild(raiz);

        for (Libro l : libros) {
            Element elemLibro = doc.createElement("libro");
            raiz.appendChild(elemLibro);

            Element elemIsbn = doc.createElement("isbn");
            elemIsbn.setTextContent(l.obtenerIsbn());
            elemLibro.appendChild(elemIsbn);

            Element elemTitulo = doc.createElement("titulo");
            elemTitulo.setTextContent(l.obtenerTitulo());
            elemLibro.appendChild(elemTitulo);

            Element elemAutor = doc.createElement("autor");
            elemAutor.setTextContent(l.obtenerAutor());
            elemLibro.appendChild(elemAutor);

            Element elemAnio = doc.createElement("anioPublicacion");
            elemAnio.setTextContent(String.valueOf(l.obtenerAnioPublicacion()));
            elemLibro.appendChild(elemAnio);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(rutaXml.toFile());
        transformer.transform(source, result);

        return rutaXml;
    }

    public int importarXml() throws Exception {
        Path rutaXml = obtenerRutaEscritorio("libros.xml");
        if (!Files.exists(rutaXml)) {
            throw new IOException("No se encontró el archivo libros.xml en el Escritorio.");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(rutaXml.toFile());
        doc.getDocumentElement().normalize();

        NodeList nodosLibro = doc.getElementsByTagName("libro");
        int agregados = 0;

        for (int i = 0; i < nodosLibro.getLength(); i++) {
            Node nodo = nodosLibro.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nodo;
                String isbn = obtenerTextoEtiqueta(elem, "isbn");
                String titulo = obtenerTextoEtiqueta(elem, "titulo");
                String autor = obtenerTextoEtiqueta(elem, "autor");
                String anioStr = obtenerTextoEtiqueta(elem, "anioPublicacion");

                if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty() || anioStr.isEmpty()) {
                    throw new IOException("Faltan etiquetas obligatorias en un nodo <libro> de libros.xml.");
                }

                try {
                    int anio = Integer.parseInt(anioStr);
                    if (agregarLibro(new Libro(isbn, titulo, autor, anio))) {
                        agregados++;
                    }
                } catch (NumberFormatException e) {
                    throw new IOException("El valor de <anioPublicacion> no es un entero válido en libros.xml.");
                }
            }
        }
        return agregados;
    }

    private String obtenerTextoEtiqueta(Element padre, String etiqueta) {
        NodeList lista = padre.getElementsByTagName(etiqueta);
        if (lista.getLength() == 0) {
            return "";
        }
        return lista.item(0).getTextContent().trim();
    }
}