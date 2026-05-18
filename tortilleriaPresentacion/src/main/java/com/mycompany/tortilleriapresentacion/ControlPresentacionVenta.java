/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriapresentacion;

import com.mycompany.tortilleriadtos.DetalleVentaDTO;
import com.mycompany.tortilleriadtos.VentaDTO;
import com.mycompany.tortilleriadtos.VentaLocalDTO;
import com.mycompany.tortillerianegocio.FachadaVenta;
import com.mycompany.tortillerianegocio.IFachadaVentas;
import com.mycompany.tortilleriapresentacion.PantallaCancelacion;
import com.mycompany.tortilleriapresentacion.PantallaMetodoPago;
import com.mycompany.tortilleriapresentacion.PantallaTicket;
import com.mycompany.tortilleriapresentacion.PantallaVenta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author MoriTejo
 */
public class ControlPresentacionVenta {
    private IFachadaVentas fachada = new FachadaVenta();
    
    private List<DetalleVentaDTO> carritoActual = new ArrayList<>();
    private double totalActual = 0.0;

    public ControlPresentacionVenta() {
    }

    public void agregarProducto(String nombre, String tipoProducto, double kilos) {
        double subtotal = fachada.calcularTotal(kilos); 
        DetalleVentaDTO producto = new DetalleVentaDTO(nombre, tipoProducto, kilos, subtotal);
        carritoActual.add(producto);
        totalActual += subtotal;
    }

    public double getTotalActual() {
        return totalActual;
    }
    
    public List<DetalleVentaDTO> getCarritoActual() {
        return carritoActual;
    }

    public void solicitarCobro(double efectivoRecibido, String metodoPago, JFrame pantallaPagoActual) {
        int idGenerico = 0; 
        Date fechaVenta = new Date();
        VentaDTO ventaNueva = new VentaLocalDTO(idGenerico, totalActual, fechaVenta, metodoPago, carritoActual);
        
        boolean exito = fachada.confirmarVentaLocal(ventaNueva, efectivoRecibido);
        
        if (exito) {
            double kilosTotales = 0.0;
            for (DetalleVentaDTO producto : carritoActual) {
                kilosTotales += producto.getCantidadKilos();
            }
            double totalDeLaVenta = totalActual;
            double precioPorKg = (kilosTotales > 0) ? (totalDeLaVenta / kilosTotales) : 0;
            carritoActual.clear();
            totalActual = 0.0;
            mostrarPantallaTicket(pantallaPagoActual, kilosTotales, precioPorKg, totalDeLaVenta);
            
        } else {
            JOptionPane.showMessageDialog(pantallaPagoActual, "Error: El efectivo es insuficiente o hubo un problema.", "Pago Rechazado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
//    public void iniciarSistema() {
//        PantallaLogin login = new PantallaLogin(this); 
//        login.setVisible(true);
//    }

    public void mostrarPantallaVenta(JFrame pantallaActual) {
        if (pantallaActual != null) {
            pantallaActual.dispose();
        }
        PantallaVenta venta = new PantallaVenta(this);
        venta.setVisible(true);
    }

    public void mostrarMetodoPago() {
        PantallaMetodoPago pago = new PantallaMetodoPago(this);
        pago.setVisible(true);
    }

    public void mostrarPantallaTicket(JFrame pantallaActual, double kilos, double precioKg, double total) {
        if (pantallaActual != null) {
            pantallaActual.dispose();
        }
        PantallaTicket ticket = new PantallaTicket(this, kilos, precioKg, total);
        ticket.setVisible(true);
    }
    
    public List<VentaDTO> obtenerTodasLasVentas() {
        return fachada.obtenerTodasLasVentas();
    }

    public boolean cancelarVenta(int idVenta) {
        return fachada.cancelarVenta(idVenta);
    }
    
    public void mostrarPantallaCancelacion(JFrame pantallaActual) {
        new PantallaCancelacion(this).setVisible(true);
    }

    public void mostrarPantallaReportes(JFrame pantallaActual) {
        reporte.ControlPresentacionReporte mediadorReportes = new reporte.ControlPresentacionReporte();
            mediadorReportes.iniciarReporte();
    }

    public void mostrarPantallaCierreCaja(JFrame pantallaActual) {
        cierreCaja.ControlPresentacionCierre mediadorCierre = new cierreCaja.ControlPresentacionCierre();
        double[] totales = mediadorCierre.obtenerTotalesDelDia(); 
        cierreCaja.PantallaCierrePrincipal pantalla = new cierreCaja.PantallaCierrePrincipal(mediadorCierre, totales);
        pantalla.setVisible(true);
    }
    
    
}