/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cierreCaja;


/**
 *
 * @author MoriTejo
 */
public class FachadaCaja {
    private IGestorCaja gestor = new GestorCaja();

    public CorteCajaDTO calcularCierre(double efectivoFisico) {
        return gestor.prepararResumen(efectivoFisico);
    }
    
    public double[] obtenerTotalesDia(){
        return gestor.obtenerTotalesSistema();
    }

    public boolean procesarCierre(CorteCajaDTO corte) {
        return gestor.procesarCierre(corte);
    }
}
