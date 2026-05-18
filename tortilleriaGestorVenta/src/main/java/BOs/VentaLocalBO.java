/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import java.util.Date;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public class VentaLocalBO extends VentaBO {
    private String metodoPago;

    public VentaLocalBO(int idVenta, double montoTotal, Date fechaHora, String metodoPago, List<DetalleVentaBO> carrito) {
        super(idVenta, montoTotal, fechaHora, "LOCAL", carrito);
        this.metodoPago = metodoPago;
    }

    public String getMetodoPago() { 
        return metodoPago; 
    }
}
