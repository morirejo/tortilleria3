/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tortillerianegocio;

import com.mycompany.tortilleriadtos.VentaDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public interface IFachadaVentas {
    double calcularTotal(double kilos);
    boolean confirmarVentaLocal(VentaDTO ventaDTO, double efectivo);
    List<VentaDTO> obtenerVentas(LocalDate fechaInicio, LocalDate fechaFin);
    List<VentaDTO> obtenerTodasLasVentas();
    double calcularTotalVentas(List<VentaDTO> ventas);
    double calcularKilosTotales(List<VentaDTO> ventas);
    boolean cancelarVenta(int idVenta);
}
