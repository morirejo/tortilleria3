/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriadtos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public abstract class VentaDTO {
    protected int idVenta;
    protected double montoTotal;
    protected Date fechaHora;
    protected String tipoVenta; // Para el filtro de reportes ("LOCAL" o "DOMICILIO")
    protected List<DetalleVentaDTO> carrito;

    public VentaDTO() {
    }

    public VentaDTO(int idVenta, double montoTotal, Date fechaHora, String tipoVenta, List<DetalleVentaDTO> carrito) {
        this.idVenta = idVenta;
        this.montoTotal = montoTotal;
        this.fechaHora = fechaHora;
        this.tipoVenta = tipoVenta;
        this.carrito = carrito;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public List<DetalleVentaDTO> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<DetalleVentaDTO> carrito) {
        this.carrito = carrito;
    }
    
    
    
    
    
}
