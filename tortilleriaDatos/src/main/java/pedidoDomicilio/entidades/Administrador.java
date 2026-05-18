/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio.entidades;

/**
 *
 * @author USUARIO
 */
public class Administrador
        extends Empleado {

    public Administrador(String nombre) {

        super(nombre);
    }

    public void generarReporte() {

        System.out.println(
                "Reporte generado");
    }
}