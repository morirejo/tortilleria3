/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package reporte;

import java.time.LocalDate;

/**
 *
 * @author marki
 */
public interface IGestorReporte {
    ReporteVentasDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin);
}
