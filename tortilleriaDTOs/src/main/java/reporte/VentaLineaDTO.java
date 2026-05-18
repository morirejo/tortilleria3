/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import java.util.Date;

/**
 *
 * @author marki
 */
public class VentaLineaDTO {
    private int folio;
    private Date fecha;
    private String producto;
    private double kilos;
    private double total;
    private String metodoPago;

    public VentaLineaDTO() {
    }

    public VentaLineaDTO(int folio, Date fecha, String producto, double kilos, double total, String metodoPago) {
        this.folio = folio;
        this.fecha = fecha;
        this.producto = producto;
        this.kilos = kilos;
        this.total = total;
        this.metodoPago = metodoPago;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getKilos() {
        return kilos;
    }

    public void setKilos(double kilos) {
        this.kilos = kilos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    
}
