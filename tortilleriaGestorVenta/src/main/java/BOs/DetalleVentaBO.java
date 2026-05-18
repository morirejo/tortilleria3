/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

/**
 *
 * @author MoriTejo
 */
public class DetalleVentaBO {
    private String nombreProducto;
    private String tipoProducto;
    private double cantidadKilos;
    private double subtotal;

    public DetalleVentaBO(String nombreProducto, String tipoProducto, double cantidadKilos, double subtotal) {
        this.nombreProducto = nombreProducto;
        this.tipoProducto = tipoProducto;
        this.cantidadKilos = cantidadKilos;
        this.subtotal = subtotal;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public double getCantidadKilos() {
        return cantidadKilos;
    }

    public void setCantidadKilos(double cantidadKilos) {
        this.cantidadKilos = cantidadKilos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    
}
