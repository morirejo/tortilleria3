/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cierreCaja;


/**
 *
 * @author MoriTejo
 */
public interface ICajaDAO {
    public double[] obtenerVentasSegmentadas();
    public boolean guardarCorte(CorteCajaDTO corte);
}