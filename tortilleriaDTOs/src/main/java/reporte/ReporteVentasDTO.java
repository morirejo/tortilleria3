/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author marki
 */
public class ReporteVentasDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private double totalIngresos;
    private double totalKilosVendidos;

    private double ingresosTortilla;
    private double ingresosMasa;


    private double kilosTortilla;
    private double kilosMasa;


    private double totalEfectivo;
    private double totalDebito;
    private double totalCredito;


    private List<VentaLineaDTO> lineas;

    public ReporteVentasDTO() {
    }

    public ReporteVentasDTO(LocalDate fechaInicio, LocalDate fechaFin, double totalIngresos, double totalKilosVendidos, double ingresosTortilla, double ingresosMasa, double kilosTortilla, double kilosMasa, double totalEfectivo, double totalDebito, double totalCredito, List<VentaLineaDTO> lineas) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalIngresos = totalIngresos;
        this.totalKilosVendidos = totalKilosVendidos;
        this.ingresosTortilla = ingresosTortilla;
        this.ingresosMasa = ingresosMasa;
        this.kilosTortilla = kilosTortilla;
        this.kilosMasa = kilosMasa;
        this.totalEfectivo = totalEfectivo;
        this.totalDebito = totalDebito;
        this.totalCredito = totalCredito;
        this.lineas = lineas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public double getTotalKilosVendidos() {
        return totalKilosVendidos;
    }

    public void setTotalKilosVendidos(double totalKilosVendidos) {
        this.totalKilosVendidos = totalKilosVendidos;
    }

    public double getIngresosTortilla() {
        return ingresosTortilla;
    }

    public void setIngresosTortilla(double ingresosTortilla) {
        this.ingresosTortilla = ingresosTortilla;
    }

    public double getIngresosMasa() {
        return ingresosMasa;
    }

    public void setIngresosMasa(double ingresosMasa) {
        this.ingresosMasa = ingresosMasa;
    }

    public double getKilosTortilla() {
        return kilosTortilla;
    }

    public void setKilosTortilla(double kilosTortilla) {
        this.kilosTortilla = kilosTortilla;
    }

    public double getKilosMasa() {
        return kilosMasa;
    }

    public void setKilosMasa(double kilosMasa) {
        this.kilosMasa = kilosMasa;
    }

    public double getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(double totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public double getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(double totalDebito) {
        this.totalDebito = totalDebito;
    }

    public double getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(double totalCredito) {
        this.totalCredito = totalCredito;
    }

    public List<VentaLineaDTO> getLineas() {
        return lineas;
    }

    public void setLineas(List<VentaLineaDTO> lineas) {
        this.lineas = lineas;
    } 
}
