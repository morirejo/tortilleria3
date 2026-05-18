/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cierreCaja;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mycompany.tortilleriadatos.ConexionDB;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author MoriTejo
 */
public class CajaDAO implements ICajaDAO {
    private MongoCollection<Document> collectionCortes;
    private MongoCollection<Document> collectionVentas;

    public CajaDAO() {
        this.collectionCortes = ConexionDB.getInstance().getDatabase().getCollection("cortes_caja");
        this.collectionVentas = ConexionDB.getInstance().getDatabase().getCollection("ventas");
    }

    @Override
    public double[] obtenerVentasSegmentadas() {
        double efectivo = 0, credito = 0, debito = 0;
        LocalDate hoy = LocalDate.now();
        Date inicio = Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(hoy.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Document filtro = new Document("fecha", new Document("$gte", inicio).append("$lt", fin));

        try (MongoCursor<Document> cursor = collectionVentas.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Document venta = cursor.next();
                double monto = venta.getDouble("montoTotal");
                String metodo = venta.getString("metodoPago");

                if (metodo == null) metodo = "Efectivo";

                if (metodo.equalsIgnoreCase("Efectivo")) {
                    efectivo += monto;
                } else if (metodo.equalsIgnoreCase("Tarjeta Credito") || metodo.equalsIgnoreCase("Credito")) {
                    credito += monto;
                } else if (metodo.equalsIgnoreCase("Tarjeta Debito") || metodo.equalsIgnoreCase("Debito")) {
                    debito += monto;
                }
            }
        }
        return new double[]{efectivo, credito, debito};
    }

    @Override
    public boolean guardarCorte(CorteCajaDTO corte) {
        try {
            Document doc = new Document("fecha", corte.getFecha())
                    .append("ventasEfectivo", corte.getVentasEfectivo())
                    .append("ventasCredito", corte.getVentasCredito())
                    .append("ventasDebito", corte.getVentasDebito())
                    .append("totalSistema", corte.getTotalSistema())
                    .append("efectivoContado", corte.getEfectivoContado())
                    .append("diferencia", corte.getDiferencia());
            collectionCortes.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
