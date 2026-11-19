package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FacturasModelo {
    private ArrayList<Factura> facturas;

    public FacturasModelo() {
        this.facturas = new ArrayList<>();
    }

    public Path obtenerCarpetaDatos() {
        return Paths.get(System.getProperty("user.home"), "Desktop", "datos");
    }

    public Path obtenerRutaTsv() {
        Path enDatos = obtenerCarpetaDatos().resolve("facturas.tsv");
        if (Files.exists(enDatos)) {
            return enDatos;
        }
        return Paths.get(System.getProperty("user.home"), "Desktop", "facturas.tsv");
    }

    public boolean cargarFacturasTsv() {
        facturas.clear();
        Path ruta = obtenerRutaTsv();
        if (!Files.exists(ruta)) {
            return false;
        }

        try {
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            for (int i = 0; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] campos = linea.split("\t");
                if (campos.length < 8) {
                    continue;
                }

                try {
                    String concepto = campos[0].trim();
                    double descuento = Double.parseDouble(campos[1].trim().replace(",", "."));
                    String fecha = campos[2].trim();
                    double importe = Double.parseDouble(campos[3].trim().replace(",", "."));
                    String nif = campos[4].trim();
                    String nombreCliente = campos[5].trim();
                    String direccion = campos[6].trim();
                    double iva = Double.parseDouble(campos[7].trim().replace(",", "."));

                    facturas.add(new Factura(concepto, descuento, fecha, importe,
                            nif, nombreCliente, direccion, iva));
                } catch (NumberFormatException e) {
                    // Si la primera línea es cabecera de texto, se omite sin error
                }
            }
            return !facturas.isEmpty();
        } catch (IOException e) {
            return false;
        }
    }

    public ArrayList<Factura> filtrarFacturasMayoresQue(double valorMinimo) {
        ArrayList<Factura> resultado = new ArrayList<>();
        for (Factura f : facturas) {
            if (f.calcularImporteFinal() > valorMinimo) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    public Path exportarAHtml() throws IOException {
        Path carpeta = obtenerCarpetaDatos();
        Files.createDirectories(carpeta);
        Path rutaHtml = carpeta.resolve("facturas.html");

        String plantillaHtml = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <title>Listado de Facturas</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 24px; }
                        h1 { color: #2c3e50; }
                        table { border-collapse: collapse; width: 100%; }
                        th, td { border: 1px solid #ccc; padding: 8px 10px; text-align: left; }
                        th { background-color: #2c3e50; color: white; }
                        tr:nth-child(even) { background-color: #f4f6f8; }
                        .num { text-align: right; }
                    </style>
                </head>
                <body>
                    <h1>Listado de Facturas</h1>
                    %%TABLA%%
                </body>
                </html>
                """;

        StringBuilder tabla = new StringBuilder();
        tabla.append("<table>\n<thead>\n<tr>")
             .append("<th>Concepto</th><th>Fecha</th><th>NIF</th><th>Cliente</th>")
             .append("<th>Dirección</th><th>Importe Base</th><th>Dto.</th>")
             .append("<th>IVA</th><th>Importe Final</th>")
             .append("</tr>\n</thead>\n<tbody>\n");

        for (Factura f : facturas) {
            tabla.append("<tr>")
                 .append("<td>").append(escaparHtml(f.obtenerConcepto())).append("</td>")
                 .append("<td>").append(escaparHtml(f.obtenerFecha())).append("</td>")
                 .append("<td>").append(escaparHtml(f.obtenerNif())).append("</td>")
                 .append("<td>").append(escaparHtml(f.obtenerNombreCliente())).append("</td>")
                 .append("<td>").append(escaparHtml(f.obtenerDireccion())).append("</td>")
                 .append("<td class=\"num\">").append(String.format(Locale.US, "%.2f EUR", f.obtenerImporte())).append("</td>")
                 .append("<td class=\"num\">").append(String.format(Locale.US, "%.2f", f.obtenerDescuento())).append("</td>")
                 .append("<td class=\"num\">").append(String.format(Locale.US, "%.2f", f.obtenerIva())).append("</td>")
                 .append("<td class=\"num\"><strong>").append(String.format(Locale.US, "%.2f EUR", f.calcularImporteFinal())).append("</strong></td>")
                 .append("</tr>\n");
        }
        tabla.append("</tbody>\n</table>");

        String htmlFinal = plantillaHtml.replace("%%TABLA%%", tabla.toString());
        Files.writeString(rutaHtml, htmlFinal, StandardCharsets.UTF_8);
        return rutaHtml;
    }

    public Path exportarACsv() throws IOException {
        Path carpeta = obtenerCarpetaDatos();
        Files.createDirectories(carpeta);
        Path rutaCsv = carpeta.resolve("facturas.csv");

        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Concepto,Descuento,Fecha,Importe,NIF,Cliente,Direccion,IVA,ImporteFinal");

        for (Factura f : facturas) {
            String fila = String.join(",",
                    escaparCsv(f.obtenerConcepto()),
                    String.format(Locale.US, "%.2f", f.obtenerDescuento()),
                    escaparCsv(f.obtenerFecha()),
                    String.format(Locale.US, "%.2f", f.obtenerImporte()),
                    escaparCsv(f.obtenerNif()),
                    escaparCsv(f.obtenerNombreCliente()),
                    escaparCsv(f.obtenerDireccion()),
                    String.format(Locale.US, "%.2f", f.obtenerIva()),
                    String.format(Locale.US, "%.2f", f.calcularImporteFinal())
            );
            lineas.add(fila);
        }

        Files.write(rutaCsv, lineas, StandardCharsets.UTF_8);
        return rutaCsv;
    }

    private String escaparHtml(String texto) {
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;");
    }

    private String escaparCsv(String campo) {
        if (campo.contains(",") || campo.contains("\"")) {
            return "\"" + campo.replace("\"", "\"\"") + "\"";
        }
        return campo;
    }

    public ArrayList<Factura> obtenerFacturas() {
        return facturas;
    }

    public boolean hayFacturas() {
        return !facturas.isEmpty();
    }
}