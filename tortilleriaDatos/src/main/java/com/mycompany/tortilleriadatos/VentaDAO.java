/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriadatos;

import com.mongodb.client.MongoCollection;
import com.mycompany.tortilleriadtos.DetalleVentaDTO;
import com.mycompany.tortilleriadtos.VentaDTO;
import com.mycompany.tortilleriadtos.VentaLocalDTO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/** 
 *
 * @author MoriTejo
 */
public class VentaDAO implements  IVentaDAO{
    private MongoCollection<Document> collection;

    public VentaDAO() {
        this.collection = ConexionDB.getInstance().getDatabase().getCollection("ventas");
    }

    @Override
    public boolean guardarVenta(VentaDTO venta) {
        try {
            List<Document> arrayProductos = new ArrayList<>();
            if (venta.getCarrito() != null) {
                for (DetalleVentaDTO detalle : venta.getCarrito()) {
                    Document docDetalle = new Document("nombreProducto", detalle.getNombreProducto())
                            .append("tipoProducto", detalle.getTipoProducto()) // Guardamos el tipo (Abarrote, Tortilla, etc.)
                            .append("cantidadKilos", detalle.getCantidadKilos())
                            .append("subtotal", detalle.getSubtotal());
                    arrayProductos.add(docDetalle);
                }
            }
            Document docVenta = new Document("idVenta", venta.getIdVenta())
                    .append("montoTotal", venta.getMontoTotal())
                    .append("fecha", venta.getFechaHora())
                    .append("tipoVenta", venta.getTipoVenta()) // "LOCAL" o "DOMICILIO"
                    .append("productos", arrayProductos);
            if (venta.getTipoVenta() != null && venta.getTipoVenta().equals("LOCAL") && venta instanceof VentaLocalDTO) {
                VentaLocalDTO ventaLocal = (VentaLocalDTO) venta;
                docVenta.append("metodoPago", ventaLocal.getMetodoPago());
            }
            collection.insertOne(docVenta);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar la venta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<VentaDTO> ventasPorFecha(LocalDate inicio, LocalDate fin) {
        Date fechaInicio = Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(fin.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var filtro = new Document("fecha", new Document("$gte", fechaInicio).append("$lte", fechaFin));
        var resultados = collection.find(filtro);
        List<VentaDTO> lista = new ArrayList<>();
        for (Document doc : resultados) {
            lista.add(mapearDocumentoAVenta(doc));
        }
        return lista;
    }
    
    @Override
    public List<VentaDTO> obtenerTodasLasVentas() {
        List<VentaDTO> lista = new ArrayList<>();
        for (Document doc : collection.find()) {
            lista.add(mapearDocumentoAVenta(doc));
        }
        return lista;
    }

    @Override
    public boolean cancelarVenta(int idVenta) {
        try {
            collection.deleteOne(new Document("idVenta", idVenta));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private VentaDTO mapearDocumentoAVenta(Document doc) {
        Number totalNum = (Number) doc.get("montoTotal");
        double total = totalNum != null ? totalNum.doubleValue() : 0.0;
        String metodo = doc.getString("metodoPago") != null ? doc.getString("metodoPago") : "Efectivo";
        Date fecha = new Date(); 
        Object fechaObjeto = doc.get("fecha");
        if (fechaObjeto instanceof Date) {
            fecha = (Date) fechaObjeto; 
        } else if (fechaObjeto instanceof Long) {
            fecha = new Date((Long) fechaObjeto); 
        }
        int id = doc.getInteger("idVenta", 0);
        List<DetalleVentaDTO> productos = new ArrayList<>();
        List<Document> prods = (List<Document>) doc.get("productos");
        if (prods != null) {
            for (Document producto : prods) {
                Number kilos = (Number) producto.get("cantidadKilos");
                Number subtotal = (Number) producto.get("subtotal");
                String nombreProd = producto.getString("nombreProducto");
                String tipoProd = producto.getString("tipoProducto") != null ? producto.getString("tipoProducto") : "No clasificado";
                double kilosFinal = (kilos != null) ? kilos.doubleValue() : 0.0;
                double subtotalFinal = (subtotal != null) ? subtotal.doubleValue() : 0.0;
                productos.add(new DetalleVentaDTO(nombreProd, tipoProd, kilosFinal, subtotalFinal));
            }
        }
        return new VentaLocalDTO(id, total, fecha, metodo, productos);
    }
}
