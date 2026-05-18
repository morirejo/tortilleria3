/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tortillerianegocio;

import BOs.VentaBO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public interface IGestorVentas {
    double calcularTotal(double kilos);
    boolean procesarVenta(VentaBO venta, double efectivoRecibido);
    List<VentaBO> obtenerVentasPorFecha(LocalDate inicio, LocalDate fin);
    List<VentaBO> obtenerTodasLasVentas();
    double calcularTotaldeVentas(List<VentaBO> ventas);
    double calcularKilosTotales(List<VentaBO> ventas);
    boolean cancelarVenta(int idVenta);
}
