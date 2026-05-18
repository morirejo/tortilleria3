/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mycompany.tortilleriadatos.ConexionDB;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author marki
 */
public class ReporteDAO implements IReporteDAO{
    private MongoCollection<Document> collection;

    public ReporteDAO() {
        this.collection = ConexionDB.getInstance().getDatabase().getCollection("ventas");
    }

    @Override
    public ReporteVentasDTO obtenerReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        Date inicio= Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fin= Date.from(fechaFin.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Document filtro = new Document("fecha", new Document("$gte", inicio).append("$lt", fin));

        double totalIngresos = 0;
        double totalKilos = 0;
        double ingresosTortilla = 0;
        double ingresosMasa = 0;
        double kilosTortilla = 0;
        double kilosMasa = 0;
        double totalEfectivo = 0;
        double totalDebito = 0;
        double totalCredito = 0;

        List<VentaLineaDTO> lineas = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Document venta = cursor.next();

                int folio = venta.getInteger("idVenta", 0);
                double montoTotal = toDouble(venta.get("montoTotal"));
                String metodo = venta.getString("metodoPago");
                if (metodo == null) metodo = "Efectivo";

                Date fecha = venta.getDate("fecha");

                // Acumular por método de pago
                switch (metodo.toLowerCase()) {
                    case "efectivo" -> totalEfectivo += montoTotal;
                    case "tarjeta debito" -> totalDebito   += montoTotal;
                    case "tarjeta credito" -> totalCredito  += montoTotal;
                    default -> totalEfectivo += montoTotal;
                }

                totalIngresos += montoTotal;

                // Procesar líneas de producto
                List<Document> productos = (List<Document>) venta.get("productos");
                String productoLinea = "—";
                double kilosLinea = 0;

                if (productos != null && !productos.isEmpty()) {
                    for (Document p : productos) {
                        String nombre = p.getString("nombreProducto");
                        double kilos  = toDouble(p.get("cantidadKilos"));
                        double sub    = toDouble(p.get("subtotal"));

                        kilosLinea  += kilos;
                        totalKilos  += kilos;

                        if (nombre != null && nombre.equalsIgnoreCase("Masa")) {
                            ingresosMasa += sub;
                            kilosMasa    += kilos;
                        } else {
                            ingresosTortilla += sub;
                            kilosTortilla    += kilos;
                        }
                    }
                    productoLinea = productos.get(0).getString("nombreProducto");
                    if (productoLinea == null) productoLinea = "—";
                }

                lineas.add(new VentaLineaDTO(folio, fecha, productoLinea, kilosLinea, montoTotal, metodo));
            }
        }

        return new ReporteVentasDTO(
                fechaInicio, fechaFin,
                totalIngresos, totalKilos,
                ingresosTortilla, ingresosMasa,
                kilosTortilla, kilosMasa,
                totalEfectivo, totalDebito, totalCredito,
                lineas
        );
    }
    // este es para parsear el id jeje
    private double toDouble(Object valor) {
        if (valor instanceof Number n) return n.doubleValue();
        return 0.0;
    }
}
