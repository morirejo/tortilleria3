/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cierreCaja;


/**
 *
 * @author MoriTejo
 */
public interface IGestorCaja {
    public CorteCajaDTO prepararResumen(double efectivoFisico);
    public boolean procesarCierre(CorteCajaDTO corte);
    public double[] obtenerTotalesSistema();
}
