/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tortilleriadatos;

import com.mycompany.tortilleriadtos.VentaDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public interface IVentaDAO {
    boolean guardarVenta(VentaDTO venta);
    List<VentaDTO> obtenerTodasLasVentas();
    List<VentaDTO> ventasPorFecha(LocalDate inicio, LocalDate fin);
    boolean cancelarVenta(int idVenta);
}
