/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import java.time.LocalDate;

/**
 *
 * @author marki
 */
public class FachadaReporte {
    private final IGestorReporte gestor = new GestorReporte();

    public ReporteVentasDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        return gestor.generarReporte(fechaInicio, fechaFin);
    }
}
