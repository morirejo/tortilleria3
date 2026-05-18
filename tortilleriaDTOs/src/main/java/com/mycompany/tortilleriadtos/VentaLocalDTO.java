/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriadtos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public class VentaLocalDTO extends VentaDTO {
    private String metodoPago;
    
    public VentaLocalDTO() {
    }

    public VentaLocalDTO(int idVenta, double montoTotal, Date fechaHora, String metodoPago, List<DetalleVentaDTO> carrito) {
        super(idVenta, montoTotal, fechaHora, "LOCAL", carrito);
        this.metodoPago = metodoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    
}
