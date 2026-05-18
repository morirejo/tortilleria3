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
public class GestorReporte implements IGestorReporte{
    
    private final IReporteDAO dao = new ReporteDAO();
    
    @Override
    public ReporteVentasDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        return dao.obtenerReporte(fechaInicio, fechaFin);
    }
    
}
