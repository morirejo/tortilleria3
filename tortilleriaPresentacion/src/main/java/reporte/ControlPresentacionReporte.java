/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import reporte.ReporteVentasDTO;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reporte.FachadaReporte;

/**
 *
 * @author marki
 */
public class ControlPresentacionReporte {
    private final FachadaReporte negocio = new FachadaReporte();
    private ReporteVentasDTO reporteActual;

    
    public void iniciarReporte() {
        new PantallaConfigReporte(this).setVisible(true);
    }
    
    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        new ControlPresentacionReporte().iniciarReporte();
    });
}

    /**
     * Llamado desde PantallaConfigReporte cuando el usuario presiona "Generar".
     * Consulta la capa de negocio y navega a la pantalla de resultados.
     */
    public void procesarReporte(LocalDate fechaInicio, LocalDate fechaFin, JFrame pantallaActual) {
        try {
            this.reporteActual = negocio.generarReporte(fechaInicio, fechaFin);
            pantallaActual.dispose();
            new PantallaReporteVentas(this, reporteActual).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pantallaActual,
                    "Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Navega desde PantallaReporteVentas a PantallaResultadosPeriodo.
     */
    public void verResultadosCompletos(JFrame pantallaActual) {
        new PantallaResultadosPeriodo(this, reporteActual).setVisible(true);
    }

    /**
     * Regresa a la pantalla de configuración desde cualquier pantalla.
     */
    public void regresarAConfig(JFrame pantallaActual) {
        pantallaActual.dispose();
        new PantallaConfigReporte(this).setVisible(true);
    }
}
